/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.anuncios;

import com.mycompany.apprevistas.backend.AnunciosDTOs.LlaveAnuncioDTO;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.ErrorInternoException;
import com.mycompany.apprevistas.backend.Repositorios.RepositorioCrud;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.constantes.TipoAnuncio;
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
public class RepositorioAnuncios implements RepositorioCrud<Anuncio,Long,String>  {

    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<Anuncio> listar(String parametro) throws SQLException {
    List<Anuncio> anuncios = new ArrayList<>();
            String selectQuery = "";

        if (parametro.equals("obtenertodas")) {
                selectQuery = "SELECT * FROM anuncios";
        } else {
                selectQuery = "SELECT * FROM anuncios WHERE nombre_usuario = ?";
        }

        try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
                if (!parametro.equals("obtenertodas")) {
                    stmt.setString(1, parametro); // Solo si no es "obtenertodas" seteamos el parámetro
                }

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    anuncios.add(crearAnuncio(rs));
                }
        } catch (SQLException e) {
                throw new SQLException("Error al listar los anuncios: " + e.getMessage(), e);
            }
        return anuncios;
    }

    @Override
    public Anuncio guardar(Anuncio modelo) throws SQLException {
        String insertQuery = "INSERT INTO anuncios(nombre_usuario, fecha_compra, tipo_anuncio, ruta_imagen_texto, ruta_video, ruta_texto, precio_total, dias_duracion) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, modelo.getNombreUsuario());
                stmt.setDate(2, Date.valueOf(modelo.getFechaCompra()));
                stmt.setString(3, modelo.getTipoAnuncio().toString());
             
                 if (modelo.getTipoAnuncio() == TipoAnuncio.TEXTO) {
                     stmt.setString(6, modelo.getRutaTexto());
                     stmt.setString(4, null);
                     stmt.setString(5, null);
                }else if (modelo.getTipoAnuncio() == TipoAnuncio.IMAGEN_TEXTO) {
                     stmt.setString(4, modelo.getRutaImagenTexto());
                     stmt.setString(5, null);
                     stmt.setString(6, modelo.getRutaTexto());
                }else{
                     stmt.setString(5, modelo.getRutaVideo());
                     stmt.setString(4, null);
                     stmt.setString(6, null);
                }
                 stmt.setDouble(7, modelo.getPrecioTotal());
                 stmt.setInt(8, modelo.getDiasDuracion());
                 
                stmt.executeUpdate();
                
                return modelo;
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Anuncio actualizar(Anuncio modelo) throws SQLException {

         String updateQuery = "UPDATE anuncios SET habilitado = ? WHERE id_anuncio = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setBoolean(1,modelo.isAnuncioHabilitado());
                stmt.setLong(2, modelo.getIdAnuncio());
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected < 0) {
                    throw new ErrorInternoException(" Error en el procesamiento de datos");
                }
                return modelo;
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el anuncio: " + e.getMessage(), e);
        }
        
    }

    @Override
    public Anuncio obtenerPorId(Long identificador) throws SQLException {
        String selectQuery = "SELECT * FROM anuncios WHERE id_anuncio = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
            stmt.setLong(1, identificador);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return crearAnuncio(rs);
            }else{
                throw new DatosInvalidosUsuarioException();
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el anuncio: " + e.getMessage(), e);
        }

    }
    
    public List<LlaveAnuncioDTO> obtnerLlavesAnuncios() throws SQLException{
        List<LlaveAnuncioDTO> llaves = new ArrayList<>();
        
        String selectQuery = "SELECT id_anuncio, ruta_imagen_texto, ruta_video, ruta_texto , tipo_anuncio FROM anuncios WHERE habilitado = TRUE ";
        
        try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                  llaves.add(crearLlaveDTO(rs));
            }
            return llaves;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el anuncio: " + e.getMessage(), e);
        }
    }
    
    private Anuncio crearAnuncio(ResultSet rs) throws SQLException{
            Anuncio anuncio = new Anuncio();
            anuncio.setIdAnuncio(rs.getLong("id_anuncio"));
            anuncio.setNombreUsuario(rs.getString("nombre_usuario"));
            anuncio.setFechaCompra(rs.getDate("fecha_compra").toLocalDate());
            anuncio.setTipoAnuncio(TipoAnuncio.valueOf(rs.getString("tipo_anuncio")));
            anuncio.setRutaImagenTexto(rs.getString("ruta_imagen_texto"));
            anuncio.setRutaVideo(rs.getString("ruta_video"));
            anuncio.setRutaTexto(rs.getString("ruta_texto"));
            anuncio.setPrecioTotal(rs.getDouble("precio_total"));
            anuncio.setAnuncioHabilitado(rs.getBoolean("habilitado"));
            anuncio.setDiasDuracion(rs.getInt("dias_duracion"));
            return anuncio;
    }

    private LlaveAnuncioDTO crearLlaveDTO(ResultSet rs) throws SQLException {
        LlaveAnuncioDTO anuncio = new LlaveAnuncioDTO();
            anuncio.setIdAnuncio(rs.getLong("id_anuncio"));
            anuncio.setTipoAnuncio(TipoAnuncio.valueOf(rs.getString("tipo_anuncio")));
            anuncio.setRutaTextoImagen(rs.getString("ruta_imagen_texto"));
            anuncio.setRutaVideo(rs.getString("ruta_video"));
            anuncio.setContenidoTexto(rs.getString("ruta_texto"));
            return anuncio;
    }
}
