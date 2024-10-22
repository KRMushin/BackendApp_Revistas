/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.constantes;

/**
 *
 * @author kevin-mushin
 */
public enum TipoCostoRevista {
    BLOQUEO_ANUNCIOS {
        @Override
        public String obtenerConsulta() {
            return "costo_bloqueo_anuncios";
        }
    },
    MANTENIMIENTO {
        @Override
        public String obtenerConsulta() {
            return "costo_mantenimiento";
        }
    };
    public abstract String obtenerConsulta();
}
