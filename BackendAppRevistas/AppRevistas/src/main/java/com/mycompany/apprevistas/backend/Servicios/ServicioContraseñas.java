/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.ConsultasModelos.ConsultaUsuarios;
import com.mycompany.apprevistas.Excepciones.DatabaseException;
import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.usuariosDTOs.ActualizarContraseñaDTO;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioContraseñas;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import com.mycompany.apprevistas.backend.util.EncriptadorDatos;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioContraseñas {
    
    private RepositorioContraseñas repositorioContraseña;
    private EncriptadorDatos encriptador;
    private ConsultaUsuarios consultaUsuario;
    
    public ServicioContraseñas() {
        this.repositorioContraseña = new RepositorioContraseñas();
        this.encriptador = new EncriptadorDatos();
        this.consultaUsuario = new ConsultaUsuarios();
    }
    
    public void actualizarContraseña(ActualizarContraseñaDTO contraseñaDTO) {
        
         if (!contraseñaDTO.esValido() || !consultaUsuario.esUsuarioExistente(contraseñaDTO.getNombreUsuario())) {
                 throw new DatosInvalidosUsuarioException();
         }

         try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
             repositorioContraseña.setConn(conn);
             String contraseñaActual = repositorioContraseña.obtenerPorId(contraseñaDTO.getNombreUsuario());
            
             if (!encriptador.contraseñasIguales(contraseñaDTO.getActualPassword(),contraseñaActual)) {
                throw new DatosInvalidosUsuarioException();
            }
             String nuevaContraseña = encriptador.encriptarPassword(contraseñaDTO.getNuevaPassword());
             contraseñaDTO.setNuevaPassword(nuevaContraseña);
             repositorioContraseña.actualizar(contraseñaDTO);
             
        } catch (SQLException e) {
                throw new DatabaseException(e);
        }
        
    }
    
    
    
    
    
    
    
    
}
