package org.example.palabres.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.palabres.business.contract.ManagerFactory;
import org.example.palabres.business.contract.manager.ChatManager;
import org.example.palabres.business.contract.manager.UtilisateurManager;


/**
 * Implémentation de la {@link ManagerFactory}.
 */
@Named("managerFactory")
public class ManagerFactoryImpl implements ManagerFactory {

    @Inject
    private ChatManager chatManager;
    @Override
    public ChatManager getChatManager() {
        return this.chatManager;
    }
    public void setChatManager(ChatManager pChatManager) {
        chatManager = pChatManager;
    }


    @Inject
    private UtilisateurManager utilisateurManager;
    @Override
    public UtilisateurManager getUtilisateurManager() {
        return this.utilisateurManager;
    }
    public void setTicketManager(UtilisateurManager pUtilisateurManager) {
        utilisateurManager = pUtilisateurManager;
    }
}
