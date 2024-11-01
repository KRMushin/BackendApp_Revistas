/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.RevistasDTOs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.InputStream;
import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class NumeroRevistaDTO {

    private Long idNumeroRevista;
    private Long idRevista;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaPublicacion;
    private String tituloNumero;
    private byte[] archivoPdf;

    public NumeroRevistaDTO() {
    }

    public NumeroRevistaDTO(Long idRevista, byte[] archivoPdf, String tituloRevista, LocalDate fechaPublicacion) {
        this.idRevista = idRevista;
        this.archivoPdf = archivoPdf;
        this.tituloNumero = tituloRevista;
        this.fechaPublicacion = fechaPublicacion;
    }

    
    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public byte[] getArchivoPdf() {
        return archivoPdf;
    }

    public void setArchivoPdf(byte[] archivoPdf) {
        this.archivoPdf = archivoPdf;
    }


    public Long getIdNumeroRevista() {
        return idNumeroRevista;
    }

    public void setIdNumeroRevista(Long idNumeroRevista) {
        this.idNumeroRevista = idNumeroRevista;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTituloNumero() {
        return tituloNumero;
    }

    public void setTituloNumero(String tituloNumero) {
        this.tituloNumero = tituloNumero;
    }

    
    
    
}
