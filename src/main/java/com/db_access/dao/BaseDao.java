package com.db_access.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {

    Optional<T> getById(Long id);

    List<T> getByRecipient(String recipient);

    List<T> getAll();

    void save(T t);

    void updateById(T t);

    void deleteById(Long id);

}
