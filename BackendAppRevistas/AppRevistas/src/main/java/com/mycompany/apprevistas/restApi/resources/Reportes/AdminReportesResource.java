/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.Reportes;

import com.mycompany.apprevistas.backend.Servicios.Reportes.GananciasReporte;
import com.mycompany.apprevistas.backend.Servicios.Reportes.ServicioGanancias;
import com.mycompany.apprevistas.backend.Servicios.Reportes.ServicioReportesAdmin;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltrosAdminDTO;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConComentarios;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConSuscripciones;
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
@Path("reportesAdmin")
public class AdminReportesResource {
    
    @POST
    @Path("/ganaciasSistema")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteGanancias(FiltrosAdminDTO filtro){
        ServicioGanancias serviceAdmin = new ServicioGanancias();
        GananciasReporte ganancias = serviceAdmin.obtnerGananciasSistema(filtro);
        return Response.ok(ganancias).build();
        
    }
    @POST
    @Path("/anunciosComprados")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteAnunciosComprados(FiltrosAdminDTO filtro){
        ServicioReportesAdmin serviceAdmin = new ServicioReportesAdmin();
        List<Anuncio> reporte = serviceAdmin.obtenerAnunciosComprados(filtro);
        if (reporte.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); // 204 
        }
        return Response.ok(reporte).build();
    }
@POST
    @Path("/gananciasAnunciantes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response gananciasPorAnunciante(FiltrosAdminDTO filtro){
        ServicioReportesAdmin serviceAdmin = new ServicioReportesAdmin();
        List<Anuncio> reporte = serviceAdmin.obtenerGananciasAnuncios(filtro);
        if (reporte.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); // 204 
        }
        return Response.ok(reporte).build();

    }
@POST
    @Path("/revistasPopulares")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteRevistasPopulares(FiltrosAdminDTO filtro){
         ServicioReportesAdmin serviceAdmin = new ServicioReportesAdmin();
        List<RevistaConSuscripciones> reporte = serviceAdmin.obtnerMasPopulares(filtro);
        if (reporte.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); // 204 
        }
        return Response.ok(reporte).build();

    }
@POST
    @Path("/revistasComentadas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteRevistasMasComentadas(FiltrosAdminDTO filtro){
         ServicioReportesAdmin serviceAdmin = new ServicioReportesAdmin();
        List<RevistaConComentarios> reporte = serviceAdmin.obtnerMasComentadas(filtro);
        if (reporte.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); // 204 
        }
        return Response.ok(reporte).build();
    }
//    @GET
//    @Path("")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response reporteAnunciosPorAnunciante(){
//                        ServicioReportesAdmin serviceAdmin = new ServicioReportesAdmin();
//        
//    return null;
//    }
}
