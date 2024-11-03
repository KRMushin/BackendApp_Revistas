/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.modelos.Filtros;

import com.mycompany.apprevistas.backend.constantes.Filtros.TipoDeFiltro;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class FiltroNavegacionRevistas {
    
    private Long idRevista;
    private TipoDeFiltro tipoFiltro;
    private List<Long> valoresFiltros;
    private Long idCategoria;  // para cuando se filtre por categoria etiqueta
    private String nombreUsuario;
    

    public FiltroNavegacionRevistas() {
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public TipoDeFiltro getTipoFiltro() {
        return tipoFiltro;
    }

    public void setTipoFiltro(TipoDeFiltro tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

    public List<Long> getValoresFiltros() {
        return valoresFiltros;
    }

    public void setValoresFiltros(List<Long> valoresFiltros) {
        this.valoresFiltros = valoresFiltros;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    
}
