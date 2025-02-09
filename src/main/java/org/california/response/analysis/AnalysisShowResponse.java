package org.california.response.analysis;

import org.california.entity.analysis.Analysis;

public record AnalysisShowResponse(
        boolean success,
        String message,
        Analysis analysis
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Анализ не найден!";

    public static AnalysisShowResponse of(boolean success, Analysis analysis) {
        if (success) {
            return new AnalysisShowResponse(
                    true,
                    SUCCESS_MESSAGE,
                    analysis
            );
        }
        return new AnalysisShowResponse(
                false,
                FAILURE_MESSAGE,
                null
        );
    }
}
