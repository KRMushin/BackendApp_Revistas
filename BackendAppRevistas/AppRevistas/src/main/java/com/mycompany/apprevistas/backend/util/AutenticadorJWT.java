/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import com.mycompany.apprevistas.backend.Excepciones.SinAutorizacionException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.ws.rs.core.HttpHeaders;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

/**
 *
 * @author kevin-mushin
 */
public class AutenticadorJWT {

    private final SecretKey LLAVE_SECRETA = Keys.hmacShaKeyFor("H8nK@z3$Q2#Vf1P&jD5!xZr6L*Q4uT8v".getBytes(StandardCharsets.UTF_8));
    
    public Claims validarTokenl(HttpHeaders headerRequest)  {
        
        String tokenRequest = headerRequest.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (tokenRequest == null || !tokenRequest.startsWith("Bearer ")) {
            throw new SinAutorizacionException("No posees autorización para acceder al recurso.");
        }
        String token = tokenRequest.substring(7);
        
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(LLAVE_SECRETA)  
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("TOKEN VALIDADO CORRECTAMENTE ---------------------->");
            return claims;
        } catch (ExpiredJwtException e) {
            throw new SinAutorizacionException("El token ha expirado. Por favor, solicita uno nuevo."); // tokenexpirado
        } catch (MalformedJwtException e) {
            throw new SinAutorizacionException("El token es inválido. El formato del token no es correcto."); // sin formato correcto
        } catch (SignatureException e) {
            throw new SinAutorizacionException("El token ha sido manipulado. Firma inválida."); // firma dañada
        } catch (UnsupportedJwtException e) {
            throw new SinAutorizacionException("El token no es soportado."); // token no valido
        } catch (IllegalArgumentException e) {
            throw new SinAutorizacionException("El token proporcionado es incorrecto."); // token invalido
        }
    }
}
