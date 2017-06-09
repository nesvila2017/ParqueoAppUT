/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexionParqueo.Conexion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.FacturaDetalle;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author GIGABYTE
 */
public class FacturaController {

    private Conexion connect = new Conexion();

    public FacturaController() {
        this.connect = new Conexion();
    }

    public Time calcularDiferenciaTiempo(int idRegistroParqueo, String placa) {
        PreparedStatement ps;
        ResultSet rs;
        Time difDuracionParqueo;
        try {
            ps = connect.getConexion().prepareStatement("select timediff(now(),(select fechaHoraEntrada from registroParqueo where idRegistroParqueo = ? and placaVehiculo = ?))");
            ps.setInt(1, idRegistroParqueo);
            ps.setString(2, placa);

            rs = ps.executeQuery();
            rs.next();
            difDuracionParqueo = rs.getTime(1);
            System.out.println("Hora diferencia Parqueo timestamp: " + difDuracionParqueo);
            ps.close();
            return difDuracionParqueo;

        } catch (SQLException sQLException) {
            System.out.println("Error al caluclar tiempohoras " + sQLException);
        }
        return null;
    }

    public int calcularTiempoHoras(int idRegistroParqueo, String placa) {
        PreparedStatement ps;
        ResultSet rs;
        int horaDuracionParqueo;
        try {
            ps = connect.getConexion().prepareStatement("select hour((select timediff(now(), (select fechaHoraEntrada from registroparqueo where placaVehiculo = ? and idRegistroParqueo = ? ))))");
            ps.setString(1, placa);
            ps.setInt(2, idRegistroParqueo);
            rs = ps.executeQuery();
            rs.next();
            horaDuracionParqueo = rs.getInt(1);
            System.out.println("Hora Parqueo timestamp: " + horaDuracionParqueo);
            ps.close();
            return horaDuracionParqueo;

        } catch (SQLException sQLException) {
            System.out.println("Error al caluclar tiempohoras " + sQLException);
        }
        return 0;
    }

    public int calcularTiempoMinutos(int idRegistroParqueo, String placa) {
        PreparedStatement ps;
        ResultSet rs;
        int minutoDuracionParqueo;
        try {
            ps = connect.getConexion().prepareStatement("select minute((select timediff(now(), (select fechaHoraEntrada from registroparqueo where placaVehiculo = ? and idRegistroParqueo = ?))))");
            ps.setInt(1, idRegistroParqueo);
            ps.setString(2, placa);
            rs = ps.executeQuery();
            rs.next();
            minutoDuracionParqueo = rs.getInt(1);
            System.out.println("Hora Parqueo timestamp: " + minutoDuracionParqueo);
            ps.close();
            return minutoDuracionParqueo;
        } catch (SQLException sQLException) {
            System.out.println("Error al caluclar tiempoMinutos " + sQLException);
        }

        return 0;

    }

    public double precioSalida(int idRegistroParqueo, String placa) {
        PreparedStatement ps;
        ResultSet rs;
        int horaDuracionParqueo;
        int minutoDuracionParqueo;

        double precioFraccion;
        int tipoVehiculo;
        try {
            ps = connect.getConexion().prepareStatement("select TipoVehiculo_idTipoVehiculo from Vehiculo where placaVehiculo =  ? ");
            ps.setString(1, placa);
            rs = ps.executeQuery();
            rs.next();
            tipoVehiculo = rs.getInt(1);
            System.out.println("Tipo vehiculo " + tipoVehiculo);
            ps.close();

            horaDuracionParqueo = calcularTiempoHoras(idRegistroParqueo, placa);
            minutoDuracionParqueo = calcularTiempoMinutos(idRegistroParqueo, placa);

            ps = connect.getConexion().prepareStatement("select precioFraccion from tarifa where TipoVehiculo_idTipoVehiculo = ?");
            ps.setInt(1, tipoVehiculo);
            rs = ps.executeQuery();
            rs.next();
            precioFraccion = rs.getDouble(1);
            ps.close();

            double resultado;
            if (minutoDuracionParqueo > 0) {
                minutoDuracionParqueo = 0;
                resultado = (horaDuracionParqueo + minutoDuracionParqueo) * precioFraccion;
            } else {
                minutoDuracionParqueo = 1;
                resultado = (horaDuracionParqueo + minutoDuracionParqueo) * precioFraccion;
            }

            return resultado;

        } catch (SQLException ex) {
            System.out.println("ddd " + ex);
        }

        return 0;
    }

    public boolean createFactura(String placaVehiculo, int idRegistroParqueo, int idLugarParqueo, double valorParqueo) {
        PreparedStatement ps;
        RegistroParqueoController rpc = new RegistroParqueoController();
        try {
            if (rpc.buscarRegistroParqueoEstadoFacturado(placaVehiculo, idRegistroParqueo) == true) {
                return true;
            } else {
                ps = connect.getConexion().prepareStatement("INSERT INTO factura (FechaFactura, fechaSalida, placaVehiculo, idRegistroParqueo, idLugarParqueo, valParqueo) VALUES (now(),now(),?,?,?,?)");
                ps.setString(1, placaVehiculo);
                ps.setInt(2, idRegistroParqueo);
                ps.setInt(3, idLugarParqueo);
                ps.setDouble(4, valorParqueo);
                ps.executeUpdate();
                ps.close();
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Error al crear Factura: " + ex);
        }
        return false;

    }

    public List<FacturaDetalle> verFacturaDetalle() {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<FacturaDetalle> listaFacturas = new ArrayList<>();

        try {
            ps = connect.getConexion().prepareStatement("select * from detalle order by idFactura");
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaDetalle n = new FacturaDetalle();
                n.setIdFactura(rs.getInt(1));
                n.setIdRegistroParqueo(rs.getInt(2));
                n.setFechaFactura(rs.getTimestamp(3));
                n.setFechaHoraEntrada(rs.getTimestamp(4));
                n.setFechaSalida(rs.getTimestamp(5));
                n.setDuracion(rs.getTime(6));
                n.setHoras(rs.getInt(7));
                n.setMinutos(rs.getInt(8));
                n.setPlacaVehiculo(rs.getString(9));
                n.setTipoVehTxt(rs.getString(10));
                n.setIdLugarParqueo(rs.getInt(11));
                n.setPrecio(rs.getDouble(12));
                listaFacturas.add(n);
            }

            ps.close();
            return listaFacturas;

        } catch (SQLException ex) {
            System.out.println("Error al mostrar todos los registros de factura. " + ex);
        }
        return listaFacturas;

    }

    public double importeTotal() {
        double importe = 0;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connect.getConexion().prepareStatement("select sum(valParqueo) as totalImporte from detalle");
            rs = ps.executeQuery();
            rs.next();
            importe = rs.getDouble(1);
            return importe;

        } catch (SQLException sQLException) {
        }
        return 0;

    }

}
