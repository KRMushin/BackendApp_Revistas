/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones;

import com.mycompany.apprevistas.backend.Repositorios.RepositorioCrud;
import com.mycompany.apprevistas.backend.modelos.Revista;
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
public class RepositorioRevistas implements RepositorioCrud<Revista,Long,String>{

    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Revista> listar(String nombreUsuario) throws SQLException {
        List<Revista> revistas = new ArrayList<>();

        String getList = "SELECT * FROM revistas WHERE nombre_autor = ? ";
            try (PreparedStatement stmt = conn.prepareStatement(getList)) {
                stmt.setString(1, nombreUsuario);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Revista revista = crearRevista(rs);
                        revistas.add(revista);
                    }
                }
            }
        return revistas;
    }

    @Override
    public Revista guardar(Revista modelo) throws SQLException {
        String insertModel = "INSERT INTO revistas (id_archivo, id_categoria, titulo_revista, nombre_autor, descripcion, fecha_creacion, "
                + "costo_mantenimiento, estado_revista, revista_comentable, revista_likeable) values(?,?,?,?,?,?,?,?,?,?)";
      
        try(PreparedStatement stmt = conn.prepareStatement(insertModel, PreparedStatement.RETURN_GENERATED_KEYS)) {
//                  stmt.setLong(1, modelo.getIdArchivoRevisa()); 
                  stmt.setLong(2, modelo.getIdCategoria());
                  stmt.setString(3, modelo.getTituloRevista());
                  stmt.setString(4, modelo.getNombreAutor());
                  stmt.setString(5, modelo.getDescripcion());
                  stmt.setDate(6, Date.valueOf(modelo.getFechaCreacion()));
                  stmt.setDouble(7, modelo.getCostoMantenimiento());
                  stmt.setString(8, modelo.getEstadoRevista());
                  stmt.setBoolean(9, modelo.isRevistaComentable());
                  stmt.setBoolean(10, modelo.isRevistaLikeable());
                  
                  int filasAfectadas = stmt.executeUpdate();
                  if (filasAfectadas <= 0) {
                      throw new SQLException("Error al publicar la revista: revise que los datos sean correctos porfavor");
                 }
                  try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            Long idRevista = generatedKeys.getLong(1);
                            modelo.setIdRevista(idRevista);
                        } else {
                            throw new SQLException("Guardar revista falló, no se obtuvo ID.");
                        }
                }
                  return modelo;
        }
    }

    @Override
    public Revista actualizar(Revista modelo) throws SQLException {
        /*          METODO PARA ACTUALIZAR LOS ESTADOS DE LA REVISTA */
        String updateQuery = "UPDATE revistas SET revista_comentable = ? , revista_likeable = ?, acepta_suscripciones = ?  WHERE id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
             stmt.setBoolean(1, modelo.isRevistaComentable());
             stmt.setBoolean(2, modelo.isRevistaLikeable());
             stmt.setBoolean(3, modelo.isAceptaSuscripciones());
             stmt.setLong(4, modelo.getIdRevista());
             
             int affectedRows = stmt.executeUpdate();
             if (affectedRows <= 0) {
                    throw new SQLException("Error en repositorio de revistas");
            }
            return modelo;
        } 
    }

    @Override
    public Revista obtenerPorId(Long identificador) throws SQLException {

        Revista revista = null;
        String getModel = "SELECT * FROM revistas WHERE id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(getModel)) {
            stmt.setLong(1, identificador);
             ResultSet rs = stmt.executeQuery();
             if (rs.next()) {
                revista = crearRevista(rs);
            }
             
             
             return revista;
        } catch (Exception e) {
            throw new SQLException("Error en la base de datos al obtener revista por ID");
        }
    }
    private Revista crearRevista(ResultSet rs) throws SQLException {
        Revista revista = new Revista();
        revista.setIdRevista(rs.getLong("id_revista"));
//        revista.setIdArchivoRevisa(rs.getLong("id_archivo"));
        revista.setIdCategoria(rs.getLong("id_categoria"));
        revista.setTituloRevista(rs.getString("titulo_revista"));
        revista.setNombreAutor(rs.getString("nombre_autor"));
        revista.setDescripcion(rs.getString("descripcion"));
        revista.setFechaCreacion((rs.getDate("fecha_creacion")).toLocalDate());
        revista.setCostoMantenimiento(rs.getDouble("costo_mantenimiento"));
        revista.setRevistaComentable(rs.getBoolean("revista_comentable"));
        revista.setRevistaLikeable(rs.getBoolean("revista_likeable"));
        revista.setEstadoRevista(rs.getString("estado_revista"));
        revista.setNumeroLikes(rs.getInt("numero_likes"));
        revista.setAceptaSuscripciones(rs.getBoolean("acepta_suscripciones"));
        return revista;

    }
    
    
} // fin de repositorio 
