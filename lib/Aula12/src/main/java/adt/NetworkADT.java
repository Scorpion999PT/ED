/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 * NetworkADT define a interface para uma estrutura de grafos pesados.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public interface NetworkADT<T> extends GraphADT<T> {

    /**
     * Insere um edge entre dois vertices do grafico.
     *
     * @param vertex1 primeiro vertice
     * @param vertex2 segundo vertice
     * @param weight o peso
     */
    public void addEdge(T vertex1, T vertex2, double weight);

    /**
     * Retorna o peso do caminho mais curto da network.
     *
     * @param vertex1 primeiro vertice
     * @param vertex2 segundo vertice
     * @return o peso do caminho mais curto da network
     */
    public double shortestPathWeight(T vertex1, T vertex2);
}
