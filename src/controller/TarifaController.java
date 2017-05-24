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

    public void insertTarifa(int idTipo, double precioFrac, int tipoVehiculo) {

        PreparedStatement ps;
        try {
            ps = connector.getConexion().prepareStatement("INSERT INTO Tarifa (idTarifa, precioFraccion, TipoVehiculo_idTipoVehiculo) values (?,?,(SELECT idTipoVehiculo FROM tipoVehiculo WHERE idTipoVehiculo = ?))");
            ps.setInt(1, idTipo);
            ps.setDouble(2, precioFrac);
            ps.setInt(3, tipoVehiculo);
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error insertar tarifa: " + ex);
        } finally {
            connector.cerraConexion();
        }
    }

    public void actualizarTarifa(int idTarifa, double precioFrac, int tipoVehic) {
        PreparedStatement ps;
        try {
            ps = connector.getConexion().prepareStatement("UPDATE Tarifa SET precioFraccion =?  WHERE idTarifa = ? and TipoVehiculo_idTipoVehiculo=?");
            ps.setDouble(1, precioFrac);
            ps.setInt(2, idTarifa);
            ps.setInt(3, tipoVehic);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error actualizar Tarifa: " + ex);

        } finally {
            connector.cerraConexion();
        }
    }

    public void eliminarTarifa(int idTipo) {
        PreparedStatement ps;

        try {
            ps = connector.getConexion().prepareStatement("DELETE FROM tarifa WHERE idTarifa =?");
            ps.setInt(1, idTipo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo Vehiculo: " + e);
        } finally {
            connector.cerraConexion();
        }

    }

    public List<Tarifa> mostrarTarifas() {

        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connector.getConexion().prepareStatement("SELECT * FROM persona");
            rs = ps.executeQuery();
            ArrayList<Tarifa> fraccionLista = new ArrayList<>();
            while (rs.next()) {
                Tarifa tar = new Tarifa();
                tar.setIdTarifa(rs.getInt(1));
                tar.setPrecioFraccion(rs.getDouble(2));
                tar.setIdTipoVehiculo(rs.getInt(3));
                fraccionLista.add(tar);

            }
            return fraccionLista;

        } catch (SQLException ex) {
            System.out.println("Error al mostrar tarifas: " + ex);
            return null;
        } finally {
            connector.cerraConexion();
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
                //tipo.setTarifa(rs.getString(2));
                return tipo;
            }

        } catch (SQLException ex) {
            System.out.println("Error al mostrar Tarifa por id: " + ex);
            return null;
        } finally {
            connector.cerraConexion();
        }
        return tipo;

    }

}
