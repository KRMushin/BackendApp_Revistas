/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.MapperExcepciones;

import com.mycompany.apprevistas.backend.Excepciones.ConflictoUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author kevin-mushin
 */
@Provider
public class DatabaseExceptionMapper implements ExceptionMapper<DatabaseException>{
    @Override
    public Response toResponse(DatabaseException exception) {
        System.out.println(exception.getMessage() + exception.getLocalizedMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage() + exception.getLocalizedMessage()).build();   //Retorna 409 Conflict                       
    }
}
