package org.example.palabres.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.example.palabres.business.contract.manager.UtilisateurManager;
import org.example.palabres.model.bean.utilisateur.Utilisateur;
import org.example.palabres.model.exception.FunctionalException;
import org.example.palabres.model.exception.NotFoundException;
import org.example.palabres.webapp.WebappHelper;

import java.util.Map;

public class LoginAction extends ActionSupport  implements SessionAware {

    // ==================== Attributs ====================
    // ----- Paramètres en entrée
    Utilisateur utilisateur;
    private String pseudo;


    // ----- Eléments Struts
    private Map<String, Object> session;


    // ==================== Getters/Setters ====================
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pPseudo) {
        pseudo = pPseudo;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    // ==================== Méthodes ====================
    /**
     * Action permettant la connexion d'un utilisateur
     * @return input / success
     */
    public String doLogin() throws FunctionalException, NotFoundException {
        String vResult = ActionSupport.INPUT;
        if (this.utilisateur != null) {
            // WebappHelper.getManagerFactory().getUtilisateurManager().addUtilisateur(this.utilisateur);
            utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(this.utilisateur.getPseudo());
            this.session.put("user", utilisateur);
            vResult = ActionSupport.SUCCESS;
        }
        return vResult;
    }


    /**
     * Action de déconnexion d'un utilisateur
     * @return success
     */
    public String doLogout() throws NotFoundException {

        WebappHelper.getManagerFactory().getUtilisateurManager().deleteUtilisateur(utilisateur);
        this.session.remove("user");

        return ActionSupport.SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public Map<String, Object> getSession() {
        return session;
    }
}

