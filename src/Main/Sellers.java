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
    private String id;
    
    private Iterator<Local> currentRoot;

    private Local posicaoAtual;
    
    public Sellers(Market[] ownedMarkets,String id, double maxWeight, Local enterprise) {
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
    
    public String getId(){
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
    
    public Iterator getCurrentRoot(){
        return currentRoot;
    }
    
    public void updateCurrentRoot(Graph<Local> map){
        
        LinkedList<Local> mercadorPorVisitar = new LinkedList<>();
        for (Market mercado:ownedMarkets) {
            mercadorPorVisitar.add((Local)mercado);
        }
        
        Local destino = mercadorPorVisitar.poll();
        currentRoot = map.getShortPath(posicaoAtual, destino);
        
        posicaoAtual = destino;
    }
    
    private Local findStorage(Graph<Local> map){
        
        // Procurar o mercado mais proximo
        Iterator<Node<Local>> nodes = map.getMoreClose(posicaoAtual);
        
        while (nodes.hasNext()) {
            Node<Local> next = nodes.next();
            if(next.getKey().getType() == TypeLocal.Armazem){
                return next.getKey();
            }
            
        }
        
        return null;
    }
    
    
}
