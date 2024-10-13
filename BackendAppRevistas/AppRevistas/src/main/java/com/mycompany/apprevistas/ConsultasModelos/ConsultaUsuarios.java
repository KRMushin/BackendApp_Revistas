/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.ConsultasModelos;

import com.mycompany.apprevistas.Excepciones.DatabaseException;
import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.Excepciones.NotFoundException;
import com.mycompany.apprevistas.Excepciones.TransaccionFallidaException;
import com.mycompany.apprevistas.backend.usuariosDTOs.LoginDTO;
import com.mycompany.apprevistas.backend.usuariosDTOs.RegistroUsuarioDTO;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioFotosUsuarios;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioPreferenciasUsuario;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioUsuarios;
import com.mycompany.apprevistas.backend.modelos.FotoUsuario;
import com.mycompany.apprevistas.backend.modelos.Usuario;
import com.mycompany.apprevistas.backend.usuariosDTOs.LlaveUsuarioDTO;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
public class ConsultaUsuarios {
    
    private final RepositorioUsuarios repositorioUsuarios;
    private final RepositorioFotosUsuarios repositorioFotos; 
    private final RepositorioPreferenciasUsuario repositorioPrefUsuario;
    
    public ConsultaUsuarios() {
        this.repositorioUsuarios = new RepositorioUsuarios();
        this.repositorioFotos = new RepositorioFotosUsuarios();
        this.repositorioPrefUsuario = new RepositorioPreferenciasUsuario();
    }
    
    public Usuario guardarUsuario(Usuario usuario){
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repositorioUsuarios.setConn(conn);
              return repositorioUsuarios.guardar(usuario);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    public Optional<LlaveUsuarioDTO> obtenerCredencialUsuario(String nombreUsuario) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repositorioUsuarios.setConn(conn);
              return repositorioUsuarios.obtenerLlaveEntidad(nombreUsuario);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public Optional<Usuario> obtenerUsuarioConPreferencias(String nombreUsuario) {

        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioUsuarios.setConn(conn);
             repositorioPrefUsuario.setConn(conn);
             
             Usuario usuario = repositorioUsuarios.obtenerPorId(nombreUsuario);
             if (usuario != null) {
                 usuario.setPreferenciasUsuario(repositorioPrefUsuario.listar(nombreUsuario));
                 return Optional.of(usuario);
              }else{
                 throw new NotFoundException();
             }
             
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    public Optional<File> obtenerFotoPerfil(String nombreUsuario) {
        
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repositorioFotos.setConn(conn);
            FotoUsuario fotoUsuario = repositorioFotos.obtenerPorId(nombreUsuario);
            
            if (fotoUsuario.getFotoUrl() != null) {
                String ruta = fotoUsuario.getFotoUrl();
                File archFoto = new File(ruta);
                
                // Verificar si el archivo realmente existe
                if (archFoto.exists()) {
                    return Optional.of(archFoto);  // Devolver el archivo si existe
                }else{
                        throw new NotFoundException();
                }
            }else{
                    throw new NotFoundException();
            }
            
        } catch (SQLException ex) {
                throw new DatabaseException(ex);
        }
    }
    
    public boolean esUsuarioExistente(String nombreUsuario) {
        
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new DatosInvalidosUsuarioException();
        }
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repositorioUsuarios.setConn(conn);
                Usuario usuario = repositorioUsuarios.obtenerPorId(nombreUsuario);
                return usuario != null;
        } catch(SQLException e){
            return true;
        }
    }
    
    public Optional<File> ingresarUsuarioASistema(Usuario usuario) {
    
            try (Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repositorioFotos.setConn(conn);
            FotoUsuario fotoUsuario = repositorioFotos.obtenerPorId(usuario.getNombreUsuario());

            if (fotoUsuario != null) {
                String ruta = fotoUsuario.getFotoUrl();
                File archFoto = new File(ruta);

                // Verificar si el archivo existe y es accesible
                if (archFoto.exists() && archFoto.isFile()) {
                    return Optional.of(archFoto);
                } else {
                    return Optional.empty();
                }
            } else {
                    return Optional.empty();
            }

        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }
}
