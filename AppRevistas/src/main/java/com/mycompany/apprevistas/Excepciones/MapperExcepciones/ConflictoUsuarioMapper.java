/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.Excepciones.MapperExcepciones;

import com.mycompany.apprevistas.Excepciones.ConflictoUsuarioException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author kevin-mushin
 */
@Provider
public class ConflictoUsuarioMapper implements ExceptionMapper<ConflictoUsuarioException>{
    @Override
    public Response toResponse(ConflictoUsuarioException exception) {
        return Response.status(Response.Status.CONFLICT).build();   //Retorna 409 Conflict                       
    }
}
