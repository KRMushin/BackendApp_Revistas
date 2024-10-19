/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources;

import com.mycompany.apprevistas.backend.Servicios.Anuncios.ServicioAnuncios;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasPreciosGlobales;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Servicios.ServicioPreciosGlobales;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.modelos.PrecioGlobal;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
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
@Path("preciosGlobales")
public class PreciosGlobalesResource {

        @GET
        @Produces(MediaType.APPLICATION_JSON)
     public Response obtenerTodos(){
         ConsultasPreciosGlobales service = new ConsultasPreciosGlobales();
         List<PrecioGlobal> anuncios= service.obtnerTodos();
         return Response.ok().entity(anuncios).build();
     }
        @PUT
        @Path("/{idPrecio}/actualizar")
        @Consumes(MediaType.APPLICATION_JSON)
     public Response actualizarPrecio(@PathParam("idPrecio") Long idPrecio, Double nuevoPrecio){
        ServicioPreciosGlobales service = new ServicioPreciosGlobales();
        service.actualizarPrecioGlobal(idPrecio, nuevoPrecio);
        return Response.ok().build();
     }
}
