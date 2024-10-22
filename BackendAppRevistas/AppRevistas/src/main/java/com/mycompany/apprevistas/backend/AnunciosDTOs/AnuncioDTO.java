/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.AnunciosDTOs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mycompany.apprevistas.backend.constantes.TipoAnuncio;
import jakarta.json.bind.annotation.JsonbTypeAdapter;

import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */

/*Error deserializando el JSON: Unrecognized field "diasDuracion" (class com.mycompany.apprevistas.backend.AnunciosDTOs.AnuncioDTO), not marked as ignorable (2 known properties: "nombreUsuario", "tipoAnuncio"])
 at [Source: (String)"{"nombreUsuario":"comprador7","tipoAnuncio":"TEXTO","diasDuracion":14,"textoAnuncio":"HOLA ESTE ES UN TEXTO"}";*/
public class AnuncioDTO {
    
    private String nombreUsuario;
    private TipoAnuncio tipoAnuncio;
    private int diasDuracion;
    private String textoAnuncio;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaPago;
    
    
    public AnuncioDTO() {
    }
    /*getters y setters area */
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public TipoAnuncio getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(TipoAnuncio tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public int getDiasDuracion() {
        return diasDuracion;
    }

    public void setDiasDuracion(int diasDuracion) {
        this.diasDuracion = diasDuracion;
    }

    public String getTextoAnuncio() {
        return textoAnuncio;
    }

    public void setTextoAnuncio(String textoAnuncio) {
        this.textoAnuncio = textoAnuncio;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }
    
    public boolean esValido() {
    return nombreUsuario != null && !nombreUsuario.isEmpty()
            && tipoAnuncio != null
            && diasDuracion > 0
            && fechaPago != null;
    }

    
    /*para depuracion */
    @Override
    public String toString() {
        return "AnuncioDTO{" + "nombreUsuario=" + nombreUsuario + ", tipoAnuncio=" + tipoAnuncio + ", diasDuracion=" + diasDuracion + ", textoAnuncio=" + textoAnuncio + ", fechaPago=" + fechaPago + '}';
    }

    
    
    
}
