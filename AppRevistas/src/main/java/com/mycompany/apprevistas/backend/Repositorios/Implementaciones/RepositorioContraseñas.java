/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.Repositorios.Implementaciones;

import com.mycompany.aplicacionrevistas.backend.DTOs.ActContraseñaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioContraseñas{

    private Connection conn;
    
    public String actualizar(ActContraseñaDTO contraseñaDTO) throws SQLException {

       String insertUpdate = "UPDATE password_usuario = ? WHERE nombre_usuario = ?";
       try(PreparedStatement stmt = conn.prepareStatement(insertUpdate)){
           stmt.setString(1, contraseñaDTO.getNuevaPassword());
           stmt.setString(2, contraseñaDTO.getNombreUsuario());
           
           int rowsAffected = stmt.executeUpdate();
           
           if (rowsAffected <= 0) {
               throw new SQLException("Error en el repositorio de contraseñas");
           }
           return contraseñaDTO.getNuevaPassword();
       }
    }

    public String obtenerPorId(String nombreUsuario) throws SQLException {

        String insertQuery = "SELECT password_usuario FROM usuarios WHERE nombre_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             stmt.setString(1, nombreUsuario);
             
             ResultSet rs = stmt.executeQuery();
             if (rs.next()) {
                return rs.getString("password_usuario");
            }
             return null;
        }
    }
}
