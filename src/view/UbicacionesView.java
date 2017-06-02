/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.LugarParqueoController;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.LugarParqueo;

/**
 *
 * @author GIGABYTE
 */
public class UbicacionesView extends javax.swing.JFrame {

    private int tipoOperacion;

    /**
     * Creates new form UbicacionesView
     */
    public UbicacionesView() {
        initComponents();
        mostrarLugaresParqueo();
        setLocationRelativeTo(null);
        comboEstadoUbic.setEnabled(false);
                comboTipoUbicacion.setEnabled(false);
                spinCantUbic.setEnabled(false);
        btnGuardar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLugares = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboTipoUbicacion = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        spinCantUbic = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        comboEstadoUbic = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnCrearUbicaciones = new javax.swing.JButton();
        btnModificarUbic = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminarUbicacion = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        labelTotalUbicaciones = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaLugares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaLugares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaLugaresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaLugares);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Administrar Ubicaciones");

        jLabel3.setText("TIpo Ubicación:");

        comboTipoUbicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el tipo de Ubicacion", "CARRO", "MOTO", "CAMION", "TRACTOCAMION" }));

        jLabel4.setText("Cant. Ubicaciones:");

        spinCantUbic.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel5.setText("Ubicacion:");

        txtUbicacion.setEditable(false);

        comboEstadoUbic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el estado de la Ubicación", "DISPONIBLE", "OCUPADO" }));

        jLabel7.setText("Estado:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(spinCantUbic, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstadoUbic, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboTipoUbicacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboTipoUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(spinCantUbic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboEstadoUbic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCrearUbicaciones.setText("Crear Ubicaciones");
        btnCrearUbicaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUbicacionesActionPerformed(evt);
            }
        });

        btnModificarUbic.setText("Modificar Ubicación");
        btnModificarUbic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarUbicActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminarUbicacion.setText("Eliminar Ubicación");
        btnEliminarUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUbicacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificarUbic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrearUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCrearUbicaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarUbic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarUbicacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel6.setText("Total Ubicaciones:");

        labelEstado.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        labelEstado.setForeground(new java.awt.Color(0, 255, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTotalUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(labelTotalUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    
    
    private void seleccionTablaLLenarCampos() {
        int numIdParqueo = Integer.parseInt(String.valueOf(tablaLugares.getValueAt(tablaLugares.getSelectedRow(), 0)));
        System.out.println("NUme id parqueo: " + numIdParqueo);
        LugarParqueoController lpc = new LugarParqueoController();
        LugarParqueo le;
        le = lpc.mostrarLugarParqueo(numIdParqueo);
        txtUbicacion.setText(le.getIdLugarParqueo() + "");
        comboTipoUbicacion.setSelectedIndex(le.getIdTipoLugar());
        comboEstadoUbic.setSelectedIndex(le.getDisponibilidad());

    }
    
    private void deshabilitarInterfaz(){
        comboEstadoUbic.setEnabled(false);
                comboTipoUbicacion.setEnabled(false);
                spinCantUbic.setEnabled(false);
        btnGuardar.setEnabled(false);
    }
    
    private void habilitarInterfaz(){
        comboEstadoUbic.setEnabled(true);
                comboTipoUbicacion.setEnabled(true);
                spinCantUbic.setEnabled(true);
        btnGuardar.setEnabled(true);
    }

    private void mostrarLugaresParqueo() {
        LugarParqueoController lpc = new LugarParqueoController();
        List<LugarParqueo> lp = lpc.mostrarLugaresParqueo();
        DefaultTableModel dt = new DefaultTableModel();
        String[] cabecera = new String[]{"ID Lugar Parqueo", "ID Tipo Ubicación", "Tipo Ubicación", "Disponibilidad"};
        dt.setColumnIdentifiers(cabecera);
        tablaLugares.setModel(dt);
        try {
            dt.setNumRows(lp.size());
            for (int i = 0; i < lp.size(); i++) {
                dt.setValueAt(lp.get(i).getIdLugarParqueo(), i, 0);
                dt.setValueAt(lp.get(i).getIdTipoLugar(), i, 1);
                switch (lp.get(i).getIdTipoLugar()) {
                    case 1:
                        dt.setValueAt("CARRO", i, 2);
                        break;
                    case 2:
                        dt.setValueAt("MOTO", i, 2);
                        break;
                    case 3:
                        dt.setValueAt("CAMION", i, 2);
                        break;
                    case 4:
                        dt.setValueAt("TRACTOCAMION", i, 2);
                        break;
                }

                switch (lp.get(i).getDisponibilidad()) {
                    case 1:
                        dt.setValueAt("DISPONIBLE", i, 3);
                        break;

                    case 2:
                        dt.setValueAt("OCUPADO", i, 3);
                        break;
                }
            }
            labelTotalUbicaciones.setText(lp.size() + " Lugares de parqueo.");
        } catch (Exception e) {
            System.out.println("Error al llenar tabla: " + e);
        }

    }

    private void crearUbicaciones() {
        LugarParqueoController lpc = new LugarParqueoController();
        int cantidadUbic = (int) spinCantUbic.getValue();
        int tipo = comboTipoUbicacion.getSelectedIndex();
        System.out.println("Tipo ubica" + tipo);
        if (cantidadUbic == 0 || tipo == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una cantidad de ubicaciones a crear y su tipo de ubicacion.");
        } else if (comboEstadoUbic.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un estado para la Ubicación.");
        } else {
            for (int i = 0; i < cantidadUbic; i++) {
                JOptionPane.showMessageDialog(null, "Creación Exítosa.");
                lpc.createLugarParqueo(tipo, 1, comboEstadoUbic.getSelectedIndex());
                txtUbicacion.setText("");
                comboEstadoUbic.setSelectedIndex(0);
                comboTipoUbicacion.setSelectedIndex(0);
                spinCantUbic.setValue(0);
                tablaLugares.setRowSelectionInterval(tablaLugares.getRowCount()-1, tablaLugares.getRowCount()-1);
                mostrarLugaresParqueo();
            }
        }
    }

    private void modificarUbicaciones() {
        LugarParqueoController lpc = new LugarParqueoController();
        if ("".equals(txtUbicacion.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione el lugar o ubicación de parqueo que desee modificar.");
        } else {
            lpc.updateLugarParqueo(Integer.parseInt(txtUbicacion.getText()), comboTipoUbicacion.getSelectedIndex(), comboEstadoUbic.getSelectedIndex(), 1);
            mostrarLugaresParqueo();
            txtUbicacion.setText("");
            comboEstadoUbic.setSelectedIndex(0);
            comboTipoUbicacion.setSelectedIndex(0);
            spinCantUbic.setValue(0);
            JOptionPane.showMessageDialog(null, "Modificación Exítosa.");
        }
    }

    private void eliminarUbicacion() {
        LugarParqueoController lpc = new LugarParqueoController();
        if ("".equals(txtUbicacion.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione el lugar o ubicación de parqueo que desee modificar.");
        } else {
            lpc.eliminarLugarParqueo(Integer.parseInt(txtUbicacion.getText()));
            mostrarLugaresParqueo();
            txtUbicacion.setText("");
            comboEstadoUbic.setSelectedIndex(0);
            comboTipoUbicacion.setSelectedIndex(0);
            spinCantUbic.setValue(0);
            JOptionPane.showMessageDialog(null, "Eliminación Exítosa.");
        }
    }

    private void setTipoOperacion(int tipo) {
        this.tipoOperacion = tipo;
    }

    private int getTipoOperacion() {

        return tipoOperacion;
    }


    private void btnCrearUbicacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUbicacionesActionPerformed
        btnCrearUbicaciones.setEnabled(false);
        btnModificarUbic.setEnabled(false);
        btnEliminarUbicacion.setEnabled(false);
        btnGuardar.setEnabled(true);
        habilitarInterfaz();
        labelEstado.setText("Creando Ubicaciones.");
        setTipoOperacion(1);


    }//GEN-LAST:event_btnCrearUbicacionesActionPerformed

    private void tablaLugaresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaLugaresMouseClicked
        seleccionTablaLLenarCampos();
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaLugaresMouseClicked

    private void btnModificarUbicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUbicActionPerformed
        btnCrearUbicaciones.setEnabled(false);
        btnModificarUbic.setEnabled(false);
        btnEliminarUbicacion.setEnabled(false);
        btnGuardar.setEnabled(true);
        habilitarInterfaz();
        labelEstado.setText("Modificando Ubicación.");
        setTipoOperacion(2);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarUbicActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int tipo = getTipoOperacion();
        switch (tipo) {
            case 1:
                crearUbicaciones();
                btnCrearUbicaciones.setEnabled(true);
                btnModificarUbic.setEnabled(true);
                btnEliminarUbicacion.setEnabled(true);
                labelEstado.setText("");
                deshabilitarInterfaz();
                break;
            case 2:
                modificarUbicaciones();
                btnCrearUbicaciones.setEnabled(true);
                btnModificarUbic.setEnabled(true);
                btnEliminarUbicacion.setEnabled(true);
                labelEstado.setText("");
                deshabilitarInterfaz();
                break;
            case 3:
                eliminarUbicacion();
                btnCrearUbicaciones.setEnabled(true);
                btnModificarUbic.setEnabled(true);
                btnEliminarUbicacion.setEnabled(true);
                labelEstado.setText("");
                deshabilitarInterfaz();
                break;

        }

// TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUbicacionActionPerformed
        btnCrearUbicaciones.setEnabled(false);
        btnModificarUbic.setEnabled(false);
        btnEliminarUbicacion.setEnabled(false);
        btnGuardar.setEnabled(true);
        labelEstado.setText("Eliminando Ubicación.");
        habilitarInterfaz();
        setTipoOperacion(3);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarUbicacionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UbicacionesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UbicacionesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UbicacionesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UbicacionesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UbicacionesView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearUbicaciones;
    private javax.swing.JButton btnEliminarUbicacion;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificarUbic;
    private javax.swing.JComboBox<String> comboEstadoUbic;
    private javax.swing.JComboBox<String> comboTipoUbicacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelTotalUbicaciones;
    private javax.swing.JSpinner spinCantUbic;
    private javax.swing.JTable tablaLugares;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
