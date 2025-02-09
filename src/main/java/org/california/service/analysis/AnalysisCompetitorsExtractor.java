package org.california.service.analysis;

import org.california.entity.analysis.Analysis;
import org.california.entity.competitor.Competitor;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnalysisCompetitorsExtractor implements ResultSetExtractor<List<Analysis>> {

    @Override
    public List<Analysis> extractData(ResultSet rs) throws SQLException {
        Map<Long, Analysis> analysisMap = new LinkedHashMap<>(); // Чтобы сохранить порядок

        while (rs.next()) {
            Long analysisId = rs.getLong("analysis_id");

            // Если анализ с таким ID еще не добавлен — создаем его
            Analysis analysis = analysisMap.computeIfAbsent(analysisId, id -> {
                try {
                    return new Analysis(
                            id,
                            rs.getString("external_id"),
                            rs.getString("sku"),
                            rs.getDouble("price"),
                            rs.getDouble("purchase"),
                            rs.getInt("quantity"),
                            rs.getTimestamp("analysis_created_at").toLocalDateTime()
                    ).setCompetitor(new ArrayList<>());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            // Если у анализа есть конкурент, добавляем его в список
            Long competitorId = rs.getLong("competitor_id");
            if (competitorId != 0) { // Если null, то вернется 0
                Competitor competitor = new Competitor(
                        competitorId,
                        rs.getString("site"),
                        rs.getString("url"),
                        rs.getDouble("competitor_price"),
                        rs.getBoolean("relevant"),
                        rs.getInt("position"),
                        rs.getTimestamp("competitor_created_at").toLocalDateTime(),
                        analysisId
                );
                analysis.getCompetitor().add(competitor);
            }
        }

        return new ArrayList<>(analysisMap.values());
    }
}
