package org.california.controller;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface BaseController<T> {
    ResponseEntity<Map<String, Object>> create(T dto);
    ResponseEntity<Map<String, Object>> update(T dto, Long id);
    ResponseEntity<Map<String, Object>> findAll();
    ResponseEntity<Map<String, Object>> findById(Long id);
    ResponseEntity<Map<String, Object>> delete(Long id);

}
