/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Reportes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class AnuncioConVisualizaciones {
    
    private String nombreUsuario;
    
    private Long idAnuncio;
    private String tipoAnuncio;
    private String rutaUrl;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaVisualizacion;
    private int totalVisualizaciones;

    public AnuncioConVisualizaciones() {
    }

    public Long getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Long idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getRutaUrl() {
        return rutaUrl;
    }

    public void setRutaUrl(String rutaUrl) {
        this.rutaUrl = rutaUrl;
    }

    public LocalDate getFechaVisualizacion() {
        return fechaVisualizacion;
    }

    public void setFechaVisualizacion(LocalDate fechaVisualizacion) {
        this.fechaVisualizacion = fechaVisualizacion;
    }

    public int getTotalVisualizaciones() {
        return totalVisualizaciones;
    }

    public void setTotalVisualizaciones(int totalVisualizaciones) {
        this.totalVisualizaciones = totalVisualizaciones;
    }

    @Override
    public String toString() {
        return "AnuncioConVisualizaciones{" + "nombreUsuario=" + nombreUsuario + ", idAnuncio=" + idAnuncio + ", tipoAnuncio=" + tipoAnuncio + ", rutaUrl=" + rutaUrl + ", fechaVisualizacion=" + fechaVisualizacion + ", totalVisualizaciones=" + totalVisualizaciones + '}';
    }
    
    
    
}
