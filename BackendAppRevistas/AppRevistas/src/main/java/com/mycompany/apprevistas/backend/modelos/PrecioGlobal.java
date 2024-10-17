/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos;

/**
 *
 * @author kevin-mushin
 */
public class PrecioGlobal {
    
    
    private Long idPrecio;
    private Double precioGlobal;
    private String modeloPrecio;

    public PrecioGlobal() {
    }

    public Long getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Long idPrecio) {
        this.idPrecio = idPrecio;
    }

    public Double getPrecioGlobal() {
        return precioGlobal;
    }

    public void setPrecioGlobal(Double precioGlobal) {
        this.precioGlobal = precioGlobal;
    }

    public String getModeloPrecio() {
        return modeloPrecio;
    }

    public void setModeloPrecio(String modeloPrecio) {
        this.modeloPrecio = modeloPrecio;
    }

    public boolean esValido() {
    return precioGlobal != null && precioGlobal > 0 &&
           modeloPrecio != null && !modeloPrecio.trim().isEmpty() &&
           idPrecio != null && idPrecio > 0;
    }

    
}
