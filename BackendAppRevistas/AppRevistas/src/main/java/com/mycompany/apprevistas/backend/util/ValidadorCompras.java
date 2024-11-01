/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.util;

import com.mycompany.apprevistas.backend.AnunciosDTOs.AnuncioDTO;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.DineroInsuficienteException;
import com.mycompany.apprevistas.backend.RevistasDTOs.CompraBloqueoDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.modelos.ConfiguracionAnuncio;

/**
 *
 * @author kevin-mushin
 */
public class ValidadorCompras {

    public Double validarCompraAnuncio(AnuncioDTO anuncioDTO, CarteraDigital carteraDigital, ConfiguracionAnuncio datosAnuncio) throws DineroInsuficienteException {

        try {
                  Double costoTotal = obtenerCostoTotalAnuncio(anuncioDTO, datosAnuncio);
                  if (carteraDigital.getCantidadDinero() < costoTotal) {
                      throw new DineroInsuficienteException();
                  }
                  
                  return costoTotal;
            } catch (NullPointerException | NumberFormatException e) {
                throw new DatosInvalidosUsuarioException("datos nulos o formatos de numeros invalidos");
            }
    }

    private Double obtenerCostoTotalAnuncio(AnuncioDTO anuncioDTO, ConfiguracionAnuncio datosAnuncio) throws NumberFormatException{
        return (datosAnuncio.getPrecio() + (anuncioDTO.getDiasDuracion() * datosAnuncio.getTiempoDuracion()) ); // suma del precio base + la multiplicacion del precio por dia por los dias de duracion
    }

    public Double validarCompraBloqueo(CarteraDigital carteraDigital, LlaveRevistaDTO llave, CompraBloqueoDTO compraBloqueo) throws DineroInsuficienteException {
        try {
               Double costoTotal = obtenerCostoTotalBloqueo(compraBloqueo, llave);
                  if (carteraDigital.getCantidadDinero() < costoTotal) {
                      throw new DineroInsuficienteException();
                  }
                  return costoTotal;
        } catch (NullPointerException | NumberFormatException e) {
                throw new DatosInvalidosUsuarioException("datos nulos o formatos de numeros invalidos");
        }
    }

    private Double obtenerCostoTotalBloqueo(CompraBloqueoDTO compraBloqueo, LlaveRevistaDTO llave) {
            return (compraBloqueo.getDiasCompra() * llave.getCostoBloqueoAnuncios());
    }



    
}
