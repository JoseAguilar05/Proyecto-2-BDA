package itson.fabrica;

import itson.implementaciones.ParticipantesBO;
import itson.implementaciones.ParticipantesDAO;
import itson.interfaces.IParticipantesBO;
import itson.interfaces.IParticipantesDAO;

public class ObjetosNegocioFactory {
    
    public static IParticipantesBO crearParticipantesBO() {
        IParticipantesDAO participantesDAO = new ParticipantesDAO();
        return new ParticipantesBO(participantesDAO);
    }
}
