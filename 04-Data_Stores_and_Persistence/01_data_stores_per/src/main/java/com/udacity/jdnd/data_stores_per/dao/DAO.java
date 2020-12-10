package com.udacity.jdnd.data_stores_per.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(Long id);
    Collection<T> getAll();
    Long save(T t);
    void update(T t);
    void delete(T t);
}
