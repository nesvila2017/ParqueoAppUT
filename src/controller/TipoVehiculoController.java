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
import java.util.ArrayList;
import java.util.List;
import model.TipoVehiculo;

/**
 *
 * @author GIGABYTE
 */
public class TipoVehiculoController {

    private final Conexion connector;

    public TipoVehiculoController() {
        this.connector = new Conexion();
    }

    public void insertTipoVehiculo(String tipoVeh) {

        PreparedStatement ps;
        try {
            ps = connector.getConexion().prepareStatement("INSERT INTO TipoVehiculo (tipoVehic) values (?)");
            ps.setString(1, tipoVeh);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error insertar tipo vehiculo: " + ex);
        }
    }

    public void actualizarTipoVehiculo(int idTipo, String tipoVehiculo) {
        PreparedStatement ps;
        try {
            ps = connector.getConexion().prepareStatement("UPDATE TipoVehiculo SET tipoVehic =? WHERE idTipoVehiculo = ?");
            ps.setString(1, tipoVehiculo);
            ps.setInt(2, idTipo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error actualizar tipo vehiculo: " + ex);

        }
    }

    public void eliminarTipoVehiculo(int idTipo) {
        PreparedStatement ps;

        try {
            ps = connector.getConexion().prepareStatement("DELETE FROM tipoVehiculo WHERE idTipoVehiculo =?");
            ps.setInt(1, idTipo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo Vehiculo: " + e);
        }

    }

    public List<TipoVehiculo> mostrarTipolugar() {

        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connector.getConexion().prepareStatement("SELECT * FROM TipoVehiculo");
            rs = ps.executeQuery();
            ArrayList<TipoVehiculo> tipoVehiculo = new ArrayList<>();
            while (rs.next()) {
                TipoVehiculo tipo = new TipoVehiculo();
                tipo.setIdTipoVehiculo(rs.getInt(1));
                tipo.setTipoVehiculo(rs.getString(2));
                tipoVehiculo.add(tipo);

            }
            return tipoVehiculo;

        } catch (SQLException ex) {
            System.out.println("Error al mostra los tipos de vehiculo: " + ex);
            return null;
        }

    }

    public TipoVehiculo mostrarTipoVehiculo(int idTipo) {
        TipoVehiculo tipo = new TipoVehiculo();
        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connector.getConexion().prepareStatement("SELECT * FROM tipoVehiculo WHERE idTipoVehiculo = ?");
            ps.setInt(1, idTipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                tipo.setIdTipoVehiculo(rs.getInt(1));
                tipo.setTipoVehiculo(rs.getString(2));
                return tipo;
            }

        } catch (SQLException ex) {
            System.out.println("Error al mostrar TipoVehiculo por id: " + ex);
            return null;
        }
        return tipo;

    }

    public List<String> mostrarTiposVehiculos() {
        ArrayList<String> listTipVeh = new ArrayList<>();
        ResultSet rs;
        String tip;
        try {
            PreparedStatement ps;
            ps = connector.getConexion().prepareStatement("SELECT TipoVehic FROM tipoVehiculo");
            rs = ps.executeQuery();
            while (rs.next()) {
                tip = (rs.getString(1));
                listTipVeh.add(tip);
            }
            return listTipVeh;

        } catch (SQLException ex) {
            System.out.println("Error al mostrar TipoVehiculo por id: " + ex);
            return null;
        }

    }

}
