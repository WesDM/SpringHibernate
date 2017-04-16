package com.wesdm.springhibernate.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

public abstract class AbstractJpaDao {
	@PersistenceContext
    private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
	
	protected void persist(Object obj){
		getEntityManager().persist(obj);
	}
	
	protected void delete(Object obj) {
		getEntityManager().remove(obj);
	}
	
	protected Session getSession() {
		return getEntityManager().unwrap(Session.class);
	}
}
