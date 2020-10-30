/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.model.Exemplar;
import java.io.Serializable;

/**
 *
 * @author dmeireles
 */
public class ExemplarDAO extends GenericDAO<Exemplar, Long> implements Serializable {
    
    public static ExemplarDAO exemplarDAO;
    
    public ExemplarDAO() {
        super(Exemplar.class);
    }
    
    public static ExemplarDAO getInstance() {
    
        if (exemplarDAO == null) {
            exemplarDAO = new ExemplarDAO();
        }
        return exemplarDAO;
    }
    
}