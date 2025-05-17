package itson.implementaciones;

import java.util.List;
import java.util.regex.Pattern;

import itson.dtos.ParticipanteDTO;
import itson.enums.TipoParticipante;
import itson.excepciones.NegocioException;
import itson.excepciones.SeguridadException;
import itson.interfaces.IParticipantesBO;
import itson.interfaces.IParticipantesDAO;

public class ParticipantesBO implements IParticipantesBO {

    private IParticipantesDAO participantesDAO;
    
    public ParticipantesBO(IParticipantesDAO participantesDAO) {
        this.participantesDAO = participantesDAO;
    }

    @Override
    public void registrarParticipante(ParticipanteDTO participanteDTO) throws NegocioException {
        if(!validarCorreo(participanteDTO.getCorreo())){
            throw new NegocioException("El correo electrónico no es válido.");
        }
        try{
            if(participanteDTO.getTipoParticipante().equals(TipoParticipante.ESTUDIANTE)){
                if(participanteDTO.getNumeroControl() == null || participanteDTO.getNumeroControl().isEmpty()){
                    throw new NegocioException("El número de control es obligatorio para estudiantes.");
                }
                if(participanteDTO.getDependencia() == null || participanteDTO.getDependencia().isEmpty()){
                    throw new NegocioException("La dependencia es obligatoria para estudiantes.");
                }
                if(obtenerParticipantesConFiltro(participanteDTO.getNumeroControl()).size() > 0){
                    throw new NegocioException("El número de control ya está registrado.");
                }
            } 
            participantesDAO.registrarParticipante(participanteDTO);
        } catch (SeguridadException e){
            throw new NegocioException("Error al registrar el participante: " + e.getMessage());
        }
    }

    private boolean validarCorreo(String correo) {
        final String REGEX = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern patronCorreo = Pattern.compile(REGEX);
        return patronCorreo.matcher(correo).matches();
    }

    @Override
    public ParticipanteDTO obtenerParticipantePorId(Integer id) throws NegocioException {
        try {
            return participantesDAO.obtenerParticipantePorId(id);
        } catch (SeguridadException e) {
            throw new NegocioException("Error al obtener el participante: " + e.getMessage());
        } catch (Exception e) {
            throw new NegocioException("Error inesperado al obtener el participante: " + e.getMessage());
        }
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantes() throws NegocioException {
        try {
            return participantesDAO.obtenerParticipantes();
        } catch (SeguridadException e) {
            throw new NegocioException("Error al obtener los participantes: " + e.getMessage());
        } catch (Exception e) {
            throw new NegocioException("Error inesperado al obtener los participantes: " + e.getMessage());
        }
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantesConFiltro(String filtro) throws NegocioException {
        try {
            return participantesDAO.obtenerParticipantesConFiltro(filtro);
        } catch (SeguridadException e) {
            throw new NegocioException("Error al obtener los participantes con filtro: " + e.getMessage());
        } catch (Exception e) {
            throw new NegocioException("Error inesperado al obtener los participantes con filtro: " + e.getMessage());
        }
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantesConFiltroYTipo(String filtro, String tipo)
            throws NegocioException {
        try {
            TipoParticipante tipoParticipante = TipoParticipante.valueOf(tipo.toUpperCase());
            return participantesDAO.obtenerParticipantesConFiltroYTipo(filtro, tipoParticipante);
        } catch (SeguridadException e) {
            throw new NegocioException("Error al obtener los participantes con filtro y tipo: " + e.getMessage());
        } catch (Exception e) {
            throw new NegocioException("Error inesperado al obtener los participantes con filtro y tipo: " + e.getMessage());
        }
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantesPorTipo(String tipo) throws NegocioException {
        try {
            TipoParticipante tipoParticipante = TipoParticipante.valueOf(tipo.toUpperCase());
            return participantesDAO.obtenerParticipantesPorTipo(tipoParticipante);
        } catch (SeguridadException e) {
            throw new NegocioException("Error al obtener los participantes por tipo: " + e.getMessage());
        } catch (Exception e) {
            throw new NegocioException("Error inesperado al obtener los participantes por tipo: " + e.getMessage());
        }
    }

}
