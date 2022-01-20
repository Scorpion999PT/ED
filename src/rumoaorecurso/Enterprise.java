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
public class Enterprise {
    
    private Sellers[] sellers;
    private int numSellers = 0;

    public Enterprise() {
        
        sellers = new Sellers[10]; // Temporario
        
    }
    
    
    
    public void addSellers(double maxWeight){
        sellers[numSellers] = new Sellers(maxWeight);
    }
    
    
    
}
