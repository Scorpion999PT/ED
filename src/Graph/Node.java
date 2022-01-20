/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.LinkedList;
import java.util.List;

import java.util.Iterator;

/**
 *
 * @author renat
 */
public class Node<T> {

    private T object;

    private LinkedList<Node<T>> shortestPath = new LinkedList<>();

    private double distance = Double.MAX_VALUE;

    private LinkedList<Neighbours> adjacentNodes = new LinkedList<>();

    public Node(T object) {
        this.object = object;
    }

    public void addNeighbour(Node<T> node, double distance) {
        adjacentNodes.add(new Neighbours(node, distance));
    }
    
    public Iterator getNeighbour() {
        return adjacentNodes.iterator();
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public T getKey() {
        return object;
    }

    public LinkedList<Node<T>> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node<T>> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void removeNeighbour(Node node) {
        
        Iterator<Neighbours> neighbour = getNeighbour();
        
        while (neighbour.hasNext()) {
            Neighbours next = neighbour.next();
            if(next.getNode().equals(node)){

                adjacentNodes.remove(next);             
                break;
                
            }
        }
    }
}
