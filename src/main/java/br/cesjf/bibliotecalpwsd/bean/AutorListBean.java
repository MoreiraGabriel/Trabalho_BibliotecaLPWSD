/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.AutorDAO;
import br.cesjf.bibliotecalpwsd.model.Autor;
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
public class AutorListBean extends ProcessReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Autor autor;
    private List<Autor> autores;
    private List<Autor> autoresSelecionados;
    private List<Autor> autoresFiltrados;
    private Long id;

    //construtor
    public AutorListBean() {
        autores = AutorDAO.getInstance().getList();
        autor = Autor.Builder
                     .newInstance()
                     .build();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        AutorDAO.getInstance().persist(autor);
        autores = AutorDAO.getInstance().getList();
    }

    public void exclude(ActionEvent actionEvent) {
        for (Autor a: autoresSelecionados){
            AutorDAO.getInstance().remove(a.getId());
        }
        autores = AutorDAO.getInstance().getList();
    }
    
    public void novo(ActionEvent actionEvent) {
        autor = Autor.Builder
                     .newInstance()
                     .build();
    }
    
    public void buscarPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Insira um ID");
        }
        autoresSelecionados.add(AutorDAO.getInstance().find(id));
    }

    //getters and setters
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Autor> getAutores() {
        return AutorDAO.getInstance().getList();
    }

    public void setAutores(List autores) {
        this.autores = autores;
    }

    public List<Autor> getAutoresSelecionados() {
        return autoresSelecionados;
    }

    public void setAutoresSelecionados(List autoresSelecionados) {
        this.autoresSelecionados = autoresSelecionados;
    }

    public List<Autor> getAutoresFiltrados() {
        return autoresFiltrados;
    }

    public void setAutoresFiltrados(List<Autor> autoresFiltrados) {
        this.autoresFiltrados = autoresFiltrados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}