/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Reportes;

import com.mycompany.apprevistas.backend.AnunciosDTOs.AnuncioVisualizacionDTO;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.constantes.Filtros.TipoReporteAnuncio;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
import com.mycompany.apprevistas.backend.modelos.Reportes.AnuncioConVisualizaciones;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltrosAdminDTO;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConComentarios;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConSuscripciones;
import com.mycompany.apprevistas.backend.util.ConstructorConsultasAdmin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioReportesAdmin {
    
    private AdminConsultas adminConsultas;
    private ConstructorConsultasAdmin constructorConsultas;

    public ServicioReportesAdmin() {
        this.adminConsultas = new AdminConsultas();
        this.constructorConsultas = new ConstructorConsultasAdmin();
    }

    public List<Anuncio> obtenerAnunciosComprados(FiltrosAdminDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        
        if (filtro.getTipoReporte() == TipoReporteAnuncio.ANUNCIOS_COMPRADOS) {
            String consulta = constructorConsultas.construirConsultaAnuncios(parametros, filtro);
            return adminConsultas.obtnerReporteComprados(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public List<Anuncio> obtenerGananciasAnuncios(FiltrosAdminDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        
        if (filtro.getTipoReporte() == TipoReporteAnuncio.GANANCIAS_ANUNCIANTES) {
            String consulta = constructorConsultas.construirConsultaAnuncios(parametros, filtro);
            return adminConsultas.obtnerReporteAnunciantes(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public List<RevistaConSuscripciones> obtnerMasPopulares(FiltrosAdminDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        
        if (filtro.getTipoReporte() == TipoReporteAnuncio.REVISTAS_MAS_POPULARES) {
            String consulta = constructorConsultas.construirRevistasPopulares(parametros, filtro);
            return adminConsultas.obtnerRevistasMasPopulares(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public List<RevistaConComentarios> obtnerMasComentadas(FiltrosAdminDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        
        if (filtro.getTipoReporte() == TipoReporteAnuncio.REVISTAS_MAS_COMENTADAS) {
            String consulta = constructorConsultas.construirConsultaComentarios(parametros, filtro);
            return adminConsultas.obtnerRevistasMasComentadas(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public void alamcenarVisualizacion(AnuncioVisualizacionDTO dto) {
        if (dto.getRuta() == null || dto.getIdAnuncio() == null) {
            return;
        }
         adminConsultas.registrarVisualizacion(dto.getRuta(), dto.getIdAnuncio());
    }

    public List<AnuncioConVisualizaciones> efectividadAnuncioss(FiltrosAdminDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        
        if (filtro.getTipoReporte() == TipoReporteAnuncio.EFECTIVIDAD_ANUNCIOS) {
            String consulta = constructorConsultas.consultEfectividad(parametros, filtro);
            return adminConsultas.obtnerEfectividadAnuncios(parametros, consulta);
        }
        throw new DatosInvalidosUsuarioException();

    }

    
}
