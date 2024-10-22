/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Repositorios.Implementaciones;

import com.mycompany.apprevistas.backend.Excepciones.NotFoundException;
import com.mycompany.apprevistas.backend.usuariosDTOs.CredencialUsuario;
import com.mycompany.apprevistas.backend.usuariosDTOs.LoginDTO;
import com.mycompany.apprevistas.backend.Repositorios.RepositorioEscrituraLectura;
import com.mycompany.apprevistas.backend.Repositorios.RepositorioLlaveEntidad;
import com.mycompany.apprevistas.backend.modelos.Usuario;
import com.mycompany.apprevistas.backend.usuariosDTOs.LlaveUsuarioDTO;
import com.mycompany.apprevistas.backend.constantes.RolUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioUsuarios implements RepositorioEscrituraLectura<Usuario,String>, RepositorioLlaveEntidad<LlaveUsuarioDTO,String> {

    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Usuario guardar(Usuario modelo) throws SQLException {
        String insertQuery = " INSERT INTO usuarios(nombre_usuario, password_usuario, rol_usuario, nombre_pila, descripcion_usuario) "
        + "values(?,?,?,?,?)";
         /* devuelve el valor de llave primario generado ya que es autoincrementable*/
         try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
             
                 stmt.setString(1, modelo.getNombreUsuario());
                 stmt.setString(2, modelo.getPassword());
                 stmt.setString(3, modelo.getRolUsuario().toString());
                 stmt.setString(4, modelo.getNombrePila());
                 stmt.setString(5, modelo.getDescripcion());
             
                 int filasAfectadas = stmt.executeUpdate();
                 if (filasAfectadas <= 0) {
                    throw   new  SQLException("Verifique los datos del usuario, No se inserto en el sistema");
                 }
         }
        return modelo;
    }
    @Override
    public Usuario actualizar(Usuario modelo) throws SQLException {        
        String updateQuery = "UPDATE usuarios SET nombre_pila = ?, descripcion_usuario = ?  WHERE nombre_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)){
            
            stmt.setString(1, modelo.getNombrePila());
            stmt.setString(2, modelo.getDescripcion());
            stmt.setString(3, modelo.getNombreUsuario());

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected <= 0) {
                throw new SQLException("Verifique los datos de actualizacion , algunos estan en formato equivocado");
            }
        }
        return modelo;
    }

    @Override
    public Usuario obtenerPorId(String identificador) throws SQLException {
         String insertQuery = "SELECT *FROM usuarios WHERE nombre_usuario = ?";
         
             try (PreparedStatement stmt = conn.prepareStatement(insertQuery)){
                   stmt.setString(1, identificador);

                   ResultSet rs = stmt.executeQuery();

                   if (rs.next()) {
                      return crearUsuario(rs);
                  }
                  return null; 
             }
    }

    private Usuario crearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        
         usuario.setNombreUsuario(rs.getString("nombre_usuario"));
         usuario.setPassword(rs.getString("password_usuario"));
         usuario.setRolUsuario(RolUsuario.valueOf(rs.getString("rol_usuario")));
         usuario.setNombrePila(rs.getString("nombre_pila"));
         String descripcion = rs.getString("descripcion_usuario");
         if (descripcion != null) {
             usuario.setDescripcion(descripcion);
         }
        return usuario;
    }

    @Override
    public Optional<LlaveUsuarioDTO> obtenerLlaveEntidad(String nombreUsuario) throws SQLException {

        String insertQuery = "SELECT password_usuario, nombre_usuario, rol_usuario FROM usuarios WHERE nombre_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             stmt.setString(1, nombreUsuario);
             ResultSet rs = stmt.executeQuery();
             if (rs.next()) {
                 LlaveUsuarioDTO cu = new LlaveUsuarioDTO();
                 cu.setNombreUsuario(rs.getString("nombre_usuario"));
                 cu.setPassword(rs.getString("password_usuario"));
                 String rol = rs.getString("rol_usuario");
                System.out.println("Valor de rol_usuario: " + rol);
                cu.setRolUsuario(RolUsuario.valueOf(rol));

                 return Optional.of(cu);
             }else{
                 throw new NotFoundException();
             }
        }
    }
}
