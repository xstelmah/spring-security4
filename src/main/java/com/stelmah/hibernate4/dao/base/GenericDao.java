package com.stelmah.hibernate4.dao.base;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    PK save(T obj);

    void update(T obj);

    List<T> getAll();

    T getById(PK id);

    void delete(T obj);

}
