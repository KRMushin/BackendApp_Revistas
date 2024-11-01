/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author kevin-mushin
 */
public class AlmacenAnuncios {
    
//    public static final String PATH_BASE = "/home/kevin-mushin/servidor/";
    public static final String PATH_ANUNCIOS = "/home/kevin-mushin/servidor/app-revistas/anuncios/";
    
    public String guardarArchivoAnuncio(InputStream archivoInputStream, String nombreArchivo) throws IOException {
        String rutaCompletaArchivo = PATH_ANUNCIOS + nombreArchivo;
        File archivoDestino = new File(rutaCompletaArchivo);

        int contador = 1;
        while (archivoDestino.exists()) {
                String nuevoNombreArchivo = nombreArchivo.replaceFirst("(\\.[^.]*)?$", "_" + contador + "$1");
                rutaCompletaArchivo = PATH_ANUNCIOS + nuevoNombreArchivo;
                archivoDestino = new File(rutaCompletaArchivo);
                contador++;
        }

        try (FileOutputStream out = new FileOutputStream(archivoDestino)) {
            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while ((bytesLeidos = archivoInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesLeidos);
            }
        }
        System.out.println("    EL ARCHIVO SE ALMACENO CORRECTAMENTE" + rutaCompletaArchivo);
        return rutaCompletaArchivo; 
    }


    public InputStream obtenerArchivo(String rutaArchivoAnuncio) throws FileNotFoundException {
    File archivoImagen = new File(rutaArchivoAnuncio);

    if (!archivoImagen.exists()) {
        throw new FileNotFoundException("El anuncio no existe en la ruta especificada: " + rutaArchivoAnuncio);
    }
    return new FileInputStream(archivoImagen);  // Devolver un InputStream para la imagen
}

}
