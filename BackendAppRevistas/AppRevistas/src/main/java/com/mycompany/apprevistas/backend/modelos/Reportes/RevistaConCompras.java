/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Reportes;

import com.mycompany.apprevistas.backend.RevistasDTOs.CompraBloqueoDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RevistaConCompras {
    
    private Double totalPagos;
    private String nombreAutor;
    private String tituloRevista;
    private CompraBloqueoDTO compra;

    public RevistaConCompras() {
    }
    
    

    public Double getTotalPagos() {
        return totalPagos;
    }

    public void setTotalPagos(Double totalPagos) {
        this.totalPagos = totalPagos;
    }

    public CompraBloqueoDTO getCompra() {
        return compra;
    }

    public void setCompra(CompraBloqueoDTO compra) {
        this.compra = compra;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }
    
    
    
}
