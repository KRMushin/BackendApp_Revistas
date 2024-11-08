/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Reportes;

import com.mycompany.apprevistas.backend.RevistasDTOs.CompraBloqueoDTO;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ReporteIngresosEditores {
    
    private Double totalIngresos;
    private List<CompraBloqueoDTO> compras;

    public ReporteIngresosEditores() {
        this.totalIngresos = 0.0;
    }

    public Double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(Double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public List<CompraBloqueoDTO> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraBloqueoDTO> compras) {
        this.compras = compras;
    }
    
    
    
    
}
