/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.ConsultasModelos.ConsultasCarteraDigital;
import com.mycompany.apprevistas.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.Excepciones.NotFoundException;
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
        
        if (carteraActual.getSaldoDisponible() < carteraDTO.getCantidadDinero()) {
            throw new DatosInvalidosUsuarioException("Saldo insuficiente para realizar el débito");
        }
    
        carteraActual.setSaldoDisponible(carteraActual.getSaldoDisponible() - carteraDTO.getCantidadDinero());
        consultaCartera.guardarCarteraDigital(carteraActual);
}
    
    public void recargarSaldoCartera(CarteraDigitalDTO carteraDTO){
        
        if (!carteraDTO.esValido()) {
            throw new DatosInvalidosUsuarioException();
        }
        
        CarteraDigital saldoActual = obtenerCarteraActual(carteraDTO.getNombreUsuario());
        Optional<CarteraDigital> carteraSaldoNuevo = creadorCartera.crearYValidar(carteraDTO, saldoActual);
        
        if (!carteraSaldoNuevo.isEmpty()) {
            throw new DatosInvalidosUsuarioException();
        }
        consultaCartera.guardarCarteraDigital(carteraSaldoNuevo.get());
    }
    
    public CarteraDigital obtenerCarteraActual(String nombreUsuario){
        Optional<CarteraDigital> saldoActual = consultaCartera.obtenerCarteraDigitalUsuario(nombreUsuario);
        if (!saldoActual.isPresent()) {
             throw new NotFoundException("No se encontro la cartera digital");
        }
        return saldoActual.get();
    }
    
}
