/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.model.Autor;
import java.io.Serializable;

/**
 *
 * @author dmeireles
 */
public class AutorDAO extends GenericDAO<Autor, Long> implements Serializable {
    
    public static AutorDAO autorDAO;
    
    public AutorDAO() {
        super(Autor.class);
    }
    
    public static AutorDAO getInstance() {
    
        if (autorDAO == null) {
            autorDAO = new AutorDAO();
        }
        return autorDAO;
    }
    
}