/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.usuariosDTOs;

import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class UsuarioDTO {
    
    private String nombreUsuario;
    
    private String nombrePila;
    private String password;
    private String descripcion;
    private List<String> preferencias;
    private List<String> hobbies;
    private List<String> gustos;

    public UsuarioDTO() {
        
    }
    
    public String getNombrePila() {
        return nombrePila;
    }

    public void setNombrePila(String nombrePila) {
        this.nombrePila = nombrePila;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<String> preferencias) {
        this.preferencias = preferencias;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getGustos() {
        return gustos;
    }

    public void setGustos(List<String> gustos) {
        this.gustos = gustos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
    
    
    
}
