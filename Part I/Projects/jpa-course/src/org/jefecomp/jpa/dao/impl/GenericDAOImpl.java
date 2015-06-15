/**
 * 
 */
package org.jefecomp.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jefecomp.jpa.dao.GenericDAO;
import org.jefecomp.jpa.dao.PersistenceUnitEnum;

/**
 * @author jefecomp
 *
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

	private ThreadLocal<EntityManager> localStorage;
	
	private EntityManagerFactory emFactory;
	
	
	private void openSession(){
		this.localStorage.set(this.emFactory.createEntityManager());
		this.localStorage.get().getTransaction().begin();
	}
	
	private boolean closeSession(){
		this.localStorage.get().getTransaction().commit();
		this.localStorage.get().close();
		this.localStorage.remove();
		return true;
	}
	
	public GenericDAOImpl() {
		// TODO Auto-generated constructor stub
		this.emFactory = Persistence.createEntityManagerFactory(PersistenceUnitEnum.JPA_COURSE_PU.getPersistenceUnitName());
		
		this.localStorage = new ThreadLocal<EntityManager>();
	}
	
	@Override
	public boolean persist(T entity) {
		// TODO Auto-generated method stub
		this.openSession();
		
		this.localStorage.get().persist(entity);
		
		return this.closeSession();
	}

	@Override
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		this.openSession();
		
		this.localStorage.get().merge(entity);
		
		return this.closeSession();
		
	}

	@Override
	public boolean delete(T entity) {
		// TODO Auto-generated method stub
		this.openSession();
		
		this.localStorage.get().remove(entity);
		
		return this.closeSession();
	}


	@Override
	public <K> T findByPrimaryKey(Class<T> clazz, K primaryKey) {
		// TODO Auto-generated method stub
		this.openSession();
		T result = this.localStorage.get().find(clazz, primaryKey);
		this.closeSession();
		
		return result;
	}
	
	

}
