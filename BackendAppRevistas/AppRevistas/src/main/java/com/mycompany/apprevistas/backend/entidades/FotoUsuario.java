/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.entidades;

import java.io.InputStream;

/**
 *
 * @author kevin-mushin
 */
public class FotoUsuario {
    
    private String nombreUsuario;
    private String fotoUrl;

    public FotoUsuario() {
    }

    public FotoUsuario(String nombreUsuario, String url) {
        this.nombreUsuario = nombreUsuario;
        this.fotoUrl = url;
    }
    
    /*AREA SETTERS Y GETTERS*/

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    
}
