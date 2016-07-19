package com.stelmah.hibernate4.dao.base;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@Repository
public class AbstractDao<T, PK extends Serializable> implements GenericDao<T, PK> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PK save(T obj) {
        return (PK) this.getSession().save(obj);
    }

    @Override
    public void update(T obj) {
        this.getSession().update(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Criteria criteria = getSession().createCriteria(
                this.getGenericEntityClass());
        return  (List<T>) criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(PK id) {
        return (T) getSession().get(this.getGenericEntityClass(), id);
    }

    @Override
    public void delete(T obj) {
        getSession().delete(obj);
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass()
                .getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
}
