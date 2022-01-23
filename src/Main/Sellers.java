/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Enum.TypeLocal;
import Graph.Graph;
import Graph.Node;
import Local.Local;
import Local.Market;
import Local.Storage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author renat
 */
public class Sellers {

    // Mercados a visitar pelos vendedores
    private Market[] ownedMarkets;

    private double maxWeight;
    private double currentStorage;
    private String id;

    private LinkedList<Local> currentRoot;

    private Local posicaoAtual;

    private LinkedList<Local> mercadorPorVisitar = new LinkedList<>();

    public Sellers(Market[] ownedMarkets, String id, double maxWeight, Local enterprise) {
        this.ownedMarkets = ownedMarkets;
        this.maxWeight = maxWeight;
        this.id = id;

        // O Seller começa na empresa o precurso
        this.posicaoAtual = enterprise;
    }

    public Sellers(String id, double maxWeight, Local enterprise) {
        this.maxWeight = maxWeight;
        this.id = id;

        // O Seller começa na empresa o precurso
        this.posicaoAtual = enterprise;
    }

    public String getId() {
        return id;
    }

    public Market[] getOwnedMarkets() {
        return ownedMarkets;
    }

    public void setOwnedMarkets(Market[] ownedMarkets) {
        this.ownedMarkets = ownedMarkets;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Iterator getCurrentRoot() {
        return currentRoot.iterator();
    }

    public void walkAllPath(Graph<Local> map) {

        currentRoot = new LinkedList<>();
        
        for (Market mercado : ownedMarkets) {
            //Se a mercadoria acabar ou acabar o que o mercado está a precisar
            if(currentStorage < mercado.getStorageNeed()){
                addCurrentRoot(goToStorage(map));
                currentStorage = ((Storage)currentRoot.getLast()).retirarArmazem(maxWeight);
            }
            
            addCurrentRoot(goToPosition(mercado, map));
        }
    }
    
    // Adicionar iterator a current root
    private void addCurrentRoot(Iterator<Local> it){

        while (it.hasNext()) {
            currentRoot.add(it.next());
        }
    }
    
    // Retorna caminho
    public Iterator goToPosition(Local destino, Graph map) {

        Iterator it = map.getShortPath(posicaoAtual, destino);
        
        posicaoAtual = destino;
        
        return it;
    }
    
    // retorna o caminho usado
    private Iterator goToStorage(Graph<Local> map){
        
        Node<Local> node = findStorage(map);
        
        posicaoAtual =  node.getKey();
        
        return node.getShortestPath().iterator();
        
        
    }

    // Retorna o storage mais proximo
    private Node<Local> findStorage(Graph<Local> map) {

        // Procurar o mercado mais proximo
        Iterator<Node<Local>> nodes = map.getMoreClose(posicaoAtual);
        
        double distance = Double.MAX_VALUE;
        
        Node node = null;

        while (nodes.hasNext()) {
            Node<Local> next = nodes.next();
            if (next.getKey().getType() == TypeLocal.Armazem) {
                
                if(distance < next.getDistance()){
                    
                    distance = next.getDistance();
                    
                    node = next;
                }
            }

        }

        return node;
    }

}
