/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasConfigAnuncios;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.modelos.ConfiguracionAnuncio;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioConfigAnuncios {
    
    private ConsultasConfigAnuncios consultaConfiguraciones;

    public ServicioConfigAnuncios() {
            this.consultaConfiguraciones = new ConsultasConfigAnuncios();
    }
    
    public List<ConfiguracionAnuncio> obtenerConfiguraciones() {
        return consultaConfiguraciones.obtenerConfiguracionesAnuncios();
    }
    
    public void actualizarConfiguracion(ConfiguracionAnuncio config){
         if (!config.esValido()) {
             System.out.println("config no valida");
            throw new DatosInvalidosUsuarioException();
        }
         consultaConfiguraciones.actualizarConfiguracion(config);
    }
}
