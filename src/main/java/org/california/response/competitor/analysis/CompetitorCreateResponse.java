package org.california.response.competitor.analysis;

import org.california.entity.competitor.Competitor;

public record CompetitorCreateResponse(
        boolean success,
        String message,
        Competitor competitor
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Не удалось выполнить запрос";

    public static CompetitorCreateResponse of(boolean success, Competitor competitor) {
        if (success) {
            return new CompetitorCreateResponse(
                    true,
                    SUCCESS_MESSAGE,
                    competitor
            );
        }
        return new CompetitorCreateResponse(
                false,
                FAILURE_MESSAGE,
                null
        );
    }
}
