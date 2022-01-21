/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Local;

import Enum.TypeLocal;

/**
 *
 * @author renat
 */
public class Local {

    private String name;
    private int id;
    private TypeLocal type;

    public Local(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public TypeLocal getType() {
        return type;
    }
    
    public void setType(TypeLocal type){
        this.type = type;
    }
    
}
