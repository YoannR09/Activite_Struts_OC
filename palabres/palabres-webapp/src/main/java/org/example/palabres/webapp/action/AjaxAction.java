package org.example.palabres.webapp.action;


import java.util.List;
import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;
import org.example.palabres.business.contract.ManagerFactory;
import org.example.palabres.model.bean.chat.Channel;
import org.example.palabres.model.bean.chat.Message;
import org.example.palabres.model.exception.NotFoundException;
import org.example.palabres.model.exception.TechnicalException;
import org.example.palabres.webapp.WebappHelper;


/**
 * Action de démo pour les appels AJAX
 */
public class AjaxAction extends ActionSupport {

    // ==================== Attributs ====================
    @Inject
    private ManagerFactory managerFactory;

    // ----- Eléments en sortie
    private Channel channel;
    private List<Message> listM;


    // ==================== Getters/Setters ====================
    public List<Message> getListMessage() {
        return listM;
    }
    public Channel getChannel(){
        return channel;
    }


    // ==================== Méthodes ====================
    public String execute() {
        return ActionSupport.SUCCESS;
    }


    /**
     * Action "AJAX" renvoyant la liste des projets
     * @return success
     */
    public String doAjaxGetListMessage() throws NotFoundException, TechnicalException {
        channel = WebappHelper.getManagerFactory().getChatManager().getChannel("Random");
        listM = managerFactory.getChatManager().getChannelMessageList(channel);
        return ActionSupport.SUCCESS;
    }
}