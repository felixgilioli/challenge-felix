package br.com.senior.server.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    public Optional<T> findById(ID id) {
        return this.getRepository().findById(id);
    }

    @Override
    public T save(T entity) {
        validPreSave(entity);
        return postSave(this.getRepository().save(preSave(entity)));
    }

    protected void validPreSave(T entity) {

    }

    protected T preSave(T entity) {
        return entity;
    }

    protected T postSave(T entity) {
        return entity;
    }

    @Override
    public void delete(ID id) {
        this.getRepository().deleteById(id);
    }
}
