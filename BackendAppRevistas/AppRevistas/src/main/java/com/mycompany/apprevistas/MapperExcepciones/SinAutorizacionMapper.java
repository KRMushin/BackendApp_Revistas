/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.MapperExcepciones;

import com.mycompany.apprevistas.backend.Excepciones.SinAutorizacionException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author kevin-mushin
 */
@Provider
public class SinAutorizacionMapper implements ExceptionMapper<SinAutorizacionException>{
    @Override
    public Response toResponse(SinAutorizacionException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
                       .entity("{\"error\": \"" + exception.getMessage() + "\"}")
                       .build();
        }
}
