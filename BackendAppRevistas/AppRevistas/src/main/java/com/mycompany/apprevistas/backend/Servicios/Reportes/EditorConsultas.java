/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Reportes;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioReportesEditor;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltroEditorDTO;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConComentarios;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConCompras;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConLikes;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConSuscripciones;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
class EditorConsultas {
    
    private RepositorioReportesEditor repEditor;

    public EditorConsultas() {
        this.repEditor = new RepositorioReportesEditor();
    }
    
    List<RevistaConComentarios> obtenerRevistasConComentarios(List<Object> parametros, String consultaConstruida) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repEditor.setConn(conn);
            return repEditor.generarReporteComentarios(parametros, consultaConstruida);
        } catch (SQLException e) {
            throw new DatabaseException();
        }

    }

    List<RevistaConSuscripciones> obtenerRevistasSuscripciones(List<Object> parametros, String consulta) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repEditor.setConn(conn);
            return repEditor.generarReporteSuscripciones(parametros, consulta);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    List<RevistaConLikes> obtnerRevistasMasGustadas(List<Object> parametros, String consulta) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repEditor.setConn(conn);
            return repEditor.generarMejoresRevistas(parametros, consulta);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    List<RevistaConCompras> obtnerRevistasCostos(List<Object> parametros, String consulta) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repEditor.setConn(conn);
            return repEditor.generarComprasEditores(parametros, consulta);
        } catch (SQLException e) {
            throw new DatabaseException();
        }


    }
    
}
