/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.Repositorios;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 * parametro id identifica a nombre de user o long
 * @param <T>
 * @param <ID>
 */
public interface RepositorioCrud<T, ID, Parametro> {
    
    List<T> listar(Parametro identificador) throws SQLException;
        
    T guardar(T modelo) throws SQLException;
    
    T actualizar(T modelo) throws SQLException;
    
    T obtenerPorId(ID identificador) throws SQLException;
    
}
