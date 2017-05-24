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

    public void insertTipoVehiculo(int idTipo, String tipoVeh) {

        PreparedStatement ps;
        try {
            ps = connector.getConexion().prepareStatement("INSERT INTO TipoVehiculo (idTipoVehiculo, tipoVehic) values (?,?)");
            ps.setInt(1, idTipo);
            ps.setString(2, tipoVeh);
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error insertar tipo vehiculo: " + ex);
        } finally {
            connector.cerraConexion();
        }
    }

    public void actualizarTipoVehiculo(int numIdent, String tipoVehiculo) {
        PreparedStatement ps;
        try {
            ps = connector.getConexion().prepareStatement("UPDATE TipoVehiculo SET tipoVehic WHERE idTipoVehiculo = ?");
            ps.setString(1, tipoVehiculo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error actualizar tipo vehiculo: " + ex);

        } finally{
            connector.cerraConexion();
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
        } finally {
            connector.cerraConexion();
        }

    }

    public List<TipoVehiculo> mostrarTipolugar() {

        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connector.getConexion().prepareStatement("SELECT * FROM persona");
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
        } finally {
            connector.cerraConexion();
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
        } finally {
            connector.cerraConexion();
        }
        return tipo;

    }
    

}
