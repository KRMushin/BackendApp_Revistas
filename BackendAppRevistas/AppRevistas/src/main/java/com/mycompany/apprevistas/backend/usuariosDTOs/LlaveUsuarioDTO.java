/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.usuariosDTOs;

import com.mycompany.apprevistas.backend.util.RolUsuario;

/**
 *
 * @author kevin-mushin
 */
public class LlaveUsuarioDTO {
    
    private String nombreUsuario;
    private String password;
    private RolUsuario rolUsuario;

    public LlaveUsuarioDTO() {
    }

    public LlaveUsuarioDTO(String nombreUsuario, String password, RolUsuario rolUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rolUsuario = rolUsuario;
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
    
    
}
