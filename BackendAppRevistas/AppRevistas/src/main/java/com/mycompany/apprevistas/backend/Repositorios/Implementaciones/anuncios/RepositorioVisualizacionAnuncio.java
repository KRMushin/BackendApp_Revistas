/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.anuncios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioVisualizacionAnuncio {
    
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public int almacenarSuscripcion(String url, Long idAnuncio) throws SQLException {
        String consulta = "INSERT INTO visualizaciones_anuncios(id_anuncio, url) VALUES(?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(consulta)){
            stmt.setLong(1, idAnuncio);
            stmt.setString(2, url);
            return stmt.executeUpdate();
        }
    }
}
