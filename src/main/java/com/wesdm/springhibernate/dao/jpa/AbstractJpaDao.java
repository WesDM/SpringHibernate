package com.wesdm.springhibernate.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

public abstract class AbstractJpaDao {
	
	/*
	 * Container managed entity manager (defined in persistenceJPA.xml).  Open and close managed by spring
	 * 
	 * per JPA specifications EntityManager instances are not thread safe, but if Spring handles them, they are made thread safe
	 */
	@PersistenceContext
    private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
	
	protected void persist(Object obj){
		em.persist(obj);
	}
	
	protected void remove(Object obj) {
		em.remove(obj);
	}
	
	protected Session getSession() {
		return em.unwrap(Session.class);
	}
}
