/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.Excepciones.MapperExcepciones;

import com.mycompany.apprevistas.Excepciones.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author kevin-mushin
 */
    
    @Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {
    public Response toResponse(NotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
