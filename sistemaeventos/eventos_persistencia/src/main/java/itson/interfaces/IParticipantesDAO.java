package itson.interfaces;

import java.util.List;

import itson.dtos.ParticipanteDTO;
import itson.entidades.Participante;
import itson.enums.TipoParticipante;
import itson.excepciones.SeguridadException;

public interface IParticipantesDAO {

    Participante registrarParticipante(ParticipanteDTO participanteDTO) throws SeguridadException;

    ParticipanteDTO obtenerParticipantePorId(Integer id) throws SeguridadException;
    
    List<ParticipanteDTO> obtenerParticipantes() throws SeguridadException;

    List<ParticipanteDTO> obtenerParticipantesConFiltro(String filtro) throws SeguridadException;

    List<ParticipanteDTO> obtenerParticipantesConFiltroYTipo(String filtro, TipoParticipante tipo) throws SeguridadException;

    List<ParticipanteDTO> obtenerParticipantesPorTipo(TipoParticipante tipo) throws SeguridadException;

    boolean eliminarParticipante(Integer id) ;



}
