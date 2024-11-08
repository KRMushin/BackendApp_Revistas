/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Exportaciones;

import com.mycompany.apprevistas.backend.modelos.Reportes.AnuncioConVisualizaciones;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ExpEfectividadAnuncios {
    
    private String nombreUsuario;
    private List<AnuncioConVisualizaciones> anuncios;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<AnuncioConVisualizaciones> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<AnuncioConVisualizaciones> anuncios) {
        this.anuncios = anuncios;
    }
    
    
    
}
