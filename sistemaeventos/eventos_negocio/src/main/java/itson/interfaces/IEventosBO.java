package itson.interfaces;

import java.util.List;

import itson.dtos.ActividadDTO;
import itson.dtos.BusquedaEventoDTO;
import itson.dtos.EventoDTO;
import itson.enums.EstadoEvento;
import itson.excepciones.NegocioException;

public interface IEventosBO {
    /**
     * Guarda un evento en la base de datos.
     *
     * @param eventoDTO El evento a guardar.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    void guardarEventoConActividades(EventoDTO eventoDTO, List<ActividadDTO> actividades) throws NegocioException;

    /**
     * Busca un evento por su ID.
     *
     * @param id El ID del evento a buscar.
     * @return El evento encontrado o null si no se encontró.
     */
    EventoDTO buscarEventoPorId(int id) throws NegocioException;

    /**
     * Obtiene la lista de todos los eventos
     * @return Lista de eventos
     */
    List<EventoDTO> obtenerEventos();

    /**
     * Busca eventos por un filtro específico.
     * @param filtro El filtro de búsqueda que contiene los criterios de búsqueda.
     * @return Lista de eventos que cumplen con el filtro.
     */
    List<EventoDTO> buscarEventosPorFiltro(BusquedaEventoDTO filtro);

    /**
     * Modifica el estado de un evento.
     * @param idEvento El ID del evento a modificar.
     * @param estadoEvento El nuevo estado del evento.
     * @return true si se modificó correctamente, false en caso contrario.
     * @throws NegocioException si ocurre un error al modificar el estado del evento.
     */
    boolean modificarEstadoEvento(Integer idEvento, EstadoEvento estadoEvento) throws NegocioException;

    /**
     * Modifica un evento en la base de datos.
     *
     * @param eventoDTO El evento a modificar.
     * @return true si se modificó correctamente, false en caso contrario.
     */
    boolean modificarEvento(EventoDTO eventoDTO) throws NegocioException;
}
