/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Repositorios.RepositorioCrud;
import com.mycompany.apprevistas.backend.modelos.Revista;
import com.mycompany.apprevistas.backend.RevistasDTOs.EstadoRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.MantenimientoCostoDTO;
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
    public List<Revista> listar(String parametro) throws SQLException {
        List<Revista> revistas = new ArrayList<>();
        String getList = "SELECT * FROM revistas WHERE nombre_autor = ?";
            try (PreparedStatement stmt = conn.prepareStatement(getList)) {
                stmt.setString(1, parametro);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Revista revista = crearRevista(rs);
                        revistas.add(revista);
                    }
                }
            } catch (SQLException e) {
                throw new SQLException("Error al listar revistas para el usuario " + parametro, e);
            }
        return revistas;

    }
    @Override
    public Revista guardar(Revista modelo) throws SQLException {
        String insertModel = "INSERT INTO revistas (id_categoria, ruta_revista,titulo_revista, nombre_autor, descripcion, fecha_creacion, "
                + "costo_mantenimiento, revista_comentable, revista_likeable) values(?,?,?,?,?,?,?,?,?)";
      
        try(PreparedStatement stmt = conn.prepareStatement(insertModel, PreparedStatement.RETURN_GENERATED_KEYS)) {
                  stmt.setLong(1, modelo.getIdCategoria());
                  stmt.setString(2, modelo.getRutaRevista());
                  stmt.setString(3, modelo.getTituloRevista());
                  stmt.setString(4, modelo.getNombreAutor());
                  stmt.setString(5, modelo.getDescripcion());
                  stmt.setDate(6, Date.valueOf(modelo.getFechaCreacion()));
                  stmt.setDouble(7, modelo.getCostoMantenimiento());
                  stmt.setBoolean(8, modelo.isRevistaComentable());
                  stmt.setBoolean(9, modelo.isRevistaLikeable());
                  
                  int filasAfectadas = stmt.executeUpdate();
                  if (filasAfectadas <= 0) {
                          throw new DatosInvalidosUsuarioException();
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
        String getModel = "SELECT * FROM revistas WHERE id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(getModel)) {
            stmt.setLong(1, identificador);
             ResultSet rs = stmt.executeQuery();
             if (rs.next()) {
                return crearRevista(rs);
            }  else{
                 throw new DatosInvalidosUsuarioException();
             }
        } catch (Exception e) {
            throw new SQLException("Error en la base de datos al obtener revista por ID");
        }
    }
    
    private Revista crearRevista(ResultSet rs) throws SQLException {
        Revista revista = new Revista();
        revista.setIdRevista(rs.getLong("id_revista"));
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
        revista.setAnunciosBloqueados(rs.getBoolean("anuncios_bloqueados"));
        revista.setAceptaSuscripciones(rs.getBoolean("acepta_suscripciones"));
        return revista;
    }
    
   public void actualizarAnunciosRevista(EstadoRevistaDTO estadoDTO) throws SQLException{
       String updateQuery = "UPDATE revistas SET anuncios_bloqueados= ?  WHERE id_revista = ?";
       try(PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setBoolean(1, estadoDTO.isEstado());
            stmt.setLong(2, estadoDTO.getIdRevista());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected <= 0) {
                throw new DatosInvalidosUsuarioException();
           }
       } catch (Exception e) {
                throw new SQLException(e);
       }
   }
   
   public void actualizarCostoMantenimientoRevista(MantenimientoCostoDTO costoDTO) throws SQLException{
       String updateQuery = "UPDATE revistas SET costo_mantenimiento = ?  WHERE id_revista = ?";
       try(PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
           stmt.setDouble(1, costoDTO.getCostoNuevo());
           stmt.setLong(2, costoDTO.getIdRevista());
           
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected <= 0) {
                throw new DatosInvalidosUsuarioException();
            }
       } catch (Exception e) {
                throw new SQLException(e);
       }
   }
    
   public void activarRevista(Long idRevista) throws SQLException{
       String updateQuery = "UPDATE revistas SET estador_revista = ?  WHERE id_revista = ?";
       try(PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setString(1, "ACTIVA");
            stmt.setLong(2, idRevista);
            
            int io = stmt.executeUpdate();
            if (io <= 0) {
               throw new DatosInvalidosUsuarioException();
           }
       } catch (Exception e) {
           throw new SQLException(e);
       }
   }
    
} // fin de repositorio 
