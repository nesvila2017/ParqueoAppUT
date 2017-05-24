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
public class RegistroParqueo {

    private Date fechaHoraEntrada;
    private String placaVehiculo;
    private int idLugarParqueo;
    private int idTipoLugar;

    public RegistroParqueo() {
    }

    public RegistroParqueo(Date fechaHoraEntrada, String placaVehiculo, int idLugarParqueo, int idTipoLugar) {
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.placaVehiculo = placaVehiculo;
        this.idLugarParqueo = idLugarParqueo;
        this.idTipoLugar = idTipoLugar;
    }

    public Date getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(Date fechaHoraEntrada) {
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

    public int getIdTipoLugar() {
        return idTipoLugar;
    }

    public void setIdTipoLugar(int idTipoLugar) {
        this.idTipoLugar = idTipoLugar;
    }

}
