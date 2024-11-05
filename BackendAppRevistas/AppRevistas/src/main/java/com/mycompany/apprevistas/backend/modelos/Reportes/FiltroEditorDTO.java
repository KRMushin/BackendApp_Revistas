/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Reportes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mycompany.apprevistas.backend.constantes.Filtros.TipoReporteRevista;
import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class FiltroEditorDTO {
    
    TipoReporteRevista tipoReporte;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaInicio;            // Filtro por fecha de inicio
    private LocalDate fechaFin;               // Filtro por fecha de fin
    /* VARS REPORTE*/
    private Long idRevista;
    private String nombreEditor;

    public FiltroEditorDTO() {
    }

    public TipoReporteRevista getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoReporteRevista tipoReporte) {
        this.tipoReporte = tipoReporte;
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

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public String getNombreEditor() {
        return nombreEditor;
    }

    public void setNombreEditor(String nombreEditor) {
        this.nombreEditor = nombreEditor;
    }
    
    
    
}
