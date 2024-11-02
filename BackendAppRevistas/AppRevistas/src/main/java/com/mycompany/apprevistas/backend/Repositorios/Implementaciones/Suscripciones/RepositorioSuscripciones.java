/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Suscripciones;

import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Suscripcion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioSuscripciones {
    private Connection conn;

    public RepositorioSuscripciones(Connection conn) {
        this.conn = conn;
    }
   
    public List<LlaveRevistaDTO> listarSuscripcionesUsuario(String nombreUsuario) throws SQLException{
        List<LlaveRevistaDTO> suscripcionesAsociadas = new ArrayList<>();

        String query = "SELECT r.id_revista, r.titulo_revista, s.nombre_usuario, s.fecha_suscripcion, s.id_suscripcion FROM suscripciones s JOIN revistas r ON s.id_revista = r.id_revista WHERE s.nombre_usuario = ? AND r.estado_revista = 'ACTIVA'";
        
        try(PreparedStatement stmt = conn.prepareStatement(query)){
             stmt.setString(1, nombreUsuario);
             
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 LlaveRevistaDTO suscripcionRevista = obtenerLlaveRevistaDTO(rs);
                 suscripcionesAsociadas.add(suscripcionRevista);
            }
        }
        return suscripcionesAsociadas;
    }
    
    public Suscripcion guardarSuscripcion(Suscripcion suscripcion) throws SQLException{
        
        String insertUpdate = "INSERT INTO suscripciones(nombre_usuario, id_revista, fecha_suscripcion) values(?,?,?) ";
        try(PreparedStatement stmt = conn.prepareStatement(insertUpdate, PreparedStatement.RETURN_GENERATED_KEYS)){
             stmt.setString(1, suscripcion.getNombreUsuario());
             stmt.setLong(2, suscripcion.getIdRevista());
             stmt.setDate(3, Date.valueOf(suscripcion.getFechaSuscripcion()));
        
             int filasAfectadas = stmt.executeUpdate();
             
             if (filasAfectadas == 0) {
                 throw new SQLException("verifique los datos de suscripcion");
            }
             try(ResultSet id = stmt.getGeneratedKeys()){
                      if (id.next()) {
                             Long idSuscripcion = id.getLong(1);
                        suscripcion.setIdSuscripcion(idSuscripcion);
                     }else {
                          throw new SQLException("Guardar suscripcion falló, no se obtuvo ID.");
                      }
             }
        }
        return suscripcion;
    }
    
    public Suscripcion obtnerPorId(Long idSuscripcion) throws SQLException{
        
        String insertQuery = "SELECT *FROM suscripciones WHERE id_suscripcion = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
            stmt.setLong(1, idSuscripcion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return crearSuscripcion(rs);
            }else{
              throw new SQLException("falla en la obtencion de la suscripcion, verificar ID.");
            }
        }   
    }
    
    private Suscripcion crearSuscripcion(ResultSet rs) throws SQLException {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setIdSuscripcion(rs.getLong("id_suscripcion"));
        suscripcion.setIdRevista(rs.getLong("id_revista"));
        suscripcion.setNombreUsuario(rs.getString("nombre_usuario"));
        suscripcion.setFechaSuscripcion(rs.getDate("fecha_suscripcion").toLocalDate());
        suscripcion.setCalificoRevista(rs.getBoolean("califico_suscripcion"));
        return suscripcion;
    }

    private LlaveRevistaDTO obtenerLlaveRevistaDTO(ResultSet rs) throws SQLException {
        LlaveRevistaDTO revista = new LlaveRevistaDTO();
        revista.setRevistaComentable(rs.getBoolean(""));
        revista.setTituloRevista(rs.getString("titulo_revista"));
        revista.setIdRevista(rs.getLong("id_revista"));
        return revista;
    }

    public void apreciarSuscripcion(Long idRevista, String nombreUsuario) throws SQLException {

        String updateLikes = "UPDATE revistas r JOIN suscripciones s ON r.id_revista = s.id_revista SET r.numero_likes = r.numero_likes + 1, s.aprecio_suscripcion = TRUE WHERE s.nombre_usuario = ? AND r.id_revista = ? AND s.aprecio_suscripcion = FALSE;";
        try(PreparedStatement stmt = conn.prepareStatement(updateLikes)){
            stmt.setString(1, nombreUsuario);
            stmt.setLong(2, idRevista);
            
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas <= 0) {
                throw new SQLException("No puedes realizar like dos veces ya lo has echo una vez");
            }
        }
    }
    
}
