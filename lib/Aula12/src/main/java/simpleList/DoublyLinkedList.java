/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleList;

import exception.EmptyCollectionException;
import exception.InvalidElementException;
import node.DoubleNode;

/**
 * DoublyLinkedList trata-se de uma coleção linear, composta por Nodes. É
 * possivel percorrer a lista duplemente ligada através das referencias dos
 * Nodes, estes possuem referencias relativamente aos Nodes anteriores e aos
 * Nodes seguintes.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class DoublyLinkedList<T> {

    /**
     * int, representa o numero de elementos da DoublyLinkedList.
     */
    private int count;

    /**
     * LinearNode<T>, representa o primeiro elemento da DoublyLinkedList.
     */
    private DoubleNode<T> head;
    /**
     * LinearNode<T>, representa o ultimo elemento da DoublyLinkedList.
     */
    private DoubleNode<T> tail;

    /**
     * Construtor de uma DoublyLinkedList vazia.
     */
    public DoublyLinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = head;
    }

    /**
     * Adiciona um novo elemento valido a DoublyLinkedList. O elemento é
     * transformado num Node e apenas posteriormente é adicionado logo aseguir
     * ao head. Apenas não é possivel adicionar o elemento a lista se este for
     * nulo ou se ja existir um elemento igual.
     *
     * @param data, elemento que se deseja adicionar a lista
     * @throws exceptions.InvalidElementException, caso o elemento não seja
     * valido para ser adicionado
     */
    public void add(T data) throws InvalidElementException {
        if (data == null) {
            throw new InvalidElementException("Não é possivel adicionar um elemento nulo");
        }

        DoubleNode<T> newNode = new DoubleNode(data);
        if (this.head == null && this.tail == null) {
            this.head = newNode;
            this.tail = this.head;
            this.count++;
        } else if (search(data) == 1) {
            this.head.setPrevious(newNode);
            newNode.setNext(this.head);
            this.head = newNode;
            this.count++;
        } else {
            throw new InvalidElementException("O elemento:" + data + " ja se encontra anteriormente adicionado");
        }

    }

    /**
     * Verifica se ja existe um elemento na DoublyLinkedList
     *
     * @param data, elemento a procurar na DoubblyLinkedList
     * @return -1 se o elemento nao existir e 1 caso ele exista
     */
    private int search(T data) {

        DoubleNode<T> temp = this.head;
        while (temp != null) {
            if ((temp.getData()).equals(data)) {
                return -1;
            }
            temp = temp.getNext();
        }
        return 1;
    }

    /**
     * Remove um elemento a cabeça ou a cauda de DoublyLinkedList
     *
     * @param toDel, elemento que se deseja remover
     * @throws exceptions.InvalidElementException, caso o elemento que se deseja
     * remover nao existir na DoublyLinkedList
     */
    public void remove(T toDel) throws InvalidElementException {
        this.verifyEmpty();
        int auxCount = this.count;
        if (search(toDel) != 1) {
            if (this.head.getData().equals(toDel)) {
                this.removeFirst();
            } else if (this.tail.getData().equals(toDel)) {
                this.removeLast();
            }
            if (auxCount - 1 != this.count) {
                throw new InvalidElementException("O elemento: " + toDel + " não foi elimininado pois nao se "
                        + "encontra nas extremidades da lista");
            }
        } else {
            throw new InvalidElementException("O elemento: " + toDel + " não existe na lista");
        }
    }

    /**
     * Verifica se o elemento é simultaneamente igual a cabeça e a cauda da
     * lista, se tal acontecer o elemento é removido.
     *
     * @param toDel, elemento a remover
     * @return true caso o elemento tenha sido removido, false caso nao tenha
     * sido removido
     */
    private boolean removeFirstLast(T toDel) {
        if (this.head.getData().equals(toDel) && this.tail.getData().equals(toDel)) {
            this.head = null;
            this.tail = this.head;
            this.count--;
            return true;
        }
        return false;
    }

    /**
     * Remove o primeiro elemento da DoublyLinkedList
     *
     */
    public void removeFirst() {
        this.verifyEmpty();
        if (!removeFirstLast(this.head.getData())) {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
            this.count--;
        }
    }

    /**
     * Remove o ultimo elemento da DoublyLinkedList
     *
     */
    public void removeLast() {
        this.verifyEmpty();
        if (!removeFirstLast(this.tail.getData())) {
            this.tail = this.tail.getPrevious();
            this.tail.setNext(null);
            this.count--;
        }
    }

    /**
     * Verifica se a lista se encontra vazia, se tal for verdadeiro é lançada
     * uma exceção
     *
     * @throws exceptions.EmptyException, se a lista se encontrar vazia
     */
    private void verifyEmpty() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("A lista encontra-se vazia, logo não "
                    + "é possivel eliminar nenhum elemento");
        }
    }

    /**
     * Retorna verdadeiro se a lista nao possuir elementos,e falso caso possua
     * pelo menos um elemento
     *
     * @return verdadeiro se a lista nao possuir elemento, e falso caso possua
     * pelo menos um elemento
     *
     */
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /**
     * Retorna uma string que representa a DoublyLinkedList
     *
     * @return string, que representa a DoublyLinkedList
     */
    @Override
    public String toString() {
        this.verifyEmpty();
        DoubleNode<T> temp = this.head;
        String s = "\n------------------Lista Duplamente Ligada----------------\n";
        while (temp != null) {
            s += "Nó: " + temp.getData().toString() + "\n";
            temp = (DoubleNode<T>) temp.getNext();
        }
        s += "-------------------------------------------------------";
        return s;
    }

    /**
     * Retorna o primeiro DoubleNode da lista(head)
     *
     * @return DoubleNode<T>, o primeiro da lista(head)
     */
    public DoubleNode<T> firstNode() {
        DoubleNode<T> temp = head;
        return temp;
    }

    /**
     * Retorna o ultimo DoubleNode da lista(tail)
     *
     * @return DoubleNode<T>, ultimo da lista(tail)
     */
    public DoubleNode<T> lastNode() {
        DoubleNode<T> temp = tail;
        return temp;
    }

    /**
     * Retorna uma string que representa um DoubleNode.No entanto o metodo tem
     * como objetivo ser recursivo percorrendo assim toda a lista, ou seja de
     * head para tail, para tal é enviado como parametro o primeiro node da
     * lista, o node da head.
     *
     * @param node, elemento que se deseja obter a representação pertencente
     * lista, para o metodo percorrer toda a lista no sentido desejado deve ser
     * enviado o head da lista
     * @return string, uma representação do LinearNode
     */
    public String toStringRecurHead(DoubleNode<T> node) {
        String s = "\n" + node.toString();
        if (node.getNext() != null) {
            s += toStringRecurHead(node.getNext());
        }
        return s;
    }

    /**
     * Retorna uma string que representa um DoubleNode.No entanto o metodo tem
     * como objetivo ser recursivo percorrendo assim toda a lista no sentido
     * contrario, ou seja de tail para head, para tal é enviado como parametro o
     * ultimo node da lista, o node da tail.
     *
     * @param node, , elemento que se deseja obter a representação pertencente
     * lista, para o metodo percorrer toda a lista no sentido desejado deve ser
     * enviado o tail da lista
     * @return string, uma representação do LinearNode
     */
    public String toStringRecurTail(DoubleNode<T> node) {
        String s = "\n" + node.toString();
        if (node.getPrevious() != null) {
            s += toStringRecurTail(node.getPrevious());
        }
        return s;
    }

}
