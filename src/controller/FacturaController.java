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
import java.sql.Timestamp;

/**
 *
 * @author GIGABYTE
 */
public class FacturaController {

    private Conexion connect = new Conexion();

    public FacturaController() {
        this.connect = new Conexion();
    }
    
    public double precioSalida(int idRegistroParqueo, String placa){
        PreparedStatement ps;
        ResultSet rs;
        Timestamp horaDuracionParqueo;
        Timestamp minutoDuracionParqueo;
        int minutoConvertido;
        int horaConvertida;
        double precioFraccion;
        int tipoVehiculo;
        try {
            ps = connect.getConexion().prepareStatement("select tipovehiculo from registroParqueo where placaVehiculo = ? ");
            ps.setString(1, placa);
            rs=ps.executeQuery();
            rs.next();
            tipoVehiculo = rs.getInt(1);
            System.out.println("Tipo vehiculo " + tipoVehiculo);
            ps.close();
                    
            ps = connect.getConexion().prepareStatement("select hour( select timediff(now(), (select fechaHoraEntrada from registroparqueo where placaVehiculo = ? and idRegistroParqueo =? )))");
            ps.setInt(1, idRegistroParqueo);
            ps.setString(2, placa);
            rs=ps.executeQuery();
            rs.next();
            horaDuracionParqueo = rs.getTimestamp(1);
            System.out.println("Hora Parqueo timestamp: " + horaDuracionParqueo);
            horaConvertida= Integer.parseInt(horaDuracionParqueo.toString());
            System.out.println("Hora Parqueo Convertida: " + horaConvertida);
            ps.close();
            ps = connect.getConexion().prepareStatement("select minute(select timediff(now(), (select fechaHoraEntrada from registroparqueo where placaVehiculo = ? and idRegistroParqueo = ?)))");
            ps.setInt(1, idRegistroParqueo);
            ps.setString(2, placa);
            rs = ps.executeQuery();
            rs.next();
            minutoDuracionParqueo=rs.getTimestamp(1);
            minutoConvertido = Integer.parseInt(minutoDuracionParqueo.toString());
            ps.close();
            ps = connect.getConexion().prepareStatement("select precioFraccion from tarifa where TipoVehiculo_idTipoVehiculo = 1");  
            ps.setInt(1, tipoVehiculo);
            rs = ps.executeQuery();
            rs.next();
            precioFraccion = rs.getDouble(1);
            
            double resultado = (horaConvertida + minutoConvertido) * precioFraccion;
            
            return resultado;
            
        } catch (SQLException ex) {
            System.out.println("ddd" +ex);
        }
        
        return 0;
    } 
    
    
    private void createFactura(int idFactura, String placaVehiculo, int idRegistroParqueo, int idLugarParqueo){
        PreparedStatement ps;
        try {
            ps = connect.getConexion().prepareStatement("INSERT INTO factura (idFactura, FechaFactura, fechaSalida, placaVehiculo, idRegistroParqueo, idLugarParqueo, valParqueo) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(idFactura, idFactura);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al crear Factura: " + ex);
        }
        
    }

}
