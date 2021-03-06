/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NotFindException;
import Structs.LinkedList;

import java.util.Iterator;

/**
 *
 * @author renat
 */
public class Node<T> implements Comparable<Node>{

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
    
    public void addShortestPath(Node node){
        shortestPath.add(node);
    }

    public void setShortestPath(LinkedList<Node<T>> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void removeNeighbour(Node node) throws NotFindException, EmptyCollectionException, ElementNotFoundException {
        
        Iterator<Neighbours> neighbour = getNeighbour();
        
        while (neighbour.hasNext()) {
            Neighbours next = neighbour.next();
            if(next.getNode().equals(node)){

                adjacentNodes.remove(next);             
                break;
                
            }
        }
    }
    
    public void resetNode(){
        shortestPath = new LinkedList<Node<T>>();
        distance = Double.MAX_VALUE;
    }

    @Override
    public int compareTo(Node node) {
        if(this.distance > node.getDistance()){
            return -1;
        }
        else if(this.distance < node.getDistance()){
            return 1;
        }
        else{
            return 0;
        }
    }
}
