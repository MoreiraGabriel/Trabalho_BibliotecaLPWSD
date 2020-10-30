/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.UsuarioDAO;
import br.cesjf.bibliotecalpwsd.enums.UserType;
import br.cesjf.bibliotecalpwsd.model.Usuario;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.event.ActionEvent;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;

/**
 *
 * @author dmeireles
 */
@Named
@ViewScoped
public class UsuarioFormBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private Long id;
    Map<String, UserType> tipos;

    //construtor
    public UsuarioFormBean() {
        tipos = new HashMap<String, UserType>();
        for (UserType userType: UserType.values()) {
            tipos.put(userType.getUserType(), userType);
        }
    }
    
    public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        if (id != null) {
            usuario = UsuarioDAO.getInstance().find(id);
        } else {
            usuario = Usuario.Builder
                             .newInstance()
                             .build();
        }
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        UsuarioDAO.getInstance().persist(usuario);
    }
    
    public void exclude(ActionEvent actionEvent) {
        UsuarioDAO.getInstance().remove(usuario.getId());
    }

    //getters and setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void clear() {
        usuario = Usuario.Builder
                         .newInstance()
                         .build();
    }
    
    public boolean isNew() {
        return usuario == null || usuario.getId() == null || usuario.getId() == 0;
    }

    public Map<String, UserType> getTipos() {
        return tipos;
    }

    public void setTipos(Map<String, UserType> tipos) {
        this.tipos = tipos;
    }

}