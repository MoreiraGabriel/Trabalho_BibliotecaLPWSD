/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.model.Usuario;
import java.io.Serializable;

/**
 *
 * @author dmeireles
 */
public class UsuarioDAO extends GenericDAO<Usuario, Long> implements Serializable {
    
    public static UsuarioDAO usuarioDAO;
    
    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    public static UsuarioDAO getInstance() {
    
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }
    
}
