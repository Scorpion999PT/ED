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
import exception.NullPointerException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import node.LinearNode;
import queue.LinkedQueue;
import simpleList.LinkedList;
import stack.LinkedStack;

/**
 * GraphList representa a implementação de um grafo não pesado que tem por base
 * uma lista de adjacencias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class GraphList<T> extends Graph<T> implements GraphADT<T> {

    /**
     * Lista de adjacencias.
     */
    protected LinkedList<Integer>[] adjList;

    /**
     * Cria um grafo vazio, usando a DEFAULT_CAPACITY, para definir o tamanho do
     * grafo.
     */
    public GraphList() {
        this.numVertices = 0;
        this.adjList = new LinkedList[DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Cria um grafo vazio, onde o tamanho deste é definifo pelo utilizador.
     *
     * @param initialCapacity, representa o tamanho do Grafo
     */
    public GraphList(int initialCapacity) {
        this.numVertices = 0;
        this.adjList = new LinkedList[initialCapacity];
        this.vertices = (T[]) (new Object[initialCapacity]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandList();
            expandArray();
        }

        vertices[numVertices] = vertex;
        adjList[numVertices] = new LinkedList<>();;
        numVertices++;
    }

    /**
     * Expande a capacidade da lista de adjacencias.
     */
    protected void expandList() {
        int tam = this.size();
        LinkedList<Integer>[] newAdjList = new LinkedList[tam + DEFAULT_CAPACITY];
        for (int i = 0; i < tam; i++) {
            newAdjList[i] = this.adjList[i];

        }
        this.adjList = newAdjList;

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
            this.adjList[index1].add(index2);
            this.adjList[index2].add(index1);
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
        backList(pos);
        numVertices--;
    }

    /**
     * Ajusta a lista de adjacencias depois da remoção de um vertice.
     *
     * @param toRemove, posição que foi removida.
     */
    private void backList(int toRemove) {
        int vertex = 0;
        while (vertex < this.numVertices) {
            LinearNode<Integer> temp = adjList[vertex].getHead();
            for (int i = 0; i < adjList[vertex].size(); i++) {
                if (temp.getElement().equals(toRemove)) {
                    try {
                        adjList[vertex].remove(toRemove);
                    } catch (EmptyCollectionException | NullPointerException ex) {
                        Logger.getLogger(GraphList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                int aux = temp.getElement();
                if (aux > toRemove) {
                    temp.setElement(aux - 1);
                }
                temp = temp.getNext();
            }
            vertex++;
        }

        for (int i = toRemove; i < numVertices; i++) {
            this.adjList[i] = this.adjList[i + 1];
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
        try {
            this.adjList[index1].remove(index2);
            this.adjList[index2].remove(index1);
        } catch (EmptyCollectionException | NullPointerException ex) {
            Logger.getLogger(GraphList.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private Iterator<T> iteratorBFS(int startIndex) {

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        Integer x = startIndex;
        traversalQueue.enqueue(x);
        visited[x] = true;

        while (!traversalQueue.isEmpty()) {
            x = (Integer) traversalQueue.dequeue();
            resultList.addToRear(vertices[x]);
            for (int i = 0; i < numVertices; i++) {
                if ((adjList[x].search(i) != null) && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
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
    public Iterator<T> iteratorDFS(int startIndex) {
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
                if ((adjList[x].search(i) != null) && !visited[i]) {
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
    public Iterator iteratorShortestPath(T startVertex, T targetVertex
    ) {
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
        int[] path = new int[numVertices];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        Integer x = startVertex;
        traversalQueue.enqueue(x);
        visited[x] = true;
        path[startVertex] = -1;

        while (!traversalQueue.isEmpty()) {
            x = (Integer) traversalQueue.dequeue();
            for (int i = 0; i < numVertices; i++) {
                if ((adjList[x].search(i) != null) && !visited[i]) {
                    path[i] = x;
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        if (x != targetVertex) // no path must have been found
        {
            return resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        x = targetVertex;
        stack.push(new Integer(x));
        do {
            x = path[x];
            stack.push(new Integer(x));
        } while (x != startVertex);

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
        int vertex = 0;
        int tam = this.size();

        String s = "\n -------------Grafo------------ \n";

        if (this.isEmpty()) {
            s += EmptyCollectionException.GRAFO;
            return s;
        }

        while (vertex < tam) {
            s += "[" + vertex + "]  Vertex: " + vertices[vertex];
            LinearNode<Integer> temp = adjList[vertex].getHead();
            for (int i = 0; i < adjList[vertex].size(); i++) {
                int pos = temp.getElement();
                s += "  ==> " + vertices[pos];
                temp = temp.getNext();
            }
            s += "\n";
            vertex++;
        }
        return s;
    }

}
