/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.rest.resources;

import com.mycompany.apprevistas.backend.Servicios.ServicioAutUsuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author kevin-mushin
 */
@Path("/acceso/login")
public class InicioSesionResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCredenciales(){
        ServicioAutUsuario service = new ServicioAutUsuario();
        return null;
    }
    
}
