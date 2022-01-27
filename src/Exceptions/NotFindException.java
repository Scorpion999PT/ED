/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author renat
 */
public class NotFindException extends Exception {

    /**
     * Creates a new instance of <code>NotFindException</code> without detail
     * message.
     */
    public NotFindException() {
    }

    /**
     * Constructs an instance of <code>NotFindException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotFindException(String msg) {
        super(msg);
    }
}
