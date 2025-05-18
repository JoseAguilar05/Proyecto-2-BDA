package paneles;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import itson.control.ControlFlujo;
import itson.control.Formateador;
import itson.dtos.ActividadDTO;
import itson.dtos.EventoDTO;
import itson.dtos.ResponsableDTO;
import itson.enums.EstadoActividad;
import itson.excepciones.NegocioException;
import itson.fabrica.ObjetosNegocioFactory;
import itson.frames.FrmActividades;
import itson.interfaces.IActividadesBO;
import itson.interfaces.IEventosBO;
import itson.interfaces.IResponsablesBO;

public class PnlActividad extends javax.swing.JPanel {

    private ActividadDTO actividad;
    private int opcion;

    public static final int OPCION_VER = 1;
    public static final int OPCION_MODIFICAR = 2;
    public static final int OPCION_ASOCIAR = 3;

    public PnlActividad(ActividadDTO actividad, int opcion) {
        this.actividad = actividad;
        this.opcion = opcion;
        initComponents();
        cargarDatosActividad();
        cargarOpcion();
    }

    private void cargarDatosActividad() {
        lblNombre.setText("Actividad: " + actividad.getNombre());
        lblTipo.setText("Tipo: " + Formateador.formatearCap(actividad.getTipoActividad().toString()));
        lblCapacidad.setText("Capacidad máxima: " + actividad.getCapacidadMaxima());
        IResponsablesBO responsablesBO = ObjetosNegocioFactory.crearResponsablesBO();
        try {
            ResponsableDTO responsable = responsablesBO.obtenerResponsablePorId(actividad.getResponsableId());
            lblPonente.setText("Ponente: " + responsable.getNombre() + " " + responsable.getApellidoPaterno() + " "
                    + responsable.getApellidoMaterno());
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener el responsable: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        int porcentajeOcupacion = (int) ((actividad.getParticipantesIds().size()
                / (double) actividad.getCapacidadMaxima()) * 100);
        lblOcupacion.setText("Ocupación: " + porcentajeOcupacion + "%");
        lblEstado.setText("Estado: " + Formateador.formatearCap(actividad.getEstado().toString()));
        lblDuracion.setText("Duración (mins): " + actividad.getDuracionEstimada());
        lblFechaInicio.setText("Fecha Inicio: " + Formateador.formatearFechaHora(actividad.getFechaInicio()));
    }

    private void cargarOpcion() {
        if (actividad.getEstado() == EstadoActividad.FINALIZADA) {
            btnMarcarFinalizada.setVisible(false);
        }
        switch (opcion) {
            case OPCION_VER:
                btnGenerarListaDeAsistencias.setVisible(false);
                btnMarcarFinalizada.setVisible(false);
                btnEliminar.setVisible(false);
                break;
            case OPCION_MODIFICAR:
                break;
            case OPCION_ASOCIAR:
                btnGenerarListaDeAsistencias.setVisible(false);
                btnMarcarFinalizada.setVisible(false);
                btnEliminar.setVisible(false);
                break;
            default:
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTipo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCapacidad = new javax.swing.JLabel();
        lblPonente = new javax.swing.JLabel();
        lblOcupacion = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        btnGenerarListaDeAsistencias = new javax.swing.JButton();
        btnMarcarFinalizada = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 246, 247));
        setMaximumSize(new java.awt.Dimension(621, 184));
        setMinimumSize(new java.awt.Dimension(621, 184));

        lblTipo.setFont(new Font("Inter", Font.PLAIN, 14));
        lblTipo.setForeground(new java.awt.Color(0, 0, 0));
        lblTipo.setText("Tipo:");

        lblNombre.setFont(new Font("Inter", Font.PLAIN, 14));
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setText("Actividad:");

        lblCapacidad.setFont(new Font("Inter", Font.PLAIN, 14));
        lblCapacidad.setForeground(new java.awt.Color(0, 0, 0));
        lblCapacidad.setText("Capacidad máxima: ");

        lblPonente.setFont(new Font("Inter", Font.PLAIN, 14));
        lblPonente.setForeground(new java.awt.Color(0, 0, 0));
        lblPonente.setText("Ponente:");

        lblOcupacion.setFont(new Font("Inter", Font.PLAIN, 14));
        lblOcupacion.setForeground(new java.awt.Color(0, 0, 0));
        lblOcupacion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblOcupacion.setText("Ocupación: ");

        lblEstado.setFont(new Font("Inter", Font.PLAIN, 14));
        lblEstado.setForeground(new java.awt.Color(0, 0, 0));
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblEstado.setText("Estado:");

        lblDuracion.setFont(new Font("Inter", Font.PLAIN, 14));
        lblDuracion.setForeground(new java.awt.Color(0, 0, 0));
        lblDuracion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblDuracion.setText("Duración (mins)");

        lblFechaInicio.setFont(new Font("Inter", Font.PLAIN, 14));
        lblFechaInicio.setForeground(new java.awt.Color(0, 0, 0));
        lblFechaInicio.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblFechaInicio.setText("Fecha Inicio:");

        btnGenerarListaDeAsistencias.setBackground(new java.awt.Color(66, 165, 245));
        btnGenerarListaDeAsistencias.setFont(new Font("Inter", Font.PLAIN, 14));
        btnGenerarListaDeAsistencias.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarListaDeAsistencias.setText("Generar Lista de Asistencias");
        btnGenerarListaDeAsistencias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarListaDeAsistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarListaDeAsistenciasActionPerformed(evt);
            }
        });

        btnMarcarFinalizada.setBackground(new java.awt.Color(66, 165, 245));
        btnMarcarFinalizada.setFont(new Font("Inter", Font.PLAIN, 14));
        btnMarcarFinalizada.setForeground(new java.awt.Color(255, 255, 255));
        btnMarcarFinalizada.setText("Marcar como Finalizada");
        btnMarcarFinalizada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMarcarFinalizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarFinalizadaActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(66, 165, 245));
        btnEliminar.setFont(new Font("Inter", Font.PLAIN, 14));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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
                                                .addComponent(btnGenerarListaDeAsistencias,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 224,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnMarcarFinalizada,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 223,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(lblCapacidad,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(lblPonente,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(lblTipo,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(lblNombre,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(lblOcupacion,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(lblEstado,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(lblDuracion,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(lblFechaInicio,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(22, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout
                                                .createSequentialGroup()
                                                .addComponent(lblFechaInicio)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblDuracion)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblEstado)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblOcupacion))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblNombre)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblTipo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblPonente)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblCapacidad)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnGenerarListaDeAsistencias,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnMarcarFinalizada, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(19, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarListaDeAsistenciasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGenerarListaDeAsistenciasActionPerformed
        // TODO: Implementar la lógica para generar la lista de asistencias
    }// GEN-LAST:event_btnGenerarListaDeAsistenciasActionPerformed

    private void btnMarcarFinalizadaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMarcarFinalizadaActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea marcar la actividad como finalizada?", "Confirmar acción",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            IActividadesBO actividadesBO = ObjetosNegocioFactory.crearActividadesBO();
            try {
                actividadesBO.modificarEstadoActividad(actividad.getId(), EstadoActividad.FINALIZADA);
                JOptionPane.showMessageDialog(this, "Actividad marcada como finalizada.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                volverACargarFrame();
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, "Error al marcar la actividad como finalizada: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }// GEN-LAST:event_btnMarcarFinalizadaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEliminarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar la actividad?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            IActividadesBO actividadesBO = ObjetosNegocioFactory.crearActividadesBO();
            try {
                actividadesBO.eliminarActividad(actividad.getId());
                JOptionPane.showMessageDialog(this, "Actividad eliminada correctamente.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                volverACargarFrame();
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la actividad: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }// GEN-LAST:event_btnEliminarActionPerformed

    private void volverACargarFrame() {
        try{
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                IEventosBO eventosBO = ObjetosNegocioFactory.crearEventosBO();
                EventoDTO evento = eventosBO.buscarEventoPorId(actividad.getIdEvento());
                ControlFlujo.mostrarFrame(new FrmActividades(evento, FrmActividades.OPCION_MODIFICAR));
                parentFrame.dispose();
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el frame: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerarListaDeAsistencias;
    private javax.swing.JButton btnMarcarFinalizada;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblOcupacion;
    private javax.swing.JLabel lblPonente;
    private javax.swing.JLabel lblTipo;
    // End of variables declaration//GEN-END:variables
}
