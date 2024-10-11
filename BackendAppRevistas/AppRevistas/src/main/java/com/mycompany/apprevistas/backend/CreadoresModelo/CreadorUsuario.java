/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.CreadoresModelo;

import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.DTOs.RegistroUsuarioDTO;
import com.mycompany.apprevistas.backend.DTOs.UsuarioDTO;
import com.mycompany.apprevistas.backend.entidades.Usuario;
import com.mycompany.apprevistas.backend.util.EncriptadorDatos;

/**
 *
 * @author kevin-mushin
 */
public class CreadorUsuario {
    
    private EncriptadorDatos encriptador;

    public CreadorUsuario() {
        this.encriptador = new EncriptadorDatos();
    }
    
    public Usuario validarRegistroUsuario(RegistroUsuarioDTO registroDTO) throws DatosInvalidosUsuarioException{
        
         try {
               Usuario usuario = new Usuario();
               usuario.setNombreUsuario(registroDTO.getNombreUsuario());
               usuario.setNombrePila(registroDTO.getNombreCompleto());
               usuario.setRolUsuario(registroDTO.getRolUsuario());
               usuario.setPassword(encriptador.encriptarPassword(registroDTO.getPassword()));
               return usuario;

         } catch (IllegalArgumentException | NullPointerException e) {
             System.out.println("Excepcion capturada en validaciones de usuario" + e);
            throw new DatosInvalidosUsuarioException();
        }
    }

    public Usuario validarDatosUsuario(UsuarioDTO usuarioDTO) throws DatosInvalidosUsuarioException{
        try {
            Usuario usuario = new Usuario();
            usuario.setNombrePila(usuarioDTO.getNombrePila());
            if (usuarioDTO.getDescripcion() != null) {
                usuario.setDescripcion(usuarioDTO.getDescripcion());
            }
            return usuario;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new DatosInvalidosUsuarioException();
        }


    }
    
    
}
