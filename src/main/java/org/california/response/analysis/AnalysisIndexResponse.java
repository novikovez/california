package org.california.response.analysis;

import org.california.entity.analysis.Analysis;

import java.util.ArrayList;
import java.util.List;

public record AnalysisIndexResponse(
        boolean success,
        String message,
        List<Analysis> analysis
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Список пуст!";

    public static AnalysisIndexResponse of(boolean success, List<Analysis> analysis) {
        if (success) {
            return new AnalysisIndexResponse(
                    true,
                    SUCCESS_MESSAGE,
                    analysis
            );
        }
        return new AnalysisIndexResponse(
                false,
                FAILURE_MESSAGE,
                new ArrayList<>()
        );
    }
}
