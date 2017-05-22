package com.wesdm.springhibernate.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

public abstract class AbstractJpaDao {
	
	/*
	 * Container managed entity manager (defined in persistenceJPA.xml).  Open and close managed by spring
	 */
	@PersistenceContext
    private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
	
	protected void persist(Object obj){
		getEntityManager().persist(obj);
	}
	
	protected void remove(Object obj) {
		getEntityManager().remove(obj);
	}
	
	protected Session getSession() {
		return getEntityManager().unwrap(Session.class);
	}
}
