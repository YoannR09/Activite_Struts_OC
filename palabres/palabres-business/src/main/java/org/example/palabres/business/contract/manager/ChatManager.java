package org.example.palabres.business.contract.manager;

import java.util.List;

import org.example.palabres.model.bean.chat.Channel;
import org.example.palabres.model.bean.chat.Message;
import org.example.palabres.model.exception.FunctionalException;
import org.example.palabres.model.exception.NotFoundException;
import org.example.palabres.model.exception.TechnicalException;


/**
 * Manager du package « channel »
 */
public interface ChatManager {

    /**
     * Renvoie la liste des {@link Channel}
     *
     * @return Set
     */
    List<Channel> getListChannel();

    /**
     * Renvoie le {@link Channel} demandé
     *
     * @param pName le nome du channel
     * @return Le {@link Channel}
     * @throws NotFoundException Si le channel n'est pas trouvé
     */
    Channel getChannel(String pName) throws NotFoundException, TechnicalException;

    /**
     * Ajoute un nouveau {@link Channel}
     * @param pChannel -
     * @throws FunctionalException Channel invalide
     * @throws TechnicalException Erreur technique
     */
    void addChannel(Channel pChannel) throws FunctionalException, TechnicalException;


    /**
     * Renvoie la liste des nouveaux messages d'un channel à partir du dernier message id
     * @param pChannel -
     * @param pLastMessageIndex index du dernier message connu ({@code null} si aucun message connu)
     *  Si {@code null}, renvoye la liste complète des messages depuis le début
     * @return List
     */
    List<Message> getListNewMessage(Channel pChannel,
                                    Integer pLastMessageIndex) throws NotFoundException, TechnicalException;

    /**
     * Ajoute un nouveau {@link Message} dans un {@link Channel}
     * @param pMessage -
     * @throws FunctionalException Version invalide
     */
    void addMessage(Channel pChannel,
                    Message pMessage) throws FunctionalException, NotFoundException, TechnicalException;

    List<Message> getChannelMessageList(Channel pChannel) throws NotFoundException, TechnicalException;
}
