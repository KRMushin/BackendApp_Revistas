/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.Reportes;

import com.mycompany.apprevistas.backend.Servicios.Reportes.GananciasReporte;
import com.mycompany.apprevistas.backend.Servicios.Reportes.ServicioExportaciones;
import com.mycompany.apprevistas.backend.modelos.Exportaciones.ExpEfectividadAnuncios;
import com.mycompany.apprevistas.backend.modelos.Reportes.AnuncioConVisualizaciones;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author kevin-mushin
 */
@Path("exportarAdmin")
public class ExportarReporteResource {
    
 @POST
    @Path("/efectividadAnuncios")
    @Produces("application/pdf")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response exportarReporte(List<AnuncioConVisualizaciones> efectividades) {
        try {
            ServicioExportaciones service = new ServicioExportaciones();
            ByteArrayOutputStream pdfOutputStream = service.generarReporteEfectividadAnuncios(efectividades);

            // Retorna el PDF como respuesta
            return Response.ok(pdfOutputStream.toByteArray()).header("Content-Disposition", "inline; filename=\"reporte_efectividad_anuncio.pdf\"").build();
        } catch (JRException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al generar el reporte").build();
        }
    }
 @POST
    @Path("/gananciasSistema")
    @Produces("application/pdf")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response exportarReporte(GananciasReporte ganancias) {
        try {
            ServicioExportaciones service = new ServicioExportaciones();
            ByteArrayOutputStream pdfOutputStream = service.exportarReporteGanancias(ganancias);

            // Retorna el PDF como respuesta
            return Response.ok(pdfOutputStream.toByteArray()).header("Content-Disposition", "inline; filename=\"reporte_efectividad_anuncio.pdf\"").build();
        } catch (JRException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al generar el reporte").build();
        }
    }
}
