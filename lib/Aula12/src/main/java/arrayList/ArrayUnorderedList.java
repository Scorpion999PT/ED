/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayList;

import adt.UnorderedListADT;
import exception.EmptyCollectionException;

/**
 * Classe especifica de uma ArrayList, trata-se de uma ArrayUnorderedList, ou
 * seja de uma Lista Desordenada. No entanto esta encontra-se "ordenada" mas
 * pela ordem que o utilizador deseja e não por um conjunto de caracteristicas
 * dos elementos inseridos.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Cria uma Lista Desordenada vazia, usando a DEFAULT_CAPACITY, para definir o tamanho
     * da Lista.
     */
    public ArrayUnorderedList() {
        super();
    }

    /**
     * Cria uma Lista Desordenada vazia, onde o tamanho desta é especificado pelo utilizador
     *
     * @param tam, representa o tamanho da Lista
     */
    public ArrayUnorderedList(int tam) {
        super(tam);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToFront(Object add) {
        if (count == list.length) {
            expandCapacity();
        }

        shiftRight(0, add);
        count++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToRear(Object add) {
        if (count == list.length) {
            expandCapacity();
        }
        this.list[count] = (T) add;
        count++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToAfter(Object element, Object target) {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista encontra-se vazia.");
        }

        int tam = list.length;

        if (count == list.length) {
            expandCapacity();
        }

        for (int x = 0; x < tam; x++) {
            if (target.equals(list[x])) {
                shiftRight(x + 1, element);
                break;
            }
        }
        count++;

    }

}
