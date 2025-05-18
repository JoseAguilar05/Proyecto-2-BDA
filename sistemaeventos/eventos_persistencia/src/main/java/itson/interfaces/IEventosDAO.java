package itson.interfaces;

import java.util.List;

import itson.dtos.ActividadDTO;
import itson.dtos.BusquedaEventoDTO;
import itson.dtos.EventoDTO;
import itson.entidades.Evento;
import itson.enums.EstadoEvento;

public interface IEventosDAO {

    /**
     * Guarda un evento en la base de datos.
     *
     * @param eventoDTO El evento a guardar.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    Evento guardarEventoConActividades(EventoDTO eventoDTO, List<ActividadDTO> actividades);

    /**
     * Busca un evento por su ID.
     *
     * @param id El ID del evento a buscar.
     * @return El evento encontrado o null si no se encontró.
     */
    EventoDTO buscarEventoPorId(int id);

    /**
     * Obtiene la lista de todos los eventos
     * @return Lista de eventos
     */
    List<EventoDTO> obtenerEventos();

    /**
     * Elimina un evento de la base de datos.
     *
     * @param id El ID del evento a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    boolean eliminarEvento(int id);

    /**
     * Busca eventos por un filtro específico.
     * @param filtro El filtro de búsqueda que contiene los criterios de búsqueda.
     * @return Lista de eventos que cumplen con el filtro.
     */
    List<EventoDTO> buscarEventosPorFiltro(BusquedaEventoDTO filtro);

    /**
     * Modifica el estado de un evento.
     *
     * @param idEvento El ID del evento a modificar.
     * @param estadoEvento El nuevo estado del evento.
     * @return true si se modificó correctamente, false en caso contrario.
     */
    boolean modificarEstadoEvento(Integer idEvento, EstadoEvento estadoEvento);

    /**
     * Modifica un evento en la base de datos.
     * @param eventoDTO El evento a modificar con los nuevos datos.
     * @return El evento modificado.
     */
    Evento modificarEvento(EventoDTO eventoDTO);
}
