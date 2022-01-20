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
public class NullPointerException extends Exception {

    /**
     * Creates a new instance of <code>NullPointerException</code> without
     * detail message.
     */
    public NullPointerException() {
    }

    /**
     * Constructs an instance of <code>NullPointerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NullPointerException(String msg) {
        super(msg);
    }
}
