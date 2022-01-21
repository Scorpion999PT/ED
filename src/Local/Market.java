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
public class Market extends Local{

    private LinkedList<Integer> clientsList;

    public Market(String name) {
        super(name);
    }
    
    public void addClient(int valor){
        clientsList.add(valor);
    }
}
