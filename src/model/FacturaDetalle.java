/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author GIGABYTE
 */
public class FacturaDetalle {

    private int idFactura;
    private int idRegistroParqueo;
    private Timestamp FechaFactura;
    private Timestamp fechaHoraEntrada;
    private Timestamp fechaSalida;
    private Time duracion;
    private int horas;
    private int minutos;
    private String placaVehiculo;
    private int tipoVehiculo;
    private String tipoVehTxt;
    private int idLugarParqueo;
    private double precio;

    public FacturaDetalle() {
    }

    public FacturaDetalle(int idFactura, int idRegistroParqueo, Timestamp FechaFactura, Timestamp fechaHoraEntrada, Timestamp fechaSalida, int horas, int minutos, String placaVehiculo, String tipoVehTxt, int idLugarParqueo, double precio) {
        this.idFactura = idFactura;
        this.idRegistroParqueo = idRegistroParqueo;
        this.FechaFactura = FechaFactura;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.fechaSalida = fechaSalida;
        this.horas = horas;
        this.minutos = minutos;
        this.placaVehiculo = placaVehiculo;
        this.tipoVehTxt = tipoVehTxt;
        this.idLugarParqueo = idLugarParqueo;
        this.precio = precio;
    }

    public String getTipoVehTxt() {
        return tipoVehTxt;
    }

    public void setTipoVehTxt(String tipoVehTxt) {
        this.tipoVehTxt = tipoVehTxt;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
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
