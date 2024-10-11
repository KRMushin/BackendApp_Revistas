/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public interface RepositorioConfigAnuncios<T> {
    
        List<T> listarTodos() throws SQLException;
        
        T actualizar(T modelo) throws SQLException;
    
        T obtenerPorId(Long identificador) throws SQLException;
    
}
