/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Reportes;

import com.mycompany.apprevistas.backend.modelos.Suscripcion;

/**
 *
 * @author kevin-mushin
 */
public class RevistaConSuscripciones {
    
    private Long idRevista;
    private String nombreAutor;
    private String tituloRevista;
    private Suscripcion suscripcionesRevista;

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public Suscripcion getSuscripcionesRevista() {
        return suscripcionesRevista;
    }

    public void setSuscripcionesRevista(Suscripcion suscripcionesRevista) {
        this.suscripcionesRevista = suscripcionesRevista;
    }
    
    
}
