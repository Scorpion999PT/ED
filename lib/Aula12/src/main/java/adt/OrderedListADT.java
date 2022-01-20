package adt;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Interface OrderedListADT define as operações para uma Lista Ordenada. Extende
 * a interface ListADT, pois la encontram-se as operações para uma Lista Geral.
 * Diferentes tipos de Listas Ordenadas irão estender esta interface para
 * completar o conjunto de operações necessarias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public interface OrderedListADT<T> extends ListADT<T> {

    /**
     * Adiciona um elemento comparavel a uma lista ordenada. Como o elemento é
     * comparavel, este é adicionado automaticamanente na sua posição mais
     * correta.
     *
     * @param comparable, elemento que se deseja adicionar a lista.
     */
    public void add(T comparable);

}
