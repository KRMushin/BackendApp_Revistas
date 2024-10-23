/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources;

import com.mycompany.apprevistas.backend.usuariosDTOs.ActualizarContraseñaDTO;
import com.mycompany.apprevistas.backend.Servicios.ServicioContraseñas;
import com.mycompany.apprevistas.backend.util.AutenticadorJWT;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("actualizar/contraseña")
public class ContraseñasResource {
    
     private final AutenticadorJWT autenticadorJWT = new AutenticadorJWT();
     
     @PUT
     @Path("{nombreUsuario}")
     @Consumes(MediaType.APPLICATION_JSON)
     public Response actualizarContraseña(ActualizarContraseñaDTO contraseñaDTO,
                                                                   @Context HttpHeaders headerRequest){
         
           autenticadorJWT.validarTokenl(headerRequest); 
            ServicioContraseñas passwordService = new ServicioContraseñas();
            passwordService.actualizarContraseña(contraseñaDTO);
            return Response.ok().build();
        }
    }
