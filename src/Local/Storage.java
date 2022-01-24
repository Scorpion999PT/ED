/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Local;

import Enum.TypeLocal;

/**
 *
 * @author renat
 */
public class Storage extends Local {

    private double merchandiseAvailable;
    private double merchandiseMax;

    public Storage(String name, double merchandiseMax) {
        super(name);
        this.merchandiseMax = merchandiseMax;
    }

    public double retirarArmazem(double amount) {

        if (amount > merchandiseAvailable) {
            amount = merchandiseAvailable;
            merchandiseAvailable = 0;

            System.out.println("O armazem " + super.getName() + " ficou sem mercadoria");
        }
        else{
            merchandiseAvailable -= amount;
        }
        
        return amount;
    }

    public void addMerchandise(double amount) {

        // Se tentar adicionar mais mercadoria do que a suportada pelo armazem
        if (merchandiseMax < amount + merchandiseAvailable) {
//            new Exceptions.MaxMerchandiseException();
            System.out.println("exceçao nao pode adicionar mais mercadoria ao armazem");
        }

        merchandiseAvailable += amount;
    }
    
    public boolean isEmpty(){
        if(merchandiseAvailable == 0){
            return true;
        }
        return false;
    }
    
}
