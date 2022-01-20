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
public class InvalidElementException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidElementException</code> without
     * detail message.
     */
    public InvalidElementException() {
    }

    /**
     * Constructs an instance of <code>InvalidElementException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidElementException(String msg) {
        super(msg);
    }
}
