/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.Repositorios.Implementaciones;

import com.mycompany.aplicacionrevistas.backend.Repositorios.RepositorioEscrituraLectura;
import com.mycompany.aplicacionrevistas.backend.entidades.FotoUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioFotosUsuarios implements RepositorioEscrituraLectura<FotoUsuario,String>{
    
    private Connection conn;
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public FotoUsuario guardar(FotoUsuario modelo) throws SQLException {
         
         String inserUpdate = " INSERT INTO fotos_usuarios(nombre_usuario) values(?)";
         
         try(PreparedStatement stmt = conn.prepareStatement(inserUpdate)){
              stmt.setString(1, modelo.getNombreUsuario());
              int rowsAffected = stmt.executeUpdate();
              
              if (rowsAffected <= 0) {
                  throw new SQLException("La foto de usuario no se registro en la base de datos");
             }
              return modelo;
         }
    }
    
    @Override
    public FotoUsuario actualizar(FotoUsuario modelo) throws SQLException {

        String insertUpdate = "UPDATE foto_url=? WHERE nombre_usuario = ? ";
        
        try(PreparedStatement stmt = conn.prepareStatement(insertUpdate)){
            stmt.setString(1, modelo.getFotoUrl());
            stmt.setString(2, modelo.getNombreUsuario());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected <= 0) {
                   throw new SQLException("La foto de usuario no se actualizo en la base de datos");
            }
            return modelo;
        }
    }
    
    @Override
    public FotoUsuario obtenerPorId(String identificador) throws SQLException {
        String insertQuery = "SELECT *FROM fotos_usuario WHERE nombre_usuario = ? ";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             stmt.setString(1, identificador);
        
             ResultSet rs = stmt.executeQuery();
             
             if (rs.next()) {
                 FotoUsuario fotoUsuario = new FotoUsuario();
                 fotoUsuario.setNombreUsuario(rs.getString("nombre_usuario"));
                 fotoUsuario.setFotoUrl(rs.getString("foto_url"));
                 return fotoUsuario;
            }else{
                 return null;
             }
        }
    }
}
