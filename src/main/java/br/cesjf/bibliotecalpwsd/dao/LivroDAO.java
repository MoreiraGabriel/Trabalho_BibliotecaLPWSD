/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.model.Livro;
import br.cesjf.bibliotecalpwsd.util.PersistenceUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author dmeireles
 */
public class LivroDAO extends GenericDAO<Livro, Long> implements Serializable {
    
    public static LivroDAO livroDAO;
    
    public LivroDAO() {
        super(Livro.class);
    }
    
    public static LivroDAO getInstance() {
    
        if (livroDAO == null) {
            livroDAO = new LivroDAO();
        }
        return livroDAO;
    }
    
    public List<Livro> buscarTop5() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Livro As a Order By id Desc").setMaxResults(2);
        System.out.println("Aqui" + query.toString());
        return query.getResultList();
    }
    
}