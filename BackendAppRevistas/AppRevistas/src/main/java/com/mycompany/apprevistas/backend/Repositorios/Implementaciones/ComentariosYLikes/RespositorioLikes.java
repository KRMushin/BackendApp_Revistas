/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.ComentariosYLikes;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Repositorios.RepositorioEscrituraLectura;
import com.mycompany.apprevistas.backend.modelos.LikeRevista;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class RespositorioLikes{

    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
    public LikeRevista guardar(LikeRevista modelo) throws SQLException {

        // la fecha la genera la base de datos
        String insert = "INSERT INTO likes_revistas(nombre_usuario, id_revista) VALUES(?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(insert)){
            stmt.setString(1, modelo.getNombreUsuario());
            stmt.setLong(2, modelo.getIdRevista());
            
            int filas = stmt.executeUpdate();
            if (filas <= 0) {
                throw new DatosInvalidosUsuarioException();
            }
        }
        return modelo;
    }

    public LikeRevista actualizar(LikeRevista modelo) throws SQLException {
        String updateQuery = "UPDATE likes_revistas SET like_hecho = ? WHERE id_like = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setBoolean(1, modelo.isLikeHecho()); 
            stmt.setLong(2, modelo.getIdLike());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas <= 0) {
                throw new DatosInvalidosUsuarioException(); // Lanza una excepción si no se actualizó ninguna fila
            }
        }
        return modelo;
    }
    
    public LikeRevista obtenerPorId(String nombreUsuario, Long idRevista) throws SQLException {
        String select = "SELECT * FROM likes_revistas WHERE nombre_usuario = ? AND id_revista = ?";
        LikeRevista likeRevista = null;

        try (PreparedStatement stmt = conn.prepareStatement(select)) {
            stmt.setString(1, nombreUsuario);
            stmt.setLong(2, idRevista);
            
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return crearLike(rs);
                }
        }
        return likeRevista;
    }
    
    private LikeRevista crearLike(ResultSet rs) throws SQLException{
                LikeRevista likeRevista = new LikeRevista();
                likeRevista.setIdLike(rs.getLong("id_like"));
                likeRevista.setNombreUsuario(rs.getString("nombre_usuario"));
                likeRevista.setIdRevista(rs.getLong("id_revista"));
                likeRevista.setFechaLike(rs.getDate("fecha_like").toLocalDate());
                likeRevista.setLikeHecho(rs.getBoolean("like_hecho"));
                return likeRevista;
    }
    
    
    
}
