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
import model.LugarParqueo;

/**
 *
 * @author GIGABYTE
 */
public class LugarParqueoController {

    private final Conexion connect;

    public LugarParqueoController() {
        this.connect = new Conexion();
    }

    private void createLugarParqueo(int idLugarParq, int tipoLugar, int dispo, int capacidad) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("INSERT INTO lugarparqueo (idLugarParqueo, idTipoLugar, disponibilidad, capacidad) VALUES ( ?, ?, ?, ?)");
            ps.setInt(1, idLugarParq);
            ps.setInt(2, tipoLugar);
            ps.setInt(3, dispo);
            ps.setInt(4, capacidad);
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al ingresar lugar parqueo: " + ex);
        }

    }

    private void updateLugarParqueo(int idLugarParq, int tipoLugarOrig, int tipoLugarNuevo, int dispo, int capacidad) {
        PreparedStatement ps;

        try {
            ps = connect.getConexion().prepareStatement("UPDATE lugarparqueo SET idTipoLugar= ? SET disponibilidad= ? SET capacidad = ? WHERE idLugarParqueo= ? ");
            ps.setInt(1, tipoLugarNuevo);
            ps.setInt(2, dispo);
            ps.setInt(3, capacidad);
            ps.setInt(4, idLugarParq);
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar lugar de parqueo" + ex);
        }

    }

    private void updateDispoLugarParqueo(int idLugar, int dispo) {
        PreparedStatement ps;

        try {
            ps = connect.getConexion().prepareStatement("UPDATE lugarparqueo SET disponibilidad= ? WHERE idLugarParqueo= ? ");
            ps.setInt(1, dispo);
            ps.setInt(2, idLugar);
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar lugar de parqueo" + ex);
        }
    }

    private void eliminarLugarParqueo(int idLugar) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("DELETE FROM lugarparqueo WHERE idLugarParqueo= ?");
            ps.setInt(1, idLugar);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar lugar de parqueo" + ex);
        }
    }

    private LugarParqueo mostrarLugarParqueo(int idLugarParqueo) {
        PreparedStatement ps;
        ResultSet rs;
        LugarParqueo lp = new LugarParqueo();
        try {
            ps = connect.getConexion().prepareStatement("SELECT idLugarParqueo, idTipoLugar, disponibilidad, capacidad FROM lugarParqueo WHERE idLugarParqueo = ?");
            ps.setInt(1, idLugarParqueo);
            rs = ps.executeQuery();
            lp.setIdTipoLugar(rs.getInt(1));
            lp.setIdTipoLugar(rs.getInt(2));
            lp.setDisponibilidad(rs.getInt(3));
            lp.setCapacidad(rs.getInt(4));

            return lp;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar lugar de parqueo: " + ex);
        }

        return null;

    }

    private List<LugarParqueo> mostrarLugaresParqueoTipoDisponibilidad(int tipoDispo) {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<LugarParqueo> listaTipoDiponibles = new ArrayList<>();
        
        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM lugarParqueo WHERE disponibilidad = ?");
            ps.setInt(1, tipoDispo);
            rs = ps.executeQuery();
            while (rs.next()) {
                LugarParqueo lp = new LugarParqueo();
                lp.setIdTipoLugar(rs.getInt(1));
                lp.setIdTipoLugar(rs.getInt(2));
                lp.setDisponibilidad(rs.getInt(3));
                lp.setCapacidad(rs.getInt(4));
                listaTipoDiponibles.add(lp);

            }

            return listaTipoDiponibles;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar lugar de parqueo: " + ex);
        }

        return listaTipoDiponibles;

    }
    
    private List<LugarParqueo> mostrarLugaresParqueo() {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<LugarParqueo> listaTipoDiponibles = new ArrayList<>();
        
        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM lugarParqueo");
            rs = ps.executeQuery();
            while (rs.next()) {
                LugarParqueo lp = new LugarParqueo();
                lp.setIdTipoLugar(rs.getInt(1));
                lp.setIdTipoLugar(rs.getInt(2));
                lp.setDisponibilidad(rs.getInt(3));
                lp.setCapacidad(rs.getInt(4));
                listaTipoDiponibles.add(lp);

            }

            return listaTipoDiponibles;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar lugar de parqueo: " + ex);
        }

        return listaTipoDiponibles;

    }

}
