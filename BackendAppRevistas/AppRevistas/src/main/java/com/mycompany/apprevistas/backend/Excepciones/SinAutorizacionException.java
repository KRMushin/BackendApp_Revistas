/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Excepciones;

/**
 *
 * @author kevin-mushin
 */
public class SinAutorizacionException extends RuntimeException{

    public SinAutorizacionException() {
    }

    public SinAutorizacionException(String message) {
        super(message);
    }

    public SinAutorizacionException(Throwable cause) {
        super(cause);
    }
    
    
    
}
