/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.rest.resources;

import com.mycompany.apprevistas.backend.usuariosDTOs.ActualizarContraseñaDTO;
import com.mycompany.apprevistas.backend.Servicios.ServicioContraseñas;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("actualizar/contraseña")
public class ContraseñasResource {
    
    @PUT
    @Path("{nombreUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarContraseña(ActualizarContraseñaDTO contraseñaDTO ){
             ServicioContraseñas passwordService = new ServicioContraseñas();
            passwordService.actualizarContraseña(contraseñaDTO);
            return Response.ok().build();
        }
    }
