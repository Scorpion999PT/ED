/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Enum.TypeLocal;
import Graph.Graph;
import Graph.Node;
import Local.Local;
import Local.Market;
import Local.Storage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author renat
 */
public class Sellers {

    // Mercados a visitar pelos vendedores
    private Market[] ownedMarkets;

    private double maxWeight;
    // Mercadoria disponivel
    private double currentStorage;
    private String id;

    private LinkedList<Local> currentRoot;

    private Local posicaoAtual;

    private LinkedList<Local> mercadorPorVisitar = new LinkedList<>();

    public Sellers(Market[] ownedMarkets, String id, double maxWeight, Local enterprise) {
        this.ownedMarkets = ownedMarkets;
        this.maxWeight = maxWeight;
        this.id = id;

        // O Seller começa na empresa o precurso
        this.posicaoAtual = enterprise;
    }

    public Sellers(String id, double maxWeight, Local enterprise) {
        this.maxWeight = maxWeight;
        this.id = id;

        // O Seller começa na empresa o precurso
        this.posicaoAtual = enterprise;
    }

    public String getId() {
        return id;
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

    public Iterator getCurrentRoot() {
        return currentRoot.iterator();
    }

    public void walkAllPath(Graph<Local> map) {

        currentRoot = new LinkedList<>();

        for (int i = 0; i < ownedMarkets.length; i++) {

            //Se nao tiver mercadoria suficiente para 1 cliente
            // if (currentStorage < ownedMarkets[i].getStorageNeed()) {
            if(currentStorage == 0){
                goToStorage(map);
                // Abastecer
                currentStorage = ((Storage)posicaoAtual).retirarArmazem(maxWeight);

                i--;
                continue;
            }

            goToPosition(ownedMarkets[i], map);
            currentStorage = ((Market)posicaoAtual).giveMerchandise(currentStorage);

            // Se o market ainda tem clientes a pedir mercadoria
            if (ownedMarkets[i].haveClients()) {
                i--;
            }
        }
    }

    // Adicionar iterator a current root
    private void addCurrentRoot(Iterator<Local> it) {

        while (it.hasNext()) {
            currentRoot.add(it.next());
        }
    }

    public void goToPosition(Local destino, Graph map) {

        Iterator it = map.getShortPath(posicaoAtual, destino);

        posicaoAtual = destino;

        addCurrentRoot(it);
    }

    // retorna o caminho usado
    private void goToStorage(Graph<Local> map) {

        Node<Local> node = findStorage(map);

        goToPosition(node.getKey(), map);
    }

    // Retorna o storage mais proximo
    private Node<Local> findStorage(Graph<Local> map) {

        // Procurar o mercado mais proximo
        Iterator<Node<Local>> nodes = map.getMoreClose(posicaoAtual);

        double distance = Double.MAX_VALUE;

        Node<Local> node = null;

        while (nodes.hasNext()) {
            Node<Local> next = nodes.next();
            if (next.getKey().getType() == TypeLocal.Armazem) {

                Storage storage = (Storage) next.getKey();

                if (!storage.isEmpty()) {
                    // Adicionar o if nao houver mercadoria no armazem
                    if (distance > next.getDistance()) {

                        distance = next.getDistance();

                        node = next;
                    }
                }
            }

        }

        if (node == null) {
            System.out.println("Nao encontrou nenhum storage disponivel");
        }
        return node;
    }

}
