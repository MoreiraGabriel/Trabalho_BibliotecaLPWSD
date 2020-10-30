/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.AssuntoDAO;
import br.cesjf.bibliotecalpwsd.model.Assunto;
import java.io.Serializable;
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
public class AssuntoFormBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Assunto assunto;
    private Long id;
    
    public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        if (id != null) {
            assunto = AssuntoDAO.getInstance().find(id);
        } else {
            assunto = Assunto.Builder
                             .newInstance()
                             .build();
        }
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        AssuntoDAO.getInstance().persist(assunto);
    }
    
    public void exclude(ActionEvent actionEvent) {
        AssuntoDAO.getInstance().remove(assunto.getId());
    }

    //getters and setters
    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void clear() {
        assunto = Assunto.Builder
                         .newInstance()
                         .build();
    }
    
    public boolean isNew() {
        return assunto == null || assunto.getId() == null || assunto.getId() == 0;
    }

}