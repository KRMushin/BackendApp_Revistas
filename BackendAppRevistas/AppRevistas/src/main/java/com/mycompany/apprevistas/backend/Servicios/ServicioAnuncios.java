/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.apprevistas.backend.AnunciosDTOs.AnuncioDTO;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasAnuncios;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasCarteraDigital;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasConfigAnuncios;
import com.mycompany.apprevistas.backend.CreadoresModelo.CreadorAnuncio;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.DineroInsuficienteException;
import com.mycompany.apprevistas.backend.Excepciones.ErrorInternoException;
import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.modelos.ConfiguracionAnuncio;
import com.mycompany.apprevistas.backend.util.AlmacenAnuncios;
import com.mycompany.apprevistas.backend.util.ValidadorCompras;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
public class ServicioAnuncios {
    
    private final AlmacenAnuncios almacenArchivos;
    private final CreadorAnuncio creadorAnuncio;
    private final ConsultasAnuncios consultasAnuncios;
    private final ConsultasCarteraDigital consultasCarteras;
    private final ConsultasConfigAnuncios consultasConfigAnuncios;
    private final ValidadorCompras validarCompra;
 
    public ServicioAnuncios() {
        this.almacenArchivos = new AlmacenAnuncios();
        this.creadorAnuncio = new CreadorAnuncio();
        this.consultasAnuncios = new ConsultasAnuncios();
        this.consultasCarteras = new ConsultasCarteraDigital();
        this.consultasConfigAnuncios = new ConsultasConfigAnuncios();
        this.validarCompra = new ValidadorCompras();
    }
    
    public List<Anuncio> obtenerAnunciosUsuario(String nombreUsuario) {
            if (nombreUsuario.equals("Admin1")) {
                return consultasAnuncios.obtenerTodos();
            }
            return consultasAnuncios.obtenerAnunciosUsuario(nombreUsuario);
    }

    public void publicarAnuncio(String anuncioDTOJson, InputStream archivoInputStream, String nombreArchivo) throws DineroInsuficienteException{
        try {
            AnuncioDTO anuncioDTO = deserealizarDTO(anuncioDTOJson);
            
            if (!anuncioDTO.esValido()) {
                throw new DatosInvalidosUsuarioException();
            }
            
            Optional<CarteraDigital> carteraUsuario = consultasCarteras.obtenerCarteraDigitalUsuario(anuncioDTO.getNombreUsuario());
            ConfiguracionAnuncio datosAnuncio = consultasConfigAnuncios.obtenerDatosAnuncio(anuncioDTO.getTipoAnuncio());
            
            if (carteraUsuario.isEmpty() || datosAnuncio == null) {
                throw new NotFoundException();
            }

            Double costoTotalAnuncio = validarCompra.validarCompraAnuncio(anuncioDTO, carteraUsuario.get(), datosAnuncio);
            Anuncio anuncio = creadorAnuncio.crearYAlmacenar(anuncioDTO, archivoInputStream, nombreArchivo);
            
            carteraUsuario.get().setCantidadDinero(carteraUsuario.get().getCantidadDinero() - costoTotalAnuncio);
            anuncio.setPrecioTotal(costoTotalAnuncio);
            consultasAnuncios.guardarAnuncio(anuncio, carteraUsuario.get());
            
        } catch (IOException ex) {
                throw new ErrorInternoException(ex);
        }
    }

    public void actualizarEstadoAnuncio(Long idAnuncio, boolean habilitado) {
            Anuncio anuncio = new Anuncio();
            anuncio.setIdAnuncio(idAnuncio);
            anuncio.setAnuncioHabilitado(habilitado);
            consultasAnuncios.actualizarEstadoAnuncio(anuncio);
    }
    
    public AnuncioDTO deserealizarDTO(String anuncioDTOJson) {
        try {
            // Usar ObjectMapper de Jackson para deserializar el JSON
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(anuncioDTOJson, AnuncioDTO.class);
        } catch (JsonProcessingException e) {
            throw new ErrorInternoException(e);
        }
    }
}
