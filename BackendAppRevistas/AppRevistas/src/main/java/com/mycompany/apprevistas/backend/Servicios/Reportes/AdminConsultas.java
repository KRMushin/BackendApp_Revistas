/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Reportes;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioReportesAdmin;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioReportesEditor;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.anuncios.RepositorioVisualizacionAnuncio;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.modelos.Reportes.AnuncioConVisualizaciones;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConComentarios;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConSuscripciones;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class AdminConsultas {
    
    private RepositorioReportesAdmin rp;
    private RepositorioVisualizacionAnuncio rva;

    public AdminConsultas() {
        this.rp = new RepositorioReportesAdmin();
        this.rva = new RepositorioVisualizacionAnuncio();
    }
    
    public List<Anuncio> obtnerReporteComprados(List<Object> parametros, String consulta) {
         try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            rp.setConn(conn);
            return rp.generarReporteComprados(parametros,consulta);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public List<Anuncio> obtnerReporteAnunciantes(List<Object> parametros, String consulta) {
         try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            rp.setConn(conn);
            return rp.generarReporteAnunciantes(parametros,consulta);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public List<RevistaConSuscripciones> obtnerRevistasMasPopulares(List<Object> parametros, String consulta) {
         try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            rp.setConn(conn);
            return rp.generarReporteMasPopulares(parametros,consulta);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public List<RevistaConComentarios> obtnerRevistasMasComentadas(List<Object> parametros, String consulta) {
         try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            rp.setConn(conn);
            return rp.generarReporteMasComentadas(parametros,consulta);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public void registrarVisualizacion(String url, Long idAnuncio) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            rva.setConn(conn);
            rva.almacenarSuscripcion(url,idAnuncio);
        } catch (Exception e) {
            throw new DatabaseException();
        }
    }

    public List<AnuncioConVisualizaciones> obtnerEfectividadAnuncios(List<Object> parametros, String consulta) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            rp.setConn(conn);
            return rp.generarReporteEfectividad(parametros,consulta);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    
}
