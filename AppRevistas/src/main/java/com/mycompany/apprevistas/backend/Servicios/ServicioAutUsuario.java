/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.Excepciones.CredencialInvalidaException;
import com.mycompany.apprevistas.backend.DTOs.CredencialUsuario;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioUsuarios;
import com.mycompany.apprevistas.backend.DTOs.InicioSesionDTO;
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
