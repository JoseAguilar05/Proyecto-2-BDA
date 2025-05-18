package itson.implementaciones;

import java.util.List;

import itson.dtos.ActividadDTO;
import itson.dtos.ParticipanteDTO;
import itson.entidades.Actividad;
import itson.enums.EstadoActividad;
import itson.excepciones.NegocioException;
import itson.interfaces.IActividadesBO;
import itson.interfaces.IActividadesDAO;

public class ActividadesBO implements IActividadesBO{

    private IActividadesDAO actividadesDAO;

    public ActividadesBO(IActividadesDAO actividadesDAO) {
        this.actividadesDAO = actividadesDAO;
    }

    @Override
    public void crearActividad(ActividadDTO actividad) throws NegocioException {
        if (actividad == null) {
            throw new NegocioException("La actividad no puede ser nula");
        }
        Actividad actividadCreada = actividadesDAO.guardarActividad(actividad);
        if (actividadCreada == null) {
            throw new NegocioException("Error al crear la actividad");
        }
    }

    @Override
    public void eliminarActividad(Integer id) throws NegocioException {
        if (id == null) {
            throw new NegocioException("El ID de la actividad no puede ser nulo");
        }
        boolean resultado = actividadesDAO.eliminarActividad(id);
        if (!resultado) {
            throw new NegocioException("Error al eliminar la actividad");
        }
    }

    @Override
    public ActividadDTO buscarActividadPorId(Integer id) throws NegocioException {
        if (id == null) {
            throw new NegocioException("El ID de la actividad no puede ser nulo");
        }
        ActividadDTO actividad = actividadesDAO.buscarActividadPorId(id);
        if (actividad == null) {
            throw new NegocioException("Actividad no encontrada");
        }
        return actividad;
    }

    @Override
    public boolean modificarEstadoActividad(Integer idActividad, EstadoActividad estadoActividad)
            throws NegocioException {
        if (idActividad == null || estadoActividad == null) {
            throw new NegocioException("El ID de la actividad y el estado no pueden ser nulos");
        }
        boolean resultado = actividadesDAO.modificarEstadoActividadDTO(idActividad, estadoActividad);
        if (!resultado) {
            throw new NegocioException("Error al modificar el estado de la actividad");
        }
        return resultado;
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantesPorActividad(int idActividad) throws NegocioException {
        List<ParticipanteDTO> participantes = actividadesDAO.obtenerParticipantesPorActividad(idActividad);
        if (participantes == null || participantes.isEmpty()) {
            throw new NegocioException("No se encontraron participantes para la actividad");
        }
        return participantes;
    }


}
