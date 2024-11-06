/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import com.mycompany.apprevistas.backend.Excepciones.ErrorInternoException;
import com.mycompany.apprevistas.backend.modelos.FotoUsuario;
import jakarta.ws.rs.Path;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author kevin-mushin
 */
public class AlmacenFotos {

    public static final String PATH_IMAGENES = "/home/kevin-mushin/servidor/app-revistas/img-perfil";

    public FotoUsuario guardarFotoUsuario(InputStream fotoInputStream, String nombreUsuario) {
        try {
            String extension = ".png"; // Cambia a la extensión que prefieras o recibe como parámetro
            String nombreArchivo = nombreUsuario + extension;

            // Crear la ruta completa donde se almacenará la imagen
            java.nio.file.Path rutaArchivo = Paths.get(PATH_IMAGENES, nombreArchivo);

            // Guardar el archivo en la ruta especificada
            Files.copy(fotoInputStream, rutaArchivo, StandardCopyOption.REPLACE_EXISTING);

            // Crear y devolver el objeto FotoUsuario con la ruta y el nombre de usuario
            FotoUsuario fotoUsuario = new FotoUsuario();
            fotoUsuario.setFotoUrl(rutaArchivo.toString());
            fotoUsuario.setNombreUsuario(nombreUsuario);

            return fotoUsuario;

        } catch (IOException e) {
            throw new ErrorInternoException(); // O maneja la excepción según tu lógica
        }
    }


    
}
