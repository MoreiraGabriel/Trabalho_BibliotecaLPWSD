/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.EditoraDAO;
import br.cesjf.bibliotecalpwsd.model.Editora;
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
public class EditoraListBean extends ProcessReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Editora editora;
    private List<Editora> editoras;
    private List<Editora> editorasSelecionados;
    private List<Editora> editorasFiltrados;
    private Long id;

    //construtor
    public EditoraListBean() {
        editoras = EditoraDAO.getInstance().getList();
        editora = Editora.Builder
                         .newInstance()
                         .build();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        EditoraDAO.getInstance().persist(editora);
        editoras = EditoraDAO.getInstance().getList();
    }

    public void exclude(ActionEvent actionEvent) {
        for (Editora e: editorasSelecionados){
            EditoraDAO.getInstance().remove(e.getId());
        }
        editoras = EditoraDAO.getInstance().getList();
    }
    
    public void novo(ActionEvent actionEvent) {
        editora = Editora.Builder
                         .newInstance()
                         .build();
    }
    
    public void buscarPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Insira um ID");
        }
        editorasSelecionados.add(EditoraDAO.getInstance().find(id));
    }

    //getters and setters
    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List getEditoras() {
        return editoras = EditoraDAO.getInstance().getList();
    }

    public void setEditoras(List editoras) {
        this.editoras = editoras;
    }

    public List getEditorasSelecionados() {
        return editorasSelecionados;
    }

    public void setEditorasSelecionados(List editorasSelecionados) {
        this.editorasSelecionados = editorasSelecionados;
    }

    public List getEditorasFiltrados() {
        return editorasFiltrados;
    }

    public void setEditorasFiltrados(List editorasFiltrados) {
        this.editorasFiltrados = editorasFiltrados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}