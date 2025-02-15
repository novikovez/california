package org.california.dao.proxy;

import org.california.dto.proxy.ProxyDto;
import org.california.entity.proxy.Proxy;
import org.california.service.BaseService;

import java.util.List;
import java.util.Optional;

public interface ProxyDao extends BaseService<Proxy, ProxyDto> {
    Proxy create(ProxyDto proxyDto);
    Optional<Proxy> show(Long id);
    List<Proxy> index();
    Optional<Proxy> update(ProxyDto proxyDto);
    boolean delete(Long id);
}
