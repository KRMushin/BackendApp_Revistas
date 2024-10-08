/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.Servicios;

import com.mycompany.aplicacionrevistas.Excepciones.ConflictoUsuarioException;
import com.mycompany.aplicacionrevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.aplicacionrevistas.Excepciones.TransaccionFallidaException;
import com.mycompany.aplicacionrevistas.backend.CreadoresModelo.CreadorUsuario;
import com.mycompany.aplicacionrevistas.backend.DTOs.RegistroUsuarioDTO;
import com.mycompany.aplicacionrevistas.backend.Repositorios.Implementaciones.RepositorioFotosUsuarios;
import com.mycompany.aplicacionrevistas.backend.Repositorios.Implementaciones.RepositorioUsuarios;
import com.mycompany.aplicacionrevistas.backend.entidades.FotoUsuario;
import com.mycompany.aplicacionrevistas.backend.entidades.Usuario;
import com.mycompany.aplicacionrevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioRegistro {
    
    private RepositorioUsuarios repositorioUsuario;
    private RepositorioFotosUsuarios repositorioFotos; 
    private CreadorUsuario creadorUsuario;

    public ServicioRegistro() {
        this.repositorioUsuario = new RepositorioUsuarios();
        this.repositorioFotos = new RepositorioFotosUsuarios();
        this.creadorUsuario = new CreadorUsuario();
    }
    
    public void registrarUsuario(RegistroUsuarioDTO registroDTO) throws DatosInvalidosUsuarioException, SQLException, TransaccionFallidaException, ConflictoUsuarioException{
        
              if (nombreUsuarioExistente(registroDTO)) {
                  throw new ConflictoUsuarioException();
             }
              registroDTO.convertirStringAEnum(); // convertir el valor del json a enum 
        
             if (!registroDTO.esRegistroValido()) {
                  throw new DatosInvalidosUsuarioException();
               }
              Usuario usuario = creadorUsuario.validarRegistroUsuario(registroDTO);
              ingresarUsuarioSistema(usuario);
    }
    
    
    private void ingresarUsuarioSistema(Usuario usuario) throws SQLException, TransaccionFallidaException{
    
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
              repositorioUsuario.setConn(conn);
              repositorioFotos.setConn(conn);
              
              if (conn.getAutoCommit()) {
                  conn.setAutoCommit(false);
              }
              try {
                      repositorioUsuario.guardar(usuario);
                      repositorioFotos.guardar(new FotoUsuario(usuario.getNombreUsuario(),null));
                      conn.commit();
              } catch (SQLException e) {
                      conn.rollback();
                      throw new TransaccionFallidaException();
             }
        } 
    }
        
    private boolean nombreUsuarioExistente(RegistroUsuarioDTO registroDTO) throws DatosInvalidosUsuarioException, SQLException{
        
        if (registroDTO.getNombreUsuario() == null || registroDTO.getNombreUsuario().isEmpty()) {
            throw new DatosInvalidosUsuarioException();
        }
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repositorioUsuario.setConn(conn);
                Usuario usuario = repositorioUsuario.obtenerPorId(registroDTO.getNombreUsuario());
                return usuario != null;
        } catch(SQLException e){
                throw new SQLException("Error de consulta en servicio registro");
        }
    }
}
