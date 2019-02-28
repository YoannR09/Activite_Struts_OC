package org.example.palabres.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.example.palabres.business.contract.ManagerFactory;
import org.example.palabres.model.bean.chat.Channel;
import org.example.palabres.model.bean.utilisateur.Utilisateur;
import org.example.palabres.model.exception.FunctionalException;
import org.example.palabres.model.exception.NotFoundException;
import org.example.palabres.model.exception.TechnicalException;

import javax.inject.Inject;
import java.util.Map;

/**
 * Class qui gère la connexion/déconnexion/création d'un utilisateur.
 */
public class LoginAction extends ActionSupport  implements SessionAware {

    // ==================== Attributs ====================
    // ----- Paramètres en entrée
    private        Utilisateur    utilisateur;
    private        String         pseudo;
    private        String         password;

    // ----- Eléments Struts
    private Map<String, Object> session;

    @Inject
    private ManagerFactory managerFactory;


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

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    // ==================== Méthodes ====================

    /**
     * Méthode qui permet de créer un utilisateur.
     * Récupère l'identifiant et le mot de passe.
     * @return
     */
    public String doCreate(){
        // Par défaut, le result est "input"
        String vResult = ActionSupport.INPUT;
        if ( pseudo != null) {
            if (!this.hasErrors()) {
                try {
                    managerFactory.getUtilisateurManager().addUtilisateur(new Utilisateur(pseudo,password));
                    // Si ajout avec succès -> Result "success"

                    vResult = ActionSupport.SUCCESS;
                    this.addActionMessage("Channel ajouté avec succès");

                } catch (FunctionalException pEx) {
                    // Sur erreur fonctionnelle on reste sur la page de saisie
                    // et on affiche un message d'erreur
                    this.addActionError(pEx.getMessage());

                }
            }
        }
        return vResult;

    }

    /**
     * Action permettant la connexion d'un utilisateur
     * On vérifie si le compte existe.
     * Ensuite on vérifie si le mot de passe est bon.
     * @return input / success
     */
    public String doLogin() throws FunctionalException, NotFoundException {
        String vResult = ActionSupport.INPUT;
        if (pseudo != null) {
            utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(pseudo);
            if(password.equals(utilisateur.getPassword())){
                this.session.put("user", utilisateur);
                vResult = ActionSupport.SUCCESS;
            }
        }
        return vResult;
    }


    /**
     * Action de déconnexion d'un utilisateur
     * @return success
     */
    public String doLogout() throws NotFoundException {

        managerFactory.getUtilisateurManager().deleteUtilisateur(utilisateur);
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

