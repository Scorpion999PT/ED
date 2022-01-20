/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 * Interface UnorderedListADT define as operações para uma Lista Desordenada.
 * Extende a interface ListADT, pois la encontram-se as operações para uma Lista
 * Geral. Diferentes tipos de Listas Desordenadas irão estender esta interface
 * para completar o conjunto de operações necessarias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public interface UnorderedListADT<T> extends ListADT<T> {

    /**
     * Adiciona um elemento ao inicio da Lista
     *
     * @param add, , elemento que se deseja adicionar a lista.
     */
    public void addToFront(Object add);

    /**
     * Adiciona um elemento ao fim da Lista
     *
     * @param add, , elemento que se deseja adicionar a lista.
     */
    public void addToRear(Object add);

    /**
     * Adiciona um elemento a lista, a seguir a um elemento que ja exista na
     * lista
     *
     * @param element, elemento que se deseja adicionar a Lista.
     * @param target , elemento que ja existe na lista
     */
    public void addToAfter(Object element, Object target);
}
