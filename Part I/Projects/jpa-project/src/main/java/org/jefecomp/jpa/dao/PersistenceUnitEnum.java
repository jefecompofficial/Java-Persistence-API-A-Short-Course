/**
 * 
 */
package org.jefecomp.jpa.dao;

/**
 * @author jefecomp
 *
 */
public enum PersistenceUnitEnum {
	
	JPA_COURSE_PU("JPA_COURSE_PU");
	
	private String persistenceUnitName;
	
	private PersistenceUnitEnum(String persistenceUnitName) {
		
		this.persistenceUnitName = persistenceUnitName;
	}
	
	public String getPersistenceUnitName(){
		return this.persistenceUnitName;
	}

}
