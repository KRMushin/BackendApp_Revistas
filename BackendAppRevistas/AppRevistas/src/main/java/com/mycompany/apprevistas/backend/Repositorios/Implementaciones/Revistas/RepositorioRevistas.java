/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.Excepciones.ErrorInternoException;
import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.Repositorios.RepositorioCrud;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.NuevoCostoDTO;
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

    @Override // listar por nombre de usuario
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
        String insertModel = "INSERT INTO revistas (id_categoria, titulo_revista, nombre_autor, descripcion, fecha_publicacion, "
                + "costo_mantenimiento, costo_bloqueo_anuncios) values(?,?,?,?,?,?,?)";
      
        try(PreparedStatement stmt = conn.prepareStatement(insertModel, PreparedStatement.RETURN_GENERATED_KEYS)) {
                  stmt.setLong(1, modelo.getIdCategoria());
                  stmt.setString(2, modelo.getTituloRevista());
                  stmt.setString(3, modelo.getNombreAutor());
                  stmt.setString(4, modelo.getDescripcion());
                  stmt.setDate(5, Date.valueOf(modelo.getFechaCreacion()));
                  stmt.setDouble(6, modelo.getCostoMantenimiento());
                  stmt.setDouble(7, modelo.getCostoBloqueosAnuncios());
                  
                  int filasAfectadas = stmt.executeUpdate();
                  if (filasAfectadas <= 0) {
                      throw new ErrorInternoException("No se guardo correctamente la revista");
                 }
                  try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            Long idRevista = generatedKeys.getLong(1);
                            modelo.setIdRevista(idRevista);
                        } else {
                      throw new ErrorInternoException("No se generaron las llaves");
                        }
                }
                  return modelo;
        }
    }

    @Override
    public Revista actualizar(Revista modelo) throws SQLException {
        /*          METODO PARA ACTUALIZAR LOS ESTADOS DE LA REVISTA */
        String updateQuery = "UPDATE revistas SET descripcion = ? , titulo_revista = ? WHERE id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setString(1, modelo.getDescripcion());
            stmt.setString(2, modelo.getTituloRevista());
            
             int affectedRows = stmt.executeUpdate();
             if (affectedRows <= 0) {
                   throw new ErrorInternoException("No se actualizo correctamente la revista");
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
                return crearRevista(rs);
            }else{
                 throw new NotFoundException("no se encontro con ese id");
             }
        } 
    }
    
    public void activarRevista(Long idRevista) throws SQLException {
        
        String insertUpdate = " UPDATE revistas SET estado_revista = ? WHERE id_revista = ? ";
        try(PreparedStatement stmt = conn.prepareStatement(insertUpdate)){
             stmt.setString(1, "ACTIVA");
             stmt.setLong(2, idRevista);
             int l = stmt.executeUpdate();
             
        }
    
    }
   
    private Revista crearRevista(ResultSet rs) throws SQLException {
        Revista revista = new Revista();
        revista.setIdRevista(rs.getLong("id_revista"));
        revista.setIdCategoria(rs.getLong("id_categoria"));
        revista.setTituloRevista(rs.getString("titulo_revista"));
        revista.setNombreAutor(rs.getString("nombre_autor"));
        revista.setDescripcion(rs.getString("descripcion"));
        revista.setFechaCreacion((rs.getDate("fecha_publicacion")).toLocalDate());
        revista.setCostoMantenimiento(rs.getDouble("costo_mantenimiento"));
        revista.setEstadoRevista(rs.getString("estado_revista"));
        return revista;
    }

    public void actualizarCostoRevista(NuevoCostoDTO nuevoCosto) throws SQLException {
        String insertUpdate = "UPDATE revistas SET " + nuevoCosto.getTipoCosto().obtenerConsulta() + "= ? WHERE id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertUpdate)){
            stmt.setDouble(1, nuevoCosto.getCosto());
            stmt.setLong(2, nuevoCosto.getIdRevista());
            
            int filas = stmt.executeUpdate();
            
            if (filas <= 0) {
                throw new ErrorInternoException(" Verificar la cadena de consulta ");
            }
        }
    }
} // fin de repositorio 
