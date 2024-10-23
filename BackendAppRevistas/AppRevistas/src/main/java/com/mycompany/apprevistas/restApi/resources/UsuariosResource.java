/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources;

import com.mycompany.apprevistas.backend.usuariosDTOs.UsuarioDTO;
import com.mycompany.apprevistas.backend.Servicios.ServicioUsuario;
import com.mycompany.apprevistas.backend.modelos.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
@Path("/datos/usuario")
public class UsuariosResource {
    
    @GET
    @Path("{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPerfilUsuario(@PathParam("nombreUsuario") String nombreUsuario){
              
            //crear una validacion solo que contenta un rol valido 
            ServicioUsuario userService = new ServicioUsuario();
            Optional<Usuario> usuario = userService.obtenerPerfilUsurio(nombreUsuario);
            return Response.ok(usuario.get()).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarUsuarioDatos(UsuarioDTO usuarioDTO){
            ServicioUsuario userService = new ServicioUsuario();
            userService.actualizarUsuario(usuarioDTO);
            return Response.ok().build();
    }
    @GET
    @Path("{nombreUsuario}/foto")
    @Produces({"image/jpeg", "image/png"})
    public Response obtenerFotoPerfil(@PathParam("nombreUsuario") String nombreUsuario){
        
        ServicioUsuario userService = new ServicioUsuario();
        Optional<File> foto = userService.obtenerFotoPerfil(nombreUsuario); 
        if (foto.isPresent()) {
            try {
                return Response.ok(Files.newInputStream(foto.get().toPath())).header("Content-Disposition", "inline; filename=\"" + foto.get().getName() + "\"").build(); // devuelve un imputstream
            } catch (IOException ex) {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }else{
                    return Response.status(Response.Status.NOT_FOUND).build();
        }
    } 
}
