/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasCarteraDigital;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.CreadoresModelo.CreadorCarteraDigital;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.usuariosDTOs.CarteraDigitalDTO;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ServicioCarteraDigital {
    
    private CreadorCarteraDigital creadorCartera;
    private ConsultasCarteraDigital consultaCartera;
    
    public ServicioCarteraDigital() {
        this.creadorCartera = new CreadorCarteraDigital();
        this.consultaCartera = new ConsultasCarteraDigital();
    }
    //delegar a que reciba CarteraDigital dto y alguien la cree ccon validacion retorne cartera digital esto para 
    public void debitarSaldoCartera(CarteraDigitalDTO carteraDTO){

        if (!carteraDTO.esValido()) {
            throw new DatosInvalidosUsuarioException();
        }
        CarteraDigital carteraActual = obtenerCarteraActual(carteraDTO.getNombreUsuario());
        
        if (carteraActual.getCantidadDinero() < carteraDTO.getCantidadDinero()) {
            throw new DatosInvalidosUsuarioException("Saldo insuficiente para realizar el débito");
        }
    
        carteraActual.setCantidadDinero(carteraActual.getCantidadDinero() - carteraDTO.getCantidadDinero());
        consultaCartera.actualizarCarteraDigital(carteraActual);
}
    
    public CarteraDigital recargarSaldoCartera(CarteraDigitalDTO carteraDTO){
        System.out.println(" CANTIDAD A RECARGAR " + carteraDTO.getCantidadDinero());
        if (!carteraDTO.esValido()) {
            throw new DatosInvalidosUsuarioException(" La cartera lleva campos no validos");
        }
        
        CarteraDigital saldoActual = obtenerCarteraActual(carteraDTO.getNombreUsuario());
        System.out.println(saldoActual.getCantidadDinero());
        Optional<CarteraDigital> carteraSaldoNuevo = creadorCartera.crearYValidar(carteraDTO, saldoActual);
        System.out.println(carteraSaldoNuevo.get().getCantidadDinero() + " saldo nuevo");
        if (carteraSaldoNuevo.isEmpty()) {
            throw new DatosInvalidosUsuarioException("La cartera nueva se encuentra vacia");
        }
        System.out.println(carteraSaldoNuevo.get().getNombreUsuario());
        CarteraDigital c = consultaCartera.actualizarCarteraDigital(carteraSaldoNuevo.get());
        System.out.println("RETORNA " + c.getCantidadDinero());
        return c;
    }
    
    public CarteraDigital obtenerCarteraActual(String nombreUsuario){
        Optional<CarteraDigital> saldoActual = consultaCartera.obtenerCarteraDigitalUsuario(nombreUsuario);
        if (!saldoActual.isPresent()) {
             throw new NotFoundException("No se encontro la cartera digital");
        }
        return saldoActual.get();
    }
    
}
