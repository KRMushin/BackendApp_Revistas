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
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
              
            ServicioUsuario userService = new ServicioUsuario();
            Optional<Usuario> usuario = userService.obtenerPerfilUsurio(nombreUsuario);
            return Response.ok(usuario.get()).build();
    }
    
        ServicioUsuario userServiceDatos = new ServicioUsuario();
    @PUT
    @Path("/actualizarDatos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarUsuarioDatos(UsuarioDTO usuarioDTO){
            userServiceDatos.actualizarUsuario(usuarioDTO);
            return Response.ok().build();
    }
    
    @POST
    @Path("{nombreUsuario}/guardarFoto")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    
    public Response actualizarFoto(
                    @FormDataParam("foto") InputStream fotoInputStream,
                    @PathParam("nombreUsuario") String nombreUsuario
    ){
            ServicioUsuario userService = new ServicioUsuario();
            userService.guardarFotoPerfil(fotoInputStream, nombreUsuario);
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
                return Response.ok(Files.newInputStream(foto.get().toPath())).header("Content-Disposition", "inline; filename=\"" + foto.get().getName() + "\"").build(); 
            } catch (IOException ex) {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
            return Response.status(Response.Status.NO_CONTENT).build();
        } 
    
    
    @GET
    @Path("/listarCompradores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtnerCompradores(){
            ServicioUsuario userService = new ServicioUsuario();
            List<String> nombres = userService.listarCompradores();
            
            if (nombres.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.ok(nombres).build();
    }
    
    
}
