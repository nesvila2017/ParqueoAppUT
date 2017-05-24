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
public class LugarParqueo {

    private int idLugarParqueo;
    private int idTipoLugar;
    private boolean disponibilidad;
    private int capacidad;

    public LugarParqueo() {
    }

    public LugarParqueo(int idLugarParqueo, int idTipoLugar, boolean disponibilidad, int capacidad) {
        this.idLugarParqueo = idLugarParqueo;
        this.idTipoLugar = idTipoLugar;
        this.disponibilidad = disponibilidad;
        this.capacidad = capacidad;
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

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

}
