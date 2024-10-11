/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.entidades;

import com.mycompany.apprevistas.backend.util.TipoPreferencia;

/**
 *
 * @author kevin-mushin
 */
public class PreferenciaUsuario {
    
    private  Long idPreferencia; 
    private String nombreUsuario; //llave foranea en DB
    private TipoPreferencia tipoPreferencia;
    private String valorPreferencia;

    public PreferenciaUsuario(String preferencia, String nombreUsuario, TipoPreferencia tipoPreferencia) {
        this.valorPreferencia = preferencia;
        this.nombreUsuario = nombreUsuario;
        this.tipoPreferencia = tipoPreferencia;
    }
        /*AREA SETTERS Y GETTERS*/
    public Long getIdPreferencia() {
        return idPreferencia;
    }

    public void setIdPreferencia(Long idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public TipoPreferencia getTipoPreferencia() {
        return tipoPreferencia;
    }

    public void setTipoPreferencia(TipoPreferencia tipoPreferencia) {
        this.tipoPreferencia = tipoPreferencia;
    }

    public String getValorPreferencia() {
        return valorPreferencia;
    }

    public void setValorPreferencia(String valorPreferencia) {
        this.valorPreferencia = valorPreferencia;
    }
    
        
        
}
