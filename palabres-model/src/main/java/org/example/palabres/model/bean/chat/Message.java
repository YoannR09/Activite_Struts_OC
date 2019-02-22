package org.example.palabres.model.bean.chat;

import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.example.palabres.model.bean.utilisateur.Utilisateur;


/**
 * Classe représentant un message de discussion
 */
public class Message {

    /** Logger pour la classe */
    private static final Log LOGGER = LogFactory.getLog(Message.class);


    // ==================== Attributs ====================
    @NotNull
    private Utilisateur author;
    @NotNull
    private String message;


    // ==================== Constructeurs ====================
    /**
     * Constructeur.
     */
    public Message() {
    }


    /**
     * Constructeur.
     *
     * @param pAuthor -
     * @param pMessage -
     */
    public Message(Utilisateur pAuthor, String pMessage) {
        author = pAuthor;
        message = pMessage;
    }


    // ==================== Getters/Setters ====================
    public Utilisateur getAuthor() {
        return author;
    }
    public void setAuthor(Utilisateur pAuthor) {
        author = pAuthor;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String pMessage) {
        message = pMessage;
    }


    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append(" {")
            .append("author=").append(author)
            .append(vSEP).append("message=\"").append(message).append('"')
            .append("}");
        return vStB.toString();
    }
}
