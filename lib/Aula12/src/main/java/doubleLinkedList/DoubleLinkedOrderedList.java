/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doubleLinkedList;

import adt.OrderedListADT;
import exception.UnsupportedDataTypeException;
import node.DoubleNode;

/**
 * Classe especifica de uma DoublyLinkedList, trata-se de uma
 * DoubleLinkedOrderedList, ou seja de uma Lista Ordenada. A sua ordenação é
 * feita automaticamente pela classe Comparable, ou seja os elementos sao
 * comparados e adicionados na sua posição corretamente.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class DoubleLinkedOrderedList<T extends Comparable> extends DoublyLinkedList<T> implements OrderedListADT<T> {

    /**
     * Cria uma DoubleLinkedOrderedList vazia.
     */
    public DoubleLinkedOrderedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T comparable) {
        if (!(comparable instanceof Comparable)) {//Elementos não precisam de ser omparavéis , falar com david
            throw new UnsupportedDataTypeException("Not Comparable");
        }

        DoubleNode<T> newNode = new DoubleNode(comparable);

        if (tail == null && head == null) {
            head = newNode;
            tail = head;
        } else if (comparable.compareTo(head.getData()) <= 0) {
            DoubleNode<T> tempNode = head;
            head = newNode;
            head.setNext(tempNode);
            tempNode.setPrevious(head);
        } else {
            DoubleNode<T> current = head.getNext();
            boolean post = false;
            int x = 0, res;
            while (current != null && !post) {
                res = comparable.compareTo(current.getData());
                if (res > 0) {
                    current = current.getNext();
                } else {
                    newNode.setNext(current);
                    newNode.setPrevious(current.getPrevious());
                    current.getPrevious().setNext(newNode);
                    current.setPrevious(newNode);
                    //break;
                    post = true;
                }
            }
            if (!post) {
                DoubleNode<T> tempNode = tail;
                tail = newNode;
                newNode.setPrevious(tempNode);
                tempNode.setNext(newNode);
            }
        }
        count++;
    }
}
