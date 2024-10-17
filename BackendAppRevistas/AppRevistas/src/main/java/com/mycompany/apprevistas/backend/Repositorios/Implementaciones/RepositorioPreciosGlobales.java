/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones;

import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.modelos.PrecioGlobal;
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
public class RepositorioPreciosGlobales {
    
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public PrecioGlobal actualizar(PrecioGlobal modelo) throws SQLException {

        String insertQuery = "UPDATE precios_globales SET precio_global = ? WHERE id_precio = ? ";
        
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
            stmt.setDouble(1, modelo.getPrecioGlobal());
            stmt.setLong(2, modelo.getIdPrecio());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected <= 0) {
                   throw new NotFoundException();
            }
            return modelo;
        }
    }
    
    public List<PrecioGlobal> listarTodos() throws SQLException {

    String selectQuery = "SELECT id_precio, precio_global, modelo_precio FROM precios_globales";
    
    List<PrecioGlobal> listaPrecios = new ArrayList<>();
    
    try (PreparedStatement stmt = conn.prepareStatement(selectQuery);
         ResultSet rs = stmt.executeQuery()) {
         
        while (rs.next()) {
            PrecioGlobal precio = new PrecioGlobal();
            precio.setIdPrecio(rs.getLong("id_precio"));
            precio.setPrecioGlobal(rs.getDouble("precio_global"));
            precio.setModeloPrecio(rs.getString("modelo_precio"));
            
            listaPrecios.add(precio);
        }
    }
    
    return listaPrecios;
}

    
    
    
}
