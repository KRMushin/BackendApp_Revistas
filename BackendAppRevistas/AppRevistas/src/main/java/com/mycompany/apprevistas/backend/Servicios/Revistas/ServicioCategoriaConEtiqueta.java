/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Revistas;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasCategoriaEtiqueta;
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
public class ServicioCategoriaConEtiqueta {
    
    
    private ConsultasCategoriaEtiqueta categoriaConsultas;
    private RepositorioCategoriaConEtiquetas categorias;

    public ServicioCategoriaConEtiqueta() {
        this.categoriaConsultas = new ConsultasCategoriaEtiqueta();
        this.categorias = new RepositorioCategoriaConEtiquetas();
    }
    
    public Optional<List<Categoria>> obtenerCategoriasConEtiqueta(){
        return categoriaConsultas.obtenerCategoriasConEtiquetas();
    } 
}
