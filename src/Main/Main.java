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

        LinkedList<Integer> teste = new LinkedList<>();

        teste.add(0);
        teste.add(1);
        teste.add(2);
        teste.add(3);
        teste.add(4);
        teste.add(5);

        try {
            teste.remove(0);
            teste.remove(1);
            teste.remove(3);
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Iterator<Integer> it = teste.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }

//        for (Integer x:teste) {
//            System.out.println(x);
//            
//        }
//        System.out.println(teste.size());
        Enterprise enterprise = new Enterprise(new Local("Empresa"));
        Graph<Local> map = enterprise.getMap();

        Generate.gerarManualmente(enterprise);

        // Generate.gerarAutomaticamente(enterprise);
        // Map
        try {
            enterprise.getSeller("1").walkAllPath(map);
        } catch (NotFindException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NodesNotConectionException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotComparableException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Iterator<Local> it2 = enterprise.getSeller("1").getCurrentRoot();

        while (it2.hasNext()) {
            System.out.println(it2.next().getName());
        }
    }
}
