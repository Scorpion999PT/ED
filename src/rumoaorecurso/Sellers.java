/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumoaorecurso;

/**
 *
 * @author renat
 */
public class Sellers {
    
    // Mercados a visitar pelos vendedores
    private Market ownedMarkets[]; 
    
    private double maxWeight;
    
    private Root CurrentRoot;

    public Sellers(Market[] ownedMarkets, double maxWeight) {
        this.ownedMarkets = ownedMarkets;
        this.maxWeight = maxWeight;
    }
    public Sellers(double maxWeight){
        this.maxWeight = maxWeight;
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
    
    
    
    
}
