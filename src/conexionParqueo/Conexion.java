/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionParqueo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author GIGABYTE
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://127.0.0.3:3306/Parqueo";
    private static final String USER = "root";
    private static final String PASS = "0000";
    private Connection conector;

    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conector = DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException error) {
            System.out.println("Error de conexion" + error);
        }
    }

    public Connection getConexion() {
        if (conector != null) {
            return conector;
        } else {
            return null;
        }

    }

    public void cerraConexion() {
        conector = null;
    }

}
