/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.RevistasDTOs.Configs;

/**
 *
 * @author kevin-mushin
 */
public enum EstadoRevista {
    
    COMENTARIOS {
        @Override
        public String obtenerConsulta() {
            return "revista_comentable";
        }
    },
    LIKES {
        @Override
        public String obtenerConsulta() {
            return "revista_likeable";
        }
    },
    BLOQUE_ANUNCIOS {
        @Override
        public String obtenerConsulta() {
            return "anuncios_bloqueados";
        }
    },
    SUSCRIPCIONES {
        @Override
        public String obtenerConsulta() {
            return "acepta_suscripciones";
        }
    };

    public abstract String obtenerConsulta();
}

