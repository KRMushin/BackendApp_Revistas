/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.Anuncios;

import com.mycompany.apprevistas.backend.AnunciosDTOs.LlaveAnuncioDTO;
import com.mycompany.apprevistas.backend.Servicios.Anuncios.ServicioDespliegueAnuncios;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Optional;


/**
 *
 * @author kevin-mushin
 */
@Path("anunciosDespliegue")
public class AnunciosDespliegueResource {
    
        @GET
        @Path("anuncioAleatorio")
        @Produces(MediaType.APPLICATION_JSON)  
        public Response anuncioAleatorio() {
            ServicioDespliegueAnuncios service = new ServicioDespliegueAnuncios();
            Optional<LlaveAnuncioDTO>  anuncioAleatorio = service.obtnerAnuncioAleatorio();
            if (anuncioAleatorio.isPresent()) {
                return Response.ok(anuncioAleatorio.get()).build(); // anuncioAleatorio.get()
            }
            return Response.status(Response.Status.NO_CONTENT).build();  
        }
        
        @GET
        @Path("{idAnuncio}/archivo")
         @Produces(MediaType.APPLICATION_OCTET_STREAM)  // Para archivos en general
        public Response obtnerArchivoAnuncio(@PathParam("idAnuncio") Long idAnuncio) {
            
            ServicioDespliegueAnuncios service = new ServicioDespliegueAnuncios();
            Optional<InputStream> anuncioArchivo = service.obtenerArchivoAnuncio(idAnuncio);
                    if (anuncioArchivo.isPresent()) {
                        return Response.ok(anuncioArchivo.get()).build();
                    }
            return Response.status(Response.Status.NO_CONTENT).build();  
        }
    
}
