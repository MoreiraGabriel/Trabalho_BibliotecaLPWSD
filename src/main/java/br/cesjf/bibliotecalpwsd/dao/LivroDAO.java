/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.model.Livro;
import java.io.Serializable;

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
    
}