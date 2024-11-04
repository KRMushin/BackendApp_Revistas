/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.ComentariosYLikes;

import com.mycompany.apprevistas.backend.Servicios.ComentariosYLikes.ServicioComentarios;
import com.mycompany.apprevistas.backend.modelos.Comentario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
@Path("comentarios")
public class ComentariosResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/publicarComentario")
    public Response publicarComentarioRevista(Comentario comentario){
        ServicioComentarios serviceComentarios = new ServicioComentarios();
            serviceComentarios.publicarComentario(comentario);
            return Response.ok().build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/usuarioComentarios/{nombreUsuario}/{idRevista}")
    public Response obtnerComentariosUsuario(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("idRevista") Long idRevista){
        ServicioComentarios serviceComentarios = new ServicioComentarios();
            // optional para obtener los comentarios hechos
            Optional<List<Comentario>> comentariosHechos = serviceComentarios.obtnerComentariosHechos(nombreUsuario,idRevista);
            if (comentariosHechos.isPresent()) {
            return Response.ok(comentariosHechos.get()).build();
        }
            return Response.ok(Optional.empty()).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/comentariosRevista/{idRevista}")
    public Response obtnerComentariosDeRevista(@PathParam("idRevista") Long idRevista){
            ServicioComentarios serviceComentarios = new ServicioComentarios();
            //comentarios de revista
            Optional<List<Comentario>> comentariosRevista = serviceComentarios.obtnerComentariosRevista(idRevista);
            if (comentariosRevista.isPresent()) {
            return Response.ok(comentariosRevista.get()).build();
        }
            return Response.ok(Optional.empty()).build();
    }
    
    @DELETE
    @Path("/eliminarComentario/{idComentario}")
    public Response eliminarComentario(@PathParam("idComentario") Long idComentario){
            ServicioComentarios serviceComentarios = new ServicioComentarios();
            boolean seElimino = serviceComentarios.eliminarComentario(idComentario);
            return Response.ok(seElimino).build();
    }
    
}
