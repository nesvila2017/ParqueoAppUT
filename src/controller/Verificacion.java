/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import view.LugarParqueoView;
import view.TarifaView;
import view.TipoLugarView;
import view.UbicacionesView;

/**
 *
 * @author Wilter Villar
 */
public class Verificacion {

    TarifaController tc = new TarifaController();
    TipoLugarController tp = new TipoLugarController();
    LugarParqueoController lpc = new LugarParqueoController();

    public void verificacion() {

    }

    public void validacion() {
        if (tp.conteoTipoLugar() == 0) {
            JOptionPane.showMessageDialog(null, "No encontramos tipos de vehiculos establecidos, a continuación ingrese los tipo de vehiculo que administra su parqueadero.");
            TipoLugarView tlv = new TipoLugarView();
            tlv.setVisible(true);
        } else if (tc.conteoTarifas() == 0) {
            JOptionPane.showMessageDialog(null, "No encontramos tarifas establecidas, a continuación cree sus tarifas para cada tipo de vehiculo.");
            TarifaView tf = new TarifaView();
            tf.setVisible(true);
        } else if (lpc.conteoUbicaciones() == 0) {
            JOptionPane.showMessageDialog(null, "No encontramos ubicaciones de parqueo establecidas, a continuación cree las ubicaciones para cada tipo de vehiculo.");
            UbicacionesView ub = new UbicacionesView();
            ub.setVisible(true);
        } else {
            LugarParqueoView lpv = new LugarParqueoView();
            lpv.setVisible(true);
        }
    }
}
