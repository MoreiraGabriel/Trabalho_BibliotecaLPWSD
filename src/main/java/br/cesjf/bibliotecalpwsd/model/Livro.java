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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dmeireles
 */
@Entity
@Table(name = "Livro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l")
    , @NamedQuery(name = "Livro.findById", query = "SELECT l FROM Livro l WHERE l.id = :id")
    , @NamedQuery(name = "Livro.findByTitulo", query = "SELECT l FROM Livro l WHERE l.titulo = :titulo")
    , @NamedQuery(name = "Livro.findByIsbn", query = "SELECT l FROM Livro l WHERE l.isbn = :isbn")
    , @NamedQuery(name = "Livro.findByEdicao", query = "SELECT l FROM Livro l WHERE l.edicao = :edicao")
    , @NamedQuery(name = "Livro.findByAno", query = "SELECT l FROM Livro l WHERE l.ano = :ano")})
public class Livro implements Serializable, Comparable<Livro> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;
    @Basic(optional = false)
    @Column(name = "edicao")
    private Integer edicao;
    @Basic(optional = false)
    @Column(name = "ano")
    private Integer ano;
    @Column(name = "capa")
    private String capa;
    @Column(name = "arquivo")
    private String arquivo;
    @ManyToMany
    @JoinTable(name = "LivroAssunto",
        joinColumns = @JoinColumn(name = "idLivro"),
        inverseJoinColumns = @JoinColumn(name = "idAssunto")
    )
    private List<Assunto> assuntoList;
    @ManyToMany
    @JoinTable(name = "AutorLivro",
        joinColumns = @JoinColumn(name = "idLivro"),
        inverseJoinColumns = @JoinColumn(name = "idAutor")
    )
    private List<Autor> autorList;
    @OneToMany(mappedBy = "idLivro")
    private List<Exemplar> exemplarList;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idEditora", referencedColumnName = "id")
    private Editora idEditora;
    
    public Livro() {
    }
    
    public Livro(Builder builder) {
        this.id = builder.id;
        this.titulo = builder.titulo;
        this.isbn = builder.isbn;
        this.edicao = builder.edicao;
        this.ano = builder.ano;
        this.capa = builder.capa;
        this.arquivo = builder.arquivo;
        this.assuntoList = builder.assuntoList;
        this.autorList = builder.autorList;
        this.exemplarList = builder.exemplarList;
        this.idEditora = builder.idEditora;
    }
    
    public static class Builder {
        
        private Long id;
        private String titulo;
        private String isbn;
        private Integer edicao;
        private Integer ano;
        private String capa;
        private String arquivo;
        private List<Assunto> assuntoList;
        private List<Autor> autorList;
        private List<Exemplar> exemplarList;
        private Editora idEditora;
        
        public static Builder newInstance() { 
            return new Builder(); 
        } 
        
        private Builder() {
            
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder setEdicao(Integer edicao) {
            this.edicao = edicao;
            return this;
        }

        public Builder setAno(Integer ano) {
            this.ano = ano;
            return this;
        }

        public Builder setCapa(String capa) {
            this.capa = capa;
            return this;
        }

        public Builder setArquivo(String arquivo) {
            this.arquivo = arquivo;
            return this;
        }

        public Builder setAssuntoList(List<Assunto> assuntoList) {
            this.assuntoList = assuntoList;
            return this;
        }

        public Builder setAutorList(List<Autor> autorList) {
            this.autorList = autorList;
            return this;
        }

        public Builder setExemplarList(List<Exemplar> exemplarList) {
            this.exemplarList = exemplarList;
            return this;
        }

        public Builder setIdEditora(Editora idEditora) {
            this.idEditora = idEditora;
            return this;
        }
        
        public Livro build() {
            return new Livro(this);
        }
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    
    
    
    public String getAssuntos() {
        String texto = "";
        for(Assunto a: assuntoList){
            texto = texto + a.getAssunto() + "; ";
        }
        return texto;
    }
    
    public String getAutores() {
        String texto = "";
        for(Autor a: autorList){
            texto = texto + a.getNome() + "; ";
        }
        return texto;
    }

    @XmlTransient
    public List<Assunto> getAssuntoList() {
        return assuntoList;
    }

    public void setAssuntoList(List<Assunto> assuntoList) {
        this.assuntoList = assuntoList;
    }

    @XmlTransient
    public List<Autor> getAutorList() {
        return autorList;
    }

    public void setAutorList(List<Autor> autorList) {
        this.autorList = autorList;
    }

    @XmlTransient
    public List<Exemplar> getExemplarList() {
        return exemplarList;
    }

    public void setExemplarList(List<Exemplar> exemplarList) {
        this.exemplarList = exemplarList;
    }

    public Editora getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(Editora idEditora) {
        this.idEditora = idEditora;
    }
    
    public int getQtdeExemplarCircular() {
        int i = 0;
        for(Exemplar e: exemplarList) {
            if(e.getCircular()) {
                i++;
            }
        }
        return i;
    }
    
    public int getQtdeExemplarNaoCircular() {
        int i = 0;
        for(Exemplar e: exemplarList) {
            if(!e.getCircular()) {
                i++;
            }
        }
        return i;
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
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titulo;
    }
    
    @Override
    public int compareTo(Livro livro) {
        return this.titulo.toLowerCase().compareTo(livro.getTitulo().toLowerCase());
    }
    
}
