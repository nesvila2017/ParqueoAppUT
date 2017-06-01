/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexionParqueo.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RegistroParqueo;

/**
 *
 * @author GIGABYTE
 */
public class RegistroParqueoController {

    private final Conexion connect;

    public RegistroParqueoController() {
        this.connect = new Conexion();
    }
    
    
    private void createRegistroParqueo(int idRegistro, String placaVehiculo, int idLugarParqueo ){
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("INSERT INTO registroparqueo (idRegistroParqueo, fechaHoraEntrada, placaVehiculo, idLugarParqueo) VALUES (? , NOW(), ?, ?)");
            ps.setInt(1, idRegistro);
            ps.setString(2, placaVehiculo);
            ps.setInt(3, idLugarParqueo);
            ps.execute();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistroParqueoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void eliminarRegistroParqueo(int idRegistro) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("DELETE FROM RegistroParqueo WHERE idRegistroParqueo= ?");
            ps.setInt(1, idRegistro);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar lugar de parqueo" + ex);
        }
    }

    private RegistroParqueo mostrarRegistroParqueo(String placa, Timestamp fechaEntrada) {
        //fechaHoraEntrada, placaVehiculo, idLugarParqueo, idTipoLugar
        PreparedStatement ps;
        ResultSet rs;
        RegistroParqueo rp = new RegistroParqueo();
        try {
            ps = connect.getConexion().prepareStatement("SELECT idRegistroParqueo, fechaHoraEntrada, placaVehiculo, idLugarParqueo FROM registroParqueo WHERE fechaHoraEntrada = ? and PlacaVehiculo = ?");
            ps.setTimestamp(1, fechaEntrada);
            ps.setString(2, placa);
            rs = ps.executeQuery();
            rp.setIdRegistroParqueo(rs.getInt(1));
            rp.setFechaHoraEntrada(rs.getTimestamp(2));
            rp.setPlacaVehiculo(rs.getString(3));
            rp.setIdLugarParqueo(rs.getInt(4));
            
            return rp;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar lugar de parqueo: " + ex);
        }

        return null;

    }

    private List<RegistroParqueo> mostrarRegistroParqueo() {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<RegistroParqueo> listaRegistroParqueo = new ArrayList<>();
        
        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM RegistroParqueo");
            rs = ps.executeQuery();
            while (rs.next()) {
                RegistroParqueo rp = new RegistroParqueo();
                rp.setIdRegistroParqueo(rs.getInt(1));
                rp.setFechaHoraEntrada(rs.getTimestamp(2));
                rp.setPlacaVehiculo(rs.getString(3));
                rp.setIdLugarParqueo(rs.getInt(4));
                listaRegistroParqueo.add(rp);

            }

            return listaRegistroParqueo;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar lugar de parqueo: " + ex);
        }

        return listaRegistroParqueo;

    }
    

    
    
}
