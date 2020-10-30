/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.enums.MessageType;
import br.cesjf.bibliotecalpwsd.util.PersistenceUtil;
import br.cesjf.bibliotecalpwsd.util.Message;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

/**
 *
 * @author dmeireles
 */
public abstract class GenericDAO<T, I extends Serializable> {

   protected EntityManager entityManager;

   private Class<T> persistedClass;
   
   public static GenericDAO genericDAO;

   protected GenericDAO() {
   }

   public GenericDAO(Class<T> persistedClass) {
       this();
       this.persistedClass = persistedClass;
       entityManager = PersistenceUtil.getEntityManager();
   }

    public T persist(@Valid T entity) {
        try {
            EntityTransaction t = entityManager.getTransaction();
            t.begin();
            entityManager.merge(entity);
            entityManager.flush();
            t.commit();
            
            Message.logAndScreenMessage(MessageType.INFO, persistedClass.getSimpleName()+ " salvo(a) com sucesso!");
//            entityManager.close();
            return entity;
        } catch (Exception e) {
            if(e.getMessage().contains("ConstraintViolationException")){
                Message.logAndScreenMessage(MessageType.INFO, "Não foi possível salvar o(a) " + persistedClass.getSimpleName() + ", por não ser é único!");
            } else {
                Message.logAndScreenMessage(MessageType.INFO, "Não foi possível salvar o(a) " + persistedClass.getSimpleName() + "!");
            }
            return null;
        }
    }

    public void remove(I id) {
        try {
            T entity = find(id);
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            T mergedEntity = entityManager.merge(entity);
            entityManager.remove(mergedEntity);
            entityManager.flush();
            tx.commit();
            Message.logAndScreenMessage(MessageType.INFO, persistedClass.getSimpleName() + " removido(a) com sucesso!");
        } catch (Exception e) {
            Message.logAndScreenMessage(MessageType.WARNING, "Não foi possível remover o(a) " + persistedClass.getSimpleName() + ", por estar sendo utilizado!");
        }
    }

    public List<T> getList() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(persistedClass);
            query.from(persistedClass);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            Message.logAndScreenMessage(MessageType.WARNING, "Não foram encontrados(as) " + persistedClass.getSimpleName() + "s!");
            return null;
        }
    }

    public T find(I id) {
        try {
            return entityManager.find(persistedClass, id);
        } catch (Exception e) {
            Message.logAndScreenMessage(MessageType.WARNING, "Não foram encontrados(as) " + persistedClass.getSimpleName() + "s!");
            return null;
        }
    }
    
    public List<T> find(String namedQuery, List<List> parameters) {
        Query query = entityManager.createNamedQuery(namedQuery, persistedClass);
        for (List<String> a: parameters) {
            query.setParameter(a.get(0), a.get(1));
        }
        return query.getResultList();
    }
   
}