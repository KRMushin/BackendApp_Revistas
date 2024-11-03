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
    
    public List<EstadoRevistaDTO> ListarEstadosRevistaActiva(String nombreUsuario) throws SQLException{
        List<EstadoRevistaDTO> revistasActivas = new ArrayList<>();
        String selectQuery = "SELECT c.*, r.titulo_revista, r.descripcion FROM configuracion_revistas c JOIN revistas r ON c.id_revista = r.id_revista "
                                        + "LEFT JOIN suscripciones s ON r.id_revista = s.id_revista AND s.nombre_usuario = ? "
                                        + "WHERE r.estado_revista = 'ACTIVA' AND s.id_revista IS NULL; ";
//        String selectQuery = "SELECT c.*, r.titulo_revista, r.descripcion FROM configuracion_revistas c JOIN revistas r ON c.id_revista = r.id_revista WHERE r.estado_revista = 'ACTIVA' ";

        try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
               stmt.setString(1, nombreUsuario);
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
        s.setTituloRevista(rs.getString("titulo_revista"));
        s.setDescripcion(rs.getString("descripcion"));
        return s;
        
    }


    public EstadoRevistaDTO listarPorCategorias(Long id, String nombreUsuario) throws SQLException {
        
          String insertQuery = "SELECT c.*, r.titulo_revista, r.descripcion FROM configuracion_revistas c JOIN revistas r ON c.id_revista = r.id_revista "
                                          + "LEFT JOIN suscripciones s ON r.id_revista = s.id_revista AND s.nombre_usuario = ? "
                                          + "WHERE r.estado_revista = 'ACTIVA' AND r.id_categoria = ? AND s.id_revista IS NULL;";
          
//        String insertQuery = "SELECT c.*, r.titulo_revista, r.descripcion FROM configuracion_revistas c JOIN revistas r ON "
//                + "                        c.id_revista = r.id_revista WHERE r.estado_revista = 'ACTIVA' AND r.id_categoria = ?";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, nombreUsuario);
                stmt.setLong(2, id);
               ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return crearEstadosActivos(rs);
            }
        }
        return null;
    }

    public List<EstadoRevistaDTO> listarPorEtiquetas(List<Long> etiquetas, String nombreUsuario) throws SQLException {
    List<EstadoRevistaDTO> revistas = new ArrayList<>();

    // Construye la consulta con la cantidad correcta de placeholders "?" para cada ID
    String consultaConstruida = construirConsultaEtiquetas(etiquetas);
    String insertQuery = "SELECT c.*, r.titulo_revista, r.descripcion " +
                         "FROM configuracion_revistas c " +
                         "JOIN revistas r ON c.id_revista = r.id_revista " +
                         "JOIN revista_etiqueta re ON r.id_revista = re.id_revista " +
                         "LEFT JOIN suscripciones s ON r.id_revista = s.id_revista AND s.nombre_usuario = ? " +
                         "WHERE r.estado_revista = 'ACTIVA' " +
                         "AND s.id_revista IS NULL " +
                         "AND re.id_etiqueta IN (" + consultaConstruida + ");";

    try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
        // Asigna el nombre de usuario como el primer parámetro
        stmt.setString(1, nombreUsuario);
        
        // Asigna cada uno de los valores de las etiquetas
        for (int i = 0; i < etiquetas.size(); i++) {
            stmt.setLong(i + 2, etiquetas.get(i)); // Comienza en el índice 2 después del usuario
        }
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            revistas.add(crearEstadosActivos(rs));
        }
    }
    return revistas;
}


    
    public List<EstadoRevistaDTO> listarPorEtiquetasYCategoria(List<Long> etiquetas, Long idCategoria, String nombreUsuario) throws SQLException {
            List<EstadoRevistaDTO> revistas = new ArrayList<>();
          
            String consultaConstruida = construirConsultaEtiquetas(etiquetas);
            String insertQuery = "SELECT c.*, r.titulo_revista, r.descripcion " +
                                 "FROM configuracion_revistas c " +
                                 "JOIN revistas r ON c.id_revista = r.id_revista " +
                                 "JOIN revista_etiqueta re ON r.id_revista = re.id_revista " +
                                 "LEFT JOIN suscripciones s ON r.id_revista = s.id_revista AND s.nombre_usuario = ? " +
                                 "WHERE r.estado_revista = 'ACTIVA' " +
                                 "AND re.id_etiqueta IN (" + consultaConstruida + ") " +
                                 "AND s.id_revista IS NULL " +
                                 "AND r.id_categoria = ?;"; 

            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                   stmt.setString(1, nombreUsuario);

                    for (int i = 0; i < etiquetas.size(); i++) {
                        stmt.setLong(i + 2, etiquetas.get(i));
                    }
                    stmt.setLong(etiquetas.size() + 2, idCategoria);

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
