/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasPorParametros;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioLlavesRevista;
import com.mycompany.apprevistas.backend.RevistasDTOs.EstadoRevista;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.constantes.RevistaEstadoVisibilidad;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasLlavesRevistas {
    
    private RepositorioLlavesRevista revistasParametro;

    public ConsultasLlavesRevistas() {
        this.revistasParametro = new RepositorioLlavesRevista();
    }

    public List<LlaveRevistaDTO> obtnerRevistasPorEstados(RevistaEstadoVisibilidad estado) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             revistasParametro.setConn(conn);
             return revistasParametro.listarRevistasPorEstado(estado);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
