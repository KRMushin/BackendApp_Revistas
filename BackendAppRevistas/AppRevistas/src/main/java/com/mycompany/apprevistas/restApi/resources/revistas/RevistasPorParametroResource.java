/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.revistas;

import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.Servicios.Revistas.ServicioListarRevistas;
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

/**
 *
 * @author kevin-mushin
 */
@Path("revistasPorParametro")
//sudo systemctl start mysql

public class RevistasPorParametroResource {
        private final AutenticadorJWT autenticadorJWT = new AutenticadorJWT();
    
    @GET
    @Path("{estadoRevista}/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarRevistasPorEstado(@PathParam("estadoRevista") String estado,
                                                                         @Context HttpHeaders headerRequest){
                                                                        
        autenticadorJWT.validarTokenl(headerRequest);
            
        ServicioListarRevistas service = new ServicioListarRevistas();
        List<LlaveRevistaDTO> revistas = service.obtenerRevistasPorEstado(estado);
        return Response.ok().entity(revistas).build();
    }
    
    
}
