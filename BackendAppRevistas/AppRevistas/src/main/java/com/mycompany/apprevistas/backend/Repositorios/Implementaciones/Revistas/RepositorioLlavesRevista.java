/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoRevista;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltroEditorDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.constantes.RevistaEstadoVisibilidad;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltrosAdminDTO;
import com.mycompany.apprevistas.backend.modelos.Reportes.ReporteCostosRevista;
import com.mycompany.apprevistas.backend.modelos.Revista;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioLlavesRevista {
    private  Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public List<LlaveRevistaDTO> listarRevistasUsuario(String nombreUsuario) throws SQLException {
        List<LlaveRevistaDTO> revistas = new ArrayList<>();

        String getList = "SELECT * FROM revistas WHERE nombre_autor = ? ";
            try (PreparedStatement stmt = conn.prepareStatement(getList)) {
                stmt.setString(1, nombreUsuario);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        LlaveRevistaDTO revista = crearLlave(rs);
                        revistas.add(revista);
                    }
                }
            }
        return revistas;
    }
    
    public List<LlaveRevistaDTO> listarRevistasPorEstado(RevistaEstadoVisibilidad estado) throws SQLException {
        List<LlaveRevistaDTO> revistas = new ArrayList<>();
        System.out.println(estado.toString());

        String getList = "SELECT * FROM revistas WHERE estado_revista = ? ";
            try (PreparedStatement stmt = conn.prepareStatement(getList)) {
                stmt.setString(1, estado.toString());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        LlaveRevistaDTO revista = crearLlave(rs);
                        revistas.add(revista);
                    }
                }
            }
        return revistas;
    }
    
    public Optional<LlaveRevistaDTO> obtenerLlavePorId(Long idRevista) throws SQLException {
    
            String getById = "SELECT * FROM revistas WHERE id_revista = ?";
    
            try (PreparedStatement stmt = conn.prepareStatement(getById)) {
                stmt.setLong(1, idRevista);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(crearLlave(rs));
                    }
                    return Optional.empty();
                }
            }
}
        
    private LlaveRevistaDTO crearLlave(ResultSet rs) throws SQLException {
            LlaveRevistaDTO llave = new LlaveRevistaDTO();
                llave.setIdRevista(rs.getLong("id_revista"));
                llave.setTituloRevista(rs.getString("titulo_revista"));
                llave.setDescripcion(rs.getString("descripcion"));
                llave.setEstadoRevista(rs.getString("estado_revista"));
                llave.setCostoMantenimiento(rs.getDouble("costo_mantenimiento"));
                llave.setCostoBloqueoAnuncios(rs.getDouble("costo_bloqueo_anuncios"));
                return llave;
    }

    public ReporteCostosRevista obtenerReporte(FiltrosAdminDTO filtro) throws SQLException {
        

        ReporteCostosRevista repCosto = new ReporteCostosRevista();
        String consultaG = "SELECT titulo_revista, nombre_autor, costo_mantenimiento, fecha_publicacion,SUM(costo_mantenimiento) OVER () AS total_costos FROM revistas WHERE 1=1";
        StringBuilder consulta = new StringBuilder();
        consulta.append(consultaG);
        
          if (filtro.getFechaInicio() != null) {
        consulta.append(" AND fecha_publicacion >= ?");
    }
    if (filtro.getFechaFin() != null) {
        consulta.append(" AND fecha_publicacion <= ?");
    }

    try (PreparedStatement stmt = conn.prepareStatement(consulta.toString())) {
        int indice = 1;

        if (filtro.getFechaInicio() != null) {
            stmt.setDate(indice++, Date.valueOf(filtro.getFechaInicio()));
        }
        if (filtro.getFechaFin() != null) {
            stmt.setDate(indice++, Date.valueOf(filtro.getFechaFin()));
        }

         try (ResultSet rs = stmt.executeQuery()) {
                List<Revista> llaves = new ArrayList<>();

                while (rs.next()) {
                        Revista r = new Revista();
                        r.setTituloRevista(rs.getString("titulo_revista"));
                        r.setNombreAutor(rs.getString("nombre_autor"));
                        r.setCostoMantenimiento(rs.getDouble("costo_mantenimiento"));
                        r.setFechaCreacion(rs.getDate("fecha_publicacion").toLocalDate());
                        llaves.add(r);

                        repCosto.setTotalCostosMantenimiento(rs.getDouble("total_costos"));
            }
            repCosto.setRevistas(llaves);
        }
    }
    return repCosto;
    }

    
}
