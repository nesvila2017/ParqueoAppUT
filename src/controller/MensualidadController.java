/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexionParqueo.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Mensualidad;

/**
 *
 * @author GIGABYTE
 */
public class MensualidadController {

    private final Conexion connect;

    public MensualidadController() {
        this.connect = new Conexion();
    }

    public void createMensualidad(int idMens, int numIdent, String placa, double precio, int idlugarParqueo, int idtipoLugar, int cantMeses) {
        Date fechaSalida;
        PreparedStatement ps;
        ResultSet rs;
        try {

            ps = connect.getConexion().prepareCall("SET @FECHASALIDA = (SELECT  DATE_ADD(now(),INTERVAL " + cantMeses + " DAY)");
            rs = ps.executeQuery();
            while (rs.next()) {
                fechaSalida = rs.getDate(1);
                System.out.println("Fecha de salida: ");
            }
            // ps = connect.getConexion().prepareStatement("INSERT INTO Mensualidad (idMesualidad, Persona_numIdentPersona, placaVehiculo, precio, fechaEntrada, fechaSalida, idLugarParqueo, idTipoLugar) values(?,?,?,?,NOW(),)");

        } catch (SQLException ex) {
            System.out.println("Error al ingresar mensualidad: " + ex);
        }

    }

    public String calcularMensualidad(int cantMeses) {
        Date fechaSalida;
        Time horaSalida;
        Date fechaEntrada;
        Time horaEntrada;
        String fechaSalidaMostrar;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PreparedStatement ps;
        ResultSet rs;
        try {

            ps = connect.getConexion().prepareStatement("SELECT NOW(), DATE_ADD(now(),INTERVAL " + cantMeses + " MONTH)");
            //rs = ps.executeQuery("SELECT @FECHASALIDA as Fecha_Salida");
            rs = ps.executeQuery();
            while (rs.next()) {
                fechaSalida = rs.getDate(2);
                horaSalida = rs.getTime(2);
                fechaEntrada = rs.getDate(1);
                horaEntrada = rs.getTime(1);

                System.out.println("Fecha Entrada: " + fechaEntrada + " " + horaEntrada + "\n" + "Fecha de salida: " + fechaSalida + " " + horaSalida);

                fechaSalidaMostrar = "Fecha Entrada: " + fechaEntrada + " " + horaEntrada + "\n" + "Fecha de salida: " + fechaSalida + " " + horaSalida;
                return fechaSalidaMostrar;
            }
            // ps = connect.getConexion().prepareStatement("INSERT INTO Mensualidad (idMesualidad, Persona_numIdentPersona, placaVehiculo, precio, fechaEntrada, fechaSalida, idLugarParqueo, idTipoLugar) values(?,?,?,?,NOW(),)");

        } catch (SQLException ex) {
            System.out.println("Error al ingresar mensualidad: " + ex);
        } finally {
            connect.cerraConexion();
        }

        return null;
    }

    public List<Mensualidad> mostrarMensualidad() {
        ArrayList<Mensualidad> ml = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connect.getConexion().prepareStatement("SELECT * FROM Mensualidad INNER JOIN Tipo");

        } catch (SQLException ex) {
            System.out.println("Error al mostrar mensualidad: " + ex);
        }

        return ml;
    }

}
