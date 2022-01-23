/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Graph.Graph;
import Local.Local;
import Local.Market;
import Local.Storage;

import java.util.Iterator;

/**
 *
 * @author renat
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Enterprise enterprise = new Enterprise(new Local("Empresa"));

        // Adicionar Mercado
        Market mercado1 = new Market("Mercado 1");
        Market mercado2 = new Market("Mercado 2");
        Market mercado3 = new Market("Mercado 3");
        Market mercado4 = new Market("Mercado 4");
        Market mercado5 = new Market("Mercado 5");

        enterprise.addMarket(mercado1);
        enterprise.addMarket(mercado2);
        enterprise.addMarket(mercado3);
        enterprise.addMarket(mercado4);
        enterprise.addMarket(mercado5);

        // Adicionar Armazem
        enterprise.addStorage(new Storage("Armazem 1"));
        enterprise.addStorage(new Storage("Armazem 2"));
        enterprise.addStorage(new Storage("Armazem 3"));
        enterprise.addStorage(new Storage("Armazem 4"));

        // Adicionar caminhos
        enterprise.addPath("Empresa", "Mercado 1", 2);
        enterprise.addPath("Empresa", "Armazem 1", 4);
        enterprise.addPath("Armazem 1", "Mercado 4", 3);
        enterprise.addPath("Mercado 4", "Armazem 2", 7);
        enterprise.addPath("Mercado 1", "Mercado 2", 3);
        enterprise.addPath("Mercado 1", "Mercado 3", 3);
        enterprise.addPath("Mercado 3", "Armazem 3", 6);
        enterprise.addPath("Mercado 3", "Armazem 2", 4);
        enterprise.addPath("Mercado 5", "Mercado 2", 1);
        enterprise.addPath("Mercado 5", "Armazem 3", 2);
        enterprise.addPath("Mercado 5", "Armazem 4", 5);

        // Adicionar Sellers
        Market[] ownedMarkets1 = {mercado1, mercado2};
        enterprise.addSellers(ownedMarkets1, "1", 10);

        Market[] ownedMarkets2 = {mercado2, mercado3};
        enterprise.addSellers(ownedMarkets2, "2", 15);

        Market[] ownedMarkets3 = {mercado4, mercado5};
        enterprise.addSellers(ownedMarkets3, "3", 20);

        // Map
        Graph<Local> map = enterprise.getMap();

//        System.out.println("Ir para o primeiro mercado");
//        enterprise.getSeller("1").walkAllPath(map);
//
//        Iterator<Local> it = enterprise.getSeller("1").getCurrentRoot();
//
//        while (it.hasNext()) {
//            System.out.println(it.next().getName());
//        }


        enterprise.getSeller("1").walkAllPath(map);

        Iterator<Local> it2 = enterprise.getSeller("1").getCurrentRoot();

        while (it2.hasNext()) {
            System.out.println(it2.next().getName());
        }
    }
}
