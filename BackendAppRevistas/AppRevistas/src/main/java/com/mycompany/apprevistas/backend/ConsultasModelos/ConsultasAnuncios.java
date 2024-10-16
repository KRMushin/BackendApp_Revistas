/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos;

import com.mycompany.apprevistas.backend.AnunciosDTOs.AnuncioDTO;
import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioAnuncios;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioCarterasDigitales;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasAnuncios {
    
    private RepositorioAnuncios repositorioAnuncios;
    private RepositorioCarterasDigitales repositorioCarteras;

    public ConsultasAnuncios() {
        this.repositorioCarteras = new RepositorioCarterasDigitales();
        this.repositorioAnuncios = new RepositorioAnuncios();
    }
    
    public void guardarAnuncio(Anuncio anuncio, CarteraDigital carteraSaldo){
    
           try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
                repositorioAnuncios.setConn(conn);
                repositorioCarteras.setConn(conn);
                
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                } 

                try {
                      repositorioAnuncios.guardar(anuncio);
                      repositorioCarteras.actualizar(carteraSaldo);
                     conn.commit();
               } catch (SQLException e) {
                   conn.rollback();
                   throw new DatabaseException(e);
               }
               
        } catch (SQLException e) {
               throw new DatabaseException(e);
        }
    }

    public List<Anuncio> obtenerTodos() {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioAnuncios.setConn(conn);
             return repositorioAnuncios.listar("obtenertodas");
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public List<Anuncio> obtenerAnunciosUsuario(String nombreUsuario) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioAnuncios.setConn(conn);
             return repositorioAnuncios.listar(nombreUsuario);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
