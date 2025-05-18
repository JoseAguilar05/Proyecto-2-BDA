package itson.frames;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.JOptionPane;

import itson.control.ControlFlujo;
import itson.dtos.ActividadDTO;
import itson.dtos.EventoDTO;
import itson.excepciones.NegocioException;
import itson.fabrica.ObjetosNegocioFactory;
import itson.interfaces.IActividadesBO;
import itson.interfaces.IEventosBO;
import itson.modales.DlgAgregarActividad;
import paneles.PnlActividad;

public class FrmActividades extends javax.swing.JFrame {

    private EventoDTO evento;
    private int opcion;

    public static final int OPCION_VER = 1;
    public static final int OPCION_MODIFICAR = 2;
    public static final int OPCION_ASOCIAR = 3;

    public FrmActividades(EventoDTO evento, int opcion) {
        this.evento = evento;
        this.opcion = opcion;
        initComponents();
        cargarActividades();
        setTitle("Actividades del Evento");
        lblTituloEvento.setText(evento.getTitulo());
        cargarOpcion();
    }

    private void cargarActividades() {
        try {
            IEventosBO eventosBO = ObjetosNegocioFactory.crearEventosBO();
            this.evento = eventosBO.buscarEventoPorId(evento.getId());
            boxPnlActividades.removeAll();
            for (Integer idActividad : evento.getIdsActividades()) {
                IActividadesBO actividadesBO = ObjetosNegocioFactory.crearActividadesBO();
                ActividadDTO actividad = actividadesBO.buscarActividadPorId(idActividad);
                if (actividad != null) {
                    PnlActividad actividadPanel = new PnlActividad(actividad, opcion);
                    boxPnlActividades.add(actividadPanel);
                    boxPnlActividades.add(Box.createVerticalStrut(15));
                } else {
                    JOptionPane.showMessageDialog(this, "Actividad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            boxPnlActividades.revalidate();
            boxPnlActividades.repaint();
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar actividades: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarOpcion() {
        switch (opcion) {
            case OPCION_VER:
                btnAgregarActividad.setVisible(false);
                break;
            case OPCION_MODIFICAR:
                btnAgregarActividad.setVisible(true);
                break;
            case OPCION_ASOCIAR:
                btnAgregarActividad.setVisible(false);
                break;
            default:
                throw new IllegalArgumentException("Opción no válida: " + opcion);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnVolver = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblTituloEvento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        boxPnlActividades = new javax.swing.JPanel();
        btnAgregarActividad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(244, 246, 247));

        lblTitulo.setFont(new Font("Inter", Font.PLAIN, 36));
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Actividades");

        btnVolver.setIcon(new javax.swing.ImageIcon("C:\\Users\\pc\\Downloads\\image 4.png")); // NOI18N
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblNombre.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setText("Evento:");

        lblTituloEvento.setFont(new Font("Inter", Font.PLAIN, 16));
        lblTituloEvento.setForeground(new java.awt.Color(0, 0, 0));
        lblTituloEvento.setText("Evento");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        boxPnlActividades.setBackground(new java.awt.Color(255, 255, 255));
        boxPnlActividades.setLayout(new javax.swing.BoxLayout(boxPnlActividades, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(boxPnlActividades);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(lblTituloEvento, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        502, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(25, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTituloEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(16, Short.MAX_VALUE)));

        btnAgregarActividad.setBackground(new java.awt.Color(66, 165, 245));
        btnAgregarActividad.setFont(new Font("Inter", Font.PLAIN, 18));
        btnAgregarActividad.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarActividad.setText("Agregar Nueva Actividad");
        btnAgregarActividad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActividadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 525,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnVolver)
                                .addGap(65, 65, 65))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(btnAgregarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 412,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(176, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)));
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
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

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
        if (opcion == OPCION_VER) {
            ControlFlujo.mostrarFrame(new FrmInfoEvento(evento, FrmInfoEvento.OPCION_VER));
            this.dispose();
        } else if (opcion == OPCION_MODIFICAR) {
            ControlFlujo.mostrarFrame(new FrmInfoEvento(evento, FrmInfoEvento.OPCION_EDITAR));
            this.dispose();
        } else if (opcion == OPCION_ASOCIAR) {
            ControlFlujo.mostrarFrame(new FrmParticipantes());
            this.dispose();
        }
    }// GEN-LAST:event_btnVolverMouseClicked

    private void btnAgregarActividadActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarActividadActionPerformed
        DlgAgregarActividad dlgAgregarActividad = new DlgAgregarActividad(this, true, evento.getTitulo());
        ActividadDTO actividad = dlgAgregarActividad.agregarActividadDTO();
        if (actividad != null) {
            try {
                IActividadesBO actividadesBO = ObjetosNegocioFactory.crearActividadesBO();
                actividad.setIdEvento(evento.getId());
                actividadesBO.crearActividad(actividad);
                JOptionPane.showMessageDialog(this, "Actividad agregada exitosamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarActividades();
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, "Error al agregar actividad: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_btnAgregarActividadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boxPnlActividades;
    private javax.swing.JButton btnAgregarActividad;
    private javax.swing.JLabel btnVolver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloEvento;
    // End of variables declaration//GEN-END:variables
}
