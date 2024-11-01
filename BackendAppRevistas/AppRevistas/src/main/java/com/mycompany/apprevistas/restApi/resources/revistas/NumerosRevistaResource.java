/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.revistas;

import com.mycompany.apprevistas.backend.RevistasDTOs.NumeroRevistaDTO;
import com.mycompany.apprevistas.backend.Servicios.Revistas.ServicioNumerosRevista;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author kevin-mushin
 */
@Path("numerosRevista")
public class NumerosRevistaResource {
    
    @POST
    @Path("/publicarNumero/{idRevista}") // metodo para publicar la revista
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response publicarNumeroRevista(@PathParam("idRevista") Long idRevista,
                                                                        @FormDataParam("file") InputStream numeroRevista,
                                                                        @FormDataParam("tituloNumero") String tituloNumero,   // Título del número
                                                                        @FormDataParam("fechaPublicacion") String fechaPublicacion){
        
            ServicioNumerosRevista service = new ServicioNumerosRevista();
            Optional<NumeroRevistaDTO> numero = service.publicarNumeroRevista(idRevista, numeroRevista, tituloNumero, fechaPublicacion);
            if (numero.isPresent()) {
                return Response.ok().build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
    
    @GET
    @Path("/obtenerTodos/{idRevista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtnerNumerosRevista(@PathParam("idRevista") Long idRevista){
            ServicioNumerosRevista service = new ServicioNumerosRevista();
            List<NumeroRevistaDTO> numerosRevista = service.obtnerTodosLosNumeros(idRevista);
            return Response.ok(numerosRevista).build();
    }
    
    @GET
    @Path("{idNumeroRevista}/archivoPDF")
    @Produces("application/pdf") 
    public Response obtnerArchivoNumero(@PathParam("idNumeroRevista") Long idNumeroRevista){
            ServicioNumerosRevista service = new ServicioNumerosRevista();
            InputStream pdf = service.obtnerArchivoNumero(idNumeroRevista);
 byte[] contenidoArchivo;

    try {
        contenidoArchivo = pdf.readAllBytes();
        System.out.println("Tamaño del archivo: " + contenidoArchivo.length + " bytes");
    } catch (IOException ex) {
        return Response.serverError().build();
    }

    return Response.ok(contenidoArchivo)
                   .header("Content-Disposition", "inline; filename=numero_" + idNumeroRevista + ".pdf")
                   .header("Content-Type", "application/pdf")
                   .build();
    }
}
