/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Exceptions.ElementNotFoundException;
import Exceptions.NotFindException;
import Local.Market;
import Local.Storage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renat
 */
public class Generate {

    public static void gerarManualmente(Enterprise enterprise) {

        // Adicionar Mercado
        Market mercado1 = new Market("Mercado 1");
        Market mercado2 = new Market("Mercado 2");
        Market mercado3 = new Market("Mercado 3");
        Market mercado4 = new Market("Mercado 4");
        Market mercado5 = new Market("Mercado 5");

        double[] valores1 = {30, 60, 100, 50, 30};

        mercado1.addClients(valores1);

        double[] valores2 = {100, 40, 70, 40, 20};

        mercado2.addClients(valores2);

        enterprise.addMarket(mercado1);
        enterprise.addMarket(mercado2);
        enterprise.addMarket(mercado3);
        enterprise.addMarket(mercado4);
        enterprise.addMarket(mercado5);

        // Adicionar Armazem
        enterprise.addStorage(new Storage("Armazem 1", 500));
        enterprise.addStorage(new Storage("Armazem 2", 500));
        enterprise.addStorage(new Storage("Armazem 3", 500));
        enterprise.addStorage(new Storage("Armazem 4", 500));

        for (Storage storage : enterprise.getStorages()) {
            storage.addMerchandise(500);
        }

        try {
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
        } catch (NotFindException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Adicionar Sellers
        enterprise.addSellers("1", 200);
        enterprise.getSeller("1").addOwnedMarkets(mercado2);
        enterprise.getSeller("1").addOwnedMarkets(mercado4);
        enterprise.getSeller("1").addOwnedMarkets(mercado5);
    }

    public static void gerarAutomaticamente(Enterprise enterprise) throws NotFindException, ElementNotFoundException {

        int numMercados = 100;
        Market mercados[] = new Market[numMercados];

        for (int i = 0; i < numMercados; i++) {
            mercados[i] = new Market("Mercado " + i);
        }

        int numClientes = 3;
        for (int i = 0; i < numMercados; i++) {
            for (int j = 0; j < numClientes; j++) {
                mercados[i].addClient(100);
            }
        }

        for (int i = 0; i < numMercados; i++) {
            enterprise.addMarket(mercados[i]);

        }

        int numArmazens = 100;
        for (int i = 0; i < numArmazens; i++) {
            enterprise.addStorage(new Storage("Armazem " + i, 500));
            enterprise.getStorages().get(i).addMerchandise(500);
        }

        // Adicionar Sellers
        enterprise.addSellers("1", 200);
        for (int i = 0; i < numMercados; i++) {
            enterprise.getSeller("1").addOwnedMarkets(mercados[i]);
        }

        for (int i = 0; i < numMercados - 1; i++) {
            enterprise.addPath("Mercado " + i, "Mercado " + (i + 1), 5);
        }

        for (int i = 0; i < numArmazens - 1; i++) {
            enterprise.addPath("Armazem " + i, "Armazem " + (i + 1), 5);
        }

        for (int i = 0; i < numArmazens && i < numMercados; i++) {
            enterprise.addPath("Armazem " + i, "Mercado " + i, 7);
        }

        enterprise.addPath("Mercado 1", "Armazem 1", 5);
        enterprise.addPath("Empresa", "Armazem 1", 5);
    }
}
