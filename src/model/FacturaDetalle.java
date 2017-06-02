/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author GIGABYTE
 */
public class FacturaDetalle {
    private int idFactura;
    private Timestamp FechaFactura;
    private int idRegistroParqueo;
    private String placaVehiculo;
    private int tipoVehiculo;
    private Timestamp fechaHoraEntrada;
    private Timestamp fechaSalida;
    private int idLugarParqueo;
    private double precio;

    public FacturaDetalle() {
    }

    
    
    public FacturaDetalle(int idFactura, Timestamp FechaFactura, int idRegistroParqueo, String placaVehiculo, int tipoVehiculo, Timestamp fechaHoraEntrada, Timestamp fechaSalida, int idLugarParqueo, double precio) {
        this.idFactura = idFactura;
        this.FechaFactura = FechaFactura;
        this.idRegistroParqueo = idRegistroParqueo;
        this.placaVehiculo = placaVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.fechaSalida = fechaSalida;
        this.idLugarParqueo = idLugarParqueo;
        this.precio = precio;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Timestamp getFechaFactura() {
        return FechaFactura;
    }

    public void setFechaFactura(Timestamp FechaFactura) {
        this.FechaFactura = FechaFactura;
    }

    public int getIdRegistroParqueo() {
        return idRegistroParqueo;
    }

    public void setIdRegistroParqueo(int idRegistroParqueo) {
        this.idRegistroParqueo = idRegistroParqueo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public int getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(int tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Timestamp getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(Timestamp fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getIdLugarParqueo() {
        return idLugarParqueo;
    }

    public void setIdLugarParqueo(int idLugarParqueo) {
        this.idLugarParqueo = idLugarParqueo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
}
