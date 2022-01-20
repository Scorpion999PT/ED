/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryTree;

import arrayList.ArrayUnorderedList;
import queue.LinkedQueue;
import adt.BinaryTreeADT;
import exception.ElementNotFoundException;
import java.util.Iterator;
import node.BinaryTreeNode;

/**
 * LinkedBinaryTree trata-se de uma coleção não linear. A LinkedBinaryTree tem
 * na base da sua construção os BinaryTreeNode.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    /**
     * int, que representa o numero de elementos na Arvore Binaria de Pesquisa.
     */
    protected int count;

    /**
     * BinaryTreeNode, que representa o elemento raiz da Arvore Binaria de
     * Pesquisa, ou seja o root.
     */
    protected BinaryTreeNode<T> root;

    /**
     * Cria uma BinaryTree vazia.
     */
    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    /**
     * Cria uma BinaryTree com o elemento recebido por parametro como o elemento
     * root(raiz) da Arvore Binaria.
     *
     * @param element, elemento que se tornara o root(raiz) da nova BinaryTree
     */
    public LinkedBinaryTree(T element) {
        this.count = 1;
        this.root = new BinaryTreeNode<>(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getRoot() {
        return this.root.getElement();
    }

    /**
     * Retorna uma referencia para BinaryTreeNode do elemento root(raiz)
     *
     * @return BinaryTreeNode<T> do elemento root(raiz)
     */
    protected BinaryTreeNode<T> getNodeRoot() {
        return this.root;
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
    public boolean contains(T targetElement) {
        T find = (T) findAgain(targetElement, this.root);

        return (find != null);

    }

    /**
     * Metodo auxiliar ao metodo find, permite fazer uma maior distinção entre a
     * invocação do método original e as chamadas recursivas.Retorna uma
     * referencia para o elemento especifico se este for encontrado na
     * LinkedBinaryTree
     *
     * @param targetElement, elemento a ser procurado na Arvore Binaria
     * @param next,elemento para comecar a pesquisa
     * @return BinaryTreeNode, onde se encontra o elemento que se deseja
     * encontrar, ou entao null, caso este nao seja encontrado.
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        if (next.getElement().equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if (temp == null) {
            temp = findAgain(targetElement, next.getRight());
        }
        return temp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null) {
            throw new ElementNotFoundException("binary tree dont have the element");
        }

        return (current.getElement());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String s = "-----------Linked Binary Tree-------------";

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
        String s = "-----------Linked Binary Tree(In-Order) -------------";
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
        String s = "-----------Linked Binary Tree(Post-Order) -------------";
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
        String s = "-----------Linked Binary Tree(Pre-Order) -------------";
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
        String s = "-----------Linked Binary Tree(Pre-Order) -------------";
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
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        inorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Executa uma travessia de ordem recursiva,para a travessia InOrder. A
     * travessia InOrder começa pelo filho esquerdo do nó no nível mais abaixo e
     * então todos os nós restantes subindo ate a raiz e depois descendo ate ao
     * ultimo filho do lado direito.
     *
     * @param node, node a ser usado como root para esta travesia
     * @param tempList, lista temporaria a ser usada nesta travesia
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inorder(node.getRight(), tempList);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        preorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Executa uma travessia PreOrder de ordem recursiva. A travessia PreOrder
     * começa pela raiz e de seguida pelos seus filhos, primeiro os da esquerda
     * e depois os das direita.
     *
     * @param node, node a ser usado como root para esta travesia
     * @param tempList, lista temporaria a ser usada nesta travesia
     */
    protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preorder(node.getLeft(), tempList);
            preorder(node.getRight(), tempList);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        postorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Executa uma travessia PostOrder de ordem recursiva. A travessia PostOrder
     * começa pelos filhos, primeiro os da esquerd, depois os da direita e por
     * ultimo a raiz
     *
     * @param node, node a ser usado como root para esta travesia
     * @param tempList, lista temporaria a ser usada nesta travesia
     */
    protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postorder(node.getLeft(), tempList);
            postorder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>();

        levelorder(results);

        return results.iterator();
    }

    /**
     * Executa uma travessia LevelOrder. A travessia LevelOrder visita todos os
     * nós em cada nível, um nível de cada vez, começando pela raiz.
     *
     * @param results, lista temporaria a ser usada nesta travesia
     */
    protected void levelorder(ArrayUnorderedList<T> results) {

        LinkedQueue<T> nodes = new LinkedQueue<>();
        nodes.enqueue(root);

        while (!nodes.isEmpty()) {
            BinaryTreeNode<T> element = (BinaryTreeNode<T>) nodes.dequeue();

            if (element != null) {
                results.addToRear(element.getElement());
                if (element.getLeft() != null) {
                    nodes.enqueue(element.getLeft());
                }
                if (element.getRight() != null) {
                    nodes.enqueue(element.getRight());
                }
            } else {
                results.addToRear(null);
            }
        }
    }
}
