/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Enum.TypeLocal;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NodesNotConectionException;
import Exceptions.NotComparableException;
import Exceptions.NotFindException;
import Local.Local;

import Graph.Graph;
import Local.Market;
import Local.Storage;
import java.util.Iterator;
import Structs.LinkedList;

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

    public Enterprise() {

    }

    public void setEnterprise(Local enterprise) {

        enterprise.setType(TypeLocal.Sede);
        this.enterprise = enterprise;
        map.addNode(enterprise);
    }

    public LinkedList<Storage> getStorages() {
        return storage;
    }

    public Market getMarket(String name) throws NotFindException {

        for (Market market : markets) {
            if (market.getName().equals(name)) {
                return market;
            }
        }

        throw new NotFindException();
    }

    public void addSellers(Sellers seller) {

        seller.setEnterprise(enterprise);
        sellers.add(seller);
    }

    public Sellers getSeller(int id) throws NotFindException {
        return findSeller(id);
    }

    private Sellers findSeller(int id) throws NotFindException {
        for (Sellers seller : sellers) {
            if (seller.getId() == id) {
                return seller;
            }
        }
        
        throw new NotFindException();
    }

    public void addMarket(Market market) {

        market.setType(TypeLocal.Mercado);

        this.markets.add(market);

        map.addNode(market);
    }

    public void addStorage(Storage storage) {

        storage.setType(TypeLocal.Armazém);

        this.storage.add(storage);

        map.addNode(storage);
    }

    public Graph<Local> getMap() {
        return map;
    }

    public void addPath(String nameA, String nameB, int distance) throws NotFindException {
        map.addPath(findByName(nameA), findByName(nameB), distance);
    }

    private Local findByName(String name) throws NotFindException {

        if (name.equals(enterprise.getName())) {
            return enterprise;
        }

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
        
        throw new NotFindException();

    }
    
    public LinkedList<Local> fazerPercurso(int id) throws NotFindException, NodesNotConectionException, NotComparableException, ElementNotFoundException, EmptyCollectionException{
        
        return getSeller(id).walkAllPath(map);
    }

}
