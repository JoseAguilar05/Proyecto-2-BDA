package itson.fabrica;

import itson.implementaciones.ActividadesBO;
import itson.implementaciones.ActividadesDAO;
import itson.implementaciones.EventosBO;
import itson.implementaciones.EventosDAO;
import itson.implementaciones.LugaresBO;
import itson.implementaciones.LugaresDAO;
import itson.implementaciones.ParticipantesBO;
import itson.implementaciones.ParticipantesDAO;
import itson.implementaciones.ResponsablesBO;
import itson.implementaciones.ResponsablesDAO;
import itson.interfaces.IActividadesBO;
import itson.interfaces.IActividadesDAO;
import itson.interfaces.IEventosBO;
import itson.interfaces.IEventosDAO;
import itson.interfaces.ILugaresBO;
import itson.interfaces.ILugaresDAO;
import itson.interfaces.IParticipantesBO;
import itson.interfaces.IParticipantesDAO;
import itson.interfaces.IResponsablesBO;
import itson.interfaces.IResponsablesDAO;

public class ObjetosNegocioFactory {
    
    public static IParticipantesBO crearParticipantesBO() {
        IParticipantesDAO participantesDAO = new ParticipantesDAO();
        return new ParticipantesBO(participantesDAO);
    }
    
    public static ILugaresBO crearLugaresBO() {
        ILugaresDAO lugaresDAO = new LugaresDAO();
        return new LugaresBO(lugaresDAO);
    }

    public static IResponsablesBO crearResponsablesBO() {
        IResponsablesDAO responsablesDAO = new ResponsablesDAO();
        return new ResponsablesBO(responsablesDAO);
    }

    public static IEventosBO crearEventosBO() {
        IEventosDAO eventosDAO = new EventosDAO();
        return new EventosBO(eventosDAO);
    }

    public static IActividadesBO crearActividadesBO() {
        IActividadesDAO actividadesDAO = new ActividadesDAO();
        return new ActividadesBO(actividadesDAO);
    }

}
