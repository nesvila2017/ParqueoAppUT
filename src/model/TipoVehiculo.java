/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author GIGABYTE
 */
public class TipoVehiculo {

    private int idTipoVehiculo;
    private String tipoVehiculo;

    public TipoVehiculo() {
    }

    public TipoVehiculo(int idTipoVehiculo, String tipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

}
