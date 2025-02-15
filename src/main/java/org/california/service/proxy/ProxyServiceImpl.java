package org.california.service.proxy;

import org.california.dao.proxy.ProxyDao;
import org.california.dto.proxy.ProxyDto;
import org.california.entity.proxy.Proxy;
import org.california.exception.ProxyShowException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProxyServiceImpl implements ProxyService {
    private ProxyDao proxyDao;

    public ProxyServiceImpl(ProxyDao proxyDao) {
        this.proxyDao = proxyDao;
    }

    @Override
    public Proxy create(ProxyDto proxyDto) {
        Proxy proxy = this.proxyDao.create(proxyDto);
        if(proxy != null) {
            return proxy;
        }
        throw new ProxyShowException("Не удалось создать запись!");
    }

    @Override
    public Optional<Proxy> show(Long id) {
        try {
            Optional<Proxy> proxy = this.proxyDao.show(id);
            if(proxy.isPresent()) {
                return proxy;
            }
            throw new ProxyShowException("Не удалось найти запись!");
        } catch (EmptyResultDataAccessException e) {
            throw new ProxyShowException("Не удалось найти запись!");
        }

    }

    @Override
    public List<Proxy> index() {
        return this.proxyDao.index();
    }

    @Override
    public Optional<Proxy> update(ProxyDto proxyDto) {
        return this.proxyDao.update(proxyDto);
    }

    @Override
    public boolean delete(Long id) {
        return this.proxyDao.delete(id);
    }
}
