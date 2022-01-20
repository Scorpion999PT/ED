/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import exception.EmptyCollectionException;

/**
 * Interface StackADT define as operações para uma Stack geral. Diferentes tipos
 * de Stacks irão estender esta interface para completar o conjunto de operações
 * necessarias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public interface StackADT<T> {

    /**
     * Adiciona um elemento no topo da stack
     *
     * @param element, elemento que sera adicionado ao topo da Stack
     */
    public void push(T element);

    /**
     * Remove e retorna o elemento do topo da stack
     *
     * @return T, elemento removido do topo da Stack
     * @throws exceptions.EmptyCollectionException, se a Stack estiver vazia
     */
    public T pop() throws EmptyCollectionException;

    /**
     * Retorna sem remover o elemento que se encontra no topo da stack
     *
     * @return o elemento no topo da Stack
     * @throws exception.EmptyCollectionException, , se a Stack estiver vazia
     */
    public T peek() throws EmptyCollectionException;

    /**
     * Retorna verdadeiro se a Stack nao possuir nenhum elemento, falso se
     * possuir pelo menos um elemento
     *
     * @return boleano, se a Stack esta ou nao vazia
     */
    public boolean isEmpty();

    /**
     * Retorna o numero de elementos existentes na Stack
     *
     * @return int, numero de elementos existentes na Stack
     */
    public int size();

    /**
     * Retorna uma string que representa o conteudo da Stack. Uma Stack não
     * permite aos utilizadores aceder aos elementos no meio da fila, ou seja o
     * método é criado apenas por conveniência.
     *
     * @return string, representativa da Stack
     */
    @Override
    public String toString();

}
