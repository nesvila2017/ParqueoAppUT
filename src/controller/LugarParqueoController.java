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

    public int conteoUbicaciones() {
        int conteo = 0;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connect.getConexion().prepareStatement("SELECT COUNT(*) FROM LUGARPARQUEO");
            rs = ps.executeQuery();
            rs.next();
            conteo = rs.getInt(1);
            ps.close();
            return conteo;

        } catch (SQLException e) {
            System.out.println("Error en conteo de ubicaciones: " + e);
        }
        return conteo;

    }

    public void createLugarParqueo(int tipoLugar, int dispo, int capacidad) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("INSERT INTO lugarparqueo (idTipoLugar, disponibilidad, capacidad) VALUES (  ?, ?, ?)");
            ps.setInt(1, tipoLugar);
            ps.setInt(2, dispo);
            ps.setInt(3, capacidad);
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al ingresar lugar parqueo: " + ex);
        }

    }

    public void updateLugarParqueo(int idLugarParq, int tipoLugarNuevo, int dispo, int capacidad) {
        PreparedStatement ps;
        //idLugarParqueo`, `idTipoLugar`, `disponibilidad`, `capacidad
        try {                                           //UPDATE parqueo.lugarParqueo SET idLugarParqueo='2', idTipoLugar='2', disponibilidad='2', capacidad='2' WHERE idLugarParqueo='3';
            ps = connect.getConexion().prepareStatement("UPDATE parqueo.lugarParqueo SET idLugarParqueo=?, idTipoLugar=?, disponibilidad=?, capacidad=? WHERE idLugarParqueo=?");
            ps.setInt(1, idLugarParq);
            ps.setInt(2, tipoLugarNuevo);
            ps.setInt(3, dispo);
            ps.setInt(4, capacidad);
            ps.setInt(5, idLugarParq);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar lugar de parqueo" + ex);
        }

    }

    public void updateDispoLugarParqueo(int idLugar, int dispo) {
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

    public void eliminarLugarParqueo(int idLugar) {
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

    public LugarParqueo mostrarLugarParqueo(int idLugarParqueo) {
        PreparedStatement ps;
        ResultSet rs;
        LugarParqueo lp = new LugarParqueo();
        try {
            ps = connect.getConexion().prepareStatement("SELECT idLugarParqueo, idTipoLugar, disponibilidad, capacidad FROM lugarParqueo WHERE idLugarParqueo = ?");
            ps.setInt(1, idLugarParqueo);
            rs = ps.executeQuery();
            if (rs.next()) {
                lp.setIdLugarParqueo(rs.getInt(1));
                lp.setIdTipoLugar(rs.getInt(2));
                lp.setDisponibilidad(rs.getInt(3));
                lp.setCapacidad(rs.getInt(4));
                ps.close();
            }

            return lp;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar cada item lugar de parqueo: " + ex);
        }

        return null;

    }

    public int totalPorTipoUbicacion() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM lugarParqueo WHERE disponibilidad = ?");
        } catch (SQLException ex) {
            Logger.getLogger(LugarParqueoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public List<LugarParqueo> mostrarLugaresParqueoTipoDispoTipoLugar(int tipoDispo, int idTipoLugar) {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<LugarParqueo> listaTipoDiponibles = new ArrayList<>();

        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM lugarParqueo WHERE disponibilidad = ? and idtipolugar = ?");
            ps.setInt(1, tipoDispo);
            ps.setInt(2, idTipoLugar);
            rs = ps.executeQuery();
            while (rs.next()) {
                LugarParqueo lp = new LugarParqueo();
                lp.setIdLugarParqueo(rs.getInt(1));
                lp.setIdTipoLugar(rs.getInt(2));
                lp.setDisponibilidad(rs.getInt(3));
                lp.setCapacidad(rs.getInt(4));
                listaTipoDiponibles.add(lp);

            }

            ps.close();
            return listaTipoDiponibles;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar lugar de parqueo por tipo: " + ex);
        }

        return listaTipoDiponibles;

    }

    public List<LugarParqueo> mostrarLugaresParqueo() {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<LugarParqueo> listaTipoDiponibles = new ArrayList<>();

        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM lugarParqueo");
            rs = ps.executeQuery();
            while (rs.next()) {
                LugarParqueo lp = new LugarParqueo();
                lp.setIdLugarParqueo(rs.getInt(1));
                lp.setIdTipoLugar(rs.getInt(2));
                lp.setDisponibilidad(rs.getInt(3));
                lp.setCapacidad(rs.getInt(4));
                listaTipoDiponibles.add(lp);

            }
            ps.close();
            return listaTipoDiponibles;

        } catch (SQLException ex) {
            System.out.println("Error al Mostrar todos los lugares de parqueo: " + ex);
        }

        return listaTipoDiponibles;

    }

}
