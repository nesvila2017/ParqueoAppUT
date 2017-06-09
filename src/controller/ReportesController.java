/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexionParqueo.Conexion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Wilter Villar
 */
public class ReportesController {

    private static Conexion connect;

    public ReportesController() {
        connect = new Conexion();
    }

    public void reporteVehiculosRegistrados() {
        try {
            InputStream archivo = new FileInputStream("src/Reportes/VehiculosRegistrados.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(archivo);
            JasperPrint jpr = JasperFillManager.fillReport(jr, null, connect.getConexion());
            JasperViewer.viewReport(jpr, false);

        } catch (FileNotFoundException | JRException e) {
            System.out.println("Error reporte vehiculos registrados: " + e);
        }
    }

    public void reporteRegistrosParqueoPorFecha(Timestamp fechaIni, Timestamp fechaFin) {
        try {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("fechaInicio", fechaIni);
            parametros.put("FechaFinal", fechaFin);
            try (InputStream archivo = new FileInputStream("src/Reportes/registroparqueoporFecha.jrxml")) {
                JasperReport jr = JasperCompileManager.compileReport(archivo);
                JasperPrint jpr = JasperFillManager.fillReport(jr, parametros, connect.getConexion());
                JasperViewer.viewReport(jpr, false);
            }
        } catch (FileNotFoundException | JRException e) {
            System.out.println("Error al mostrar Informe de registro parqueo por fecha: " + e);
        } catch (IOException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reporteRegistrosParqueoTotal() {
        try {
            InputStream archivo = new FileInputStream("src/Reportes/RegistroParqueo.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(archivo);
            JasperPrint jpr = JasperFillManager.fillReport(jr, null, connect.getConexion());
            JasperViewer.viewReport(jpr, false);
        } catch (FileNotFoundException | JRException e) {
            System.out.println("Error al generar informe de registro de parqueo totalizado: " + e);
        }
    }

    public void reporteRegistrosParqueoPorEstado() {

    }

    public void reporteFacturasTotal() {
        try {
            InputStream archivo = new FileInputStream("src/Reportes/FacturaTotal.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(archivo);
            JasperPrint jpr = JasperFillManager.fillReport(jr, null, connect.getConexion());
            JasperViewer.viewReport(jpr, false);
        } catch (JRException | FileNotFoundException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reporteFacturasTotalRangoFecha(Timestamp fechaIni, Timestamp fechaFin) {
        try {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("fechaInicio", fechaIni);
            parametros.put("fechaFin", fechaFin);
            InputStream archivo = new FileInputStream("src/Reportes/facturaFecha.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(archivo);
            JasperPrint jpr = JasperFillManager.fillReport(jr, parametros, connect.getConexion());
            JasperViewer.viewReport(jpr, false);
        } catch (FileNotFoundException | JRException e) {
            System.out.println("Error en generacion de informe de facturas por fecha: " + e);
        }
    }

    public void reporteFactura(int idfactura) {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("idFact", idfactura);
            InputStream reportStream = new FileInputStream("src/Reportes/Factura.jrxml");
            Conexion con = new Conexion();

            JasperReport report = JasperCompileManager.compileReport(reportStream);
            JasperPrint print = JasperFillManager.fillReport(report, (Map<String, Object>) parameters, con.getConexion());
            JasperViewer.viewReport(print, false);
            reportStream.close();

        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }

}
