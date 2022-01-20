/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Iterator;
import graph.NetworkMatrix;

/**
 *
 * @author Mariana Ribeiro
 */
public class DemoNMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NetworkMatrix net = new NetworkMatrix();
        System.out.println("Is empty: " + net.isEmpty());

        net.addVertex(10);
        net.addVertex(20);
        net.addVertex(30);
        net.addVertex(40);

        net.addEdge(10, 20, 5.9);
        net.addEdge(20, 30, 6);
        net.addEdge(30, 40, 7.1);
        net.addEdge(30, 10, 9);

        System.out.println(net.toString());
        //graph.removeVertex(20);
        // graph.addEdge(1,4);
        Iterator<Integer> it = net.iteratorShortestPath(10, 40);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("Weight: "+net.shortestPathWeight(10, 40));

    }

}
