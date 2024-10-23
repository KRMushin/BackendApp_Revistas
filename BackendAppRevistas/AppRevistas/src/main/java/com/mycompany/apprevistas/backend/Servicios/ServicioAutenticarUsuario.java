/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasUsuarios;
import com.mycompany.apprevistas.backend.Excepciones.ConflictoUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.usuariosDTOs.CredencialUsuario;
import com.mycompany.apprevistas.backend.usuariosDTOs.LlaveUsuarioDTO;
import com.mycompany.apprevistas.backend.usuariosDTOs.LoginDTO;
import com.mycompany.apprevistas.backend.util.EncriptadorDatos;
import com.mycompany.apprevistas.backend.util.ServicioJWT;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ServicioAutenticarUsuario {
    
    private ConsultasUsuarios consultasUsuario;
    private ServicioJWT serviceJWT;
    private EncriptadorDatos encriptador;
    
    
    public ServicioAutenticarUsuario() {
        this.consultasUsuario = new ConsultasUsuarios();
        this.encriptador = new EncriptadorDatos();
        this.serviceJWT = new ServicioJWT();
        
    }

    public CredencialUsuario obtenerCredencialUsuario(LoginDTO loginDTO) {
         
         if (!loginDTO.esValida()) {
             throw new DatosInvalidosUsuarioException();
         }
         Optional<LlaveUsuarioDTO> credencial = consultasUsuario.obtenerCredencialUsuario(loginDTO.getNombreUsuario());   
         if (credencial.isEmpty()) {
             throw new DatosInvalidosUsuarioException();
         }
         
         return autenticarUsuario(loginDTO,credencial.get());
    }

    private CredencialUsuario autenticarUsuario(LoginDTO loginDTO, LlaveUsuarioDTO credencial) {
         if (encriptador.contraseñasIguales(loginDTO.getPassword(), credencial.getPassword())) {
             String token = serviceJWT.generarToken(credencial);
             return new CredencialUsuario(true,token);
         }
         else{
             throw new DatosInvalidosUsuarioException();
         }
    }
}
