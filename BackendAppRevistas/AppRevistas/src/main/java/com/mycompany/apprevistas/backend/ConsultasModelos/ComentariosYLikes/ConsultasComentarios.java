/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos.ComentariosYLikes;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.ComentariosYLikes.RepositorioComentarios;
import com.mycompany.apprevistas.backend.modelos.Comentario;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasComentarios {

    private RepositorioComentarios repComentarios;

    public ConsultasComentarios() {
        this.repComentarios = new RepositorioComentarios();
    }

    public void publicarComentario(Comentario comentario) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repComentarios.setConn(conn);
              repComentarios.guardar(comentario);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public List<Comentario> comentariosUsuario(String nombreUsuario, Long idRevista) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repComentarios.setConn(conn);
              // retorno de los valores de los comentarios
              return repComentarios.listarComentariosUsuario(nombreUsuario, idRevista);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public List<Comentario> comentariosRevista(Long idRevista) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repComentarios.setConn(conn);
              return repComentarios.listarComentariosRevista(idRevista);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public boolean eliminarComentario(Long idComentario) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repComentarios.setConn(conn);
              return repComentarios.eliminarComentario(idComentario);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
