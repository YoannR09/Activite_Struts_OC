package org.example.palabres.business.impl.manager;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.example.palabres.business.contract.manager.ChatManager;
import org.example.palabres.business.contract.manager.UtilisateurManager;
import org.example.palabres.model.bean.chat.Channel;
import org.example.palabres.model.bean.chat.Message;
import org.example.palabres.model.exception.FunctionalException;
import org.example.palabres.model.exception.NotFoundException;
import org.example.palabres.model.exception.TechnicalException;


@Named
public class ChatManagerImpl extends AbstractManager implements ChatManager {

    /** Logger pour la classe */
    private static final Log LOGGER = LogFactory.getLog(ChatManagerImpl.class);

    @Inject
    private UtilisateurManager utilisateurManager;

    @Inject
    @Named("testMessageSource")
    private Enumeration<Message> messageSource;

    private final Map<Channel, List<Message>> messages = new HashMap<>();

    private int fakeAddMessageMin = 0;
    private int fakeAddMessageMax = 5;


    // ==================== Getters/Setters ====================
    public void setFakeAddMessageMin(int pFakeAddMessageMin) {
        fakeAddMessageMin = pFakeAddMessageMin;
    }
    public void setFakeAddMessageMax(int pFakeAddMessageMax) {
        fakeAddMessageMax = pFakeAddMessageMax;
    }

    // ==================== Méthodes ====================
    @Inject
    @Named("refListChannel")
    private void setListChannel(List<Channel> pListChannel) {
        pListChannel.forEach(c -> messages.put(c, new ArrayList<>()));
    }


    /**
     * Renvoie la liste des messages du {@link Channel}.
     *
     * @param pChannel -
     * @return List
     * @throws NotFoundException Si le {@link Channel} n'existe pas
     * @throws TechnicalException Si {@code pChannel} est {@code null}
     */
    public List<Message> getChannelMessageList(Channel pChannel) throws NotFoundException, TechnicalException {
        if (pChannel == null) {
            throw new TechnicalException("L'objet Channel ne doit pas être null !");
        }
        List<Message> vMessageList = this.messages.get(pChannel);
        if (vMessageList == null) {
            throw new NotFoundException("Channel inexistant : NAME=" + pChannel.getName());
        }
        return vMessageList;
    }

    /**
     * Recherche le {@link Channel} par son nom
     * @param pName -
     * @return Optional
     */
    private Optional<Channel> searchChannel(String pName) {
        Optional<Channel> vChannel
            = messages.keySet().stream()
                      .filter(c -> StringUtils.equals(c.getName(), pName))
                      .findFirst();
        return vChannel;
    }


    /**
     * Simule l'ajout de messages par des utilisateurs.
     *
     * @param pChannel the p channel
     * @throws NotFoundException Si le {@link Channel} n'existe pas
     * @throws TechnicalException Si {@code pChannel} est {@code null}
     */
    public void fakeNewMessages(Channel pChannel) throws NotFoundException, TechnicalException {
        // Ici, je simule l'ajout de messages par d'autres utilisateurs !
        List<Message> vMessageList = this.getChannelMessageList(pChannel);
        for (int vI = 0; vI < RandomUtils.nextInt(fakeAddMessageMin, fakeAddMessageMax + 1); vI++) {
            if (messageSource.hasMoreElements()) {
                vMessageList.add(messageSource.nextElement());
                LOGGER.debug("fakeNewMessage : " + vMessageList.get(vMessageList.size() - 1));
            }
        }
    }


    @Override
    public List<Channel> getListChannel() {
        return new ArrayList<>(messages.keySet());
    }


    @Override
    public Channel getChannel(String pName) throws NotFoundException {
        Channel vChannel = this.searchChannel(pName)
                               .orElseThrow(() -> new NotFoundException("Channel non trouvé : NAME=" + pName));
        return vChannel;
    }


    @Override
    public void addChannel(Channel pChannel) throws FunctionalException, TechnicalException {
        if (pChannel == null) {
            throw new FunctionalException("L'objet Channel ne doit pas être null !");
        }

        // Validation du bean pChannel
        Set<ConstraintViolation<Channel>> vViolations = getConstraintValidator().validate(pChannel);
        if (!vViolations.isEmpty()) {
            throw new FunctionalException("L'objet Channel est invalide",
                                          new ConstraintViolationException(vViolations));
        }

        // Vérification qu'un channel de même nom n'existe pas déjà
        if (searchChannel(pChannel.getName()).isPresent()) {
            throw new FunctionalException("Le channel existe déjà !");
        }

        this.messages.put(pChannel, new ArrayList<>());
    }


    @Override
    public List<Message> getListNewMessage(Channel pChannel,
                                           Integer pLastMessageIndex) throws NotFoundException, TechnicalException {
        int vLastMessageIndex = pLastMessageIndex == null ? -1 : pLastMessageIndex;

        List<Message> vListMessage = this.getChannelMessageList(pChannel);

        List<Message> vRetour;
        if (vLastMessageIndex == vListMessage.size() - 1) {
            vRetour = new ArrayList<>();
        } else if (vLastMessageIndex >= -1 && vLastMessageIndex < vListMessage.size()) {
            vRetour = new ArrayList<>(vListMessage.subList(vLastMessageIndex + 1, vListMessage.size()));
        } else {
            throw new TechnicalException("Last message index invalide !");
        }

        this.fakeNewMessages(pChannel);
        return vRetour;
    }


    @Override
    public void addMessage(Channel pChannel,
                           Message pMessage) throws FunctionalException, NotFoundException, TechnicalException {
        if (pChannel == null) {
            throw new FunctionalException("L'objet Channel ne doit pas être null !");
        }
        if (pMessage == null) {
            throw new FunctionalException("L'objet Message ne doit pas être null !");
        }

        // Validation du bean pMessage
        Set<ConstraintViolation<Message>> vViolations = getConstraintValidator().validate(pMessage);
        if (!vViolations.isEmpty()) {
            throw new FunctionalException("L'objet Message est invalide",
                                          new ConstraintViolationException(vViolations));
        }

        List<Message> vListMessage =  this.getChannelMessageList(pChannel);
        vListMessage.add(pMessage);
    }

}
