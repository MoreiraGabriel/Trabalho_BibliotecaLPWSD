/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.ExemplarDAO;
import br.cesjf.bibliotecalpwsd.model.Exemplar;
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
public class ExemplarListBean extends ProcessReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Exemplar exemplar;
    private List exemplares;
    private List<Exemplar> exemplaresSelecionados;
    private List exemplaresFiltrados;
    private Long id;

    //construtor
    public ExemplarListBean() {
        exemplares = ExemplarDAO.getInstance().getList();
        exemplar = Exemplar.Builder
                           .newInstance()
                           .build();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        ExemplarDAO.getInstance().persist(exemplar);
        exemplares = ExemplarDAO.getInstance().getList();
    }

    public void exclude(ActionEvent actionEvent) {
        for (Exemplar e: exemplaresSelecionados){
            ExemplarDAO.getInstance().remove(e.getId());
        }
        exemplares = ExemplarDAO.getInstance().getList();
    }
    
    public void novo(ActionEvent actionEvent) {
        exemplar = Exemplar.Builder
                           .newInstance()
                           .build();
    }
    
    public void buscarPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Insira um ID");
        }
        exemplaresSelecionados.add(ExemplarDAO.getInstance().find(id));
    }

    //getters and setters
    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public List getExemplares() {
        return exemplares;
    }

    public void setExemplares(List exemplares) {
        this.exemplares = exemplares;
    }

    public List getExemplaresSelecionados() {
        return exemplaresSelecionados;
    }

    public void setExemplaresSelecionados(List exemplaresSelecionados) {
        this.exemplaresSelecionados = exemplaresSelecionados;
    }

    public List getExemplaresFiltrados() {
        return exemplaresFiltrados;
    }

    public void setExemplaresFiltrados(List exemplaresFiltrados) {
        this.exemplaresFiltrados = exemplaresFiltrados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}