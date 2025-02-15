package org.california.entity.proxy;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProxyMapper implements RowMapper<Proxy> {
    @Override
    public Proxy mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Proxy(
                rs.getLong("id"),
                rs.getString("ip"),
                rs.getInt("port"),
                rs.getString("user_name"),
                rs.getString("password"),
                rs.getBoolean("status"),
                rs.getInt("request"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
