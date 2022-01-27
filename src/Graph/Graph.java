/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

// Mudar
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NodesNotConectionException;
import Exceptions.NotComparableException;
import Exceptions.NotFindException;
import Local.Local;
import Structs.ArrayOrderedList;

import java.util.Iterator;
import Structs.LinkedList;


/**
 *
 * @author renat
 * @param <T> Objeto a ser guardado dentro dos Nodes
 */
public class Graph<T> {
    
    private LinkedList<Node<T>> nodes = new LinkedList<>();

    /**
     * Remover um objeto do grapho
     *
     * @param key objeto que quer remover
     * @throws NotFindException
     */
    public void remove(T key) throws NotFindException, EmptyCollectionException, ElementNotFoundException {
        
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
    
    public LinkedList<Node<T>> getNodes() {
        return nodes;
    }

    /**
     * Adiciona um caminho entre 2 pontos com certa distancia
     *
     * @param keyA Ponto A
     * @param keyB Ponto B
     * @param distance Distancia entre os 2 pontos
     * @throws NotFindException
     */
    public void addPath(T keyA, T keyB, double distance) throws NotFindException {
        addPath(findNode(keyA), findNode(keyB), distance);
    }
    
    public void addPath(Node nodeA, Node nodeB, double distance) {
        nodeA.addNeighbour(nodeB, distance);
        nodeB.addNeighbour(nodeA, distance);
    }

    /**
     * Pegar o caminho mais curto
     *
     * @param keyA Inicio
     * @param keyB Fim
     * @return Caminho mais curto entra A e B
     * @throws Exceptions.NotFindException
     * @throws Exceptions.NodesNotConectionException Nao há conexao entre os 2
     * Nodes
     */
    public Iterator getShortPath(T keyA, T keyB) throws NotFindException, NodesNotConectionException, EmptyCollectionException, ElementNotFoundException {
        return getShortPath(findNode(keyA), findNode(keyB));
    }
    
    public Iterator getShortPath(Node start, Node finish) throws NodesNotConectionException, NotFindException, EmptyCollectionException, ElementNotFoundException {

        // Atualiza todos os nodes com informacoes da distancia ao node Start e por onde passou
        Iterator<Node<T>> finishNodes = updateShortestPathFromSource(start);
        
        while (finishNodes.hasNext()) {
            Node<T> node = finishNodes.next();
            // Procurar qual dos nodes é o de chegada
            if (finish.equals(node)) {
                
                LinkedList<T> result = new LinkedList<>();
                
                for (Node x : node.getShortestPath()) {
                    result.add((T) x.getKey());
                }
                return result.iterator();
            }
        }
        
        throw new NodesNotConectionException();
    }

    /**
     * Procurar um Node com um objeto
     *
     * @param key objeto do note a encontrar
     * @return Node
     * @throws NotFindException
     */
    public Node findNode(T key) throws NotFindException {
        
        for (Node node : nodes) {
            if (node.getKey() == key) {
                return node;
            }
        }
        
        throw new NotFindException();
    }

    /**
     * Retorna os Nodes por ordem de distancia
     *
     * @param key Dar o Node de origem
     * @return Retorna os Nodes por ordem de distancia
     * @throws NotFindException
     * @throws NotComparableException
     */
    public ArrayOrderedList<Node> getMoreClose(T key) throws NotFindException, NotComparableException, EmptyCollectionException, ElementNotFoundException {
        Iterator<Node> it = updateShortestPathFromSource(findNode(key));
        
        ArrayOrderedList<Node> arrayOrder = new ArrayOrderedList<>();
        
        while (it.hasNext()) {
            arrayOrder.add(it.next());
        }
        return arrayOrder;
    }

    /**
     * Atualizar os Nodes e colocar a informacao distancia para o source e
     * caminho percorrido dentro de cada Node
     *
     * @param source Node de inicio
     * @return Todos os nodes que tem licaçao com o node inicial
     */
    private Iterator updateShortestPathFromSource(Node source) throws NotFindException, EmptyCollectionException, ElementNotFoundException {
        
        resetNodes();
        
        source.setDistance(0);

        // Nodes já vistos
        LinkedList<Node<T>> settledNodes = new LinkedList<>();
        // Nodes por ver
        LinkedList<Node> unsettledNodes = new LinkedList<>();
        
        unsettledNodes.add(source);
        
        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            
            Iterator<Neighbours> neighbourIte = currentNode.getNeighbour();
            
            while (neighbourIte.hasNext()) {
                
                Neighbours next = neighbourIte.next();
                
                Node adjacentNode = next.getNode();
                Double edgeWeight = next.getDistance();
                
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    if (!unsettledNodes.contains(adjacentNode)) {
                        unsettledNodes.add(adjacentNode);
                    }
                }
            }
            settledNodes.add(currentNode);
        }

        // Adicionar o node no final do percurso
        for (Node node : settledNodes) {
            node.addShortestPath(node);
        }
        
        return settledNodes.iterator();
    }

    /**
     * Pegar o node com menor distancia
     *
     * @param unsettledNodes
     * @return Retorna o node com menor distancia
     */
    private Node getLowestDistanceNode(LinkedList<Node> unsettledNodes) {
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

    /**
     * Calcula a distancia minima e seta o trageto do node inicial até ao node a
     * ser avaliado
     *
     * @param evaluationNode node a ser avaliado
     * @param edgeWeigh
     * @param sourceNode node inicial
     */
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

    /**
     * Resatar a informacao dos nodes a certa do caminho mais curto Chamar esta
     * funcao antes de procurar um novo caminho
     */
    private void resetNodes() {
        for (Node node : nodes) {
            node.resetNode();
        }
    }
}
