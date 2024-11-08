/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Reportes;

import com.mycompany.apprevistas.backend.modelos.Reportes.AnuncioConVisualizaciones;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author kevin-mushin
 */
public class ServicioExportaciones {

    
    public ByteArrayOutputStream generarReporteEfectividadAnuncios(List<AnuncioConVisualizaciones> anuncios) throws JRException {
        // Cargar el archivo .jasper del reporte
        InputStream reportStream = getClass().getResourceAsStream("/reportes/rep12.jasper");
        if (reportStream == null) {
            throw new JRException("No se encontró el archivo de reporte /reportes/reporte1.jasper");
        }
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
        
        

        // Crear el DataSource a partir de la lista de anuncios
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(anuncios);

        // Llenar el reporte con el DataSource
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        

        // Exportar el reporte a un ByteArrayOutputStream como PDF
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, pdfOutputStream);

        return pdfOutputStream;
    }


public ByteArrayOutputStream exportarReporteGanancias(GananciasReporte ganancias) throws JRException {
    ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
    

    try {
        System.out.println("Iniciando exportación del reporte de ganancias...");

        InputStream reporteStream = getClass().getResourceAsStream("/reportes/ReporteGanancias.jasper");
        if (reporteStream == null) {
            throw new JRException("No se encontró el archivo ReporteGanancias.jasper");
        }
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reporteStream);

        JRBeanCollectionDataSource comprasDataSource = new JRBeanCollectionDataSource(ganancias.getIngresosEditores().getCompras());
        JRBeanCollectionDataSource anunciosDataSource = new JRBeanCollectionDataSource(ganancias.getIngresosAnuncios().getAnuncios());
        JRBeanCollectionDataSource revistasDataSource = new JRBeanCollectionDataSource(ganancias.getCostosRevista().getRevistas());

        JRBeanCollectionDataSource mainDataSource = new JRBeanCollectionDataSource(Collections.singletonList(ganancias));

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("paramCompras", comprasDataSource);
        parametros.put("paramAnuncios", anunciosDataSource);
        parametros.put("paramRevistas", revistasDataSource);
        
        parametros.put("totalIngresos", ganancias.getTotalIngresos());
        parametros.put("totalIngresosAnuncios", ganancias.getIngresosAnuncios().getTotalIngresos());
        parametros.put("totalCostosMantenimiento", ganancias.getCostosRevista().getTotalCostosMantenimiento());
        parametros.put("totalGanancia", ganancias.getTotalGanancia());
        
        parametros.put("SUBREPORT_DIR", getClass().getResource("/reportes/").toString());

        System.out.println("Llenando el reporte...");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, mainDataSource);

        System.out.println("Exportando el reporte a PDF...");
        JasperExportManager.exportReportToPdfStream(jasperPrint, pdfOutputStream);

        System.out.println("Exportación completada.");
    } catch (JRException e) {
        System.err.println("Error de JasperReports: " + e.getMessage());
        e.printStackTrace();
        throw new JRException("Error al generar el reporte de ganancias", e);
    }

    return pdfOutputStream;
}



        
        
        
    
}
