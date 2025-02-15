package org.california.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Response<T> {
    public ResponseEntity<Map<String, Object>> responseEntity(boolean success, T body, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        if(success) {
            response.put("data", body);
            return ResponseEntity.ok().body(response);
        }
        response.put("status", status.value());
        response.put("timestamp", Instant.now());
        response.put("error", body);
        return ResponseEntity.badRequest().body(response);
    }
}
