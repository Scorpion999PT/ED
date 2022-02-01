/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Import_Export;

import Enum.TypeLocal;
import Exceptions.NotFindException;
import Local.Local;
import Local.Market;
import Local.Storage;
import Main.Enterprise;
import Main.Sellers;
import Structs.LinkedList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author renat
 */
public class Import {

    public void importar(Enterprise enterprise,String nameFile) throws FileNotFoundException, IOException, ParseException, NotFindException {

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("file\\" + nameFile + ".json"));

        JSONObject jsonObject = (JSONObject) obj;
        
        // Locais
        
        JSONArray locais = (JSONArray) jsonObject.get("locais");
        
        for (int i = 0; i < locais.size(); i++) {
            
            JSONObject local = (JSONObject)locais.get(i);
            
            String nome = (String)local.get("nome");
            
            TypeLocal typelocal = TypeLocal.valueOf((String)local.get("tipo"));
            
            switch(typelocal){
                case Sede:
                    enterprise.setEnterprise(new Local(nome));
                    break;
                    
                case Mercado:
                    
                    Market mercado = new Market(nome);
            
                    JSONArray clientes = (JSONArray) local.get("clientes");
                    
                    for (int j = 0; j < clientes.size(); j++) {
                        mercado.addClient((long)clientes.get(j));
                    }
                    enterprise.addMarket(mercado);
                    break;
                    
                case Armazém:
                    
                    Storage armazem = new Storage(nome,(long) local.get("capacidade"));  
                    armazem.addMerchandise((long)local.get("stock"));   
                    enterprise.addStorage(armazem);
                    break;  
            }   
        }
        
        // Caminhos
        JSONArray caminhos = (JSONArray) jsonObject.get("caminhos");
        
        for (int i = 0; i < caminhos.size(); i++) {
            
            JSONObject caminho = (JSONObject)caminhos.get(i);
            String pontoA = (String)caminho.get("de");
            String pontoB = (String)caminho.get("para");
            int distancia = (int)(long)caminho.get("distancia");
            
            enterprise.addPath(pontoA, pontoB, distancia);
        }
        
        
        // Vendedores
        JSONArray vendedores = (JSONArray) jsonObject.get("vendedores");

        for (int i = 0; i < vendedores.size(); i++) {

            JSONObject vendedor = (JSONObject) vendedores.get(i);

            int id = (int) (long) vendedor.get("id");
            String nome = (String) vendedor.get("nome");
            double capacidade = (long) vendedor.get("capacidade");

            JSONArray mercadosVisitar = (JSONArray) vendedor.get("mercados_a_visitar");

            LinkedList<Market> mercados = new LinkedList<>();

            for (int j = 0; j < mercadosVisitar.size(); j++) {

                String mercado = (String) mercadosVisitar.get(j);

                mercados.add(enterprise.getMarket(mercado));
            }
            
            enterprise.addSellers(new Sellers(mercados, id, nome, capacidade));
        }

    }

}
