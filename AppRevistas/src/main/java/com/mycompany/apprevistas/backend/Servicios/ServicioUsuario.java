/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.Servicios;

import com.mycompany.aplicacionrevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.aplicacionrevistas.Excepciones.TransaccionFallidaException;
import com.mycompany.aplicacionrevistas.backend.CreadoresModelo.CreadorPreferenciaUsuario;
import com.mycompany.aplicacionrevistas.backend.CreadoresModelo.CreadorUsuario;
import com.mycompany.aplicacionrevistas.backend.DTOs.UsuarioDTO;
import com.mycompany.aplicacionrevistas.backend.Repositorios.Implementaciones.RepositorioFotosUsuarios;
import com.mycompany.aplicacionrevistas.backend.Repositorios.Implementaciones.RepositorioPreferenciasUsuario;
import com.mycompany.aplicacionrevistas.backend.Repositorios.Implementaciones.RepositorioUsuarios;
import com.mycompany.aplicacionrevistas.backend.entidades.FotoUsuario;
import com.mycompany.aplicacionrevistas.backend.entidades.PreferenciaUsuario;
import com.mycompany.aplicacionrevistas.backend.entidades.Usuario;
import com.mycompany.aplicacionrevistas.backend.util.ConexionBaseDatos;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author kevin-mushin
 */
public class ServicioUsuario {
    
    private RepositorioUsuarios repositorioUsuario;
    private RepositorioPreferenciasUsuario repositorioPrefUsuario;
    private CreadorUsuario creadorUsuario;
    private CreadorPreferenciaUsuario creadorPreferencias;
    private RepositorioFotosUsuarios repositorioFotosUsuario;
    
    public ServicioUsuario() {
        this.repositorioUsuario = new RepositorioUsuarios();
        this.repositorioPrefUsuario = new RepositorioPreferenciasUsuario();
        this.creadorUsuario = new CreadorUsuario();
        this.creadorPreferencias = new CreadorPreferenciaUsuario();
        this.repositorioFotosUsuario = new RepositorioFotosUsuarios();
    }

    public void actualizarUsuario(UsuarioDTO usuarioDTO) throws TransaccionFallidaException, DatosInvalidosUsuarioException, SQLException{
        
        Usuario usuario = creadorUsuario.validarDatosUsuario(usuarioDTO);
        List<PreferenciaUsuario> preferencias = creadorPreferencias.validarPreferencias(usuarioDTO);
        
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioUsuario.setConn(conn);
             repositorioPrefUsuario.setConn(conn);
             
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
                
            try {
                  repositorioUsuario.actualizar(usuario);
                  repositorioPrefUsuario.guardarPreferencias(preferencias,usuario);
                  conn.commit();
            } catch (SQLException e) {
                  conn.rollback();
                  throw new TransaccionFallidaException();
            }
        }
    }
    public Optional<Usuario> obtenerUsuario(String nombreUsuario) {

        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioUsuario.setConn(conn);
             repositorioPrefUsuario.setConn(conn);
             
             Usuario usuario = repositorioUsuario.obtenerPorId(nombreUsuario);
             if (usuario != null) {
                 usuario.setPreferenciasUsuario(repositorioPrefUsuario.listar(nombreUsuario));
                 return Optional.of(usuario);
              }
             return Optional.empty();
             
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    public FotoUsuario actualizarFotoUsuario(InputStream fileInputStream, FormDataContentDisposition archivoDatos, String nombreUsuario) throws SQLException, DatosInvalidosUsuarioException {
            
        String directorioImagenes = "/home/servidor/app-revistas/img-perfil";
        String nombreArchivo = nombreUsuario + "_" + archivoDatos.getFileName(); // Generar nombre único para la imagen
    
        Path rutaArchivo = Paths.get(directorioImagenes + nombreArchivo);// Ruta completa del archivo
        
        try {
        
            Files.copy(fileInputStream, rutaArchivo, StandardCopyOption.REPLACE_EXISTING);//copiar la imagen en el server
            String fotoUrl = "http://localhost:8080/imagenes/usuarios/" + nombreArchivo;// ruta que va en la db
            
            return repositorioFotosUsuario.actualizar(new FotoUsuario(nombreUsuario,fotoUrl));
            
        } catch (IOException e) {
            throw new DatosInvalidosUsuarioException();
        }

    }

    public File obtenerFotoPerfil(String nombreUsuario) throws SQLException{
        
             // Obtener la información de la foto desde la base de datos
    FotoUsuario fotoUsuario = repositorioFotosUsuario.obtenerPorId(nombreUsuario);

    // Verificar si la foto existe en la base de datos
    if (fotoUsuario != null) {
        String ruta = fotoUsuario.getFotoUrl();
        File archFoto = new File(ruta); // Obtener el archivo de la foto desde la ruta

        // Verificar si el archivo realmente existe
        if (archFoto.exists()) {
            return archFoto;  // Devolver el archivo si existe
        }
    }
    // Si no se encuentra la foto o no existe el archivo, devolver null
    return null;
    }
}
