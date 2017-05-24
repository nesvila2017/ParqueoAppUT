/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author GIGABYTE
 */
public class Factura {

    private int idFactura;
    private Date FechaFactura;
    private int horasDuracion;
    private int minutosDuracion;
    private Date fechaSalida;
    private String placaVehiculo;
    private Date fechaHoraEntrada;
    private int idLugarParqueo;
    private int idTipoLugar;
    private double valParqueo;

    public Factura() {
    }

    public Factura(int idFactura, Date FechaFactura, int horasDuracion, int minutosDuracion, Date fechaSalida, String placaVehiculo, Date fechaHoraEntrada, int idLugarParqueo, int idTipoLugar, double valParqueo) {
        this.idFactura = idFactura;
        this.FechaFactura = FechaFactura;
        this.horasDuracion = horasDuracion;
        this.minutosDuracion = minutosDuracion;
        this.fechaSalida = fechaSalida;
        this.placaVehiculo = placaVehiculo;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.idLugarParqueo = idLugarParqueo;
        this.idTipoLugar = idTipoLugar;
        this.valParqueo = valParqueo;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFactura() {
        return FechaFactura;
    }

    public void setFechaFactura(Date FechaFactura) {
        this.FechaFactura = FechaFactura;
    }

    public int getHorasDuracion() {
        return horasDuracion;
    }

    public void setHorasDuracion(int horasDuracion) {
        this.horasDuracion = horasDuracion;
    }

    public int getMinutosDuracion() {
        return minutosDuracion;
    }

    public void setMinutosDuracion(int minutosDuracion) {
        this.minutosDuracion = minutosDuracion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public Date getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(Date fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public int getIdLugarParqueo() {
        return idLugarParqueo;
    }

    public void setIdLugarParqueo(int idLugarParqueo) {
        this.idLugarParqueo = idLugarParqueo;
    }

    public int getIdTipoLugar() {
        return idTipoLugar;
    }

    public void setIdTipoLugar(int idTipoLugar) {
        this.idTipoLugar = idTipoLugar;
    }

    public double getValParqueo() {
        return valParqueo;
    }

    public void setValParqueo(double valParqueo) {
        this.valParqueo = valParqueo;
    }

}
