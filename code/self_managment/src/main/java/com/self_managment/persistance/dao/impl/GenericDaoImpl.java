package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.self_managment.persistance.dao.GenericDao;
import com.self_managment.util.CustomHibernateDaoSupport;

public abstract class GenericDaoImpl<E, PK extends Serializable> extends
	CustomHibernateDaoSupport implements GenericDao<E, PK> {

    @SuppressWarnings("unchecked")
    public PK save(E newInstance) {
	return (PK) getHibernateTemplate().save(newInstance);
    }

    @SuppressWarnings("unchecked")
    public E findById(PK id) {
	return (E) getHibernateTemplate().get(getEntityClass(), id);
    }

    @SuppressWarnings("unchecked")
    public List<E> findAll() {
	return getHibernateTemplate().findByCriteria(createDetachedCriteria());
    }

    @SuppressWarnings("unchecked")
    public List<E> findAllByProperty(String propertyName, Object value) {
	DetachedCriteria criteria = createDetachedCriteria();
	criteria.add(Restrictions.eq(propertyName, value));
	return getHibernateTemplate().findByCriteria(criteria);
    }

    @SuppressWarnings("unchecked")
    public List<E> findByExample(E object) {
	List<E> resultList = getHibernateTemplate().findByExample(object, 0, 1);
	return resultList;
    }

    @SuppressWarnings("unchecked")
    public List<E> findByExample(E object, int firstResult, int maxResults) {
	List<E> resultList = getHibernateTemplate().findByExample(object,
		firstResult, maxResults);
	return resultList;
    }

    public void update(E transientObject) {
	getHibernateTemplate().update(transientObject);
	getSession().flush();
    }

    public void saveOrUpdate(E transientObject) {
	getHibernateTemplate().saveOrUpdate(transientObject);
	getSession().flush();
    }

    public void delete(E persistentObject) {
	getHibernateTemplate().delete(persistentObject);
	getSession().flush();
    }

    protected abstract Class<E> getEntityClass();

    protected DetachedCriteria createDetachedCriteria() {
	return DetachedCriteria.forClass(getEntityClass());
    }

}
