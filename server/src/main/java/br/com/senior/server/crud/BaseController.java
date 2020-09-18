package br.com.senior.server.crud;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class BaseController<T, ID> {

    protected abstract BaseService<T, ID> getService();

    @GetMapping("/{id}")
    public T findById(@PathVariable ID id) {
        return getService().findById(id).orElse(null);
    }

    @PostMapping
    public T save(@Valid @RequestBody T entity) {
        return getService().save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        getService().delete(id);
    }
}
