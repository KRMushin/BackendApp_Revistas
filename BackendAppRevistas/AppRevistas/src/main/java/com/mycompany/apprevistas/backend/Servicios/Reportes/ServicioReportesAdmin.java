/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Reportes;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.constantes.Filtros.TipoReporteAnuncio;
import com.mycompany.apprevistas.backend.modelos.Anuncio;
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
    
    private AdminConsultas aC;
    private ConstructorConsultasAdmin co;

    public ServicioReportesAdmin() {
        this.aC = new AdminConsultas();
        this.co = new ConstructorConsultasAdmin();
    }

    public List<Anuncio> obtenerAnunciosComprados(FiltrosAdminDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        
        if (filtro.getTipoReporte() == TipoReporteAnuncio.ANUNCIOS_COMPRADOS) {
            String consulta = co.construirConsultaAnuncios(parametros, filtro);
            return aC.obtnerReporteComprados(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public List<Anuncio> obtenerGananciasAnuncios(FiltrosAdminDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        
        if (filtro.getTipoReporte() == TipoReporteAnuncio.GANANCIAS_ANUNCIANTES) {
            String consulta = co.construirConsultaAnuncios(parametros, filtro);
            return aC.obtnerReporteAnunciantes(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public List<RevistaConSuscripciones> obtnerMasPopulares(FiltrosAdminDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        
        if (filtro.getTipoReporte() == TipoReporteAnuncio.REVISTAS_MAS_POPULARES) {
            String consulta = co.construirRevistasPopulares(parametros, filtro);
            System.out.println(consulta);
            return aC.obtnerRevistasMasPopulares(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public List<RevistaConComentarios> obtnerMasComentadas(FiltrosAdminDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        
        if (filtro.getTipoReporte() == TipoReporteAnuncio.REVISTAS_MAS_COMENTADAS) {
            String consulta = co.construirConsultaComentarios(parametros, filtro);
            System.out.println(consulta);
            return aC.obtnerRevistasMasComentadas(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    
}
