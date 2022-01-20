/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayList;

import adt.ListADT;
import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import java.util.Iterator;

/**
 * Classe Abstrata de um ArrayList. Tem como objetivo implementar os metodos
 * gerais para uma DoublyLinkedList, as ArrayList especificas apenas
 * necessitarem de implementar as suas operações especificas.A ArrayList tem na
 * base da sua construção um array de elementos genericos.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public abstract class ArrayList<T> implements ListADT<T> {

    /**
     * constante int, que representa a capacidade inicial, por defeito, do
     * array.
     */
    protected final int DEFAULT_CAPACITY = 5;

    /**
     * int, representa o numero de elementos da Lista.
     */
    protected int count;

    /**
     * array circular, de elementos genericos que representa a Lista.
     */
    protected T[] list;

    /**
     * Cria uma Lista vazia, usando a DEFAULT_CAPACITY, para definir o tamanho
     * da Lista.
     */
    public ArrayList() {
        this.list = (T[]) (new Object[this.DEFAULT_CAPACITY]);
        this.count = 0;
    }

    /**
     * Cria uma Lista vazia, onde o tamanho desta é especificado pelo utilizador
     *
     * @param initialCapacity, representa o tamanho da Lista
     */
    public ArrayList(int initialCapacity) {
        this.list = (T[]) (new Object[initialCapacity]);
        this.count = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia!");
        }
        T send = this.first();
        this.shiftLeft(0);
        this.count--;
        return send;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia!");
        }
        T send = this.last();

        list[this.count - 1] = null;
        this.count--;
        return send;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia!");
        }

        int pos = this.find(element);
        if (pos == -1) {
            throw new ElementNotFoundException("Elemento " + element + " nao encontrado");
        }
        T send = this.list[pos];
        shiftLeft(pos);
        count--;

        return send;
    }

    /**
     * Organiza a lista de elementos permintindo organizar os elementos para uma
     * remoção
     *
     * @param previous, elemento anterior ao elemento que se quer remover
     */
    protected void shiftLeft(int previous) {
        int tam = this.size() - 1;
        T[] temp = (T[]) new Object[tam];
        int x = 0, y = 0;

        for (; x < previous; x++) {
            temp[y] = list[x];
            y++;
        }

        for (x = previous + 1; x < this.size(); x++) {
            temp[y] = list[x];
            y++;
        }

        this.list = temp;
    }

    /**
     * Organiza a lista de elementos permitindo assim a adição de elementos nas
     * posições corretas
     *
     * @param inicial, elemento anterior ao elemento que sera adicionado
     * @param add, elemento a ser adicionado
     */
    protected void shiftRight(int inicial, Object add) {

        int tam = this.size() + 1;
        T[] newArray = (T[]) (new Object[tam]);
        newArray[inicial] = (T) add;

        for (int i = 0; i < inicial; i++) {
            newArray[i] = this.list[i];
        }

        for (int i = inicial + 1; i < tam; i++) {
            newArray[i] = list[i - 1];
        }

        this.list = newArray;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T first() {
        if (this.isEmpty()) {
            System.out.println("Lista vazia!");
        }
        return this.list[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T last() {
        if (this.isEmpty()) {
            System.out.println("Lista vazia!");
        }
        return this.list[this.count - 1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista vazia!");
        }

        int pos = this.find(target);

        return (pos != -1);
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
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {

        private int next;
        private int modCount;

        ArrayIterator() {
            this.next = 0;
            this.modCount = count;
        }

        @Override
        public boolean hasNext() {
            //Verifica se o array esta na capacidade total
            if(next>=modCount){
                return false;
            }
            return (list[next] != null);
        }

        @Override
        public T next() {

            if (this.modCount != count) {
                throw new java.util.ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NullPointerException("Não existe proximo elemento no iterador");
            }

            T send = list[next];
            next++;
            return send;

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String s = " \n ------------- A r r a y   L i s t -----------------------\n";
        int x = 0, i = 1, tam = list.length;
        while (x < tam) {
            T current = list[x];
            if (current != null) {
                s += i++ + "º  (pos" + x + ")--> " + current + "\n";
            }
            //s += i++ +"º  (pos"+x+")--> "+current+ "\n";
            x++;
        }

        s += "----------------------------------------------------- \n";
        return s;
    }

    /**
     * Expande a capacidade do array de elementos genericos que representa a
     * Lista. Cria uma nova Lista com o tamanho da Lista atual mais a constante
     * de tamanho por defeito. Copia todos os elementos da Lista atual, para a
     * nova Lista. Substitui a Lista pela nova Lista, expandindo assim a sua
     * capacidade
     *
     */
    protected void expandCapacity() {
        T[] newArray = (T[]) (new Object[list.length + DEFAULT_CAPACITY]);
        for (int x = 0; x < this.count; x++) {
            newArray[x] = this.list[x];
        }
        list = newArray;
    }

    /**
     * Procura um elemento na Lista e retorna a posição da sua primeira
     * instancia encontrada
     *
     * @param element, que se deseja procurar na Lista
     * @return a posição da primeira instancia do elemento, ou entao uma posiçao
     * invalida caso o elemento nao se encontra na lista
     */
    protected int find(T element) {
        int x = 0, send = -1;
        while (x < this.size() && send != 1) {
            if (list[x].equals(element)) {
                send = x;
            }
            x++;
        }

        return send;
    }
}
