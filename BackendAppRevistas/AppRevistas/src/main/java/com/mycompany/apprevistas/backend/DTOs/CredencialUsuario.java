/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.DTOs;

import com.mycompany.apprevistas.backend.util.RolUsuario;

/**
 *
 * @author kevin-mushin
 */
public class CredencialUsuario {
    
    private boolean estaAutenticado;
    private String token;

    public CredencialUsuario() {
    }

    public boolean isEstaAutenticado() {
        return estaAutenticado;
    }

    public void setEstaAutenticado(boolean estaAutenticado) {
        this.estaAutenticado = estaAutenticado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
    
    
}
