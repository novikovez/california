package org.california.dao.competitor;

import org.california.dto.competitor.CompetitorRequestDto;
import org.california.entity.competitor.Competitor;
import org.california.entity.competitor.CompetitorMapper;
import org.california.exception.analysis.AnalysisDaoException;
import org.california.exception.analysis.CompetitorDaoException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("CompetitorDaoImpl")
public class CompetitorDaoImpl implements CompetitorDao {
    JdbcTemplate jdbcTemplate;

    public CompetitorDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Competitor create(CompetitorRequestDto competitorRequestDto) {
        try {
            String query = "INSERT INTO competitors (analysis_id, site, url, price, relevant, position, product) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
            Long resultId = jdbcTemplate.queryForObject(
                    query, Long.class,
                    competitorRequestDto.getAnalysisId(),
                    competitorRequestDto.getSite(),
                    competitorRequestDto.getUrl(),
                    competitorRequestDto.getPrice(),
                    competitorRequestDto.isRelevant(),
                    competitorRequestDto.getPosition(),
                    competitorRequestDto.getProduct()
            );
            if(resultId == null) {
                throw new CompetitorDaoException("Не удалось записать конкурента");
            }
            return this.show(resultId).orElse(null);
        } catch (DataIntegrityViolationException exception) {
            throw new CompetitorDaoException("Не удалось записать конкурента! Возможно ID анализа указан неверно!");
        }

    }

    @Override
    public Optional<Competitor> show(Long id) {
        String query = "SELECT * FROM competitors WHERE id = ?";
        Optional<Competitor> optional;
        try {
            optional = Optional.ofNullable(jdbcTemplate.queryForObject(query, new CompetitorMapper(), id));
        } catch (Exception e) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public Optional<Competitor> update(Competitor competitor) {
        String query = "UPDATE competitors SET analysis_id = ?, site = ?, url = ?, price = ?, relevant = ?, position = ?," +
                "created_at = ?, product = ? WHERE id = ?";
        int result = jdbcTemplate.update(query,
                competitor.getAnalysisId(),
                competitor.getSite(),
                competitor.getUrl(),
                competitor.getPrice(),
                competitor.isRelevant(),
                competitor.getPosition(),
                LocalDateTime.now(),
                competitor.getProductName(),
                competitor.getId()
        );
        if(result > 0) {
            return Optional.of(competitor);
        }
        return Optional.empty();
    }

    @Override
    public List<Competitor> index() {
        String query = "SELECT * FROM competitors";
        return jdbcTemplate.query(query, new CompetitorMapper());
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM competitors WHERE id = ?";
        int result = jdbcTemplate.update(query, id);
        return result > 0;
    }
}
