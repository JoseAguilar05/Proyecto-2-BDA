package itson.dtos;

import java.util.Calendar;
import java.util.List;
import itson.enums.EstadoEvento;
import itson.enums.ModalidadEvento;

public class EventoDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private EstadoEvento estado;
    private ModalidadEvento modalidad;
    private List<Integer> idsActividades;
    private Integer responsableId;

    /**
     * Constructor de la clase EventoDTO.
     * 
     * @param id            El identificador único del evento.
     * @param titulo        El título del evento.
     * @param descripcion   La descripción del evento.
     * @param fechaInicio   La fecha y hora de inicio del evento.
     * @param fechaFin      La fecha y hora de finalización del evento.
     * @param estado        El estado del evento (EN_CURSO, PLANEADO, FINALIZADO).
     * @param modalidad     La modalidad del evento (PRESENCIAL, VIRTUAL).
     * @param idsActividades Lista de identificadores de actividades asociadas al evento.
     * @param responsableId El identificador del responsable del evento.
     */
    public EventoDTO(Integer id, String titulo, String descripcion, Calendar fechaInicio, Calendar fechaFin,
            EstadoEvento estado, ModalidadEvento modalidad, List<Integer> actividadesIds, Integer responsableId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.modalidad = modalidad;
        this.idsActividades = actividadesIds;
        this.responsableId = responsableId;
    }

    

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public EstadoEvento getEstado() {
        return estado;
    }

    public ModalidadEvento getModalidad() {
        return modalidad;
    }

    public List<Integer> getIdsActividades() {
        return idsActividades;
    }

    public Integer getResponsableId() {
        return responsableId;
    }

}