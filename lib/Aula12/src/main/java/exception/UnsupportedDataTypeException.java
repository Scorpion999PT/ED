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
public class UnsupportedDataTypeException extends RuntimeException {


    public UnsupportedDataTypeException(String not_Comparable) {
        throw new UnsupportedOperationException(not_Comparable); //To change body of generated methods, choose Tools | Templates.
    }
    
}
