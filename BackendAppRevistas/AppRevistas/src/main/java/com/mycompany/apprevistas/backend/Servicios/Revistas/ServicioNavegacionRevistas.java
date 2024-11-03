/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Revistas;

import com.mycompany.apprevistas.backend.ConsultasModelos.Revistas.ConsultasPorFiltro;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoRevistaDTO;
import com.mycompany.apprevistas.backend.constantes.Filtros.TipoDeFiltro;
import com.mycompany.apprevistas.backend.modelos.Filtros.FiltroNavegacionRevistas;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ServicioNavegacionRevistas {
    
    private ConsultasPorFiltro consultas;

    public ServicioNavegacionRevistas() {
        this.consultas = new ConsultasPorFiltro();
    }
    
    public List<EstadoRevistaDTO> navegarPorRevistas(FiltroNavegacionRevistas filtro){
        
         TipoDeFiltro tipo = filtro.getTipoFiltro();
        switch (tipo) {
            case REVISTAS_ACTIVAS:
                return consultas.obtnerTodasActivas(filtro.getNombreUsuario());
            case REVISTAS_POR_CATEGORIA:
                return consultas.obtnerPorCategorias(filtro);
            case REVISTAS_POR_ETIQUETAS:
                return consultas.obtnerPorEtiquetas(filtro);
            case REVISTAS_POR_CATEGORIA_ETIQUETA:
                return consultas.obtnerPorCategoriaYEtiquetas(filtro);
            default:
                        return null;
        }
    }
    
}
