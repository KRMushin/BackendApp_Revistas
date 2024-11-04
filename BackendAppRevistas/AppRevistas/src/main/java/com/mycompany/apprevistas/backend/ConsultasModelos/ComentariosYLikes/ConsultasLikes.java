/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos.ComentariosYLikes;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.ComentariosYLikes.RespositorioLikes;
import com.mycompany.apprevistas.backend.modelos.LikeRevista;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasLikes {
    
    private RespositorioLikes repLikes;

    public ConsultasLikes() {
        this.repLikes = new RespositorioLikes();
    }
    
    public void postearLike(LikeRevista likeRevista) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repLikes.setConn(conn);
            repLikes.guardar(likeRevista);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void actualizarLike(LikeRevista rev) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repLikes.setConn(conn);
            repLikes.actualizar(rev);
        } catch (NullPointerException | SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public LikeRevista obtnerPorUsuario(String nombreUsuario, Long idRevista) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repLikes.setConn(conn);
            LikeRevista like = repLikes.obtenerPorId(nombreUsuario,idRevista);
            return like;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
