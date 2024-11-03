/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Suscripciones;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasUsuarios;
import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasRevistas;
import com.mycompany.apprevistas.backend.ConsultasModelos.Suscripciones.ConsultasSuscripciones;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Suscripcion;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioSuscripciones {
    
    private ConsultasSuscripciones consultas;
    private ConsultasRevistas consultasRevistas;
    private ConsultasUsuarios consultasUsuarios;
    

    public ServicioSuscripciones() {
        this.consultas = new ConsultasSuscripciones();
        this.consultasRevistas = new ConsultasRevistas();
        this.consultasUsuarios = new ConsultasUsuarios();
    }

    public void realizarSuscripcion(Suscripcion suscripcion) {
        if (!suscripcion.esSuscripcionValida()) {
            throw new DatosInvalidosUsuarioException(" Datos invalidos ");
        }
        if (!consultasRevistas.existeRevista(suscripcion.getIdRevista())) {
            throw new DatosInvalidosUsuarioException("No existe la revista");
        }
        if (!consultasUsuarios.esUsuarioExistente(suscripcion.getNombreUsuario())) {
            throw new DatosInvalidosUsuarioException("No existe el usuario");
        }
        consultas.almacenarSuscripcion(suscripcion);
    }

    public List<LlaveRevistaDTO> obtnerRevistasSuscritas(String nombreUsuario) {
        if (!consultasUsuarios.esUsuarioExistente(nombreUsuario)) {
            throw new DatosInvalidosUsuarioException("No existe el usuario");
        }
        return consultas.obtnerRevistaSuscripcion(nombreUsuario);
    }

    
}
