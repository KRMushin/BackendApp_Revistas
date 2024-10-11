/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.rest.resources;

import com.mycompany.apprevistas.backend.Servicios.ServicioConfigAnuncios;
import com.mycompany.apprevistas.backend.entidades.ConfiguracionAnuncio;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
@Path("/anuncios/configuraciones")
public class ConfigAnunciosResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerConfiguraciones(){
        try {
            ServicioConfigAnuncios servicio = new ServicioConfigAnuncios();
            List<ConfiguracionAnuncio> configs = servicio.obtenerConfiguraciones();
            
            for (int i = 0; i < configs.size(); i++) {
                ConfiguracionAnuncio con = configs.get(i);
                if (con != null) {
                    con.getTipoAnuncio();
                }
                
            }
            return Response.ok(configs).build();
        } catch (SQLException ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
