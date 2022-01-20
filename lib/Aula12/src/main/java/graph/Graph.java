/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 * Graph representa uma implementação abstrata de um grafo. Contem o metodos e
 * variaveis comuns a todos os grafos
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public abstract class Graph<T> {

    /**
     * Constante int, que representa a capacidade inicial, por defeito.
     */
    protected final int DEFAULT_CAPACITY = 6;

    /**
     * Numero de vertices no grafico.
     */
    protected int numVertices;

    /**
     * Valores dos vertices.
     */
    protected T[] vertices;

    /**
     * Retorna a posição de um vertice no array onde se encontram guardados.
     *
     * @param vertex, vertice
     * @return a posição onde se encontra o vertice.
     */
    protected int getIndex(T vertex) {
        int send = -1;
        for (int i = 0; i < this.vertices.length; i++) {
            if (vertex.equals(vertices[i])) {
                send = i;
                break;
            }
        }
        return send;
    }

    /**
     * Verifica se a posição do vertice é valida
     *
     * @param index, posição de um vertice
     * @return verdadeiro caso a posição seja valida, falso caso o contrario.
     */
    protected boolean indexIsValid(int index) {
        return ((index > -1) && (index < this.numVertices));
    }

    /**
     * Expande a capacidade do array que representa os vertices grafo.
     */
    protected void expandArray() {
        int tam=this.numVertices;
        T[] newVertex = (T[]) (new Object[tam + DEFAULT_CAPACITY]);
        for (int i = 0; i < tam; i++) {
            newVertex[i] = this.vertices[i];

        }
        this.vertices = newVertex;
    }

    /**
     * Ajusta o array de vertices depois da remoção de um vertice.
     *
     * @param toRemove, posição que foi removida.
     */
    protected void backArray(int toRemove) {
        for (int i = toRemove; i < numVertices; i++) {
            this.vertices[i] = this.vertices[i + 1];
        }
    }

}
