/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public String almacenarTexto(String textoAnuncio, String nombreArchivo) throws IOException {
    String rutaCompletaArchivo = generarNombreUnicoTexto(PATH_ANUNCIOS, nombreArchivo, ".html");
        System.out.println(rutaCompletaArchivo);
            File archivoDestino = new File(rutaCompletaArchivo);

            try (FileWriter writer = new FileWriter(archivoDestino)) {
                writer.write("<html><body>");
                writer.write("<div style='width: 100%; padding: 10px; background-color: #f1f1f1; border: 1px solid #ccc;'>");
                writer.write("<p>" + textoAnuncio + "</p>");
                writer.write("</div>");
                writer.write("</body></html>");
            }

            System.out.println("    EL ARCHIVO SE ALMACENO CORRECTAMENTE" + rutaCompletaArchivo);
            return rutaCompletaArchivo; 
    }
    
    private String generarNombreUnicoTexto(String rutaBase, String nombreArchivo, String extension) {
        String rutaCompletaArchivo = rutaBase + "anuncio_texto" + extension;
        File archivoDestino = new File(rutaCompletaArchivo);

        int contador = 1;
        while (archivoDestino.exists()) {
            String nuevoNombreArchivo = "anuncio_texto" + "_" + contador;
            rutaCompletaArchivo = rutaBase  + nuevoNombreArchivo + extension;
            archivoDestino = new File(rutaCompletaArchivo);
            contador++;
        }
        return rutaCompletaArchivo; 
    }
}
