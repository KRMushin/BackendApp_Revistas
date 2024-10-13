/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.ConsultasModelos.ConsultaUsuarios;
import com.mycompany.apprevistas.Excepciones.ConflictoUsuarioException;
import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.CreadoresModelo.CreadorUsuario;
import com.mycompany.apprevistas.backend.usuariosDTOs.RegistroUsuarioDTO;
import com.mycompany.apprevistas.backend.modelos.Usuario;

/**
 *
 * @author kevin-mushin
 */
public class ServicioRegistro {
    
    private ConsultaUsuarios consultaUsuario;
    private CreadorUsuario creadorUsuario;

    public ServicioRegistro() {
        this.creadorUsuario = new CreadorUsuario();
        this.consultaUsuario = new ConsultaUsuarios();
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
    
    
    
        
    
}
