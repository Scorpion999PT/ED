/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author renat
 */
public class Neighbours {  
    Node node;
    double distance;

    public Neighbours(Node node, double distance) {
        this.node = node;
        this.distance = distance;
    }  
    
    public Node getNode(){
        return node;
    }
    
    public double getDistance(){
        return distance;
    }
    
}
