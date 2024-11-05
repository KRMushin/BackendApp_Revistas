/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Reportes;

import com.mycompany.apprevistas.backend.modelos.Comentario;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RevistaConComentarios {
    
    private Long idRevista;
    private String tituloRevista;
    private Comentario comentariosRevista;
    public RevistaConComentarios() {
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public Comentario getComentariosRevista() {
        return comentariosRevista;
    }

    public void setComentariosRevista(Comentario comentariosRevista) {
        this.comentariosRevista = comentariosRevista;
    }

    
    
    
}
