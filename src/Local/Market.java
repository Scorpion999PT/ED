/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Local;

import Enum.TypeLocal;
import java.util.LinkedList;
import Main.Clients;

/**
 *
 * @author renat
 */
public class Market extends Local {

    private LinkedList<Double> clientsList = new LinkedList<>();

    public Market(String name) {
        super(name);
    }

    public void addClient(double valor) {
        clientsList.add(valor);
    }

    public void addClients(double[] valores) {
        for (double valor : valores) {
            clientsList.add(valor);
        }
    }

    public double getStorageNeed() {

        return clientsList.getLast();
    }

    public boolean haveClients() {
        return !clientsList.isEmpty();
    }

    // Retornar o que sobra
    public double giveMerchandise(double amount) {

        while (amount != 0 && haveClients()) {

            Double client = clientsList.pollLast();
            
            if (amount >= client) {
                amount -= client;
            }
            else{
                client -= amount;
                amount = 0;
                
                clientsList.addLast(client);
            }
            

        }
        
        return amount;
    }
}
