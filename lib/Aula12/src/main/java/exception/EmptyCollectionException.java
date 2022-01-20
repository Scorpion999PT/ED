package exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mariana Ribeiro
 */
public class EmptyCollectionException extends RuntimeException {

     public static String GRAFO="\n - O grafo encontra-se vazio";
    
    /**
     * Creates a new instance of <code>EmptyCollectionException</code> without detail
     * message.
     */
    public EmptyCollectionException() {
    }

    /**
     * Constructs an instance of <code>EmptyCollectionException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public EmptyCollectionException(String msg) {
        super(msg);
    }
}
