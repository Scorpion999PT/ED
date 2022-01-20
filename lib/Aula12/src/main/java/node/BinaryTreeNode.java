/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package node;

/**
 * BinaryTreeNode é uma estrutura que cria "blocos de armazenamento" (para a sua
 * informação e uma referencia para o filho direito do BinaryTreeNode atual mas
 * tambem para o filho esquerdo). A informação do BinaryTreeNode é um elemento
 * generico.Ao guardar informação relativa ao filho direito do BinaryTreeNode e
 * ao esquerdo, permite-nos poder escolher um filho para avançar numa das
 * direções pertendidas ate não existir mais filhos.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class BinaryTreeNode<T> {

    /**
     * T, representa o elemento generico que esta guardado no BinaryTreeNode
     * atual.
     */
    private T element;
    /**
     * BinaryTreeNode<T>, referencia para o BinaryTreeNode anterior.
     */
    private BinaryTreeNode<T> left;
    /**
     * BinaryTreeNode<T>, referencia para o proximo BinaryTreeNode.
     */
    private BinaryTreeNode<T> right;

    /**
     * Cria um novo BinaryTreeNode que guarda o elemento enviado por parametro.
     *
     * @param obj, elemento a ser guardado no BinaryTreeNode
     */
    public BinaryTreeNode(T obj) {
        this.element = obj;
        this.left = null;
        this.right = null;
    }

    /**
     * Retorna o numero de elementos não nulos dos filhos do BinaryTreeNode
     * atual
     *
     *
     * @return int, que representa o numero de filhos nao nulos do
     * BinaryTreeNode atual
     */
    public int numChildren() {
        int children = 0;

        if (this.left != null) {
            children = 1 + this.left.numChildren();
        }

        if (this.right != null) {
            children = children + 1 + this.right.numChildren();
        }

        return children;
    }

    /**
     * Retorna o BinaryTreeNode seguinte ao BinaryTreeNode atual
     *
     * @return BinaryTreeNode, seguinte ao BinaryTreeNode atual
     */
    public BinaryTreeNode<T> getLeft() {
        return this.left;
    }

    /**
     * Retorna o BinaryTreeNode seguinte ao BinaryTreeNode atual
     *
     * @return BinaryTreeNode, seguinte ao BinaryTreeNode atual
     */
    public BinaryTreeNode<T> getRight() {
        return this.right;
    }

    /**
     * Retorna o conteudo do BinaryTreeNode atual
     *
     * @return T,conteudo guardado no BinaryTreeNode
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Altera o BinaryTreeNode anterior
     *
     * @param left,representa um BinaryTreeNode, e este vai alterar o left do
     * BinaryTreeNode atual
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Altera o BinaryTreeNode seguinte
     *
     * @param right,representa um BinaryTreeNode, e este vai alterar o right do
     * BinaryTreeNode atual
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Altera o conteudo do BinaryTreeNode atual
     *
     * @param element, novo conteudo do BinaryTreeNode atual
     */
    public void setElement(T element) {
        this.element = element;
    }
}
