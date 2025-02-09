package org.california.exception.analysis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnalysisDaoException extends RuntimeException {
    public AnalysisDaoException(String message) {
        super(message);
    }
}
