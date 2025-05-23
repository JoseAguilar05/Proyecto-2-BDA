package itson.frames;

import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import itson.control.ControlFlujo;
import itson.dtos.ParticipanteDTO;
import itson.enums.TipoParticipante;
import itson.excepciones.NegocioException;
import itson.fabrica.ObjetosNegocioFactory;
import itson.interfaces.IParticipantesBO;
import itson.paneles.PnlParticipante;

public class FrmParticipantes extends javax.swing.JFrame {

    private final List<PnlParticipante> panelesParticipantes = new LinkedList<>();

    /**
     * Creates new form FrmParticipantes
     */
    public FrmParticipantes() {
        initComponents();
        cargarTipos();
        cargarDocumentos();
        cargarParticipantes();
    }

    private void cargarParticipantes() {
        IParticipantesBO participantesBO = ObjetosNegocioFactory.crearParticipantesBO();
        try {
            List<ParticipanteDTO> participantes = participantesBO.obtenerParticipantes();
            cargarParticipantes(participantes);
        } catch (NegocioException e) {
            System.out.println("Error al cargar los participantes: " + e.getMessage());
        }
    }

    private void cargarParticipantes(List<ParticipanteDTO> participantes) {
        boxPnlParticipantes.removeAll();
        panelesParticipantes.clear();
        for (ParticipanteDTO participante : participantes) {
            PnlParticipante participantePanel = new PnlParticipante(participante);
            boxPnlParticipantes.add(participantePanel);
            boxPnlParticipantes.add(javax.swing.Box.createVerticalStrut(10));
            panelesParticipantes.add(participantePanel);
        }
        boxPnlParticipantes.revalidate();
        boxPnlParticipantes.repaint();
    }

    private void cargarTipos() {
        TipoParticipante[] tipos = TipoParticipante.values();
        for (TipoParticipante tipo : tipos) {
            String nombre = tipo.toString();
            String formateado = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
            comboBoxTipo.addItem(formateado);

        }
    }

    private void cargarDocumentos() {
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        };
        txtBuscar.getDocument().addDocumentListener(listener);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlBotones = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        lblTitulo2 = new javax.swing.JLabel();
        comboBoxTipo = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        btnVolver = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnAsociar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        scrollPaneParticipantes = new javax.swing.JScrollPane();
        boxPnlParticipantes = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(244, 246, 247));

        lblTitulo.setFont(new Font("Inter", Font.PLAIN, 36));
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Participantes");

        pnlBotones.setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo1.setFont(new Font("Inter", Font.PLAIN, 18));
        lblTitulo1.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo1.setText("Filtros de búsqueda");

        lblTitulo2.setFont(new Font("Inter", Font.PLAIN, 18));
        lblTitulo2.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo2.setText("Tipo de participante");

        comboBoxTipo.setBackground(new java.awt.Color(244, 246, 247));
        comboBoxTipo.setFont(new Font("Inter", Font.PLAIN, 16));
        comboBoxTipo.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        comboBoxTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTipoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTitulo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(comboBoxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setFont(new Font("Inter", Font.PLAIN, 16));
        txtBuscar.setForeground(new java.awt.Color(100, 100, 100));
        txtBuscar.setText("Buscar...");
        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtBuscarMouseExited(evt);
            }
        });

        btnVolver.setIcon(new javax.swing.ImageIcon("C:\\Users\\pc\\Downloads\\image 4.png")); // NOI18N
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverMouseClicked(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(66, 165, 245));
        btnNuevo.setFont(new Font("Inter", Font.PLAIN, 18));
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo Participante");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAsociar.setBackground(new java.awt.Color(66, 165, 245));
        btnAsociar.setFont(new Font("Inter", Font.PLAIN, 18));
        btnAsociar.setForeground(new java.awt.Color(255, 255, 255));
        btnAsociar.setText("Asociar a Actividad");
        btnAsociar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAsociar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsociarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        scrollPaneParticipantes.setBorder(null);
        scrollPaneParticipantes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        boxPnlParticipantes.setBackground(new java.awt.Color(255, 255, 255));
        boxPnlParticipantes.setLayout(new javax.swing.BoxLayout(boxPnlParticipantes, javax.swing.BoxLayout.Y_AXIS));
        scrollPaneParticipantes.setViewportView(boxPnlParticipantes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneParticipantes))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(scrollPaneParticipantes, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnVolver)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAsociar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAsociar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
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

    private void comboBoxTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTipoItemStateChanged
        if(evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            buscar();
        }
    }//GEN-LAST:event_comboBoxTipoItemStateChanged

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        ControlFlujo.mostrarFrame(new FrmRegistrarParticipante());
        this.dispose();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAsociarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsociarActionPerformed
        List<ParticipanteDTO> participantesSeleccionados = new LinkedList<>();
        for(PnlParticipante panel : panelesParticipantes) {
            if(panel.isSeleccionado()) {
                participantesSeleccionados.add(panel.getParticipante());
            }
        }
        if(participantesSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se han seleccionado participantes", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ControlFlujo.mostrarFrame(new FrmEventos(participantesSeleccionados));
            this.dispose();
        }
    }//GEN-LAST:event_btnAsociarActionPerformed

    private boolean seleccionado = false;

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_txtBuscarMouseClicked
        seleccionado = true;
        txtBuscar.setText("");
        txtBuscar.setForeground(new java.awt.Color(0, 0, 0));
    }// GEN-LAST:event_txtBuscarMouseClicked

    private void txtBuscarMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_txtBuscarMouseEntered
        if (!seleccionado) {
            txtBuscar.setText("");
        }
    }// GEN-LAST:event_txtBuscarMouseEntered

    private void txtBuscarMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_txtBuscarMouseExited
        if (!seleccionado) {
            txtBuscar.setText("Buscar...");
        }
    }// GEN-LAST:event_txtBuscarMouseExited

    private void btnVolverMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnVolverMouseClicked
        ControlFlujo.iniciarFlujo();
        this.dispose();
    }// GEN-LAST:event_btnVolverMouseClicked

    private void buscar() {
        String filtro = txtBuscar.getText();
        String tipo = comboBoxTipo.getSelectedItem().toString();
        IParticipantesBO participantesBO = ObjetosNegocioFactory.crearParticipantesBO();
        try {
            List<ParticipanteDTO> participantes;
            if (tipo.equals("Seleccionar...")) {
                if(filtro.equals("Buscar...") || filtro.isEmpty()) {
                    participantes = participantesBO.obtenerParticipantes();
                } else {
                    participantes = participantesBO.obtenerParticipantesConFiltro(filtro);
                }
            } else {
                if(filtro.equals("Buscar...") || filtro.isEmpty()) {
                    participantes = participantesBO.obtenerParticipantesPorTipo(tipo);
                } else {
                    participantes = participantesBO.obtenerParticipantesConFiltroYTipo(filtro, tipo);
                }
            }
            cargarParticipantes(participantes);
        } catch (NegocioException e) {
            System.out.println("Error al buscar participantes: " + e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boxPnlParticipantes;
    private javax.swing.JButton btnAsociar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel btnVolver;
    private javax.swing.JComboBox<String> comboBoxTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JScrollPane scrollPaneParticipantes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
