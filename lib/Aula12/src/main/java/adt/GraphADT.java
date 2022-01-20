/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import java.util.Iterator;

/**
 * GraphADT define a interface para uma estrutura de grafos não pesados.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public interface GraphADT<T> {

    /**
     * Adiciona um vertice ao grafo, associando o objeto com o vertice.
     *
     * @param vertex o vertice a ser adicionado ao grafico
     */
    public void addVertex(T vertex);

    /**
     * Remove apenas um vertice, do grafo, com o valor recebido como parametro
     *
     * @param vertex vertice ser removido do grafo
     */
    public void removeVertex(T vertex);

    /**
     * Insere um edge entre dois vertices do grafico
     *
     * @param vertex1, primeiro vertice
     * @param vertex2, segundo vertice
     */
    public void addEdge(T vertex1, T vertex2);

    /**
     * Remove um edge entre dois vertices do grafico
     *
     * @param vertex1, primeiro vertice
     * @param vertex2, segundo vertice
     */
    public void removeEdge(T vertex1, T vertex2);

    /**
     * Retorna um iterador de largura começando com o vértice dado.
     *
     * @param startVertex vertice inicial
     * @return um iterador de largura começando no vértice dado
     */
    public Iterator iteratorBFS(T startVertex);

    /**
     * Retorna um iterador de profundidade começando com o vértice dado.
     *
     * @param startVertex vertice inicial
     * @return um iterador de profundidade começando no vértice dado
     */
    public Iterator iteratorDFS(T startVertex);

    /**
     * Retorna um iterador que contem o caminho mais pequeno entre dois
     * vertices.
     *
     * @param startVertex vertice inicial
     * @param targetVertex vetice final
     * @return um iterador que contem o caminho mais pequeno entre dois vertices
     */
    public Iterator iteratorShortestPath(T startVertex, T targetVertex);

    /**
     * Retorna verdadeiro se o grafico estiver vazio, falso caso contrario
     *
     * @return verdadeiro se o grafico estiver vazio, falso caso contrario
     */
    public boolean isEmpty();

    /**
     * Retorna verdadeiro se o grafico é conexo verdadeiro caso nao seja
     * conexo
     *
     * @return verdadeiro se o grafico é conectado, falso caso contrario.
     */
    public boolean isConnected();

    /**
     * Retorna o numero de vertices do grafo.
     *
     * @return a representação em inteiro do numero de vertices do grafico
     */
    public int size();

    /**
     * Retorna uma string representativa da matriz de adjencias
     *
     * @return string representativa da matriz da adjencias
     */
    @Override
    public String toString();
}
