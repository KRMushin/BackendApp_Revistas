/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos.Revistas;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioCarterasDigitales;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioComprasBloqueos;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioEstadoRevistas;
import com.mycompany.apprevistas.backend.RevistasDTOs.CompraBloqueoDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoConfigRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasComprasBloqueos {
    
    private RepositorioComprasBloqueos repositorio;
    private RepositorioCarterasDigitales repositorioCarteras;
    private RepositorioEstadoRevistas repositorioEstadosRevistas;

    public ConsultasComprasBloqueos() {
        this.repositorio = new RepositorioComprasBloqueos();
        this.repositorioCarteras = new RepositorioCarterasDigitales();
        this.repositorioEstadosRevistas = new RepositorioEstadoRevistas();
    }
    
    public void guardarCompra(CompraBloqueoDTO compra, EstadoConfigRevistaDTO actualizarEstado, CarteraDigital carteraDebitada) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
              repositorio.setConn(conn);
              repositorioEstadosRevistas.setConn(conn);
              repositorioCarteras.setConn(conn);
              
                    if (conn.getAutoCommit()) {
                        conn.setAutoCommit(false);
                    }
                    try {
                          repositorioCarteras.actualizar(carteraDebitada);
                          repositorioEstadosRevistas.actualizarEstadoRevista(actualizarEstado);
                          repositorio.guardarCompra(compra);
                          conn.commit();
                    } catch (SQLException e) {
                        conn.rollback();
                        throw new DatabaseException(e);
                    }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
