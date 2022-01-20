/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

import adt.StackADT;
import exception.EmptyCollectionException;
import node.LinearNode;

/**
 * LinkedStack é um genero especifico de uma Stack. É uma stack(pilha) que tem
 * na sua base uma Lista Ligada, esta é garantida e construida atraves do
 * LinearNode pois este possui uma referencia para os LinearNode's seguintes.
 * Estende a StackADT, para garantir que as operações base de uma Stack são
 * implementadas
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class LinkedStack<T> implements StackADT<T> {

    /**
     * int, representa o numero de elementos da Stack.
     */
    private int count;

    /**
     * LinearNode<T>, representa o elemento na topo da Stack.
     */
    private LinearNode<T> head;

    /**
     * Cria uma Stack vazia.
     */
    public LinkedStack() {
        this.count = 0;
        this.head = null;
    }

    /**
     * Adiciona o elemento enviado como parametro no topo da Stack.
     *
     * @param element , elemento generico a ser inserido na Stack
     */
    @Override
    public void push(T element) {

        LinearNode<T> newNode = new LinearNode(element);
        newNode.setNext(this.head);
        this.head = newNode;
        this.count++;
    }

    /**
     * Remove o elemento que se encontra no topo da Stack e retorna uma
     * referencia para este. Lança uma EmptyCollectionException se a Stack
     * estiver vazia
     *
     * @return T elemento que foi removido do topo da Stack
     * @throws EmptyCollectionException se a stack estiver vazia
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        this.count--;
        T element = this.head.getElement();
        this.head = this.head.getNext();

        return element;
    }

    /**
     * Retorna uma referencia para o elemento que se encontra no topo da Stack,
     * este não é removido da Stack. Lança uma EmptyCollectionException se a
     * Stack estiver vazia
     *
     * @return T, elemento do topo da Stack
     * @throws EmptyCollectionException se a Stack estiver vazia
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        return this.head.getElement();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String s = " \n ------------- S T A C K -----------------------\n";
        LinearNode<T> temp = this.head;
        while (temp != null) {
            s += "Elemento:  " + temp.getElement() + "\n";
            temp = temp.getNext();
        }
        s += "----------------------------------------------------- \n";
        return s;
    }

}
