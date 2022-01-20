/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

import adt.StackADT;
import exception.EmptyCollectionException;

/**
 * ArrayStack é um genero especifico de uma Stack. É uma stack(pilha) que tem
 * por base um array de elementos genericos, este é garantido pela variavel top
 * que alem de representar o numero de elementos representa tambem a posiçao
 * livre, onde sera inserido o proximo elemento.Estende a StackADT, para
 * garantir que as operações base de uma Stack são implementadas
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class ArrayStack<T> implements StackADT<T> {

    /**
     * constante int , que representa a capacidade inicial do array.
     */
    private final int DEFAULT_CAPACITY = 100;
    /**
     * int, representa o numero de elementos da stack mas tambem a proxima
     * posição disponivel no array.
     */
    private int top;
    /**
     * array, representa a stack atraves de array de elementos genericos.
     */
    private T[] stack;

    /**
     * Cria uma Stack vazia Usando a DEFAULT_CAPACITY, para definir o tamanho da
     * Stack.
     */
    public ArrayStack() {
        this.top = 0;
        this.stack = (T[]) (new Object[this.DEFAULT_CAPACITY]);
    }

    /**
     * Cria uma stack vazia, onde o tamanho desta é especificado pelo utilizador
     *
     * @param initialCapacity representa o tamanho da Stack
     */
    public ArrayStack(int initialCapacity) {
        this.top = 0;
        this.stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Adiciona o elemento enviado como parametro no topo da stack, caso seja
     * necessario expande a capacidade da stack.
     *
     * @param element , elemento generico a ser inserido na stack
     */
    @Override
    public void push(T element) {
        if (this.size() == this.stack.length) {
            expandCapacity();
        }
        this.stack[top] = element;
        this.top++;
    }

    /**
     * Remove o elemento que se encontra no topo da stack e retorna uma
     * referencia para este. Lança uma EmptyCollectionException se a stack
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
        this.top--;
        T result = this.stack[this.top];
        this.stack[this.top] = null;

        return result;
    }

    /**
     * Retorna uma referencia para o elemento que se encontra no topo da stack,
     * este não é removido da stack. Lança uma EmptyCollectionException se a
     * stack estiver vazia
     *
     * @return T, elemento do topo da stack
     * @throws EmptyCollectionException se a stack estiver vazia
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        return this.stack[this.top - 1];
    }

    /**
     * Expande a capacidade do array de elementos genericos que representa a
     * stack. Cria uma nova Stack com o tamanho da stack atual mais a constante
     * da capacidade por defeito. Copia todos os elementos da stack atual, para
     * a nova stack. Substitui a Stack pela nova Stack, expandindo assim a sua
     * capacidad
     *
     */
    private void expandCapacity() {
        T[] newStack = (T[]) (new Object[this.size() + this.DEFAULT_CAPACITY]);
        for (int i = 0; i < this.size(); i++) {
            newStack[i] = this.stack[i];
        }

        this.stack = newStack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return (this.top == 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.top;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String s = " \n ------------- S T A C K -----------------------\n";
        for (int i = this.top - 1; i >= 0; i--) {
            s += "Elemento " + i + ":  " + this.stack[i].toString() + "\n";
        }
        s += "----------------------------------------------------- \n";
        return s;
    }
}
