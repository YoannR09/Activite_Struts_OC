package org.example.palabres.webapp.action;


import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;
import org.example.palabres.business.contract.ManagerFactory;
import org.example.palabres.model.bean.chat.Channel;
import org.example.palabres.model.bean.chat.Message;
import org.example.palabres.model.bean.utilisateur.Utilisateur;
import org.example.palabres.model.exception.FunctionalException;
import org.example.palabres.model.exception.NotFoundException;
import org.example.palabres.model.exception.TechnicalException;


/**
 * Class qui gère les actions Ajax
 * Une action pour afficher les messages d'un channel.
 * Une action pour ajouter un message dans un channel.
 */
public class AjaxAction extends ActionSupport {

    // ==================== Attributs ====================
    @Inject
    private ManagerFactory managerFactory;
    private GestionChannelAction channelAction;
    private LoginAction loginAction;

    // ----- Eléments en sortie
    private Channel         channel;
    private Utilisateur     user;
    private List<Message>   listMessage;
    private String          name;
    private String          pseudo;
    private String          contenuMessage;



    // ==================== Getters/Setters ====================
    public List<Message> getListMessage() {
        return listMessage;
    }

    public Channel getChannel(){
        return channel;
    }

    public String getContenuMessage() { return contenuMessage; }

    public void setContenuMessage(String contenuMessage) { this.contenuMessage = contenuMessage; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPseudo() { return pseudo; }

    public void setPseudo(String pseudo) { this.pseudo = pseudo; }

    public Utilisateur getUser() { return user; }

    public void setUser(Utilisateur user) { this.user = user; }


    // ==================== Méthodes ====================
    public String execute() throws NotFoundException, TechnicalException {
        return ActionSupport.SUCCESS; }


    /**
     * Action "AJAX" renvoyant la liste des messages d'un channel.
     * @return success
     */
    public String doAjaxGetListMessage() { // throws NotFoundException, TechnicalException {
        String vResult = ActionSupport.SUCCESS;
        try {

            channel = managerFactory.getChatManager().getChannel(name);
            listMessage = managerFactory.getChatManager().getChannelMessageList(channel);

        } catch (NotFoundException | TechnicalException e) {
            this.addActionError(e.getMessage());
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }


    /**
     * Méthode pour l'ajout d'un message.
     * On récupère le channel en premier, puis l'utilisateur connecté.
     * On ajoute le message à ce channel.
     * On récupère le list de message pour l'afficher à jour.
     * @return
     * @throws NotFoundException
     * @throws FunctionalException
     * @throws TechnicalException
     */
    public String doAjaxAddMessage() { //throws NotFoundException, FunctionalException, TechnicalException {
        String vResult = ActionSupport.SUCCESS;
        try {
            channel = managerFactory.getChatManager().getChannel(name);

            user = managerFactory.getUtilisateurManager().getUtilisateur(pseudo);
            managerFactory.getChatManager().addMessage(channel, new Message(user, contenuMessage));
            listMessage = managerFactory.getChatManager().getChannelMessageList(channel);


            // vResult = this.doAjaxGetListMessage(); // En cas de succès : renvoyer à la page la liste des messages à jour.
        } catch (FunctionalException | NotFoundException | TechnicalException e) {
            this.addActionError(e.getMessage());
            vResult = ActionSupport.ERROR;
        }
        return vResult;

    }

    }