package itson.interfaces;

import java.util.List;

import itson.dtos.ActividadDTO;
import itson.dtos.ParticipanteDTO;
import itson.enums.EstadoActividad;
import itson.excepciones.NegocioException;

public interface IActividadesBO {
    /**
     * Crea una actividad.
     *
     * @param actividad la actividad a crear
     * @return la actividad creada
     */
    public void crearActividad(ActividadDTO actividad) throws NegocioException;

    /**
     * Elimina una actividad.
     *
     * @param id el ID de la actividad a eliminar
     */
    public void eliminarActividad(Integer id) throws NegocioException;

    /**
     * Busca una actividad por su ID.
     *
     * @param id el ID de la actividad a buscar
     * @return la actividad encontrada
     */
    public ActividadDTO buscarActividadPorId(Integer id) throws NegocioException;

    /**
     * Modifica una actividad.
     *
     * @param idActividad el ID de la actividad a modificar
     * @param estadoActividad el nuevo estado de la actividad
     * @return la actividad modificada
     */
    public boolean modificarEstadoActividad(Integer idActividad, EstadoActividad estadoActividad) throws NegocioException;

    public List<ParticipanteDTO> obtenerParticipantesPorActividad(int idActividad) throws NegocioException;


}
