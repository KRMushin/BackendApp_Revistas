/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.CreadoresModelo;

import com.mycompany.apprevistas.backend.AnunciosDTOs.AnuncioDTO;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.util.AlmacenAnuncios;
import com.mycompany.apprevistas.backend.constantes.TipoAnuncio;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author kevin-mushin
 */
public class CreadorAnuncio {
    
    private final AlmacenAnuncios almacenArchivos;

    public CreadorAnuncio() {
        this.almacenArchivos = new AlmacenAnuncios();
    }

    public Anuncio crearYAlmacenar(AnuncioDTO anuncioDTO, InputStream archivoInputStream, String nombreArchivo) throws IOException  {
            Anuncio anuncio = new Anuncio();

            anuncio.setNombreUsuario(anuncioDTO.getNombreUsuario());
            anuncio.setFechaCompra(anuncioDTO.getFechaPago());
            anuncio.setTipoAnuncio(anuncioDTO.getTipoAnuncio());
            anuncio.setDiasDuracion(anuncioDTO.getDiasDuracion());
            TipoAnuncio tipoAnuncio = anuncioDTO.getTipoAnuncio();

        if (null != tipoAnuncio) switch (tipoAnuncio) {
                case IMAGEN_TEXTO:
                    if (!nombreArchivo.endsWith(".jpg") && !nombreArchivo.endsWith(".png")) {
                        throw new DatosInvalidosUsuarioException("el archivo no termina en png o jpg");
                    }
//                    anuncio.setRutaTexto(almacenArchivos.almacenarTexto(anuncioDTO.getTextoAnuncio(),nombreArchivo));
                    anuncio.setRutaTexto(anuncioDTO.getTextoAnuncio());
                    anuncio.setRutaImagenTexto(almacenArchivos.guardarArchivoAnuncio(archivoInputStream, nombreArchivo));
                    break;
                case VIDEO:
                    if (!nombreArchivo.endsWith(".mp4")) {
                        throw new DatosInvalidosUsuarioException();
                    }
                    anuncio.setRutaVideo(almacenArchivos.guardarArchivoAnuncio(archivoInputStream, nombreArchivo));
                    break;
                case TEXTO:
//                    anuncio.setRutaTexto(almacenArchivos.almacenarTexto(anuncioDTO.getTextoAnuncio(),nombreArchivo));
                    anuncio.setRutaTexto(anuncioDTO.getTextoAnuncio());

                    break;
                default:
                    break;
        }
         return anuncio;      
    }
}
