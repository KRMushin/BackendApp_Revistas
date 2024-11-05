/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Reportes;

import com.mycompany.apprevistas.backend.modelos.Revista;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ReporteCostosRevista {
    
    private Double totalCostosMantenimiento;
    private List<Revista> revistas;

    public ReporteCostosRevista() {
        this.totalCostosMantenimiento = 0.0;
    }

    public Double getTotalCostosMantenimiento() {
        return totalCostosMantenimiento;
    }

    public void setTotalCostosMantenimiento(Double totalCostosMantenimiento) {
        this.totalCostosMantenimiento = totalCostosMantenimiento;
    }

    public List<Revista> getRevistas() {
        return revistas;
    }

    public void setRevistas(List<Revista> revistas) {
        this.revistas = revistas;
    }
    
    
}
