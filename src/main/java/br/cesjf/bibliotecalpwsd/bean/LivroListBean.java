/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.LivroDAO;
import br.cesjf.bibliotecalpwsd.model.Livro;
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
public class LivroListBean extends ProcessReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Livro livro;
    private List<Livro> livros;
    private List<Livro> livrosSelecionados;
    private List livrosFiltrados;
    private Long id;

    //construtor
    public LivroListBean() {
        livros = LivroDAO.getInstance().getList();
        livro = Livro.Builder
                     .newInstance()
                     .build();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        LivroDAO.getInstance().persist(livro);
        livros = LivroDAO.getInstance().getList();
    }

    public void exclude(ActionEvent actionEvent) {
        for (Livro l: livrosSelecionados){
            LivroDAO.getInstance().remove(l.getId());
        }
        livros = LivroDAO.getInstance().getList();
    }
    
    public void novo(ActionEvent actionEvent) {
        livro = Livro.Builder
                     .newInstance()
                     .build();
    }
    
    public void buscarPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Insira um ID");
        }
        livrosSelecionados.add(LivroDAO.getInstance().find(id));
    }

    //getters and setters
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Livro> getLivros() {
//        return livros;
        return LivroDAO.getInstance().getList();
    }

    public void setLivros(List livros) {
        this.livros = livros;
    }

    public List getLivrosSelecionados() {
        return livrosSelecionados;
    }

    public void setLivrosSelecionados(List livrosSelecionados) {
        this.livrosSelecionados = livrosSelecionados;
    }

    public List getLivrosFiltrados() {
        return livrosFiltrados;
    }

    public void setLivrosFiltrados(List livrosFiltrados) {
        this.livrosFiltrados = livrosFiltrados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}