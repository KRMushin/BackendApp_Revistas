/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones;

import com.mycompany.apprevistas.backend.RevistasDTOs.CompraBloqueoDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Comentario;
import com.mycompany.apprevistas.backend.modelos.LikeRevista;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltroEditorDTO;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConComentarios;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConCompras;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConLikes;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConSuscripciones;
import com.mycompany.apprevistas.backend.modelos.Suscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioReportesEditor {
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    /*              REPORTE DE COMENTARIOS              */
    public List<RevistaConComentarios> generarReporteComentarios(List<Object> parametros, String consultaConstruida) throws SQLException {
        List<RevistaConComentarios> revistasComentarios = new ArrayList<>();
        
        try(PreparedStatement stmt = conn.prepareStatement(consultaConstruida)){
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
    
    /*              REPORTE DE   REVISTAS SUSCRIPCIONES                                                            */

    public List<RevistaConSuscripciones> generarReporteSuscripciones(List<Object> parametros, String consulta) throws SQLException {
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
    
    public List<RevistaConLikes> generarMejoresRevistas(List<Object> parametros, String consulta) throws SQLException {
        List<RevistaConLikes> llaves = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(consulta)){
                 for (int i = 0; i < parametros.size(); i++) {
                      stmt.setObject(i + 1, parametros.get(i)); // `i + 1 por la cuestion de prepared Statement
                 }
                 ResultSet rs = stmt.executeQuery();
                 while (rs.next()) {
                     llaves.add(crearLlave(rs));
                  }
        }
        return llaves;
        
    }
    
    public List<RevistaConCompras> generarComprasEditores(List<Object> parametros, String consulta) throws SQLException {
                List<RevistaConCompras> rl = new ArrayList<>();
                try(PreparedStatement stmt = conn.prepareStatement(consulta)){
                 for (int i = 0; i < parametros.size(); i++) {
                      stmt.setObject(i + 1, parametros.get(i)); // `i + 1 por la cuestion de prepared Statement
                 }
                 ResultSet rs = stmt.executeQuery();
                 while (rs.next()) {
                     rl.add(crearRevistaCompra(rs));
                  }
        }
        return rl;

    }
    
    private RevistaConComentarios crearRevistaConComentarios(ResultSet rs) throws SQLException {
        RevistaConComentarios rc = new RevistaConComentarios();
        Comentario c = new Comentario();
        c.setComentario(rs.getString("comentario"));
        c.setIdComentario(rs.getLong("id_comentario"));
        c.setNombreUsuario(rs.getString("nombre_usuario"));
        c.setFechaComentario(rs.getDate("fecha_comentario").toLocalDate());
        rc.setComentariosRevista(c);
        rc.setIdRevista(rs.getLong("id_revista"));
        rc.setTituloRevista(rs.getString("titulo_revista"));
        return rc;
    }

    private RevistaConSuscripciones crearRevistaConSuscripciones(ResultSet rs) throws SQLException {
        RevistaConSuscripciones revS = new RevistaConSuscripciones();
        revS.setNombreAutor(rs.getString("nombre_autor"));
        revS.setTituloRevista(rs.getString("titulo_revista"));
        
        Suscripcion s = new Suscripcion();
        s.setIdSuscripcion(rs.getLong("id_suscripcion"));
        s.setNombreUsuario(rs.getString("nombre_usuario"));
        s.setFechaSuscripcion(rs.getDate("fecha_suscripcion").toLocalDate());
        s.setIdRevista(rs.getLong("id_revista"));
        revS.setSuscripcionesRevista(s);
        return revS;
        
    }

    private RevistaConLikes crearLlave(ResultSet rs) throws SQLException {
        RevistaConLikes revistaLike = new RevistaConLikes();
        revistaLike.setIdRevista(rs.getLong("id_revista"));
        revistaLike.setTituloRevista(rs.getString("titulo_revista"));
        revistaLike.setNombreAutor(rs.getString("nombre_autor"));
        revistaLike.setTotalLikes(rs.getInt("total_likes"));
        
        LikeRevista like = new LikeRevista();
        like.setFechaLike(rs.getDate("fecha_like").toLocalDate()); 
        like.setNombreUsuario(rs.getString("nombre_usuario"));
        
        revistaLike.setLike(like);
        return revistaLike;
        
    }

    private RevistaConCompras crearRevistaCompra(ResultSet rs) throws SQLException {
        RevistaConCompras rc = new RevistaConCompras();
        rc.setTotalPagos(rs.getDouble("total_pagos_general"));
        rc.setTituloRevista(rs.getString("titulo_revista"));
        rc.setNombreAutor(rs.getString("nombre_autor"));
        rc.setEstadoCompra(rs.getBoolean("vigencia"));
        
        CompraBloqueoDTO compra = new CompraBloqueoDTO();
        compra.setIdRevista(rs.getLong("id_revista"));
        compra.setFechaCompra(rs.getDate("fecha_compra").toLocalDate());
        compra.setDiasCompra(rs.getInt("dias_compra"));
        compra.setCostoTotal(rs.getDouble("costo_total"));
        rc.setCompra(compra);
        return rc;
    }


    
    
}
