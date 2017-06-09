/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.FacturaController;
import controller.LugarParqueoController;
import controller.RegistroParqueoController;
import controller.ReportesController;
import controller.VehiculoController;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.FacturaDetalle;
import model.LugarParqueo;
import model.RegistroParqueo;
import model.Vehiculo;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author GIGABYTE
 */
public class LugarParqueoView extends javax.swing.JFrame {

    private static boolean iniciado = false;

    /**
     * Creates new form LugarParqueo
     */
    public LugarParqueoView() {
        apariencia();
        initComponents();
        mostrarLugaresParqueo();
        mostrarTodosLosVehiculos();
        mostrarRegistroFactura();
        mostrarRegistroParqueo();
        deshabilitarRegistro();
        importe();
        placaParte2.setOpaque(false);
        placaParte1.setOpaque(false);
        btnFacturar.setEnabled(false);
        reloj();
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
        javax.swing.Timer timer = new javax.swing.Timer(1000, (java.awt.event.ActionEvent ae) -> {
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
        System.out.println("Fecha timestamp: " + fechaRegistro);
        RegistroParqueoController rpc = new RegistroParqueoController();
        RegistroParqueo rp;
        rp = rpc.mostrarRegistroParqueo(placa, fechaRegistro);
        System.out.println("RegistroParqueo: " + placa + fechaRegistro);
        System.out.println("Id registro encontrado " + (rp.getIdLugarParqueo()));
        txtIdRegistro.setText("" + (rp.getIdRegistroParqueo()));
        txtPlaca.setText("" + (rp.getPlacaVehiculo()));
        txtFechaEntradaFactura.setText("" + (rp.getFechaHoraEntrada()));
        txtUbicacionSalida.setText("" + rp.getIdLugarParqueo());
        FacturaController fc = new FacturaController();
        int hora = fc.calcularTiempoHoras(regParq, placa);
        int minuto = fc.calcularTiempoMinutos(regParq, placa);
        double precio = fc.precioSalida(regParq, placa);
        txtCantidaHoras.setText(hora + "");
        txtCantidadMinuto.setText(minuto + "");
        txtPrecio.setText(precio + "");
        txtFechaSalidaFactura.setText(fechaHoraHoy());
        System.out.println("Diferencia: " + fc.calcularDiferenciaTiempo(regParq, placa));
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

    public void mostrarLugaresParqueo() {
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

    private void mostrarRegistroFactura() {
        FacturaController fc = new FacturaController();
        List<FacturaDetalle> l = fc.verFacturaDetalle();
        DefaultTableModel dtf = new DefaultTableModel();
        String[] cabeceraFactura = new String[]{"ID Factura", "ID Registro Parqueo", "Fecha Factura", "Fecha Entrada", "Fecha Salida", "Duración", "Duración (Horas)", "Duración (Minutos)", "Placa Vehiculo", "Tipo Vehiculo", "Ubicación", "Precio Total"};
        dtf.setColumnIdentifiers(cabeceraFactura);

        dtf.setNumRows(l.size());
        tablaRegistrosFactura.setModel(dtf);
        for (int i = 0; i < l.size(); i++) {
            dtf.setValueAt(l.get(i).getIdFactura(), i, 0);
            dtf.setValueAt(l.get(i).getIdRegistroParqueo(), i, 1);
            dtf.setValueAt(l.get(i).getFechaFactura(), i, 2);
            dtf.setValueAt(l.get(i).getFechaHoraEntrada(), i, 3);
            dtf.setValueAt(l.get(i).getFechaSalida(), i, 4);
            dtf.setValueAt(l.get(i).getDuracion(), i, 5);
            dtf.setValueAt(l.get(i).getHoras(), i, 6);
            dtf.setValueAt(l.get(i).getMinutos(), i, 7);
            dtf.setValueAt(l.get(i).getPlacaVehiculo(), i, 8);
            dtf.setValueAt(l.get(i).getTipoVehTxt(), i, 9);
            dtf.setValueAt(l.get(i).getIdLugarParqueo(), i, 10);
            dtf.setValueAt(l.get(i).getPrecio(), i, 11);

        }

    }

    private void mostrarRegistroParqueo() {
        RegistroParqueoController rpc = new RegistroParqueoController();
        List<RegistroParqueo> rpl = rpc.mostrarRegistroParqueo();
        DefaultTableModel dtf = new DefaultTableModel();
        String[] cabeceraRegistros = new String[]{"ID Regist. Parq.", "Fecha/Hora Entrada", "Placa Veh.", "Tipo Vehiculo", "Lugar Parqueo", "Estado"};
        dtf.setColumnIdentifiers(cabeceraRegistros);
        dtf.setNumRows(rpl.size());

        tablaRegistro.setModel(dtf);
        tablaRegistro.setDefaultEditor(Object.class, null);
        try {
            for (int i = 0; i < rpl.size(); i++) {
                dtf.setValueAt(rpl.get(i).getIdRegistroParqueo(), i, 0);
                dtf.setValueAt(rpl.get(i).getFechaHoraEntrada(), i, 1);
                dtf.setValueAt(rpl.get(i).getPlacaVehiculo(), i, 2);
                dtf.setValueAt(rpl.get(i).getTipoVehiculo(), i, 3);
                dtf.setValueAt(rpl.get(i).getIdLugarParqueo(), i, 4);
                dtf.setValueAt(rpl.get(i).getEstadoRegistro(), i, 5);
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

    private void importe() {
        FacturaController fc = new FacturaController();
        labelImporte.setText("Importe Total: $" + fc.importeTotal());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaRegistrosFactura = new javax.swing.JTable();
        btnIngresarVehiculo = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        Master = new javax.swing.JPanel();
        datosBasicosVehiculo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaEntradaFactura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUbicacionSalida = new javax.swing.JTextField();
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
        btnSalida = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtIdRegistro = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboTipoVehiculo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        btnRegistrarVehiculo = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtFechaHoraEntrada = new javax.swing.JTextField();
        placaParte2 = new javax.swing.JTextField();
        placaParte1 = new javax.swing.JTextField();
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
        cerrar = new javax.swing.JButton();
        btnMostrarFactura = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labelFechaHora = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        labelImporte = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        btnInformeVehReg = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setOpaque(false);

        jScrollPane5.setBorder(null);

        tablaRegistrosFactura.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaRegistrosFactura.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jScrollPane5.setViewportView(tablaRegistrosFactura);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 557, 1090, 120));

        btnIngresarVehiculo.setForeground(new java.awt.Color(102, 102, 102));
        btnIngresarVehiculo.setText("Ingresar Vehiculo");
        btnIngresarVehiculo.setBorder(null);
        btnIngresarVehiculo.setBorderPainted(false);
        btnIngresarVehiculo.setContentAreaFilled(false);
        btnIngresarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarVehiculoActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, 130, 20));

        btnFacturar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnFacturar.setForeground(new java.awt.Color(102, 102, 102));
        btnFacturar.setText("Facturar");
        btnFacturar.setBorder(null);
        btnFacturar.setBorderPainted(false);
        btnFacturar.setContentAreaFilled(false);
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });
        getContentPane().add(btnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 120, 31));

        jPanel5.setOpaque(false);

        Master.setOpaque(false);

        datosBasicosVehiculo.setOpaque(false);
        datosBasicosVehiculo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 235, 235));
        jLabel2.setText("Fecha/Hora Entr.: ");
        datosBasicosVehiculo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 128, -1, 20));

        txtFechaEntradaFactura.setEditable(false);
        txtFechaEntradaFactura.setBorder(null);
        txtFechaEntradaFactura.setOpaque(false);
        txtFechaEntradaFactura.setPreferredSize(new java.awt.Dimension(100, 20));
        txtFechaEntradaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaEntradaFacturaActionPerformed(evt);
            }
        });
        datosBasicosVehiculo.add(txtFechaEntradaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 120, -1));

        jLabel3.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("Ubicación:");
        datosBasicosVehiculo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 20));

        txtUbicacionSalida.setEditable(false);
        txtUbicacionSalida.setBorder(null);
        txtUbicacionSalida.setOpaque(false);
        txtUbicacionSalida.setPreferredSize(new java.awt.Dimension(100, 20));
        txtUbicacionSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUbicacionSalidaActionPerformed(evt);
            }
        });
        datosBasicosVehiculo.add(txtUbicacionSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 130, -1));

        jLabel6.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(235, 235, 235));
        jLabel6.setText("Fecha/Hora Sal.: ");
        datosBasicosVehiculo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 160, -1, 20));

        txtFechaSalidaFactura.setEditable(false);
        txtFechaSalidaFactura.setBorder(null);
        txtFechaSalidaFactura.setOpaque(false);
        txtFechaSalidaFactura.setPreferredSize(new java.awt.Dimension(100, 20));
        datosBasicosVehiculo.add(txtFechaSalidaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 120, -1));

        txtPlaca.setEditable(false);
        txtPlaca.setBorder(null);
        txtPlaca.setEnabled(false);
        txtPlaca.setOpaque(false);
        txtPlaca.setPreferredSize(new java.awt.Dimension(100, 20));
        datosBasicosVehiculo.add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 130, 10));

        jLabel7.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(235, 235, 235));
        jLabel7.setText("Placa Vehiculo:");
        datosBasicosVehiculo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 96, -1, 20));

        jLabel8.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(235, 235, 235));
        jLabel8.setText("Duración Parqueo:");
        datosBasicosVehiculo.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 20));

        txtDuracion.setEditable(false);
        txtDuracion.setBorder(null);
        txtDuracion.setEnabled(false);
        txtDuracion.setOpaque(false);
        txtDuracion.setPreferredSize(new java.awt.Dimension(100, 20));
        txtDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDuracionActionPerformed(evt);
            }
        });
        datosBasicosVehiculo.add(txtDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 120, -1));

        jLabel9.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(235, 235, 235));
        jLabel9.setText("Cantidad Horas:");
        datosBasicosVehiculo.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        txtCantidaHoras.setEditable(false);
        txtCantidaHoras.setBorder(null);
        txtCantidaHoras.setEnabled(false);
        txtCantidaHoras.setOpaque(false);
        txtCantidaHoras.setPreferredSize(new java.awt.Dimension(100, 20));
        datosBasicosVehiculo.add(txtCantidaHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 130, -1));

        jLabel10.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(235, 235, 235));
        jLabel10.setText("Cantidad Minutos:");
        datosBasicosVehiculo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        txtCantidadMinuto.setEditable(false);
        txtCantidadMinuto.setBorder(null);
        txtCantidadMinuto.setEnabled(false);
        txtCantidadMinuto.setOpaque(false);
        txtCantidadMinuto.setPreferredSize(new java.awt.Dimension(100, 20));
        datosBasicosVehiculo.add(txtCantidadMinuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 120, 20));

        btnSalida.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSalida.setForeground(new java.awt.Color(102, 102, 102));
        btnSalida.setText("Salida de Vehiculo");
        btnSalida.setBorder(null);
        btnSalida.setBorderPainted(false);
        btnSalida.setContentAreaFilled(false);
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });
        datosBasicosVehiculo.add(btnSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 15, 130, 20));

        jLabel14.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(235, 235, 235));
        jLabel14.setText("Precio Total:");
        datosBasicosVehiculo.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, 17));

        txtPrecio.setEditable(false);
        txtPrecio.setBorder(null);
        txtPrecio.setOpaque(false);
        txtPrecio.setPreferredSize(new java.awt.Dimension(100, 20));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        datosBasicosVehiculo.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 120, -1));

        jLabel17.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(235, 235, 235));
        jLabel17.setText("ID Reg. Parqueo:");
        datosBasicosVehiculo.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 64, -1, 20));

        txtIdRegistro.setEditable(false);
        txtIdRegistro.setBorder(null);
        txtIdRegistro.setOpaque(false);
        txtIdRegistro.setPreferredSize(new java.awt.Dimension(100, 20));
        txtIdRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdRegistroActionPerformed(evt);
            }
        });
        datosBasicosVehiculo.add(txtIdRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 120, 10));

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 235, 235));
        jLabel1.setText("Tipo de Vehiculo:");

        comboTipoVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccion el tipo de Vehiculo.", "Carro", "Moto", "Camion", "TractoCamion" }));
        comboTipoVehiculo.setOpaque(false);
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

        jLabel5.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(235, 235, 235));
        jLabel5.setText("Ubicación:");

        txtUbicacion.setEditable(false);
        txtUbicacion.setBorder(null);
        txtUbicacion.setOpaque(false);

        btnRegistrarVehiculo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRegistrarVehiculo.setForeground(new java.awt.Color(102, 102, 102));
        btnRegistrarVehiculo.setText("Registrar Vehiculo");
        btnRegistrarVehiculo.setBorder(null);
        btnRegistrarVehiculo.setBorderPainted(false);
        btnRegistrarVehiculo.setContentAreaFilled(false);
        btnRegistrarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVehiculoActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(235, 235, 235));
        jLabel12.setText("Fecha/Hora Entrada: ");

        txtFechaHoraEntrada.setEditable(false);
        txtFechaHoraEntrada.setBorder(null);
        txtFechaHoraEntrada.setOpaque(false);

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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                placaParte2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                placaParte2KeyTyped(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(placaParte1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(placaParte2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboTipoVehiculo, 0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnRegistrarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placaParte1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(placaParte2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(comboTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(txtFechaHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        panelDisponibles.setOpaque(false);

        jScrollPane1.setOpaque(false);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDisponiblesLayout.setVerticalGroup(
            panelDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDisponiblesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        InformacionTablas.addTab("Ubicaciones Disponibles", panelDisponibles);

        panelResgistros.setOpaque(false);

        jScrollPane2.setOpaque(false);

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
        tablaRegistro.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelResgistrosLayout.setVerticalGroup(
            panelResgistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResgistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        InformacionTablas.addTab("Registro Parqueo", panelResgistros);

        jPanel3.setOpaque(false);

        jScrollPane4.setOpaque(false);

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
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBusquedaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarVehiculo))
                            .addComponent(btnMostrarTodosVehiculos))
                        .addGap(0, 104, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMostrarTodosVehiculos)
                .addGap(130, 130, 130))
        );

        InformacionTablas.addTab("Vehiculos Registrador", jPanel3);

        cerrar.setBorderPainted(false);
        cerrar.setContentAreaFilled(false);
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MasterLayout = new javax.swing.GroupLayout(Master);
        Master.setLayout(MasterLayout);
        MasterLayout.setHorizontalGroup(
            MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MasterLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MasterLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(datosBasicosVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(InformacionTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MasterLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        MasterLayout.setVerticalGroup(
            MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MasterLayout.createSequentialGroup()
                .addGroup(MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MasterLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MasterLayout.createSequentialGroup()
                        .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(MasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(MasterLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(InformacionTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MasterLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(datosBasicosVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Master, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Master, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, 460));

        btnMostrarFactura.setText("Mostar Factura");
        btnMostrarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarFacturaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrarFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 690, -1, -1));

        jPanel2.setOpaque(false);

        labelFechaHora.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        labelFechaHora.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Fecha:");

        labelFecha.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        labelFecha.setForeground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Hora:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(labelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(labelFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(labelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 690, 460, -1));

        labelImporte.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        labelImporte.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(labelImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 700, 260, 20));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ResourcesVisual/parking2.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, 1420, 960));

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));
        jMenuBar1.setBorder(null);
        jMenuBar1.setBorderPainted(false);

        jMenu1.setText("Archivo");

        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Administracion");

        jMenuItem1.setText("Administrar Lugares");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Administrar Tarifas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jCheckBoxMenuItem1.setText("TIpo de Vehiculo");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Informes");

        jMenuItem5.setText("Informe Facturacion Total");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem4.setText("Informe Registros de Parqueo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        btnInformeVehReg.setText("Informe Vehiculos Registrados");
        btnInformeVehReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformeVehRegActionPerformed(evt);
            }
        });
        jMenu3.add(btnInformeVehReg);

        jMenuItem7.setText("Informes por Fecha");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

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
        InformacionTablas.setSelectedIndex(0);
        RegistroParqueoController rcp = new RegistroParqueoController();
        String placaValidas = placaParte1.getText() + placaParte2.getText();
        boolean enparqueo = rcp.buscarRegistroParqueoEstado(placaValidas);
        if (("").equals(placaParte1.getText()) || ("").equals(placaParte2.getText())) {
            JOptionPane.showMessageDialog(null, "Digite la placa");
        } else if (comboTipoVehiculo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el tipo de Vehiculo.");
        } else if ("".equals(txtUbicacion.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una ubicación.");

        } else if (enparqueo == true) {
            JOptionPane.showMessageDialog(null, "La placa del vehiculo ingresado aun esta en el parqueadero.");
        } else {
            VehiculoController vc = new VehiculoController();
            String placa = (placaParte1.getText() + placaParte2.getText());
            int tipo = comboTipoVehiculo.getSelectedIndex();
            vc.createVehiculo(placa, tipo);
            RegistroParqueoController rpc = new RegistroParqueoController();
            rpc.createRegistroParqueo(placa, Integer.parseInt(txtUbicacion.getText()));
            LugarParqueoController lpc = new LugarParqueoController();
            lpc.updateDispoLugarParqueo(Integer.parseInt(txtUbicacion.getText()), 2);
            JOptionPane.showMessageDialog(null, "Regístro Exitoso.");
            System.out.println("VAriable parqueo: " + enparqueo);

        }
        deshabilitarRegistro();
        mostrarLugaresParqueo();
        mostrarRegistroParqueo();
        mostrarTodosLosVehiculos();

    }//GEN-LAST:event_btnIngresarVehiculoActionPerformed

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed
        if (txtIdRegistro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione el registro de parqueo que requiere facturar.");
            InformacionTablas.setSelectedIndex(1);
        } else {
            btnFacturar.setEnabled(true);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalidaActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed

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
        if (iniciado == false) {
            uv.setVisible(true);
            iniciado = true;
        } else {
            uv.setAlwaysOnTop(true);
            uv.toFront();
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed

        if (txtPlaca.getText().equals("") || txtIdRegistro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el registro de parqueo.");
        } else {
            FacturaController fc = new FacturaController();
            int id = Integer.parseInt(txtIdRegistro.getText());
            String plac = txtPlaca.getText();
            int lugPar = Integer.parseInt(txtUbicacionSalida.getText());
            if (JOptionPane.showConfirmDialog(null, "Desea Realizar la factura.", "Facturar Parqueo", JOptionPane.YES_NO_CANCEL_OPTION) == 1) {

            } else {
                double precio = fc.precioSalida(id, plac);
                if (fc.createFactura(plac, id, lugPar, precio) == true) {
                    JOptionPane.showMessageDialog(null, "Este registro ya esta facturado.", "Error Facturación.", JOptionPane.WARNING_MESSAGE);
                    mostrarRegistroFactura();
                } else {
                    ReportesController rc = new ReportesController();
                    rc.reporteFactura(id);
                    JOptionPane.showMessageDialog(null, "Se ha facturado el registro de parqueo No. " + txtIdRegistro.getText() + "\n" + "Placa Vehiculo" + txtPlaca.getText());
                    RegistroParqueoController rcp = new RegistroParqueoController();
                    rcp.actualizarEstadoRegistro(id, plac, "FACTURADO");
                    LugarParqueoController lpc = new LugarParqueoController();
                    lpc.updateDispoLugarParqueo(lugPar, 1);
                    mostrarRegistroFactura();
                    importe();

                }

            }

            //    fc.precioSalida(PROPERTIES, placa)
        }
        mostrarRegistroFactura();
        mostrarLugaresParqueo();
        mostrarTodosLosVehiculos();
        mostrarRegistroParqueo();
        btnFacturar.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void tablaRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRegistroMouseClicked
        int row = tablaRegistro.getSelectedRow();
        String valor = String.valueOf(tablaRegistro.getValueAt(row, 5));
        if (!valor.equals("FACTURADO")) {
            seleccionTablaLLenarCamposFactura();
        }
    }//GEN-LAST:event_tablaRegistroMouseClicked

    private void txtUbicacionSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUbicacionSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacionSalidaActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        TarifaView tf = new TarifaView();
        tf.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        ReportesController rpc = new ReportesController();
        rpc.reporteRegistrosParqueoTotal();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        TipoLugarView tl = new TipoLugarView();
        tl.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void placaParte2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_placaParte2KeyReleased
        VehiculoController vc = new VehiculoController();
        String placa = placaParte1.getText() + placaParte2.getText();
        try {
            Vehiculo v = vc.buscarVehiculoPorPlaca(placa);
            int seleccion = v.getIdtipoVehiculo();

            comboTipoVehiculo.setSelectedIndex(seleccion);
        } catch (Exception e) {
            comboTipoVehiculo.setSelectedIndex(0);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_placaParte2KeyReleased

    private void txtIdRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdRegistroActionPerformed

    private void txtFechaEntradaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaEntradaFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaEntradaFacturaActionPerformed

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_cerrarActionPerformed

    private void txtDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDuracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDuracionActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnMostrarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarFacturaActionPerformed
        ReportesController rc = new ReportesController();
        if (tablaRegistrosFactura.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Para ver la factura de un registro. Seleccione un registro de factura de la tabla.");
        } else {
            rc.reporteFactura((int) tablaRegistrosFactura.getValueAt(tablaRegistrosFactura.getSelectedRow(), 1));

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMostrarFacturaActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        ReportesController rc = new ReportesController();
        rc.reporteFacturasTotal();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void btnInformeVehRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeVehRegActionPerformed
        ReportesController rc = new ReportesController();
        rc.reporteVehiculosRegistrados();

    }//GEN-LAST:event_btnInformeVehRegActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ReportesView rv = new ReportesView();
        rv.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * @param args the command line arguments
     */
    /* public static void main(String args[]) {
        /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    private void apariencia() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LugarParqueoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
    }
    /* Create and display the form */
 /*   java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LugarParqueoView().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JTabbedPane InformacionTablas;
    private javax.swing.JPanel Master;
    private javax.swing.JButton btnBuscarVehiculo;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JMenuItem btnInformeVehReg;
    private javax.swing.JButton btnIngresarVehiculo;
    private javax.swing.JButton btnMostrarFactura;
    private javax.swing.JButton btnMostrarTodosVehiculos;
    private javax.swing.JButton btnRegistrarVehiculo;
    private javax.swing.JButton btnSalida;
    private javax.swing.JButton cerrar;
    private javax.swing.JComboBox<String> comboTipoVehiculo;
    private javax.swing.JPanel datosBasicosVehiculo;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelFechaHora;
    private javax.swing.JLabel labelImporte;
    private javax.swing.JPanel panelDisponibles;
    private javax.swing.JPanel panelResgistros;
    private javax.swing.JTextField placaParte1;
    private javax.swing.JTextField placaParte2;
    private javax.swing.JTable tablaLugaresDisponibles;
    private javax.swing.JTable tablaRegistro;
    private javax.swing.JTable tablaRegistrosFactura;
    private javax.swing.JTable tablaTodosVehiculos;
    private javax.swing.JTextField txtBusquedaVehiculo;
    private javax.swing.JTextField txtCantidaHoras;
    private javax.swing.JTextField txtCantidadMinuto;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtFechaEntradaFactura;
    private javax.swing.JTextField txtFechaHoraEntrada;
    private javax.swing.JTextField txtFechaSalidaFactura;
    private javax.swing.JTextField txtIdRegistro;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtUbicacion;
    private javax.swing.JTextField txtUbicacionSalida;
    // End of variables declaration//GEN-END:variables
}
