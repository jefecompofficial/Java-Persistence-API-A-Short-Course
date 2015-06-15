/**
 * 
 */
package org.jefecomp.jpa.dao;



/**
 * @author jefecomp
 *
 */
public interface GenericDAO<T> {
	
	public boolean persist(T entity);
	
	public boolean update(T entity);
	
	public boolean delete(T entity);
	
	public <K> T findByPrimaryKey(Class<T> clazz, K primaryKey);

}
