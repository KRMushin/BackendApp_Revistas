/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Reportes;

import com.mycompany.apprevistas.backend.modelos.Anuncio;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ReporteIngresosAnuncios {

    private Double totalIngresos;
    private List<Anuncio> anuncios;

    public ReporteIngresosAnuncios() {
        this.totalIngresos = 0.0;
    }

    
    public Double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(Double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }


}
