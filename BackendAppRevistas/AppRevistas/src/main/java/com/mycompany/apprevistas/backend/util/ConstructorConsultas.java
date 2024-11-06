/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltroEditorDTO;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConstructorConsultas {

   private final String consultaComentarios = "SELECT rc.*, r.titulo_revista FROM revistas_comentarios rc JOIN revistas r ON rc.id_revista = r.id_revista WHERE 1=1";
   private final String consultaSuscripciones = "SELECT s.*, r.titulo_revista, r.nombre_autor FROM suscripciones s JOIN revistas r ON s.id_revista = r.id_revista WHERE 1=1";
   private final String consultaCostos = "SELECT r.id_revista, r.titulo_revista, r.nombre_autor, "
             + "COUNT(l.id_like) AS total_likes, l.nombre_usuario, l.fecha_like FROM likes_revistas l JOIN revistas r ON l.id_revista = r.id_revista WHERE";

    public final String consultaCompras= "SELECT r.id_revista, r.titulo_revista, r.nombre_autor, b.id_compra, b.fecha_compra, b.vigencia, b.dias_compra, b.costo_total, SUM(b.costo_total) OVER () AS total_pagos_general "
            + "                                                 FROM bloqueos_anuncios_compras b JOIN revistas r ON b.id_revista = r.id_revista"
            + "                                                  WHERE 1=1";


    public String construirConsultaComentarios(FiltroEditorDTO filtro, List<Object> parametros) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(consultaComentarios);
        
        if (filtro.getNombreEditor() == null || filtro.getNombreEditor().isEmpty()) {
            throw new DatosInvalidosUsuarioException("El nombre del editor es obligatorio.");
        }
        // añadir el nombre del editor
        stringBuilder.append(" AND r.nombre_autor = ?");
        parametros.add(filtro.getNombreEditor());

        if (filtro.getFechaInicio() != null) {
            stringBuilder.append(" AND rc.fecha_comentario >= ?");
            parametros.add(filtro.getFechaInicio());
        }
        if (filtro.getFechaFin() != null) {
            stringBuilder.append(" AND rc.fecha_comentario <= ?");
            parametros.add(filtro.getFechaFin());
        }
        if (filtro.getIdRevista() != null) {
            stringBuilder.append(" AND r.id_revista = ?");
            parametros.add(filtro.getIdRevista());
        }
        return stringBuilder.toString(); // Devuelve la consulta completa con placeholders
    }

    public String construirConsultaSuscripciones(FiltroEditorDTO filtro, List<Object> parametros) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(consultaSuscripciones);

        // Añadir filtro obligatorio por nombre de editor
        stringBuilder.append(" AND r.nombre_autor = ?");
        parametros.add(filtro.getNombreEditor());

        if (filtro.getFechaInicio() != null) {
            stringBuilder.append(" AND s.fecha_suscripcion >= ?");
            parametros.add(filtro.getFechaInicio());
        }
        if (filtro.getFechaFin() != null) {
            stringBuilder.append(" AND s.fecha_suscripcion <= ?");
            parametros.add(filtro.getFechaFin());
        }
        if (filtro.getIdRevista() != null) {
            stringBuilder.append(" AND r.id_revista = ?");
            parametros.add(filtro.getIdRevista());
        }

        return stringBuilder.toString(); 
    }

     

    public String construirMasGustadas(FiltroEditorDTO filtro, List<Object> parametros) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(consultaCostos);

        stringBuilder.append(" r.nombre_autor = ?");
        parametros.add(filtro.getNombreEditor());

        if (filtro.getFechaInicio() != null) {
            stringBuilder.append(" AND l.fecha_like >= ?");
            parametros.add(filtro.getFechaInicio());
        }
        if (filtro.getFechaFin() != null) {
            stringBuilder.append(" AND l.fecha_like <= ?");
            parametros.add(filtro.getFechaFin());
        }
        if (filtro.getIdRevista() != null) {
            stringBuilder.append(" AND r.id_revista = ?");
            parametros.add(filtro.getIdRevista());
        }

        // Agrupar por revista y ordenar para obtener las 5 más gustadas
        stringBuilder.append(" GROUP BY r.id_revista, r.titulo_revista, r.nombre_autor, l.nombre_usuario, l.fecha_like");
        stringBuilder.append(" ORDER BY total_likes DESC");
        stringBuilder.append(" LIMIT 5");

        return stringBuilder.toString(); 
    }

    public String construirRevistaCostos(FiltroEditorDTO filtro, List<Object> parametros) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(consultaCompras);
              stringBuilder.append(" AND r.nombre_autor = ?");
        parametros.add(filtro.getNombreEditor());

        if (filtro.getFechaInicio() != null) {
            stringBuilder.append(" AND b.fecha_compra >= ?");
            parametros.add(filtro.getFechaInicio());
        }
        if (filtro.getFechaFin() != null) {
            stringBuilder.append(" AND b.fecha_compra <= ?");
            parametros.add(filtro.getFechaFin());
        }
        if (filtro.getIdRevista() != null) {
            stringBuilder.append(" AND r.id_revista = ?");
            parametros.add(filtro.getIdRevista());
        }

        return stringBuilder.toString(); // Devuelve la consulta completa 
    
    }
    
}
