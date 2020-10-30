/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.LivroDAO;
import br.cesjf.bibliotecalpwsd.dto.LivroDto;
import br.cesjf.bibliotecalpwsd.enums.MessageType;
import br.cesjf.bibliotecalpwsd.model.Livro;
import br.cesjf.bibliotecalpwsd.util.Message;
import com.github.adminfaces.template.exception.BusinessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author gabriel.moreira
 */
@Named
@ViewScoped
public class ResumoLivroBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<LivroDto> livrosDto;
    private Long id;
    private String titulo;
    
    //construtor
    public ResumoLivroBean() {
        livrosDto = LivroDto.converteDto(LivroDAO.getInstance().getList());
    }
    
    public List<LivroDto> getLivros() {        
        return LivroDto.converteDto(LivroDAO.getInstance().getList());
    }
    
    public void buscarPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Insira um ID");
        }

        Livro livro = LivroDAO.getInstance().find(id);
        
        if(livro.getId() == null) {
            Message.logAndScreenMessage(MessageType.WARNING, "Não foram encontrados livros");
            livrosDto = LivroDto.converteDto(LivroDAO.getInstance().getList());
        }else{
            livrosDto.add(new LivroDto(livro));
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
        livrosDto = LivroDto.converteDto(LivroDAO.getInstance().find("Livro.findByTitulo", parameters));
        if(livrosDto.isEmpty()) {
            Message.logAndScreenMessage(MessageType.WARNING, "Não foram encontrados livros");
            livrosDto = LivroDto.converteDto(LivroDAO.getInstance().getList());
        }
        this.id = null;
        this.titulo = null;
    }
    
    public void limpar() {
        livrosDto = LivroDto.converteDto(LivroDAO.getInstance().getList());
        this.id = null;
        this.titulo = null;
    }

    public void setLivrosDto(List<LivroDto> livrosDto) {
        this.livrosDto = livrosDto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<LivroDto> getLivrosDto() {
        return livrosDto;
    }

}
