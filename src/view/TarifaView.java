/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TarifaController;
import controller.TipoVehiculoController;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Tarifa;

/**
 *
 * @author Wilter Villar
 */
public class TarifaView extends javax.swing.JFrame {

    private int tipoOperacion;

    /**
     * Creates new form TarifaView
     */
    public TarifaView() {
        initComponents();
        llenarTipoVehiculos();
        deshabilitarItems();
        llenarTabla();
    }

    private void setTipoOperacion(int tipo) {
        this.tipoOperacion = tipo;
    }

    private int getTipoOperacion() {

        return tipoOperacion;
    }

    private void llenarTipoVehiculos() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        TipoVehiculoController tvc = new TipoVehiculoController();
        List<String> lista = tvc.mostrarTiposVehiculos();
        cb.addElement("Selecione un tipo de Vehiculo.");
        if (!lista.isEmpty()) {
            for (int i = 0; i < lista.size(); i++) {
                cb.addElement(lista.get(i));
            }
            comboTipoVehiculo.setModel(cb);
        } else {
            JOptionPane.showMessageDialog(null, "No hay registros de tipos de vehiculos.");
        }

    }

    private void llenarTabla() {
        DefaultTableModel dtf = new DefaultTableModel();
        String cabecera[] = new String[]{"ID Tarifa", "Nombre Tarifa", "Precio Tarifa", "Tipo Vehiculo"};
        dtf.setColumnIdentifiers(cabecera);
        tablaTarifa.setCellEditor(null);
        TarifaController tfc = new TarifaController();
        List<Tarifa> tf = tfc.mostrarTarifas();
        tablaTarifa.setModel(dtf);
        dtf.setNumRows(tf.size());
        for (int i = 0; i < tf.size(); i++) {
            dtf.setValueAt(tf.get(i).getIdTarifa(), i, 0);
            dtf.setValueAt(tf.get(i).getNomTarifa(), i, 1);
            dtf.setValueAt(tf.get(i).getPrecioFraccion(), i, 2);
            dtf.setValueAt(tf.get(i).getTipoVehic(), i, 3);
        }

    }

    private void deshabilitarBotones() {
        btnIngresarTarifa.setEnabled(false);
        btnModificarTarifa.setEnabled(false);
        btnEliminarTarifa.setEnabled(false);
        btnGuardar.setEnabled(true);
    }

    private void habilitarBotones() {
        btnIngresarTarifa.setEnabled(true);
        btnModificarTarifa.setEnabled(true);
        btnEliminarTarifa.setEnabled(true);
        btnGuardar.setEnabled(false);
    }

    private void deshabilitarItems() {
        txtID.setText("");
        txtNomTarifa.setText("");
        txtPrecioTarifa.setText("");
        comboTipoVehiculo.setSelectedIndex(0);

        txtNomTarifa.setEnabled(false);
        txtPrecioTarifa.setEnabled(false);
        comboTipoVehiculo.setEnabled(false);
    }

    private void habilitarItems() {
        txtID.setText("");
        txtNomTarifa.setText("");
        txtPrecioTarifa.setText("");
        comboTipoVehiculo.setSelectedIndex(0);
        txtNomTarifa.setEnabled(true);
        txtPrecioTarifa.setEnabled(true);
        comboTipoVehiculo.setEnabled(true);

    }

    private void insertarTarifa() {
        TarifaController tfc = new TarifaController();
        if (txtNomTarifa.getText().equals("") || txtPrecioTarifa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre de la tarifa y su respectivo precio.");
        } else {
            tfc.insertTarifa(txtNomTarifa.getText(), Double.valueOf(txtPrecioTarifa.getText()), comboTipoVehiculo.getSelectedIndex());
            JOptionPane.showMessageDialog(null, "Registro Exitoso.");
        }

    }

    private void modificarTarifa() {

        TarifaController tfc = new TarifaController();
        if (txtNomTarifa.getText().equals("") || txtPrecioTarifa.getText().equals("") || txtID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione la tarifa que desea modificar.");
        } else {
            tfc.actualizarTarifa(Integer.parseInt(txtID.getText()), txtNomTarifa.getText(), Double.valueOf(txtPrecioTarifa.getText()), comboTipoVehiculo.getSelectedIndex());
            JOptionPane.showMessageDialog(null, "Actualización Exitosa.");
        }
    }

    private void eliminarTarifa() {

        TarifaController tfc = new TarifaController();
        if (txtNomTarifa.getText().equals("") || txtPrecioTarifa.getText().equals("") || txtID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione la tarifa que desea modificar.");
        } else {
            tfc.eliminarTarifa(Integer.parseInt(txtID.getText()));
            JOptionPane.showMessageDialog(null, "Eliminación Exitosa.");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTarifa = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNomTarifa = new javax.swing.JTextField();
        comboTipoVehiculo = new javax.swing.JComboBox<>();
        txtPrecioTarifa = new javax.swing.JTextField();
        btnIngresarTarifa = new javax.swing.JButton();
        btnModificarTarifa = new javax.swing.JButton();
        btnEliminarTarifa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaTarifa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaTarifa);

        jLabel1.setText("Nombre Tarifa:");

        jLabel2.setText("Tarifa para Vehiculo:");

        jLabel3.setText("Precio Tarifa:");

        comboTipoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoVehiculoActionPerformed(evt);
            }
        });

        txtPrecioTarifa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioTarifaKeyTyped(evt);
            }
        });

        btnIngresarTarifa.setText("Ingresar");
        btnIngresarTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarTarifaActionPerformed(evt);
            }
        });

        btnModificarTarifa.setText("Modificar");
        btnModificarTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTarifaActionPerformed(evt);
            }
        });

        btnEliminarTarifa.setText("Eliminar");
        btnEliminarTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTarifaActionPerformed(evt);
            }
        });

        jLabel4.setText("ID Tarifa:");

        txtID.setEditable(false);

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.setOpaque(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboTipoVehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrecioTarifa)
                            .addComponent(txtNomTarifa, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(btnIngresarTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificarTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresarTarifa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarTarifa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecioTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTarifa))
                .addContainerGap())
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboTipoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoVehiculoActionPerformed

    private void txtPrecioTarifaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioTarifaKeyTyped

    }//GEN-LAST:event_txtPrecioTarifaKeyTyped

    private void btnIngresarTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarTarifaActionPerformed
        habilitarItems();
        deshabilitarBotones();
        setTipoOperacion(1);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresarTarifaActionPerformed

    private void btnModificarTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTarifaActionPerformed
        habilitarItems();
        deshabilitarBotones();
        setTipoOperacion(2);
    }//GEN-LAST:event_btnModificarTarifaActionPerformed

    private void btnEliminarTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTarifaActionPerformed
        habilitarItems();
        deshabilitarBotones();
        setTipoOperacion(3);
    }//GEN-LAST:event_btnEliminarTarifaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        deshabilitarItems();
        habilitarBotones();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int tipo = getTipoOperacion();
        switch (tipo) {
            case 1:
                insertarTarifa();
                habilitarBotones();
                deshabilitarItems();

                break;
            case 2:
                modificarTarifa();
                habilitarBotones();
                deshabilitarItems();
                break;
            case 3:
                eliminarTarifa();
                habilitarBotones();
                deshabilitarItems();
                break;

        }

        llenarTabla();

    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarTarifa;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIngresarTarifa;
    private javax.swing.JButton btnModificarTarifa;
    private javax.swing.JComboBox<String> comboTipoVehiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaTarifa;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNomTarifa;
    private javax.swing.JTextField txtPrecioTarifa;
    // End of variables declaration//GEN-END:variables
}
