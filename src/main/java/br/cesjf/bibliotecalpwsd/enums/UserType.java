/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.enums;

/**
 *
 * @author dmeireles
 */
public enum UserType {
    
    ALUNO("Aluno"),
    PROFESSOR("Professor"),
    FUNCIONARIO("Funcionário"),
    BIBLIOTECARIO("Bibliotecário"),
    ADMINISTRADOR("Administrador");
    
    
    private String userType;
    
    private UserType(String userType) {
        this.userType = userType;
    }
    
    public String getUserType() {
        return userType;
    }
    
}
