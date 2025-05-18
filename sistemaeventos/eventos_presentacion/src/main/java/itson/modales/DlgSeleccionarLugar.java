package itson.modales;

import itson.dtos.LugarDTO;
import itson.interfaces.ILugaresBO;
import itson.fabrica.ObjetosNegocioFactory; 

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DlgSeleccionarLugar extends JDialog {

    private JTable tblLugares;
    private DefaultTableModel modeloTabla;
    private JButton btnSeleccionar;
    private JButton btnCancelar;
    private LugarDTO lugarSeleccionado;
    private transient ILugaresBO lugaresBO;
    private List<LugarDTO> listaLugaresMostrados;

    public DlgSeleccionarLugar(Frame owner, boolean modal) {
        super(owner, modal);
        // Asumiendo que tienes una forma de obtener tu LugaresBO, por ejemplo, a través
        // de una fábrica
        this.lugaresBO = ObjetosNegocioFactory.crearLugaresBO();
        this.listaLugaresMostrados = new ArrayList<>();
        initComponents();
        cargarLugares();
        setTitle("Seleccionar Lugar");
        setSize(500, 350);
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        modeloTabla = new DefaultTableModel(new Object[] { "Nombre", "Edificio" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir edición directa en la tabla
            }
        };
        tblLugares = new JTable(modeloTabla);
        tblLugares.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblLugares);

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
                int filaSeleccionada = tblLugares.getSelectedRow();
                if (filaSeleccionada != -1) {
                    lugarSeleccionado = listaLugaresMostrados.get(filaSeleccionada);
                    dispose(); // Cerrar el diálogo
                } else {
                    JOptionPane.showMessageDialog(DlgSeleccionarLugar.this,
                            "Por favor, seleccione un lugar de la tabla.",
                            "Ningún Lugar Seleccionado",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lugarSeleccionado = null;
                dispose();
            }
        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                lugarSeleccionado = null;
            }
        });
    }

    private void cargarLugares() {
        List<LugarDTO> todosLosLugares = lugaresBO.obtenerLugares();
        listaLugaresMostrados.clear();
        modeloTabla.setRowCount(0);

        if (todosLosLugares != null) {
            for (LugarDTO lugar : todosLosLugares) {
                Vector<Object> fila = new Vector<>();
                fila.add(lugar.getNombre());
                fila.add(lugar.getEdificio());
                modeloTabla.addRow(fila);
                listaLugaresMostrados.add(lugar);
            }
        }

    }

    /**
     * Muestra el modal y permite al usuario seleccionar un lugar.
     *
     * @return El LugarDTO seleccionado, o null si se cancela o cierra el diálogo.
     */
    public LugarDTO obtenerLugarSeleccionado() {
        this.lugarSeleccionado = null;
        setVisible(true);
        return this.lugarSeleccionado;
    }
}