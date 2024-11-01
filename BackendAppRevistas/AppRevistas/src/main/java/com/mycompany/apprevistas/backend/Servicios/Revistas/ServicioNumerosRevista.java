/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Revistas;

import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasNumerosRevistas;
import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasRevistas;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.ErrorInternoException;
import com.mycompany.apprevistas.backend.RevistasDTOs.NumeroRevistaDTO;
import com.mycompany.apprevistas.backend.util.ValidadorArchivos;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
public class ServicioNumerosRevista {

    private final ConsultasRevistas consultasRevistas;
    private final ConsultasNumerosRevistas consultasNumeros;
    private final ValidadorArchivos validador;

    public ServicioNumerosRevista() {
        this.consultasRevistas = new ConsultasRevistas();
        this.consultasNumeros = new ConsultasNumerosRevistas();
        this.validador = new ValidadorArchivos();
    }
    
    public Optional<NumeroRevistaDTO> publicarNumeroRevista(Long idRevista, InputStream numeroRevista, String tituloNumero, String fechaPublicacion) {
        byte[] contenidoArchivo;
        try {
             contenidoArchivo = numeroRevista.readAllBytes();
            
            if (!validador.isPdf(contenidoArchivo)) {
                throw new DatosInvalidosUsuarioException();
            }
            LocalDate fechaParse = parsearFecha(fechaPublicacion);
            return consultasNumeros.publicarNumeroRevista(new NumeroRevistaDTO(idRevista, contenidoArchivo,  tituloNumero, fechaParse));
        } catch (IOException ex) {
            throw new ErrorInternoException(ex);
        }
    }
    
    public List<NumeroRevistaDTO> obtnerTodosLosNumeros(Long idRevista) { // consulto por idRevista
        if (!consultasRevistas.existeRevista(idRevista)) {
            return null;
        }
         return consultasNumeros.obtnerTodosLosNumeros(idRevista);
    }

    public InputStream obtnerArchivoNumero(Long idNumeroRevista) {
         return consultasNumeros.obtnerarchivoNumero(idNumeroRevista);
    }

    private LocalDate parsearFecha(String fechaPublicacion) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fechaPublicacion, formatter);
    } catch (Exception e) {
        throw new DatosInvalidosUsuarioException("Formato de fecha inválido. Use 'yyyy-MM-dd'.");
    }
}

}
