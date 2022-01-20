/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Mariana Ribeiro
 */
public class ElementNotFoundException extends RuntimeException {

    public static String NOT_EXIST="\n - O elemento que deseja remover não existe";
    public static String EDGE_NOT_EXIST="\n - A edge que deseja remover não existe";

    
    public ElementNotFoundException(String write) {
        throw new UnsupportedOperationException(write); //To change body of generated methods, choose Tools | Templates.
    }
    
}
