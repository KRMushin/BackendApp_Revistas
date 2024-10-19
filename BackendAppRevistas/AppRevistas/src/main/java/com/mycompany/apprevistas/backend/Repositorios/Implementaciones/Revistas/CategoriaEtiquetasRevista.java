/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

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
public class CategoriaEtiquetasRevista {
    
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void agregarCategoriaRevista(Long idRevista, Long idCategoria) throws SQLException {
        String sql = "INSERT INTO revista_categoria (id_revista, id_categoria) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idRevista);
            ps.setLong(2, idCategoria);
            ps.executeUpdate();
        }
    }

    public void agregarEtiquetaRevista(Long idRevista, Long idEtiqueta) throws SQLException {
        String sql = "INSERT INTO revista_etiqueta (id_revista, id_etiqueta) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idRevista);
            ps.setLong(2, idEtiqueta);
            ps.executeUpdate();
        }
    }   

    public void guardarEtiquetas(Long idRevista, List<Long> idEtiquetas) throws SQLException {
        for (Long idEtiqueta : idEtiquetas) {
            agregarEtiquetaRevista(idRevista, idEtiqueta);
        }
    }
}
