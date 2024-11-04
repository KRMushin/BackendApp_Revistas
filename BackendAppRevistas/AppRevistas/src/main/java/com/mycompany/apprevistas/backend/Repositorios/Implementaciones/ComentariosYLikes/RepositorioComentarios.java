/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.ComentariosYLikes;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Repositorios.RepositorioCrud;
import com.mycompany.apprevistas.backend.Repositorios.RepositorioEscrituraLectura;
import com.mycompany.apprevistas.backend.modelos.Comentario;
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
public class RepositorioComentarios {
    
   private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
   
   

    public List<Comentario> listarComentariosUsuario(String nombreUsuario, Long id) throws SQLException{
        List<Comentario> comentariosAsociados = new ArrayList<>();
        
        String insertQuery = "SELECT *FROM revistas_comentarios WHERE nombre_usuario = ? AND id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
             stmt.setString(1, nombreUsuario);
             stmt.setLong(2, id);
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 comentariosAsociados.add(crearComentario(rs));
            }
        } 
    
    return comentariosAsociados;
    }
    
    public Comentario guardar(Comentario comentario) throws SQLException{
        
        String insertModel = "INSERT INTO revistas_comentarios(nombre_usuario,id_revista,fecha_comentario,comentario) VALUES(?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertModel)){
            stmt.setString(1, comentario.getNombreUsuario());
            stmt.setLong(2, comentario.getIdRevista());
            stmt.setDate(3, Date.valueOf(comentario.getFechaComentario()));
            stmt.setString(4, comentario.getComentario());
            
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas <= 0) {
                throw new DatosInvalidosUsuarioException();
            }
            return comentario;
        }
    }
    
    public List<Comentario> listarComentariosRevista(Long id) throws SQLException{
        List<Comentario> comentariosAsociados = new ArrayList<>();
        
        String insertQuery = "SELECT *FROM revistas_comentarios WHERE id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
             stmt.setLong(1, id);
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 comentariosAsociados.add(crearComentario(rs));
            }
        } 
    
    return comentariosAsociados;
    }
    
    public boolean eliminarComentario(Long idComentario) throws SQLException {
    String deleteQuery = "DELETE FROM revistas_comentarios WHERE id_comentario = ?";

    try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
        stmt.setLong(1, idComentario);
        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0;
    }
}


    private Comentario crearComentario(ResultSet rs) throws SQLException {
        Comentario comentario = new Comentario();
        comentario.setIdComentario(rs.getLong("id_comentario"));
        comentario.setComentario(rs.getString("comentario"));
        comentario.setNombreUsuario(rs.getString("nombre_usuario"));
        comentario.setIdRevista(rs.getLong("id_revista"));
        comentario.setFechaComentario(rs.getDate("fecha_comentario").toLocalDate());
        return comentario;
    }


    
}

//    public Comentario obtenerPorId(Long id) throws SQLException{
//        
//        String insertQuery = "SELECT *FROM comentarios WHERE id_comentario = ?";
//        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return crearComentario(rs);
//            }else{
//                throw new DatosInvalidosUsuarioException("El comentario buscado no existe con el id proporcionado");
//            }
//            
//        } 
//    }