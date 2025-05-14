package itson.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private String id;

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
    @Column(name = "fecha_inicio")
    private String fechaInicio;

    /**
     * Duración estimada de la actividad en minutos.
     */
    @Column(name = "duracion_estimada")
    private Integer duracionEstimada;

    /**
     * Ubicación donde se llevará a cabo la actividad.
     */
    @Column(name = "lugar")
    private String lugar;

    /**
     * Lista de participantes asociados a la actividad.
     */
    @ManyToMany(mappedBy = "actividades")
    private List<Participante> participantes;

    /**
     * Evento al que pertenece la actividad.
     */
    @ManyToOne()
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
    public Actividad(String id, String nombre, String descripcion, String fechaInicio, Integer duracionEstimada, String lugar) {
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
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único de la actividad.
     * 
     * @param id El identificador de la actividad.
     */
    public void setId(String id) {
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
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha y hora de inicio de la actividad.
     * 
     * @param fechaInicio La fecha y hora de inicio de la actividad.
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la duración estimada de la actividad en minutos.
     * 
     * @return La duración estimada de la actividad.
     */
    public Integer getFechaFin() {
        return duracionEstimada;
    }

    /**
     * Establece la duración estimada de la actividad en minutos.
     * 
     * @param fechaFin La duración estimada de la actividad.
     */
    public void setFechaFin(Integer fechaFin) {
        this.duracionEstimada = fechaFin;
    }

    /**
     * Obtiene la ubicación de la actividad.
     * 
     * @return La ubicación de la actividad.
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Establece la ubicación de la actividad.
     * 
     * @param lugar La ubicación de la actividad.
     */
    public void setLugar(String lugar) {
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
}
