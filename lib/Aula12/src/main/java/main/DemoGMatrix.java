/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import graph.GraphMatrix;
import java.util.Iterator;

/**
 *
 * @author Mariana Ribeiro
 */
public class DemoGMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*GraphMatrix<String> graph = new GraphMatrix();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");
        graph.addVertex("K");

        graph.addEdge("A", "B");
        graph.addEdge("A", "D");

        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("B", "C");

        graph.addEdge("C", "G");
        graph.addEdge("C", "I");

        graph.addEdge("D", "E");
        graph.addEdge("D", "F");
        graph.addEdge("D", "G");

        graph.addEdge("F", "H");

        graph.addEdge("G", "H");
        graph.addEdge("G", "J");

        graph.addEdge("H", "K");

        graph.addEdge("I", "J");
        
        graph.addEdge("J","K");

        Iterator<Integer> it = graph.iteratorDFS("A");
        while (it.hasNext()) {
            System.out.println(it.next());
        }*/

        GraphMatrix<Integer> graph = new GraphMatrix();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);
        graph.addVertex(9);
        graph.addVertex(10);
        graph.addVertex(11);
        graph.addVertex(12);

        graph.addEdge(0, 1);

        graph.addEdge(1, 3);
        graph.addEdge(1, 9);
        graph.addEdge(1, 2);

        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 8);
        graph.addEdge(7, 11);
        graph.addEdge(3, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 11);
        graph.addEdge(7, 8);
        graph.addEdge(8, 12);
        
        
        graph.addEdge(9, 10);
        graph.addEdge(10, 11);
        graph.addEdge(11, 12);

        graph.addEdge(2, 12);

        Iterator<Integer> it = graph.iteratorBFS(0);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
