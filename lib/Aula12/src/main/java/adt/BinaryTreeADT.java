/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import java.util.Iterator;

import exception.ElementNotFoundException;

/**
 * * Interface BinaryTreeADT define as operações para uma Arvore Binaria geral.
 * Diferentes tipos de Arvore Binaria irão estender esta interface para
 * completar o conjunto de operações necessarias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public interface BinaryTreeADT<T> {

    /**
     * Retorna uma referencia para o elemento root(raiz)
     *
     * @return T, referencia para o elemento root(raiz)
     */
    public T getRoot();

    /**
     * Retorna verdadeiro se a arvore binaria estiver vazia, e falso se a arvore
     * binaria possuir pelo menos um elemento
     *
     * @return verdadeiro se a arvore binaria esta vazia
     */
    public boolean isEmpty();

    /**
     * Retorna o numero de elementos na arvore binaria
     *
     * @return int, numero de elementos na arvore
     */
    public int size();

    /**
     * Retorna verdadeiro se a arvore binaria possuir o elemento enviado por
     * parametro, falso se nao possuir esse elemento.
     *
     * @param targetElement, elemento que se deseja procurar na arvore binaria
     * @return verdadeiro caso a arvore possua o elemento falso caso nao possua
     */
    public boolean contains(T targetElement);

    /**
     * Retorna a referencia para o elemento enviado por parametro se o encontrar
     * na arvore binaria.
     *
     * @param targetElement, elemento para procurar na arvore binaria
     * @return T, referencia do elemento enviado por parametro
     * @throws ElementNotFoundException se nao encontrar o elemento enviado por
     * parametro
     */
    public T find(T targetElement) throws ElementNotFoundException;

    /**
     * Retorna uma string que representa a Arvore Binaria
     *
     * @return uma string que representa a Arvore Binaria
     */
    @Override
    public String toString();

    /**
     * Executa uma travessia In-Order sobre a arvore binaria, chamando um metodo
     * de recursivo que começa com a raiz, root da arvore
     *
     * @return um iterador sobre os elementos da arvore Binaria
     */
    public Iterator<T> iteratorInOrder();

    /**
     * Executa uma travessia In-Order sobre a arvore binaria, chamando um metodo
     * de recursivo que começa com a raiz, root da arvore
     *
     * @return um iterador sobre os elementos da arvore Binaria
     */
    public Iterator<T> iteratorPreOrder();

    /**
     * Executa uma travessia In-Order sobre a arvore binaria, chamando um metodo
     * de recursivo que começa com a raiz, root da arvore
     *
     * @return um iterador sobre os elementos da arvore Binaria
     */
    public Iterator<T> iteratorPostOrder();

    /**
     * Executa uma travessia Level-Order sobre a arvore binaria, usando uma
     * queue
     *
     * @return um iterador sobre os elementos da arvore Binaria
     */
    public Iterator<T> iteratorLevelOrder();
}
