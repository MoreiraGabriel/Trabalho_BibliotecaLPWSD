/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.EmprestimoDAO;
import br.cesjf.bibliotecalpwsd.enums.MessageType;
import br.cesjf.bibliotecalpwsd.model.Emprestimo;
import br.cesjf.bibliotecalpwsd.util.Message;
import br.cesjf.bibliotecalpwsd.util.ProcessReport;
import com.github.adminfaces.template.exception.BusinessException;
import java.io.Serializable;
import java.util.Date;
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
public class EmprestimoListBean extends ProcessReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Emprestimo emprestimo;
    private List<Emprestimo> emprestimos;
    private List<Emprestimo> emprestimosSelecionados;
    private List<Emprestimo> emprestimosFiltrados;
    private Long id;

    //construtor
    public EmprestimoListBean() {
        emprestimos = EmprestimoDAO.getInstance().getList();
        emprestimo = Emprestimo.Builder
                               .newInstance()
                               .build();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        EmprestimoDAO.getInstance().persist(emprestimo);
        emprestimos = EmprestimoDAO.getInstance().getList();
    }

    public void exclude(ActionEvent actionEvent) {
        for (Emprestimo e: emprestimosSelecionados){
            EmprestimoDAO.getInstance().remove(e.getId());
        }
        emprestimos = EmprestimoDAO.getInstance().getList();
    }
    
    public void novo(ActionEvent actionEvent) {
        emprestimo = Emprestimo.Builder
                               .newInstance()
                               .build();
    }
    
    public void buscarPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Insira um ID");
        }
        emprestimosSelecionados.add(EmprestimoDAO.getInstance().find(id));
    }

    //getters and setters
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public List<Emprestimo> getEmprestimosSelecionados() {
        return emprestimosSelecionados;
    }

    public void setEmprestimosSelecionados(List<Emprestimo> emprestimosSelecionados) {
        this.emprestimosSelecionados = emprestimosSelecionados;
    }

    public List<Emprestimo> getEmprestimosFiltrados() {
        return emprestimosFiltrados;
    }

    public void setEmprestimosFiltrados(List<Emprestimo> emprestimosFiltrados) {
        this.emprestimosFiltrados = emprestimosFiltrados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void efetuaDevolucao() {
        emprestimo.setDataDevolucao(new Date());
        EmprestimoDAO.getInstance().persist(emprestimo);
        Message.screenMessage(MessageType.INFO, "Devolução efetuada com sucesso!");
    }
    
}