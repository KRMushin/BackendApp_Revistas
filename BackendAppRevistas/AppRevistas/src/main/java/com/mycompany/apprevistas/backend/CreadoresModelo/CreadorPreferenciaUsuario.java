/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.CreadoresModelo;

import com.mycompany.apprevistas.backend.usuariosDTOs.UsuarioDTO;
import com.mycompany.apprevistas.backend.modelos.PreferenciaUsuario;
import com.mycompany.apprevistas.backend.constantes.TipoPreferencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class CreadorPreferenciaUsuario {

    public List<PreferenciaUsuario> validarPreferencias(UsuarioDTO usuarioDTO) {
            List<PreferenciaUsuario> prefs = new ArrayList<>();
            String patronValido = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$";

            List<String> preferencias = usuarioDTO.getPreferencias();
            List<String> hobbies = usuarioDTO.getHobbies();
            List<String> gustos = usuarioDTO.getGustos();

            if (preferencias != null) {
                for (String preferencia : preferencias) {
                    if (preferencia.matches(patronValido)) {
                        prefs.add(new PreferenciaUsuario(preferencia, usuarioDTO.getNombreUsuario(), TipoPreferencia.TEMA_PREFERENCIA));
                    }
                }
            }

            if (hobbies != null) {
                for (String hobby : hobbies) {
                    if (hobby.matches(patronValido)) {
                        prefs.add(new PreferenciaUsuario(hobby, usuarioDTO.getNombreUsuario(), TipoPreferencia.HOBBIE));
                    }
                }
            }

            if (gustos != null) {
                for (String gusto : gustos) {
                    if (gusto.matches(patronValido)) {
                        prefs.add(new PreferenciaUsuario(gusto, usuarioDTO.getNombreUsuario(), TipoPreferencia.GUSTO));
                    }
                }
            }
            return prefs;
}

    
}
