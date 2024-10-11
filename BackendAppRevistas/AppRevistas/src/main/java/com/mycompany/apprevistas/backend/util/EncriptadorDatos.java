/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author kevin-mushin
 */
public class EncriptadorDatos {

    public String encriptarPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
    public boolean contraseñasIguales(String passwordNoEncriptada, String passwordEncriptada) {
        return BCrypt.checkpw(passwordNoEncriptada, passwordEncriptada);
    }
    
}
