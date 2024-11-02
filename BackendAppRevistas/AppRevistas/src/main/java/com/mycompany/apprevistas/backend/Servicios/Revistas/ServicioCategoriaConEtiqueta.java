/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Revistas;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasCategoriaEtiqueta;
import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasRevistas;
import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas.RepositorioCategoriaConEtiquetas;
import com.mycompany.apprevistas.backend.modelos.Categoria;
import com.mycompany.apprevistas.backend.modelos.Etiqueta;
import com.mycompany.apprevistas.backend.modelos.Revista;
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
    private ConsultasRevistas consultasRevistas;
    
    public ServicioCategoriaConEtiqueta() {
        this.categoriaConsultas = new ConsultasCategoriaEtiqueta();
        this.consultasRevistas = new ConsultasRevistas();
    }
    
    public Optional<List<Categoria>> obtenerCategoriasConEtiqueta(){
        return categoriaConsultas.obtenerCategoriasConEtiquetas();
    } 

    public Optional<Categoria> obtenerCategoria(Long idRevista) {
        if (!consultasRevistas.existeRevista(idRevista)) {
            throw new DatosInvalidosUsuarioException();
        }
        Revista revista = consultasRevistas.obtenerPorId(idRevista);
        return categoriaConsultas.obtenerCategoriaConEtiqueta(revista.getIdCategoria());
    }

    public List<Etiqueta> obtenerEtiquetas() {
        return categoriaConsultas.obtenerTodasEtiquetas();
    }

    public List<Categoria> obterTodasCategorias() {
        return categoriaConsultas.obtenerTodasCategorias();
    }
}
