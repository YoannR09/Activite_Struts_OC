package org.example.palabres.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.example.palabres.business.contract.ManagerFactory;
import org.example.palabres.model.bean.chat.Channel;
import org.example.palabres.model.bean.chat.Message;
import org.example.palabres.model.bean.utilisateur.Utilisateur;
import org.example.palabres.model.exception.FunctionalException;
import org.example.palabres.model.exception.NotFoundException;
import org.example.palabres.model.exception.TechnicalException;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Class qui gère :
 * L'affichage de la liste des messages.
 * L'affichage des détails d'un channel.
 * Le retour à l'accueil.
 * Créer un nouveau channel.
 */
public class GestionChannelAction extends ActionSupport{



    @Inject
    private ManagerFactory managerFactory;

    // ----- Eléments Struts

    private Map<String, Object> session;

    // ------Attributs
    private         Integer         id;
    private         String          name;
    private         String          nameChannel;
    private         Message         message;
    private         Channel         channel;
    private         List<Channel>   listChannel;
    private         List<Message>   listMessage;
    private         Utilisateur     utilisateur;
    private         String          contenuMessage;



    // ------Getters/Setters--------

    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }

    public String getName(){
        return name;
    }
    public void setName(String pName){
        name=pName;
    }

    public List<Channel> getListChannel(){
        return listChannel;
    }
    public List<Message> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<Message> listMessage) {
        this.listMessage = listMessage;
    }


    public Channel getChannel(){
        return channel;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getContenuMessage() {
        return contenuMessage;
    }

    public void setContenuMessage(String contenuMessage) {
        this.contenuMessage = contenuMessage;
    }



    // -------- Traitement struts

    /**
     * Méthode qui affiche la liste des channels.
     * @return
     */
    public String doList() {
        listChannel = managerFactory.getChatManager().getListChannel();
        return ActionSupport.SUCCESS;
    }


    /**
     * Méthode qui permet d'afficher les détails d'un channel.
     * Affiche le nom du channel.
     * Affiche les messages contenu dans ce channel.
     * @return
     * @throws TechnicalException
     */
    public String doDetail() throws TechnicalException {
        if (this.channel == null) {
            this.addActionError("Vous devez indiquer un nom de channel");
        } else {
            try {
                channel = managerFactory.getChatManager().getChannel(this.channel.getName());
                listMessage = managerFactory.getChatManager().getChannelMessageList(channel);
                nameChannel = this.channel.getName();
                if(listMessage.isEmpty()){
                    this.addActionMessage("Channel vide.");
                }
            } catch (NotFoundException pE) {
                this.addActionError("Channel non trouvé. name = " + name);
            }
        }
        return (this.hasErrors()) ? ActionSupport.ERROR : ActionSupport.SUCCESS;
    }

    /**
     * Méthode pour retourner à la page d'accueil.
     * @return
     */
    public String doHome(){
        return ActionSupport.SUCCESS;
    }


    /**
     * Méthode qui permet de créer un channel.
     * Ajout le channel avec le nom entrée dans le textfield.
     * @return
     */
    public String doCreate() {

        // Par défaut, le result est "input"
        String vResult = ActionSupport.INPUT;
        if (name != null) {
            if (!this.hasErrors()) {
                try {
                    managerFactory.getChatManager().addChannel(new Channel(name));
                    // Si ajout avec succès -> Result "success"

                    vResult = ActionSupport.SUCCESS;
                    this.addActionMessage("Channel ajouté avec succès");

                } catch (FunctionalException pEx) {
                    // Sur erreur fonctionnelle on reste sur la page de saisie
                    // et on affiche un message d'erreur
                    this.addActionError(pEx.getMessage());

                } catch (TechnicalException pEx) {
                    // Sur erreur technique on part sur le result "error"
                    this.addActionError(pEx.getMessage());
                    vResult = ActionSupport.ERROR;
                }
            }
        }
        return vResult;
    }
}
