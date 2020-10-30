/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.UsuarioDAO;
import br.cesjf.bibliotecalpwsd.model.Usuario;
import br.cesjf.bibliotecalpwsd.util.ProcessReport;
import com.github.adminfaces.template.exception.BusinessException;
import java.io.Serializable;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author dmeireles
 */
@Named
@ViewScoped
public class UsuarioListBean extends ProcessReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private List<Usuario> usuarios;
    private List<Usuario> usuariosSelecionados;
    private List<Usuario> usuariosFiltrados;
    private Long id;

    //construtor
    public UsuarioListBean() {
        usuarios = UsuarioDAO.getInstance().getList();
        usuario = Usuario.Builder
                         .newInstance()
                         .build();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        UsuarioDAO.getInstance().persist(usuario);
        usuarios = UsuarioDAO.getInstance().getList();
    }

    public void exclude(ActionEvent actionEvent) {
        for (Usuario u: usuariosSelecionados){
            UsuarioDAO.getInstance().remove(u.getId());
        }
        usuarios = UsuarioDAO.getInstance().getList();
    }
    
    public void novo(ActionEvent actionEvent) {
        usuario = Usuario.Builder
                         .newInstance()
                         .build();
    }
    
    public void buscarPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Insira um ID");
        }
        usuariosSelecionados.add(UsuarioDAO.getInstance().find(id));
    }

    //getters and setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List usuarios) {
        this.usuarios = usuarios;
    }

    public List getUsuariosSelecionados() {
        return usuariosSelecionados;
    }

    public void setUsuariosSelecionados(List usuariosSelecionados) {
        this.usuariosSelecionados = usuariosSelecionados;
    }

    public List getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public void setUsuariosFiltrados(List usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}