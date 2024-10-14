/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos;

/**
 *
 * @author kevin-mushin
 */
public class CarteraDigital {
    
    private String nombreUsuario;
    private Double cantidadDinero;

    public CarteraDigital() {
    }

    public CarteraDigital(String nombreUsuario, Double saldoDisponible) {
        this.nombreUsuario = nombreUsuario;
        this.cantidadDinero = saldoDisponible;
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

    
    
    
}
