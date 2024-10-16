/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.RevistasDTOs;

/**
 *
 * @author kevin-mushin
 */
public class MantenimientoCostoDTO {
    
    private Long idRevista;
    private Double costoNuevo;

    public MantenimientoCostoDTO() {
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public Double getCostoNuevo() {
        return costoNuevo;
    }

    public void setCostoNuevo(Double costoNuevo) {
        this.costoNuevo = costoNuevo;
    }
    
    
    
}
