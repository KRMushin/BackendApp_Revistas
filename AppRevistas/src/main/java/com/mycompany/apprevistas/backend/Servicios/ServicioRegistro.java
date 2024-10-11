/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.Excepciones.ConflictoUsuarioException;
import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.Excepciones.TransaccionFallidaException;
import com.mycompany.apprevistas.backend.CreadoresModelo.CreadorUsuario;
import com.mycompany.apprevistas.backend.DTOs.RegistroUsuarioDTO;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioFotosUsuarios;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioUsuarios;
import com.mycompany.apprevistas.backend.entidades.FotoUsuario;
import com.mycompany.apprevistas.backend.entidades.Usuario;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void registrarUsuario(RegistroUsuarioDTO registroDTO) {
        
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
    
    
    private void ingresarUsuarioSistema(Usuario usuario) {
    
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
        } catch (SQLException ex) {
            Logger.getLogger(ServicioRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        
    private boolean nombreUsuarioExistente(RegistroUsuarioDTO registroDTO) {
        
        if (registroDTO.getNombreUsuario() == null || registroDTO.getNombreUsuario().isEmpty()) {
            throw new DatosInvalidosUsuarioException();
        }
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repositorioUsuario.setConn(conn);
                Usuario usuario = repositorioUsuario.obtenerPorId(registroDTO.getNombreUsuario());
                return usuario != null;
        } catch(SQLException e){
            return true;
        }
    }
}
