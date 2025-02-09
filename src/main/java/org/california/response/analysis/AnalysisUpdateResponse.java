package org.california.response.analysis;

import org.california.entity.analysis.Analysis;

public record AnalysisUpdateResponse(
        boolean success,
        String message,
        Analysis analysis
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Не удалось обновить запись!";

    public static AnalysisUpdateResponse of(boolean success, Analysis analysis) {
        if (success) {
            return new AnalysisUpdateResponse(
                    true,
                    SUCCESS_MESSAGE,
                    analysis
            );
        }
        return new AnalysisUpdateResponse(
                false,
                FAILURE_MESSAGE,
                null
        );
    }
}
