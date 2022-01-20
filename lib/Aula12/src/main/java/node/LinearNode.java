/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package node;

/**
 * LinearNode é uma estrutura que cria "blocos de armazenamento" (para a sua
 * informação e uma referencia para o seguinte LinearNode). A informação do
 * LinearNode é um elemento generico.Ao guardar informação relativa ao seguinte
 * LinearNode permite-nos ir avançando por todos os LinearNode's ate
 * encontrar-mos um que não possua informação relativa ao seguinte.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class LinearNode<T> {

    /**
     * T, representa o elemento generico que esta guardado neste LinearNode.
     */
    private T element;

    /**
     * LinearNode<T>, referencia para o proximo LinearNode.
     */
    private LinearNode<T> next;

    /**
     * Cria um LinearNode vazio.
     */
    public LinearNode() {
        this.next = null;
        this.element = null;
    }

    /**
     * Cria um LinearNode que guarda o elemento enviado por parametro.
     *
     * @param elem , elemento a ser guardado no LinearNode
     */
    public LinearNode(T elem) {
        this.next = null;
        this.element = elem;
    }

    /**
     * Retorna o LinearNode que vem aseguir ao atual
     *
     * @return LinearNode<T> referencia para o proximo LinearNode
     */
    public LinearNode<T> getNext() {
        return this.next;
    }

    /**
     * Altera o LinearNode seguinte
     *
     * @param node, LinearNode seguinte do LinearNode atual
     */
    public void setNext(LinearNode<T> node) {
        this.next = node;
    }

    /**
     * Retorna o elemento guardado no LinearNode
     *
     * @return T elemento guardado neste LinearNode
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Altera o elemento guardado no LinearNode
     *
     * @param elem , elemento a ser guardado no LinearNode
     */
    public void setElement(T elem) {
        this.element = elem;
    }

}
