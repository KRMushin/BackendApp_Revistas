/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.Servicios;

import com.mycompany.aplicacionrevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.aplicacionrevistas.backend.DTOs.ActContraseñaDTO;
import com.mycompany.aplicacionrevistas.backend.Repositorios.Implementaciones.RepositorioContraseñas;
import com.mycompany.aplicacionrevistas.backend.util.EncriptadorDatos;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioContraseñas {
    
    private RepositorioContraseñas repositorioContraseña;
    private EncriptadorDatos encriptador;
    
    public ServicioContraseñas() {
        this.repositorioContraseña = new RepositorioContraseñas();
        this.encriptador = new EncriptadorDatos();
    }
    
    public void actualizarContraseña(ActContraseñaDTO contraseñaDTO) throws DatosInvalidosUsuarioException, SQLException{
        
         if (!contraseñaDTO.esValido()) {
                 throw new DatosInvalidosUsuarioException();
         }
        
         String contraseñaActual = repositorioContraseña.obtenerPorId(contraseñaDTO.getNombreUsuario());

         if (!encriptador.contraseñasIguales(contraseñaActual, contraseñaDTO.getActualPassword())) {
            throw new DatosInvalidosUsuarioException();
        }

         String nuevaContraseña = encriptador.encriptarPassword(contraseñaDTO.getNuevaPassword());
         contraseñaDTO.setNuevaPassword(nuevaContraseña);
         repositorioContraseña.actualizar(contraseñaDTO);
        
    }
    
    
    
    
    
    
    
    
}
