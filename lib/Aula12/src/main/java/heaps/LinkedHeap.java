/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps;

import adt.HeapADT;
import binaryTree.LinkedBinaryTree;
import exception.EmptyCollectionException;
import node.HeapNode;

/**
 * Classe especifica de uma BinaryTree, trata-se de um LinkedHeap, ou seja de
 * uma Arvore Binaria onde o elemento menor encontra-se na raiz. A LinkedHeap
 * tem na base uma LinkedBinaryTree
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {

    /**
     * HeapNode, que representa o ultimo node da LinkedHeap.
     */
    public HeapNode<T> lastNode;

    /**
     * Cria uma LinkedHeap do tipo LinkedBinaryTree.
     */
    public LinkedHeap() {
        super();
    }

    /**
     * Adiciona o elemento recebido como parametro na sua posiçao apropriada de
     * acordo com a sua key. Nota: elemntos iguais sao adicionados a direita.
     *
     * @param obj elemento a ser adicionado a Heap.
     */
    @Override
    public void addElement(T obj) {
        HeapNode<T> node = new HeapNode<T>(obj);
        if (root == null) {
            root = node;
        } else {
            HeapNode<T> nextParent = getNextParentAdd();
            if (nextParent.getLeft() == null) {
                nextParent.setLeft(node);
            } else {
                nextParent.setRight(node);
            }

            node = nextParent;
        }
        lastNode = node;
        count++;
        if (count > 1) {
            heapifyAdd();
        }
    }

    /**
     * Retorna o HeapNode parente do HeapNode atual.
     *
     * @return HeapNode<T>, HeapNode parente do HeapNode atual
     */
    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;
        while ((result != root)
                && (result.getParent().getLeft() != result)) {
            result = result.getParent();
        }
        if (result != root) {
            if (result.getParent().getRight() == null) {
                result = result.getParent();
            } else {
                result = (HeapNode<T>) result.getParent().getRight();
                while (result.getLeft() != null) {
                    result = (HeapNode<T>) result.getLeft();
                }
            }
        } else {
            while (result.getLeft() != null) {
                result = (HeapNode<T>) result.getLeft();
            }
        }

        return result;
    }

    /**
     * Reordena a Heap para manter a ordem depois de adicionar um node.
     */
    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = lastNode;

        temp = next.getElement();

        while ((next != root) && (((Comparable) temp).compareTo(next.getParent().getElement()) < 0)) {
            next.setElement(next.getParent().getElement());
            next = next.getParent();
        }
        next.setElement(temp);
    }

    /**
     *
     * Remove o elemento menor da Heap e retorna uma referencia para este. Lança
     * uma EmptyCollectionException se a heap estiver vazia
     *
     * @return T, referencia para o elemento removido da Heap
     * @throws EmptyCollectionException se a Heap estiver vazia
     */
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A Heap encontra-se vazia");
        }
        T minElement = root.getElement();
        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> next_last = getNewLastNode();
            if (lastNode.getParent().getLeft() == lastNode) {
                lastNode.getParent().setLeft(null);
            } else {
                lastNode.getParent().setRight(null);
            }
            root.setElement(lastNode.getElement());
            lastNode = next_last;
            heapifyRemove();
        }
        count--;

        return minElement;
    }

    /**
     * Retorna o HeapNode que passara a ser o ultimo HeapNode depois de ser
     * feita uma remoção
     *
     * @return HeapNode<T> , HeapNode que passara a ser o ultimo HeapNode depois
     * de ser feita uma remoção
     */
    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = lastNode;
        while ((result != root) && (result.getParent().getLeft() == result)) {
            result = result.getParent();
        }

        if (result != root) {
            result = (HeapNode<T>) result.getParent().getLeft();
        }
        while (result.getRight() != null) {
            result = (HeapNode<T>) result.getRight();
        }
        return result;
    }

    /**
     * Reordena a heap para manter a ordenação correta, depois de ser removido
     * um elemento.
     */
    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = (HeapNode<T>) root;
        HeapNode<T> left = (HeapNode<T>) node.getLeft();
        HeapNode<T> right = (HeapNode<T>) node.getRight();
        HeapNode<T> next;

        if ((left == null) && (right == null)) {
            next = null;
        } else if (left == null) {
            next = right;
        } else if (right == null) {
            next = left;
        } else if (((Comparable) left.getElement()).compareTo(right.getElement()) < 0) {
            next = left;
        } else {
            next = right;
        }
        temp = node.getElement();
        while ((next != null) && (((Comparable) next.getElement()).compareTo(temp) < 0)) {
            node.setElement(next.getElement());
            node = next;
            left = (HeapNode<T>) node.getLeft();
            right = (HeapNode<T>) node.getRight();

            if ((left == null) && (right == null)) {
                next = null;
            } else if (left == null) {
                next = right;
            } else if (right == null) {
                next = left;
            } else if (((Comparable) left.getElement()).compareTo(right.getElement()) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
        node.setElement(temp);
    }

    @Override
    public T findMin() {
        return this.root.getElement();
    }

}
