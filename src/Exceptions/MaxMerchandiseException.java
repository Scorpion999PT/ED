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
public class MaxMerchandiseException extends Exception {

    /**
     * Creates a new instance of <code>MaxMerchandiseException</code> without
     * detail message.
     */
    public MaxMerchandiseException() {
    }

    /**
     * Constructs an instance of <code>MaxMerchandiseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public MaxMerchandiseException(String msg) {
        super(msg);
    }
}
