package itson.interfaces;

import java.util.List;

import itson.dtos.ParticipanteDTO;
import itson.excepciones.NegocioException;

public interface IParticipantesBO {

    /**
     * Registra un nuevo participante en el evento.
     *
     * @param nombre Nombre del participante.
     * @param correo Correo electrónico del participante.
     * @param telefono Número de teléfono del participante.
     * @throws NegocioException Si ocurre un error al registrar el participante.
     */
    void registrarParticipante(ParticipanteDTO participanteDTO) throws NegocioException;

    ParticipanteDTO obtenerParticipantePorId(Integer id) throws NegocioException;

    /**
     * Obtiene la lista de participantes registrados en el evento.
     *
     * @return Lista de participantes registrados.
     */
    List<ParticipanteDTO> obtenerParticipantes() throws NegocioException;

    /**
     * Obtiene la lista de participantes registrados en el sistema con un filtro específico.
     * @param filtro Filtro para buscar participantes.
     * @return Lista de participantes que cumplen con el filtro.
     * @throws NegocioException Si ocurre un error al obtener los participantes.
     */
    List<ParticipanteDTO> obtenerParticipantesConFiltro(String filtro) throws NegocioException;
    
    /**
     * Obtiene la lista de participantes registrados en el sistema con un filtro específico y un tipo de participante.
     * 
     * @param filtro Filtro para buscar participantes.
     * @param tipo Tipo de participante a filtrar.
     * @return Lista de participantes que cumplen con el filtro.
     * @throws NegocioException Si ocurre un error al obtener los participantes.
     */
    List<ParticipanteDTO> obtenerParticipantesConFiltroYTipo(String filtro, String tipo) throws NegocioException;

    /**
     * Obtiene la lista de participantes registrados en el sistema por tipo de participante.
     *
     * @param tipo Tipo de participante a filtrar.
     * @return Lista de participantes que cumplen con el filtro.
     * @throws NegocioException Si ocurre un error al obtener los participantes.
     */
    List<ParticipanteDTO> obtenerParticipantesPorTipo(String tipo) throws NegocioException;

    
}
