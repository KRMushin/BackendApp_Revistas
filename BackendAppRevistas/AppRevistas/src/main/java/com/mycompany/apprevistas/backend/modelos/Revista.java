/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class Revista {
    
//    private Long idArchivoRevista;
    private Long idRevista;
    private Long idCategoria;    
    private String tituloRevista;
    private String nombreAutor;
    private String descripcion;
    private Double costoMantenimiento;
    private Double costoBloqueosAnuncios;
    private LocalDate fechaCreacion;
    private String estadoRevista;
    private List<Long> idEtiquetas;
    private boolean bloquearAnuncios;
    
    public Revista() {
    
    }
    
    
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

    public List<Long> getIdEtiquetas() {
        return idEtiquetas;
    }

    public void setIdEtiquetas(List<Long> idEtiquetas) {
        this.idEtiquetas = idEtiquetas;
    }

    public boolean isBloquearAnuncios() {
        return bloquearAnuncios;
    }

    public void setBloquearAnuncios(boolean bloquearAnuncios) {
        this.bloquearAnuncios = bloquearAnuncios;
    }

    public Double getCostoBloqueosAnuncios() {
        return costoBloqueosAnuncios;
    }

    public void setCostoBloqueosAnuncios(Double costoBloqueosAnuncios) {
        this.costoBloqueosAnuncios = costoBloqueosAnuncios;
    }

    @Override
    public String toString() {
        return "Revista{" + "idRevista=" + idRevista + ", idCategoria=" + idCategoria + ", tituloRevista=" + tituloRevista + ", nombreAutor=" + nombreAutor + ", descripcion=" + descripcion + ", costoMantenimiento=" + costoMantenimiento + ", costoBloqueosAnuncios=" + costoBloqueosAnuncios + ", fechaCreacion=" + fechaCreacion + ", estadoRevista=" + estadoRevista + ", idEtiquetas=" + idEtiquetas + ", bloquearAnuncios=" + bloquearAnuncios + '}';
    }
    

    
}
