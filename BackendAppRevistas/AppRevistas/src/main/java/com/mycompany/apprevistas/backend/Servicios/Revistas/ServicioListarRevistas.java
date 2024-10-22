/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Revistas;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasRevistas;
import com.mycompany.apprevistas.backend.ConsultasPorParametros.ConsultasLlavesRevistas;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.RevistasDTOs.EstadoRevista;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.constantes.RevistaEstadoVisibilidad;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ServicioListarRevistas {
    
    private ConsultasLlavesRevistas consultasLlaves;
    private ConsultasRevistas rss;
    
    public ServicioListarRevistas() {
        this.consultasLlaves = new ConsultasLlavesRevistas();
        this.rss = new ConsultasRevistas();
    }
    
    public List<LlaveRevistaDTO> obtenerRevistasPorEstado(String estadoCadena) {
        if (estadoCadena == null) {
            throw new DatosInvalidosUsuarioException();
        }
        try {
            RevistaEstadoVisibilidad estado = RevistaEstadoVisibilidad.valueOf(estadoCadena);
            return consultasLlaves.obtnerRevistasPorEstados(estado);
        } catch (IllegalArgumentException e) {
            throw new DatosInvalidosUsuarioException(e);
        }
    }
}
