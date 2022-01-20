/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 * Interface HeapADT define as operações para uma Arvore Binaria Heap. Extende a
 * interface BinaryTreeADT, pois la encontram-se as operações para uma Arvore
 * Binaria. Diferentes tipos Heap's irão estender esta interface para completar
 * o conjunto de operações necessarias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public interface HeapADT<T> extends BinaryTreeADT<T> {

    /**
     * Adiciona um elemento especifico a Heap
     *
     * @param obj, elemento que ira ser adicionado a Heap
     */
    public void addElement(T obj);

    /**
     * Remove o elemento de menor valor da Heap
     *
     * @return T, elemento menor da Heap
     */
    public T removeMin();

    /**
     * Retorna uma referencia com o menor valor da Heap
     *
     * @return T, referencia do elemento menor da Heap
     */
    public T findMin();

}
