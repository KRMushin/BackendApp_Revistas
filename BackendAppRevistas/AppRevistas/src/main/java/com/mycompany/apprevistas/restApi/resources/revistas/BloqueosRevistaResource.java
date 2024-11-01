/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.revistas;

import com.mycompany.apprevistas.backend.Excepciones.DineroInsuficienteException;
import com.mycompany.apprevistas.backend.RevistasDTOs.CompraBloqueoDTO;
import com.mycompany.apprevistas.backend.Servicios.Revistas.ServicioCostosRevistas;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author kevin-mushin
 */
@Path("bloqueosRevista")
public class BloqueosRevistaResource {
    
     @POST
     @Path("/comprarBloqueo")
     @Consumes(MediaType.APPLICATION_JSON)
     public Response comprarBloqueoRevista(CompraBloqueoDTO compraBloqueo){
                 ServicioCostosRevistas service = new ServicioCostosRevistas();         
         try {
                 service.realizarCompraBloqueo(compraBloqueo);
                 return Response.ok().build();
         } catch (DineroInsuficienteException ex) {
             return Response.status(Response.Status.CONFLICT).build();
         }
     }
}
