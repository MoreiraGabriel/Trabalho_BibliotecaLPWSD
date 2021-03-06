/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.model;

import br.cesjf.bibliotecalpwsd.enums.UserType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dmeireles
 */
@Entity
@Table(name = "Reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findById", query = "SELECT r FROM Reserva r WHERE r.id = :id")
    , @NamedQuery(name = "Reserva.findByDataReserva", query = "SELECT r FROM Reserva r WHERE r.dataReserva = :dataReserva")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "dataReserva")
    @Temporal(TemporalType.DATE)
    private Date dataReserva;
    @Basic(optional = false)
    @Column(name = "dataDevolucaoPrevista")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucaoPrevista;
    @Column(name = "cancelada")
    private Boolean cancelada;
    @Basic(optional = true)
    @Column(name = "obsCancelamento")
    private String obsCancelamento;
    @OneToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinColumn(name = "idEmprestimo", referencedColumnName = "id")
    private Emprestimo idEmprestimo;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idExemplar", referencedColumnName = "id")
    private Exemplar idExemplar;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private Usuario idUsuario;
    
    public Reserva() {
        this.cancelada = false;
    }
    
    public Reserva(Builder builder) {
        this.id = builder.id;
        this.dataReserva = builder.dataReserva;
        this.dataDevolucaoPrevista = builder.dataDevolucaoPrevista;
        this.cancelada = builder.cancelada;
        this.obsCancelamento = builder.obsCancelamento;
        this.idEmprestimo = builder.idEmprestimo;
        this.idExemplar = builder.idExemplar;
        this.idUsuario = builder.idUsuario;
    }
    
    public static class Builder {
        
        private Long id;
        private Date dataReserva;
        private Date dataDevolucaoPrevista;
        private Boolean cancelada;
        private String obsCancelamento;
        private Emprestimo idEmprestimo;
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

        public Builder setDataReserva(Date dataReserva) {
            this.dataReserva = dataReserva;
            return this;
        }

        public Builder setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
            this.dataDevolucaoPrevista = dataDevolucaoPrevista;
            return this;
        }

        public Builder setCancelada(Boolean cancelada) {
            this.cancelada = cancelada;
            return this;
        }

        public Builder setObsCancelamento(String obsCancelamento) {
            this.obsCancelamento = obsCancelamento;
            return this;
        }

        public Builder setIdEmprestimo(Emprestimo idEmprestimo) {
            this.idEmprestimo = idEmprestimo;
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
        
        public Reserva build() {
            return new Reserva(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Emprestimo getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Emprestimo idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
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

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public String getObsCancelamento() {
        return obsCancelamento;
    }

    public void setObsCancelamento(String obsCancelamento) {
        this.obsCancelamento = obsCancelamento;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    
    public void calculaDevolucaoPrevista() {
        Calendar c = Calendar.getInstance();
        if(dataReserva != null){
            
            c.setTime(dataReserva);
            
            if(idExemplar.getCircular() && idUsuario.getTipo().equals(UserType.ALUNO)){
                c.add(Calendar.DAY_OF_MONTH, 10);
            } else if(idExemplar.getCircular() && !idUsuario.getTipo().equals(UserType.ALUNO)){
                c.add(Calendar.DAY_OF_MONTH, 15);
            } else if(!idExemplar.getCircular()) {
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
            
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                c.add(Calendar.DAY_OF_MONTH, 2);
            } else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
        }else {
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
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
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
