/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Excepciones;

/**
 *
 * @author kevin-mushin
 */
public class DatosInvalidosUsuarioException extends  RuntimeException{

    public DatosInvalidosUsuarioException() {
    }

    public DatosInvalidosUsuarioException(String message) {
        super(message);
    }

    public DatosInvalidosUsuarioException(Throwable cause) {
        super(cause);
    }
    
    
    
    
}
