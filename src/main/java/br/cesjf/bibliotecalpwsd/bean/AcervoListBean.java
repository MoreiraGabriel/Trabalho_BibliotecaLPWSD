/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.LivroDAO;
import br.cesjf.bibliotecalpwsd.enums.MessageType;
import br.cesjf.bibliotecalpwsd.model.Livro;
import br.cesjf.bibliotecalpwsd.util.Message;
import com.github.adminfaces.template.exception.BusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author dmeireles
 */
@Named
@ViewScoped
public class AcervoListBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<Livro> livros;
    private Long id;
    private String titulo;

    //construtor
    public AcervoListBean() {
        livros = LivroDAO.getInstance().getList();
    }

    //getters and setters
    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void buscarPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Insira um ID");
        }
        livros = new ArrayList<>();
        Livro l = LivroDAO.getInstance().find(id);
        if(l != null){
            livros.add(l);
        }
        if(livros.isEmpty()) {
            Message.logAndScreenMessage(MessageType.WARNING, "Não foram encontrados livros");
            livros = LivroDAO.getInstance().getList();
        }
        this.id = null;
        this.titulo = null;
    }
    
    public void buscarPorTitulo(String titulo) {
        if (titulo.isEmpty()) {
            throw new BusinessException("Insira um Título");
        }
        List<List> parameters = new ArrayList<>();
        parameters.add(Arrays.asList("titulo", titulo));
        livros = LivroDAO.getInstance().find("Livro.findByTitulo", parameters);
        if(livros.isEmpty()) {
            Message.logAndScreenMessage(MessageType.WARNING, "Não foram encontrados livros");
            livros = LivroDAO.getInstance().getList();
        }
        this.id = null;
        this.titulo = null;
    }
    
    public void limpar() {
        livros = LivroDAO.getInstance().getList();
        this.id = null;
        this.titulo = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
}