/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.Excepciones;

/**
 *
 * @author kevin-mushin
 */
public class TransaccionFallidaException extends  RuntimeException{

    public TransaccionFallidaException() {
    }

    public TransaccionFallidaException(String message) {
        super(message);
    }

    public TransaccionFallidaException(Throwable cause) {
        super(cause);
    }
    
    
}
