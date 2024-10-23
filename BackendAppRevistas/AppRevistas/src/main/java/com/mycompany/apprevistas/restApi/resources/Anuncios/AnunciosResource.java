/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.Anuncios;

import com.mycompany.apprevistas.backend.Excepciones.DineroInsuficienteException;
import com.mycompany.apprevistas.backend.Servicios.Anuncios.ServicioAnuncios;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
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
import java.io.InputStream;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author kevin-mushin
 */
@Path("anuncios")
public class AnunciosResource {
    
        private final AutenticadorJWT autenticadorJWT = new AutenticadorJWT();
    
        @GET
        @Path("{nombreUsuario}")
        @Produces(MediaType.APPLICATION_JSON)
     public Response obtenerAnunciosUsuariol(@PathParam("nombreUsuario") String nombreUsuario,
                                                                           @Context HttpHeaders headerRequest){

            autenticadorJWT.validarTokenl(headerRequest); 

            ServicioAnuncios service = new ServicioAnuncios();
             List<Anuncio> anuncios = service.obtenerAnunciosUsuario(nombreUsuario);
             return Response.ok().entity(anuncios).build();
     }
    
        @POST
        @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response publicarAnuncio(@FormDataParam("anuncioDTO") String anuncioDTOJson,
                                                            @FormDataParam("archivo") InputStream archivoInputStream,
                                                            @FormDataParam("nombreArchivo") String nombreArchivo,
                                                            @Context HttpHeaders headerRequest) {

        try {
               autenticadorJWT.validarTokenl(headerRequest); 
               
                ServicioAnuncios service = new ServicioAnuncios();
                service.publicarAnuncio(anuncioDTOJson, archivoInputStream, nombreArchivo);
            return Response.ok()
                    .build();
        } catch (DineroInsuficienteException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } 
    }

    @PUT
    @Path("/{id}/actualizar")
    public Response actualizarAnuncio(@PathParam("id") Long idAnuncio, boolean habilitado,
                                                                @Context HttpHeaders headerRequest){

        autenticadorJWT.validarTokenl(headerRequest); 

        ServicioAnuncios service = new ServicioAnuncios();
        service.actualizarEstadoAnuncio(idAnuncio, habilitado);
        return Response.ok().build();
    }
    
}
