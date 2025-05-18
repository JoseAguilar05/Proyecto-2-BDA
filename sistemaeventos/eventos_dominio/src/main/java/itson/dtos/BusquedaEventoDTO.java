package itson.dtos;

import java.util.Calendar;

import itson.enums.EstadoEvento;
import itson.enums.ModalidadEvento;

public class BusquedaEventoDTO {
    private String filtro;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private ModalidadEvento modalidad;
    private EstadoEvento estado;

    public BusquedaEventoDTO(String filtro, Calendar fechaInicio, Calendar fechaFin, ModalidadEvento modalidad,
            EstadoEvento estado) {
        this.filtro = filtro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.modalidad = modalidad;
        this.estado = estado;
    }

    public String getFiltro() {
        return filtro;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public ModalidadEvento getModalidad() {
        return modalidad;
    }

    public EstadoEvento getEstado() {
        return estado;
    }


}
