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
public class Tarifa {

    private int idTarifa;
    private String nomTarifa;
    private double precioFraccion;
    private int idTipoVehiculo;
    

    public Tarifa() {
    }

    public Tarifa(int idTarifa, String nomTarifa, double precioFraccion, int idTipoVehiculo) {
        this.idTarifa = idTarifa;
        this.nomTarifa = nomTarifa;
        this.precioFraccion = precioFraccion;
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getNomTarifa() {
        return nomTarifa;
    }

    public void setNomTarifa(String nomTarifa) {
        this.nomTarifa = nomTarifa;
    }

    
    public double getPrecioFraccion() {
        return precioFraccion;
    }

    public void setPrecioFraccion(double precioFraccion) {
        this.precioFraccion = precioFraccion;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

}
