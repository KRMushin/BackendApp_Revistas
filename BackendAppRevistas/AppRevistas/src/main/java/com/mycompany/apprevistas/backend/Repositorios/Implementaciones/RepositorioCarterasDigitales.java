/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones;

import com.mycompany.apprevistas.backend.Repositorios.RepositorioCrud;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
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
public class RepositorioCarterasDigitales implements RepositorioCrud<CarteraDigital, String, String >{

    private Connection conn;
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<CarteraDigital> listar(String identificador) throws SQLException {
        List<CarteraDigital> carteras = new ArrayList<>();
        String insertQuery = "SELECT *FROM carteras_digitales";
        
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             ResultSet rs = stmt.executeQuery();
             
             while (rs.next()) {
                carteras.add(crearCartera(rs));
            }
        }
        return carteras;
    }
   
    @Override
    public CarteraDigital guardar(CarteraDigital modelo) throws SQLException {
         String insertQuery = "INSERT INTO carteras_digitales(nombre_usuario) values (?)";
         
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, modelo.getNombreUsuario());
            int filasAfectadas = stmt.executeUpdate();       
            if (filasAfectadas == 0) {
                throw   new  SQLException("No se concreto el ingreso de la cartera digital");
            }
        }
        return modelo;
    }
    
    @Override
    public CarteraDigital actualizar(CarteraDigital modelo) throws SQLException {
        String updateQuery = " UPDATE carteras_digitales SET saldo_disponible= ? WHERE nombre_usuario = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)){
            stmt.setDouble(1, modelo.getSaldoDisponible());
            stmt.setString(2, modelo.getNombreUsuario());
             int filasAfectadas = stmt.executeUpdate();
             
             if (filasAfectadas == 0) {
                throw   new  SQLException("No se encontro un usuario para actualizar");
            }
        }
        return modelo;
    }
    
    @Override
    public CarteraDigital obtenerPorId(String nombreUsuario) throws SQLException {
        CarteraDigital cartera = null;
        String query = "SELECT * FROM carteras_digitales WHERE nombre_usuario = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(query)){
             stmt.setString(1, nombreUsuario);
             ResultSet resultSet = stmt.executeQuery();
             
             if (resultSet.next()) {
                cartera = crearCartera(resultSet);
            }
        }
        return cartera;
    }
    
    private CarteraDigital crearCartera(ResultSet resultSet) throws SQLException {
            String representante = resultSet.getString("nombre_usuario");
            Double saldoDisponible = resultSet.getDouble("saldo_disponible");

            return new CarteraDigital(representante,saldoDisponible);
    }
}
