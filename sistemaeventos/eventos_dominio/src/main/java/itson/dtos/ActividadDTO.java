package itson.dtos;

import java.util.Calendar;
import java.util.List;

import itson.enums.EstadoActividad;

public class ActividadDTO {
    private Integer id;
    private String nombre;
    private String tipoActividad;
    private Calendar fechaInicio;
    private Integer duracionEstimada;
    private Integer capacidadMaxima;
    private EstadoActividad estado;
    private Integer lugarId;
    private Integer responsableId;
    private List<Integer> participantesIds;
    private Integer idEvento;

    /**
     * Constructor de la clase ActividadDTO.
     * 
     * @param id               El identificador único de la actividad.
     * @param nombre           El nombre de la actividad.
     * @param tipoActividad    La descripción o tipo de la actividad.
     * @param fechaInicio      La fecha y hora de inicio de la actividad.
     * @param duracionEstimada La duración estimada de la actividad en minutos.
     * @param lugarId          El lugar donde se llevará a cabo la actividad.
     * @param participantesIds Lista de identificadores de los participantes en la
     *                         actividad.
     * @param eventoId         El identificador del evento al que pertenece la
     *                         actividad.
     */
    public ActividadDTO(Integer id, String nombre, String tipoActividad, Calendar fechaInicio,
            Integer duracionEstimada, Integer capacidadMaxima, EstadoActividad estado, Integer lugarId, Integer responsableId,
            List<Integer> participantesIds, Integer eventoId) {
        this.id = id;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.fechaInicio = fechaInicio;
        this.duracionEstimada = duracionEstimada;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = estado;
        this.lugarId = lugarId;
        this.responsableId = responsableId;
        this.participantesIds = participantesIds;
        this.idEvento = eventoId;
    }

    /**
     * Constructor de la clase ActividadDTO para cuando no se tiene el id.
     * 
     * @param nombre           Nombre de la actividad.
     * @param tipoActividad    Descripción o tipo de la actividad.
     * @param fechaInicio      Fecha y hora de inicio de la actividad.
     * @param duracionEstimada Duración estimada de la actividad en minutos.
     * @param lugarId          Ubicación donde se llevará a cabo la actividad.
     * @param responsableId    Identificador del responsable de la actividad.
     */
    public ActividadDTO(String nombre, String tipoActividad, Calendar fechaInicio,
            Integer duracionEstimada, Integer capacidadMaxima, EstadoActividad estado, Integer lugarId,
            Integer responsableId) {
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.fechaInicio = fechaInicio;
        this.duracionEstimada = duracionEstimada;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = estado;
        this.lugarId = lugarId;
        this.responsableId = responsableId;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public Integer getDuracionEstimada() {
        return duracionEstimada;
    }

    public EstadoActividad getEstado() {
        return estado;
    }

    public Integer getLugarId() {
        return lugarId;
    }

    public List<Integer> getParticipantesIds() {
        return participantesIds;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getResponsableId() {
        return responsableId;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }
}