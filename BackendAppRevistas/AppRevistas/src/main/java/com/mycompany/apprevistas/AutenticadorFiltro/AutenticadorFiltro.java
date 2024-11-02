/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.AutenticadorFiltro;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.SecretKey;

/**
 *
 * @author kevin-mushin
 */
@Provider
public class AutenticadorFiltro implements ContainerRequestFilter {

    private final SecretKey LLAVE_SECRETA = Keys.hmacShaKeyFor("H8nK@z3$Q2#Vf1P&jD5!xZr6L*Q4uT8v".getBytes(StandardCharsets.UTF_8));
     private final Set<String> excludedPaths;

    public AutenticadorFiltro() {
        this.excludedPaths = new HashSet<>();
        excludedPaths.add("login/usuario");
        excludedPaths.add("registrar");
        
//        excludedPaths.add("revistasPorParametro/navegacion/revistas");
//        excludedPaths.add("categorias/obtnerTodas");
//        excludedPaths.add("categorias/obtnerTodasEtiquetas");

    }
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();

        if (isExcludedPath(path)) {
            return; 
        }
      String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (token == null || !token.startsWith("Bearer ")) {
            abortWithUnauthorized(requestContext, "No posees autorización para acceder al recurso.");
            return;
        }

        try {
            token = token.substring(7);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(LLAVE_SECRETA)  
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            requestContext.setProperty("claims", claims);
            System.out.println("TOKEN VALIDADO CORRECTAMENTE ---------------------->");

        } catch (ExpiredJwtException e) {
            abortWithUnauthorized(requestContext, "El token ha expirado. Por favor, solicita uno nuevo.");
        } catch (MalformedJwtException e) {
            abortWithUnauthorized(requestContext, "El token es inválido. El formato del token no es correcto.");
        } catch (SignatureException e) {
            abortWithUnauthorized(requestContext, "El token ha sido manipulado. Firma inválida.");
        } catch (UnsupportedJwtException e) {
            abortWithUnauthorized(requestContext, "El token no es soportado.");
        } catch (IllegalArgumentException e) {
            abortWithUnauthorized(requestContext, "El token proporcionado es incorrecto.");
        }
    }
    
    private boolean isExcludedPath(String path) {
        return excludedPaths.contains(path);
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext, String message) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(message).build());
    }
    
}
