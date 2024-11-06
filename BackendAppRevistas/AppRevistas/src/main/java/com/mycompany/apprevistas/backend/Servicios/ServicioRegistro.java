/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasUsuarios;
import com.mycompany.apprevistas.backend.Excepciones.ConflictoUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.CreadoresModelo.CreadorUsuario;
import com.mycompany.apprevistas.backend.usuariosDTOs.RegistroUsuarioDTO;
import com.mycompany.apprevistas.backend.modelos.Usuario;

/**
 *
 * @author kevin-mushin
 */
public class ServicioRegistro {
    
    private ConsultasUsuarios consultaUsuario;
    private CreadorUsuario creadorUsuario;

    public ServicioRegistro() {
        this.creadorUsuario = new CreadorUsuario();
        this.consultaUsuario = new ConsultasUsuarios();
    }
    
    public void registrarUsuario(RegistroUsuarioDTO registroDTO) {
              if (consultaUsuario.esUsuarioExistente(registroDTO.getNombreUsuario())) {
                  throw new ConflictoUsuarioException();
             }
              registroDTO.convertirStringAEnum(); // convertir el valor del json a enum 
        
             if (!registroDTO.esRegistroValido()) {
                  throw new DatosInvalidosUsuarioException();
               }
              Usuario usuario = creadorUsuario.validarRegistroUsuario(registroDTO);
              consultaUsuario.guardarUsuario(usuario);
    }

    public void registrarAdministrador(RegistroUsuarioDTO registroDTO) {
        if (consultaUsuario.esUsuarioExistente(registroDTO.getNombreUsuario())) {
                  throw new ConflictoUsuarioException();
         }
          registroDTO.convertirStringAEnum(); // convertir el valor del json a enum 
          Usuario usuario = creadorUsuario.validarRegistroUsuario(registroDTO);
          consultaUsuario.guardarUsuario(usuario);
    }
    
    
    
        
    
}
