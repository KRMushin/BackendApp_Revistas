/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class LikeRevista {
    
    private Long idLike;
    private Long idRevista;
    private String nombreUsuario;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaLike;
    private boolean likeHecho;

    public LikeRevista() {
    }

    public Long getIdLike() {
        return idLike;
    }

    public LikeRevista(Long idLike, boolean likeHecho) {
        this.idLike = idLike;
        this.likeHecho = likeHecho;
    }

        
    
    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public LocalDate getFechaLike() {
        return fechaLike;
    }

    public void setFechaLike(LocalDate fechaLike) {
        this.fechaLike = fechaLike;
    }

    public boolean isLikeHecho() {
        return likeHecho;
    }

    public void setLikeHecho(boolean likeHecho) {
        this.likeHecho = likeHecho;
    }
    
    public boolean esLikeValido() {
    return idRevista != null &&
           nombreUsuario != null && !nombreUsuario.isEmpty() ;
}

    
    
}
