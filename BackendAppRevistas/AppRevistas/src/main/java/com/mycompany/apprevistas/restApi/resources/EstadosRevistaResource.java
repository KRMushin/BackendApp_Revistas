/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources;

import com.mycompany.apprevistas.backend.RevistasDTOs.EstadoConfigRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.EstadoRevistaDTO;
import com.mycompany.apprevistas.backend.Servicios.Revistas.ServicioRevistas;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author kevin-mushin
 */
@Path("estadoRevista")
public class EstadosRevistaResource {
    
    @GET
    @Path("{idRevista}/obtenerEstados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEstadosRevista(@PathParam("idRevista") Long idRevista){
        ServicioRevistas service = new ServicioRevistas();
        EstadoRevistaDTO estado = service.obtenerEstadosRevista(idRevista);
        return Response.ok().entity(estado).build();
    }
    
    @PUT
    @Path("{idRevista}/actualizarEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarRevista(EstadoConfigRevistaDTO estado){
        ServicioRevistas service = new ServicioRevistas();
        service.actualizarEstadoRevista(estado);
        return Response.ok().build();
    }
}
