/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package node;

/**
 * PriorityQueueNode representa um nó em uma queue prioritaria que contem um
 * objeto comparavel, ordem e um valor de priorodade.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode> {

    private static int nextOrder = 0;
    private int priority;
    private int order;
    private T element;

    /**
     * Cria um novo PriorityQueueNode com dados especificos.
     *
     * @param obj, elemento do nó(PriorityQueueNode)
     * @param prio, prioridade do novo nó(PriorityQueueNode) em inteiro.
     */
    public PriorityQueueNode(T obj, int prio) {
        this.element = obj;
        this.priority = prio;
        this.order = nextOrder;
        nextOrder++;
    }

    /**
     * Retorna o elemento guardado no PriorityQueueNode
     *
     * @return T elemento guardado neste PriorityQueueNode
     */
    public T getElement() {
        return element;
    }

    /**
     * Retorna a prioridade do PriorityQueueNode
     *
     * @return int, prioridade do PriorityQueueNode
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Retorna a ordem do PriorityQueueNode
     *
     * @return int, ordem do PriorityQueueNode
     */
    public int getOrder() {
        return order;
    }

    /**
     * Retorna uma string representativa do PriorityQueueNode
     *
     * @return string, representativa do PriorityQueueNode
     */
    @Override
    public String toString() {
        String temp = (element.toString() + priority + order);
        return temp;

    }

    /**
     * Retorna 1 caso o PriorityQueueNode atual tiver prioridade superior ao
     * PriorityQueueNode recebido como parametro , se não retorna -1.
     *
     * @param obj, PriorityQueueNode a comparar com o atual
     * @return int, que representa a comparação do PriorityQueueNode obj com o
     * PriorityQueueNode atual
     */
    @Override
    public int compareTo(PriorityQueueNode obj) {
        int result;
        PriorityQueueNode<T> temp = obj;
        if (priority > temp.getPriority()) {
            result = 1;
        } else if (priority < temp.getPriority()) {
            result = -1;
        } else if (order > temp.getOrder()) {
            result = 1;
        } else {
            result = -1;
        }
        return result;

    }

}
