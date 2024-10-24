/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Anuncios;

import com.mycompany.apprevistas.backend.AnunciosDTOs.LlaveAnuncioDTO;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasAnuncios;
import com.mycompany.apprevistas.backend.CreadoresModelo.CreadorAnuncioMultipart;
import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.constantes.TipoAnuncio;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.util.AlmacenAnuncios;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author kevin-mushin
 */
public class ServicioDespliegueAnuncios {
    
    private ConsultasAnuncios consultasAnuncios;
    private CreadorAnuncioMultipart creadorAnuncio;
    private AlmacenAnuncios almacenAnuncios;
    private Random random;

    public ServicioDespliegueAnuncios() {
        this.consultasAnuncios = new ConsultasAnuncios();
        this.creadorAnuncio = new CreadorAnuncioMultipart();
        this.random = new Random();
        this.almacenAnuncios = new AlmacenAnuncios();
    }

    public Optional<LlaveAnuncioDTO> obtnerAnuncioAleatorio() {
        List<LlaveAnuncioDTO> llaves = consultasAnuncios.obtnerLlavesAnuncios();
            if (llaves.isEmpty()) {
                return Optional.empty();
            }
        LlaveAnuncioDTO anuncioLlave = obtnerUnAnuncioAleatorio(llaves);
        return Optional.of(anuncioLlave);
    }
    
    
    public Optional<InputStream> obtenerArchivoAnuncio(Long idAnuncio) {
            Optional<Anuncio> anuncio = consultasAnuncios.obtenerPorId(idAnuncio);
            
                if (anuncio.isEmpty()) {
                    return Optional.empty();
                }
        try {
                String rutaArchivo = obtenerRuta(anuncio.get());
                return Optional.of(almacenAnuncios.obtenerArchivo(rutaArchivo));
            } catch (FileNotFoundException ex) {
                System.out.println("NO SE ENCONTRO ESA MIERDA");
                    throw new NotFoundException("NO SE ENCONTRO ESA MIERDA");
            }
    }
    
    private LlaveAnuncioDTO obtnerUnAnuncioAleatorio(List<LlaveAnuncioDTO> llaves) {
        int numeroRandom = random.nextInt(llaves.size());
        return llaves.get(numeroRandom);
    }

    private String obtenerRuta(Anuncio anuncio) {
        TipoAnuncio tipo = anuncio.getTipoAnuncio();
        
           if (tipo == TipoAnuncio.VIDEO) {
             return anuncio.getRutaVideo();
          } else if (tipo == TipoAnuncio.IMAGEN_TEXTO) {
             return anuncio.getRutaImagenTexto();
          }else {
             return null;
         }
    }
}


//    public Optional<FormDataMultiPart> obtnerAnuncioAleatorio(){
//        List<LlaveAnuncioDTO> llaves = consultasAnuncios.obtnerLlavesAnuncios();
//        
//        if (llaves.isEmpty()) {
//            return Optional.empty();
//        }
//        LlaveAnuncioDTO anuncioLlave = obtnerAnuncioAleatorio(llaves);
//
//        try {
//            return creadorAnuncio.obtnerMultipart(anuncioLlave);
//        } catch (IOException ex) {
//            return Optional.empty();
//        }
//    }
//
//    private LlaveAnuncioDTO obtnerAnuncioAleatorio(List<LlaveAnuncioDTO> llaves) {
//        int numeroRandom = random.nextInt(llaves.size());
//        System.out.println("numeor generardo " + numeroRandom);
//        System.out.println(llaves.get(numeroRandom).getTipoAnuncio());
//        return llaves.get(numeroRandom);
//    }










