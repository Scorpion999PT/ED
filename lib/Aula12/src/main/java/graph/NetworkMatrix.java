/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import adt.NetworkADT;
import arrayList.ArrayUnorderedList;
import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import heaps.ArrayHeap;
import java.util.Iterator;
import queue.LinkedQueue;
import stack.LinkedStack;

/**
 *
 * NetworkMatrixrepresenta a implementação de um grafo com pesos que tem por
 * base uma matriz de adjacencias.
 *
 * @author Mariana Ribeiro
 * @param <T>
 */
public class NetworkMatrix<T> extends Graph<T> implements NetworkADT<T> {

    /**
     * Matriz de adjacencias.
     */
    protected double[][] adjMatrix;

    /**
     * Cria um grafo vazio, usando a DEFAULT_CAPACITY, para definir o tamanho do
     * grafo.
     */
    public NetworkMatrix() {
        this.numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Cria um grafo vazio, onde o tamanho deste é definifo pelo utilizador.
     *
     * @param initialCapacity, representa o tamanho do Grafo
     */
    public NetworkMatrix(int initialCapacity) {
        this.numVertices = 0;
        this.adjMatrix = new double[initialCapacity][initialCapacity];
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
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    /**
     * Expande a capacidade da matriz de adjacencias.
     */
    protected void expandMatrix() {
        int tam = this.size();
        double[][] newMatrix = new double[tam + DEFAULT_CAPACITY][tam + DEFAULT_CAPACITY];
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
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), Double.POSITIVE_INFINITY);
    }

    /**
     * Insere um edge entre dois vertices do grafico, utilizando as suas
     * posições.
     *
     * @param index1 posição de um dos vertices que se deseja ligar o edge
     * @param index2 posição do outro vertices que se deseja ligar o edge
     */
    protected void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = weight;
            this.adjMatrix[index2][index1] = weight;
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

        this.adjMatrix[index1][index2] = Double.POSITIVE_INFINITY;
        this.adjMatrix[index2][index1] = Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a minimum spanning tree of the network.
     *
     * @return a minimum spanning tree of the network
     */
    public NetworkMatrix mstNetwork() {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        ArrayHeap<Double> minHeap = new ArrayHeap<Double>();
        NetworkMatrix<T> resultGraph = new NetworkMatrix<T>();
        if (isEmpty() || !isConnected()) {
            return resultGraph;
        }
        resultGraph.adjMatrix = new double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                resultGraph.adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        resultGraph.vertices = (T[]) (new Object[numVertices]);

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;
        /**
         * Add all edges, which are adjacent to the starting vertex, to the heap
         */
        for (int i = 0; i < numVertices; i++) {
            minHeap.addElement(new Double(adjMatrix[0][i]));
        }
        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {
            /**
             * Get the edge with the smallest weight that has exactly one vertex
             * already in the resultGraph
             */
            do {
                weight = (minHeap.removeMin()).doubleValue();
                edge = getEdgeWithWeightOf(weight, visited);
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));

            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }
            /**
             * Add the new edge and vertex to the resultGraph
             */
            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;
            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
            resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];
            /**
             * Add all edges, that are adjacent to the newly added vertex, to
             * the heap
             */
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && (this.adjMatrix[i][index]
                        < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(new Double(adjMatrix[index][i]));
                }
            }
        }
        return resultGraph;
    }

    /**
     * Returns the edge with the given weight. Exactly one vertex of the edge
     * must have been visited.
     */
    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if ((adjMatrix[i][j] == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }

        /**
         * Will only get to here if a valid edge is not found
         */
        edge[0] = -1;
        edge[1] = -1;
        return edge;
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
                    if (this.adjMatrix[x][i] != -1 && !visited[i]) {
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
                if (adjMatrix[x][i] < Double.POSITIVE_INFINITY && !visited[i]) {
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
            resultList.addToRear(vertices[((Integer) it.next()).intValue()]);
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
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        ArrayHeap<Double> traversalMinHeap = new ArrayHeap<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = -1;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        pathWeight[startVertex] = 0;
        predecessor[startVertex] = -1;
        visited[startVertex] = true;
        weight = 0;

        /**
         * Update the pathWeight for each vertex except the startVertex. Notice
         * that all vertices not adjacent to the startVertex will have a
         * pathWeight of infinity for now.
         */
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startVertex]
                        + adjMatrix[startVertex][i];
                predecessor[i] = startVertex;
                traversalMinHeap.addElement(new Double(pathWeight[i]));
            }
        }

        do {
            weight = (traversalMinHeap.removeMin()).doubleValue();
            traversalMinHeap.removeAll();
            if (weight == Double.POSITIVE_INFINITY) // no possible path
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight,
                        weight);
                visited[index] = true;
            }

            /**
             * Update the pathWeight for each vertex that has has not been
             * visited and is adjacent to the last vertex that was visited.
             * Also, add each unvisited vertex to the heap.
             */
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(new Double(pathWeight[i]));
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetVertex]);

        index = targetVertex;
        stack.push(new Integer(index));
        do {
            index = predecessor[index];
            stack.push(new Integer(index));
        } while (index != startVertex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    /**
     * Retorna a posição do vertice que possui o peso enviado por parametro no
     * entanto que ainda não tenha sido visitado
     *
     * @param visited, array que representa se os vertices ja foram ou nao
     * visitados
     * @param pathWeight, array que representa o caminho
     * @param weight, peso
     * @return
     */
    private int getIndexOfAdjVertexWithWeightOf(boolean[] visited,
            double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if ((adjMatrix[i][j] != -1)
                            && visited[j]) {
                        return i;
                    }
                }
            }
        }

        return -1;  // should never get to here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        double result = 0;
        int indexVertex1 = this.getIndex(vertex1);
        int indexVertex2 = this.getIndex(vertex2);

        if (!indexIsValid(indexVertex1) || !indexIsValid(indexVertex2)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<T> it = iteratorShortestPath(indexVertex1,
                indexVertex2);

        if (it.hasNext()) {
            index1 = ((Integer) it.next());
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = ((Integer) it.next());
            result += adjMatrix[index1][index2];
            index1 = index2;
        }

        return result;
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

        s += "\nValue\tIndex\t";

        for (int i = 0; i < numVertices; i++) {
            s += "      |" + i + "|   ";
        }
        s += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            s += vertices[i].toString();
            s += "\t" + i + "\t";
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    String resultado = String.format("|%.2f|", adjMatrix[i][j]);
                    s += "   " + resultado + "    ";
                } else {
                    s += " |" + adjMatrix[i][j] + "| ";
                }

            }
            s += "\n";
        }

        /**
         * Print the weights of the edges
         */
        s += "\n\nWeights of Edges";
        s += "\n----------------\n";
        s += "Value\t\tWeight\n\n";

        for (int i = 0;
                i < numVertices;
                i++) {
            for (int j = numVertices - 1; j > i; j--) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    s += vertices[i] + " to " + vertices[j] + "\t\t";
                    s += adjMatrix[i][j] + "\n";
                }
            }
        }

        s += "\n";
        return s;
    }
}
