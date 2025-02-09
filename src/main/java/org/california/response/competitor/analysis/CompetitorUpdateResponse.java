package org.california.response.competitor.analysis;

import org.california.entity.competitor.Competitor;

public record CompetitorUpdateResponse(
        boolean success,
        String message,
        Competitor analysis
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Не удалось обновить запись!";

    public static CompetitorUpdateResponse of(boolean success, Competitor competitor) {
        if (success) {
            return new CompetitorUpdateResponse(
                    true,
                    SUCCESS_MESSAGE,
                    competitor
            );
        }
        return new CompetitorUpdateResponse(
                false,
                FAILURE_MESSAGE,
                null
        );
    }
}
