/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasPorParametros;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioLlavesRevista;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoRevista;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.constantes.RevistaEstadoVisibilidad;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasLlavesRevistas {
    
    private RepositorioLlavesRevista repositorioLlavesRevista;

    public ConsultasLlavesRevistas() {
        this.repositorioLlavesRevista = new RepositorioLlavesRevista();
    }

    // metodo para otbener llaves solo activas
    public List<LlaveRevistaDTO> obtnerRevistasPorEstados(RevistaEstadoVisibilidad estado) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioLlavesRevista.setConn(conn);
             return repositorioLlavesRevista.listarRevistasPorEstado(estado);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
        public Optional<LlaveRevistaDTO> obtenerLlavePorId(Long idRevista) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repositorioLlavesRevista.setConn(conn);
                return repositorioLlavesRevista.obtenerLlavePorId(idRevista);
        } catch (SQLException ex) {
                throw new DatabaseException(ex);
        }
    }
}
