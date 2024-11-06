/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.RevistasDTOs.CompraBloqueoDTO;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltrosAdminDTO;
import com.mycompany.apprevistas.backend.modelos.Reportes.ReporteIngresosEditores;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioComprasBloqueos {

    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
        
    public void guardarCompra(CompraBloqueoDTO modelo) throws SQLException {
         String insertQuery = "INSERT INTO bloqueos_anuncios_compras (id_revista, fecha_compra, dias_compra, costo_total) "
                           + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setLong(1, modelo.getIdRevista());
                stmt.setDate(2, Date.valueOf(modelo.getFechaCompra())); 
                stmt.setInt(3, modelo.getDiasCompra());
                stmt.setDouble(4, modelo.getCostoTotal());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas <= 0) {
                System.out.println("compra hecha");
                throw new SQLException("No se pudo guardar la compra de bloqueo");
            }
        }
    }
    
     public List<CompraBloqueoDTO> listarCompras() throws SQLException {
        List<CompraBloqueoDTO> listaCompras = new ArrayList<>();
        String selectQuery = "SELECT id_revista, fecha_compra, dias_compra, costo_total FROM bloqueos_anuncios_compras";
        
        try (PreparedStatement stmt = conn.prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                CompraBloqueoDTO compra = new CompraBloqueoDTO();
                compra.setIdRevista(rs.getLong("id_revista"));
                compra.setFechaCompra(rs.getDate("fecha_compra").toLocalDate());
                compra.setDiasCompra(rs.getInt("dias_compra"));
                compra.setCostoTotal(rs.getDouble("costo_total"));
                
                listaCompras.add(compra);
            }
        }
        
        return listaCompras;
    }

    public ReporteIngresosEditores reporteIngresos(FiltrosAdminDTO filtro) throws SQLException{
        ReporteIngresosEditores repIngresos = new ReporteIngresosEditores();
        String consultaG = "        SELECT b.fecha_compra, b.dias_compra, r.nombre_autor, b.costo_total,SUM(b.costo_total) OVER () AS total_ingreso FROM bloqueos_anuncios_compras b JOIN revistas r ON b.id_revista = r.id_revista WHERE 1=1";
        StringBuilder consulta = new StringBuilder();

        consulta.append(consultaG);

        if (filtro.getFechaInicio() != null) {
            consulta.append(" AND b.fecha_compra >= ?");
        }
        if (filtro.getFechaFin() != null) {
            consulta.append(" AND b.fecha_compra <= ?");
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
            List<CompraBloqueoDTO> compras = new ArrayList<>();

            while (rs.next()) {
                CompraBloqueoDTO compra = new CompraBloqueoDTO();
                compra.setFechaCompra(rs.getDate("fecha_compra").toLocalDate());
                compra.setDiasCompra(rs.getInt("dias_compra"));
                compra.setNombreUsuario(rs.getString("nombre_autor"));
                compra.setCostoTotal(rs.getDouble("costo_total"));
                compras.add(compra);

                repIngresos.setTotalIngresos(rs.getDouble("total_ingreso"));
            }

            repIngresos.setCompras(compras);
        }
    }

    return repIngresos;
    }

    public CompraBloqueoDTO obtnerPorId(Long idCompra) throws SQLException {
    String selectQuery = "SELECT * FROM bloqueos_anuncios_compras WHERE id_revista = ? AND vigencia = TRUE";
    
    try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
        stmt.setLong(1, idCompra); // Asigna el id de compra al PreparedStatement

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Si el registro existe, crea y devuelve un objeto CompraBloqueoDTO con los datos obtenidos
                CompraBloqueoDTO compra = new CompraBloqueoDTO();
                compra.setIdRevista(rs.getLong("id_revista"));
                compra.setFechaCompra(rs.getDate("fecha_compra").toLocalDate());
                compra.setDiasCompra(rs.getInt("dias_compra"));
                compra.setCostoTotal(rs.getDouble("costo_total"));
                compra.setIdCompra(rs.getLong("id_compra"));
                return compra;
            } 
        }
    }
        return null;
}

    public void invalidarId(Long idCompra) throws SQLException {
    String updateQuery = "UPDATE bloqueos_anuncios_compras SET vigencia = 0 WHERE id_compra = ?";

    try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
        stmt.setLong(1, idCompra); // Asigna el id_compra al parámetro de la consulta

        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("La vigencia de la compra con id " + idCompra + " ha sido invalidada.");
        } else {
            System.out.println("No se encontró una compra con id " + idCompra);
        }
    } 
}


}
