/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoConfigRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.NuevoCostoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioEstadoRevistas {
    
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public void guardarEstadosRevista(Long idRevista) throws SQLException {
        String insertQuery = " INSERT INTO configuracion_revistas(id_revista) VALUES(?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
            stmt.setLong(1, idRevista);
            int rows = stmt.executeUpdate();
            if (rows <= 0) {
                throw new DatosInvalidosUsuarioException();
            }
        }
    }
    
    public void actualizarEstadoRevista(EstadoConfigRevistaDTO estado) throws SQLException{
        String updateQuery = "UPDATE configuracion_revistas SET " + estado.getTipoEstado().obtenerConsulta() + " = ? WHERE id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)){
             stmt.setBoolean(1, estado.isEstado());
             stmt.setLong(2, estado.getIdRevista());
            int rows = stmt.executeUpdate();
            
            if (rows <= 0) {
                throw new DatosInvalidosUsuarioException();
            }
        }
    }
    
    public EstadoRevistaDTO obtenerEstadoRevista(Long idRevista) throws SQLException{
        String insertQuery = "SELECT *FROM configuracion_revistas WHERE id_revista = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             stmt.setLong(1, idRevista);
             ResultSet rs = stmt.executeQuery();
             if (rs.next()) {
                 return crearEstado(rs);
            }else{
                 throw new NotFoundException();
             }
        }
    }

    private EstadoRevistaDTO crearEstado(ResultSet rs) throws SQLException {
        EstadoRevistaDTO s = new EstadoRevistaDTO();
        s.setAceptaSuscripciones(rs.getBoolean("acepta_suscripciones"));
        s.setAnunciosBloqueados(rs.getBoolean("anuncios_bloqueados"));
        s.setEsLikeable(rs.getBoolean("revista_likeable"));
        s.setEsComentable(rs.getBoolean("revista_comentable"));
        s.setIdRevista(rs.getLong("id_revista"));
        return s;

    }
}
