package com.self_managment.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDService<E, PK extends Serializable> {
    PK save(E newInstance);

    void update(E transientObject);

    void saveOrUpdate(E transientObject);

    void delete(E persistentObject);

    E findById(PK id);

    List<E> findAll();

    List<E> findAllByProperty(String propertyName, Object value);
}
