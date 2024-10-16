/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.MapperExcepciones;

import com.mycompany.apprevistas.backend.Excepciones.ErrorInternoException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author kevin-mushin
 */
@Provider
public class ErrorInternoMapper implements ExceptionMapper<ErrorInternoException> {
    @Override
    public Response toResponse(ErrorInternoException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity("Error en el sistema" + exception.getMessage() + exception.getLocalizedMessage()).build();
    }
}