/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioPreciosGlobales;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.CategoriaEtiquetasRevista;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioEstadoRevistas;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioLlavesRevista;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioRevistas;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioRevistasPDF;
import com.mycompany.apprevistas.backend.RevistasDTOs.EstadoConfigRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.EstadoRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Revista;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasRevistas {
    
    private RepositorioEstadoRevistas repositorioConfiguraciones;
    private RepositorioPreciosGlobales repositorioGlobales;
    private RepositorioRevistasPDF repocitorioConfigRevistas;
    private RepositorioRevistas repositorioRevistas;
    private RepositorioLlavesRevista repositorioLlavesRevista;
    private CategoriaEtiquetasRevista repositorioRelacionRevista;

    public ConsultasRevistas() {
        this.repositorioConfiguraciones = new RepositorioEstadoRevistas();
        this.repositorioRevistas = new RepositorioRevistas();
        this.repositorioGlobales = new RepositorioPreciosGlobales();
        this.repocitorioConfigRevistas = new RepositorioRevistasPDF();
        this.repositorioRelacionRevista = new CategoriaEtiquetasRevista();
        this.repositorioLlavesRevista = new RepositorioLlavesRevista();
    }
    public Revista publicarRevista(Revista revista){
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
             repositorioConfiguraciones.setConn(conn);
             repositorioRevistas.setConn(conn);
             repositorioGlobales.setConn(conn);
             repositorioRelacionRevista.setConn(conn);
             
             if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
             
             try {
                   revista.setCostoMantenimiento(repositorioGlobales.obtenerPrecioModelo("REVISTA"));
                   Revista revistaGuardada = repositorioRevistas.guardar(revista);
                   repositorioConfiguraciones.guardarEstadosRevista(revistaGuardada.getIdRevista());
                   repositorioRelacionRevista.guardarEtiquetas(revistaGuardada.getIdRevista(), revista.getIdEtiquetas());
                   conn.commit();
                   return revistaGuardada;
            } catch (SQLException e) {
                conn.rollback();
                throw new DatosInvalidosUsuarioException(e);
            }
        } catch (SQLException e) {
                throw new DatabaseException(e);
        }
    }

    public boolean existeRevista(Long idRevista) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repocitorioConfigRevistas.setConn(conn);
                return repocitorioConfigRevistas.existeRevista(idRevista);
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }
    
    public EstadoRevistaDTO ObtenerEstadoRevista(Long idRevista){
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repositorioConfiguraciones.setConn(conn);
                return repositorioConfiguraciones.obtenerEstadoRevista(idRevista);
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }

    public void actualizarEstado(EstadoConfigRevistaDTO estado) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repositorioConfiguraciones.setConn(conn);
                repositorioConfiguraciones.actualizarEstadoRevista(estado);
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }

    public List<LlaveRevistaDTO> obtenerPublicacionesUsuario(String nombreUsuario) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repositorioLlavesRevista.setConn(conn);
                return repositorioLlavesRevista.listarRevistasUsuario(nombreUsuario);
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }

    public Revista obtenerDatosRevista(Long idRevista) {
    try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
            repositorioRevistas.setConn(conn);
                return repositorioRevistas.obtenerPorId(idRevista);
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }

    public void activarRevista(Long idRevista) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repositorioRevistas.setConn(conn);
                    repositorioRevistas.activarRevista(idRevista);
            } catch (SQLException ex) {
                throw new DatabaseException(ex);
            }
    }

    public Revista obtenerPorId(Long idRevista) {
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
                repositorioRevistas.setConn(conn);
                return repositorioRevistas.obtenerPorId(idRevista);
            } catch (SQLException ex) {
                throw new DatabaseException(ex);
            }
    }
}

