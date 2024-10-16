/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.RevistasDTOs;

/**
 *
 * @author kevin-mushin
 */
public class EstadoRevistaDTO {
    private Long idRevista;
    private String estadoCambio;
    private boolean estado;

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public String getEstadoCambio() {
        return estadoCambio;
    }

    public void setEstadoCambio(String estadoCambio) {
        this.estadoCambio = estadoCambio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
