/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos;

import java.io.InputStream;
import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class Revista {
    
//    private Long idArchivoRevista;
    private Long idRevista;
    private Long idCategoria;
    private String rutaRevista;
    
    private String tituloRevista;
    private String nombreAutor;
    private String descripcion;
    private Double costoMantenimiento;
    private LocalDate fechaCreacion;
    private String estadoRevista;
    private int numeroLikes;
    
    private boolean revistaComentable;
    private boolean revistaLikeable;
    private boolean aceptaSuscripciones;
    private boolean anunciosBloqueados;

    public Revista() {
    
    }
    
    
//    private byte[] pdfRevistaLectura; // archivo para leer el pdf 
//    private InputStream pdfRevista; // archivo para almacenar el pdf
    /*SETTERS Y GETTERS*/
    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCostoMantenimiento() {
        return costoMantenimiento;
    }

    public void setCostoMantenimiento(Double costoMantenimiento) {
        this.costoMantenimiento = costoMantenimiento;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstadoRevista() {
        return estadoRevista;
    }

    public void setEstadoRevista(String estadoRevista) {
        this.estadoRevista = estadoRevista;
    }

    public boolean isRevistaComentable() {
        return revistaComentable;
    }

    public void setRevistaComentable(boolean revistaComentable) {
        this.revistaComentable = revistaComentable;
    }

    public boolean isRevistaLikeable() {
        return revistaLikeable;
    }

    public void setRevistaLikeable(boolean revistaLikeable) {
        this.revistaLikeable = revistaLikeable;
    }

    public boolean isAceptaSuscripciones() {
        return aceptaSuscripciones;
    }

    public void setAceptaSuscripciones(boolean aceptaSuscripciones) {
        this.aceptaSuscripciones = aceptaSuscripciones;
    }

    public int getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(int numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public String getRutaRevista() {
        return rutaRevista;
    }

    public void setRutaRevista(String rutaRevista) {
        this.rutaRevista = rutaRevista;
    }

    public boolean isAnunciosBloqueados() {
        return anunciosBloqueados;
    }

    public void setAnunciosBloqueados(boolean anunciosBloqueados) {
        this.anunciosBloqueados = anunciosBloqueados;
    }
    
    
}
