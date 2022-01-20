/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import adt.GraphADT;
import arrayList.ArrayUnorderedList;
import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import java.util.Iterator;
import queue.LinkedQueue;
import stack.LinkedStack;

/**
 * GraphMatrix representa a implementação de um grafo não pesado que tem por
 * base uma matriz de adjacencias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class GraphMatrix<T> extends Graph<T> implements GraphADT<T> {

    /**
     * Matriz de adjacencias.
     */
    protected boolean[][] adjMatrix;

    /**
     * Cria um grafo vazio, usando a DEFAULT_CAPACITY, para definir o tamanho do
     * grafo.
     */
    public GraphMatrix() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Cria um grafo vazio, onde o tamanho deste é definifo pelo utilizador.
     *
     * @param initialCapacity, representa o tamanho do Grafo
     */
    public GraphMatrix(int initialCapacity) {
        this.numVertices = 0;
        this.adjMatrix = new boolean[initialCapacity][initialCapacity];
        this.vertices = (T[]) (new Object[initialCapacity]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandMatrix();
            expandArray();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }

    /**
     * Expande a capacidade da matriz de adjacencias.
     */
    protected void expandMatrix() {
        int tam = this.size();
        boolean[][] newMatrix = new boolean[tam + DEFAULT_CAPACITY][tam + DEFAULT_CAPACITY];
        for (int x = 0; x < tam; x++) {
            for (int j = 0; j < tam; j++) {
                newMatrix[x][j] = this.adjMatrix[x][j];
            }
        }
        this.adjMatrix = newMatrix;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Insere um edge entre dois vertices do grafico, utilizando as suas
     * posições.
     *
     * @param index1 posição de um dos vertices que se deseja ligar o edge
     * @param index2 posição do outro vertices que se deseja ligar o edge
     */
    protected void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = true;
            this.adjMatrix[index2][index1] = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeVertex(T vertex) {
        int pos = this.getIndex(vertex);

        if (this.isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.GRAFO);
        }

        if (!this.indexIsValid(pos)) {
            throw new ElementNotFoundException(ElementNotFoundException.NOT_EXIST);
        }

        backArray(pos);
        backMatrix(pos);
        numVertices--;
    }

    /**
     * Ajusta a matriz de adjacencias depois da remoção de um vertice.
     *
     * @param toRemove, posição que foi removida.
     */
    private void backMatrix(int toRemove) {
        for (int i = toRemove; i < numVertices; i++) {
            for (int j = 0; j <= numVertices; j++) {
                adjMatrix[i][j] = adjMatrix[i + 1][j];
            }
        }

        for (int i = toRemove; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[j][i] = adjMatrix[j][i + 1];
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {

        int index1 = this.getIndex(vertex1);
        int index2 = this.getIndex(vertex2);
        
        if (this.isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.GRAFO);
        }

        if (!indexIsValid(index1) || !indexIsValid(index2)) {
            throw new ElementNotFoundException(ElementNotFoundException.EDGE_NOT_EXIST);
        }

        this.adjMatrix[index1][index2] = false;
        this.adjMatrix[index2][index1] = false;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator iteratorBFS(T startVertex) {
        return this.iteratorBFS(this.getIndex(startVertex));
    }

    /**
     * Retorna um iterador de largura começando com a posição do vértice dado.
     *
     * @param startIndex posição do vertice inicial
     * @return um iterador de largura começando no vértice dado
     */
    protected Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList();
        if (!this.indexIsValid(startIndex)) {
            return resultList.iterator();
        } else {
            boolean[] visited = new boolean[this.numVertices];

            int i;
            for (i = 0; i < this.numVertices; ++i) {
                visited[i] = false;
            }

            traversalQueue.enqueue(new Integer(startIndex));
            visited[startIndex] = true;

            while (!traversalQueue.isEmpty()) {
                x = (Integer) traversalQueue.dequeue();
                resultList.addToRear(this.vertices[x]);

                for (i = 0; i < this.numVertices; ++i) {
                    if (this.adjMatrix[x][i] && !visited[i]) {
                        traversalQueue.enqueue(new Integer(i));
                        visited[i] = true;
                    }
                }
            }

            return resultList.iterator();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator iteratorDFS(T startVertex) {
        return this.iteratorDFS(this.getIndex(startVertex));
    }

    /**
     * Retorna um iterador de profundidade começando com a posição do vértice
     * dado.
     *
     * @param startIndex posição do vertice inicial
     * @return um iterador de profundidade começando no vértice dado
     */
    protected Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        int indexStart = this.getIndex(startVertex);
        int indexTarget = this.getIndex(targetVertex);

        if (!indexIsValid(indexStart) || !indexIsValid(indexTarget)
                || (startVertex == targetVertex)) {
            return resultList.iterator();
        }

        Iterator<T> it = iteratorShortestPath(indexStart, indexTarget);
        while (it.hasNext()) {
            resultList.addToRear(vertices[((Integer) it.next())]);
        }

        return resultList.iterator();
    }

    /**
     * Retorna um iterador que contem o caminho mais pequeno entre as posições
     * de dois vertices.
     *
     * @param startVertex posição do vertice inicial
     * @param targetVertex posição do vertice final
     * @return um iterador que contem o caminho mais pequeno entre dois vertices
     */
    protected Iterator<T> iteratorShortestPath(int startVertex, int targetVertex) {
        Integer current = startVertex;
        int[] path = new int[numVertices];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList();

        boolean[] visited = new boolean[this.numVertices];

        int i;
        for (i = 0; i < this.numVertices; ++i) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(startVertex));
        visited[startVertex] = true;
        path[startVertex] = -1;

        while (!traversalQueue.isEmpty() && (current != targetVertex)) {
            current = (Integer) traversalQueue.dequeue();

            for (i = 0; i < this.numVertices; ++i) {
                if (this.adjMatrix[current][i] && !visited[i]) {
                    path[i] = current;
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        if (current != targetVertex) // no path must have been found
        {
            return resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        current = targetVertex;
        stack.push(new Integer(current));
        do {
            current = path[current];
            stack.push(new Integer(current));
        } while (current != startVertex);

        while (!stack.isEmpty()) {
            resultList.addToRear(((Integer) stack.pop()));
        }

        return resultList.iterator();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConnected() {
        if (isEmpty()) {
            return false;
        }

        Iterator<T> iterator = this.iteratorDFS(0);
        int count = 0;

        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }

        return (count == numVertices);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return (this.numVertices == 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.numVertices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        
        String s = "\n\nGrafo";
        s += "\n----------------";
        if (this.isEmpty()) {
            s += EmptyCollectionException.GRAFO;
            return s;
        }

        s += "\n Value\tIndex\t";

        for (int i = 0; i < numVertices; i++) {
            s += i + " ";
        }
        s += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            s += vertices[i].toString();
            s += "\t" + i + "\t";
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j]) {
                    s += "1 ";
                } else {
                    s += "0 ";
                }

                if (j > 9) {
                    s += " ";
                }

            }
            s += "\n";
        }

        s += "\n------------------------------------------------\n";
        return s;
    }

}
