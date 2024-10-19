/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.anuncios;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Repositorios.RepositorioConfigAnuncios;
import com.mycompany.apprevistas.backend.modelos.ConfiguracionAnuncio;
import com.mycompany.apprevistas.backend.util.TipoAnuncio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioConfigAnuncio implements RepositorioConfigAnuncios<ConfiguracionAnuncio>{

    private Connection conn;
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<ConfiguracionAnuncio> listarTodos() throws SQLException{
        List<ConfiguracionAnuncio> configs = new ArrayList<>();

        String insertQuery = "SELECT *FROM configuracion_anuncios";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             ResultSet rs = stmt.executeQuery();
             
             while (rs.next()) {
                configs.add(obtenerConfiguracion(rs));
            }
        }
        return configs;

    }

    @Override
    public ConfiguracionAnuncio actualizar(ConfiguracionAnuncio modelo) throws SQLException {
        String insertUpdate = "UPDATE configuracion_anuncios SET precio = ? , precio_dia = ? WHERE id_configuracion = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(insertUpdate)){
             stmt.setDouble(1, modelo.getPrecio());
             stmt.setDouble(2, modelo.getTiempoDuracion());
             stmt.setLong(3, modelo.getIdAnuncio());

             int rowsAffected = stmt.executeUpdate();
             if (rowsAffected <= 0) {
                 return null;
            }
             return modelo;
        }
    }

    @Override
    public ConfiguracionAnuncio obtenerPorId(TipoAnuncio identificador) throws SQLException {
            String insertQuery = "SELECT * FROM configuracion_anuncios WHERE tipo_anuncio = ?";

            try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
                 stmt.setString(1, identificador.toString());
                 
                 ResultSet rs = stmt.executeQuery();
                 if (rs.next()) {
                    return obtenerConfiguracion(rs);
                }else{
                     throw new DatosInvalidosUsuarioException();
                 }
            }
    }
    
    private ConfiguracionAnuncio obtenerConfiguracion(ResultSet rs) throws SQLException {
         ConfiguracionAnuncio config = new ConfiguracionAnuncio();
        
         config.setIdAnuncio(rs.getLong("id_configuracion"));
         config.setTipoAnuncio(rs.getString("tipo_anuncio"));
         config.setPrecio(rs.getDouble("precio"));
         config.setTiempoDuracion(rs.getDouble("precio_dia"));
        return config;
    }
    
    

}
