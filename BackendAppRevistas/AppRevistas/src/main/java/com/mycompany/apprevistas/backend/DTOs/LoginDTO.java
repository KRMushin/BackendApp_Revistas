/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.DTOs;

/**
 *
 * @author kevin-mushin
 */
public class LoginDTO {
    
    private String nombreUsuario;
    private String password;

    public LoginDTO() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean esValida() {
        return password != null && !password.isEmpty() &&
                    nombreUsuario != null && !nombreUsuario.isEmpty() && nombreUsuario.length() <= 15;
    }
    
}
