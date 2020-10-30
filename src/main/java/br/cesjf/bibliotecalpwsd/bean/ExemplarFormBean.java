/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.ExemplarDAO;
import br.cesjf.bibliotecalpwsd.dao.LivroDAO;
import br.cesjf.bibliotecalpwsd.model.Exemplar;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
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
public class ExemplarFormBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Exemplar exemplar;
    private Long id;
    private List livros;

    //construtor
    public ExemplarFormBean() {
        livros = LivroDAO.getInstance().getList();
    }
    
    public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        if (id != null) {
            exemplar = ExemplarDAO.getInstance().find(id);
        } else {
            exemplar = new Exemplar();
        }
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        ExemplarDAO.getInstance().persist(exemplar);
    }
    
    public void exclude(ActionEvent actionEvent) {
        ExemplarDAO.getInstance().remove(exemplar.getId());
    }

    //getters and setters
    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List getLivros() {
        Collections.sort(livros);
        return livros;
    }

    public void setLivros(List livros) {
        this.livros = livros;
    }
    
    public void clear() {
        exemplar = new Exemplar();
    }
    
    public boolean isNew() {
        return exemplar == null || exemplar.getId() == null || exemplar.getId() == 0;
    }

}