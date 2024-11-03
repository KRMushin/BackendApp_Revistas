/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos.Revistas;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioNavegacionRevistas;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Filtros.FiltroNavegacionRevistas;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasPorFiltro {
    
    private RepositorioNavegacionRevistas r;

    public ConsultasPorFiltro() {
        this.r = new RepositorioNavegacionRevistas();
    }

    public List<EstadoRevistaDTO> obtnerTodasActivas(String nombreUsuario) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            r.setConn(conn);
            return r.ListarEstadosRevistaActiva(nombreUsuario);
        } catch (SQLException e) {
            throw new DatosInvalidosUsuarioException();
        }
    }

    public List<EstadoRevistaDTO> obtnerPorCategorias(FiltroNavegacionRevistas filtro) {
        if (filtro.getValoresFiltros().isEmpty()) {
            return null;
        }
        List<EstadoRevistaDTO> estados = new ArrayList<>();
        List<Long>ids = filtro.getValoresFiltros();
        
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            r.setConn(conn);
            
            for (Long id: ids) {
                      estados.add(r.listarPorCategorias(id,filtro.getNombreUsuario()));
            }
            return estados;
        } catch (SQLException e) {
            throw new DatosInvalidosUsuarioException();
        }
        
    }
    
    public List<EstadoRevistaDTO> obtnerPorEtiquetas(FiltroNavegacionRevistas filtro) {
        if (filtro.getValoresFiltros().isEmpty()) {
            return null;
        }
        List<Long>ids = filtro.getValoresFiltros();
        
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            r.setConn(conn);
            return r.listarPorEtiquetas(filtro.getValoresFiltros(), filtro.getNombreUsuario());
        } catch (SQLException e) {
            throw new DatosInvalidosUsuarioException();
        }
        
    }

    public List<EstadoRevistaDTO> obtnerPorCategoriaYEtiquetas(FiltroNavegacionRevistas filtro) {
        if (filtro.getValoresFiltros().isEmpty()) {
            return null;
        }
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            r.setConn(conn);
            return r.listarPorEtiquetasYCategoria(filtro.getValoresFiltros(), filtro.getIdCategoria(), filtro.getNombreUsuario());
        } catch (SQLException e) {
            throw new DatosInvalidosUsuarioException();
        }
    }

    private String construirConsultaEtiquetas(List<Long> etiquetas) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int j = 0; j < etiquetas.size(); j++) {
        stringBuilder.append(etiquetas.get(j));
        
        if (j < etiquetas.size() - 1) {
            stringBuilder.append(",");
        }
    }
    
    return stringBuilder.toString();
}

    
}
