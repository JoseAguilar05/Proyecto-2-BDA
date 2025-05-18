package itson.implementaciones;

import java.util.List;

import itson.dtos.ActividadDTO;
import itson.dtos.EventoDTO;
import itson.entidades.Evento;
import itson.excepciones.NegocioException;
import itson.interfaces.IEventosBO;
import itson.interfaces.IEventosDAO;

public class EventosBO implements IEventosBO {

    private IEventosDAO eventosDAO;

    public EventosBO(IEventosDAO eventosDAO) {
        this.eventosDAO = eventosDAO;
    }

    @Override
    public void guardarEventoConActividades(EventoDTO eventoDTO, List<ActividadDTO> actividades)
            throws NegocioException {
        if(actividades == null || actividades.isEmpty()) {
            throw new NegocioException("La lista de actividades no puede estar vacía");
        }
        Evento evento = eventosDAO.guardarEventoConActividades(eventoDTO, actividades);
        if (evento == null) {
            throw new NegocioException("Error al guardar el evento");
        }
    }

    @Override
    public EventoDTO buscarEventoPorId(int id) throws NegocioException {
        EventoDTO evento = eventosDAO.buscarEventoPorId(id);
        if (evento == null) {
            throw new NegocioException("Evento no encontrado");
        }
        return evento;
    }

    @Override
    public List<EventoDTO> obtenerEventos() {
        List<EventoDTO> eventos = eventosDAO.obtenerEventos();
        return eventos;
    }

}
