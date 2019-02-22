package org.example.palabres.business.contract.manager;

import java.util.List;

import org.example.palabres.model.bean.utilisateur.Utilisateur;
import org.example.palabres.model.exception.FunctionalException;
import org.example.palabres.model.exception.NotFoundException;


/**
 * Manager du package « utilisateur »
 */
public interface UtilisateurManager {

    /**
     * Renvoie la liste des {@link Utilisateur}
     *
     * @return List
     */
    List<Utilisateur> getListUtilisateur();

    /**
     * Renvoie l'{@link Utilisateur} demandé
     *
     * @param pPseudo le pseudo de l'Utilisateur
     * @return Le {@link Utilisateur}
     * @throws NotFoundException Si l'Utilisateur n'est pas trouvé
     */
    Utilisateur getUtilisateur(String pPseudo) throws NotFoundException;

    /**
     * Ajoute un nouvel {@link Utilisateur}
     *
     * @param pUtilisateur -
     * @throws FunctionalException Si le pseudo est déjà utilisé
     */
    void addUtilisateur(Utilisateur pUtilisateur) throws FunctionalException;

    /**
     * Supprime un {@link Utilisateur}
     *
     * @param pUtilisateur -
     */
    void deleteUtilisateur(Utilisateur pUtilisateur);
}
