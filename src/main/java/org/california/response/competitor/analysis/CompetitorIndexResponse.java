package org.california.response.competitor.analysis;

import org.california.entity.competitor.Competitor;

import java.util.ArrayList;
import java.util.List;

public record CompetitorIndexResponse(
        boolean success,
        String message,
        List<Competitor> competitors
) {
    public static final String SUCCESS_MESSAGE = "Запрос успешно выполнен!";
    public static final String FAILURE_MESSAGE = "Список пуст!";

    public static CompetitorIndexResponse of(boolean success, List<Competitor> competitors) {
        if (success) {
            return new CompetitorIndexResponse(
                    true,
                    SUCCESS_MESSAGE,
                    competitors
            );
        }
        return new CompetitorIndexResponse(
                false,
                FAILURE_MESSAGE,
                new ArrayList<>()
        );
    }
}
