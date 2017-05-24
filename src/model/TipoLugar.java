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
public class TipoLugar {

    private int idTipoLugar;
    private String tipoLugar;

    public TipoLugar() {
    }

    public TipoLugar(int idTipoLugar, String tipoLugar) {
        this.idTipoLugar = idTipoLugar;
        this.tipoLugar = tipoLugar;
    }

    public int getIdTipoLugar() {
        return idTipoLugar;
    }

    public void setIdTipoLugar(int idTipoLugar) {
        this.idTipoLugar = idTipoLugar;
    }

    public String getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

}
