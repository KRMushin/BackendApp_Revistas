/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.RevistasDTOs.CompraBloqueoDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioComprasBloqueos {

    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
        
    public void guardarCompra(CompraBloqueoDTO modelo) throws SQLException {
         String insertQuery = "INSERT INTO bloqueos_anuncios_compras (id_revista, fecha_compra, dias_compra, costo_total) "
                           + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setLong(1, modelo.getIdRevista());
                stmt.setDate(2, Date.valueOf(modelo.getFechaCompra())); 
                stmt.setInt(3, modelo.getDiasCompra());
                stmt.setDouble(4, modelo.getCostoTotal());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas <= 0) {
                System.out.println("compra hecha");
                throw new SQLException("No se pudo guardar la compra de bloqueo");
            }
        }
    }
}
