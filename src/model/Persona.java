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
public class Persona {

    private int numIdent;
    private String nomPersona;
    private String apePersona;

    public Persona() {
    }

    public Persona(int numIdent, String nomPersona, String apePersona) {
        this.numIdent = numIdent;
        this.nomPersona = nomPersona;
        this.apePersona = apePersona;
    }

    public int getNumIdent() {
        return numIdent;
    }

    public void setNumIdent(int numIdent) {
        this.numIdent = numIdent;
    }

    public String getNomPersona() {
        return nomPersona;
    }

    public void setNomPersona(String nomPersona) {
        this.nomPersona = nomPersona;
    }

    public String getApePersona() {
        return apePersona;
    }

    public void setApePersona(String apePersona) {
        this.apePersona = apePersona;
    }

}
