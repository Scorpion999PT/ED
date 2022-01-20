///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package teste;
//
//import java.util.Iterator;
//
///**
// * Graph represents an adjacency matrix implementation of a graph.
// *
// */
//public class Graph<T> implements GraphADT<T> {
//
//    protected final int DEFAULT_CAPACITY = 10;
//    protected int numVertices; // number of vertices in the graph
//    protected boolean[][] adjMatrix; // adjacency matrix
//    protected T[] vertices; // values of vertices
//
//    /**
//     * Creates an empty graph.
//     */
//    public Graph() {
//        numVertices = 0;
//        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
//        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
//    }
//
//    /**
//     * Inserts an edge between two vertices of the graph.
//     *
//     * @param vertex1 the first vertex
//     * @param vertex2 the second vertex
//     */
//    public void addEdge(T vertex1, T vertex2) {
//        addEdge(getIndex(vertex1), getIndex(vertex2));
//    }
//
//    /**
//     * Inserts an edge between two vertices of the graph.
//     *
//     * @param index1 the first index
//     * @param index2 the second index
//     */
//    public void addEdge(int index1, int index2) {
//        if (indexIsValid(index1) && indexIsValid(index2)) {
//            adjMatrix[index1][index2] = true;
//            adjMatrix[index2][index1] = true;
//        }
//    }
//
//    /**
//     * Adds a vertex to the graph, expanding the capacity of the graph if
//     * necessary. It also associates an object with the vertex.
//     *
//     * @param vertex the vertex to add to the graph
//     */
//    public void addVertex(T vertex) {
//        if (numVertices == vertices.length) {
//            expandCapacity();
//        }
//        vertices[numVertices] = vertex;
//        for (int i = 0; i <= numVertices; i++) {
//            adjMatrix[numVertices][i] = false;
//            adjMatrix[i][numVertices] = false;
//        }
//        numVertices++;
//    }
//
//    @Override
//    public void removeVertex(T vertex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void removeEdge(T vertex1, T vertex2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Iterator iteratorBFS(T startVertex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Iterator iteratorDFS(T startVertex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean isEmpty() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean isConnected() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public int size() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
