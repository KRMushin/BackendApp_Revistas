/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.ComentariosYLikes;

import com.mycompany.apprevistas.backend.Servicios.ComentariosYLikes.ServicioLikes;
import com.mycompany.apprevistas.backend.modelos.LikeRevista;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
@Path("revistasLikes")
public class LikesResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/postearLike")
    public Response postearLikeRevista(LikeRevista likeRevista){
            ServicioLikes serviceLikes = new ServicioLikes();
                serviceLikes.postearLike(likeRevista);
                return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/actualizarLike/{idLike}/{estado}")
    public Response actualizarLike(@PathParam("idLike") Long idLike, @PathParam("estado") boolean estado){
            ServicioLikes serviceLikes = new ServicioLikes();
                serviceLikes.actualizarLike(idLike,estado);
            return Response.ok().build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtner/{nombreUsuario}/{idRevista}")
    public Response obtnerPorUsuario(@PathParam("nombreUsuario") String nombreUsuario,
                                                              @PathParam("idRevista") Long idRevista){
        
            ServicioLikes serviceLikes = new ServicioLikes();
            Optional<LikeRevista> like = serviceLikes.obtnerPorUsuario(nombreUsuario, idRevista);
        
            if (like.isPresent()) {
                return Response.ok(like.get()).build();
            }
            return Response.ok(Optional.empty()).build();
    }
}
