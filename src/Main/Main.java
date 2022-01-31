/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NodesNotConectionException;
import Exceptions.NotComparableException;
import Exceptions.NotFindException;
import Graph.Graph;
import Local.Local;
import Local.Market;
import Local.Storage;
import Structs.LinkedList;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renat
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Enterprise enterprise = new Enterprise();

        try {
            Generate.gerarManualmente(enterprise);
            // Generate.gerarAutomaticamente(enterprise);
        } catch (NotFindException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            enterprise.fazerPercurso(1);
        } catch (NotFindException | NodesNotConectionException | NotComparableException | ElementNotFoundException | EmptyCollectionException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Iterator<Local> it2;
        
        try {
            
            it2 = enterprise.getSeller(1).getCurrentRoot();
            while (it2.hasNext()) {
                System.out.println(it2.next().getName());
            }
            
        } catch (NotFindException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
