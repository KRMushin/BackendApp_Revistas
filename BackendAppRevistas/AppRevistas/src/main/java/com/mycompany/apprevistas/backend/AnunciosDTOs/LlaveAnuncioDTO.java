/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.AnunciosDTOs;

import com.mycompany.apprevistas.backend.constantes.TipoAnuncio;

/**
 *
 * @author kevin-mushin
 */
public class LlaveAnuncioDTO {
    
    private Long idAnuncio;
    private TipoAnuncio tipoAnuncio;
    private String contenidoTexto;
    private String rutaVideo;
    private String rutaTextoImagen;

    public LlaveAnuncioDTO() {
    }

    public Long getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Long idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getContenidoTexto() {
        return contenidoTexto;
    }

    public void setContenidoTexto(String contenidoTexto) {
        this.contenidoTexto = contenidoTexto;
    }

    public TipoAnuncio getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(TipoAnuncio tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getRutaVideo() {
        return rutaVideo;
    }

    public void setRutaVideo(String rutaVideo) {
        this.rutaVideo = rutaVideo;
    }

    public String getRutaTextoImagen() {
        return rutaTextoImagen;
    }

    public void setRutaTextoImagen(String rutaTextoImagen) {
        this.rutaTextoImagen = rutaTextoImagen;
    }
    
    
    
}
