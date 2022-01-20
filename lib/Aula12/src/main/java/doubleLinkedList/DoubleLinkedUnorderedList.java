/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doubleLinkedList;

import adt.UnorderedListADT;
import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import node.DoubleNode;

/**
 * Classe especifica de uma DoublyLinkedList, trata-se de uma
 * DoubleLinkedUnorderedList, ou seja de uma Lista Desordenada. No entanto esta
 * encontra-se "ordenada" mas pela ordem que o utilizador deseja e não por um
 * conjunto de caracteristicas dos elementos inseridos.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class DoubleLinkedUnorderedList<T> extends DoublyLinkedList<T> implements UnorderedListADT<T> {

    /**
     * Cria uma DoubleLinkedUnorderedList vazia.
     */
    public DoubleLinkedUnorderedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToFront(Object add) {

        DoubleNode newNode = new DoubleNode(add);
        if (this.tail == null && this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            DoubleNode tempHead = this.head;
            this.head = newNode;
            this.head.setNext(tempHead);
            tempHead.setPrevious(this.head);
        }
        this.count++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToRear(Object add) {
        DoubleNode newNode = new DoubleNode(add);
        if (this.tail == null && this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            DoubleNode<T> tempNode = this.tail;
            this.tail = newNode;
            newNode.setPrevious(tempNode);
            tempNode.setNext(newNode);
        }
        this.count++;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToAfter(Object element, Object target) {

        if (isEmpty()) {
            throw new EmptyCollectionException("A lista encontra-se vazia.");
        }

        DoubleNode newNode = new DoubleNode(element);
        if (target.equals(this.head.getData())) {
            DoubleNode<T> tempNode = this.head;
            this.head = newNode;
            this.head.setNext(tempNode);
            tempNode.setPrevious(this.head);
            tempNode.setNext(tempNode.getNext());
        } else if (target.equals(this.tail.getData())) {
            DoubleNode<T> tempNode = this.tail;
            this.tail = newNode;
            newNode.setPrevious(tempNode);
            tempNode.setNext(newNode);
        } else {
            DoubleNode<T> nodeTarget = search(target);
            if (nodeTarget == null) {
                throw new ElementNotFoundException("Elemento" + target + "nao encontrado");

            }
            newNode.setPrevious(nodeTarget);
            newNode.setNext(nodeTarget.getNext());
            nodeTarget.getNext().setPrevious(newNode);
            nodeTarget.setNext(newNode);
        }
        this.count++;

    }

    /**
     * Procura um elemento na Lista e retorna o seu DoubleNode<T>
     *
     * @param target, elemento a procurar na lista
     * @return DoubleNode<T> que possui o elemento enviado por parametro ou um
     * DoubleNode<T> nulo caso não o encontre
     */
    private DoubleNode<T> search(Object target) {
        boolean find = false;
        DoubleNode current = this.head.getNext(), send = null;
        while (current != null) {
            if (current.getData().equals(target)) {
                send = current;
                break;
            } else {
                current = current.getNext();
            }

        }

        return send;

    }

}
