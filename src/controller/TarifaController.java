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
import model.Tarifa;

/**
 *
 * @author GIGABYTE
 */
public class TarifaController {

    private final Conexion connector;

    public TarifaController() {
        this.connector = new Conexion();
    }

    public int conteoTarifas() {
        int conteo = 0;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connector.getConexion().prepareStatement("SELECT COUNT(*) FROM tarifa");
            rs = ps.executeQuery();
            rs.next();
            conteo = rs.getInt(1);
            ps.close();
            return conteo;

        } catch (SQLException e) {
            System.out.println("Error en conteo de tarifas: " + e);
        }
        return conteo;

    }

    public void insertTarifa(String nomTarif, double precioFrac, int tipoVehiculo) {

        PreparedStatement ps;
        try {
            ps = connector.getConexion().prepareStatement("INSERT INTO Tarifa (nombreTarifa, precioFraccion, TipoVehiculo_idTipoVehiculo) values (?,?,(SELECT idTipoVehiculo FROM tipoVehiculo WHERE idTipoVehiculo = ?))");
            ps.setString(1, nomTarif);
            ps.setDouble(2, precioFrac);
            ps.setInt(3, tipoVehiculo);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error insertar tarifa: " + ex);

        }
    }

    public void actualizarTarifa(int idTarifa, String nomTari, double precioFrac, int tipoVehic) {
        PreparedStatement ps;
        try {
            ps = connector.getConexion().prepareStatement("UPDATE Tarifa SET precioFraccion =? SET nombreTarifa =? WHERE idTarifa = ? and TipoVehiculo_idTipoVehiculo=?");
            ps.setDouble(1, precioFrac);
            ps.setString(2, nomTari);
            ps.setInt(3, idTarifa);
            ps.setInt(4, tipoVehic);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error actualizar Tarifa: " + ex);

        }
    }

    public void eliminarTarifa(int idTipo) {
        PreparedStatement ps;

        try {
            ps = connector.getConexion().prepareStatement("DELETE FROM tarifa WHERE idTarifa =?");
            ps.setInt(1, idTipo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo Vehiculo: " + e);
        }

    }

    public List<Tarifa> mostrarTarifas() {

        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connector.getConexion().prepareStatement("SELECT idTarifa, nombreTarifa, precioFraccion, tv.TipoVehic  FROM parqueo.tarifa as t inner join tipoVehiculo as tv on t.TipoVehiculo_idTipoVehiculo=tv.idTipoVehiculo ;");
            rs = ps.executeQuery();
            ArrayList<Tarifa> fraccionLista = new ArrayList<>();
            while (rs.next()) {
                Tarifa tar = new Tarifa();
                tar.setIdTarifa(rs.getInt(1));
                tar.setNomTarifa(rs.getString(2));
                tar.setPrecioFraccion(rs.getDouble(3));
                tar.setTipoVehic(rs.getString(4));
                fraccionLista.add(tar);

            }
            ps.close();
            return fraccionLista;

        } catch (SQLException ex) {
            System.out.println("Error al mostrar tarifas: " + ex);
            return null;
        }

    }

    public Tarifa mostrarTarifa(int idTipo) {
        Tarifa tipo = new Tarifa();
        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connector.getConexion().prepareStatement("SELECT * FROM tipoVehiculo WHERE idTarifa = ?");
            ps.setInt(1, idTipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                tipo.setIdTarifa(rs.getInt(1));
            }
            ps.close();
            return tipo;
        } catch (SQLException ex) {
            System.out.println("Error al mostrar Tarifa por id: " + ex);
            return null;
        }

    }

}
