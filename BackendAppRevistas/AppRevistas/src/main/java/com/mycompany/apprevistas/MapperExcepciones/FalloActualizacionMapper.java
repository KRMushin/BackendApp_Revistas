/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.MapperExcepciones;

import com.mycompany.apprevistas.backend.Excepciones.FalloActualizacionException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author kevin-mushin
 */
@Provider
public class FalloActualizacionMapper implements ExceptionMapper<FalloActualizacionException> {
    @Override
    public Response toResponse(FalloActualizacionException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}

