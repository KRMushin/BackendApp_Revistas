/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios.Reportes;

import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.modelos.Reportes.FiltroEditorDTO;
import com.mycompany.apprevistas.backend.Servicios.Reportes.EditorConsultas;
import com.mycompany.apprevistas.backend.constantes.Filtros.TipoReporteRevista;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConComentarios;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConCompras;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConLikes;
import com.mycompany.apprevistas.backend.modelos.Reportes.RevistaConSuscripciones;
import com.mycompany.apprevistas.backend.util.ConstructorConsultas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioReportesEditor {

    private EditorConsultas consultasEditor;
    private ConstructorConsultas constructorConsultas;

    public ServicioReportesEditor() {
        this.consultasEditor = new EditorConsultas();
        this.constructorConsultas = new ConstructorConsultas();
    }

    public List<RevistaConComentarios> obtnerRevistasConComentarios(FiltroEditorDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        // reconocer que si venga el enum de revista comentarios
        if (filtro.getTipoReporte() == TipoReporteRevista.REVISTA_COMENTARIOS) {
            String consultaConstruida = constructorConsultas.construirConsultaComentarios(filtro, parametros);
            return consultasEditor.obtenerRevistasConComentarios(parametros, consultaConstruida);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public List<RevistaConSuscripciones> obtnerRevistasConSuscripciones(FiltroEditorDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        if (filtro.getTipoReporte() == TipoReporteRevista.REVISTA_SUSCRIPCIONES) {
            String consulta = constructorConsultas.construirConsultaSuscripciones(filtro,parametros);
            return consultasEditor.obtenerRevistasSuscripciones(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public List<RevistaConLikes> obtnerMasGustadas(FiltroEditorDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        if (filtro.getTipoReporte() == TipoReporteRevista.REVISTAS_MAS_GUSTADAS) {
            String consulta = constructorConsultas.construirMasGustadas(filtro,parametros);
            return consultasEditor.obtnerRevistasMasGustadas(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();
    }

    public List<RevistaConCompras> obtnerRevistasConCompras(FiltroEditorDTO filtro) {
        List<Object> parametros = new ArrayList<>();
        if (filtro.getTipoReporte() == TipoReporteRevista.REVISTAS_COSTOS) {
            String consulta = constructorConsultas.construirRevistaCostos(filtro,parametros);
            System.out.println("c" + consulta);
            return consultasEditor.obtnerRevistasCostos(parametros,consulta);
        }
        throw new DatosInvalidosUsuarioException();

    }
    
}
