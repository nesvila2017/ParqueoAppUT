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

    public void createRegistroParqueo(String placaVehiculo, int idLugarParqueo) {
        PreparedStatement ps;
        try {

            ps = connect.getConexion().prepareStatement("INSERT INTO registroparqueo (fechaHoraEntrada, placaVehiculo, LugarParqueo_idLugarParqueo, estadoRegistro) VALUES (NOW(), ?, ?, ?)");
            ps.setString(1, placaVehiculo);
            ps.setInt(2, idLugarParqueo);
            ps.setString(3, "EN PARQUEADERO");
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(RegistroParqueoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizarEstadoRegistro(int idRegistro, String placa, String estado) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("UPDATE RegistroParqueo SET estadoRegistro = ?  WHERE idRegistroParqueo = ? and placaVehiculo=?");
            ps.setString(1, estado);
            ps.setInt(2, idRegistro);
            ps.setString(3, placa);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error actualizar Tarifa: " + ex);
        }
    }
    
    public void eliminarRegistroParqueo(int idRegistro) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("DELETE FROM RegistroParqueo WHERE idRegistroParqueo= ?");
            ps.setInt(1, idRegistro);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al eliminar registro de parqueo: " + ex);
        }
    }

    public RegistroParqueo mostrarRegistroParqueo(String placa, Timestamp fechaEntrada) {
        //fechaHoraEntrada, placaVehiculo, idLugarParqueo, idTipoLugar
        PreparedStatement ps;
        ResultSet rs;
        RegistroParqueo rp = new RegistroParqueo();
        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM registroparqueo where fechaHoraEntrada =? and placaVehiculo = ?");
            ps.setTimestamp(1, fechaEntrada);
            ps.setString(2, placa);
            rs = ps.executeQuery();
            rs.next();
            rp.setIdRegistroParqueo(rs.getInt(1));
            rp.setFechaHoraEntrada(rs.getTimestamp(2));
            rp.setPlacaVehiculo(rs.getString(3));
            rp.setIdLugarParqueo(rs.getInt(4));
            rp.setEstadoRegistro(rs.getString(5));
            ps.close();
            return rp;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar registros de parqueo segun fecha de entrada: " + ex);
        }

        return null;

    }

     public boolean buscarRegistroParqueoEstado(String placa) {
        //fechaHoraEntrada, placaVehiculo, idLugarParqueo, idTipoLugar
        PreparedStatement ps;
        ResultSet rs;
        RegistroParqueo rp = new RegistroParqueo();
        try {
            ps = connect.getConexion().prepareStatement("select count(*) from registroparqueo where placaVehiculo = ? and estadoRegistro ='EN PARQUEADERO'");
            ps.setString(1, placa);
            rs = ps.executeQuery();
            rs.next();
            int numRegEstado = rs.getInt(1);
            ps.close();
            return numRegEstado==0;
        } catch (SQLException ex) {
            System.out.println("Error al Mostrar registro de parqueo por buscarRegistroParqueoEstado: " + ex);
        }
        return false;
    }
    
    public RegistroParqueo buscarRegistroParqueoOcupado(String placa, Timestamp fechaEntrada) {
        //fechaHoraEntrada, placaVehiculo, idLugarParqueo, idTipoLugar
        PreparedStatement ps;
        ResultSet rs;
        RegistroParqueo rp = new RegistroParqueo();
        try {
            ps = connect.getConexion().prepareStatement("select * from registroparqueo inner join lugarparqueo on LugarParqueo_idLugarParqueo = idLugarParqueo where disponibilidad = ? and fechaHoraEntrada like '% ? %';");
            ps.setTimestamp(1, fechaEntrada);
            ps.setString(2, placa);
            rs = ps.executeQuery();
            rp.setIdRegistroParqueo(rs.getInt(1));
            rp.setFechaHoraEntrada(rs.getTimestamp(2));
            rp.setPlacaVehiculo(rs.getString(3));
            rp.setIdLugarParqueo(rs.getInt(4));
            rp.setEstadoRegistro(rs.getString(5));
            ps.close();
            return rp;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar registro de parqueo por buscarRegistroParqueoOcupado: " + ex);
        }

        return null;

    }

    public List<RegistroParqueo> mostrarRegistroParqueo() {
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
                rp.setEstadoRegistro(rs.getString(5));
                listaRegistroParqueo.add(rp);

            }
            ps.close();

            return listaRegistroParqueo;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar registro total de parqueo: " + ex);
        }

        return listaRegistroParqueo;

    }

}
