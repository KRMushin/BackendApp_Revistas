/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.ComentariosYLikes;

import com.mycompany.apprevistas.backend.ConsultasModelos.ComentariosYLikes.ConsultasLikes;
import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasUsuarios;
import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasRevistas;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.modelos.LikeRevista;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ServicioLikes {
    
    private ConsultasLikes consultasLikes;
    private ConsultasRevistas consultasRevistas;
    private ConsultasUsuarios consultasUsuarios;

    public ServicioLikes() {
        this.consultasLikes = new ConsultasLikes();
        this.consultasRevistas = new ConsultasRevistas();
        this.consultasUsuarios = new ConsultasUsuarios();
    }

    public void postearLike(LikeRevista likeRevista) {
        // valida para datos de fecha , nombre usuario y el id de la revista
        if (!likeRevista.esLikeValido()) {
            throw new DatosInvalidosUsuarioException("Datos like invalidos");
        }
        if (!consultasRevistas.existeRevista(likeRevista.getIdRevista())) {
            throw new DatosInvalidosUsuarioException("No existe la revista");
        }
        if (!consultasUsuarios.esUsuarioExistente(likeRevista.getNombreUsuario())) {
            throw new DatosInvalidosUsuarioException("No el usuario");
        }
        consultasLikes.postearLike(likeRevista);
    }

    public void actualizarLike(Long idLike, boolean estado) {
            if (idLike == null && idLike <= 0) {
                throw new DatosInvalidosUsuarioException("invalid DATA");
            }
            LikeRevista rev = new LikeRevista(idLike, estado);
            consultasLikes.actualizarLike(rev);
    }

    public Optional<LikeRevista> obtnerPorUsuario(String nombreUsuario, Long idRevista) {
        if (!consultasUsuarios.esUsuarioExistente(nombreUsuario)) {
            throw new DatosInvalidosUsuarioException("No el usuario");
        }
         if (!consultasRevistas.existeRevista(idRevista)) {
            throw new DatosInvalidosUsuarioException("No existe la revista");
        }
        LikeRevista like = consultasLikes.obtnerPorUsuario(nombreUsuario, idRevista);
        return Optional.ofNullable(like);
    }
}
