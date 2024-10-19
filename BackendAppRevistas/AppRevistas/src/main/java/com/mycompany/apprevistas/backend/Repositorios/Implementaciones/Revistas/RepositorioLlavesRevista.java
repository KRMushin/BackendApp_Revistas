/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Revista;
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
        private LlaveRevistaDTO crearLlave(ResultSet rs) throws SQLException {
            LlaveRevistaDTO llave = new LlaveRevistaDTO();
                llave.setIdRevista(rs.getLong("id_revista"));
                llave.setTituloRevista(rs.getString("titulo_revista"));
                llave.setDescripcion(rs.getString("descripcion"));
                llave.setEstadoRevista(rs.getString("estado_revista"));
            return llave;
    }
    
}
