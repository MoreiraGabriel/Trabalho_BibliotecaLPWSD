/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.model.Emprestimo;
import java.io.Serializable;

/**
 *
 * @author dmeireles
 */
public class EmprestimoDAO extends GenericDAO<Emprestimo, Long> implements Serializable {
    
    public static EmprestimoDAO emprestimoDAO;
    
    public EmprestimoDAO() {
        super(Emprestimo.class);
    }
    
    public static EmprestimoDAO getInstance() {
    
        if (emprestimoDAO == null) {
            emprestimoDAO = new EmprestimoDAO();
        }
        return emprestimoDAO;
    }
    
}
