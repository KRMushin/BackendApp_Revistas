/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.RevistasDTOs.Configs;

/**
 *
 * @author kevin-mushin
 */
public class EstadoRevistaDTO {
    
    private Long idRevista;
    private boolean esComentable;
    private boolean esLikeable;
    private boolean aceptaSuscripciones;
    private boolean anunciosBloqueados;
    
    private boolean usuarioSuscrito;

    private String tituloRevista;
    private String descripcion;
    
    public EstadoRevistaDTO() {
    }

    public boolean isEsComentable() {
        return esComentable;
    }

    public void setEsComentable(boolean esComentable) {
        this.esComentable = esComentable;
    }

    public boolean isEsLikeable() {
        return esLikeable;
    }

    public void setEsLikeable(boolean esLikeable) {
        this.esLikeable = esLikeable;
    }

    public boolean isAceptaSuscripciones() {
        return aceptaSuscripciones;
    }

    public void setAceptaSuscripciones(boolean aceptaSuscripciones) {
        this.aceptaSuscripciones = aceptaSuscripciones;
    }

    public boolean isAnunciosBloqueados() {
        return anunciosBloqueados;
    }

    public void setAnunciosBloqueados(boolean anunciosBloqueados) {
        this.anunciosBloqueados = anunciosBloqueados;
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

    public boolean isUsuarioSuscrito() {
        return usuarioSuscrito;
    }

    public void setUsuarioSuscrito(boolean usuarioSuscrito) {
        this.usuarioSuscrito = usuarioSuscrito;
    }
    
    

    @Override
    public String toString() {
        return "EstadoRevistaDTO{" + "idRevista=" + idRevista + ", esComentable=" + esComentable + ", esLikeable=" + esLikeable + ", aceptaSuscripciones=" + aceptaSuscripciones + ", anunciosBloqueados=" + anunciosBloqueados + '}';
    }
    
    
   
    
}
