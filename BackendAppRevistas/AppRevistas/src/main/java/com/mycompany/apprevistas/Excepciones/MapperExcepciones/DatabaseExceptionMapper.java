/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.Excepciones.MapperExcepciones;

import com.mycompany.apprevistas.Excepciones.ConflictoUsuarioException;
import com.mycompany.apprevistas.Excepciones.DatabaseException;
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
