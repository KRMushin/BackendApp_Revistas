/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.Suscripciones;

import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.Servicios.Suscripciones.ServicioSuscripciones;
import com.mycompany.apprevistas.backend.modelos.Suscripcion;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
@Path("suscripciones")
public class SuscripcionesResource {
    
    @POST
    @Path("/suscribirse/revista")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response suscribirseARevista(Suscripcion suscripcion){
        ServicioSuscripciones service = new ServicioSuscripciones();
            service.realizarSuscripcion(suscripcion);
            return Response.ok().build();
    }
    
    
    @GET
    @Path("/revistas/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSuscripcionesUsuario(
                                                                                @PathParam("nombreUsuario") String nombreUsuario){
        // se  retorna este dto ya que este contiene toda la informacion de configuracion de la revistaa , ademas
        ServicioSuscripciones service = new ServicioSuscripciones();
        List<LlaveRevistaDTO> revistasSuscritas  = service.obtnerRevistasSuscritas(nombreUsuario);
        return Response.ok(revistasSuscritas).build();
    }
    
    
}
