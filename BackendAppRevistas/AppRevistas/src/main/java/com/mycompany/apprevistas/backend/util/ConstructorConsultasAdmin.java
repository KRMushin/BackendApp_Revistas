/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import com.mycompany.apprevistas.backend.modelos.Reportes.FiltrosAdminDTO;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConstructorConsultasAdmin {
    
    private final String cincoPopulares = "SELECT r.id_revista, r.titulo_revista, r.nombre_autor, s.fecha_suscripcion, s.nombre_usuario, COUNT(s.id_suscripcion) AS total_suscripciones FROM      revistas r  JOIN      suscripciones s ON r.id_revista = s.id_revista  WHERE 1=1";

    private final String consultaComprados = "SELECT nombre_usuario, fecha_compra, tipo_anuncio, precio_total, dias_duracion FROM anuncios WHERE 1=1 ";
    
    private final String consultaComentarios = "SELECT      r.id_revista,      r.titulo_revista,      r.nombre_autor,      rc.id_comentario,      rc.nombre_usuario AS usuario_comentario,      rc.fecha_comentario,      rc.comentario,\n" +
"    COUNT(rc.id_comentario) AS total_comentarios FROM      revistas r INNER JOIN      revistas_comentarios rc ON r.id_revista = rc.id_revista WHERE      1=1";
        
    private final String consultaEfectividad = "SELECT v.id_anuncio, v.fecha_visualizacion, v.url, a.nombre_usuario, a.tipo_anuncio, "
                                                                     + "COUNT(v.id_visualizacion) AS total_visualizaciones FROM visualizaciones_anuncios v JOIN anuncios a "
                                                                     + "ON v.id_anuncio = a.id_anuncio WHERE 1=1";
    
    public String construirConsultaAnuncios(List<Object> parametros, FiltrosAdminDTO filtro) {
         StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(consultaComprados);

        if (filtro.getFechaInicio() != null) {
            stringBuilder.append(" AND fecha_compra >= ?");
            parametros.add(filtro.getFechaInicio());
        }
        if (filtro.getFechaFin() != null) {
            stringBuilder.append(" AND fecha_compra <= ?");
            parametros.add(filtro.getFechaFin());
        }

        if (filtro.getDiasPeriodo() > 0) {
            stringBuilder.append(" AND dias_duracion = ?");
            parametros.add(filtro.getDiasPeriodo());
        }
        if (filtro.getTipoAnuncio() != null) {
            stringBuilder.append(" AND tipo_anuncio = ?");
            parametros.add(filtro.getTipoAnuncio().toString());
        }
        
        if (filtro.getNombreAnunciante() != null) {
            stringBuilder.append(" AND nombre_usuario = ?");
            parametros.add(filtro.getNombreAnunciante());
            
        }
        return stringBuilder.toString(); // Devuelve la consulta completa con placeholders
    }

    public String construirRevistasPopulares(List<Object> parametros, FiltrosAdminDTO filtro) {
         StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cincoPopulares);

        if (filtro.getFechaInicio() != null) {
            stringBuilder.append(" AND fecha_suscripcion >= ?");
            parametros.add(filtro.getFechaInicio());
        }
        if (filtro.getFechaFin() != null) {
            stringBuilder.append(" AND fecha_suscripcion <= ?");
            parametros.add(filtro.getFechaFin());
        }
        stringBuilder.append(" GROUP BY r.id_revista, r.titulo_revista, r.nombre_autor, s.fecha_suscripcion, s.nombre_usuario ORDER BY total_suscripciones ASC LIMIT 5");
        
        return stringBuilder.toString();
    }
    
    public String construirConsultaComentarios(List<Object> parametros, FiltrosAdminDTO filtro) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(consultaComentarios);

        if (filtro.getFechaInicio() != null) {
            stringBuilder.append(" AND rc.fecha_comentario >= ?");
            parametros.add(filtro.getFechaInicio());
        }
        if (filtro.getFechaFin() != null) {
            stringBuilder.append(" AND rc.fecha_comentario <= ?");
            parametros.add(filtro.getFechaFin());
        }

        stringBuilder.append(" GROUP BY      r.id_revista,      r.titulo_revista,      r.nombre_autor,      rc.id_comentario,      rc.nombre_usuario,      rc.fecha_comentario,      rc.comentario ");

        return stringBuilder.toString(); // Devuelve la consulta completa
}

    public String consultEfectividad(List<Object> parametros, FiltrosAdminDTO filtro) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(consultaEfectividad);

        if (filtro.getFechaInicio() != null) {
            stringBuilder.append(" AND v.fecha_visualizacion >= ?");
            parametros.add(filtro.getFechaInicio());
        }
        if (filtro.getFechaFin() != null) {
            stringBuilder.append(" AND v.fecha_visualizacion <= ?");
            parametros.add(filtro.getFechaFin());
        }
        if (filtro.getNombreAnunciante() != null) {
            stringBuilder.append(" AND a.nombre_usuario = ?");
            parametros.add(filtro.getNombreAnunciante());
        }
        
        stringBuilder.append(" GROUP BY v.id_anuncio, a.nombre_usuario, a.tipo_anuncio, v.fecha_visualizacion, v.url");
        
        return stringBuilder.toString(); // Devuelve la consulta completa
    }



    
}
