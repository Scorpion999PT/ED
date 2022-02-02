/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Import_Export;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NodesNotConectionException;
import Exceptions.NotComparableException;
import Exceptions.NotFindException;
import Local.Local;
import Main.Enterprise;
import java.io.FileNotFoundException;
import java.io.IOException;
import Structs.LinkedList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author renat
 */
public class TesteJson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Import importar = new Import();

        Enterprise enterprise = new Enterprise();

        try {
            importar.importar(enterprise,"ImportarED");
        } catch (IOException | ParseException | NotFindException ex) {
            Logger.getLogger(TesteJson.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        try {
            LinkedList<Local> percurso = enterprise.fazerPercurso(1);

            Iterator<Local> it = percurso.iterator();
        
            while (it.hasNext()) {
                Local next = it.next();
                
                System.out.println(next.getName());

            }

        } catch (NotFindException | NodesNotConectionException | NotComparableException | ElementNotFoundException | EmptyCollectionException ex) {
            Logger.getLogger(TesteJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Export export = new Export(enterprise);
        
        try {
            export.exportEnterprise("teste");
        } catch (IOException | NotFindException | ElementNotFoundException ex) {
            Logger.getLogger(TesteJson.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
