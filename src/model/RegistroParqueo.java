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
public class RegistroParqueo {

    private int idRegistroParqueo;
    private Timestamp fechaHoraEntrada;
    private String placaVehiculo;
    private String tipoVehiculo;
    private int idLugarParqueo;
    private String estadoRegistro;

    public RegistroParqueo() {
    }

    public RegistroParqueo(int idRegistroParqueo, Timestamp fechaHoraEntrada, String placaVehiculo, String tipoVehiculo, int idLugarParqueo, String estadoRegistro) {
        this.idRegistroParqueo = idRegistroParqueo;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.placaVehiculo = placaVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.idLugarParqueo = idLugarParqueo;
        this.estadoRegistro = estadoRegistro;
    }

    public int getIdRegistroParqueo() {
        return idRegistroParqueo;
    }

    public void setIdRegistroParqueo(int idRegistroParqueo) {
        this.idRegistroParqueo = idRegistroParqueo;
    }

    public Timestamp getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(Timestamp fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public int getIdLugarParqueo() {
        return idLugarParqueo;
    }

    public void setIdLugarParqueo(int idLugarParqueo) {
        this.idLugarParqueo = idLugarParqueo;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

}
