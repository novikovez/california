package org.california.dao.analysis;

import org.california.dto.analysis.AnalysisRequestDto;
import org.california.entity.analysis.Analysis;
import org.california.entity.analysis.AnalysisMapper;
import org.california.entity.competitor.Competitor;
import org.california.entity.competitor.CompetitorMapper;
import org.california.exception.analysis.AnalysisDaoException;
import org.california.service.analysis.AnalysisCompetitorsExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("AnalysisDaoImpl")
public class AnalysisDaoImpl implements AnalysisDao {
    JdbcTemplate jdbcTemplate;

    public AnalysisDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Analysis create(AnalysisRequestDto analysisRequestDto) throws AnalysisDaoException {
        String query = "INSERT INTO analysis (external_id, sku, price, purchase, quantity) VALUES (?, ?, ?, ?, ?) RETURNING id";
        Long resultId = jdbcTemplate.queryForObject(
                query, Long.class,
                analysisRequestDto.getExternalId(),
                analysisRequestDto.getSku(),
                analysisRequestDto.getPrice(),
                analysisRequestDto.getPurchase(),
                analysisRequestDto.getQuantity());
        if(resultId == null) {
            throw new AnalysisDaoException("Cannot create analysis");
        }

        if(analysisRequestDto.getCompetitors() != null && !analysisRequestDto.getCompetitors().isEmpty()) {
            for(Competitor competitor : analysisRequestDto.getCompetitors()) {
                String queryCompetitors = "INSERT INTO competitors (analysis_id, site, url, price, relevant, position) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
                jdbcTemplate.queryForObject(
                        queryCompetitors, Long.class,
                        resultId,
                        competitor.getSite(),
                        competitor.getUrl(),
                        competitor.getPrice(),
                        competitor.isRelevant(),
                        competitor.getPosition()
                );
            }
        }

        return this.show(resultId).orElse(null);
    }

    @Override
    public Optional<Analysis> show(Long id) {
        String query = "SELECT * FROM analysis WHERE id = ?";
        Optional<Analysis> optional;
        try {
            //optional = Optional.ofNullable(jdbcTemplate.queryForObject(query, new AnalysisMapper(), id));
            Analysis analysis = jdbcTemplate.queryForObject(query, new AnalysisMapper(), id);
            if(analysis != null) {
                String queryCompetitors = "SELECT * FROM competitors WHERE analysis_id = ?";
                List<Competitor> competitors = jdbcTemplate.query(queryCompetitors, new CompetitorMapper(), id);
                analysis.setCompetitor(competitors);
            }
            optional = Optional.ofNullable(analysis);
        } catch (Exception e) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    @Transactional
    public Optional<Analysis> update(Analysis analysis) throws AnalysisDaoException {
        String query = "UPDATE analysis SET external_id = ?, sku = ?, price = ?, purchase = ?, quantity = ?, created_at = ?" +
                " WHERE id = ?";
        int result = jdbcTemplate.update(query,
                analysis.getExternalId(),
                analysis.getSku(),
                analysis.getPrice(),
                analysis.getPurchase(),
                analysis.getQuantity(),
                LocalDateTime.now(),
                analysis.getId()
        );

        if(analysis.getCompetitor() != null && !analysis.getCompetitor().isEmpty()) {
            for(Competitor competitor : analysis.getCompetitor()) {
                String queryUpdate = "UPDATE competitors SET analysis_id = ?, site = ?, url = ?, price = ?, relevant = ?, position = ?" +
                        " WHERE id = ?";
                int CountString = jdbcTemplate.update(
                        queryUpdate,
                        competitor.getAnalysisId(),
                        competitor.getSite(),
                        competitor.getUrl(),
                        competitor.getPrice(),
                        competitor.isRelevant(),
                        competitor.getPosition(),
                        competitor.getId()
                );
                if(CountString == 0) {
                    throw new AnalysisDaoException("Не удалось обновить конкурента с данным идентификатором: "
                            + competitor.getId() + ". Он не найден!");
                }
            }
        }

        if(result > 0) {
            return this.show(analysis.getId());
        }
        return Optional.empty();
    }

    @Override
    public List<Analysis> index() {
        String query = "SELECT \n" +
                "    a.id AS analysis_id, \n" +
                "    a.external_id, \n" +
                "    a.sku, \n" +
                "    a.price, \n" +
                "    a.purchase, \n" +
                "    a.quantity, \n" +
                "    a.created_at AS analysis_created_at,\n" +
                "\n" +
                "    c.id AS competitor_id, \n" +
                "    c.site, \n" +
                "    c.url, \n" +
                "    c.price AS competitor_price, \n" +
                "    c.relevant, \n" +
                "    c.position, \n" +
                "    c.created_at AS competitor_created_at\n" +
                "FROM analysis a\n" +
                "LEFT JOIN competitors c ON a.id = c.analysis_id\n" +
                "ORDER BY a.id;";
        return jdbcTemplate.query(query, new AnalysisCompetitorsExtractor());

    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        String query = "DELETE FROM analysis WHERE id = ?";
        int result = jdbcTemplate.update(query, id);
        if(result > 0) {
            String queryCompetitors = "DELETE FROM competitors WHERE analysis_id = ?";
            jdbcTemplate.update(queryCompetitors, id);
            return true;
        }
        return false;
    }
}
