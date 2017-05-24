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
public class Mensualidad {

    private int idMensualidad;
    private int Persona_numIdentPersona;
    private String placaVehiculo;
    private double precio;
    private Date fechaEntrada;
    private Date fechaSalida;
    private int idLugarParqueo;
    private int idTipoLugar;

    public Mensualidad() {
    }

    public Mensualidad(int idMensualidad, int Persona_numIdentPersona, String placaVehiculo, double precio, Date fechaEntrada, Date fechaSalida, int idLugarParqueo, int idTipoLugar) {
        this.idMensualidad = idMensualidad;
        this.Persona_numIdentPersona = Persona_numIdentPersona;
        this.placaVehiculo = placaVehiculo;
        this.precio = precio;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.idLugarParqueo = idLugarParqueo;
        this.idTipoLugar = idTipoLugar;
    }

    public int getIdMensualidad() {
        return idMensualidad;
    }

    public void setIdMensualidad(int idMensualidad) {
        this.idMensualidad = idMensualidad;
    }

    public int getPersona_numIdentPersona() {
        return Persona_numIdentPersona;
    }

    public void setPersona_numIdentPersona(int Persona_numIdentPersona) {
        this.Persona_numIdentPersona = Persona_numIdentPersona;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
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

}
