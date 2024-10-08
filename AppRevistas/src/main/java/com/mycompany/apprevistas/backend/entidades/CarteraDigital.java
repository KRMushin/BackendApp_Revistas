/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.entidades;

/**
 *
 * @author kevin-mushin
 */
public class CarteraDigital {
    
    private String nombreUsuario;
    private Double saldoDisponible;

    public CarteraDigital() {
    }

    public CarteraDigital(String nombreUsuario, Double saldoDisponible) {
        this.nombreUsuario = nombreUsuario;
        this.saldoDisponible = saldoDisponible;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }
    
    
    
}
