/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.Excepciones.ErrorInternoException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioRevistasPDF {
    private  Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public Long guardarArchivoPDF(InputStream archivoPDF, Long id) throws SQLException {
        String insertPDF = "INSERT INTO archivos_revistas (id_revista,archivo) VALUES (?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertPDF)) {
                stmt.setLong(1, id);
                stmt.setBlob(2, archivoPDF);
                int affectedRows = stmt.executeUpdate();
                System.out.println(affectedRows);
                if (affectedRows == 0) {
                    throw new ErrorInternoException();
                }
                return id;
            }
    }
    public InputStream obtenerArchivoPorId(Long idArchivoPDF) throws SQLException {
        
        String query = "SELECT archivo FROM ARCHIVOS_REVISTA WHERE id_archivo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, idArchivoPDF);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBlob("archivo").getBinaryStream();
                }
            }
        }
        return null; 
    }

    public boolean existeRevista(Long idRevista) throws SQLException {
    String query = "SELECT COUNT(*) FROM revistas WHERE id_revista = ?";  
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setLong(1, idRevista);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);  
                return count > 0;          
            }
        }
    }
    return false;  
}

    
}
