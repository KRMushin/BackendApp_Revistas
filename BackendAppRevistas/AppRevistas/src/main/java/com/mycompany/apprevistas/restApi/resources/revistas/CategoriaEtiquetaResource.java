/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.revistas;

import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.Servicios.Revistas.ServicioCategoriaConEtiqueta;
import com.mycompany.apprevistas.backend.modelos.Categoria;
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
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
@Path("categorias")
public class CategoriaEtiquetaResource {

    private final AutenticadorJWT autenticadorJWT = new AutenticadorJWT();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCategoriasConEtiquetas(@Context HttpHeaders headerRequest){
        
        autenticadorJWT.validarTokenl(headerRequest); 
        
        ServicioCategoriaConEtiqueta service = new ServicioCategoriaConEtiqueta();
        Optional<List<Categoria>> categoriasEtiqueta = service.obtenerCategoriasConEtiqueta();
             if (categoriasEtiqueta.isEmpty()) {
                    throw new NotFoundException();
             }
        return Response.ok().entity(categoriasEtiqueta.get()).build();
    }
    
    @GET
    @Path("{idCategoria}/datosCategoria")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCategoriaConEtiqueta(@PathParam("idCategoria") Long idCategoria,
                                                                                @Context HttpHeaders headerRequest){
        
        autenticadorJWT.validarTokenl(headerRequest); 

        ServicioCategoriaConEtiqueta service = new ServicioCategoriaConEtiqueta();
        Optional<Categoria> categoriaEtiqueta = service.obtenerCategoria(idCategoria);
            if (categoriaEtiqueta.isEmpty()) {
                throw new NotFoundException();
            }
            
       return Response.ok().entity(categoriaEtiqueta.get()).build();
    }
}
