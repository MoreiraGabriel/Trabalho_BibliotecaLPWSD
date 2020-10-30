/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.model.Assunto;
import java.io.Serializable;

/**
 *
 * @author dmeireles
 */
public class AssuntoDAO extends GenericDAO<Assunto, Long> implements Serializable {
    
    public static AssuntoDAO assuntoDAO;
    
    public AssuntoDAO() {
        super(Assunto.class);
    }
    
    public static AssuntoDAO getInstance() {
        if (assuntoDAO == null) {
            assuntoDAO = new AssuntoDAO();
        }
        return assuntoDAO;
    }
    
}