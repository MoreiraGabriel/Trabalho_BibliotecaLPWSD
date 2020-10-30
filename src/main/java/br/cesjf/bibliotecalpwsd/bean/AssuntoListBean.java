/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.AssuntoDAO;
import br.cesjf.bibliotecalpwsd.model.Assunto;
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
public class AssuntoListBean extends ProcessReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Assunto assunto;
    private List<Assunto> assuntos;
    private List<Assunto> assuntosSelecionados;
    private List<Assunto> assuntosFiltrados;
    private Long id;

    //construtor
    public AssuntoListBean() {
        assuntos = AssuntoDAO.getInstance().getList();
        assunto = Assunto.Builder
                         .newInstance()
                         .build();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        AssuntoDAO.getInstance().persist(assunto);
        assuntos = AssuntoDAO.getInstance().getList();
    }

    public void exclude(ActionEvent actionEvent) {
        for (Assunto a: assuntosSelecionados){
            AssuntoDAO.getInstance().remove(a.getId());
        }
        assuntos = AssuntoDAO.getInstance().getList();
    }
    
    public void novo(ActionEvent actionEvent) {
        assunto = Assunto.Builder
                         .newInstance()
                         .build();
    }
    
    public void buscarPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Insira um ID");
        }
        assuntosSelecionados.add(AssuntoDAO.getInstance().find(id));
    }

    //getters and setters
    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }

    public List getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List assuntos) {
        this.assuntos = assuntos;
    }

    public List getAssuntosSelecionados() {
        return assuntosSelecionados;
    }

    public void setAssuntosSelecionados(List assuntosSelecionados) {
        this.assuntosSelecionados = assuntosSelecionados;
    }

    public List getAssuntosFiltrados() {
        return assuntosFiltrados;
    }

    public void setAssuntosFiltrados(List assuntosFiltrados) {
        this.assuntosFiltrados = assuntosFiltrados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}