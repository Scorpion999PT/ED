/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package node;

/**
 * HeapNode é uma estrutura que cria "blocos de armazenamento" (para o seu pai e
 * uma referencia para o BinaryTreeNode ou seja para o elemento atual e para os
 * seus filhos). A informação do HeapNode é guardada no BinaryTreeNode e é um
 * elemento generico. Ao guardar informação relativa ao parent e aos leftChild e
 * rightChild, podemos percorrer todos os HeapNode's da raiz para o ultimo
 * elemento mas tambem do ultimo elemento para a raiz.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class HeapNode<T> extends BinaryTreeNode<T> {

    /**
     * HeapNode<T>, referencia para o HeapNode "pai"
     */
    private HeapNode<T> parent;

    /**
     * Cria um HeapNode que guarda o elemento enviado por parametro.
     *
     * @param obj elemento a ser guardado no HeapNode.
     */
    public HeapNode(T obj) {
        super(obj);
        this.parent = null;
    }

    public HeapNode<T> getParent() {
        return this.parent;
    }

    public void setParent(HeapNode<T> newParent) {
        this.parent = newParent;
    }
}
