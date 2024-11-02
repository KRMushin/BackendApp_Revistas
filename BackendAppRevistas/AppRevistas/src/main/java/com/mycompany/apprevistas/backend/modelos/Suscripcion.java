/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos;

import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class Suscripcion {
    
    private Long idSuscripcion;
    private String nombreUsuario;
    private Long idRevista;
    private LocalDate fechaSuscripcion;
    private boolean calificoRevista;

    public Suscripcion() {
    }

    public Long getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Long idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public LocalDate getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public boolean isCalificoRevista() {
        return calificoRevista;
    }

    public void setCalificoRevista(boolean calificoRevista) {
        this.calificoRevista = calificoRevista;
    }
    
}
