/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.CreadoresModelo;

import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.usuariosDTOs.CarteraDigitalDTO;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class CreadorCarteraDigital {

    public Optional<CarteraDigital> crearYValidar(CarteraDigitalDTO carteraDTO, CarteraDigital carteraSaldoActual) {
         
         CarteraDigital carteraNueva = new CarteraDigital();
           if (carteraDTO.getCantidadDinero() < 0) {
              throw new DatosInvalidosUsuarioException();
           }
           Double cantidadNueva = carteraDTO.getCantidadDinero() + carteraSaldoActual.getCantidadDinero();
           carteraNueva.setCantidadDinero(cantidadNueva);
           carteraNueva.setNombreUsuario(carteraSaldoActual.getNombreUsuario());
           
           return Optional.of(carteraNueva);
    }

}
