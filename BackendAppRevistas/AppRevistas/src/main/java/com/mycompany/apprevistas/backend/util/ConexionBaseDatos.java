/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.apprevistas.backend.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;




/**
 *
 * @author kevin-mushin
 */
public class ConexionBaseDatos {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/aplicacion_revistas";
    private static final String USER = "root";
    private static final String PASSWORD = "mushin2022";

    private static ConexionBaseDatos instanciaBaseDatos;

    private DataSource datasource;

    private ConexionBaseDatos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PoolProperties p = new PoolProperties();
            p.setUrl(URL_MYSQL);
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername(USER);
            p.setPassword(PASSWORD);
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(60);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors(
                    "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                    + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            datasource = new DataSource(p);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConexionBaseDatos getInstance() {
        if (instanciaBaseDatos == null) {
            instanciaBaseDatos = new ConexionBaseDatos();
        }

        return instanciaBaseDatos;
    }

    public DataSource getDatasource() {
        return datasource;
    }
    
    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }
    
}
