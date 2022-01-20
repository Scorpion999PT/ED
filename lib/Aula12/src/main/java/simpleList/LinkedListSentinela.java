/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleList;

import exception.EmptyCollectionException;
import exception.NullPointerException;
import exception.InvalidElementException;
import node.LinearNode;

/**
 * LinkedListSentinela trata-se de uma coleção linear, que usa nós sentinelas do
 * tipo LinearNode Ao usar Nós Sentinela o head e o tail nunca são preenchidos
 * com dados apenas são preenchidas as referencias para os elementos seguintes.
 * O LinearNode é permite percorrer a lista ligada através das referencias dos
 * LinearNodes, estes possuem referencias relativamente aos LinearNodes
 * seguintes.
 *
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class LinkedListSentinela<T> {

    /**
     * int, representa o numero de elementos da LinkedListSentinela.
     */
    private int count;

    /**
     * LinearNode<T>, representa o primeiro elemento da LinkedListSentinela.
     */
    private LinearNode<T> head;
    /**
     * LinearNode<T>, representa o ultimo elemento da LinkedListSentinela.
     */
    private LinearNode<T> tail;

    /**
     * Construtor de LinkedListSentinela vazia Para usarmos os nós sentinelas
     * devemos igualar a cabeça e a cauda a um no sentinela, apesar de estes
     * nunca possuir dados mas sim apenas referencias para os elementos. Devemos
     * tambem ligar o head e a tail para assim se tratar de uma lista ligada
     *
     */
    public LinkedListSentinela() {
        this.count = 0;
        this.head = new LinearNode();
        this.tail = new LinearNode();
        this.head.setNext(tail); //Head e Tail estão ligados
    }

    /**
     * Adiciona um elemento valido a Lista Sentinela. O elemento é transformado
     * num LinearNode e apenas posteriormente é adiconado, aseguir ao head.
     * Apenas não é possivel adicionar o elemento a lista se este for nulo ou se
     * ja tiver sido anteriormente adicionado
     *
     * @param data, elemento que se deseja adicionar a lista
     * @throws InvalidElementException, caso o elemento não seja valido para ser
     * adicionado
     */
    public void add(T data) throws InvalidElementException {
        if (data == null) {
            throw new InvalidElementException("Não é possivel adicionar um elemento nulo");
        }

        LinearNode<T> newNode = new LinearNode(data);
        if (search(data) == null) {
            LinearNode<T> nextToHead = this.head.getNext();
            newNode.setNext(nextToHead);
            this.head.setNext(newNode);
            this.count++;
        } else {
            throw new InvalidElementException("O elemento:" + data + " ja se encontra anteriormente adicionado");
        }
    }

    /**
     * Remove um elemento da Lista Sentinela
     *
     * @param toDel, elemento que se deseja remover
     * @throws exceptions.NullPointerException, se não encontrar o elemento que
     * se deseja remover
     * @throws exceptions.EmptyException, se a lista nao possuir nenhum elemento
     * para ser removido
     */
    public void remove(T toDel) throws EmptyCollectionException, NullPointerException {
        if (this.count > 1) {
            LinearNode<T> previous = search(toDel);
            if (previous == null) {
                throw new NullPointerException("A lista nao possui o seguinte elemento :  "
                        + toDel);
            }
            LinearNode<T> TwoNexts = previous.getNext().getNext();
            previous.setNext(TwoNexts);
            this.count--;
        } else {
            throw new EmptyCollectionException("A lista encontra-se vazia, logo não "
                    + "é possivel eliminar nenhum elemento:");
        }
    }

    /**
     * Pesquisa o Node<T> que possa existir na Lista Sentinela, atraves do
     * parametro. Caso encontre o elemento envia o LinearNode anterior, se não
     * encontrar retorna um LinearNode<T> nulo
     *
     * @param toDel, elemento a ser pesquisado na Lista Sentinela
     * @return Node<T> onde se encontra o elemento, ou null caso não o encontre
     */
    private LinearNode<T> search(T toDel) {
        LinearNode<T> previous = head;
        while (previous.getNext() != tail) {
            if ((previous.getNext().getElement()).equals(toDel)) {
                return previous;
            }
            previous = previous.getNext();
        }
        return null;

    }

    /**
     * Retorna uma string que representa a Lista Sentinela
     *
     * @return string, que representa a Lista Sentinela
     */
    @Override
    public String toString() {
        if (this.count == 0) {
            return "A lista encontra-se vazia";
        }
        LinearNode<T> temp = this.head.getNext();
        String s = "\n------------------Lista Ligada Sentinela---------------- \n";
        for (int i = 0; i < this.count; i++) {
            s += "Nó: " + temp.getElement().toString() + "\n";
            temp = temp.getNext();
        }
        s += "-------------------------------------------------------";
        return s;
    }
}
