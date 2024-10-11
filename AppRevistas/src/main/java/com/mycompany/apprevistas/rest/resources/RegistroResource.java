/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.rest.resources;

import com.mycompany.apprevistas.backend.DTOs.RegistroUsuarioDTO;
import com.mycompany.apprevistas.backend.Servicios.ServicioRegistro;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author kevin-mushin
 */
@Path("registrar")
public class RegistroResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(RegistroUsuarioDTO registroDTO) {

        ServicioRegistro servicioRegistro = new ServicioRegistro();
        servicioRegistro.registrarUsuario(registroDTO);
        return Response.status(Response.Status.CREATED).build();
                
    }
}
