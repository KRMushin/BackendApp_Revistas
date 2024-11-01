/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Revistas;

import com.mycompany.apprevistas.backend.ActualizacionesModelo.ActualizacionesRevista;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasCarteraDigital;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasPreciosGlobales;
import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasComprasBloqueos;
import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasRevistas;
import com.mycompany.apprevistas.backend.ConsultasPorParametros.ConsultasLlavesRevistas;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.Excepciones.DineroInsuficienteException;
import com.mycompany.apprevistas.backend.RevistasDTOs.CompraBloqueoDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoConfigRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoRevista;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.NuevoCostoDTO;
import com.mycompany.apprevistas.backend.modelos.CarteraDigital;
import com.mycompany.apprevistas.backend.util.ValidadorCompras;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ServicioCostosRevistas {

    private ActualizacionesRevista actualizacionesRevista;
    private ConsultasRevistas consultasRevista;
    private ValidadorCompras validador;
    private ConsultasCarteraDigital consultaCarteras;
    private ConsultasPreciosGlobales consultaPrecios;
    private ConsultasLlavesRevistas consultasLlaves;
    private ConsultasComprasBloqueos consultasCompras;

    public ServicioCostosRevistas() {
        this.actualizacionesRevista = new ActualizacionesRevista();
        this.consultasRevista = new ConsultasRevistas();
        this.validador = new ValidadorCompras();
        this.consultaCarteras = new ConsultasCarteraDigital();
        this.consultaPrecios = new ConsultasPreciosGlobales();
        this.consultasLlaves = new ConsultasLlavesRevistas();
        this.consultasCompras = new ConsultasComprasBloqueos();
    }

    public void actualizarCostoRevista(NuevoCostoDTO nuevoCosto) {
        if (!nuevoCosto.esValidao()) {
            throw new DatosInvalidosUsuarioException();
        }
        if (!consultasRevista.existeRevista(nuevoCosto.getIdRevista())) {
            throw new DatosInvalidosUsuarioException();
        }
        actualizacionesRevista.actualizarCostoRevista(nuevoCosto);
    }

    public void realizarCompraBloqueo(CompraBloqueoDTO compraBloqueo) throws DineroInsuficienteException {

        Optional<CarteraDigital> carteraComprador = consultaCarteras.obtenerCarteraDigitalUsuario(compraBloqueo.getNombreUsuario()); // cartera digital
        Optional<LlaveRevistaDTO> llaveRevista = consultasLlaves.obtenerLlavePorId(compraBloqueo.getIdRevista()); // para seber el precioi
       
        if (carteraComprador.isEmpty() || llaveRevista.isEmpty()) {
            throw new DatosInvalidosUsuarioException();
        }
        Double costoTotal = validador.validarCompraBloqueo(carteraComprador.get(), llaveRevista.get(), compraBloqueo);
        CarteraDigital carteraDebitada = debitarSaldo(costoTotal,carteraComprador.get());
        EstadoConfigRevistaDTO actualizarEstado = generarEstadoNuevo(compraBloqueo.getIdRevista());
        compraBloqueo.setCostoTotal(costoTotal);
        consultasCompras.guardarCompra(compraBloqueo, actualizarEstado, carteraDebitada);
    }

    private EstadoConfigRevistaDTO generarEstadoNuevo(Long idRevista) {
            EstadoConfigRevistaDTO estado = new EstadoConfigRevistaDTO();
                estado.setIdRevista(idRevista);
                estado.setTipoEstado(EstadoRevista.BLOQUE_ANUNCIOS);
                estado.setEstado(true);
            return estado;
    }

    private CarteraDigital debitarSaldo(Double costoTotal, CarteraDigital get) {
        get.setCantidadDinero(get.getCantidadDinero() - costoTotal);
        return get;
    }
}
