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
public interface RepositorioEscrituraLectura<T, ID> {
    
        T guardar(T modelo) throws SQLException;
    
        T actualizar(T modelo) throws SQLException;
    
        T obtenerPorId(ID identificador) throws SQLException;
    
}
