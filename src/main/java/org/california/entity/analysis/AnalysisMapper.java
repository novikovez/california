package org.california.entity.analysis;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalysisMapper implements RowMapper<Analysis> {
    @Override
    public Analysis mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Analysis(
                rs.getLong("id"),
                rs.getString("external_id"),
                rs.getString("sku"),
                rs.getDouble("price"),
                rs.getDouble("purchase"),
                rs.getInt("quantity"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}
