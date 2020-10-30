/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.bibliotecalpwsd.util;

import br.cesjf.bibliotecalpwsd.enums.MessageType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author dmeireles
 */
public abstract class Message {
    
    public static void logAndScreenMessage(MessageType messageType, String message) {
        logMessage(messageType, message);
        screenMessage(messageType, message);
    }
    
    public static void screenMessage(MessageType messageType, String message) {
        if (messageType.equals(MessageType.WARNING)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", message));
        } else if(messageType.equals(MessageType.INFO)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", message));
        }
    }
    
    public static void logMessage(MessageType messageType, String message) {
        if (messageType.equals(MessageType.WARNING)) {
            Logger.getLogger (PersistenceUtil.class.getName()).log(Level.WARNING, message);
        } else if(messageType.equals(MessageType.INFO)) {
            Logger.getLogger (PersistenceUtil.class.getName()).log(Level.INFO, message);
        }
    }
    
}
