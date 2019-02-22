package org.example.palabres.business.contract;

import org.example.palabres.business.contract.manager.ChatManager;
import org.example.palabres.business.contract.manager.UtilisateurManager;


/**
 * Factory des Managers
 */
public interface ManagerFactory {

    ChatManager getChatManager();

    UtilisateurManager getUtilisateurManager();
}
