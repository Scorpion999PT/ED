/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayList;

import exception.UnsupportedDataTypeException;
import adt.OrderedListADT;

/**
 * Classe especifica de uma DoublyLinkedList, trata-se de uma
 * DoubleLinkedOrderedList, ou seja de uma Lista Ordenada. A sua ordenação é
 * feita automaticamente pela classe Comparable, ou seja os elementos sao
 * comparados e adicionados na sua posição corretamente.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T item) {

        if (!(item instanceof Comparable)) {
            throw new UnsupportedDataTypeException("Not Comparable");
        }

        if (this.size() == this.list.length) {
            expandCapacity();
        }

        Comparable comparable = (Comparable) item;

        if (this.isEmpty()) {
            this.list[0] = item;
        } else if (comparable.compareTo(list[0]) <= 0) {
            shiftRight(0, comparable);
        } else if (comparable.compareTo(list[count - 1]) > 0) {
            this.list[count] = item;
        } else {
            for (int x = 1; x < this.size(); x++) {
                if (comparable.compareTo(list[x]) <= 0) {
                    shiftRight(x, comparable);
                    break;
                }

            }

        }
        this.count++;

    }

}
