package org.example.palabres.model.bean.utilisateur;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;


/**
 * Classe représentant un Utilsateur
 */
public class Utilisateur {

    // ==================== Attributs ====================
    @NotNull
    @Size(min = 1)
    private String pseudo;

    private String password;


    // ==================== Constructeurs ====================
    /**
     * Constructeur.
     */
    public Utilisateur() {
    }


    /**
     * Constructeur.
     *
     * @param pPseudo -
     */
    public Utilisateur(String pPseudo,String pPassword) {
        pseudo = pPseudo;
        password = pPassword;
    }


    // ==================== Getters/Setters ====================
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pPseudo) {
        pseudo = pPseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        vStB.append(" {")
            .append("pseudo=").append(pseudo)
            .append("}");
        return vStB.toString();
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
            || (obj instanceof Utilisateur && StringUtils.equals(((Utilisateur) obj).getPseudo(), pseudo));
    }


    @Override
    public int hashCode() {
        return this.pseudo != null ? this.pseudo.hashCode() : super.hashCode();
    }
}
