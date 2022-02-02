/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Import_Export;

import Exceptions.ElementNotFoundException;
import Exceptions.NotFindException;
import Local.Local;
import Local.Market;
import Local.Storage;
import Main.Enterprise;
import Main.Sellers;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author renat
 */
public class Export {

    private Enterprise enterprise;

    public Export(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public void exportEnterprise(String nameFile) throws IOException, NotFindException, ElementNotFoundException {

        FileWriter file = new FileWriter("file\\" + nameFile + ".json");

        JSONObject obj = new JSONObject();

        // Vendedores
        JSONArray vendedores = new JSONArray();
        for (int i = 0; i < enterprise.getSellers().size(); i++) {

            Sellers vendedor = enterprise.getSellers().get(i);

            JSONObject JSONvendedor = new JSONObject();
            JSONvendedor.put("Id", vendedor.getId());
            JSONvendedor.put("Nome", vendedor.getNome());
            JSONvendedor.put("Maximo Peso", vendedor.getMaxWeight());

            JSONArray ownderMarkets = new JSONArray();
            for (int j = 0; j < vendedor.getOwnedMarkets().size(); j++) {
                ownderMarkets.add(vendedor.getOwnedMarkets().get(j).getName());
            }
            JSONvendedor.put("Mercados a Visitar", ownderMarkets);

            JSONArray curretRoot = new JSONArray();
            Iterator<Local> it = vendedor.getCurrentRoot();
            if (it != null) {
                while (it.hasNext()) {

                    curretRoot.add(it.next().getName());
                }
                JSONvendedor.put("Caminho percorrido", curretRoot);
            }

            vendedores.add(JSONvendedor);
        }
        obj.put("Vendedores", vendedores);

        // Mercados
        JSONArray mercados = new JSONArray();
        for (int i = 0; i < enterprise.getMarkets().size(); i++) {

            Market mercado = enterprise.getMarkets().get(i);

            JSONObject JSONmercado = new JSONObject();
            JSONmercado.put("ID", mercado.getId());
            JSONmercado.put("Nome", mercado.getName());

            JSONArray clients = new JSONArray();
            for (int j = 0; j < mercado.getClientsList().size(); j++) {
                clients.add(mercado.getClientsList().get(j));
            }
            JSONmercado.put("Clientes", clients);

            mercados.add(JSONmercado);
        }
        obj.put("Mercados", mercados);

        // Storage
        JSONArray armazens = new JSONArray();
        for (int i = 0; i < enterprise.getStorages().size(); i++) {

            Storage armazem = enterprise.getStorages().get(i);

            JSONObject JSONStorage = new JSONObject();
            JSONStorage.put("ID", armazem.getId());
            JSONStorage.put("Nome", armazem.getName());
            JSONStorage.put("Stock Disponivel", armazem.getMerchandiseAvailable());
            JSONStorage.put("Stock Maximo", armazem.getMerchandiseMax());

            armazens.add(JSONStorage);
        }
        obj.put("Armazens", armazens);

        // Caminhos
        file.write(obj.toJSONString());
        file.flush();
        file.close();
    }

    public void exportSeller(Sellers seller, String nameFile) throws IOException, ElementNotFoundException {

        FileWriter file = new FileWriter("file\\" + nameFile + ".json");

        JSONObject obj = new JSONObject();

        JSONObject JSONvendedor = new JSONObject();

        JSONvendedor.put("Id", seller.getId());
        JSONvendedor.put("Nome", seller.getNome());
        JSONvendedor.put("Maximo Peso", seller.getMaxWeight());

        JSONArray ownderMarkets = new JSONArray();
        for (int j = 0; j < seller.getOwnedMarkets().size(); j++) {
            ownderMarkets.add(seller.getOwnedMarkets().get(j).getName());
        }
        JSONvendedor.put("Mercados a Visitar", ownderMarkets);

        JSONArray curretRoot = new JSONArray();
        Iterator<Local> it = seller.getCurrentRoot();
        if (it != null) {
            while (it.hasNext()) {

                curretRoot.add(it.next().getName());
            }
            JSONvendedor.put("Caminho percorrido", curretRoot);
        }

        obj.put("Vendedor", JSONvendedor);

        file.write(obj.toJSONString());
        file.flush();
        file.close();
    }

    public void exportStorage(Storage storage, String nameFile) throws IOException, ElementNotFoundException {

        FileWriter file = new FileWriter("file\\" + nameFile + ".json");

        JSONObject obj = new JSONObject();

        JSONObject JSONStorage = new JSONObject();

        JSONStorage.put("ID", storage.getId());
        JSONStorage.put("Nome", storage.getName());
        JSONStorage.put("Stock Disponivel", storage.getMerchandiseAvailable());
        JSONStorage.put("Stock Maximo", storage.getMerchandiseMax());

        obj.put("Storage", JSONStorage);

        file.write(obj.toJSONString());
        file.flush();
        file.close();
    }

    public void exportMarket(Market market, String nameFile) throws IOException, ElementNotFoundException {

        FileWriter file = new FileWriter("file\\" + nameFile + ".json");

        JSONObject obj = new JSONObject();
        
        JSONObject JSONMercado = new JSONObject();
        JSONMercado.put("ID", market.getId());
        JSONMercado.put("Nome", market.getName());

        JSONArray clients = new JSONArray();
        for (int j = 0; j < market.getClientsList().size(); j++) {
            clients.add(market.getClientsList().get(j));
        }
        JSONMercado.put("Clientes", clients);

        obj.put("Storage", JSONMercado);

        file.write(obj.toJSONString());
        file.flush();
        file.close();
    }

}
