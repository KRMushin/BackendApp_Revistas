/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones;

import com.mycompany.apprevistas.backend.constantes.TipoAnuncio;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.modelos.Comentario;
import com.mycompany.apprevistas.backend.modelos.Reportes.AnuncioConVisualizaciones;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConComentarios;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConSuscripciones;
import com.mycompany.apprevistas.backend.modelos.Suscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioReportesAdmin {
    
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    

    public List<Anuncio> generarReporteComprados(List<Object> parametros, String consulta) throws SQLException {
        List<Anuncio> anuncios = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(consulta)){
                 for (int i = 0; i < parametros.size(); i++) {
                      stmt.setObject(i + 1, parametros.get(i)); // `i + 1 por la cuestion de prepared Statement
                 }
                 ResultSet rs = stmt.executeQuery();
                 while (rs.next()) {
                     anuncios.add(anunciosReportes(rs));
                  }
        }
        return anuncios;

    }

    public List<Anuncio> generarReporteAnunciantes(List<Object> parametros, String consulta) throws SQLException {
        List<Anuncio> anuncios = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(consulta)){
                 for (int i = 0; i < parametros.size(); i++) {
                      stmt.setObject(i + 1, parametros.get(i)); // `i + 1 por la cuestion de prepared Statement
                 }
                 ResultSet rs = stmt.executeQuery();
                 while (rs.next()) {
                     anuncios.add(anunciosReportesAnunciantes(rs));
                  }
        }
        return anuncios;
    }

    public List<RevistaConSuscripciones> generarReporteMasPopulares(List<Object> parametros, String consulta) throws SQLException {
        List<RevistaConSuscripciones> revs = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(consulta)){
                 for (int i = 0; i < parametros.size(); i++) {
                      stmt.setObject(i + 1, parametros.get(i)); // `i + 1 por la cuestion de prepared Statement
                 }
                 ResultSet rs = stmt.executeQuery();
                 while (rs.next()) {
                     revs.add(crearRevistaConSuscripciones(rs));
                  }
        }
        return revs;
    }

    public List<RevistaConComentarios> generarReporteMasComentadas(List<Object> parametros, String consulta) throws SQLException {
        List<RevistaConComentarios> revistasComentarios = new ArrayList<>();
        
        try(PreparedStatement stmt = conn.prepareStatement(consulta)){
                 for (int i = 0; i < parametros.size(); i++) {
                      stmt.setObject(i + 1, parametros.get(i)); // `i + 1 por la cuestion de prepared Statement
                 }
                 ResultSet rs = stmt.executeQuery();
                 while (rs.next()) {
                     revistasComentarios.add(crearRevistaConComentarios(rs));
                  }
        }
        return revistasComentarios;
    }
    
    public List<AnuncioConVisualizaciones> generarReporteEfectividad(List<Object> parametros, String consulta) throws SQLException {
        List<AnuncioConVisualizaciones> visualizaciones = new ArrayList<>();
        
        try(PreparedStatement stmt = conn.prepareStatement(consulta)){
                 for (int i = 0; i < parametros.size(); i++) {
                      stmt.setObject(i + 1, parametros.get(i)); // `i + 1 por la cuestion de prepared Statement
                 }
                 ResultSet rs = stmt.executeQuery();
                 while (rs.next()) {
                     visualizaciones.add(crearAnuncioConVisualizaciones(rs));
                  }
        }
        return visualizaciones;
    
    }
    
    private RevistaConComentarios crearRevistaConComentarios(ResultSet rs) throws SQLException {
        RevistaConComentarios rc = new RevistaConComentarios();
        rc.setIdRevista(rs.getLong("id_revista"));
        rc.setTituloRevista(rs.getString("titulo_revista"));
        Comentario c = new Comentario();
        c.setComentario(rs.getString("comentario"));
        c.setIdComentario(rs.getLong("id_comentario"));
        c.setNombreUsuario(rs.getString("usuario_comentario"));
        c.setFechaComentario(rs.getDate("fecha_comentario").toLocalDate());
        rc.setComentariosRevista(c);
        return rc;
    }
    
    private RevistaConSuscripciones crearRevistaConSuscripciones(ResultSet rs) throws SQLException {
        RevistaConSuscripciones revS = new RevistaConSuscripciones();
        revS.setNombreAutor(rs.getString("nombre_autor"));
        revS.setTituloRevista(rs.getString("titulo_revista"));
        
        Suscripcion s = new Suscripcion();
        s.setFechaSuscripcion(rs.getDate("fecha_suscripcion").toLocalDate());
        s.setIdRevista(rs.getLong("id_revista"));
        s.setNombreUsuario(rs.getString("nombre_usuario"));
        revS.setSuscripcionesRevista(s);
        return revS;
        
    }
    
    private Anuncio anunciosReportes(ResultSet rs) throws SQLException {
        Anuncio anuncio = new Anuncio();
            anuncio.setNombreUsuario(rs.getString("nombre_usuario"));
            anuncio.setFechaCompra(rs.getDate("fecha_compra").toLocalDate());
            anuncio.setTipoAnuncio(TipoAnuncio.valueOf(rs.getString("tipo_anuncio"))); // Asume que `TipoAnuncio` es un enum
            anuncio.setPrecioTotal(rs.getDouble("precio_total"));
            anuncio.setDiasDuracion(rs.getInt("dias_duracion"));
        return anuncio;
}
    private Anuncio anunciosReportesAnunciantes(ResultSet rs) throws SQLException {
        Anuncio anuncio = new Anuncio();
            anuncio.setNombreUsuario(rs.getString("nombre_usuario"));
            anuncio.setTipoAnuncio(TipoAnuncio.valueOf(rs.getString("tipo_anuncio"))); // Asume que `TipoAnuncio` es un enum
            anuncio.setPrecioTotal(rs.getDouble("precio_total"));
            anuncio.setFechaCompra(rs.getDate("fecha_compra").toLocalDate());
        return anuncio;
    }

    private AnuncioConVisualizaciones crearAnuncioConVisualizaciones(ResultSet rs) throws SQLException {
        AnuncioConVisualizaciones a = new AnuncioConVisualizaciones();
            a.setFechaVisualizacion(rs.getDate("fecha_visualizacion").toLocalDate());
            a.setIdAnuncio(rs.getLong("id_anuncio"));
            a.setNombreUsuario(rs.getString("nombre_usuario"));
            a.setRutaUrl(rs.getString("url"));
            a.setTipoAnuncio(rs.getString("tipo_anuncio"));
            a.setTotalVisualizaciones(rs.getInt("total_visualizaciones"));
        return a;
    }

    
}
