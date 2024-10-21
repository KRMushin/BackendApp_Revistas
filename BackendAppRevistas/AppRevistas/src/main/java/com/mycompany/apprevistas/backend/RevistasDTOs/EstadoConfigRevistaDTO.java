/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.RevistasDTOs;

/**
 *
 * @author kevin-mushin
 */
public class EstadoConfigRevistaDTO {
    
    private Long idRevista;
    private boolean estado;
    private EstadoRevista tipoEstado;

    public EstadoConfigRevistaDTO() {
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public EstadoRevista getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(EstadoRevista tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    @Override
    public String toString() {
        return "EstadoConfigRevistaDTO{" + "idRevista=" + idRevista + ", estado=" + estado + ", tipoEstado=" + tipoEstado + '}';
    }
    
    
    
    
}
