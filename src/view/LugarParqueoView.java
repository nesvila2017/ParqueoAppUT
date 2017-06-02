/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.FacturaController;
import controller.LugarParqueoController;
import controller.RegistroParqueoController;
import controller.VehiculoController;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.LugarParqueo;
import model.RegistroParqueo;
import model.Vehiculo;

/**
 *
 * @author GIGABYTE
 */
public class LugarParqueoView extends javax.swing.JFrame {

    /**
     * Creates new form LugarParqueo
     */
    public LugarParqueoView() {
        initComponents();
        mostrarLugaresParqueo();
        mostrarTodosLosVehiculos();
        mostrarRegistroParqueo();
        deshabilitarRegistro();
        placaParte2.setOpaque(false);
        placaParte1.setOpaque(false);
        reloj();
        //fechaHoraHoy();
    }

    private void habilitarRegistro() {
        placaParte1.setEnabled(true);
        placaParte2.setEnabled(true);
        comboTipoVehiculo.setEnabled(true);
        btnIngresarVehiculo.setEnabled(true);
        btnRegistrarVehiculo.setEnabled(false);
    }

    private void deshabilitarRegistro() {
        placaParte1.setEnabled(false);
        placaParte2.setEnabled(false);
        comboTipoVehiculo.setEnabled(false);
        btnIngresarVehiculo.setEnabled(false);
        btnRegistrarVehiculo.setEnabled(true);
        placaParte1.setText("");
        placaParte2.setText("");
        txtFechaHoraEntrada.setText("");
        comboTipoVehiculo.setSelectedIndex(0);
        txtUbicacion.setText("");
    }

    private void reloj() {
        Calendar calendario = new java.util.GregorianCalendar();
        //int segundos = calendario.get(Calendar.SECOND);
        javax.swing.Timer timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                java.util.Date actual = new java.util.Date();
                calendario.setTime(actual);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);
                int mes = (calendario.get(Calendar.MONTH) + 1);
                int año = calendario.get(Calendar.YEAR);
                int hora = calendario.get(Calendar.HOUR_OF_DAY);
                int minutos = calendario.get(Calendar.MINUTE);
                int segundos = calendario.get(Calendar.SECOND);
                String hour = String.format("%02d : %02d : %02d", hora, minutos, segundos);
                String date = String.format("%02d / %02d / %02d", dia, mes, año);
                labelFechaHora.setText(hour);
                labelFecha.setText(date);
            }
        });
        timer.start();
    }

    private void seleccionTablaLLenarCamposRegistro() {
        int numIdParqueo = Integer.parseInt(String.valueOf(tablaLugaresDisponibles.getValueAt(tablaLugaresDisponibles.getSelectedRow(), 0)));
        if (comboTipoVehiculo.getSelectedIndex() == 0) {

        } else {
            System.out.println("NUme id parqueo: " + numIdParqueo);
            LugarParqueoController lpc = new LugarParqueoController();
            LugarParqueo le;
            le = lpc.mostrarLugarParqueo(numIdParqueo);
            txtUbicacion.setText(le.getIdLugarParqueo() + "");
        }

    }
    
    private void seleccionTablaLLenarCamposFactura() {
        
        int regParq = (int) (tablaRegistro.getValueAt(tablaRegistro.getSelectedRow(), 0));
        String placa = String.valueOf(tablaRegistro.getValueAt(tablaRegistro.getSelectedRow(), 2));
        Timestamp fechaRegistro;
        fechaRegistro = (Timestamp) tablaRegistro.getValueAt(tablaRegistro.getSelectedRow(), 1);
        System.out.println("Fecha timestamp: "+ fechaRegistro);
        RegistroParqueoController rpc = new RegistroParqueoController();
        RegistroParqueo rp;
            rp = rpc.mostrarRegistroParqueo(placa, fechaRegistro);
            System.out.println("RegistroParqueo: " + placa + fechaRegistro);
            System.out.println("Id registro encontrado "+ (rp.getIdLugarParqueo()));
            txtIdRegistro.setText(""+(rp.getIdRegistroParqueo()));
            txtPlaca.setText(""+(rp.getPlacaVehiculo()));
            txtFechaEntradaFactura.setText(""+(rp.getFechaHoraEntrada()));
            txtUbicacionSalida.setText(""+rp.getIdLugarParqueo());
            FacturaController fc = new FacturaController();
            int hora = fc.calcularTiempoHoras(regParq, placa);
            int minuto = fc.calcularTiempoMinutos(regParq, placa);
            double precio = fc.precioSalida(regParq, placa);
            txtCantidaHoras.setText(hora+"");
            txtCantidadMinuto.setText(minuto+"");
            txtPrecio.setText(precio+"");
            txtFechaSalidaFactura.setText(fechaHoraHoy());
            System.out.println("Diferencia: "+ fc.calcularDiferenciaTiempo(regParq, placa));
            txtDuracion.setText(String.valueOf(fc.calcularDiferenciaTiempo(regParq, placa)));
            
            
            
        

    }

    private void mostrarLugaresParqueoDisponibles(int tipoLugar) {
        LugarParqueoController lpc = new LugarParqueoController();
        List<LugarParqueo> lp = lpc.mostrarLugaresParqueoTipoDispoTipoLugar(1, tipoLugar);
        DefaultTableModel dt = new DefaultTableModel();
        String[] cabecera = new String[]{"ID Lugar Parqueo", "Tipo Ubicación", "Disponibilidad"};
        dt.setColumnIdentifiers(cabecera);
        tablaLugaresDisponibles.setModel(dt);
        try {
            dt.setNumRows(lp.size());
            for (int i = 0; i < lp.size(); i++) {
                dt.setValueAt(lp.get(i).getIdLugarParqueo(), i, 0);
                //dt.setValueAt(lp.get(i).getIdTipoLugar(), i, 1);

                switch (lp.get(i).getIdTipoLugar()) {
                    case 1:
                        dt.setValueAt("CARRO", i, 1);
                        break;
                    case 2:
                        dt.setValueAt("MOTO", i, 1);
                        break;
                    case 3:
                        dt.setValueAt("CAMION", i, 1);
                        break;
                    case 4:
                        dt.setValueAt("TRACTOCAMION", i, 1);
                        break;
                }

                switch (lp.get(i).getDisponibilidad()) {
                    case 1:
                        dt.setValueAt("DISPONIBLE", i, 2);
                        break;

                    case 2:
                        dt.setValueAt("OCUPADO", i, 2);
                        break;
                }

            }
        } catch (Exception e) {
            System.out.println("Error al llenar tabla: " + e);
        }

    }

    private void mostrarLugaresParqueo() {
        LugarParqueoController lpc = new LugarParqueoController();
        List<LugarParqueo> lp = lpc.mostrarLugaresParqueo();
        DefaultTableModel dt = new DefaultTableModel();
        String[] cabecera = new String[]{"ID Lugar Parqueo", "ID Tipo Ubicación", "Tipo Ubicación", "Disponibilidad"};
        dt.setColumnIdentifiers(cabecera);
        tablaLugaresDisponibles.setModel(dt);
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
        } catch (Exception e) {
            System.out.println("Error al llenar tabla: " + e);
        }

    }

    private void mostrarTodosLosVehiculos() {
        VehiculoController vc = new VehiculoController();
        List<Vehiculo> listaVehiculos = vc.mostrarTodosLosVehiculosRegistrados();
        DefaultTableModel dtf = new DefaultTableModel();
        String[] cabeceraVehiculo = new String[]{"Placa Vehiculo", "Tipo de Vehiculo"};
        dtf.setColumnIdentifiers(cabeceraVehiculo);
        tablaTodosVehiculos.setModel(dtf);
        dtf.setNumRows(listaVehiculos.size());

        try {
            for (int i = 0; i < listaVehiculos.size(); i++) {
                dtf.setValueAt(listaVehiculos.get(i).getPlaca(), i, 0);
                switch (listaVehiculos.get(i).getIdtipoVehiculo()) {
                    case 1:
                        dtf.setValueAt("CARRO", i, 1);
                        break;
                    case 2:
                        dtf.setValueAt("MOTO", i, 1);
                        break;
                    case 3:
                        dtf.setValueAt("CAMION", i, 1);
                        break;
                    case 4:
                        dtf.setValueAt("TRACTOCAMION", i, 1);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo listar los vehiculos registrados: " + e);
        }

    }

    private void mostrarRegistroParqueo() {
        RegistroParqueoController rpc = new RegistroParqueoController();
        List<RegistroParqueo> rpl = rpc.mostrarRegistroParqueo();
        DefaultTableModel dtf = new DefaultTableModel();
        String[] cabeceraRegistros = new String[]{"ID Regist. Parq.", "Fecha/Hora Entrada", "Placa Veh.", "Lugar Parqueo"};
        dtf.setColumnIdentifiers(cabeceraRegistros);
        dtf.setNumRows(rpl.size());
        
        tablaRegistro.setModel(dtf);
        tablaRegistro.setDefaultEditor(Object.class, null);
        try {
            for (int i = 0; i < rpl.size(); i++) {
                dtf.setValueAt(rpl.get(i).getIdRegistroParqueo(), i, 0);
                dtf.setValueAt(rpl.get(i).getFechaHoraEntrada(), i, 1);
                dtf.setValueAt(rpl.get(i).getPlacaVehiculo(), i, 2);
                dtf.setValueAt(rpl.get(i).getIdLugarParqueo(), i, 3);

            }
        } catch (Exception e) {
            System.out.println("No se pudo mostrar los registros de parqueo: " + e);
        }
    }

    private String fechaHoraHoy() {

        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String time = sdf.format(cal.getTime());
            System.out.println(sdf.format(cal.getTime()));
            return time;
        } catch (Exception ex) {
            System.out.println("Error al mostrar hora actual en LugarparqueoView (Horashoy:) " + ex);
        }
        return null;
    }

    private void calcularPrecio() {
        FacturaController fc = new FacturaController();
        String placa1 = placaParte1.getText();
        String placa2 = placaParte2.getText();
        if ("".equals(placaParte1.getText()) || "".equals(placaParte2.getText())) {
            JOptionPane.showMessageDialog(null, "Digite la placa completa.");
        } else {
        }
        double precio = fc.precioSalida(1, placa1 + placa2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Master = new javax.swing.JPanel();
        datosBasicosVehiculo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaEntradaFactura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUbicacionSalida = new javax.swing.JTextField();
        btnFacturar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtFechaSalidaFactura = new javax.swing.JTextField();
        txtPlaca = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCantidaHoras = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCantidadMinuto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFraccion = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtIdRegistro = new javax.swing.JTextField();
        InformacionTablas = new javax.swing.JTabbedPane();
        panelDisponibles = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLugaresDisponibles = new javax.swing.JTable();
        panelResgistros = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaRegistro = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaTodosVehiculos = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtBusquedaVehiculo = new javax.swing.JTextField();
        btnBuscarVehiculo = new javax.swing.JButton();
        btnMostrarTodosVehiculos = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        placaPanel = new javax.swing.JPanel();
        placaParte1 = new javax.swing.JTextField();
        placaParte2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboTipoVehiculo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        btnIngresarVehiculo = new javax.swing.JButton();
        btnRegistrarVehiculo = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtFechaHoraEntrada = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        labelFechaHora = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Master.setOpaque(false);

        datosBasicosVehiculo.setOpaque(false);

        jLabel2.setText("Fecha/Hora Entrada: ");

        txtFechaEntradaFactura.setEditable(false);

        jLabel3.setText("Ubicación:");

        txtUbicacionSalida.setEditable(false);

        btnFacturar.setText("Facturar");
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        jLabel6.setText("Fecha/Hora Salida: ");

        txtFechaSalidaFactura.setEditable(false);

        txtPlaca.setEditable(false);
        txtPlaca.setEnabled(false);

        jLabel7.setText("Placa Vehiculo:");

        jLabel8.setText("Duración Parqueo:");

        txtDuracion.setEditable(false);
        txtDuracion.setEnabled(false);

        jLabel9.setText("Cantidad Horas:");

        txtCantidaHoras.setEditable(false);
        txtCantidaHoras.setEnabled(false);

        jLabel10.setText("Cantidad Minutos:");

        txtCantidadMinuto.setEditable(false);
        txtCantidadMinuto.setEnabled(false);

        jLabel11.setText("Fraccion/Hora Consumida:");

        txtFraccion.setEditable(false);

        jButton2.setText("Salida de Vehiculo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel14.setText("Precio Total:");

        txtPrecio.setEditable(false);
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        jLabel17.setText("ID Registro Parqueo:");

        txtIdRegistro.setEditable(false);

        javax.swing.GroupLayout datosBasicosVehiculoLayout = new javax.swing.GroupLayout(datosBasicosVehiculo);
        datosBasicosVehiculo.setLayout(datosBasicosVehiculoLayout);
        datosBasicosVehiculoLayout.setHorizontalGroup(
            datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosBasicosVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datosBasicosVehiculoLayout.createSequentialGroup()
                        .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosBasicosVehiculoLayout.createSequentialGroup()
                        .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(datosBasicosVehiculoLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnFacturar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(datosBasicosVehiculoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFraccion))
                            .addGroup(datosBasicosVehiculoLayout.createSequentialGroup()
                                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaSalidaFactura)
                                    .addComponent(txtDuracion)
                                    .addComponent(txtUbicacionSalida)
                                    .addComponent(txtCantidaHoras)
                                    .addComponent(txtCantidadMinuto)
                                    .addComponent(txtPlaca)
                                    .addComponent(txtFechaEntradaFactura)
                                    .addComponent(txtIdRegistro)))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))))
        );
        datosBasicosVehiculoLayout.setVerticalGroup(
            datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosBasicosVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtIdRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(11, 11, 11)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaEntradaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaSalidaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUbicacionSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCantidaHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCantidadMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtFraccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosBasicosVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelDisponibles.setOpaque(false);

        tablaLugaresDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaLugaresDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaLugaresDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaLugaresDisponibles);

        javax.swing.GroupLayout panelDisponiblesLayout = new javax.swing.GroupLayout(panelDisponibles);
        panelDisponibles.setLayout(panelDisponiblesLayout);
        panelDisponiblesLayout.setHorizontalGroup(
            panelDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDisponiblesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDisponiblesLayout.setVerticalGroup(
            panelDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDisponiblesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        InformacionTablas.addTab("Ubicaciones Disponibles", panelDisponibles);

        tablaRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRegistroMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaRegistro);

        javax.swing.GroupLayout panelResgistrosLayout = new javax.swing.GroupLayout(panelResgistros);
        panelResgistros.setLayout(panelResgistrosLayout);
        panelResgistrosLayout.setHorizontalGroup(
            panelResgistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResgistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelResgistrosLayout.setVerticalGroup(
            panelResgistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResgistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        InformacionTablas.addTab("Registro Parqueo", panelResgistros);

        tablaTodosVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tablaTodosVehiculos);

        jLabel16.setText("Placa del Vehiculo");

        btnBuscarVehiculo.setText("Buscar Vehiculo");
        btnBuscarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVehiculoActionPerformed(evt);
            }
        });

        btnMostrarTodosVehiculos.setText("Mostrar Todos Los Vehiculos");
        btnMostrarTodosVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodosVehiculosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusquedaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarVehiculo))
                    .addComponent(btnMostrarTodosVehiculos))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtBusquedaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarVehiculo))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMostrarTodosVehiculos)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        InformacionTablas.addTab("Vehiculos Registrador", jPanel3);

        placaPanel.setOpaque(false);

        placaParte1.setFont(new java.awt.Font("Arial", 0, 50)); // NOI18N
        placaParte1.setForeground(new java.awt.Color(0, 0, 0));
        placaParte1.setAutoscrolls(false);
        placaParte1.setBorder(null);
        placaParte1.setCaretColor(new java.awt.Color(0, 0, 0));
        placaParte1.setOpaque(false);
        placaParte1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                placaParte1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                placaParte1KeyTyped(evt);
            }
        });

        placaParte2.setFont(new java.awt.Font("Arial", 0, 55)); // NOI18N
        placaParte2.setForeground(new java.awt.Color(0, 0, 0));
        placaParte2.setBorder(null);
        placaParte2.setOpaque(false);
        placaParte2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placaParte2ActionPerformed(evt);
            }
        });
        placaParte2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                placaParte2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                placaParte2KeyTyped(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ResourcesVisual/placa.png"))); // NOI18N
        jLabel4.setToolTipText("");

        javax.swing.GroupLayout placaPanelLayout = new javax.swing.GroupLayout(placaPanel);
        placaPanel.setLayout(placaPanelLayout);
        placaPanelLayout.setHorizontalGroup(
            placaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placaPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(placaParte1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(placaParte2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(placaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(placaPanelLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        placaPanelLayout.setVerticalGroup(
            placaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placaPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(placaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(placaParte1)
                    .addComponent(placaParte2))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(placaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(placaPanelLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel1.setText("Tipo de Vehiculo:");

        comboTipoVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccion el tipo de Vehiculo.", "Carro", "Moto", "Camion", "TractoCamion" }));
        comboTipoVehiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTipoVehiculoItemStateChanged(evt);
            }
        });
        comboTipoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoVehiculoActionPerformed(evt);
            }
        });

        jLabel5.setText("Ubicación:");

        txtUbicacion.setEditable(false);

        btnIngresarVehiculo.setText("Ingresar Vehiculo");
        btnIngresarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarVehiculoActionPerformed(evt);
            }
        });

        btnRegistrarVehiculo.setText("Registrar Vehiculo");
        btnRegistrarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVehiculoActionPerformed(evt);
            }
        });

        jLabel12.setText("Fecha/Hora Entrada: ");

        txtFechaHoraEntrada.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(placaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnIngresarVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel12))
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtUbicacion, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFechaHoraEntrada)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnRegistrarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegistrarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIngresarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        labelFechaHora.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        labelFechaHora.setForeground(new java.awt.Color(102, 102, 102));

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Fecha:");

        labelFecha.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        labelFecha.setForeground(new java.awt.Color(102, 102, 102));

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Hora:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(labelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(129, 129, 129))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(labelFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MasterLayout = new javax.swing.GroupLayout(Master);
        Master.setLayout(MasterLayout);
        MasterLayout.setHorizontalGroup(
            MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MasterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MasterLayout.createSequentialGroup()
                        .addComponent(datosBasicosVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(83, 83, 83)
                .addComponent(InformacionTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(MasterLayout.createSequentialGroup()
                .addGap(409, 409, 409)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MasterLayout.setVerticalGroup(
            MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MasterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MasterLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datosBasicosVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(InformacionTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Administracion");

        jMenuItem1.setText("Administrar Lugares");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Master, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Master, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void placaParte1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_placaParte1KeyPressed

    }//GEN-LAST:event_placaParte1KeyPressed

    private void placaParte2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placaParte2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placaParte2ActionPerformed

    private void placaParte2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_placaParte2KeyPressed


    }//GEN-LAST:event_placaParte2KeyPressed

    private void placaParte1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_placaParte1KeyTyped
        String texto = placaParte1.getText();
        texto = texto.toUpperCase();
        placaParte1.setText(texto);
        if (placaParte1.getText().length() == 3) {
            evt.consume();

        } else {
            char c = evt.getKeyChar();

            if (Character.isDigit(c)) {
                getToolkit().beep();
                evt.consume();
            }
        }


    }//GEN-LAST:event_placaParte1KeyTyped

    private void placaParte2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_placaParte2KeyTyped
        if (placaParte2.getText().length() == 3) {
            evt.consume();

        }
    }//GEN-LAST:event_placaParte2KeyTyped

    private void comboTipoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoVehiculoActionPerformed

    private void btnRegistrarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVehiculoActionPerformed
        habilitarRegistro();
        txtFechaHoraEntrada.setText(fechaHoraHoy());
        placaParte1.requestFocus();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarVehiculoActionPerformed

    private void comboTipoVehiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTipoVehiculoItemStateChanged
        if (comboTipoVehiculo.getSelectedIndex() == 0) {

        } else {
            mostrarLugaresParqueoDisponibles(comboTipoVehiculo.getSelectedIndex());
            txtUbicacion.setText("");

        }
    }//GEN-LAST:event_comboTipoVehiculoItemStateChanged

    private void tablaLugaresDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaLugaresDisponiblesMouseClicked
        seleccionTablaLLenarCamposRegistro();
    }//GEN-LAST:event_tablaLugaresDisponiblesMouseClicked

    private void btnIngresarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarVehiculoActionPerformed
        if (("").equals(placaParte1.getText()) || ("").equals(placaParte2.getText())) {
            JOptionPane.showMessageDialog(null, "Digite la placa");
        } else if (comboTipoVehiculo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el tipo de Vehiculo.");
        } else if ("".equals(txtUbicacion.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una ubicación.");
        } else {
            VehiculoController vc = new VehiculoController();
            String placa = (placaParte1.getText() + placaParte2.getText());
            int tipo = comboTipoVehiculo.getSelectedIndex();
            vc.createVehiculo(placa, tipo);
            RegistroParqueoController rpc = new RegistroParqueoController();
            rpc.createRegistroParqueo(placa, Integer.parseInt(txtUbicacion.getText()));
            LugarParqueoController lpc = new LugarParqueoController();
            lpc.updateDispoLugarParqueo(Integer.parseInt(txtUbicacion.getText()), 1);
            JOptionPane.showMessageDialog(null, "Regístro Exitoso.");
            deshabilitarRegistro();
            mostrarLugaresParqueo();
            mostrarRegistroParqueo();
            mostrarTodosLosVehiculos();

        }


    }//GEN-LAST:event_btnIngresarVehiculoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void btnBuscarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVehiculoActionPerformed
        String placa = txtBusquedaVehiculo.getText();
        VehiculoController vc = new VehiculoController();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarVehiculoActionPerformed

    private void btnMostrarTodosVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodosVehiculosActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_btnMostrarTodosVehiculosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        UbicacionesView uv = new UbicacionesView();
        uv.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        deshabilitarRegistro();


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
         if (txtPlaca.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el registro de parqueo.");
        }else{
             FacturaController fc = new FacturaController();
         //    fc.precioSalida(PROPERTIES, placa)
         }
   

        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void tablaRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRegistroMouseClicked
        seleccionTablaLLenarCamposFactura();
    }//GEN-LAST:event_tablaRegistroMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LugarParqueoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LugarParqueoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LugarParqueoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LugarParqueoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LugarParqueoView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane InformacionTablas;
    private javax.swing.JPanel Master;
    private javax.swing.JButton btnBuscarVehiculo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnIngresarVehiculo;
    private javax.swing.JButton btnMostrarTodosVehiculos;
    private javax.swing.JButton btnRegistrarVehiculo;
    private javax.swing.JComboBox<String> comboTipoVehiculo;
    private javax.swing.JPanel datosBasicosVehiculo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelFechaHora;
    private javax.swing.JPanel panelDisponibles;
    private javax.swing.JPanel panelResgistros;
    private javax.swing.JPanel placaPanel;
    private javax.swing.JTextField placaParte1;
    private javax.swing.JTextField placaParte2;
    private javax.swing.JTable tablaLugaresDisponibles;
    private javax.swing.JTable tablaRegistro;
    private javax.swing.JTable tablaTodosVehiculos;
    private javax.swing.JTextField txtBusquedaVehiculo;
    private javax.swing.JTextField txtCantidaHoras;
    private javax.swing.JTextField txtCantidadMinuto;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtFechaEntradaFactura;
    private javax.swing.JTextField txtFechaHoraEntrada;
    private javax.swing.JTextField txtFechaSalidaFactura;
    private javax.swing.JTextField txtFraccion;
    private javax.swing.JTextField txtIdRegistro;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtUbicacion;
    private javax.swing.JTextField txtUbicacionSalida;
    // End of variables declaration//GEN-END:variables
}
