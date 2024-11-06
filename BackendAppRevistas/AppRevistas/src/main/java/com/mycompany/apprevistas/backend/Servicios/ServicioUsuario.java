/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.backend.ActualizacionesModelo.ActualizacionesUsuario;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasUsuarios;
import com.mycompany.apprevistas.backend.CreadoresModelo.CreadorPreferenciaUsuario;
import com.mycompany.apprevistas.backend.CreadoresModelo.CreadorUsuario;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.ErrorInternoException;
import com.mycompany.apprevistas.backend.modelos.FotoUsuario;
import com.mycompany.apprevistas.backend.usuariosDTOs.UsuarioDTO;
import com.mycompany.apprevistas.backend.modelos.Usuario;
import com.mycompany.apprevistas.backend.util.AlmacenFotos;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author kevin-mushin
 */
public class ServicioUsuario {
    
    private CreadorUsuario creadorUsuario;
    private CreadorPreferenciaUsuario creadorPreferencias;
    private ConsultasUsuarios consultaUsuario;
    private ActualizacionesUsuario actualizacionUsuario;
    private AlmacenFotos almacenFotos;
    
    public ServicioUsuario() {
        this.creadorUsuario = new CreadorUsuario();
        this.creadorPreferencias = new CreadorPreferenciaUsuario();
        this.consultaUsuario = new ConsultasUsuarios();
        this.actualizacionUsuario = new ActualizacionesUsuario();
        this.almacenFotos = new AlmacenFotos();
    }

    public Optional<Usuario> obtenerPerfilUsurio(String nombreUsuario) {
        return consultaUsuario.obtenerUsuarioConPreferencias(nombreUsuario);
    }

    public Optional<File> obtenerFotoPerfil(String nombreUsuario) {
            return consultaUsuario.obtenerFotoPerfil(nombreUsuario);
    }

    public void actualizarUsuario(UsuarioDTO usuarioDTO) {
         Usuario usuario = creadorUsuario.validarDatosUsuario(usuarioDTO);
         usuario.setPreferenciasUsuario(creadorPreferencias.validarPreferencias(usuarioDTO));
        actualizacionUsuario.actualizarUsuarioConPreferencias(usuario);
    }

    public List<String> listarCompradores() {
        return consultaUsuario.obtnerCompradores();
    }

    public void guardarFotoPerfil(InputStream fotoInputStream, String nombreUsuario){
        if (!consultaUsuario.esUsuarioExistente(nombreUsuario)) {
            throw new DatosInvalidosUsuarioException();
        }
        FotoUsuario foto  = almacenFotos.guardarFotoUsuario(fotoInputStream, nombreUsuario); 
        if (foto == null) {
            throw new ErrorInternoException();
        }
        consultaUsuario.almacenarRutaFoto(foto);
    }
    
    
//    public void actualizarFotoPerfil(InputStream fotoInputStream, FormDataContentDisposition fotoMetaData, String nombreUsuario) {
//        if (!consultaUsuario.esUsuarioExistente(nombreUsuario)) {
//            throw new DatosInvalidosUsuarioException();
//        }
//        String rutaFoto = 
//
//    }



    
    


    

    
}
