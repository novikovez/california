package org.california.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <T, S> {
    T create(S request);
    Optional<T> show(Long id);
    List<T> index();
    Optional<T> update(S request);
    boolean delete(Long id);
}
