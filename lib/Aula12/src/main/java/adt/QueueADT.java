/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import exception.EmptyCollectionException;

/**
 * Interface QueueADT define as operações para uma Queue geral. Diferentes tipos
 * de Queue irão estender esta interface para completar o conjunto de operações
 * necessarias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public interface QueueADT<T> {

    /**
     * Adiciona um elemento na retaguarda da Queue
     *
     * @param element, elemento que sera adicionado na retaguarda da Queue
     */
    public void enqueue(T element);

    /**
     * Remove e retorna o elemento da frente da Queue
     *
     * @return T, elemento removido ds frentre da Queue
     * @throws exceptions.EmptyCollectionException, se a Queue estiver vazia
     */
    public T dequeue() throws EmptyCollectionException;

    /**
     * Retorna sem remover o elemento que se encontra na frente da Queue
     *
     * @return o elemento na frente da Queue
     * @throws EmptyCollectionException
     */
    public T first() throws EmptyCollectionException;

    /**
     * Retorna verdadeiro se a Queue nao possuir nenhum elemento, falso se
     * possuir pelo menos um elemento
     *
     * @return boleano, se a Queue esta ou nao vazia
     */
    public boolean isEmpty();

    /**
     * Retorna o numero de elementos existentes na Queue
     *
     * @return int, numero de elementos existentes na Queue
     */
    public int size();

    /**
     * Retorna uma string que representa o conteudo da Queue. Uma Stack não
     * permite aos utilizadores aceder aos elementos no meio da fila, ou seja o
     * método é criado apenas por conveniência.
     *
     * @return string, representativa da Queue
     */
    @Override
    String toString();

}
