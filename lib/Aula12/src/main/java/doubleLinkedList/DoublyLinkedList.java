/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doubleLinkedList;

import exception.EmptyCollectionException;
import exception.ElementNotFoundException;
import adt.ListADT;
import node.DoubleNode;
import java.util.Iterator;

/**
 * Classe Abstrata de uma DoubleLinkedList. Tem como objetivo implementar os
 * metodos gerais para uma DoublyLinkedList, as DoublyLinkedList especificas
 * apenas necessitam de implementar as suas operações especificas. A
 * DoubleLinkedList tem na base da sua construção os DoubleNode
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public abstract class DoublyLinkedList<T> implements ListADT<T> {

    /**
     * DoubleNode<T>, representa o primeiro elemento da Lista.
     */
    protected DoubleNode<T> head;

    /**
     * DoubleNode<T>, representa o ultimo elemento da Lista.
     */
    protected DoubleNode<T> tail;

    /**
     * int, representa o numero de elementos da Lista.
     */
    protected int count;

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista encontra-se vazia");
        }
        T send = this.head.getData();

        if (size() == 1) {
            this.head = this.tail = null;
        } else {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
        }

        this.count--;

        return send;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista encontra-se vazia");
        }

        T send = this.tail.getData();

        if (size() == 1) {
            this.head = this.tail = null;
        } else {
            DoubleNode newTail = this.tail.getPrevious();
            newTail.setNext(null);
            this.tail = newTail;
        }

        this.count--;

        return send;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T remove(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista encontra-se vazia");
        }

        boolean found = false;
        DoubleNode<T> previous = null;
        DoubleNode<T> current = this.head;

        while (current != null && !found) {
            if (targetElement.equals(current.getData())) {
                found = true;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        if (!found) {
            throw new ElementNotFoundException("Elemento " + targetElement + " nao encontrado");
        }

        if (size() == 1) {
            this.head = this.tail = null;
        } else if (current.equals(this.head)) {
            this.head = current.getNext();
        } else if (current.equals(this.tail)) {
            this.tail = previous;
            this.tail.setNext(null);
        } else {
            previous.setNext(current.getNext());
        }

        this.count--;

        return current.getData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T first() {
        return this.head.getData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T last() {
        return this.tail.getData();
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista encontra-se vazia");
        }

        boolean found = false;
        DoubleNode<T> current = this.head;

        while (current != null && !found) {
            if (target.equals(current.getData())) {
                found = true;
            } else {
                current = current.getNext();
            }
        }

        return found;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.count;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private DoubleNode next;
        private int modCount;

        ListIterator() {
            this.next = head;
            this.modCount=count;
        }

        @Override
        public boolean hasNext() {
            return (this.next != null);
        }

        @Override
        public T next() {
             if (this.modCount != count) {
                throw new java.util.ConcurrentModificationException();
            }
            
            try {
                T send = (T) this.next.getData();
                this.next = this.next.getNext();
                return send;

            } catch (NullPointerException e) {
                System.out.println("Não existe proximo elemento no iterador: " + e);
                return null;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String s = "-----------Double Linked List-------------- \n";
        DoubleNode<T> current = this.head;
        while (current != null) {
            s += current.getData() + "\n";
            current = current.getNext();
        }
        s += "-------------------------------------------------";
        return s;
    }

}
