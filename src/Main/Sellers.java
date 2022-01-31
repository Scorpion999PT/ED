/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Enum.TypeLocal;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NodesNotConectionException;
import Exceptions.NotComparableException;
import Exceptions.NotFindException;
import Graph.Graph;
import Graph.Node;
import Local.Local;
import Local.Market;
import Local.Storage;
import Structs.ArrayOrderedList;
import java.util.Iterator;
import Structs.LinkedList;

/**
 *
 * @author renat
 */
public class Sellers {

    // Mercados a visitar pelos vendedores
    private LinkedList<Market> ownedMarkets = new LinkedList<Market>();

    private double maxWeight;
    // Mercadoria disponivel
    private double currentStorage;
    private int id;
    private String nome;

    private LinkedList<Local> currentRoot;

    private Local posicaoAtual;
    private Local enterprise;

    private LinkedList<Local> mercadorPorVisitar = new LinkedList<>();

    public Sellers(LinkedList<Market> ownedMarkets, int id,String nome ,double maxWeight) {
        this.ownedMarkets = ownedMarkets;
        this.nome = nome;
        this.maxWeight = maxWeight;
        this.id = id;
    }

    public Sellers(int id,String nome, double maxWeight) {
        this.nome = nome;
        this.maxWeight = maxWeight;
        this.id = id;

        // O Seller começa na empresa o precurso
        this.posicaoAtual = enterprise;
    }
    
    public void setEnterprise(Local enterprise){
        this.enterprise = enterprise;
        
        // O Seller começa na empresa o precurso
        this.posicaoAtual = enterprise;      
    }

    public int getId() {
        return id;
    }

    public LinkedList<Market> getOwnedMarkets() {
        return ownedMarkets;
    }

    public void setOwnedMarkets(LinkedList<Market> ownedMarkets) {
        this.ownedMarkets = ownedMarkets;
    }

    public void addOwnedMarkets(Market market){
        ownedMarkets.add(market);
    }
    
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Iterator getCurrentRoot() {
        return currentRoot.iterator();
    }

    public LinkedList<Local> walkAllPath(Graph<Local> map) throws NotFindException, NodesNotConectionException, NotComparableException, ElementNotFoundException, EmptyCollectionException {

        currentRoot = new LinkedList<>();

        for (int i = 0; i < ownedMarkets.size(); i++) {

            //Se nao tiver mercadoria suficiente para 1 cliente
            if (currentStorage == 0) {
                goToStorage(map);
                // Abastecer
                currentStorage = ((Storage) posicaoAtual).retirarArmazem(maxWeight);

                i--;
                continue;
            }

            goToPosition(ownedMarkets.get(i), map);
            currentStorage = ((Market) posicaoAtual).giveMerchandise(currentStorage);

            // Se o market ainda tem clientes a pedir mercadoria
            if (ownedMarkets.get(i).haveClients()) {
                i--;
            }
        }
        
        goToPosition(enterprise, map);
        
        return currentRoot;
    }

    // Adicionar iterator a current root
    private void addCurrentRoot(Iterator<Local> it) {

        while (it.hasNext()) {
            currentRoot.add(it.next());
        }
    }

    public void goToPosition(Local destino, Graph map) throws NotFindException, NodesNotConectionException, EmptyCollectionException, ElementNotFoundException {

        Iterator it = map.getShortPath(posicaoAtual, destino);

        posicaoAtual = destino;

        addCurrentRoot(it);
    }

    // retorna o caminho usado
    private void goToStorage(Graph<Local> map) throws NotFindException, NodesNotConectionException, NotComparableException, EmptyCollectionException, ElementNotFoundException {

        Node<Local> node = findStorage(map);

        goToPosition(node.getKey(), map);
    }

    // Retorna o storage mais proximo
    private Node<Local> findStorage(Graph<Local> map) throws NotFindException, NotComparableException, EmptyCollectionException, ElementNotFoundException {

        // Procurar o mercado mais proximo
        ArrayOrderedList<Node> nodes = map.getMoreClose(posicaoAtual);

        Node<Local> node = null;

        Node<Local> x;

        for (int i = 0; i < nodes.size(); i++) {

            x = nodes.get(i);

            if ((x.getKey()).getType() == TypeLocal.Armazém) {

                Storage storage = (Storage) x.getKey();

                if (!storage.isEmpty()) {

                    return x;
                }
            }

        }

        throw new NotFindException();
    }

}
