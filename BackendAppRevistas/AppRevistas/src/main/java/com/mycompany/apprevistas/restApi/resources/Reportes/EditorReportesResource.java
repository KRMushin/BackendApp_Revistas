/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.Reportes;

import com.mycompany.apprevistas.backend.modelos.Reportes.FiltroEditorDTO;
import com.mycompany.apprevistas.backend.Servicios.Reportes.ServicioReportesEditor;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConSuscripciones;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConComentarios;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConCompras;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConLikes;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
@Path("ReportesEditor")
public class EditorReportesResource {
    
    @POST
    @Path("/reporteComentarios/revista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteComentariosRevista(FiltroEditorDTO filtro){
        ServicioReportesEditor serviceReportesEditor = new ServicioReportesEditor(); 
        List<RevistaConComentarios> reporte = serviceReportesEditor.obtnerRevistasConComentarios(filtro);
        
        if (reporte.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); // 204 
        }
        return Response.ok(reporte).build();
    }
    
    @POST
    @Path("/reporteSuscripciones/revista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteSuscripcionesARevista(FiltroEditorDTO filtro){
        ServicioReportesEditor serviceReportesEditor = new ServicioReportesEditor(); 
        List<RevistaConSuscripciones> reporte = serviceReportesEditor.obtnerRevistasConSuscripciones(filtro);
     if (reporte.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build(); // 204 
        }
            return Response.ok(reporte).build();
    }
    
    @POST
    @Path("/revistas/maspopulares")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteRevistasMasGustadas(FiltroEditorDTO filtro){
        ServicioReportesEditor serviceReportesEditor = new ServicioReportesEditor(); 
        List<RevistaConLikes> reporte = serviceReportesEditor.obtnerMasGustadas(filtro);
             if (reporte.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build(); // 204 
        }
            return Response.ok(reporte).build();
    }
    
    @POST
    @Path("/reporte/pagosRevistas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportePagosBloqueosAnuncios(FiltroEditorDTO filtro){
        ServicioReportesEditor serviceReportesEditor = new ServicioReportesEditor(); 
        List<RevistaConCompras> reporte = serviceReportesEditor.obtnerRevistasConCompras(filtro);
         if (reporte.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build(); // 204 
        }
            return Response.ok(reporte).build();
        
    }
    
}
