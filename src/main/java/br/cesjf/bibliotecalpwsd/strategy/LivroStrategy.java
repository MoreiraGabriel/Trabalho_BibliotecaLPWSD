/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.strategy;

import br.cesjf.bibliotecalpwsd.dto.LivroDto;
import br.cesjf.bibliotecalpwsd.model.Livro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel.moreira
 */
public abstract class LivroStrategy {
    public List<LivroDto> converteLivro(List<Livro> lista){
        return new ArrayList();
    };
}
