package org.example.palabres.model.bean.chat;

import org.apache.commons.lang3.StringUtils;


/**
 * Classe représentant un channel de discussion
 */
public class Channel {

    // ==================== Attributs ====================
    private String name;


    // ==================== Constructeurs ====================
    /**
     * Constructeur.
     */
    public Channel() {
    }


    /**
     * Constructeur.
     *
     * @param pName -
     */
    public Channel(String pName) {
        name = pName;
    }


    // ==================== Getters/Setters ====================
    public String getName() {
        return name;
    }
    public void setName(String pName) {
        name = pName;
    }


    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        vStB.append(" {")
            .append("name=").append(name)
            .append("}");
        return vStB.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
            || (obj instanceof Channel && StringUtils.equals(((Channel) obj).getName(), name));
    }

    @Override
    public int hashCode() {
        return this.name != null ? this.name.hashCode() : super.hashCode();
    }
}
