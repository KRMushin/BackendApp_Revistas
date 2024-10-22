/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.restApi.resources.Anuncios;

import com.mycompany.apprevistas.backend.Servicios.Anuncios.ServicioConfigAnuncios;
import com.mycompany.apprevistas.backend.Servicios.ServicioContraseñas;
import com.mycompany.apprevistas.backend.modelos.ConfiguracionAnuncio;
import com.mycompany.apprevistas.backend.usuariosDTOs.ActualizarContraseñaDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
@Path("/anuncios/configuraciones")
public class ConfigAnunciosResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerConfiguraciones(){
            ServicioConfigAnuncios servicio = new ServicioConfigAnuncios();
            List<ConfiguracionAnuncio> configs = servicio.obtenerConfiguraciones();
            return Response.ok(configs).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarContraseña(ConfiguracionAnuncio config ){
            ServicioConfigAnuncios servicio = new ServicioConfigAnuncios();
            servicio.actualizarConfiguracion(config);
            return Response.ok().build();
        }
    
}

