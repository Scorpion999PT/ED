/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps;

import adt.HeapADT;
import binaryTree.ArrayBinaryTree;
import exception.EmptyCollectionException;

/**
 * Classe especifica de uma BinaryTree, trata-se de um ArrayHeap, ou seja de uma
 * Arvore Binaria onde o elemento menor encontra-se na raiz. A ArrayHeap tem na
 * base um ArrayBinaryTree
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {

    /**
     * Cria um ArrayHeap do tipo ArrayBinaryTree.
     */
    public ArrayHeap() {
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
        if (count == tree.length) {
            expandCapacity();
        }
        tree[count] = obj;
        count++;
        if (count > 1) {
            heapifyAdd();
        }
    }

    /**
     * Expande a capacidade do array de genericos que representa a arvore
     * binaria de pesquisa.
     */
    private void expandCapacity() {
        T[] newArray = (T[]) (new Object[tree.length + this.CAPACITY]);
        for (int x = 0; x < this.count; x++) {
            newArray[x] = this.tree[x];
        }
        this.tree = newArray;
    }

    /**
     * Reordena a Heap para manter a ordem depois de adicionar um node.
     */
    private void heapifyAdd() {
        T temp;
        int next = count - 1;

        temp = tree[next];

        while ((next != 0) && (((Comparable) temp).compareTo(tree[(next - 1) / 2]) < 0)) {
            tree[next] = tree[(next - 1) / 2];
            next = (next - 1) / 2;
        }
        tree[next] = temp;
    }

    /**
     * Remove o elemento menor da Heap e retorna uma referencia para este. Lança
     * uma EmptyCollectionException se a heap estiver vazia
     *
     * @return T, referencia para o elemento removido da Heap
     * @throws EmptyCollectionException se a Heap estiver vazia
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A Heap encontra-se vazia");
        }
        T minElement = tree[0];
        tree[0] = tree[count - 1];
        heapifyRemove();
        count--;

        return minElement;
    }

    /**
     * Reordena a heap para manter a ordenação correta, depois de ser removido um elemento.
     */
    private void heapifyRemove() {
        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null)) {
            next = count;
        } else if (tree[left] == null) {
            next = right;
        } else if (tree[right] == null) {
            next = left;
        } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
            next = left;
        } else {
            next = right;
        }
        temp = tree[node];

        while ((next < count) && (((Comparable) tree[next]).compareTo(temp) < 0)) {
            tree[node] = tree[next];
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);
            if ((tree[left] == null) && (tree[right] == null)) {
                next = count;
            } else if (tree[left] == null) {
                next = right;
            } else if (tree[right] == null) {
                next = left;
            } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
        tree[node] = temp;
    }

    public void removeAll(){
        while(this.count!=0){
            this.removeMin();
        }
    }
    
    @Override
    public T findMin() {
        return this.tree[0];
    }

}
