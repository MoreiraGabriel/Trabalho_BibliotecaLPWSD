/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.dto;

import br.cesjf.bibliotecalpwsd.model.Livro;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gabriel.moreira
 */
public class LivroDto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String titulo;
    private Integer ano;
    
    public LivroDto(Livro livro){
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.ano = livro.getAno();
    }

    public static List<LivroDto> converteDto(List<Livro> lista) {        
        return lista.stream().map(LivroDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

}