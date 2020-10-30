/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dao;

import br.cesjf.bibliotecalpwsd.model.Editora;
import java.io.Serializable;

/**
 *
 * @author dmeireles
 */
public class EditoraDAO extends GenericDAO<Editora, Long> implements Serializable {
    
    public static EditoraDAO editoraDAO;
    
    public EditoraDAO() {
        super(Editora.class);
    }
    
    public static EditoraDAO getInstance() {
    
        if (editoraDAO == null) {
            editoraDAO = new EditoraDAO();
        }
        return editoraDAO;
    }
    
}