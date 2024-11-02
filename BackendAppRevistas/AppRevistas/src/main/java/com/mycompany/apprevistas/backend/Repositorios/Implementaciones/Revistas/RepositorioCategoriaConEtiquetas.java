/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.modelos.Categoria;
import com.mycompany.apprevistas.backend.modelos.Etiqueta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioCategoriaConEtiquetas {
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

     
    
    public List<Categoria> categoriasConEtiquetas() throws SQLException{
         List<Categoria> categorias = new ArrayList<>();
         String obtnerCategorias = "SELECT * FROM categorias";
        try(PreparedStatement stmt = conn.prepareStatement(obtnerCategorias)){
              ResultSet rs = stmt.executeQuery();
        
           while (rs.next()) {
               Categoria categoria = crearCategoria(rs);
               categoria.setEtiquetas(obtenerEtiquetas(categoria.getIdCategoria()));
               categorias.add(categoria);
           }
         }
        return categorias;
        
    }
    public Categoria categoriaConEtiqueta(Long idCategoria) throws SQLException {
        Categoria categoria = null;
        String obtnerCategoria = "SELECT * FROM categorias WHERE id_Categoria = ?";

        try (PreparedStatement stmt = conn.prepareStatement(obtnerCategoria)) {
                stmt.setLong(1, idCategoria);
        
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    categoria = crearCategoria(rs);
                    categoria.setEtiquetas(obtenerEtiquetas(categoria.getIdCategoria()));
                }
        }
            return categoria;  // Retornar la categoría con sus etiquetas (o null si no se encontró ninguna)
}


    private Categoria crearCategoria(ResultSet rs) throws SQLException {
        Long idCategoria = rs.getLong("id_categoria");
        String nombreCategoria = rs.getString("nombre_categoria");
    return new Categoria(idCategoria, nombreCategoria, new ArrayList<>());
}


    private List<Etiqueta> obtenerEtiquetas(Long idCategoria) throws SQLException {
            List<Etiqueta> etiquetas = new ArrayList<>();

            String queryEtiquetas = "SELECT e.id_etiqueta, e.nombre_etiqueta from etiquetas e JOIN categoria_etiqueta ce ON e.id_etiqueta = ce.id_etiqueta WHERE ce.id_categoria = ?";

            try (PreparedStatement statementEtiquetas = conn.prepareStatement(queryEtiquetas)) {
                statementEtiquetas.setLong(1, idCategoria);
                
                try (ResultSet resultSetEtiquetas = statementEtiquetas.executeQuery()) {
                    while (resultSetEtiquetas.next()) {
                        Long idEtiqueta = resultSetEtiquetas.getLong("id_etiqueta");
                        String nombreEtiqueta = resultSetEtiquetas.getString("nombre_etiqueta");

                        Etiqueta etiqueta = new Etiqueta(idEtiqueta, nombreEtiqueta);
                        etiquetas.add(etiqueta);
                    }
                }
            }
            return etiquetas;
    }

    public List<Categoria> listarCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

            String queryEtiquetas = "SELECT *FROM categorias";

            try (PreparedStatement stmt = conn.prepareStatement(queryEtiquetas)) {
                    ResultSet rs = stmt.executeQuery();
                
                    while (rs.next()) {
                        Long idCategoria = rs.getLong("id_categoria");
                        String nombreCategoria = rs.getString("nombre_categoria");

                        Categoria etiqueta = new Categoria(idCategoria,nombreCategoria,null);
                        categorias.add(etiqueta);
                    }
            }
            return categorias;
    }

    public List<Etiqueta> listarEtiquetas() throws SQLException {
        List<Etiqueta> etiquetas = new ArrayList<>();

            String queryEtiquetas = "SELECT *FROM etiquetas";

            try (PreparedStatement stmt = conn.prepareStatement(queryEtiquetas)) {
                    ResultSet rs = stmt.executeQuery();
                
                    while (rs.next()) {
                        Long idEtiqueta = rs.getLong("id_etiqueta");
                        String nombreEtiqueta = rs.getString("nombre_etiqueta");

                        Etiqueta etiqueta = new Etiqueta(idEtiqueta, nombreEtiqueta);
                        etiquetas.add(etiqueta);
                    }
            }
            return etiquetas;
    }
    
}
