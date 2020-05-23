/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author marco
 */
public class DataLayer {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vendas");
    private EntityManager entityManager = this.entityManagerFactory.createEntityManager();

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void initTransaction() {
        if (!this.entityManager.isOpen()) {
            this.entityManager = this.entityManagerFactory.createEntityManager();
        }
        this.entityManager.getTransaction().begin();
    }

    public void closeTransaction() {
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public void clear() {
        this.entityManager.clear();
    }

    public void create(Object object) {
        this.entityManager.persist(object);
        this.entityManager.detach(object);
        this.entityManager.clear();
    }

    public Object read(Object object, int id) {
        Object readObject = entityManager.find(object.getClass(), id);
        return readObject;
    }

    public void update(Object object) {
        this.entityManager.detach(entityManager.merge(object));
    }

    public void delete(Object object) {
        this.entityManager.remove(this.entityManager.merge(object));
    }

    public List<Object> find(Class entity) {
        Query query = this.entityManager.createQuery("FROM " + entity.getSimpleName(), entity);
        return query.getResultList();
    }

    public List<Object> where(String where, Class entity) {
        Query query = this.entityManager.createQuery("FROM " + entity.getSimpleName() + " WHERE " + where);
        return query.getResultList();
    }

    public void rollback() {
        this.entityManager.getTransaction().rollback();
    }
}
