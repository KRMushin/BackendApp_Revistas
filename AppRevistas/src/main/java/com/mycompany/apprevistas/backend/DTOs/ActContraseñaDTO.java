/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.DTOs;

/**
 *
 * @author kevin-mushin
 */
public class ActContraseñaDTO {
    
    private String nombreUsuario;
    private String actualPassword;
    private String nuevaPassword;

    public ActContraseñaDTO() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNuevaPassword() {
        return nuevaPassword;
    }

    public void setNuevaPassword(String nuevaPassword) {
        this.nuevaPassword = nuevaPassword;
    }
    
    public boolean esValido(){
        return nuevaPassword != null && !nuevaPassword.trim().isEmpty() && !nuevaPassword.contains(" ");
    }
}
