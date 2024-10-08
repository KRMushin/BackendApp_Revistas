/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.rest.resources;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mycompany.aplicacionrevistas.Excepciones.ConflictoUsuarioException;
import com.mycompany.aplicacionrevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.aplicacionrevistas.Excepciones.TransaccionFallidaException;
import com.mycompany.aplicacionrevistas.backend.DTOs.RegistroUsuarioDTO;
import com.mycompany.aplicacionrevistas.backend.Servicios.ServicioRegistro;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
@Path("/registro")
public class RegistroResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(RegistroUsuarioDTO registroDTO) {
            try {
                ServicioRegistro servicioRegistro = new ServicioRegistro();
                servicioRegistro.registrarUsuario(registroDTO);
                return Response.status(Response.Status.CREATED).entity("se registro al usuario").build();
            } catch (ConflictoUsuarioException e){
                return Response.status(Response.Status.CONFLICT).build();
            }
            catch (DatosInvalidosUsuarioException | IllegalArgumentException e) {
                System.out.println(e);
                return Response.status(Response.Status.BAD_REQUEST).entity("Datos enviados invalidos").build(); // se lanza porque el usuario mando datos invalidos para el registro
            
            }catch(TransaccionFallidaException | SQLException e){
                System.out.println(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
    }
}
