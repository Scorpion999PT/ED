/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import adt.QueueADT;
import exception.EmptyCollectionException;

/**
 * ArrayStack é um genero especifico de uma Queue. É uma Queue(fila) que tem por
 * base um array circular de elementos genericos, este é garantido pelas
 * variaveis Front e Rear(Front representa a posiçao do array circular onde se
 * encontra o primeiro elemento da Queue e Rear representa a posição livre, onde
 * sera inserido o elemento que representa a retaguarda). Estende a QueueADT,
 * para garantir que as operações base de uma Stack são implementadas
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class CircularArrayQueue<T> implements QueueADT<T> {

    /**
     * constante int, que representa a capacidade inicial, por defeito,  do array.
     */
    private final int DEFAULT_CAPACITY = 5;

    /**
     * int, representa o numero de elementos da Queue.
     */
    private int count;

    /**
     * array circular, de elementos genericos que representa a Queue.
     */
    private T[] queue;

    /**
     * int, representa a posiçao do array circular onde se encontra o primeiro
     * elemento da Queue.
     */
    private int front;

    /**
     * int, representa a posição do array circular onde sera inserido o proximo
     * elemento, este sera a nova retaguarda da Queue.
     */
    private int rear;

    /**
     * Cria uma Queue vazia, usando a DEFAULT_CAPACITY, para definir o tamanho da
     * Queue.
     */
    public CircularArrayQueue() {
        this.count = 0;
        this.queue = (T[]) (new Object[this.DEFAULT_CAPACITY]);
        this.front = 0;
        this.rear = 0;
    }

    /**
     * Cria uma Queue vazia, onde o tamanho desta é especificado pelo utilizador
     *
     * @param initialCapacity representa o tamanho da Queue
     */
    public CircularArrayQueue(int initialCapacity) {
        this.count = 0;
        this.queue = (T[]) (new Object[initialCapacity]);
        this.front = 0;
        this.rear = 0;
    }

    /**
     * Adiciona o elemento enviado como parametro na retaguarda da Queue, caso
     * seja necessario expande a capacidade da Queue.
     *
     * @param element , elemento generico a ser inserido na Queue
     */
    @Override
    public void enqueue(T element) {

        if (this.count == this.queue.length) {
            expandCapacity();
        }

        this.queue[this.rear] = element;
        this.rear = (this.rear + 1) % this.queue.length;
        this.count++;
    }

    /**
     * Remove o elemento que se encontra na retaguarda da Queue e retorna uma
     * referencia para este. Lança uma EmptyCollectionException se a Queue
     * estiver vazia
     *
     * @return T, elemento que foi removido do topo da Queue
     * @throws EmptyCollectionException se a Queue estiver vazia
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue");
        }
        T result = this.queue[this.front];
        this.queue[this.front] = null;
        this.front = (this.front + 1) % this.queue.length;
        this.count--;
        return result;
    }

    /**
     * Retorna uma referencia para o elemento que se encontra na frente da
     * Queue, este não é removido da Queue. Lança uma EmptyCollectionException
     * se a Queue estiver vazia
     *
     * @return T, elemento da frente da Queue
     * @throws EmptyCollectionException se a Queue estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue");
        }
        return this.queue[this.front];
    }

    /**
     * Expande a capacidade do array(circular) de elementos genericos que
     * representa a Queue. Cria uma nova Queue com o tamanho da Queue atual mais
     * a constante de tamanho por defeito. Copia todos os elementos da Queue
     * atual, para a nova Queue. Substitui a Queue pela nova Queue, expandindo
     * assim a sua capacidade
     *
     */
    private void expandCapacity() {
        T[] newQueue = (T[]) (new Object[this.queue.length + this.DEFAULT_CAPACITY]);
        int x = 0;
        while (x != this.count) {
            newQueue[x] = this.queue[this.rear];
            x++;
            this.rear = (this.rear + 1) % this.queue.length;
        }
        this.front = 0;
        this.rear = this.count;
        this.queue = newQueue;
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
    public String toString() {
        String s = " \n ------------- A r r a y   Q u e u e -----------------------\n";
        int x = 0, rearAux = this.rear, i = 1, tam = this.queue.length;
        while (x < tam) {
            T current = this.queue[x];
            if (current != null) {
                s += i++ + "º  (pos" + rearAux + ")--> " + current + "\n";
            }
            //s += i++ +"º  (pos"+x+")--> "+current+ "\n";
            x++;
            rearAux = (rearAux + 1) % this.queue.length;
        }

        s += "----------------------------------------------------- \n";
        return s;
    }

}
