package itson.frames;

import itson.control.ControlFlujo;
import itson.control.Formateador;
import itson.dtos.ActividadDTO;
import itson.dtos.EventoDTO;
import itson.dtos.ResponsableDTO;
import itson.enums.EstadoEvento;
import itson.enums.ModalidadEvento;
import itson.excepciones.NegocioException;
import itson.fabrica.ObjetosNegocioFactory;
import itson.interfaces.IEventosBO;
import itson.modales.DlgAgregarActividad;
import itson.modales.DlgSeleccionarOrganizador;

import java.awt.Font;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class FrmRegistrarEvento extends javax.swing.JFrame {

    private ResponsableDTO responsableSeleccionado;
    private final List<ActividadDTO> actividades = new LinkedList<>();

    public FrmRegistrarEvento() {
        initComponents();
        cargarModalidades();
    }

    private void cargarModalidades() {
        ModalidadEvento[] modalidades = ModalidadEvento.values();
        for (ModalidadEvento modalidad : modalidades) {
            comboBoxModalidad.addItem(Formateador.formatearCap(modalidad.toString()));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnVolver = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblNombre4 = new javax.swing.JLabel();
        comboBoxModalidad = new javax.swing.JComboBox<>();
        lblNombre1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        lblNombre5 = new javax.swing.JLabel();
        fechaFin = new com.github.lgooddatepicker.components.DatePicker();
        fechaInicio = new com.github.lgooddatepicker.components.DatePicker();
        lblNombre6 = new javax.swing.JLabel();
        lblNombre7 = new javax.swing.JLabel();
        lblOrganizador = new javax.swing.JLabel();
        btnBuscarOrganizador = new javax.swing.JButton();
        btnAgregarActividad = new javax.swing.JButton();
        lblActividades = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(244, 246, 247));

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registrar Evento");
        lblTitulo.setFont(new Font("Inter", Font.PLAIN, 36));
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));

        btnVolver.setIcon(new javax.swing.ImageIcon("C:\\Users\\pc\\Downloads\\image 4.png")); // NOI18N
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverMouseClicked(evt);
            }
        });

        btnRegistrar.setText("Registrar Evento");
        btnRegistrar.setBackground(new java.awt.Color(25, 118, 210));
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.setFont(new Font("Inter", Font.PLAIN, 18));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblNombre.setText("Título");
        lblNombre.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));

        txtTitulo.setFont(new Font("Inter", Font.PLAIN, 16));
        txtTitulo.setBackground(new java.awt.Color(244, 246, 247));
        txtTitulo.setForeground(new java.awt.Color(0, 0, 0));

        lblNombre4.setText("Modalidad");
        lblNombre4.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre4.setForeground(new java.awt.Color(0, 0, 0));

        comboBoxModalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        comboBoxModalidad.setBackground(new java.awt.Color(244, 246, 247));
        comboBoxModalidad.setFont(new Font("Inter", Font.PLAIN, 16));
        comboBoxModalidad.setForeground(new java.awt.Color(0, 0, 0));

        lblNombre1.setText("Descripción");
        lblNombre1.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre1.setForeground(new java.awt.Color(0, 0, 0));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new Font("Inter", Font.PLAIN, 16));
        txtDescripcion.setRows(5);
        txtDescripcion.setBackground(new java.awt.Color(244, 246, 247));
        jScrollPane1.setViewportView(txtDescripcion);

        lblNombre5.setText("Fecha de Inicio");
        lblNombre5.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre5.setForeground(new java.awt.Color(0, 0, 0));

        lblNombre6.setText("Fecha Final");
        lblNombre6.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre6.setForeground(new java.awt.Color(0, 0, 0));

        lblNombre7.setText("Organizador:");
        lblNombre7.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre7.setForeground(new java.awt.Color(0, 0, 0));

        lblOrganizador.setText("...");
        lblOrganizador.setFont(new Font("Inter", Font.PLAIN, 16));
        lblOrganizador.setForeground(new java.awt.Color(0, 0, 0));

        btnBuscarOrganizador.setText("Buscar Organizadores");
        btnBuscarOrganizador.setBackground(new java.awt.Color(66, 165, 245));
        btnBuscarOrganizador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarOrganizador.setFont(new Font("Inter", Font.PLAIN, 18));
        btnBuscarOrganizador.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarOrganizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarOrganizadorActionPerformed(evt);
            }
        });

        btnAgregarActividad.setText("Agregar Actividad");
        btnAgregarActividad.setBackground(new java.awt.Color(66, 165, 245));
        btnAgregarActividad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarActividad.setFont(new Font("Inter", Font.PLAIN, 18));
        btnAgregarActividad.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActividadActionPerformed(evt);
            }
        });

        lblActividades.setText("Actividades Registradas: 0");
        lblActividades.setFont(new Font("Inter", Font.PLAIN, 16));
        lblActividades.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel2Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 90,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(35, 35, 35)
                                                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 533,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(lblNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 116,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(96, 96, 96)
                                                .addComponent(lblNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 116,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(97, 97, 97)
                                                .addComponent(lblNombre7, javax.swing.GroupLayout.PREFERRED_SIZE, 116,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(fechaInicio,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(fechaFin,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(btnAgregarActividad,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 412,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(lblActividades,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 329,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnBuscarOrganizador,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblOrganizador,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(lblNombre4,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 116,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(comboBoxModalidad, 0,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE))))
                                .addGap(35, 35, 35)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(lblNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(44, 44, 44))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblNombre6,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNombre7,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(fechaInicio,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(fechaFin,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblOrganizador,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnBuscarOrganizador, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAgregarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNombre4, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblActividades, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(28, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 525,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnVolver)
                                .addGap(65, 65, 65))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 671,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(222, 222, 222)
                                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 296,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(33, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnVolverMouseClicked
        ControlFlujo.mostrarFrame(new FrmEventos());
        this.dispose();
    }// GEN-LAST:event_btnVolverMouseClicked

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRegistrarActionPerformed
        if (validarCampos()) {
            EventoDTO evento = new EventoDTO(
                    null,
                    txtTitulo.getText(),
                    txtDescripcion.getText(),
                    toCalendar(fechaInicio.getDate()),
                    toCalendar(fechaFin.getDate()),
                    EstadoEvento.PLANEADO,
                    ModalidadEvento.valueOf(comboBoxModalidad.getSelectedItem().toString().toUpperCase()),
                    null,
                    responsableSeleccionado.getId());
            try{
                IEventosBO eventosBO = ObjetosNegocioFactory.crearEventosBO();
                eventosBO.guardarEventoConActividades(evento, actividades);
                JOptionPane.showMessageDialog(this, "Evento registrado exitosamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                ControlFlujo.mostrarFrame(new FrmEventos());
                this.dispose();
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, "Error al registrar el evento: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_btnRegistrarActionPerformed

    private Calendar toCalendar(LocalDate date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        return calendar;
    }

    private void btnBuscarOrganizadorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBuscarOrganizadorActionPerformed
        DlgSeleccionarOrganizador modalSeleccionador = new DlgSeleccionarOrganizador(this, true,
                DlgSeleccionarOrganizador.OPCION_EVENTO);
        this.responsableSeleccionado = modalSeleccionador.obtenerResponsableSeleccionado();
        if (this.responsableSeleccionado != null) {
            this.lblOrganizador.setText(Formateador.formatearNombre(responsableSeleccionado.getNombre(),
                    responsableSeleccionado.getApellidoPaterno(), responsableSeleccionado.getApellidoMaterno()));
        }
    }// GEN-LAST:event_btnBuscarOrganizadorActionPerformed

    private void btnAgregarActividadActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarActividadActionPerformed
        if (txtTitulo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título de la actividad no puede estar vacío", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        DlgAgregarActividad dlgAgregarActividad = new DlgAgregarActividad(this, true, txtTitulo.getText());
        ActividadDTO actividad = dlgAgregarActividad.agregarActividadDTO();
        if (actividad != null) {
            JOptionPane.showMessageDialog(this, "Actividad agregada exitosamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            this.actividades.add(actividad);
            lblActividades.setText("Actividades Registradas: " + actividades.size());
        }
    }// GEN-LAST:event_btnAgregarActividadActionPerformed

    private boolean validarCampos() {
        if (txtTitulo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La descripción no puede estar vacía", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fechaInicio.getDate() == null) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no puede estar vacía", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fechaFin.getDate() == null) {
            JOptionPane.showMessageDialog(this, "La fecha de fin no puede estar vacía", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (comboBoxModalidad.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una modalidad", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fechaInicio.getDate().isAfter(fechaFin.getDate())) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la fecha de fin", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (responsableSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un organizador", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarActividad;
    private javax.swing.JButton btnBuscarOrganizador;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel btnVolver;
    private javax.swing.JComboBox<String> comboBoxModalidad;
    private com.github.lgooddatepicker.components.DatePicker fechaFin;
    private com.github.lgooddatepicker.components.DatePicker fechaInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblActividades;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre4;
    private javax.swing.JLabel lblNombre5;
    private javax.swing.JLabel lblNombre6;
    private javax.swing.JLabel lblNombre7;
    private javax.swing.JLabel lblOrganizador;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
