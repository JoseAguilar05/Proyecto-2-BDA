package itson.entidades;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa una actividad dentro de un evento.
 * Contiene información sobre la actividad, como su nombre, tipo, duración, lugar y los participantes asociados.
 */
@Entity
@Table(name = "actividades")
public class Actividad {
    
    /**
     * Identificador único de la actividad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre de la actividad.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Tipo o descripción de la actividad.
     */
    @Column(name = "tipo_actividad")
    private String tipoActividad;

    /**
     * Fecha y hora de inicio de la actividad.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio")
    private Calendar fechaInicio;

    /**
     * Duración estimada de la actividad en minutos.
     */
    @Column(name = "duracion_estimada")
    private Integer duracionEstimada;

    /**
     * Ubicación donde se llevará a cabo la actividad.
     */
    @ManyToOne
    @JoinColumn(name = "id_lugar")
    private Lugar lugar;

    /**
     * Responsable de la actividad.
     */
    @ManyToOne()
    @JoinColumn(name = "id_responsable")
    private Responsable responsable;

    /**
     * Lista de participantes asociados a la actividad.
     */
    @ManyToMany(mappedBy = "actividades")
    private List<Participante> participantes;

    /**
     * Evento al que pertenece la actividad.
     */
    @ManyToOne()
    @JoinColumn(name = "id_evento")
    private Evento evento;
    
    /**
     * Constructor por defecto.
     */
    public Actividad() {}

    /**
     * Constructor con parámetros para inicializar una actividad.
     * 
     * @param id Identificador único de la actividad.
     * @param nombre Nombre de la actividad.
     * @param descripcion Descripción o tipo de la actividad.
     * @param fechaInicio Fecha y hora de inicio de la actividad.
     * @param duracionEstimada Duración estimada de la actividad en minutos.
     * @param lugar Ubicación donde se llevará a cabo la actividad.
     */
    public Actividad(Integer id, String nombre, String descripcion, Calendar fechaInicio, Integer duracionEstimada, Lugar lugar) {
        this.id = id;
        this.nombre = nombre;
        this.tipoActividad = descripcion;
        this.fechaInicio = fechaInicio;
        this.duracionEstimada = duracionEstimada;
        this.lugar = lugar;
    }

    /**
     * Obtiene el identificador único de la actividad.
     * 
     * @return El identificador de la actividad.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de la actividad.
     * 
     * @param id El identificador de la actividad.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la actividad.
     * 
     * @return El nombre de la actividad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la actividad.
     * 
     * @param nombre El nombre de la actividad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo o descripción de la actividad.
     * 
     * @return El tipo o descripción de la actividad.
     */
    public String getTipoActividad() {
        return tipoActividad;
    }

    /**
     * Establece el tipo o descripción de la actividad.
     * 
     * @param descripcion El tipo o descripción de la actividad.
     */
    public void setTipoActividad(String descripcion) {
        this.tipoActividad = descripcion;
    }

    /**
     * Obtiene la fecha y hora de inicio de la actividad.
     * 
     * @return La fecha y hora de inicio de la actividad.
     */
    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha y hora de inicio de la actividad.
     * 
     * @param fechaInicio La fecha y hora de inicio de la actividad.
     */
    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la duración estimada de la actividad en minutos.
     * 
     * @return La duración estimada de la actividad.
     */
    public Integer getDuracionEstimada() {
        return duracionEstimada;
    }

    /**
     * Establece la duración estimada de la actividad en minutos.
     * 
     * @param duracionEstimada La duración estimada de la actividad.
     */
    public void setDuracionEstimada(Integer duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    /**
     * Obtiene la ubicación de la actividad.
     * 
     * @return La ubicación de la actividad.
     */
    public Lugar getLugar() {
        return lugar;
    }

    /**
     * Establece la ubicación de la actividad.
     * 
     * @param lugar La ubicación de la actividad.
     */
    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    /**
     * Obtiene la lista de participantes asociados a la actividad.
     * 
     * @return La lista de participantes.
     */
    public List<Participante> getParticipantes() {
        return participantes;
    }

    /**
     * Establece la lista de participantes asociados a la actividad.
     * 
     * @param participantes La lista de participantes.
     */
    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    /**
     * Obtiene el evento al que pertenece la actividad.
     * 
     * @return El evento asociado.
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Establece el evento al que pertenece la actividad.
     * 
     * @param evento El evento asociado.
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    /**
     * Obtiene el responsable de la actividad.
     * 
     * @return El responsable de la actividad.
     */
    public Responsable getResponsable() {
        return responsable;
    }

    /**
     * Establece el responsable de la actividad.
     * @param responsable El responsable de la actividad.
     */
    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}
