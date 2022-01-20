/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package node;

/**
 * DoubleNode é uma estrutura que cria "blocos de armazenamento" (para a sua
 * informação mas tambem referencias para os seguintes e anteriores nodes). A
 * informação do DoubleNode é um elemento generico.Ao guardar informação
 * relativa ao seguinte DoubleNode e ao anterior, permite-nos percorrer todos os
 * DoubleNode's em dois sentidos, no sentido dos DoubleNode's seguintes mas
 * tambem no sentido dos DoubleNode's anteriores
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class DoubleNode<T> {

    /**
     * previous, referencia para o DoubleNode anterior.
     */
    private DoubleNode<T> previous;
    /**
     * data, elemento generico guardado neste DoubleNode.
     */
    private T data;
    /**
     * next, referencia para o seguinte DoubleNode.
     */
    private DoubleNode<T> next;

    /**
     * Contrutor de um node vazio.
     */
    public DoubleNode() {
        this.next = null;
        this.data = null;
        this.previous = null;
    }

    /**
     * Construtor de um node, com um elemento
     *
     * @param newData, elemento generico a ser guardado no Node
     */
    public DoubleNode(T newData) {
        this.next = null;
        this.data = newData;
        this.previous = null;
    }

    /**
     * Retorna o DoubleNode anterior ao DoubleNode atual
     *
     * @return DoubleNode, anterior anterior ao DoubleNode atual
     */
    public DoubleNode<T> getPrevious() {
        return this.previous;
    }

    /**
     * Retorna o conteudo do DoubleNode atual
     *
     * @return T,conteudo guardado no Node
     */
    public T getData() {
        return this.data;
    }

    /**
     * Retorna o seguinte DoubleNode do DoubleNode atual
     *
     * @return DoubleNode, DoubleNode seguinte do DoubleNode atual
     */
    public DoubleNode<T> getNext() {
        return this.next;
    }

    /**
     * Altera o DoubleNode anterior
     *
     * @param previous, representa um DoubleNode, e este vai alterar o previous
     * do DoubleNode atual
     */
    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }

    /**
     * Altera o conteudo do DoubleNode atual
     *
     * @param data, novo conteudo do DoubleNode atual
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Altera o seguinte DoubleNode
     *
     * @param next , representa um DoubleNode, e este vai alterar o next do
     * DoubleNode atual.
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    /**
     * Retorna em formato de string o conteudo do DoubleNode
     *
     * @return string, com o conteudo do DoubleNode
     */
    @Override
    public String toString() {
        String s = "" + this.data;
        return s;
    }

}
