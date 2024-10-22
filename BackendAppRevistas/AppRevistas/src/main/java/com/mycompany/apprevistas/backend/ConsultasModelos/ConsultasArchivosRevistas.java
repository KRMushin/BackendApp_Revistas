/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioPreciosGlobales;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.CategoriaEtiquetasRevista;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioEstadoRevistas;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioLlavesRevista;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioRevistas;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioRevistasPDF;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasArchivosRevistas {

    private RepositorioRevistasPDF repocitorioConfigRevistas;
    private RepositorioRevistas repositorioRevistas;
    
    public ConsultasArchivosRevistas() {
        this.repositorioRevistas = new RepositorioRevistas();
        this.repocitorioConfigRevistas = new RepositorioRevistasPDF();
    }
    
   
        public void guardarRevistaPDF(Long idRevista, InputStream revistaInputStream) {
            System.out.println("guardando en el id de la revista" + idRevista);
            try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
            repocitorioConfigRevistas.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                 repocitorioConfigRevistas.guardarArchivoPDF(revistaInputStream, idRevista);
                 conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new DatabaseException(e);
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }
    
        public Optional<InputStream> obtnerRevistaPDF(Long idRevista){
             try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
                  repocitorioConfigRevistas.setConn(conn);
                  return Optional.of(repocitorioConfigRevistas.obtenerArchivoPorId(idRevista));
             } catch (SQLException e) {
                 throw new DatabaseException(e);
             }
         }
}
