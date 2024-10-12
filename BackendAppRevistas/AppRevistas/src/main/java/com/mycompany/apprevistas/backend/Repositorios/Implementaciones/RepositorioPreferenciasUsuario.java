/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones;

import com.mycompany.apprevistas.backend.Repositorios.RepositorioCrud;
import com.mycompany.apprevistas.backend.modelos.PreferenciaUsuario;
import com.mycompany.apprevistas.backend.modelos.Usuario;
import com.mycompany.apprevistas.backend.util.TipoPreferencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioPreferenciasUsuario implements RepositorioCrud<PreferenciaUsuario,Long,String>{

    private Connection conn;
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<PreferenciaUsuario> listar(String nombreUsuario) throws SQLException {
        List<PreferenciaUsuario> preferencias = new ArrayList<>();
        
        String insertQuery = " SELECT * FROM preferencias_usuario WHERE nombre_usuario = ?" ;
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             stmt.setString(1, nombreUsuario);
             ResultSet resultSet = stmt.executeQuery();
             
             while (resultSet.next()) {
                  preferencias.add(crearPreferencia(resultSet));
            }
        }
        return preferencias;
    }
    @Override
    public PreferenciaUsuario guardar(PreferenciaUsuario preferenciaUsuario) throws SQLException {
         String insertQuery = " INSERT INTO preferencias_usuario(nombre_usuario,tipo_preferencia,valor_preferencia) values(?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, preferenciaUsuario.getNombreUsuario());
            stmt.setString(2, preferenciaUsuario.getTipoPreferencia().toString());
            stmt.setString(3, preferenciaUsuario.getValorPreferencia());
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                try(ResultSet llaveG = stmt.getGeneratedKeys()){
                     if (llaveG.next()) {
                         preferenciaUsuario.setIdPreferencia(llaveG.getLong(1));
                    }
                }
            }
        }
        return preferenciaUsuario;
    }
    @Override
    public PreferenciaUsuario actualizar(PreferenciaUsuario modelo) throws SQLException {
        String insertQuery = "UPDATE preferencias_usuario SET valor_preferencia = ? WHERE id_preferencia = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             stmt.setString(1, modelo.getValorPreferencia());
             stmt.setLong(2, modelo.getIdPreferencia());
             stmt.executeUpdate();
        }
        return modelo;
    }
    @Override
    public PreferenciaUsuario obtenerPorId(Long id) throws SQLException {
        PreferenciaUsuario preferenciaUsuario = null;
        String query = "SELECT * FROM preferencias_usuario WHERE id_preferencia = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                preferenciaUsuario = crearPreferencia(resultSet);
            }
        }

        return preferenciaUsuario;
    }

    private PreferenciaUsuario crearPreferencia(ResultSet resultSet) throws SQLException {
        TipoPreferencia tipo = TipoPreferencia.valueOf(resultSet.getString("tipo_preferencia").toUpperCase());
        String nombreUsuario = resultSet.getString("nombre_usuario");
        String preferencia = resultSet.getString("valor_Preferencia");
        
        PreferenciaUsuario p = new PreferenciaUsuario(preferencia,nombreUsuario,tipo);
        p.setIdPreferencia(resultSet.getLong("id_preferencia"));
        return p;
    }

    public void eliminaroPreferencias(String nombreUsuario) throws SQLException {
        
        String borrarValores = "DELETE FROM preferencias_usuario WHERE nombre_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(borrarValores)) {
            stmt.setString(1, nombreUsuario);
            int prefBorradas = stmt.executeUpdate();
        } 
    }
    
}
