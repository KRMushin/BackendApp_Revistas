/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.RevistasDTOs;

import com.mycompany.apprevistas.backend.constantes.TipoCostoRevista;

/**
 *
 * @author kevin-mushin
 */
public class NuevoCostoDTO {
    
    private Long idRevista;
    private Double costo;
    private TipoCostoRevista tipoCosto;

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public TipoCostoRevista getTipoCosto() {
        return tipoCosto;
    }

    public void setTipoCosto(TipoCostoRevista tipoCosto) {
        this.tipoCosto = tipoCosto;
    }
    
    public boolean esValidao() {
    return idRevista != null && idRevista > 0 &&
           costo != null && costo > 0 &&
           tipoCosto != null;
}
    
}
