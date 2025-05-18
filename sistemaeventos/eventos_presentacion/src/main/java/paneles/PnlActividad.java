package paneles;

import java.awt.Font;

public class PnlActividad extends javax.swing.JPanel {

    /**
     * Creates new form PnlActividad
     */
    public PnlActividad() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEstado = new javax.swing.JLabel();
        lblModalidad = new javax.swing.JLabel();
        lblEstado1 = new javax.swing.JLabel();
        lblModalidad1 = new javax.swing.JLabel();
        lblEstado2 = new javax.swing.JLabel();
        lblModalidad2 = new javax.swing.JLabel();
        lblEstado3 = new javax.swing.JLabel();
        lblModalidad3 = new javax.swing.JLabel();
        btnGenerarListaDeAsistencias = new javax.swing.JButton();
        btnMarcarFinalizada = new javax.swing.JButton();
        btnGenerarListaDeAsistencias1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 246, 247));

        lblEstado.setFont(new Font("Inter", Font.PLAIN, 14));
        lblEstado.setForeground(new java.awt.Color(0, 0, 0));
        lblEstado.setText("Tipo:");

        lblModalidad.setFont(new Font("Inter", Font.PLAIN, 14));
        lblModalidad.setForeground(new java.awt.Color(0, 0, 0));
        lblModalidad.setText("Actividad:");

        lblEstado1.setFont(new Font("Inter", Font.PLAIN, 14));
        lblEstado1.setForeground(new java.awt.Color(0, 0, 0));
        lblEstado1.setText("Capacidad máxima: ");

        lblModalidad1.setFont(new Font("Inter", Font.PLAIN, 14));
        lblModalidad1.setForeground(new java.awt.Color(0, 0, 0));
        lblModalidad1.setText("Ponente:");

        lblEstado2.setFont(new Font("Inter", Font.PLAIN, 14));
        lblEstado2.setForeground(new java.awt.Color(0, 0, 0));
        lblEstado2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblEstado2.setText("Ocupación: ");

        lblModalidad2.setFont(new Font("Inter", Font.PLAIN, 14));
        lblModalidad2.setForeground(new java.awt.Color(0, 0, 0));
        lblModalidad2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblModalidad2.setText("Estado:");

        lblEstado3.setFont(new Font("Inter", Font.PLAIN, 14));
        lblEstado3.setForeground(new java.awt.Color(0, 0, 0));
        lblEstado3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblEstado3.setText("Duración (mins)");

        lblModalidad3.setFont(new Font("Inter", Font.PLAIN, 14));
        lblModalidad3.setForeground(new java.awt.Color(0, 0, 0));
        lblModalidad3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblModalidad3.setText("Fecha Inicio:");

        btnGenerarListaDeAsistencias.setBackground(new java.awt.Color(66, 165, 245));
        btnGenerarListaDeAsistencias.setFont(new Font("Inter", Font.PLAIN, 18));
        btnGenerarListaDeAsistencias.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarListaDeAsistencias.setText("Generar Lista de Asistencias");
        btnGenerarListaDeAsistencias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarListaDeAsistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarListaDeAsistenciasActionPerformed(evt);
            }
        });

        btnMarcarFinalizada.setBackground(new java.awt.Color(66, 165, 245));
        btnMarcarFinalizada.setFont(new Font("Inter", Font.PLAIN, 18));
        btnMarcarFinalizada.setForeground(new java.awt.Color(255, 255, 255));
        btnMarcarFinalizada.setText("Marcar como Finalizada");
        btnMarcarFinalizada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMarcarFinalizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarFinalizadaActionPerformed(evt);
            }
        });

        btnGenerarListaDeAsistencias1.setBackground(new java.awt.Color(66, 165, 245));
        btnGenerarListaDeAsistencias1.setFont(new Font("Inter", Font.PLAIN, 18));
        btnGenerarListaDeAsistencias1.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarListaDeAsistencias1.setText("Eliminar");
        btnGenerarListaDeAsistencias1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarListaDeAsistencias1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarListaDeAsistencias1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGenerarListaDeAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGenerarListaDeAsistencias1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMarcarFinalizada, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblEstado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblModalidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblEstado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblModalidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblEstado3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblModalidad3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblModalidad3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEstado3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblModalidad2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEstado2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblModalidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblModalidad1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEstado1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarListaDeAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMarcarFinalizada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarListaDeAsistencias1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarListaDeAsistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarListaDeAsistenciasActionPerformed
        //TODO: Implementar la lógica para generar la lista de asistencias
    }//GEN-LAST:event_btnGenerarListaDeAsistenciasActionPerformed

    private void btnMarcarFinalizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarFinalizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMarcarFinalizadaActionPerformed

    private void btnGenerarListaDeAsistencias1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarListaDeAsistencias1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarListaDeAsistencias1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarListaDeAsistencias;
    private javax.swing.JButton btnGenerarListaDeAsistencias1;
    private javax.swing.JButton btnMarcarFinalizada;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblEstado1;
    private javax.swing.JLabel lblEstado2;
    private javax.swing.JLabel lblEstado3;
    private javax.swing.JLabel lblModalidad;
    private javax.swing.JLabel lblModalidad1;
    private javax.swing.JLabel lblModalidad2;
    private javax.swing.JLabel lblModalidad3;
    // End of variables declaration//GEN-END:variables
}
