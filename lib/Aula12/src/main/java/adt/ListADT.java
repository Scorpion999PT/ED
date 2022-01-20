/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import exception.EmptyCollectionException;
import exception.ElementNotFoundException;
import java.util.Iterator;

/**
 * Interface ListADT define as operações para uma Lista geral. Diferentes tipos
 * de Lista irão estender esta interface para completar o conjunto de operações
 * necessarias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public interface ListADT<T> extends Iterable<T> {

    /**
     * Remove e retorna o primeiro elemento da Lista
     *
     * @return T, referencia para o elemento removido
     * @throws EmptyCollectionException caso a Lista esteja vazia
     */
    public T removeFirst() throws EmptyCollectionException;

    /**
     * Remove e retorna o ultimo elemento da Lista
     *
     * @return T, referencia para o elemento removido
     * @throws EmptyCollectionException caso a Lista esteja vazia
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Remove a primeira instancia encontrada do elemento enviado por parametro
     * e retorna uma referencia desta.
     *
     * @param targetElement, elemento a ser removido da Lista
     * @return T, referencia para o elemento removido
     * @throws EmptyCollectionException caso a Lista esteja vazia
     * @throws ElementNotFoundException se o elemento que se deseja remover nao
     * exista na Lista
     */
    public T remove(T targetElement) throws EmptyCollectionException, ElementNotFoundException;

    /**
     * Retorna sem remover, uma referencia para o primeiro elemento da Lista
     *
     * @return T, referencia para o primeiro elemento da Lista
     */
    public T first();

    /**
     * Retorna sem remover, uma referencia para o ultimo elemento da Lista
     *
     * @return T, referencia para o ultimo elemento da Lista
     */
    public T last();

    /**
     * Retorna verdadeiro se a Lista possuir o elemento,
     * falso se a Lista nao possuir o elemento
     *
     * @param target, elemento que sera procurado na Lista
     * @return verdadeiro se a Lista possui o elemento ou falso caso contrario
     * @throws EmptyCollectionException caso a Lista esteja vazia
     */
    public boolean contains(T target) throws EmptyCollectionException;

    /**
     * Retorna verdadeiro se a lista nao possuir elementos, falso caso a Lista
     * possuir pelo menos um elemento
     *
     * @return verdadeiro se a lista nao possuir elementos, falso caso contrario
     */
    public boolean isEmpty();

    /**
     * Retorna o numero de elementos da Lista
     *
     * @return int, que representa o numero de elementos da Lista
     */
    public int size();

    /**
     * Retorna um iterador para os elementos da Lista
     *
     * @return um iterador que percorre os elementos da Lista
     */
    @Override
    public Iterator<T> iterator();

    /**
     * Retorna uma string que representa o conteudo da Lista
     *
     * @return uma string que representa a Lista
     */
    @Override
    public String toString();
}
