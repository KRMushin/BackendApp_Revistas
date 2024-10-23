/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources;

import com.mycompany.apprevistas.backend.Servicios.ServicioCarteraDigital;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.usuariosDTOs.CarteraDigitalDTO;
import com.mycompany.apprevistas.backend.util.AutenticadorJWT;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
@Path("/cartera/digital")
public class CarteraDigitalResource {
    
         private final AutenticadorJWT autenticadorJWT = new AutenticadorJWT();

         @GET
         @Path("{nombreUsuario}")
         @Produces(MediaType.APPLICATION_JSON)
         public Response obtenerCarteraDigital(@PathParam("nombreUsuario") String nombreUsuario,
                                                                      @Context HttpHeaders headerRequest){
             
             autenticadorJWT.validarTokenl(headerRequest); 
             ServicioCarteraDigital serviceCarteras  = new ServicioCarteraDigital();
             CarteraDigital cartera = serviceCarteras.obtenerCarteraActual(nombreUsuario);
             return Response.ok(cartera).build();
     }
     
     
         @PUT
         @Consumes(MediaType.APPLICATION_JSON)
         public Response recargarCartera(CarteraDigitalDTO carteraDigital, 
                                                            @Context HttpHeaders headerRequest){
         
             autenticadorJWT.validarTokenl(headerRequest); // este metodo lanza una exception
             ServicioCarteraDigital serviceCarteras  = new ServicioCarteraDigital();
             serviceCarteras.recargarSaldoCartera(carteraDigital);
             return Response.ok().build();
     }
     
     
         @POST
         @Consumes(MediaType.APPLICATION_JSON)
         public Response debitarCartera(CarteraDigitalDTO carteraDigital, 
                                                         @Context HttpHeaders headerRequest){
         
             autenticadorJWT.validarTokenl(headerRequest); // este metodo lanza una exception
             ServicioCarteraDigital serviceCarteras  = new ServicioCarteraDigital();
             serviceCarteras.debitarSaldoCartera(carteraDigital);
             return Response.ok().build();
     }
}
