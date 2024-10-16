/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos;

/**
 *
 * @author kevin-mushin
 */
public class ConfiguracionAnuncio {
    
    private Long idAnuncio;
    private String tipoAnuncio;
    private Double precio; //representa al precio base
    private Double tiempoDuracion; // representa al precio por dia

    /*construct vacio*/
    public ConfiguracionAnuncio() {
    }
    

    public Long getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Long idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(Double tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }
    
    public boolean esValido() {
    return idAnuncio != null && idAnuncio > 0 &&
           tipoAnuncio != null && !tipoAnuncio.isEmpty() &&
           precio != null && precio > 0 &&
           tiempoDuracion > 0;
}

    
}
