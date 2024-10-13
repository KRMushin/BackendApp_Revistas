/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import com.mycompany.apprevistas.backend.usuariosDTOs.LlaveUsuarioDTO;
import com.mycompany.apprevistas.backend.usuariosDTOs.LoginDTO;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

/**
 *
 * @author kevin-mushin
 */
public class ServicioJWT {
    
    private final String LLAVE_SECRETA = "H8nK@z3$Q2#Vf1P&jD5!xZr6L*Q4uT8v";
    private final long TIEMPO_EXPIRACION = 3600000;
    
    public String generarToken(LlaveUsuarioDTO llave){
        SecretKey key = Keys.hmacShaKeyFor(LLAVE_SECRETA.getBytes());

        return Jwts.builder()
                .setSubject(llave.getNombreUsuario())
                .claim("rol", llave.getRolUsuario().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION))
                .signWith(key, SignatureAlgorithm.HS256) // Usa el objeto Key aquí
                .compact();
    }
    
}
