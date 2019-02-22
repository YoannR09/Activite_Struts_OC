package org.example.palabres.business.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import org.exemple.palabres.technical.structure.CyclicEnumeration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.example.palabres.model.bean.chat.Channel;
import org.example.palabres.model.bean.chat.Message;
import org.example.palabres.model.bean.utilisateur.Utilisateur;

import static org.example.palabres.business.impl.ChatConfig.Utilisateurs.*;


/**
 * Classe de
 */
@Configuration
public class ChatConfig {

    protected enum Utilisateurs {
        ARTHUR("Arthur"),
        GUENIEVRE("Guenièvre"),
        KADOC("Kadoc"),
        KARADOC("Karadoc"),
        LEODAGAN("Léodagan"),
        LE_ROI_BURGONDE("Le Roi Burgonde"),
        MERLIN("Merlin"),
        PERCEVAL("Perceval");

        private Utilisateur utilisateur;
        private Utilisateurs(String pPseudo) {
            utilisateur = new Utilisateur(pPseudo);
        }
        public Utilisateur u() {
            return utilisateur;
        }
    }


    @Bean(name = "refListUtilisateur")
    public List<Utilisateur> listUtilisateur() {
        return Arrays.stream(Utilisateurs.values()).map(Utilisateurs::u).collect(Collectors.toList());
    }


    @Bean(name = "refListChannel")
    public List<Channel> listChannel() {
        return Arrays.asList(
            new Channel("Kaamelott"),
            new Channel("Random")
        );
    }


    @Bean
    public Enumeration<Message> testMessageSource() {
        List<Message> vListMessage = Arrays.asList(
            new Message(ARTHUR.u(), "J'vous jure c'est pas bien, Il faut plus que vous parliez avec des gens"),
            new Message(ARTHUR.u(), "J'suis chef de guerre moi, j'suis pas là pour agiter des drapeaux et jouer d'la trompette..."),
            new Message(GUENIEVRE.u(), "Ah tiens, aujourd’hui j’ai fait tailler le rosier de l’arrière-cour parce qu’il en avait drôlement besoin."),
            new Message(GUENIEVRE.u(), "Le rapport, c'est qu'y a plus de pâte d'amande !"),
            new Message(KADOC.u(), "À Kadoc !"),
            new Message(KADOC.u(), "Tatan, elle fait des flans"),
            new Message(KADOC.u(), "Elle est où la poulette ?"),
            new Message(KARADOC.u(), "C'est du manger ça, ça sent pas mauvais !"),
            new Message(KARADOC.u(), "Oh, 2 secondes, y'a pas l'feu à la baignoire !"),
            new Message(KARADOC.u(), "Les chicots, c'est sacré ! Parce que si j'les lave pas maintenant, dans dix ans, c'est tout à la soupe. Et l'mec qui me fera manger de la soupe il est pas né !"),
            new Message(KARADOC.u(), "C'est pas faux"),
            new Message(LEODAGAN.u(), "Ce qu'il y a de bien avec les opinions tranchées, c'est qu'ça relance le débat."),
            new Message(LEODAGAN.u(), "Ma parole, des engins comme vous, ça devrait être fourni avec une notice !"),
            new Message(LE_ROI_BURGONDE.u(), "Arthour ! Pas changer assiette pour fromage."),
            new Message(LE_ROI_BURGONDE.u(), "Interprèèète ? Couhillère ?"),
            new Message(LE_ROI_BURGONDE.u(), "La fleur en bouquet fane, et jamais ne renaît !"),
            new Message(MERLIN.u(), "Qu'est-ce qui est petit et marron ?"),
            new Message(MERLIN.u(), "Alors, pour le ronflement c'est une crevette dans chaque narines. Vous allez vous rappeler, ou voulez qu'j'vous y marque ?"),
            new Message(PERCEVAL.u(), "Ils ont pas de bol, quand même ! Mettre au point un truc pareil et tomber sur des cerveaux comme nous !"),
            new Message(PERCEVAL.u(), "J'ai ma ch'mise mais c'est moux."),
            new Message(PERCEVAL.u(), "Nous on va faire que « chante Sloubi »"),
            new Message(PERCEVAL.u(), "Non, on fait 10 jets de 2 dés moins 4. Ca contient le résultat entre 16 et 116."),
            new Message(PERCEVAL.u(), "C'est un coup à s'planter ça ! De toute façon on dit « l'nord », selon comment on est tourné, ça change tout !"),
            new Message(PERCEVAL.u(), "On en a gros !")
        );
        Collections.shuffle(vListMessage);
        return new CyclicEnumeration<>(vListMessage);
    }

}
