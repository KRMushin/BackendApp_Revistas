/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.CreadoresModelo;

import com.mycompany.apprevistas.backend.AnunciosDTOs.LlaveAnuncioDTO;
import com.mycompany.apprevistas.backend.constantes.TipoAnuncio;
import com.mycompany.apprevistas.backend.util.AlmacenAnuncios;
import jakarta.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;

/**
 *
 * @author kevin-mushin
 */
public class CreadorAnuncioMultipart {
    
//    private AlmacenAnuncios almacenAnuncios;
//
//    public CreadorAnuncioMultipart() {
//        this.almacenAnuncios = new AlmacenAnuncios();
//    }
//    
//    public Optional<FormDataMultiPart> obtnerMultipart(LlaveAnuncioDTO anuncioLlave) throws IOException {
//            FormDataMultiPart multipart = obtenerDatosAnuncio(anuncioLlave);
//
//            if (anuncioLlave.getTipoAnuncio() == TipoAnuncio.TEXTO) {
////                String contenidoTexto = almacenAnuncios.obtenerAnuncioTexto(anuncioLlave.getRutaTexto());
////                multipart.field("contenidoTexto", contenidoTexto);
//                return Optional.of(multipart);
//                
//             } else if (anuncioLlave.getTipoAnuncio() == TipoAnuncio.IMAGEN_TEXTO) {
//                return obtenerAnuncioImagenTexto(multipart, anuncioLlave);
//
//             } else if (anuncioLlave.getTipoAnuncio() == TipoAnuncio.VIDEO) {
//                 return obtenerAnuncioVideo(multipart, anuncioLlave);
//              }
//             return Optional.empty();
//    } 
//        
//    private FormDataMultiPart obtenerDatosAnuncio(LlaveAnuncioDTO anuncioLlave) {
//      
//      FormDataMultiPart multipart = new FormDataMultiPart();
//      multipart.field("idAnuncio", anuncioLlave.getIdAnuncio().toString());
//      multipart.field("tipoAnuncio", anuncioLlave.getTipoAnuncio().toString());
//      return multipart;
//  }
//
//    private Optional<FormDataMultiPart> obtenerAnuncioImagenTexto(FormDataMultiPart multipart, LlaveAnuncioDTO anuncioLlave) throws IOException {
//        
////        String contenidoTexto = almacenAnuncios.obtenerAnuncioTexto(anuncioLlave.getRutaTexto());
//        InputStream imagen = almacenAnuncios.obtenerImagenAnuncio(anuncioLlave.getRutaTextoImagen());
//        String tipoMime = obtenerTipoMime(anuncioLlave.getRutaTextoImagen());
////        multipart.field("contenidoTexto", contenidoTexto);
//        multipart.bodyPart(new StreamDataBodyPart("imagen", imagen, "imagen.jpg", MediaType.valueOf(tipoMime)));
//        return Optional.of(multipart);
//    }
//
//    private Optional<FormDataMultiPart> obtenerAnuncioVideo(FormDataMultiPart multipart, LlaveAnuncioDTO anuncioLlave) throws FileNotFoundException {
//        InputStream video = almacenAnuncios.obtenerVideoAnuncio(anuncioLlave.getRutaVideo());
//        multipart.bodyPart(new StreamDataBodyPart("video", video, "video.mp4", MediaType.valueOf("video/mp4")));
//        return Optional.of(multipart);
//    }
//
//    private String obtenerTipoMime(String rutaArchivo) throws IOException {
//            
//             Path archivoPath =  new File(rutaArchivo).toPath();
//            return Files.probeContentType((java.nio.file.Path) archivoPath);
//    }
}
