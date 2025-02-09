package org.california.entity.competitor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompetitorMapper implements RowMapper<Competitor> {
    @Override
    public Competitor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Competitor(
                rs.getLong("id"),
                rs.getString("site"),
                rs.getString("url"),
                rs.getDouble("price"),
                rs.getBoolean("relevant"),
                rs.getInt("position"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getLong("analysis_id")
        );
    }
}
