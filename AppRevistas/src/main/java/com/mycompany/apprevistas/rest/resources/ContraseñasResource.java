/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.rest.resources;

import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.DTOs.ActContraseñaDTO;
import com.mycompany.apprevistas.backend.Servicios.ServicioContraseñas;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ContraseñasResource {
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarContraseña(ActContraseñaDTO contraseñaDTO ){
      
        try {
             ServicioContraseñas passwordService = new ServicioContraseñas();
            passwordService.actualizarContraseña(contraseñaDTO);
            return Response.ok().build();
            
        } catch (DatosInvalidosUsuarioException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex).build();
        }
    }
    
}
