package itson.implementaciones;

import java.util.List;

import javax.persistence.EntityManager;

import itson.conexion.ManejadorConexiones;
import itson.dtos.ActividadDTO;
import itson.dtos.ParticipanteDTO;
import itson.entidades.Actividad;
import itson.entidades.Evento;
import itson.entidades.Lugar;
import itson.entidades.Responsable;
import itson.enums.EstadoActividad;
import itson.interfaces.IActividadesDAO;

public class ActividadesDAO implements IActividadesDAO {

    @Override
    public Actividad guardarActividad(ActividadDTO actividadDTO) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Actividad actividad = new Actividad();
        actividad.setNombre(actividadDTO.getNombre());
        actividad.setTipoActividad(actividadDTO.getTipoActividad());
        actividad.setEstado(actividadDTO.getEstado());
        actividad.setFechaInicio(actividadDTO.getFechaInicio());
        actividad.setDuracionEstimada(actividadDTO.getDuracionEstimada());
        actividad.setCapacidadMaxima(actividadDTO.getCapacidadMaxima());
        Responsable responsable = obtenerResponsablePorId(actividadDTO.getResponsableId());
        if(responsable == null) {
            return null; // Manejar el caso donde el responsable no existe
        }
        actividad.setResponsable(responsable);
        Lugar lugar = obtenerLugarPorId(actividadDTO.getLugarId());
        if(lugar == null) {
            return null; // Manejar el caso donde el lugar no existe
        }
        actividad.setLugar(lugar);
        Evento evento = obtenerEventoPorId(actividadDTO.getIdEvento());
        actividad.setEvento(evento);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(actividad);

            evento.getActividades().add(actividad);
            entityManager.merge(evento); // Actualizar el evento con la nueva actividad
            entityManager.getTransaction().commit();
            return actividad;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    private Responsable obtenerResponsablePorId(int idResponsable) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Responsable responsable = entityManager.find(Responsable.class, idResponsable);
        return responsable;
    }

    private Lugar obtenerLugarPorId(int idLugar) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Lugar lugar = entityManager.find(Lugar.class, idLugar);
        return lugar;
    }

    private Evento obtenerEventoPorId(int idEvento) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Evento evento = entityManager.find(Evento.class, idEvento);
        return evento;
    }

    @Override
    public ActividadDTO buscarActividadPorId(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Actividad actividad = entityManager.find(Actividad.class, id);
        List<Integer> participantesIds = actividad.getParticipantes().stream()
                .map(participante -> participante.getId()).toList();
        ActividadDTO actividadDTO = new ActividadDTO(
                actividad.getId(),
                actividad.getNombre(),
                actividad.getTipoActividad(),
                actividad.getFechaInicio(),
                actividad.getDuracionEstimada(),
                actividad.getCapacidadMaxima(),
                actividad.getEstado(),
                actividad.getLugar().getId(),
                actividad.getResponsable().getId(),
                participantesIds,
                actividad.getEvento().getId());
        return actividadDTO;

    }

    @Override
    public boolean eliminarActividad(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        try {
            entityManager.getTransaction().begin();
            Actividad actividad = entityManager.find(Actividad.class, id);
            if (actividad != null) {
                Evento evento = actividad.getEvento(); 
                if (evento != null) {
                    evento.getActividades().remove(actividad);
                    entityManager.merge(evento); 
                }
                entityManager.remove(actividad); 
                entityManager.getTransaction().commit();
                return true;
            } else {
                entityManager.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean modificarEstadoActividadDTO(int idActividad, EstadoActividad estadoActividad) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Actividad actividad = entityManager.find(Actividad.class, idActividad);
        if (actividad != null) {
            try {
                entityManager.getTransaction().begin();
                actividad.setEstado(estadoActividad);
                entityManager.merge(actividad);
                entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
                e.printStackTrace();
            } finally {
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantesPorActividad(int idActividad) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Actividad actividad = entityManager.find(Actividad.class, idActividad);
        List<ParticipanteDTO> participantesDTO = actividad.getParticipantes().stream()
                .map(participante -> new ParticipanteDTO(participante.getId(), participante.getNombre(),
                        participante.getApellidoPaterno(), participante.getApellidoMaterno(),
                        participante.getCorreo(), participante.getTipoParticipante(), 
                        participante.getDependencia(), participante.getNumeroControl()))
                .toList();
        return participantesDTO;
    }

}
