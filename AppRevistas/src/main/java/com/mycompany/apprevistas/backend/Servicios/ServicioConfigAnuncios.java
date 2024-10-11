/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.backend.Repositorios.Implementaciones.RepositorioConfigAnuncio;
import com.mycompany.apprevistas.backend.entidades.ConfiguracionAnuncio;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioConfigAnuncios {
    
    private RepositorioConfigAnuncio repositorioConfiguracion;

    public ServicioConfigAnuncios() {
        this.repositorioConfiguracion = new RepositorioConfigAnuncio();
    }
    
    public List<ConfiguracionAnuncio> obtenerConfiguraciones() throws SQLException{
        
        try(Connection conn = ConexionBaseDatos.getInstance().getConnection()){
            repositorioConfiguracion.setConn(conn);
            return repositorioConfiguracion.listarTodos();
        }
    }
}
