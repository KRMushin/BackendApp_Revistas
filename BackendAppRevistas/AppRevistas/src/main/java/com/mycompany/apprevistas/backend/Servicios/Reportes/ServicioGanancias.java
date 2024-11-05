/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Reportes;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasAnuncios;
import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasComprasBloqueos;
import com.mycompany.apprevistas.backend.ConsultasPorParametros.ConsultasLlavesRevistas;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.constantes.Filtros.TipoReporteAnuncio;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltrosAdminDTO;
import com.mycompany.apprevistas.backend.modelos.Reportes.ReporteCostosRevista;
import com.mycompany.apprevistas.backend.modelos.Reportes.ReporteIngresosAnuncios;
import com.mycompany.apprevistas.backend.modelos.Reportes.ReporteIngresosEditores;
import java.util.ArrayList;

/**
 *
 * @author kevin-mushin
 */
public class ServicioGanancias {
    
    private ConsultasLlavesRevistas consultasLlaves;
    private ConsultasAnuncios consultasAnuncios;
    private ConsultasComprasBloqueos consultasBloqueos;

    public ServicioGanancias() {
        this.consultasAnuncios = new ConsultasAnuncios();
        this.consultasLlaves = new ConsultasLlavesRevistas();
        this.consultasBloqueos = new ConsultasComprasBloqueos();
    }
    
    

    public GananciasReporte obtnerGananciasSistema(FiltrosAdminDTO filtro) {
        if (filtro.getTipoReporte() != TipoReporteAnuncio.REPORTE_GANANCIAS) {
                throw new DatosInvalidosUsuarioException();
        }
        ReporteCostosRevista reporteCosto = obtenerReporteCostosSeguros(consultasLlaves.obtnerReporteCostoRevista(filtro));
        ReporteIngresosAnuncios reporteIngresoC = obtenerReporteIngresosAnunciosSeguros(consultasAnuncios.obtnerReporteAnuncios(filtro));
        ReporteIngresosEditores reporteIngresoE = obtenerReporteIngresosEditoresSeguros(consultasBloqueos.obtnerReporteEditor(filtro));

        GananciasReporte gananciasReporte = new GananciasReporte();
        gananciasReporte.setCostosRevista(reporteCosto);
        gananciasReporte.setIngresosAnuncios(reporteIngresoC);
        gananciasReporte.setIngresosEditores(reporteIngresoE);
        gananciasReporte.calcularTotales();
        
        return gananciasReporte;
    }
    
    private ReporteCostosRevista obtenerReporteCostosSeguros(ReporteCostosRevista reporte) {
        if (reporte == null) {
            ReporteCostosRevista reporteVacio = new ReporteCostosRevista();
            reporteVacio.setTotalCostosMantenimiento(0.0);
            reporteVacio.setRevistas(new ArrayList<>());
            return reporteVacio;
        }
        return reporte;
    }

    private ReporteIngresosAnuncios obtenerReporteIngresosAnunciosSeguros(ReporteIngresosAnuncios reporte) {
        if (reporte == null) {
            ReporteIngresosAnuncios reporteVacio = new ReporteIngresosAnuncios();
            reporteVacio.setTotalIngresos(0.0);
            reporteVacio.setAnuncios(new ArrayList<>());
            return reporteVacio;
        }
        return reporte;
    }

    private ReporteIngresosEditores obtenerReporteIngresosEditoresSeguros(ReporteIngresosEditores reporte) {
        if (reporte == null) {
            ReporteIngresosEditores reporteVacio = new ReporteIngresosEditores();
            reporteVacio.setTotalIngresos(0.0);
            reporteVacio.setCompras(new ArrayList<>());
            return reporteVacio;
        }
        return reporte;
    }


    
}

//        ReporteCostosRevista reporteCosto = consultasLlaves.obtnerReporteCostoRevista(filtro);
//        ReporteIngresosAnuncios reporteIngresoC = consultasAnuncios.obtnerReporteAnuncios(filtro);
//        ReporteIngresosEditores reporteIngresoE = consultasBloqueos.obtnerReporteEditor(filtro);

//         GananciasReporte ganancias = new GananciasReporte();
//        List<LlaveRevistaDTO> llaves = cll.obtnerRevistasPorEstados(RevistaEstadoVisibilidad.ACTIVA);
//        List<Anuncio> anuncios = ca.obtenerTodos();
//        List<CompraBloqueoDTO> compras = cb.listarCompras();
//        
//        Double costosRevistas = obtnerCostosEnRevistas(llaves);
//        Double gananciasEnAnuncios = obtnerGananciasAnuncios(anuncios);
//        Double gananciasEnCompras = obtnerGananciasEnBloqueos(compras);
//        
//        calcularTotalesSobreGanancias(costosRevistas, gananciasEnCompras, gananciasEnAnuncios, ganancias);
//        
//        return ganancias;
//        
//    }
//
//    private Double obtnerCostosEnRevistas(List<LlaveRevistaDTO> llaves) {
//            Double acumulado = 0.0;
//            if (llaves.isEmpty()) {
//                return acumulado;
//            }
//            for (LlaveRevistaDTO llave: llaves) {
//                  if (llave != null) {
//                    acumulado = acumulado + llave.getCostoMantenimiento();
//                }
//            }
//            return acumulado;
//    }
//
//    private Double obtnerGananciasEnBloqueos(List<CompraBloqueoDTO> compras) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    private Double obtnerGananciasAnuncios(List<Anuncio> anuncios) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    private Double calcularGanancias(Double costosRevistas, Double gananciasEnCompras, Double gananciasEnAnuncios) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    private void calcularTotalesSobreGanancias(Double costosRevistas, Double gananciasEnCompras, Double gananciasEnAnuncios, GananciasReporte ganancias) {
//                Double totalGanansias = calcularGanancias(costosRevistas, gananciasEnCompras, gananciasEnAnuncios);
//        Double totalCostos = calcularGanancias(costosRevistas, gananciasEnCompras, gananciasEnAnuncios);
//        Double totalIngresos = calcularGanancias(costosRevistas, gananciasEnCompras, gananciasEnAnuncios);