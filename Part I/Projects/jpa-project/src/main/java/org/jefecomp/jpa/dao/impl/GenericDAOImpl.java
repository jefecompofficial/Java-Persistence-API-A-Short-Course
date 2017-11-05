/**
Copyright (c) 2015, jefecomp (jefecomp.official@gmail.com)
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

* Neither the name of my-courses nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.jefecomp.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

import org.jefecomp.jpa.dao.GenericDAO;
import org.jefecomp.jpa.dao.PersistenceUnitEnum;
import java.util.List;

/**
 * @author jefecomp
 *
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private static ThreadLocal<EntityManager> em;
	
    private static EntityManagerFactory emFactory;

    static{
	init();
    }

    private static void init(){

	emFactory = Persistence.createEntityManagerFactory(PersistenceUnitEnum.JPA_COURSE_PU.getPersistenceUnitName());
		
	em = new ThreadLocal<EntityManager>();
    }
	
    private void openSession(){
	em.set(emFactory.createEntityManager());
	em.get().getTransaction().begin();
    }
	
    private boolean closeSession(){
	em.get().getTransaction().commit();
	em.get().close();
	em.remove();
	return true;
    }
	
    public GenericDAOImpl() {}
    
    @Override
    public boolean persist(T entity) {
		
	this.openSession();
		
	em.get().persist(entity);
		
	return this.closeSession();
    }

    @Override
    public boolean update(T entity) {
		
	this.openSession();
		
	em.get().merge(entity);
		
	return this.closeSession();
		
    }

    @Override
    public boolean delete(T entity) {
		
	this.openSession();
		
	em.get().remove(entity);
		
	return this.closeSession();
    }

    @Override
    public boolean deleteAll(Class<T> clazz){

	this.openSession();
	CriteriaDelete<T> criteria = em.get().getCriteriaBuilder().createCriteriaDelete(clazz);
	int deletedEntities = em.get().createQuery(criteria).executeUpdate();
	return this.closeSession() && deletedEntities > 0;
    }


    @Override
    public <K> T findByPrimaryKey(Class<T> clazz, K primaryKey) {
		
	this.openSession();
	T result = em.get().find(clazz, primaryKey);
	this.closeSession();
		
	return result;
    }

    @Override
    public List<T> findAll(Class<T> clazz){

	this.openSession();

	CriteriaQuery<T> criteria = em.get().getCriteriaBuilder().createQuery(clazz);

	List<T> resultList = em.get().createQuery(criteria).getResultList();

	this.closeSession();

	return resultList;
    }
}
