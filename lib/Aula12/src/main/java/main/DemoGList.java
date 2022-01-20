/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import graph.GraphList;
import java.util.Iterator;

/**
 *
 * @author Mariana Ribeiro
 */
public class DemoGList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GraphList graph = new GraphList();
        System.out.println("Is empty: "+graph.isEmpty());
        System.out.println("Is connected: " + graph.isConnected());

        
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addVertex(40);

        //graph.addEdge(10, 20);
        graph.addEdge(20, 30);
        graph.addEdge(30, 40);
        graph.addEdge(30, 10);

        System.out.println("Antes da Remoção \n " + graph.toString());
        //graph.removeVertex(20);
        System.out.println("Depois da Remoçao \n " + graph.toString());

        System.out.println("Is connected: " + graph.isConnected());
        Iterator<Integer> it = graph.iteratorShortestPath(20, 30);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
