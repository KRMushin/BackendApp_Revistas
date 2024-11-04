/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.ComentariosYLikes;

import com.mycompany.apprevistas.backend.ConsultasModelos.ComentariosYLikes.ConsultasComentarios;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasUsuarios;
import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasRevistas;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.modelos.Comentario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ServicioComentarios {
    
    private ConsultasUsuarios consultasUsuarios;
    private ConsultasRevistas consultasRevistas;
    private ConsultasComentarios consultasComentarios;

    public ServicioComentarios() {
        this.consultasRevistas = new ConsultasRevistas();
        this.consultasUsuarios = new ConsultasUsuarios();
        this.consultasComentarios = new ConsultasComentarios();
    }
    public void publicarComentario(Comentario comentario) {
        if (!comentario.esComentarioValido()) {
            throw new DatosInvalidosUsuarioException();
        }
        if (!consultasUsuarios.esUsuarioExistente(comentario.getNombreUsuario())) {
            throw new DatosInvalidosUsuarioException();
        }
        if (!consultasRevistas.existeRevista(comentario.getIdRevista())) {
            throw new DatosInvalidosUsuarioException();
        }
        
        consultasComentarios.publicarComentario(comentario);
    }

    public Optional<List<Comentario>> obtnerComentariosHechos(String nombreUsuario, Long idRevista) {
        if (!consultasUsuarios.esUsuarioExistente(nombreUsuario)) {
            throw new DatosInvalidosUsuarioException();
        }
        if (!consultasRevistas.existeRevista(idRevista)) {
            throw new DatosInvalidosUsuarioException();
        }
        // lista de comentarios
        List<Comentario> comentarios = consultasComentarios.comentariosUsuario(nombreUsuario,idRevista);
        //retorno de los valores de comentarios que hizo el usuario
        return Optional.of(comentarios);
    }

    public Optional<List<Comentario>> obtnerComentariosRevista(Long idRevista) {
        if (!consultasRevistas.existeRevista(idRevista)) {
            throw new DatosInvalidosUsuarioException();
        }
        List<Comentario> comentariosRevista = consultasComentarios.comentariosRevista(idRevista);
        return Optional.of(comentariosRevista);
    }

    public boolean eliminarComentario(Long idComentario) {
        return consultasComentarios.eliminarComentario(idComentario);
    }
}
