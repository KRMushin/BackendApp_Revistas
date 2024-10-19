/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.anuncios.RepositorioConfigAnuncio;
import com.mycompany.apprevistas.backend.modelos.ConfiguracionAnuncio;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import com.mycompany.apprevistas.backend.util.TipoAnuncio;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasConfigAnuncios {
    
    private final RepositorioConfigAnuncio repositorioConfiguracion;

    public ConsultasConfigAnuncios() {
        this.repositorioConfiguracion = new RepositorioConfigAnuncio();
    }
    
    public List<ConfiguracionAnuncio> obtenerConfiguracionesAnuncios() {
        
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()){
            repositorioConfiguracion.setConn(conn);
            return repositorioConfiguracion.listarTodos();
        }catch(SQLException ex){
            throw new DatabaseException(ex);
        }
    }

    public void actualizarConfiguracion(ConfiguracionAnuncio config) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()){
            repositorioConfiguracion.setConn(conn);
            repositorioConfiguracion.actualizar(config);
        }catch(SQLException ex){
            throw new DatabaseException(ex);
        }
    }
    
    public ConfiguracionAnuncio obtenerDatosAnuncio(TipoAnuncio tipoAnuncio){
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioConfiguracion.setConn(conn);
             return repositorioConfiguracion.obtenerPorId(tipoAnuncio);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
