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
import model.TipoLugar;

/**
 *
 * @author GIGABYTE
 */
public class TipoLugarController {

    private final Conexion connect;

    public TipoLugarController() {
        this.connect = new Conexion();
    }

    public int conteoTipoLugar() {
        int conteo = 0;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connect.getConexion().prepareStatement("SELECT COUNT(*) FROM tipolugar");
            rs = ps.executeQuery();
            rs.next();
            conteo = rs.getInt(1);
            ps.close();
            return conteo;

        } catch (SQLException e) {
            System.out.println("Error en conteo de tipo lugar: " + e);
        }
        return conteo;

    }

    public void createTipoLugar(String tipoLugar) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("INSERT INTO tipoLugar (tipoLuga) values (?)");
            ps.setString(1, tipoLugar);
            ps.execute();
            ps.close();
        } catch (SQLException error) {
            System.out.println("Error al insertar tipoLugar: " + error);
        }

    }

    public void actualizarTipoLugar(int idTipo, String tipoLugar) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("UPDATE tipoLugar SET tipoLuga = ? WHERE idTipoLugar = ?");
            ps.setString(1, tipoLugar);
            ps.setInt(2, idTipo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error actualizacion de tipoLugar: " + e);
        }
    }

    public void eliminarTipoLugar(int idTipo) {
        PreparedStatement ps;

        try {
            ps = connect.getConexion().prepareStatement("DELETE FROM tipoLugar WHERE idTipoLugar =?");
            ps.setInt(1, idTipo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar tipoLugar: " + e);
        }

    }

    public List<TipoLugar> mostrarTipolugar() {

        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connect.getConexion().prepareStatement("SELECT * FROM tipolugar");
            rs = ps.executeQuery();
            ArrayList<TipoLugar> tipoLugar = new ArrayList<>();
            while (rs.next()) {
                TipoLugar tipo = new TipoLugar();
                tipo.setIdTipoLugar(rs.getInt(1));
                tipo.setTipoLugar(rs.getString(2));
                tipoLugar.add(tipo);

            }
            return tipoLugar;

        } catch (SQLException ex) {
            System.out.println("Error al mostrar los tipos de lugares" + ex);
            return null;
        }

    }

    public TipoLugar mostrarTipoLugarPorId(int idTipo) {
        TipoLugar tipo = new TipoLugar();
        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connect.getConexion().prepareStatement("SELECT * FROM tipoLugar WHERE idTipoLugar = ?");
            ps.setInt(1, idTipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                tipo.setIdTipoLugar(rs.getInt(1));
                tipo.setTipoLugar(rs.getString(2));
                return tipo;
            }

        } catch (SQLException ex) {
            System.out.println("Error al mostrar TipoLugar por id: " + ex);
            return null;
        }
        return tipo;

    }

}
