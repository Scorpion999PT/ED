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
public class NodesNotConectionException extends Exception {

    /**
     * Creates a new instance of <code>NodesNotConectionException</code> without
     * detail message.
     */
    public NodesNotConectionException() {
    }

    /**
     * Constructs an instance of <code>NodesNotConectionException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NodesNotConectionException(String msg) {
        super(msg);
    }
}
