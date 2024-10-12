/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.rest.resources;

import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.usuariosDTOs.CredencialUsuario;
import com.mycompany.apprevistas.backend.usuariosDTOs.LoginDTO;
import com.mycompany.apprevistas.backend.Servicios.ServicioAutenticarUsuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author kevin-mushin
 */
@Path("login/usuario")
public class InicioSesionResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCredenciales(LoginDTO loginDTO){
        ServicioAutenticarUsuario service = new ServicioAutenticarUsuario();
        CredencialUsuario credencialUsuario = service.obtenerCredencialUsuario(loginDTO);
        return Response.ok(credencialUsuario).build();
    }
    
}
