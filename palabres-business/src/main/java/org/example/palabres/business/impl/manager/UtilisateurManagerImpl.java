package org.example.palabres.business.impl.manager;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.example.palabres.business.contract.manager.UtilisateurManager;
import org.example.palabres.model.bean.utilisateur.Utilisateur;
import org.example.palabres.model.exception.FunctionalException;
import org.example.palabres.model.exception.NotFoundException;


@Named
public class UtilisateurManagerImpl extends AbstractManager implements UtilisateurManager {

    /** Logger pour la classe */
    private static final Log LOGGER = LogFactory.getLog(UtilisateurManagerImpl.class);

    @Inject
    @Named("refListUtilisateur")
    private List<Utilisateur> listUtilisateur;


    private Optional<Utilisateur> searchUtilisateur(String pPseudo) {
        return this.listUtilisateur.stream()
                                  .filter(u -> StringUtils.equals(u.getPseudo(), pPseudo))
                                  .findFirst();
    }


    @Override
    public Utilisateur getUtilisateur(String pPseudo) throws NotFoundException {
        Utilisateur vUtilisateur
            = this.searchUtilisateur(pPseudo)
                  .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé : PSEUDO=" + pPseudo));
        return vUtilisateur;
    }



    @Override
    public void addUtilisateur(Utilisateur pUtilisateur) throws FunctionalException {
        if (pUtilisateur == null) {
            throw new FunctionalException("L'objet Utilisateur ne doit pas être null !");
        }

        // Validation du bean pChannel
        Set<ConstraintViolation<Utilisateur>> vViolations = getConstraintValidator().validate(pUtilisateur);
        if (!vViolations.isEmpty()) {
            throw new FunctionalException("L'objet Utilisateur est invalide",
                                          new ConstraintViolationException(vViolations));
        }

        // Vérification qu'un Utilisateur de même pseudo n'existe pas déjà
        if (searchUtilisateur(pUtilisateur.getPseudo()).isPresent()) {
            throw new FunctionalException("Le pseudo est déjà utilisé !");
        }

        this.listUtilisateur.add(pUtilisateur);
    }


    @Override
    public void deleteUtilisateur(Utilisateur pUtilisateur) {
        this.listUtilisateur.remove(pUtilisateur);
    }


    @Override
    public List<Utilisateur> getListUtilisateur() {
        return Collections.unmodifiableList(listUtilisateur);
    }
}
