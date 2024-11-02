/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones.Revistas;

import com.mycompany.apprevistas.backend.Repositorios.RepositorioLlaveEntidad;
import com.mycompany.apprevistas.backend.RevistasDTOs.Configs.EstadoRevistaDTO;
import com.mycompany.apprevistas.backend.RevistasDTOs.LlaveRevistaDTO;
import com.mycompany.apprevistas.backend.modelos.Filtros.FiltroNavegacionRevistas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kevin-mushin
 */
//implements RepositorioLlaveEntidad<EstadoRevistaDTO, Long>
public class RepositorioNavegacionRevistas {
    
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public RepositorioNavegacionRevistas() {
    }
    
    public List<EstadoRevistaDTO> ListarEstadosRevistaActiva() throws SQLException{
        List<EstadoRevistaDTO> revistasActivas = new ArrayList<>();
        String selectQuery = "SELECT c.*, r.titulo_revista, r.descripcion FROM configuracion_revistas c JOIN revistas r ON c.id_revista = r.id_revista WHERE r.estado_revista = 'ACTIVA' ";

        try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
               ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                revistasActivas.add(crearEstadosActivos(rs)); // Añades cada revista a la lista
            }
        }
        return revistasActivas;
    
    }
    
    private EstadoRevistaDTO crearEstadosActivos(ResultSet rs) throws SQLException {
        EstadoRevistaDTO s = new EstadoRevistaDTO();
        s.setAceptaSuscripciones(rs.getBoolean("acepta_suscripciones"));
        s.setAnunciosBloqueados(rs.getBoolean("anuncios_bloqueados"));
        s.setEsLikeable(rs.getBoolean("revista_likeable"));
        s.setEsComentable(rs.getBoolean("revista_comentable"));
        s.setIdRevista(rs.getLong("id_revista"));
        s.setTituloRevista("titulo_revista");
        s.setDescripcion(rs.getString("descripcion"));
        return s;
        
    }


    public EstadoRevistaDTO listarPorCategorias(Long id) throws SQLException {
        
        String insertQuery = "SELECT c.*, r.titulo_revista, r.descripcion FROM configuracion_revistas c JOIN revistas r ON "
                + "                        c.id_revista = r.id_revista WHERE r.estado_revista = 'ACTIVA' AND r.id_categoria = ?";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setLong(1, id);
               ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return crearEstadosActivos(rs);
            }
        }
        return null;
    }

    public List<EstadoRevistaDTO> listarPorEtiquetas(List<Long> etiquetas) throws SQLException {
    List<EstadoRevistaDTO> revistas = new ArrayList<>();

    // Construye la consulta con la cantidad correcta de placeholders "?" para cada ID
    String consultaConstruida = construirConsultaEtiquetas(etiquetas);
    String insertQuery = "SELECT c.*, r.titulo_revista, r.descripcion " +
                         "FROM configuracion_revistas c " +
                         "JOIN revistas r ON c.id_revista = r.id_revista " +
                         "JOIN revista_etiqueta re ON r.id_revista = re.id_revista " +
                         "WHERE r.estado_revista = 'ACTIVA' " +
                         "AND re.id_etiqueta IN (" + consultaConstruida + ");";
    
    try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
        for (int i = 0; i < etiquetas.size(); i++) {
            stmt.setLong(i + 1, etiquetas.get(i));
        }
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            revistas.add(crearEstadosActivos(rs));
        }
    }
    return revistas;
}

    
    public List<EstadoRevistaDTO> listarPorEtiquetasYCategoria(List<Long> etiquetas, Long idCategoria) throws SQLException {
    List<EstadoRevistaDTO> revistas = new ArrayList<>();
    
    // Construimos placeholders para cada ID en la lista de etiquetas
    String consultaPlaceholders = construirConsultaEtiquetas(etiquetas);
    String insertQuery = "SELECT c.*, r.titulo_revista, r.descripcion " +
                         "FROM configuracion_revistas c " +
                         "JOIN revistas r ON c.id_revista = r.id_revista " +
                         "JOIN revista_etiqueta re ON r.id_revista = re.id_revista " +
                         "WHERE r.estado_revista = 'ACTIVA' " +
                         "AND re.id_etiqueta IN (" + consultaPlaceholders + ") " +
                         "AND r.id_categoria = ?;";  // filtro adicional por id_categoria
    
    try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
        // Asignamos los IDs de las etiquetas a los parámetros del PreparedStatement
        for (int i = 0; i < etiquetas.size(); i++) {
            stmt.setLong(i + 1, etiquetas.get(i));
        }
        // Asignamos el id_categoria como último parámetro
        stmt.setLong(etiquetas.size() + 1, idCategoria);
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            revistas.add(crearEstadosActivos(rs));
        }
    } catch (SQLException e) {
        System.err.println("Error al ejecutar la consulta SQL: " + e.getMessage());
        System.err.println("Consulta generada: " + insertQuery);
        throw e;
    }
    
    return revistas;
}

    
    private String construirConsultaEtiquetas(List<Long> etiquetas) {
            return etiquetas.stream().map(id -> "?").collect(Collectors.joining(", "));
}
}
