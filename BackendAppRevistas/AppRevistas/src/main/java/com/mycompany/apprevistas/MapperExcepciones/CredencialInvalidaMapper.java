/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.MapperExcepciones;

import com.mycompany.apprevistas.backend.Excepciones.CredencialInvalidaException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author kevin-mushin
 */
public class CredencialInvalidaMapper implements ExceptionMapper<CredencialInvalidaException>{

    @Override
    public Response toResponse(CredencialInvalidaException e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
    
