/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.Excepciones.ErrorInternoException;
import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.RevistasDTOs.NumeroRevistaDTO;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioNumerosRevista {
    
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Optional<NumeroRevistaDTO> publicarNumero(NumeroRevistaDTO numeroRevistaDTO) throws SQLException {
        String insertModel = "INSERT INTO numeros_revista(id_revista, archivo_pdf, titulo_numero, fecha_publicacion) values(?,?, ? ,?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(insertModel)){
            stmt.setLong(1, numeroRevistaDTO.getIdRevista());
            stmt.setBytes(2, numeroRevistaDTO.getArchivoPdf());
            stmt.setString(3, numeroRevistaDTO.getTituloNumero());
            stmt.setDate(4, Date.valueOf(numeroRevistaDTO.getFechaPublicacion()));
            
            int filasAfectadas = stmt.executeUpdate();
                if (filasAfectadas <= 0) {
                    return Optional.empty();
                }
        }
        return Optional.of(numeroRevistaDTO);
    }

    public Optional<List<NumeroRevistaDTO>> obtnerTodosLosNumeros(Long idRevista) throws SQLException {
        List<NumeroRevistaDTO> numeros = new ArrayList<>();
        
        String insertQuery = "SELECT *FROM numeros_revista WHERE id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
            stmt.setLong(1, idRevista);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                numeros.add(crearNumeroConLLave(rs));
            }
        }
        return Optional.of(numeros);
    }

    public InputStream obtenerArchivoNumero(Long idNumeroRevista) throws SQLException {
        String insertQuery = "SELECT archivo_pdf FROM numeros_revista WHERE id_numero = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
            stmt.setLong(1, idNumeroRevista);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBlob("archivo_pdf").getBinaryStream();
            }
            throw new NotFoundException();
        }
    }
    
    private NumeroRevistaDTO crearNumeroConLLave(ResultSet rs) throws SQLException {
        NumeroRevistaDTO numero = new NumeroRevistaDTO();
        numero.setIdNumeroRevista(rs.getLong("id_numero"));
        numero.setTituloNumero(rs.getString("titulo_numero"));
        numero.setFechaPublicacion(rs.getDate("fecha_publicacion").toLocalDate());
        return numero;
    }

    
    
   
}
