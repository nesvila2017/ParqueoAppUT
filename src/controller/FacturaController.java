/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexionParqueo.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author GIGABYTE
 */
public class FacturaController {

    public FacturaController() {
    
    }

    private Conexion connect = new Conexion();
    
    
    public void Nuevanota(String estudiante, String materia, double nota1,double nota2,double nota3){
        
        try {
            PreparedStatement pstm =  connect.getConexion().prepareStatement("insert into " +
            "notas(estudiante, materia, nota1, nota2, nota3) " +
            " values(?,?,?,?,?)");
            pstm.setString(1, estudiante);
            pstm.setString(2, materia);
            pstm.setDouble(3, nota1);
            pstm.setDouble(4, nota2);
            pstm.setDouble(5, nota3);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
}
