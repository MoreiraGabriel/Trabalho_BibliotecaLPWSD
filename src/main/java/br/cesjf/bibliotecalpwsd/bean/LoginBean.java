/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.bean;

import br.cesjf.bibliotecalpwsd.dao.UsuarioDAO;
import br.cesjf.bibliotecalpwsd.enums.UserType;
import br.cesjf.bibliotecalpwsd.model.Usuario;
import com.github.adminfaces.template.session.AdminSession;
import org.omnifaces.util.Faces;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import com.github.adminfaces.template.config.AdminConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
/**
 *
 * @author dmeireles
 */
@Named
@SessionScoped
@Specializes
public class LoginBean extends AdminSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String usuario;
    private String senha;
    private UserType tipo;
    
    @Inject
    private AdminConfig adminConfig;
    
    @Override
    public boolean isLoggedIn() {

        return nome != null;
}

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void login() throws IOException {
        List<List> parameters = new ArrayList<>();
        parameters.add(Arrays.asList("usuario", usuario));
        parameters.add(Arrays.asList("senha", senha));
        List usuarios = UsuarioDAO.getInstance().find("Usuario.login", parameters);
        Usuario u;
        if (usuarios.isEmpty()) {
            u = null;
        } else {
            u = (Usuario) usuarios.get(0);
        }
        if (u != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("usuario", u);
            session.setAttribute("nome", u.getUsuario());
            nome = u.getNome();
            tipo = u.getTipo();
            setIsLoggedIn(true);
            Faces.getExternalContext().getFlash().setKeepMessages(true);
            Faces.redirect(adminConfig.getIndexPage());
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário ou senha inválido!", ""));
            Faces.getExternalContext().getFlash().setKeepMessages(true);
            Faces.redirect(adminConfig.getLoginPage());
        }
    }
    
    public void logout() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        nome = null;
        tipo = null;
        setIsLoggedIn(false);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout realizado com sucesso!", ""));
        Faces.getExternalContext().getFlash().setKeepMessages(true);
        Faces.redirect(adminConfig.getLoginPage());
    }
    
    public boolean menu(String menu) {
        switch(menu){
            case "Usuários":
                return tipo.equals(UserType.FUNCIONARIO)
                    || tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Exemplares":
                return tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Assuntos":
                return tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Livros":
                return tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Autores":
                return tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Editoras":
                return tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Cadastros":
                return tipo.equals(UserType.FUNCIONARIO)
                    || tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Relatórios":
                return tipo.equals(UserType.ADMINISTRADOR);
            case "Empréstimos":
                return tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Dashboard":
                return tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Reservas":
                return tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            case "Resumo":
                return tipo.equals(UserType.BIBLIOTECARIO)
                    || tipo.equals(UserType.ADMINISTRADOR);
            default:
                return false;
        }
    }
    
}