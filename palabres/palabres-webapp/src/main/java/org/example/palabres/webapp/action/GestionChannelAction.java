package org.example.palabres.webapp.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.example.palabres.business.impl.manager.ChatManagerImpl;
import org.example.palabres.model.bean.chat.Channel;
import org.example.palabres.model.bean.chat.Message;
import org.example.palabres.model.bean.utilisateur.Utilisateur;
import org.example.palabres.model.exception.FunctionalException;
import org.example.palabres.model.exception.NotFoundException;
import org.example.palabres.model.exception.TechnicalException;
import org.example.palabres.webapp.WebappHelper;

import java.util.List;
import java.util.Map;

public class GestionChannelAction extends ActionSupport{


    private Integer id;
    private String name;

    ActionInvocation pInvocation;
    LoginAction login;

    private List<Channel> listChannel;
    private List<Message> listMessage;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getContenuMessage() {
        return contenuMessage;
    }

    public void setContenuMessage(String contenuMessage) {
        this.contenuMessage = contenuMessage;
    }

    private Message message;
    private String contenuMessage;

    private Channel channel;

    public Integer getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public void setId(Integer pId){
        id=pId;
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

    public String doList() {
        listChannel = WebappHelper.getManagerFactory().getChatManager().getListChannel();
        return ActionSupport.SUCCESS;
    }

    public String doDetail() throws TechnicalException {
        if (this.channel == null) {
            this.addActionError("Vous devez indiquer un nom de channel");
        } else {
            try {

                listMessage = WebappHelper.getManagerFactory().getChatManager().getChannelMessageList(this.channel);
                if(listMessage.isEmpty()){
                    this.addActionMessage("Channel vide.");
                }
            } catch (NotFoundException pE) {
                this.addActionError("Channel non trouvé. name = " + name);
            }
        }
        return (this.hasErrors()) ? ActionSupport.ERROR : ActionSupport.SUCCESS;
    }

    public String doHome(){
        return ActionSupport.SUCCESS;
    }

    public String doCreate() {

        // Par défaut, le result est "input"
        String vResult = ActionSupport.INPUT;

        if (this.channel != null) {
            if (!this.hasErrors()) {
                try {
                    WebappHelper.getManagerFactory().getChatManager().addChannel(this.channel);
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

    public String newMessage(){

        String vResult = null;
        if(this.message != null){
            if (!this.hasErrors()) {
                try {
                    channel = WebappHelper.getManagerFactory().getChatManager().getChannel("Random");
                    this.message.setAuthor(WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur("Merlin"));
                    WebappHelper.getManagerFactory().getChatManager().addMessage(channel,this.message);
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
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    vResult = ActionSupport.ERROR;
                }
            }
        }
        return vResult;
    }
}
