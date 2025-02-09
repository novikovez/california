package org.california.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T, S> {
    T create(S request);
    Optional<T> show(Long id);
    Optional<T> update(T entity);
    List<T> index();
    boolean delete(Long id);
}
