/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ActualizacionesModelo;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Excepciones.TransaccionFallidaException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioFotosUsuarios;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioPreferenciasUsuario;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioUsuarios;
import com.mycompany.apprevistas.backend.modelos.FotoUsuario;
import com.mycompany.apprevistas.backend.modelos.PreferenciaUsuario;
import com.mycompany.apprevistas.backend.modelos.Usuario;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author kevin-mushin
 */
public class ActualizacionesUsuario {
    
    private static final String DIRECTORIO_IMAGENES = "/home/servidor/app-revistas/img-perfil";

    private final RepositorioUsuarios repositorioUsuarios;
    private final RepositorioFotosUsuarios repositorioFotos; 
    private final RepositorioPreferenciasUsuario repositorioPrefUsuario;

    public ActualizacionesUsuario() {
        this.repositorioFotos = new RepositorioFotosUsuarios();
        this.repositorioUsuarios = new RepositorioUsuarios();
        this.repositorioPrefUsuario = new RepositorioPreferenciasUsuario();
    }
    
    public FotoUsuario actualizarFotoUsuario(InputStream fileInputStream, FormDataContentDisposition archivoDatos, String nombreUsuario) {
            
        String nombreArchivo = nombreUsuario + "_" + archivoDatos.getFileName(); // Generar nombre único para la imagen
    
        Path rutaArchivo = Paths.get(DIRECTORIO_IMAGENES + nombreArchivo);// Ruta completa del archivo
        
        try {
        
            Files.copy(fileInputStream, rutaArchivo, StandardCopyOption.REPLACE_EXISTING);//copiar la imagen en el server
            String fotoUrl = "http://localhost:8080/imagenes/usuarios/" + nombreArchivo;// ruta que va en la db
            return repositorioFotos.actualizar(new FotoUsuario(nombreUsuario,fotoUrl));
            
        } catch (IOException |SQLException e) {
            throw new DatabaseException(e);
        }       
    }
    
    public void actualizarUsuarioConPreferencias(Usuario usuario) {

        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioUsuarios.setConn(conn);
             repositorioPrefUsuario.setConn(conn);
             
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
                
            try {
                  repositorioUsuarios.actualizar(usuario);
                  repositorioPrefUsuario.eliminaroPreferencias(usuario.getNombreUsuario());
                  List<PreferenciaUsuario> pref = usuario.getPreferenciasUsuario();
                  
                  for (int i = 0; i < pref.size(); i++) {
                        PreferenciaUsuario p = pref.get(i);
                        if (p != null) {
                          repositorioPrefUsuario.guardar(p);
                      }
                }
                  conn.commit();
            } catch (SQLException e) {
                  conn.rollback();
                  throw new TransaccionFallidaException(e);
            }
        } catch (SQLException ex) {
             throw new DatabaseException(ex);
        }
    }
    
    
    
}
