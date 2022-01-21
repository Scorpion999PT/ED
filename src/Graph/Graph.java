/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

// Mudar
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author renat
 * @param <T>
 */
public class Graph<T> {

    private LinkedList<Node<T>> nodes = new LinkedList<>();
    
    public void remove(T key){
        
        Node node = findNode(key);
        
        Iterator<Neighbours> it = node.getNeighbour();
        
        while (it.hasNext()) {
            Neighbours next = it.next();
            next.getNode().removeNeighbour(node);
            
        }
        nodes.remove(node);
    }

    public void addNode(T key) {
        nodes.add(new Node<>(key));
    }
    
    public void addNode(Node node) {
        nodes.add(node);
    }
    
    public LinkedList<Node<T>> getNodes(){
        return nodes;
    }
    
    public void addPath(T keyA,T keyB,double distance){
        addPath(findNode(keyA), findNode(keyB), distance);
    }
    
    public void addPath(Node nodeA,Node nodeB,double distance){
        nodeA.addNeighbour(nodeB, distance);
        nodeB.addNeighbour(nodeA, distance);
    }
    
    public Iterator getShortPath(T keyA,T keyB){
        return getShortPath(findNode(keyA), findNode(keyB));
    }
    
    public Iterator getShortPath(Node start,Node finish){
        
        Iterator<Node<T>> finishNodes = calculateShortestPathFromSource(start);
        
        while (finishNodes.hasNext()) {  
            Node<T> node = finishNodes.next();
            if(finish == node){
                
                LinkedList<T> result = new LinkedList<>();
 
                
                for(Node x : node.getShortestPath()){
                    result.add((T)x.getKey());
                }
                return result.iterator();
            }
        }
        
        System.out.println("Teste");
        
        return null;
    }
    
    // exceçao se nao encotrar
    public Node findNode(T key){
            
        for(Node node:nodes){
            if(node.getKey() == key){
                return node;
            }
        }
        
        System.out.println("Node nao encontrado");
        
        return null;
    }
    
    // Colocar por ordem do mais proximo
    public Iterator getMoreClose(T key){
        Iterator<Node> moreClose = calculateShortestPathFromSource(findNode(key));
        return moreClose;
    }

    private Iterator calculateShortestPathFromSource(Node source) {
        source.setDistance(0);

        LinkedList<Node<T>> settledNodes = new LinkedList<>();
        LinkedList<Node> unsettledNodes = new LinkedList<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty() ) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            Iterator<Neighbours> neighbourIte = currentNode.getNeighbour();

            while (neighbourIte.hasNext()) {
                
                Neighbours next = neighbourIte.next();

                Node adjacentNode = next.getNode();
                Double edgeWeight = next.getDistance();
                
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        
        return settledNodes.iterator();
    }

    private Node getLowestDistanceNode(LinkedList< Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        double lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void calculateMinimumDistance(Node evaluationNode,
            double edgeWeigh, Node sourceNode) {
        double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
            
        }
    }
}
