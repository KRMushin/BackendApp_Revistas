/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.Excepciones.ConflictoUsuarioException;
import com.mycompany.apprevistas.Excepciones.CredencialInvalidaException;
import com.mycompany.apprevistas.Excepciones.DatabaseException;
import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.DTOs.CredencialUsuario;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioUsuarios;
import com.mycompany.apprevistas.backend.DTOs.InicioSesionDTO;
import com.mycompany.apprevistas.backend.DTOs.LoginDTO;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import com.mycompany.apprevistas.backend.util.EncriptadorDatos;
import com.mycompany.apprevistas.backend.util.ServicioJWT;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ServicioAutUsuario {
    
    private RepositorioUsuarios repositorioUsuarios;
    private ServicioJWT serviceJWT;
    private EncriptadorDatos encriptador;
    
    
    public ServicioAutUsuario() {
        this.repositorioUsuarios = new RepositorioUsuarios();
        this.encriptador = new EncriptadorDatos();
        this.serviceJWT = new ServicioJWT();
        
    }

    public CredencialUsuario obtenerCredencialUsuario(LoginDTO loginDTO) {
         
         if (!loginDTO.esValida()) {
             throw new DatosInvalidosUsuarioException();
         }
         Optional<LoginDTO> credencial = obtenerCredencialUsuario(loginDTO.getNombreUsuario());
         if (!credencial.isPresent()) {
             throw new ConflictoUsuarioException();
         }
         
         return autenticarUsuario(loginDTO,credencial.get());
    }

    private Optional<LoginDTO> obtenerCredencialUsuario(String nombreUsuario) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repositorioUsuarios.setConn(conn);
              return repositorioUsuarios.obtenerLlaveEntidad(nombreUsuario);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
        
    }

    private CredencialUsuario autenticarUsuario(LoginDTO loginDTO, LoginDTO credencial) {
        CredencialUsuario cr = new CredencialUsuario();
         if (encriptador.contraseñasIguales(loginDTO.getPassword(), credencial.getPassword())) {
             cr.setEstaAutenticado(true);
             cr.setToken(serviceJWT.generarToken(credencial));
        }
         else{
             throw new DatosInvalidosUsuarioException();
         }
        return cr;
    }
}
