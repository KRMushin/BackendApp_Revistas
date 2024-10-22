/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ActualizacionesModelo;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioRevistas;
import com.mycompany.apprevistas.backend.RevistasDTOs.NuevoCostoDTO;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ActualizacionesRevista {
    
    private RepositorioRevistas repositorioRevistas;

    public ActualizacionesRevista() {
        this.repositorioRevistas = new RepositorioRevistas();
    }

    public void actualizarCostoRevista(NuevoCostoDTO nuevoCosto){
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioRevistas.setConn(conn);
             repositorioRevistas.actualizarCostoRevista(nuevoCosto);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
}
