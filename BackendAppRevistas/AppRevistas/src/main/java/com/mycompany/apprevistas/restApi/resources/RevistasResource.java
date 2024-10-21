/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources;

import com.mycompany.apprevistas.backend.AnunciosDTOs.AnuncioDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.RevistaDTO;
import com.mycompany.apprevistas.backend.Servicios.Revistas.ServicioRevistas;
import com.mycompany.apprevistas.backend.modelos.Revista;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author kevin-mushin
 */
@Path("revistas")
public class RevistasResource {
    
    
    @GET 
    @Path("{nombreUsuario}/publicaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicacionesUsuario(@PathParam("nombreUsuario") String nombreUsuario){
         ServicioRevistas revistaService = new ServicioRevistas();
         List<LlaveRevistaDTO> revistas = revistaService.obtenerPublicacionesUsuario(nombreUsuario);
        return Response.ok().entity(revistas).build();
    }
    @GET 
    @Path("{idRevista}/datosRevista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDatosRevista(@PathParam("idRevista") Long idRevista){
         ServicioRevistas revistaService = new ServicioRevistas();
         Revista revista = revistaService.obtenerDatosRevista(idRevista); 
         return Response.ok().entity(revista).build();
    }
    
    @POST
    @Path("/publicar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response publicarRevista(RevistaDTO revistaDTO){
         ServicioRevistas revistaService = new ServicioRevistas();
         Revista revista = revistaService.publicarDatosRevista(revistaDTO);
        return Response.ok().entity(revista.getIdRevista()).build();
    }
    
    @POST
    @Path("{idRevista}/guardarPDF")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response guardarArchivoRevista(@PathParam("idRevista") Long idRevista,
                                                                      @FormDataParam("revistaPDF") InputStream revistaInputStream) {
        System.out.println("Recibido ID de revista: " + idRevista);
        System.out.println("Archivo recibido: " + revistaInputStream);
        ServicioRevistas revistasService = new ServicioRevistas();
        revistasService.guardarRevistaPDF(idRevista,revistaInputStream);
        return Response.ok().build();
    }
}
