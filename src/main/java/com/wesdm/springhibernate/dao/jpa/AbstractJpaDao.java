package com.wesdm.springhibernate.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

public abstract class AbstractJpaDao {
	@PersistenceContext
    private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	public void persist(Object obj){
		getEntityManager().persist(obj);
	}
	
	public void delete(Object obj) {
		getEntityManager().remove(obj);
	}
	
	public Session getSession() {
		return getEntityManager().unwrap(Session.class);
	}
}
