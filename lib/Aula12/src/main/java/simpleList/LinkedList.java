/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleList;

import node.LinearNode;

import exception.EmptyCollectionException;
import exception.InvalidElementException;
import exception.NullPointerException;

/**
 * LinkedList trata-se de uma coleção linear, composta por LinearNodes. É
 * possivel percorrer a lista ligada através das referencias dos LinearNodes,
 * estes possuem referencias relativamente aos LinearNodes seguintes.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class LinkedList<T> {

    /**
     * int, representa o numero de elementos da LinkedList.
     */
    private int count;

    /**
     * LinearNode<T>, representa o primeiro elemento da LinkedList.
     */
    private LinearNode<T> head;

    /**
     * LinearNode<T>, representa o ultimo elemento da LinkedList.
     */
    private LinearNode<T> tail;

    /**
     * Construtor de uma LinkedList vazia.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * Retorna o primeiro LinearNode da lista (head)
     *
     * @return LinearNode<T>, o primeiro da lista (head)
     */
    public LinearNode<T> getHead() {
        return this.head;

    }
    
    /**
     * Retorna o ultimo LinearNode da lista (tail)
     *
     * @return LinearNode<T>, o ultimo da lista (tail)
     */
    public LinearNode<T> getTail() {
        return this.tail;

    }
    
    
    /**
     * Retorna o numero de nodes que existe da Lista
     *
     * @return int, o numero de elementos da lista
     */
    public int size() {
        return this.count;

    }

    /**
     * Adiciona um elemento valido a LinkedList. O elemento é transformado num
     * LinearNode e apenas posteriormente é adiconado, no head. Apenas não é
     * possivel adicionar o elemento a lista se este for nulo ou se ja tiver
     * sido anteriormente adicionado.
     *
     * @param data , elemento que se deseja adicionar a lista
     * @throws exceptions.InvalidElementException, , caso o elemento não seja
     * valido para ser adicionado
     */
    public void add(T data) throws InvalidElementException {
        if (data == null) {
            throw new InvalidElementException("Não é possivel adicionar um elemento nulo");
        }

        LinearNode<T> newNode = new LinearNode(data);
        if (this.tail == null && this.head == null) {
            this.tail = newNode;
            this.head = newNode;
            this.count++;
        } else if (search(data) == null) {
            newNode.setNext(this.head);
            this.head = newNode;
            this.count++;
        } else {
            throw new InvalidElementException("O elemento:" + data + " ja se encontra anteriormente adicionado");
        }

    }

    /**
     * Remove um elemento da LinkedList
     *
     * @param toDel , elemento que se deseja remover
     * @throws exceptions.EmptyException, se a lista nao possuir nenhum elemento
     * para ser removido
     * @throws exceptions.NullPointerException, se não encontrar o elemento que
     * se deseja remover
     */
    public void remove(T toDel) throws EmptyCollectionException, NullPointerException {
        if (this.count == 0) {
            throw new EmptyCollectionException("A lista encontra-se vazia, logo não "
                    + "é possivel eliminar nenhum elemento:");
        }

        if ((this.head.getElement()).equals(toDel)) {
            LinearNode<T> aux = this.head.getNext();
            this.head = aux;
            this.count--;
        } else {
            LinearNode<T> previous = search(toDel);
            if (previous == null) {
                throw new NullPointerException("A lista nao possui o seguinte elemento :  "
                        + toDel);
            } else {
                LinearNode<T> TwoNexts = previous.getNext().getNext();
                previous.setNext(TwoNexts);
                this.count--;
            }
        }
    }

    /**
     * Pesquisa o Node<T> que possa existir na Lista Ligada, atraves do
     * parametro Caso encontre o elemento envia o Node anterior, se não
     * encontrar retorna um Node<T> nulo
     *
     * @param toDel, elemento a ser pesquisado na Lista Sentinela
     * @return Node<T> anterior ao elemento enviado por parametro, ou null caso
     * não o encontre
     */
    public LinearNode<T> search(T toDel) {

        LinearNode<T> previous = this.head;
        LinearNode<T> temp = this.head.getNext();
        int find = 0;
        while (temp != null && find != 1) {
            if ((temp.getElement()).equals(toDel)) {
                find = 1;
            } else {
                previous = previous.getNext();
            }
            temp = temp.getNext();
        }
        if (find == 1) {
            return previous;
        } else {
            return null;
        }
    }

    /**
     * Retorna uma string que representa a LinkedList
     *
     * @return string, representa a LinkedList
     */
    @Override
    public String toString() {
        if (this.count == 0) {
            return "A lista encontra-se vazia";
        }
        LinearNode<T> temp = this.head;
        String s = "\n----------------------Lista Ligada------------------------\n";
        for (int i = 0; i < this.count; i++) {
            s += "No: " + temp.getElement().toString() + "\n";
            temp = temp.getNext();
        }
        s += " -------------------------------------------------------";
        return s;
    }

    /**
     * Retorna uma string que representa um LinearNode.No entanto o metodo tem
     * como objetivo ser recursivo percorrendo assim toda a lista, para tal é
     * enviado como parametro o primeiro node da lista.
     *
     * @param node, elemento que se deseja obter a representação pertencente
     * lista, para o metodo percorrer toda a lista deve ser enviado o head da
     * lista
     * @return string, uma representação do LinearNode
     */
    public String toStringRecur(LinearNode node) {
        String s = "\n" + node.toString();
        if (node.getNext() != null) {
            s += toStringRecur(node.getNext());
        }
        return s;

    }

    /**
     * Substitui todas as ocorrências de um elemento (o argumento
     * "existingElement") por um novo elemento(o argumento "newElement")
     *
     * @param existingElement, elemento a ser substituido
     * @param newElement, elemento pelo qual vamos substituir as ocorrencias do
     * elemento
     */
    void replace(T existingElement, T newElement) {
        LinearNode node = this.search(existingElement);
        node.setElement(newElement);
        System.out.println(this.toString());
        if (this.search(existingElement) != null) {
            replace(existingElement, newElement);
        }
    }

}
