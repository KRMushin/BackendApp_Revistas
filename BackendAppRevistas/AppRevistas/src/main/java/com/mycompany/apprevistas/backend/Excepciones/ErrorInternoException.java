/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Excepciones;

/**
 *
 * @author kevin-mushin
 */
public class ErrorInternoException extends RuntimeException{

    public ErrorInternoException() {
    }

    public ErrorInternoException(String message) {
        super(message);
    }

    public ErrorInternoException(Throwable cause) {
        super(cause);
    }
    
}
