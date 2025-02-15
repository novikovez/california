package org.california.controller.proxy;

import org.california.dto.proxy.ProxyDto;
import org.california.entity.proxy.Proxy;
import org.california.exception.ProxyShowException;
import org.california.response.Response;
import org.california.service.proxy.ProxyService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/proxy")
public class ProxyControllerImpl implements ProxyController {
    private final ProxyService proxyService;

    public ProxyControllerImpl(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody ProxyDto createProxyDto) {
        try {
            Proxy proxy = this.proxyService.create(createProxyDto);
            return new Response<Proxy>().responseEntity(true, proxy, HttpStatus.CREATED);
        } catch (ProxyShowException e) {
            return new Response<String>().responseEntity(false, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody ProxyDto updateProxyDto, @PathVariable("id") Long id)
    {
        try {
            updateProxyDto.setId(id);
            Optional<Proxy> proxy = this.proxyService.update(updateProxyDto);
            return new Response<Proxy>().responseEntity(true, proxy.orElse(null), HttpStatus.CREATED);
        } catch (ProxyShowException e) {
            return new Response<String>().responseEntity(false, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Long id) {
        try {
            Proxy proxy = this.proxyService.show(id).orElse(null);
            return new Response<Proxy>().responseEntity(true, proxy, HttpStatus.ACCEPTED);
        } catch (ProxyShowException e) {
            return new Response<String>().responseEntity(false, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        try {
            List<Proxy> proxy = this.proxyService.index();
            return new Response<List<Proxy>>().responseEntity(true, proxy, HttpStatus.ACCEPTED);
        } catch (ProxyShowException e) {
            return new Response<String>().responseEntity(false, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long id) {
        try {
            boolean proxy = this.proxyService.delete(id);
            return new Response<Boolean>().responseEntity(true, proxy, HttpStatus.ACCEPTED);
        } catch (ProxyShowException e) {
            return new Response<String>().responseEntity(false, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
