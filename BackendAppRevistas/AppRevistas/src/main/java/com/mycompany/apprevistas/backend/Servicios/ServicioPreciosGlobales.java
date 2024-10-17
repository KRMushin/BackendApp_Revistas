/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprevistas.backend.Servicios;

import com.mycompany.apprevistas.backend.ConsultasModelos.ConsultasPreciosGlobales;
import com.mycompany.apprevistas.backend.Excepciones.DatabaseException;
import com.mycompany.apprevistas.backend.Excepciones.DatosInvalidosUsuarioException;
import com.mycompany.apprevistas.backend.modelos.PrecioGlobal;
import com.mycompany.apprevistas.backend.util.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioPreciosGlobales {
    
     private ConsultasPreciosGlobales con;

    public ServicioPreciosGlobales() {
        this.con = new ConsultasPreciosGlobales();
    }
    
    
    public void actualizarPrecioGlobal(Long precioGlobal, Double nuevoPrecio){
        
        if (!esValido(precioGlobal,nuevoPrecio)) {
            throw new DatosInvalidosUsuarioException();
        }
        PrecioGlobal precio = new PrecioGlobal();
        precio.setIdPrecio(precioGlobal);
        precio.setPrecioGlobal(nuevoPrecio);
        con.actualizarPrecioGlobal(precio);
    }
    
    private boolean esValido(Long precioGlobal, Double nuevoPrecio) {
        return precioGlobal != null && precioGlobal > 0 && nuevoPrecio != null && nuevoPrecio > 0;
    }
}
