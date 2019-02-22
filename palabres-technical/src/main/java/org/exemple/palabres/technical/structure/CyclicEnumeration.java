package org.exemple.palabres.technical.structure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * {@link java.util.Enumeration} cyclique : quand on arrive au bout de la liste on reprend du début.
 * @param <E>
 */
public class CyclicEnumeration<E> implements Enumeration<E> {
    private List<E> elements;
    private int currentIndex = 0;

    public CyclicEnumeration(List<? extends E> pFromList) {
        elements = new ArrayList<>(pFromList);
    }

    @Override
    public boolean hasMoreElements() {
        return true;
    }
    @Override
    public E nextElement() {
        currentIndex = ++currentIndex % elements.size();
        return elements.get(currentIndex);
    }
}
