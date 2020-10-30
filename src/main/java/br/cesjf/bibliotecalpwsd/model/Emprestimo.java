/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dmeireles
 */
@Entity
@Table(name = "Emprestimo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emprestimo.findAll", query = "SELECT e FROM Emprestimo e")
    , @NamedQuery(name = "Emprestimo.findById", query = "SELECT e FROM Emprestimo e WHERE e.id = :id")
    , @NamedQuery(name = "Emprestimo.findByDataEmprestimo", query = "SELECT e FROM Emprestimo e WHERE e.dataEmprestimo = :dataEmprestimo")
    , @NamedQuery(name = "Emprestimo.findByDataDevolucao", query = "SELECT e FROM Emprestimo e WHERE e.dataDevolucao = :dataDevolucao")})
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "dataEmprestimo")
    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;
    @Basic(optional = false)
    @Column(name = "dataDevolucaoPrevista")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucaoPrevista;
    @Column(name = "dataDevolucao")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idExemplar", referencedColumnName = "id")
    private Exemplar idExemplar;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private Usuario idUsuario;
    
    public Emprestimo() {
    }

    public Emprestimo(Builder builder) {
        this.id = builder.id;
        this.dataEmprestimo = builder.dataEmprestimo;
        this.dataDevolucaoPrevista = builder.dataDevolucaoPrevista;
        this.dataDevolucao = builder.dataDevolucao;
        this.idExemplar = builder.idExemplar;
        this.idUsuario = builder.idUsuario;
    }
    
    public static class Builder {
        
        private Long id;
        private Date dataEmprestimo;
        private Date dataDevolucaoPrevista;
        private Date dataDevolucao;
        private Exemplar idExemplar;
        private Usuario idUsuario;
        
        public static Builder newInstance() { 
            return new Builder(); 
        } 
        
        private Builder() {
            
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDataEmprestimo(Date dataEmprestimo) {
            this.dataEmprestimo = dataEmprestimo;
            return this;
        }

        public Builder setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
            this.dataDevolucaoPrevista = dataDevolucaoPrevista;
            return this;
        }

        public Builder setDataDevolucao(Date dataDevolucao) {
            this.dataDevolucao = dataDevolucao;
            return this;
        }

        public Builder setIdExemplar(Exemplar idExemplar) {
            this.idExemplar = idExemplar;
            return this;
        }

        public Builder setIdUsuario(Usuario idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }
        
        public Emprestimo build() {
            return new Emprestimo(this);
        }
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Exemplar getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(Exemplar idExemplar) {
        this.idExemplar = idExemplar;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    
    public void calculaDevolucaoPrevista() {
        Calendar c = Calendar.getInstance();
        if(dataEmprestimo != null){
            
            c.setTime(dataEmprestimo);
            
            if(idExemplar.getCircular() && idUsuario.getTipo().equals('C')){
                c.add(Calendar.DAY_OF_MONTH, 10);
            } else if(idExemplar.getCircular() && !idUsuario.getTipo().equals('C')){
                c.add(Calendar.DAY_OF_MONTH, 15);
            } else if(!idExemplar.getCircular()) {
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
            
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                c.add(Calendar.DAY_OF_MONTH, 2);
            } else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
        } else {
            c.setTime(new Date());
        }
        dataDevolucaoPrevista = c.getTime();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprestimo)) {
            return false;
        }
        Emprestimo other = (Emprestimo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Long.toString(id);
    }
    
}
