/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryTree;

import adt.BinaryTreeADT;
import arrayList.ArrayUnorderedList;
import queue.LinkedQueue;
import exception.ElementNotFoundException;
import java.util.Iterator;

/**
 * ArrayBinaryTree trata-se de uma coleção não linear. FA ArrayBinaryTree tem na
 * base da sua construção um array de elementos genericos.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {

    /**
     * constante int, que representa a capacidade inicial, por defeito, do
     * array.
     */
    protected final int CAPACITY = 50;

    /**
     * int, representa o numero de elementos da BinaryTree.
     */
    protected int count;

    /**
     * array, de elementos genericos que representa a BinaryTree.
     */
    protected T[] tree;

    /**
     * Cria uma BinaryTree vazia, usando a CAPACITY, para definir o tamanho da
     * BinaryTree.
     */
    public ArrayBinaryTree() {
        this.count = 0;
        this.tree = (T[]) new Object[this.CAPACITY];
    }

    /**
     * Cria uma BinaryTree com o elemento recebido por parametro como o elemento
     * root(raiz) da Arvore Binaria, usando a CAPACITY, para definir o tamanho
     * da BinaryTree.
     *
     * @param element, elemento que se tornara o root(raiz) da nova BinaryTree
     */
    public ArrayBinaryTree(T element) {
        this.count = 1;
        this.tree = (T[]) new Object[this.CAPACITY];
        this.tree[0] = element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getRoot() {
        return this.tree[0];
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
        String s = "-----------Array Binary Tree-------------";

        s += "\n \nTravessia Nivel-Ordem: \n";
        Iterator<T> iterator = this.iteratorLevelOrder();

        while (iterator.hasNext()) {
            s += " - " + iterator.next();
        }

        s += "\n ---------------------------------------------- \n";
        return s;
    }

    /**
     * Retorna uma string que representa a Arvore Binaria atraves da Travessia
     * Em-Ordem
     *
     * @return uma string que representa a Arvore Binaria atraves da Travessia
     * Em-Ordem
     */
    public String toStringInOrder() {
        String s = "-----------Array Binary Tree(In-Order) -------------";
        Iterator<T> iterator = this.iteratorInOrder();

        while (iterator.hasNext()) {
            s += " - " + iterator.next();
        }
        s += "\n ---------------------------------------------- \n";
        return s;
    }

    /**
     * Retorna uma string que representa a Arvore Binaria atraves da Travessia
     * Pos Ordem
     *
     * @return uma string que representa a Arvore Binaria atraves da Travessia
     * Pos Ordem
     */
    public String toStringPostOrder() {
        String s = "-----------Array Binary Tree(Post-Order) -------------";
        Iterator<T> iterator = this.iteratorPostOrder();

        while (iterator.hasNext()) {
            s += " - " + iterator.next();
        }
        s += "\n ---------------------------------------------- \n";
        return s;
    }

    /**
     * Retorna uma string que representa a Arvore Binaria atraves da Travessia
     * Pre Ordem
     *
     * @return uma string que representa a Arvore Binaria atraves da Travessia
     * Pre Ordem
     */
    public String toStringPreOrder() {
        String s = "-----------Array Binary Tree(Pre-Order) -------------";
        Iterator<T> iterator = this.iteratorPreOrder();

        while (iterator.hasNext()) {
            s += " - " + iterator.next();
        }

        s += "\n ---------------------------------------------- \n";
        return s;
    }

    /**
     * Retorna uma string que representa a Arvore Binaria atraves da Travessia
     * Level Ordem
     *
     * @return uma string que representa a Arvore Binaria atraves da Travessia
     * Level Ordem
     */
    public String toStringLevelOrder() {
        String s = "-----------Array Binary Tree(Pre-Order) -------------";
        Iterator<T> iterator = this.iteratorLevelOrder();

        while (iterator.hasNext()) {
            s += " - " + iterator.next();
        }

        s += "\n ---------------------------------------------- \n";
        return s;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T targetElement) {
        try {
            T find = (T) find(targetElement);
        } catch (ElementNotFoundException ex) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++) {
            if (targetElement.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }
        }

        if (!found) {
            throw new ElementNotFoundException("binary tree dont have the element");
        }

        return temp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(0, tempList);
        return tempList.iterator();
    }

    /**
     * Executa uma travessia de ordem recursiva,para a travessia InOrder. A
     * travessia InOrder começa pelo filho esquerdo do nó no nível mais abaixo e
     * então todos os nós restantes subindo ate a raiz e depois descendo ate ao
     * ultimo filho do lado direito.
     *
     * @param node, posição que repsenta o node a ser usado como root para esta
     * travesia
     * @param tempList, lista temporaria a ser usada nesta travesia
     */
    protected void inorder(int node, ArrayUnorderedList<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder(node * 2 + 1, tempList);
                tempList.addToRear(tree[node]);
                inorder((node + 1) * 2, tempList);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preorder(0, tempList);
        return tempList.iterator();
    }

    /**
     * Executa uma travessia PreOrder de ordem recursiva. A travessia PreOrder
     * começa pela raiz e de seguida pelos seus filhos, primeiro os da esquerda
     * e depois os das direita.
     *
     * @param node, posição que repsenta o node a ser usado como root para esta
     * travesia
     * @param tempList, lista temporaria a ser usada nesta travesia
     */
    protected void preorder(int node, ArrayUnorderedList<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                tempList.addToRear(tree[node]);
                preorder(node * 2 + 1, tempList);
                preorder((node + 1) * 2, tempList);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postorder(0, tempList);
        return tempList.iterator();
    }

    /**
     * Executa uma travessia PostOrder de ordem recursiva. A travessia PostOrder
     * começa pelos filhos, primeiro os da esquerd, depois os da direita e por
     * ultimo a raiz
     *
     * @param node, posição que repsenta o node a ser usado como root para esta
     * travesia
     * @param tempList, lista temporaria a ser usada nesta travesia
     */
    protected void postorder(int node, ArrayUnorderedList<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                postorder(node * 2 + 1, tempList);
                postorder((node + 1) * 2, tempList);
                tempList.addToRear(tree[node]);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>();

        levelorder(0, results);

        return results.iterator();
    }

     /**
     * Executa uma travessia LevelOrder de ordem recursiva. A travessia
     * LevelOrder visita todos os nós em cada nível, um nível de cada vez,
     * começando pela raiz.
     *
     * @param start, posição que repsenta o node a ser usado como root para esta
     * travesia
     * @param results,  lista temporaria a ser usada nesta travesia
     */
    protected void levelorder(int start, ArrayUnorderedList<T> results) {
        LinkedQueue queue = new LinkedQueue();

        queue.enqueue(tree[start]);
        int n = 0;
        while (!queue.isEmpty()) {
            T element = (T) queue.dequeue();
            if (element != null) {
                results.addToRear(element);
                queue.enqueue(tree[((2 * n) + 1)]);
                queue.enqueue(tree[((2 * n) + 1)]);
            } else {
                queue.enqueue(null);
            }
            n++;
        }

    }
    

}
