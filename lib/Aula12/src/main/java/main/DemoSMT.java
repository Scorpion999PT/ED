/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import graph.NetworkMatrix;

/**
 *
 * @author Mariana Ribeiro
 */
public class DemoSMT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NetworkMatrix<Integer> net = new NetworkMatrix();

        net.addVertex(0);
        net.addVertex(1);
        net.addVertex(2);
        net.addVertex(3);
        net.addVertex(4);
        net.addVertex(5);
        net.addVertex(6);
        net.addVertex(7);
        net.addVertex(8);
        net.addVertex(9);

        net.addEdge(0, 5, 2);
        net.addEdge(0, 1, 2);

        net.addEdge(1, 3, 2);
        net.addEdge(1, 2, 1);

        net.addEdge(2, 3, 2);
        net.addEdge(2, 8, 3);
        
        net.addEdge(3, 4, 3);
        net.addEdge(3, 8, 2);
        
        net.addEdge(4, 5, 2);
        net.addEdge(4, 6, 3);
        net.addEdge(4, 7, 2);
        net.addEdge(4, 8, 2);
        
        net.addEdge(5, 6, 3);
        
        net.addEdge(6, 7, 3);
        
        net.addEdge(7, 9, 2);
        net.addEdge(7, 8, 3);
        
        net.addEdge(8, 9, 3);

        NetworkMatrix<Integer> mst = net.mstNetwork();

        System.out.println(mst.toString());

        /*NetworkMatrix<String> net = new NetworkMatrix();

        net.addVertex("A");
        net.addVertex("B");
        net.addVertex("C");
        net.addVertex("D");
        net.addVertex("E");
        net.addVertex("F");
        net.addVertex("G");
        net.addVertex("H");

        net.addEdge("A", "B", 3);
        net.addEdge("A", "F", 2);

        net.addEdge("B", "C", 1);
        net.addEdge("B", "D", 2);

        net.addEdge("C", "E", 8);
        net.addEdge("C", "D", 1);

        net.addEdge("D", "G", 2);

        net.addEdge("E", "H", 1);

        net.addEdge("F", "G", 3);

        net.addEdge("G", "H", 2);

        NetworkMatrix<String> mst = net.mstNetwork();

        System.out.println(mst.toString());*/
    }

}
