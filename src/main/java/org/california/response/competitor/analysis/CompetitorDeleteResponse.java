package org.california.response.competitor.analysis;

public record CompetitorDeleteResponse(
        boolean success,
        String message
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Анализ не удален!";

    public static CompetitorDeleteResponse of(boolean success) {
        if (success) {
            return new CompetitorDeleteResponse(
                    true,
                    SUCCESS_MESSAGE

            );
        }
        return new CompetitorDeleteResponse(
                false,
                FAILURE_MESSAGE
        );
    }
}
