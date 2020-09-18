package br.com.senior.server.crud;

import java.util.Optional;

public interface BaseService<T, ID> {

    Optional<T> findById(ID id);

    T save(T entity);

    void delete(ID id);

}
