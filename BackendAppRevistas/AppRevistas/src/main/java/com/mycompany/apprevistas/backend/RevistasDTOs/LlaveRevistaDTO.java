/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.RevistasDTOs;

/**
 *
 * @author kevin-mushin
 */
public class LlaveRevistaDTO {
    
    private Long idRevista;
    private String tituloRevista;
    private String descripcion;
    private String estadoRevista;
    private Double costoMantenimiento;
    private Double costoBloqueoAnuncios;

    public LlaveRevistaDTO() {
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoRevista() {
        return estadoRevista;
    }

    public void setEstadoRevista(String estadoRevista) {
        this.estadoRevista = estadoRevista;
    }

    public Double getCostoMantenimiento() {
        return costoMantenimiento;
    }

    public void setCostoMantenimiento(Double costoMantenimiento) {
        this.costoMantenimiento = costoMantenimiento;
    }

    public Double getCostoBloqueoAnuncios() {
        return costoBloqueoAnuncios;
    }

    public void setCostoBloqueoAnuncios(Double costoBloqueoAnuncios) {
        this.costoBloqueoAnuncios = costoBloqueoAnuncios;
    }

    
    
    
}
