/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos.Revistas;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioNumerosRevista;
import com.mycompany.apprevistas.backend.RevistasDTOs.NumeroRevistaDTO;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasNumerosRevistas {
    
    private final RepositorioNumerosRevista repositorioNumerosRevista;

    public ConsultasNumerosRevistas() {
        this.repositorioNumerosRevista = new RepositorioNumerosRevista();
    }

    public Optional<NumeroRevistaDTO> publicarNumeroRevista(NumeroRevistaDTO numeroRevistaDTO) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repositorioNumerosRevista.setConn(conn);
            return repositorioNumerosRevista.publicarNumero(numeroRevistaDTO);
        } catch (SQLException e) {
              throw new DatabaseException(e);
        }
    }

    public List<NumeroRevistaDTO> obtnerTodosLosNumeros(Long idRevista) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repositorioNumerosRevista.setConn(conn);
            return repositorioNumerosRevista.obtnerTodosLosNumeros(idRevista).get();
        } catch (SQLException e) {
              throw new DatabaseException(e);
        }
    }
 
    public InputStream obtnerarchivoNumero(Long idNumeroRevista) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            repositorioNumerosRevista.setConn(conn);
            return repositorioNumerosRevista.obtenerArchivoNumero(idNumeroRevista);
        } catch (SQLException e) {
              throw new DatabaseException(e);
        }
    }
    
}
