package itson.interfaces;

import java.util.List;

import itson.dtos.ActividadDTO;
import itson.dtos.ParticipanteDTO;
import itson.entidades.Actividad;
import itson.enums.EstadoActividad;

public interface IActividadesDAO {

    Actividad guardarActividad(ActividadDTO actividadDTO);

    ActividadDTO buscarActividadPorId(int id);

    boolean eliminarActividad(int id);

    boolean modificarEstadoActividadDTO(int idActividad, EstadoActividad estadoActividad);

    List<ParticipanteDTO> obtenerParticipantesPorActividad(int idActividad);

}
