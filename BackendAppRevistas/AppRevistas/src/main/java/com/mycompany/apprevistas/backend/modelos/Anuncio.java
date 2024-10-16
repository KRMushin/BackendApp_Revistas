/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mycompany.apprevistas.backend.util.TipoAnuncio;
import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class Anuncio {
    
    private Long idAnuncio;
    private String nombreUsuario;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaCompra;
    private TipoAnuncio tipoAnuncio;
    private Double precioTotal;
    private String rutaImagenTexto;
    private String rutaVideo;
    private String rutaTexto;
    private boolean anuncioHabilitado;

    public Anuncio() {
    }
    
    /*  SETTERS Y GETTERS*/

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

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public TipoAnuncio getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(TipoAnuncio tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getRutaImagenTexto() {
        return rutaImagenTexto;
    }

    public void setRutaImagenTexto(String rutaImagenTexto) {
        this.rutaImagenTexto = rutaImagenTexto;
    }

    
    public String getRutaVideo() {
        return rutaVideo;
    }

    public void setRutaVideo(String rutaVideo) {
        this.rutaVideo = rutaVideo;
    }

    public String getRutaTexto() {
        return rutaTexto;
    }

    public void setRutaTexto(String rutaTexto) {
        this.rutaTexto = rutaTexto;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public boolean isAnuncioHabilitado() {
        return anuncioHabilitado;
    }

    public void setAnuncioHabilitado(boolean anuncioHabilitado) {
        this.anuncioHabilitado = anuncioHabilitado;
    }
    
    
    
}
