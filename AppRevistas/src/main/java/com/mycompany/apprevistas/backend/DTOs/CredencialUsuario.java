/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionrevistas.backend.DTOs;

import com.mycompany.aplicacionrevistas.backend.util.RolUsuario;

/**
 *
 * @author kevin-mushin
 */
public class CredencialUsuario {
    
    private String nombreUsuario;
    private RolUsuario rolUsuario;
    private String[] permisosRol;

    public CredencialUsuario() {
    }
    
    /*AREA A SETTERS Y GETTERS*/

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    

    public String[] getPermisosRol() {
        return permisosRol;
    }

    public void setPermisosRol(String[] permisosRol) {
        this.permisosRol = permisosRol;
    }
    
    
    
    
    
}
