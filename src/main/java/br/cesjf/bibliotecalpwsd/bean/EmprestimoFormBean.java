/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.EmprestimoDAO;
import br.cesjf.bibliotecalpwsd.dao.ExemplarDAO;
import br.cesjf.bibliotecalpwsd.dao.LivroDAO;
import br.cesjf.bibliotecalpwsd.dao.ReservaDAO;
import br.cesjf.bibliotecalpwsd.dao.UsuarioDAO;
import br.cesjf.bibliotecalpwsd.enums.MessageType;
import br.cesjf.bibliotecalpwsd.enums.UserType;
import br.cesjf.bibliotecalpwsd.model.Emprestimo;
import br.cesjf.bibliotecalpwsd.model.Exemplar;
import br.cesjf.bibliotecalpwsd.model.Livro;
import br.cesjf.bibliotecalpwsd.model.Reserva;
import br.cesjf.bibliotecalpwsd.model.Usuario;
import br.cesjf.bibliotecalpwsd.util.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author dmeireles
 */
@Named
@ViewScoped
public class EmprestimoFormBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Emprestimo emprestimo;
    private List<Exemplar> exemplaresPermitidos;
    private List<Usuario> usuariosPermitidos;
    private List<Usuario> usuarios;
    private Livro livro;
    private List<Livro> livros;
    private Long id;
    
    public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        if (id != null) {
            emprestimo = EmprestimoDAO.getInstance().find(id);
        } else {
            emprestimo = Emprestimo.Builder
                                   .newInstance()
                                   .build();
        }
        livros = LivroDAO.getInstance().getList();
        usuarios = UsuarioDAO.getInstance().getList();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        emprestimo.calculaDevolucaoPrevista();
        EmprestimoDAO.getInstance().persist(emprestimo);
    }
    
    public void exclude(ActionEvent actionEvent) {
        EmprestimoDAO.getInstance().remove(emprestimo.getId());
    }

    //getters and setters
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Exemplar> getExemplaresPermitidos() {
        return exemplaresPermitidos;
    }

    public void setExemplaresPermitidos(List<Exemplar> exemplaresPermitidos) {
        this.exemplaresPermitidos = exemplaresPermitidos;
    }

    public List<Usuario> getUsuariosPermitidos() {
        usuariosPermitidos();
        return usuariosPermitidos;
    }

    public void setUsuariosPermitidos(List<Usuario> usuariosPermitidos) {
        this.usuariosPermitidos = usuariosPermitidos;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public void clear() {
        emprestimo = Emprestimo.Builder
                               .newInstance()
                               .build();
    }
    
    public boolean isNew() {
        return emprestimo == null || emprestimo.getId() == null || emprestimo.getId() == 0;
    }
    
    private void usuariosPermitidos() {
        usuariosPermitidos = new ArrayList<>();
        for(Usuario u: usuarios) {
            List<Emprestimo> emp = new ArrayList<>();
            for(Emprestimo e: u.getEmprestimoList()) {
                if(e.getDataDevolucao() == null) {
                    emp.add(e);
                }
            }
            if(u.getTipo().equals(UserType.ALUNO) && emp.size() < 3) {
                usuariosPermitidos.add(u);
            } else if(!u.getTipo().equals(UserType.ALUNO) && emp.size() < 5) {
                usuariosPermitidos.add(u);
            }
        }
    }
    
    public void verificaUsuario(SelectEvent event) {
        usuariosPermitidos();
        if(!usuariosPermitidos.contains(emprestimo.getIdUsuario())) {
            Message.screenMessage(MessageType.WARNING, "Não permitido. Usuário com alguma pendência.");
        }
    }
    
    public void calcularExemplaresPermitidos(SelectEvent event) {
        List<Exemplar> exemplares = ExemplarDAO.getInstance().getList();
        List<Reserva> reservas = ReservaDAO.getInstance().getList();
        exemplaresPermitidos = new ArrayList<>();
        Date dataReserva = emprestimo.getDataEmprestimo();
        if(dataReserva != null){
            if(livro == null) {
                exemplaresPermitidos.addAll(exemplares);
            } else {
                exemplares.stream().filter(e -> 
                    (e.getIdLivro().getId().equals(livro.getId())))
                        .forEachOrdered((Exemplar e) -> exemplaresPermitidos.add(e));
            }
            List<Exemplar> lista = new ArrayList<>();
            lista.addAll(exemplaresPermitidos);
            
            for(Exemplar e: lista) {
                for(Emprestimo emp: EmprestimoDAO.getInstance().getList()) {
                    if(emp.getIdExemplar().getId().equals(e.getId())) {
                        if(emp.getDataDevolucao() == null && emp.getDataEmprestimo().compareTo(dataReserva) <= 0 && emp.getDataDevolucaoPrevista().compareTo(dataReserva) >= 0) {
                            exemplaresPermitidos.remove(e);
                            continue;
                        }
                        if(emp.getDataDevolucao() != null && emp.getDataEmprestimo().compareTo(dataReserva) <= 0 && emp.getDataDevolucao().compareTo(dataReserva) >= 0) {
                            exemplaresPermitidos.remove(e);
                            continue;
                        }
                    }
                }
                for(Reserva r: ReservaDAO.getInstance().getList()) {
                    if(r.getIdExemplar().getId().equals(e.getId())){
                        if(!r.getCancelada() && r.getDataReserva().compareTo(dataReserva) <= 0 && r.getDataDevolucaoPrevista().compareTo(dataReserva) >= 0) {
                            exemplaresPermitidos.remove(e);
                        }
                    }
                }
            }
        }
        if(emprestimo.getIdExemplar() != null) {
            exemplaresPermitidos.add(emprestimo.getIdExemplar());
        }
    }

}