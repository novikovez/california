package org.california.response.competitor.analysis;
import org.california.entity.competitor.Competitor;

public record CompetitorShowResponse(
        boolean success,
        String message,
        Competitor competitor
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Конкурент не найден!";

    public static CompetitorShowResponse of(boolean success, Competitor competitor) {
        if (success) {
            return new CompetitorShowResponse(
                    true,
                    SUCCESS_MESSAGE,
                    competitor
            );
        }
        return new CompetitorShowResponse(
                false,
                FAILURE_MESSAGE,
                null
        );
    }
}
