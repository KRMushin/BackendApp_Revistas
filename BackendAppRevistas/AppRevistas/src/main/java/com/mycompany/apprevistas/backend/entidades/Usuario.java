/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.entidades;

import com.mycompany.apprevistas.backend.util.RolUsuario;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class Usuario {
    
    private String nombreUsuario;
    private String password; //encriptada
    private RolUsuario rolUsuario;
    private String nombrePila;
    private String descripcion;
    private List<PreferenciaUsuario> preferenciasUsuario;

    public Usuario() {
        this.descripcion = null;
    }
    /*AREA DE SETTERS Y GETTERS*/

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

    public String getNombrePila() {
        return nombrePila;
    }

    public void setNombrePila(String nombrePila) {
        this.nombrePila = nombrePila;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PreferenciaUsuario> getPreferenciasUsuario() {
        return preferenciasUsuario;
    }

    public void setPreferenciasUsuario(List<PreferenciaUsuario> preferenciasUsuario) {
        this.preferenciasUsuario = preferenciasUsuario;
    }
    
    
    
    
}
