package itson.modales;

import itson.control.Formateador;
import itson.dtos.ResponsableDTO;
import itson.enums.TipoResponsable;
import itson.interfaces.IResponsablesBO;
import itson.excepciones.NegocioException; // Asegúrate de tener esta excepción
import itson.fabrica.ObjetosNegocioFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DlgSeleccionarOrganizador extends JDialog {

    private JTable tblResponsables;
    private DefaultTableModel modeloTabla;
    private JButton btnSeleccionar;
    private JButton btnCancelar;
    private ResponsableDTO responsableSeleccionado;
    private transient IResponsablesBO responsablesBO; 
    private List<ResponsableDTO> listaResponsablesMostrados;
    
    private int opcion;

    public static final int OPCION_EVENTO = 0;
    public static final int OPCION_ACTIVIDAD = 1;

    public DlgSeleccionarOrganizador(Frame owner, boolean modal, int opcion) {
        super(owner, modal);
        this.responsablesBO = ObjetosNegocioFactory.crearResponsablesBO();
        this.listaResponsablesMostrados = new ArrayList<>();
        this.opcion = opcion;
        if (opcion == OPCION_EVENTO) {
            setTitle("Seleccionar Organizador para Evento");
        } else if (opcion == OPCION_ACTIVIDAD) {
            setTitle("Seleccionar Organizador para Actividad");
        }
        initComponents();
        cargarResponsables();
        setTitle("Seleccionar Organizador");
        setSize(500, 350);
        setLocationRelativeTo(owner); 
    }

    private void initComponents() {
        modeloTabla = new DefaultTableModel(new Object[]{"Nombre Completo", "Teléfono", "Tipo Responsable"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tblResponsables = new JTable(modeloTabla);
        tblResponsables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblResponsables);

        btnSeleccionar = new JButton("Seleccionar");
        btnCancelar = new JButton("Cancelar");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnSeleccionar);
        panelBotones.add(btnCancelar);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tblResponsables.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener el DTO usando el índice de la lista de responsables mostrados
                    responsableSeleccionado = listaResponsablesMostrados.get(filaSeleccionada);
                    dispose(); // Cerrar el diálogo
                } else {
                    if(opcion == OPCION_EVENTO) {
                        JOptionPane.showMessageDialog(DlgSeleccionarOrganizador.this,
                                "Por favor, seleccione un organizador de la tabla.",
                                "Ningún Organizador Seleccionado",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (opcion == OPCION_ACTIVIDAD) {
                        JOptionPane.showMessageDialog(DlgSeleccionarOrganizador.this,
                                "Por favor, seleccione un responsable de la tabla.",
                                "Ningún Organizador Seleccionado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responsableSeleccionado = null; // Asegurar que no hay selección
                dispose(); // Cerrar el diálogo
            }
        });

        // Manejar cierre de ventana (X) como cancelación
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                responsableSeleccionado = null;
            }
        });
    }

    /**
     * Carga los responsables en la tabla.
     */
    private void cargarResponsables() {
        try {
            List<ResponsableDTO> todosLosResponsables = null;
            if(opcion == OPCION_EVENTO) {
                todosLosResponsables = responsablesBO.obtenerResponsablesPorTipo(TipoResponsable.RESPONSABLE);
            } else if (opcion == OPCION_ACTIVIDAD) {
                todosLosResponsables = responsablesBO.obtenerResponsables();
            }
            listaResponsablesMostrados.clear(); 
            modeloTabla.setRowCount(0); 

            if (todosLosResponsables != null) {
                for (ResponsableDTO responsable : todosLosResponsables) {
                        Vector<Object> fila = new Vector<>();
                        String nombreCompleto = responsable.getNombre() + " " +
                                                responsable.getApellidoPaterno() + " " +
                                                responsable.getApellidoMaterno();
                        fila.add(nombreCompleto.trim());
                        fila.add(responsable.getTelefono());
                        fila.add(Formateador.formatearCap(responsable.getTipoResponsable().toString()));
                        modeloTabla.addRow(fila);
                        listaResponsablesMostrados.add(responsable); 
                }
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar responsables: " + ex.getMessage(),
                    "Error de Carga",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra el modal y permite al usuario seleccionar un organizador.
     *
     * @return El ResponsableDTO seleccionado, o null si se cancela o cierra el diálogo.
     */
    public ResponsableDTO obtenerResponsableSeleccionado() {
        this.responsableSeleccionado = null;
        setVisible(true); 
        return this.responsableSeleccionado;
    }

}