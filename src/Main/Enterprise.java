/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Enum.TypeLocal;
import Local.Local;

import Graph.Graph;
import Local.Market;
import Local.Storage;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author renat
 */
public class Enterprise {

    private LinkedList<Sellers> sellers = new LinkedList<>();

    private LinkedList<Market> markets = new LinkedList<>();
    private LinkedList<Storage> storage = new LinkedList<>();
    private Local enterprise;

    private Graph<Local> map = new Graph<>();

    public Enterprise(Local enterprise) {

        enterprise.setType(TypeLocal.Sede);
        this.enterprise = enterprise;
        map.addNode(enterprise);
    }

    public LinkedList<Storage> getStorages(){
        return storage;
    }
    
    public void addSellers(Market[] ownedMarkets,String id,double maxWeight) {
        
        sellers.add(new Sellers(ownedMarkets,id, maxWeight, enterprise));   
    }
    
    public Sellers getSeller(String id){
        return findSeller(id);
    }
    
    private Sellers findSeller(String id){
        for (Sellers seller: sellers) {
            if(seller.getId().equals(id)){
                return seller;
            }
        }
        
        System.out.println("Nao encontrou o seller");
        return null;
    }

    public void addMarket(Market market) {

        market.setType(TypeLocal.Mercado);

        this.markets.add(market);
        
        map.addNode(market);
    }

    public void addStorage(Storage storage) {

        storage.setType(TypeLocal.Armazem);

        this.storage.add(storage);
        
        map.addNode(storage);
    }

    public Graph<Local> getMap() {
        return map;
    }

    public void addPath(String nameA, String nameB, int distance) {
        map.addPath(findByName(nameA),findByName(nameB),distance);
    }

    private Local findByName(String name) {

        if(name.equals(enterprise.getName()))
            return enterprise;
        
        for (Local local : markets) {
            if (local.getName().equals(name)) {                                         
                return local;
            }
        }
        for (Local local : storage) {
            if (local.getName().equals(name)) {
                return local;
            }
        }
        System.out.println(name);
        System.out.println("Nao encontrou o nome");
        return null;
    }

}
