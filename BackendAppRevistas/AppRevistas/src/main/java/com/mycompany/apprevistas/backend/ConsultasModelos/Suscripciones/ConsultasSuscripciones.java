/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos.Suscripciones;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Suscripciones.RepositorioSuscripciones;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Suscripcion;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasSuscripciones {
    
    private RepositorioSuscripciones rep;

    public ConsultasSuscripciones() {
        this.rep = new RepositorioSuscripciones();
    }

    public void almacenarSuscripcion(Suscripcion suscripcion) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            rep.setConn(conn);
            
            rep.guardarSuscripcion(suscripcion);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    public List<LlaveRevistaDTO> obtnerRevistaSuscripcion(String nombreUsuario) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            rep.setConn(conn);
            return rep.obtnerRevistasSuscritas(nombreUsuario);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
