/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Revistas;

import com.mycompany.apprevistas.backend.ActualizacionesModelo.ActualizacionesRevista;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasRevistas;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.RevistasDTOs.NuevoCostoDTO;

/**
 *
 * @author kevin-mushin
 */
public class ServicioCostosRevistas {

    private ActualizacionesRevista actualizacionesRevista;
    private ConsultasRevistas consultasRevista;

    public ServicioCostosRevistas() {
        this.actualizacionesRevista = new ActualizacionesRevista();
        this.consultasRevista = new ConsultasRevistas();
    }

    public void actualizarCostoRevista(NuevoCostoDTO nuevoCosto) {
        if (!nuevoCosto.esValidao()) {
            throw new DatosInvalidosUsuarioException();
        }
        if (!consultasRevista.existeRevista(nuevoCosto.getIdRevista())) {
            throw new DatosInvalidosUsuarioException();
        }
        actualizacionesRevista.actualizarCostoRevista(nuevoCosto);
    }
    
    
    
}
