/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.usuariosDTOs;

import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.util.RolUsuario;

/**
 *
 * @author kevin-mushin
 */
public class RegistroUsuarioDTO {
    
    private String nombreUsuario;
    private String password;
    private String nombreCompleto;
    private String rol;
    private RolUsuario rolUsuario;
    public RegistroUsuarioDTO() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public void convertirStringAEnum() throws DatosInvalidosUsuarioException{
        try {
            rolUsuario = RolUsuario.valueOf(rol.toUpperCase());  
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new DatosInvalidosUsuarioException();
        }
    }
    
   
    public boolean esRegistroValido(){
        
        String patronNombre = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$";
        
    return rolUsuario != null 
            && nombreUsuario != null && !nombreUsuario.trim().isEmpty()  && !nombreUsuario.contains(" ")&& nombreUsuario.length() <= 15 
            && password != null && !password.trim().isEmpty() && !password.contains(" ")
            && nombreCompleto != null && !nombreCompleto.trim().isEmpty() && nombreCompleto.matches(patronNombre)
            && (rolUsuario == RolUsuario.COMPRADOR || rolUsuario == RolUsuario.EDITOR || rolUsuario == RolUsuario.SUSCRIPTOR); 
    }

}
