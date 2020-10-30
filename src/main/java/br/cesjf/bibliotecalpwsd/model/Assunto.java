/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dmeireles
 */
@Entity
@Table(name = "Assunto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assunto.findAll", query = "SELECT a FROM Assunto a")
    , @NamedQuery(name = "Assunto.findById", query = "SELECT a FROM Assunto a WHERE a.id = :id")
    , @NamedQuery(name = "Assunto.findByAssunto", query = "SELECT a FROM Assunto a WHERE a.assunto = :assunto")})
public class Assunto implements Serializable, Comparable<Assunto> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "assunto")
    private String assunto;
    @ManyToMany(mappedBy="assuntoList")
    private List<Livro> livroList;

    public Assunto() {
    }
    
    public Assunto(Builder builder) {
        this.id = builder.id;
        this.assunto = builder.assunto;
        this.livroList = builder.livroList;
    }
    
    public static class Builder {
        
        private Long id;
        private String assunto;
        private List<Livro> livroList;
        
        public static Builder newInstance() { 
            return new Builder(); 
        } 
        
        private Builder() {
            
        }
        
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder setAssunto(String assunto) {
            this.assunto = assunto;
            return this;
        }
        
        public Builder setLivroList(List<Livro> livroList) {
            this.livroList = livroList;
            return this;
        }
        
        public Assunto build() {
            return new Assunto(this);
        }
        
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
    @XmlTransient
    public List<Livro> getLivroList() {
        return livroList;
    }

    public void setLivroList(List<Livro> livroList) {
        this.livroList = livroList;
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
        if (!(object instanceof Assunto)) {
            return false;
        }
        Assunto other = (Assunto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return assunto;
    }
    
    @Override
    public int compareTo(Assunto assunto) {
        return this.assunto.toLowerCase().compareTo(assunto.getAssunto().toLowerCase());
    }
    
}