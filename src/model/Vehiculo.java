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
public class Vehiculo {

    private String placa;
    private int idtipoVehiculo;

    public Vehiculo() {
    }

    public Vehiculo(String placa, int idtipoVehiculo) {
        this.placa = placa;
        this.idtipoVehiculo = idtipoVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getIdtipoVehiculo() {
        return idtipoVehiculo;
    }

    public void setIdtipoVehiculo(int idtipoVehiculo) {
        this.idtipoVehiculo = idtipoVehiculo;
    }

}
