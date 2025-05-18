package itson.modales;

import itson.control.Formateador;
import itson.dtos.ActividadDTO;
import itson.dtos.LugarDTO;
import itson.dtos.ResponsableDTO;
import java.awt.Font;

public class DlgAgregarActividad extends javax.swing.JDialog {

    
    private ActividadDTO actividad;
    private ResponsableDTO responsableSeleccionado;
    private LugarDTO lugarSeleccionado;
    
    public DlgAgregarActividad(java.awt.Frame parent, boolean modal, String tituloEvento) {
        super(parent, modal);
        initComponents();
        lblEvento.setText(tituloEvento);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnVolver = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        lblNombre5 = new javax.swing.JLabel();
        lblEvento = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNombre3 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        fechaHora = new com.github.lgooddatepicker.components.DateTimePicker();
        lblNombre6 = new javax.swing.JLabel();
        jSpinnerDuracion = new javax.swing.JSpinner();
        lblNombre7 = new javax.swing.JLabel();
        jSpinnerCapacidad = new javax.swing.JSpinner();
        lblNombre9 = new javax.swing.JLabel();
        lblResponsable = new javax.swing.JLabel();
        btnBuscarOrganizador1 = new javax.swing.JButton();
        btnBuscarOrganizador2 = new javax.swing.JButton();
        lblNombre10 = new javax.swing.JLabel();
        lblLugar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(244, 246, 247));

        lblTitulo.setFont(new Font("Inter", Font.PLAIN, 36));
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Agregar Actividad");

        btnVolver.setIcon(new javax.swing.ImageIcon("C:\\Users\\pc\\Downloads\\image 4.png")); // NOI18N
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverMouseClicked(evt);
            }
        });

        btnRegistrar.setBackground(new java.awt.Color(25, 118, 210));
        btnRegistrar.setFont(new Font("Inter", Font.PLAIN, 18));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Agregar Actividad");
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblNombre.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setText("Evento: ");

        lblNombre1.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre1.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre1.setText("Nombre Actividad");

        lblNombre5.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre5.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre5.setText("Fecha y Hora");

        lblEvento.setText("Evento");
        lblEvento.setFont(new Font("Inter", Font.PLAIN, 16));
        lblEvento.setForeground(new java.awt.Color(0, 0, 0));

        txtNombre.setBackground(new java.awt.Color(244, 246, 247));
        txtNombre.setFont(new Font("Inter", Font.PLAIN, 16));
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        lblNombre3.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre3.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre3.setText("Tipo de Actividad");

        txtTipo.setBackground(new java.awt.Color(244, 246, 247));
        txtTipo.setFont(new Font("Inter", Font.PLAIN, 16));
        txtTipo.setForeground(new java.awt.Color(0, 0, 0));

        lblNombre6.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre6.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre6.setText("Duración estimada");

        jSpinnerDuracion.setFont(new Font("Inter", Font.PLAIN, 16));
        jSpinnerDuracion.setModel(new javax.swing.SpinnerNumberModel(10, null, null, 5));

        lblNombre7.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre7.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre7.setText("Capacidad Máxima");

        jSpinnerCapacidad.setFont(new Font("Inter", Font.PLAIN, 16));
        jSpinnerCapacidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblNombre9.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre9.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre9.setText("Responsable:");

        lblResponsable.setFont(new Font("Inter", Font.PLAIN, 16));
        lblResponsable.setForeground(new java.awt.Color(0, 0, 0));
        lblResponsable.setText("...");

        btnBuscarOrganizador1.setText("Buscar Responsable");
        btnBuscarOrganizador1.setBackground(new java.awt.Color(66, 165, 245));
        btnBuscarOrganizador1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarOrganizador1.setFont(new Font("Inter", Font.PLAIN, 18));
        btnBuscarOrganizador1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarOrganizador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarOrganizador1ActionPerformed(evt);
            }
        });

        btnBuscarOrganizador2.setText("Buscar Lugares");
        btnBuscarOrganizador2.setBackground(new java.awt.Color(66, 165, 245));
        btnBuscarOrganizador2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarOrganizador2.setFont(new Font("Inter", Font.PLAIN, 18));
        btnBuscarOrganizador2.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarOrganizador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarOrganizador2ActionPerformed(evt);
            }
        });

        lblNombre10.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre10.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre10.setText("Lugar");

        lblLugar.setFont(new Font("Inter", Font.PLAIN, 16));
        lblLugar.setForeground(new java.awt.Color(0, 0, 0));
        lblLugar.setText("...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscarOrganizador1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(lblNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblLugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscarOrganizador2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txtTipo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(lblEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(txtNombre))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(125, 125, 125)
                                        .addComponent(lblNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSpinnerCapacidad)
                                            .addComponent(lblNombre7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fechaHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinnerDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNombre7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerCapacidad)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarOrganizador2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarOrganizador1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnVolver)
                .addGap(65, 65, 65))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseClicked
        actividad = null;
        this.dispose();
    }//GEN-LAST:event_btnVolverMouseClicked

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnBuscarOrganizador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarOrganizador1ActionPerformed
        
    }//GEN-LAST:event_btnBuscarOrganizador1ActionPerformed

    private void btnBuscarOrganizador2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarOrganizador2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarOrganizador2ActionPerformed

    public ActividadDTO agregarActividadDTO() {
        this.actividad = null;
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        return this.actividad;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarOrganizador1;
    private javax.swing.JButton btnBuscarOrganizador2;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel btnVolver;
    private com.github.lgooddatepicker.components.DateTimePicker fechaHora;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinnerCapacidad;
    private javax.swing.JSpinner jSpinnerDuracion;
    private javax.swing.JLabel lblEvento;
    private javax.swing.JLabel lblLugar;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre10;
    private javax.swing.JLabel lblNombre3;
    private javax.swing.JLabel lblNombre5;
    private javax.swing.JLabel lblNombre6;
    private javax.swing.JLabel lblNombre7;
    private javax.swing.JLabel lblNombre9;
    private javax.swing.JLabel lblResponsable;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
