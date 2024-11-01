/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.revistas;

import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.Servicios.Revistas.ServicioLlavesRevistas;
import com.mycompany.apprevistas.backend.util.AutenticadorJWT;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
@Path("revistasPorParametro")
//sudo systemctl start mysql

public class RevistasPorParametroResource {
    
    @GET
    @Path("{estadoRevista}/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarRevistasPorEstado(@PathParam("estadoRevista") String estado){
                                                                        
        ServicioLlavesRevistas service = new ServicioLlavesRevistas();
        List<LlaveRevistaDTO> revistas = service.obtenerRevistasPorEstado(estado);
        return Response.ok().entity(revistas).build();
    }
    
    @GET
    @Path("{idRevista}/llaveRevista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtnerLlaveRevista(@PathParam("idRevista") Long idRevista){
        ServicioLlavesRevistas service = new ServicioLlavesRevistas();
            Optional<LlaveRevistaDTO> llave = service.obtnerRevistaPorId(idRevista);
            if (llave.isPresent()) {
                return Response.ok(llave.get()).build();
            }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    
    
}
