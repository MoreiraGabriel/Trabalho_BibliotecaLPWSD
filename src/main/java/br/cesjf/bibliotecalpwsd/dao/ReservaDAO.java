/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.model.Reserva;
import java.io.Serializable;

/**
 *
 * @author dmeireles
 */
public class ReservaDAO extends GenericDAO<Reserva, Long> implements Serializable {
    
    public static ReservaDAO reservaDAO;
    
    public ReservaDAO() {
        super(Reserva.class);
    }
    
    public static ReservaDAO getInstance() {
    
        if (reservaDAO == null) {
            reservaDAO = new ReservaDAO();
        }
        return reservaDAO;
    }
    
}