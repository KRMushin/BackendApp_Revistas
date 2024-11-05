/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Reportes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mycompany.apprevistas.backend.constantes.Filtros.TipoReporteAnuncio;
import com.mycompany.apprevistas.backend.constantes.TipoAnuncio;
import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class FiltrosAdminDTO {
    
    TipoReporteAnuncio tipoReporte;
    
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaInicio;            // Filtro por fecha de inicio
    private LocalDate fechaFin;               // Filtro por fecha de fin
    
    private TipoAnuncio tipoAnuncio;
    private int diasPeriodo;
    private String nombreAnunciante;

    public FiltrosAdminDTO() {
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public TipoAnuncio getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(TipoAnuncio tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public int getDiasPeriodo() {
        return diasPeriodo;
    }

    public void setDiasPeriodo(int diasPeriodo) {
        this.diasPeriodo = diasPeriodo;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public TipoReporteAnuncio getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoReporteAnuncio tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
    
    
}
