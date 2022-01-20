/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import adt.QueueADT;
import exception.EmptyCollectionException;
import node.LinearNode;

/**
 * LinkedQueue é um genero especifico de uma Queue. É uma Queue(fila) que tem na
 * sua base uma Lista Ligada, esta é garantida e construida atraves do
 * LinearNode pois este possui uma referencia para os LinearNode's seguintes.
 * Estende a QueueADT, para garantir que as operações base de uma Stack são
 * implementadas
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class LinkedQueue<T> implements QueueADT {

    /**
     * LinearNode<T>, representa o elemento na frente da Queue, primeiro
     * elemento da fila.
     */
    private LinearNode<T> front;

    /**
     * LinearNode<T>, representa o elemento na retaguarda da Queue, ultimo
     * elemento da fila.
     */
    private LinearNode<T> rear;

    /**
     * int, representa o numero de elementos da Queue.
     */
    private int count;

    /**
     * Cria uma LinkedQueue vazia.
     */
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.count = 0;
    }

    /**
     * Adiciona o elemento enviado como parametro na frente da Queue.
     *
     * @param element , elemento generico a ser inserido na Queue
     */
    @Override
    public void enqueue(Object element) {
        LinearNode<T> newNode = new LinearNode(element);
        if (this.count == 0) {
            this.front = newNode;
            this.rear = newNode;
        } else {
            LinearNode<T> antesRear = this.rear;
            this.rear = newNode;
            antesRear.setNext(this.rear);
            /*Trocar por
            rear.setNext(newNode);
            rear=newNode;
             */
        }
        this.count++;
    }

    /**
     * Remove o elemento que se encontra na retaguarda da Queue e retorna uma
     * referencia para este. Lança uma EmptyCollectionException se a Queue
     * estiver vazia
     *
     * @return T , elemento que foi removido da retaguarda da Queue
     * @throws EmptyCollectionException se a Queue estiver vazia
     */
    @Override
    public Object dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue");
        }
        this.count--;
        T send = this.front.getElement();
        this.front = this.front.getNext();

        return send;
    }

    /**
     * Retorna uma referencia para o elemento que se encontra no frente da
     * Queue, este não é removido da Queue. Lança uma EmptyCollectionException
     * se a Queue estiver vazia
     *
     * @return T, elemento na frente da Queue
     * @throws EmptyCollectionException se a Queue estiver vazia
     */
    @Override
    public Object first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue");
        }
        return this.front.getElement();
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
        String s = " \n ------------- Q u e u e -----------------------\n";
        LinearNode<T> temp = this.front;
        while (temp != null) {
            s += "Elemento:  " + temp.getElement().toString() + "\n";
            temp = temp.getNext();
        }
        s += "----------------------------------------------------- \n";
        return s;
    }
}
