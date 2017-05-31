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
import model.Persona;

/**
 *
 * @author GIGABYTE
 */
public class PersonaController {

    private final Conexion connect;

    public PersonaController() {
        this.connect = new Conexion();
    }

    public void CreatePersona(int ident, String nom, String ape) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("INSERT INTO Persona (numIdentPersona, nombres,apellidos) values (?,?,?)");
            ps.setInt(1, ident);
            ps.setString(2, nom);
            ps.setString(3, ape);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al Insertar Persona: " + e);
        } finally {
            //connect.cerraConexion();
        }
    }

    public void actualizarPersona(int numIdent, String nom, String ape) {
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("UPDATE persona SET numIdentPersona = ?, nombres = ?, apellidos =? WHERE numIdentPersona = ?");
            ps.setInt(1, numIdent);
            ps.setString(2, nom);
            ps.setString(3, ape);
            ps.setInt(4, numIdent);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error actualizacion de persona: " + e);
        } finally {
            //connect.cerraConexion();
        }
    }

    public void eliminarPersona(int numident) {
        PreparedStatement ps;

        try {
            ps = connect.getConexion().prepareStatement("DELETE FROM Persona WHERE numIdentPersona =?");
            ps.setInt(1, numident);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar persona: " + e);
        } finally {
            //connect.cerraConexion();
        }

    }

    public List<Persona> mostrarPersonas() {

        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connect.getConexion().prepareStatement("SELECT * FROM persona");
            rs = ps.executeQuery();
            ArrayList<Persona> personas = new ArrayList<>();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setNumIdent(rs.getInt(1));
                persona.setNomPersona(rs.getString(2));
                persona.setApePersona(rs.getString(3));
                personas.add(persona);

            }
            ps.close();
            return personas;

        } catch (SQLException ex) {
            System.out.println("Error al mostrar personas" + ex);
            return null;
        } finally {
            //connect.cerraConexion();
        }

    }

    public Persona mostrarPersonaPorId(int numident) {
        Persona p = new Persona();
        ResultSet rs;
        try {
            PreparedStatement ps;
            ps = connect.getConexion().prepareStatement("SELECT * FROM PERSONA WHERE numIdentPersona = ?");
            ps.setInt(1, numident);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setNumIdent(rs.getInt(1));
                p.setNomPersona(rs.getString(2));
                p.setApePersona(rs.getString(3));
                return p;
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al mostrar persona por id: " + ex);
            return null;
        } finally{
            //connect.cerraConexion();
        }
        return p;

    }

}
