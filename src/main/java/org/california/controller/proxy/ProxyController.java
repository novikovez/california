package org.california.controller.proxy;

import org.california.controller.BaseController;
import org.california.dto.proxy.ProxyDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProxyController extends BaseController<ProxyDto> {
    ResponseEntity<Map<String, Object>> create(ProxyDto createProxyDto);
    ResponseEntity<Map<String, Object>> update(ProxyDto updateProxyDto, Long id);
    ResponseEntity<Map<String, Object>> findById(Long id);
    ResponseEntity<Map<String, Object>> findAll();
    ResponseEntity<Map<String, Object>> delete(Long id);

}
