/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.ConsultasModelos.ConsultaUsuarios;
import com.mycompany.apprevistas.Excepciones.ConflictoUsuarioException;
import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
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
    
    private ConsultaUsuarios consultasUsuario;
    private ServicioJWT serviceJWT;
    private EncriptadorDatos encriptador;
    
    
    public ServicioAutenticarUsuario() {
        this.consultasUsuario = new ConsultaUsuarios();
        this.encriptador = new EncriptadorDatos();
        this.serviceJWT = new ServicioJWT();
        
    }

    public CredencialUsuario obtenerCredencialUsuario(LoginDTO loginDTO) {
         
         if (!loginDTO.esValida()) {
             throw new DatosInvalidosUsuarioException();
         }
         Optional<LlaveUsuarioDTO> credencial = consultasUsuario.obtenerCredencialUsuario(loginDTO.getNombreUsuario());   
         if (!credencial.isPresent()) {
             throw new ConflictoUsuarioException();
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
