package dobrovolsky.server.service;

import dobrovolsky.server.domain.EntityObject;

import java.util.List;

public interface Service<T extends EntityObject, PK> {

    void save(T entity);

    void remove(T entity);

    T findById(PK id);

    List<T> findAll();
}
