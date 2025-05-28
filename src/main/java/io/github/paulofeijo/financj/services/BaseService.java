package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.entities.EntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public abstract class BaseService<T extends EntityBase, R extends JpaRepository<T, Long>> {

    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public T update(Long id, T entity) {
        if (!repository.existsById(id)) {
            return null;
        }
        return repository.save(entity);
    }

    public T getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<T> getAll() {
        return repository.findAll();
    }
}