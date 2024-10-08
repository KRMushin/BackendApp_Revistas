/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.Servicios;

import com.mycompany.aplicacionrevistas.Excepciones.CredencialInvalidaException;
import com.mycompany.aplicacionrevistas.backend.DTOs.CredencialUsuario;
import com.mycompany.aplicacionrevistas.backend.Repositorios.Implementaciones.RepositorioUsuarios;
import com.mycompany.aplicacionrevistas.backend.DTOs.InicioSesionDTO;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioAutUsuario {
    
    private RepositorioUsuarios repositorioUsuarios;
    
    public ServicioAutUsuario() {
        this.repositorioUsuarios = new RepositorioUsuarios();
    }

    public CredencialUsuario obtenerCredencialesUsuario(InicioSesionDTO inicioSesionDTO) throws CredencialInvalidaException, SQLException{

         if (!inicioSesionDTO.esValida()) {
                  throw new CredencialInvalidaException();
         }
         
         CredencialUsuario cu = repositorioUsuarios.obtenerLlaveEntidad(inicioSesionDTO.getNombreUsuario());
//         if (!autenticadorPassword.contraseñaCorrecta(inicioSesionDTO.getPassword(), cu.get)) {
//            
//        }
         
         
         
        
        
         return null;
    }
}
