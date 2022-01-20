/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.LinkedList;

import java.util.Iterator;

/**
 *
 * @author renat
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Graphos");
        
        Graph<String> graph = new Graph();
        
        Node<String> nodeA = new Node("A");
        Node<String> nodeB = new Node("B");
        Node<String> nodeC = new Node("C");
        Node<String> nodeD = new Node("D");
        Node<String> nodeE = new Node("E");
        Node<String> nodeF = new Node("F");
        
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        
        graph.addPath(nodeA, nodeB, 10);
        graph.addPath(nodeA, nodeC, 15);
        
        graph.addPath(nodeB, nodeD, 12);
        graph.addPath(nodeB, nodeF, 15);
        
        graph.addPath(nodeC, nodeE, 10);
        
        graph.addPath(nodeD, nodeE, 2);
        graph.addPath(nodeD, nodeF, 1);
        
        graph.addPath(nodeF, nodeE, 5);

//        // Iterator<Node<String>> resultado = graph.calculateShortestPathFromSource(nodeA);
//
//        while (resultado.hasNext()) {
//            System.out.println(resultado.next().getKey());
//        }
        graph.remove("B");
        
        Iterator<Node<String>> lista = graph.getShortPath(nodeA, nodeE);
        
        //Imprime o resultado
        while (lista.hasNext()) {
            System.out.println(lista.next().getKey());
        }
        
    }
    
}
