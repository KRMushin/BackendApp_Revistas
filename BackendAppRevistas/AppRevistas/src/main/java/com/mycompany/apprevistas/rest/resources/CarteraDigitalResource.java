/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.rest.resources;

import com.mycompany.apprevistas.backend.Servicios.ServicioCarteraDigital;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.usuariosDTOs.CarteraDigitalDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
@Path("cartera/digital")
public class CarteraDigitalResource {
    
     @GET
     @Path("{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
     public Response obtenerCarteraDigital(@PathParam("nombreUsuario") String nombreUsuario){
         ServicioCarteraDigital serviceCarteras  = new ServicioCarteraDigital();
         CarteraDigital cartera = serviceCarteras.obtenerCarteraActual(nombreUsuario);
         return Response.ok(cartera).build();
     }
     
     @PUT
    @Consumes(MediaType.APPLICATION_JSON)
     public Response recargarCartera(CarteraDigitalDTO carteraDigital){
         ServicioCarteraDigital serviceCarteras  = new ServicioCarteraDigital();
         serviceCarteras.recargarSaldoCartera(carteraDigital);
         return Response.ok().build();
     }
     
     @POST
    @Consumes(MediaType.APPLICATION_JSON)
     public Response debitarCartera(CarteraDigitalDTO carteraDigital){
         ServicioCarteraDigital serviceCarteras  = new ServicioCarteraDigital();
         serviceCarteras.debitarSaldoCartera(carteraDigital);
         return Response.ok().build();
     }
}
