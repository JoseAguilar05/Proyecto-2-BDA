package itson.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import itson.enums.EstadoEvento;
import itson.enums.ModalidadEvento;

/**
 * Representa un evento en el sistema.
 * Contiene información sobre el evento, como su título, descripción, fechas, estado, modalidad y actividades asociadas.
 */
@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

    /**
     * Identificador único del evento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Título del evento.
     */
    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    /**
     * Descripción del evento.
     */
    @Column(name = "descripcion", length = 500, nullable = false)
    private String descripcion;

    /**
     * Fecha y hora de inicio del evento.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio", nullable = false)
    private Calendar fechaInicio;

    /**
     * Fecha y hora de fin del evento.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin", nullable = false)
    private Calendar fechaFin;

    /**
     * Estado del evento (por ejemplo, activo, cancelado, finalizado).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEvento estado;

    /**
     * Modalidad del evento (por ejemplo, presencial, virtual).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "modalidad", nullable = false)
    private ModalidadEvento modalidad;

    /**
     * Lista de actividades asociadas al evento.
     */
    @OneToMany(mappedBy = "evento", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Actividad> actividades;

    /**
     * Responsable del evento.
     */
    @ManyToOne
    @JoinColumn(name = "id_responsable", nullable = false)
    private Responsable responsable;

    /**
     * Constructor por defecto.
     */
    public Evento() { }

    /**
     * Constructor con parámetros para inicializar un evento.
     * 
     * @param id Identificador único del evento.
     * @param titulo Título del evento.
     * @param descripcion Descripción del evento.
     * @param fechaInicio Fecha y hora de inicio del evento.
     * @param fechaFin Fecha y hora de fin del evento.
     * @param estado Estado del evento.
     * @param modalidad Modalidad del evento.
     */
    public Evento(Integer id, String titulo, String descripcion, Calendar fechaInicio, Calendar fechaFin, EstadoEvento estado, ModalidadEvento modalidad) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.modalidad = modalidad;
    }

    /**
     * Obtiene el identificador único del evento.
     * 
     * @return El identificador del evento.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del evento.
     * 
     * @param id El identificador del evento.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el título del evento.
     * 
     * @return El título del evento.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del evento.
     * 
     * @param titulo El título del evento.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la descripción del evento.
     * 
     * @return La descripción del evento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del evento.
     * 
     * @param descripcion La descripción del evento.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha y hora de inicio del evento.
     * 
     * @return La fecha y hora de inicio del evento.
     */
    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha y hora de inicio del evento.
     * 
     * @param fechaInicio La fecha y hora de inicio del evento.
     */
    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha y hora de fin del evento.
     * 
     * @return La fecha y hora de fin del evento.
     */
    public Calendar getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha y hora de fin del evento.
     * 
     * @param fechaFin La fecha y hora de fin del evento.
     */
    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene el estado del evento.
     * 
     * @return El estado del evento.
     */
    public EstadoEvento getEstado() {
        return estado;
    }

    /**
     * Establece el estado del evento.
     * 
     * @param estado El estado del evento.
     */
    public void setEstado(EstadoEvento estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la modalidad del evento.
     * 
     * @return La modalidad del evento.
     */
    public ModalidadEvento getModalidad() {
        return modalidad;
    }

    /**
     * Establece la modalidad del evento.
     * 
     * @param modalidad La modalidad del evento.
     */
    public void setModalidad(ModalidadEvento modalidad) {
        this.modalidad = modalidad;
    }

    /**
     * Obtiene la lista de actividades asociadas al evento.
     * 
     * @return La lista de actividades del evento.
     */
    public List<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece la lista de actividades asociadas al evento.
     * 
     * @param actividades La lista de actividades del evento.
     */
    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    /**
     * Agrega una actividad al evento.
     * 
     * @param actividad La actividad a agregar.
     */
    public void agregarActividad(Actividad actividad) {
        if (actividad != null) {
            this.actividades.add(actividad);
            actividad.setEvento(this);
        }
    }

    /**
     * Obtiene el responsable del evento.
     * 
     * @return El responsable del evento.
     */
    public Responsable getResponsable() {
        return responsable;
    }

    /**
     * Establece el responsable del evento.
     * 
     * @param responsable El responsable del evento.
     */
    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

}