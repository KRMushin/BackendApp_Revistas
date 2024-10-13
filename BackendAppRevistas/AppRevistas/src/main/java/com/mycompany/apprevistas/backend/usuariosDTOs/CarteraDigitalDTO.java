/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.usuariosDTOs;

/**
 *
 * @author kevin-mushin
 */
public class CarteraDigitalDTO {
    
    private String nombreUsuario;
    private Double cantidadDinero;

    public CarteraDigitalDTO() {
    }
    
    

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Double getCantidadDinero() {
        return cantidadDinero;
    }

    public void setCantidadDinero(Double cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }
    
    public boolean esValido() {
        return nombreUsuario != null 
            && !nombreUsuario.trim().isEmpty() 
            && cantidadDinero != null 
            && cantidadDinero > 0;
    }
}
