/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioCarterasDigitales;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasCarteraDigital {
    
    private RepositorioCarterasDigitales repositorioCarteras;

    public ConsultasCarteraDigital() {
        this.repositorioCarteras = new RepositorioCarterasDigitales();
    }
    
    
    public Optional<CarteraDigital> obtenerCarteraDigitalUsuario(String nombreUsuario){
    
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repositorioCarteras.setConn(conn);
              
              CarteraDigital cartera = repositorioCarteras.obtenerPorId(nombreUsuario);
              if (cartera != null) {
                 return Optional.of(cartera);
            }
              return Optional.empty();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
    
    public void actualizarSaldoCartera(CarteraDigital cartera){
    
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repositorioCarteras.setConn(conn);
              repositorioCarteras.actualizar(cartera);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
    
    public void actualizarCarteraDigital(CarteraDigital carteraDigital){
    
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioCarteras.setConn(conn);
             repositorioCarteras.actualizar(carteraDigital);
             
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
