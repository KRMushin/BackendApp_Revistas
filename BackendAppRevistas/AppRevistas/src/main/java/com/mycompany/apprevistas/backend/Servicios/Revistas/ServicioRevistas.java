/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Revistas;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasArchivosRevistas;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasRevistas;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasUsuarios;
import com.mycompany.apprevistas.backend.CreadoresModelo.CreadorRevista;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.RevistasDTOs.EstadoConfigRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.EstadoRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.RevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Revista;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ServicioRevistas {

    private ConsultasArchivosRevistas consultasArchivos;
    private ConsultasRevistas consultasRevista;
    private ConsultasUsuarios consultasUsuarios;
    private CreadorRevista creadorRevistas;

    public ServicioRevistas() {
        this.consultasRevista = new ConsultasRevistas();
        this.creadorRevistas = new CreadorRevista();
        this.consultasUsuarios = new ConsultasUsuarios();
        this.consultasArchivos = new ConsultasArchivosRevistas();
    }
    
    public Revista publicarDatosRevista(RevistaDTO revistaDTO){
        
        Optional<Revista> revista = creadorRevistas.crearYValidarRevista(revistaDTO);
        if (revista.isEmpty()) {
            throw new DatosInvalidosUsuarioException();
        }
        return consultasRevista.publicarRevista(revista.get());
    }

    public void guardarRevistaPDF(Long idRevista, InputStream revistaInputStream) {
            if (!consultasRevista.existeRevista(idRevista)) {
                throw new  NotFoundException();
            }
            consultasArchivos.guardarRevistaPDF(idRevista,revistaInputStream);
    }

    public EstadoRevistaDTO obtenerEstadosRevista(Long idRevista) {
        if (!consultasRevista.existeRevista(idRevista)) {
            throw new DatosInvalidosUsuarioException("La revista no existe");
        }
        return consultasRevista.ObtenerEstadoRevista(idRevista);
    }

    public void actualizarEstadoRevista(EstadoConfigRevistaDTO estado) {
        if (!consultasRevista.existeRevista(estado.getIdRevista())) {
            throw new DatosInvalidosUsuarioException("La revista no existe");
        }
        consultasRevista.actualizarEstado(estado);
    }

    public List<LlaveRevistaDTO> obtenerPublicacionesUsuario(String nombreUsuario) {
        if (!consultasUsuarios.esUsuarioExistente(nombreUsuario)) {
            throw new NotFoundException();
        }
        return consultasRevista.obtenerPublicacionesUsuario(nombreUsuario);
    }

    public Revista obtenerDatosRevista(Long idRevista) {
        if (!consultasRevista.existeRevista(idRevista)) {
            throw new DatosInvalidosUsuarioException("La revista no existe");
        }
        return consultasRevista.obtenerDatosRevista(idRevista);
    }
    
    public InputStream obtnerRevistaPDF(Long idRevista){
        if (!consultasRevista.existeRevista(idRevista)) {
            throw new DatosInvalidosUsuarioException("La revista no existe");
        }
        Optional<InputStream> pdf = consultasArchivos.obtnerRevistaPDF(idRevista);
        if (pdf.isEmpty()) {
            throw new NotFoundException();
        }
        return pdf.get();
    }

    public void activarRevista(Long idRevista) {
        if (!consultasRevista.existeRevista(idRevista)) {
            throw new DatosInvalidosUsuarioException("La revista no existe");
        }
        consultasRevista.activarRevista(idRevista);
    }
}
