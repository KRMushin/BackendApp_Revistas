/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.tika.Tika;

/**
 *
 * @author kevin-mushin
 */
public class ValidadorArchivos {
    
    public boolean isPdf(byte[] fileData) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData)) {
            Tika tika = new Tika();
            String mimeType = tika.detect(byteArrayInputStream);  // Detectar tipo MIME
            return "application/pdf".equals(mimeType);  // Verificar si es PDF
        } catch (IOException e) {
            throw new DatosInvalidosUsuarioException();
        }
    }
}
