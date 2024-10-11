/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios;

import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 * @param <T>
 * @param <ID>
 */
public interface RepositorioLlaveEntidad<T, ID> {
    
         T obtenerLlaveEntidad(ID identificador) throws SQLException;
}
