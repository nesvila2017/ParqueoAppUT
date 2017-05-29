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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Vehiculo;

/**
 *
 * @author GIGABYTE
 */
public class VehiculoController {
    
    private final Conexion connect;
    
    public VehiculoController() {
        this.connect = new Conexion();
    }
    
    private void createVehiculo(String placaVehiculo, int idTipoVehiculo) {
        PreparedStatement ps;
        
        try {
            ps = connect.getConexion().prepareStatement("INSERT INTO Vehiculo (placaVehiculo, TipoVehiculo_idTipoVehiculo) values(?,?)");
            ps.setString(1, placaVehiculo);
            ps.setInt(2, idTipoVehiculo);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error ingresar Vehiculo: " + ex);
        } finally {
            connect.cerraConexion();
        }
    }
    
    private void updateVeciculo(String placa, int TipoVehic) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("UPDATE INTO Vehiculo SET placaVehiculo = ? TipoVehiculo = ? WHERE placaVehiculo = ?");
            ps.setString(1, placa);
            ps.setInt(2, TipoVehic);
            ps.setString(3, placa);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar placaVehiculo: " + ex);
        } finally {
            connect.cerraConexion();
        }
        
    }
    
    private Vehiculo buscarVehiculoPorPlaca(String placa) {
        
        Vehiculo v = new Vehiculo();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = connect.getConexion().prepareStatement("SELECT placaVehiculo, TipoVehiculo_idTipoVehiculo FROM Vehiculo WHERE placaVehiculo = ?");
            ps.setString(1, placa);
            rs = ps.executeQuery();
            while (rs.next()) {
                v.setPlaca(rs.getString(1));
                v.setIdtipoVehiculo(rs.getInt(2));
                return v;
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar vehiculo: " + ex);
        } finally {
            connect.cerraConexion();
        }
        return null;
    }
    
    private List<Vehiculo> mostrarTodosLosVehiculosRegistrados() {
        ArrayList<Vehiculo> vlista = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM VEHICULO");
            rs=ps.executeQuery();
            while (rs.next()) {  
                Vehiculo v = new Vehiculo();
                v.setPlaca(rs.getString(1));
                v.setIdtipoVehiculo(rs.getInt(2));
                vlista.add(v);
            }
            return vlista;
                    
        } catch (SQLException ex) {
            System.out.println("Error al mostrar todos los vehiculos: " +ex);
        }
        
        return vlista;        
    }
    
        private List<Vehiculo> mostrarTodosLosVehiculosPorTipo(int idTipo) {
        ArrayList<Vehiculo> vlista = new ArrayList<>();
        
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM VEHICULO WHERE TipoVehiculo_IdTipoVehiculo = ?");
            ps.setInt(1, idTipo);
            rs=ps.executeQuery();
            
            while (rs.next()) {  
                Vehiculo v = new Vehiculo();
                v.setPlaca(rs.getString(1));
                v.setIdtipoVehiculo(rs.getInt(2));
                vlista.add(v);
            }
            return vlista;
                    
        } catch (SQLException ex) {
            System.out.println("Error al mostrar todos los vehiculos por tipo: " +ex);
        }
        
        return vlista;        
    }
    
    private boolean eliminarVehiculo(String placa){
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("DELETE FROM Vehiculo WHERE placaVehiculo = ?");
            ps.setString(1, placa);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar vehiculo: " +ex);
        }finally{
            connect.cerraConexion();
        }
        return false;
    }
    
}
