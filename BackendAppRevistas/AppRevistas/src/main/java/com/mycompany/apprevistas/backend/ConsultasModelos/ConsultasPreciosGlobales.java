/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioPreciosGlobales;
import com.mycompany.apprevistas.backend.modelos.PrecioGlobal;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasPreciosGlobales {

    private RepositorioPreciosGlobales repositorioPreciosGlobales;

    public ConsultasPreciosGlobales() {
        this.repositorioPreciosGlobales = new RepositorioPreciosGlobales();
    }

    public List<PrecioGlobal> obtnerTodos() {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioPreciosGlobales.setConn(conn);
             return repositorioPreciosGlobales.listarTodos();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    public void actualizarPrecioGlobal(PrecioGlobal precioGlobal){
        
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioPreciosGlobales.setConn(conn);
             repositorioPreciosGlobales.actualizar(precioGlobal);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    

}
