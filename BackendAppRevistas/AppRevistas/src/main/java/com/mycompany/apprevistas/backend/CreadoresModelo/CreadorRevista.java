/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.CreadoresModelo;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.RevistasDTOs.RevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Revista;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class CreadorRevista {

    public Optional<Revista> crearYValidarRevista(RevistaDTO revistaDTO) {
        
        
        try {
                if (revistaDTO.getIdEtiquetas().size() <= 0) {
                   throw new DatosInvalidosUsuarioException();
                }
                Revista revista = new Revista();
                revista.setTituloRevista(revistaDTO.getTituloRevista());
                revista.setDescripcion(revistaDTO.getDescripcion());
                revista.setFechaCreacion(revistaDTO.getFechaPublicacion());
                revista.setIdCategoria(revistaDTO.getIdCategoria());
                revista.setNombreAutor(revistaDTO.getNombreAutor());
                revista.setIdEtiquetas(revistaDTO.getIdEtiquetas());
                
                return Optional.of(revista);
        } catch (NumberFormatException | NullPointerException  e) {
            throw new DatosInvalidosUsuarioException(e);
        }
    }
}
