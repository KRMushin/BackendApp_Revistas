/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.revistas;

import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoConfigRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.NuevoCostoDTO;
import com.mycompany.apprevistas.backend.Servicios.Revistas.ServicioCostosRevistas;
import com.mycompany.apprevistas.backend.Servicios.Revistas.ServicioRevistas;
import com.mycompany.apprevistas.backend.util.AutenticadorJWT;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author kevin-mushin
 */
@Path("estadoRevista")
public class EstadosRevistaResource {

    // estados se refiere a datos como suscripciones activas etc
    @GET
    @Path("{idRevista}/obtenerEstados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEstadosRevista(@PathParam("idRevista") Long idRevista){
        

            ServicioRevistas service = new ServicioRevistas();
            EstadoRevistaDTO estado = service.obtenerEstadosRevista(idRevista);
            return Response.ok().entity(estado).build();
    }
    // metodo que actualiza estaos como comentarios , likes , etc
    @PUT
    @Path("{idRevista}/actualizarEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarEstadoConfiguracionRevista(EstadoConfigRevistaDTO estado){
         
        ServicioRevistas service = new ServicioRevistas();
        service.actualizarEstadoRevista(estado);
        return Response.ok().build();
    }
     
    @PUT
    @Path("/actualizarCostoRevista")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarCostoRevista(NuevoCostoDTO nuevoCosto){

        ServicioCostosRevistas service = new ServicioCostosRevistas();
        service.actualizarCostoRevista(nuevoCosto);
        return Response.ok().build();
    }

}
