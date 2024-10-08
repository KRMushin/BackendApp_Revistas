/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.rest.resources;

import com.mycompany.aplicacionrevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.aplicacionrevistas.Excepciones.TransaccionFallidaException;
import com.mycompany.aplicacionrevistas.backend.DTOs.UsuarioDTO;
import com.mycompany.aplicacionrevistas.backend.Servicios.ServicioUsuario;
import com.mycompany.aplicacionrevistas.backend.entidades.FotoUsuario;
import com.mycompany.aplicacionrevistas.backend.entidades.Usuario;
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
import java.sql.SQLException;
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
             Optional<Usuario> usuario = userService.obtenerUsuario(nombreUsuario);
              
              if (usuario.isPresent()) {
                return Response.ok(usuario.get()).build();
            }
              return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("{nombreUsuario}/foto")
    @Produces({"image/jpeg", "image/png"})
    public Response obtenerFotoPerfil(@PathParam("nombreUsuario") String nombreUsuario){
        
        ServicioUsuario userService = new ServicioUsuario();
    
        try {
            File foto = userService.obtenerFotoPerfil(nombreUsuario); // obtener la foto de perfil

            if (foto != null) {
                return Response.ok(Files.newInputStream(foto.toPath())).header("Content-Disposition", "inline; filename=\"" + foto.getName() + "\"").build(); // devuelve un imputstream
            }
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (SQLException | IOException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarUsuarioDatos(UsuarioDTO usuarioDTO){
        try {
            ServicioUsuario userService = new ServicioUsuario();
            userService.actualizarUsuario(usuarioDTO);
            return Response.ok().build();
        } catch (DatosInvalidosUsuarioException  e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
            
        }catch(TransaccionFallidaException | SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
//    @POST
//        @Path("/{nombreUsuario}/foto")
//        @Consumes(MediaType.MULTIPART_FORM_DATA)
//        @Produces(MediaType.TEXT_PLAIN)
//        public Response actualizarFotoPerfil(@PathParam("nombreUsuario") String nombreUsuario, 
//                                        @FormDataParam("file") InputStream fileInputStream,
//                                         @FormDataParam("file") FormDataContentDisposition archivoDatos) throws SQLException, DatosInvalidosUsuarioException {
//            try {
//                ServicioUsuario userService = new ServicioUsuario();
//                FotoUsuario ft = userService.actualizarFotoUsuario(fileInputStream,archivoDatos,nombreUsuario);
//                    return Response.ok().entity(ft).build();
//
//            } catch ( SQLException e) {
//                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//
//            } catch (DatosInvalidosUsuarioException e){
//                    return Response.status(Response.Status.BAD_REQUEST).build();
//            }
//        }
}
