package itson.dtos;

import java.util.List;

public class ActividadDTO {
    private Integer id;
    private String nombre;
    private String tipoActividad;
    private String fechaInicio;
    private Integer duracionEstimada;
    private String lugar;
    private List<String> participantesIds;
    private String eventoId;

    /**
     * Constructor de la clase ActividadDTO.
     * 
     * @param id                El identificador único de la actividad.
     * @param nombre            El nombre de la actividad.
     * @param tipoActividad     La descripción o tipo de la actividad.
     * @param fechaInicio       La fecha y hora de inicio de la actividad.
     * @param duracionEstimada La duración estimada de la actividad en minutos.
     * @param lugar            El lugar donde se llevará a cabo la actividad.
     * @param participantesIds Lista de identificadores de los participantes en la actividad.
     * @param eventoId        El identificador del evento al que pertenece la actividad.
     */
    public ActividadDTO(Integer id, String nombre, String tipoActividad, String fechaInicio,
            Integer duracionEstimada, String lugar, List<String> participantesIds, String eventoId) {
        this.id = id;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.fechaInicio = fechaInicio;
        this.duracionEstimada = duracionEstimada;
        this.lugar = lugar;
        this.participantesIds = participantesIds;
        this.eventoId = eventoId;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public Integer getDuracionEstimada() {
        return duracionEstimada;
    }

    public String getLugar() {
        return lugar;
    }

    public List<String> getParticipantesIds() {
        return participantesIds;
    }

    public String getEventoId() {
        return eventoId;
    }
}