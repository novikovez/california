package org.california.response.analysis;

public record AnalysisDeleteResponse(
        boolean success,
        String message
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Анализ не удален!";

    public static AnalysisDeleteResponse of(boolean success) {
        if (success) {
            return new AnalysisDeleteResponse(
                    true,
                    SUCCESS_MESSAGE

            );
        }
        return new AnalysisDeleteResponse(
                false,
                FAILURE_MESSAGE
        );
    }
}
