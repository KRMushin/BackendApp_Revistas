/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.ConsultasModelos;

import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioCategoriaConEtiquetas;
import com.mycompany.apprevistas.backend.modelos.Categoria;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ConsultasCategoriaEtiqueta {
    
    private RepositorioCategoriaConEtiquetas categorias;

    public ConsultasCategoriaEtiqueta() {
        this.categorias = new RepositorioCategoriaConEtiquetas();
    }

    public Optional<List<Categoria>> obtenerCategoriasConEtiquetas() {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            categorias.setConn(conn);
            return Optional.of(categorias.categoriasConEtiquetas());
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }
    public Optional<Categoria> obtenerCategoriaConEtiqueta(Long idCategoria) {
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            categorias.setConn(conn);
            return Optional.of(categorias.categoriaConEtiqueta(idCategoria));
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }
    
}
