/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Reportes;

import com.mycompany.apprevistas.backend.modelos.Reportes.ReporteCostosRevista;
import com.mycompany.apprevistas.backend.modelos.Reportes.ReporteIngresosAnuncios;
import com.mycompany.apprevistas.backend.modelos.Reportes.ReporteIngresosEditores;

/**
 *
 * @author kevin-mushin
 */
public class GananciasReporte {
    
        private ReporteIngresosEditores ingresosEditores;
        private ReporteIngresosAnuncios ingresosAnuncios;
        private ReporteCostosRevista costosRevista;
        
        private double totalIngresos;
        private double totalCostos;
        private double totalGanancia;
        

    public GananciasReporte() {
    }

    public ReporteIngresosEditores getIngresosEditores() {
        return ingresosEditores;
    }

    public void setIngresosEditores(ReporteIngresosEditores ingresosEditores) {
        this.ingresosEditores = ingresosEditores;
    }

    public ReporteIngresosAnuncios getIngresosAnuncios() {
        return ingresosAnuncios;
    }

    public void setIngresosAnuncios(ReporteIngresosAnuncios ingresosAnuncios) {
        this.ingresosAnuncios = ingresosAnuncios;
    }

    public ReporteCostosRevista getCostosRevista() {
        return costosRevista;
    }

    public void setCostosRevista(ReporteCostosRevista costosRevista) {
        this.costosRevista = costosRevista;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public double getTotalCostos() {
        return totalCostos;
    }

    public void setTotalCostos(double totalCostos) {
        this.totalCostos = totalCostos;
    }

    public double getTotalGanancia() {
        return totalGanancia;
    }

    public void setTotalGanancia(double totalGanancia) {
        this.totalGanancia = totalGanancia;
    }
    
    
    
    public void calcularTotales() {
        totalIngresos = 0.0;
        if (ingresosEditores != null) {
            totalIngresos += ingresosEditores.getTotalIngresos();
        }
        if (ingresosAnuncios != null) {
            totalIngresos += ingresosAnuncios.getTotalIngresos();
        }
        totalCostos = 0.0;
        if (costosRevista != null) {
            totalCostos = costosRevista.getTotalCostosMantenimiento();
        }
        totalGanancia = totalIngresos - totalCostos;
    }
}
