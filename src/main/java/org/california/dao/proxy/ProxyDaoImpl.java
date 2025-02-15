package org.california.dao.proxy;

import org.california.dto.proxy.ProxyDto;
import org.california.entity.proxy.Proxy;
import org.california.entity.proxy.ProxyMapper;
import org.california.exception.ProxyShowException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ProxyDaoImpl")
public class ProxyDaoImpl implements ProxyDao {
    JdbcTemplate jdbcTemplate;

    public ProxyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Proxy create(ProxyDto proxyDto) {
        String query = "insert into proxy (ip, port, user_name, password) values (?, ?, ?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(
                query, Long.class,
                proxyDto.getIp(),
                proxyDto.getPort(),
                proxyDto.getUser(),
                proxyDto.getPassword()
        );
        return this.show(id).orElse(null);
    }

    @Override
    public Optional<Proxy> show(Long id) {
        String query = "select * from proxy where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, new ProxyMapper(), id));
    }

    @Override
    public List<Proxy> index() {
        String query = "select * from proxy";
        return jdbcTemplate.query(query, new ProxyMapper());

    }

    @Override
    public Optional<Proxy> update(ProxyDto proxyDto) {
        String query = "update proxy set user_name = ?, password = ?, ip = ?, port = ?, status = ? where id = ?";
        int result = jdbcTemplate.update(
                query,
                proxyDto.getUser(),
                proxyDto.getPassword(),
                proxyDto.getIp(),
                proxyDto.getPort(),
                proxyDto.isStatus(),
                proxyDto.getId()
        );
        if(result == 1) {
            return this.show(proxyDto.getId());
        }
        throw new ProxyShowException("Не удалось найти запись!");
    }

    @Override
    public boolean delete(Long id) {
        String query = "delete from proxy where id = ?";
        int result = jdbcTemplate.update(query, id);
        if(result == 1) {
            return true;
        }
        throw new ProxyShowException("Не удалось найти запись!");
    }
}
