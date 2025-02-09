package org.california.response.analysis;

import org.california.entity.analysis.Analysis;

public record AnalysisCreateResponse(
        boolean success,
        String message,
        Analysis analysis
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Не удалось выполнить запрос";

    public static AnalysisCreateResponse of(boolean success, Analysis analysis) {
        if (success) {
            return new AnalysisCreateResponse(
                    true,
                    SUCCESS_MESSAGE,
                    analysis
            );
        }
        return new AnalysisCreateResponse(
                false,
                FAILURE_MESSAGE,
                null
        );
    }
}
