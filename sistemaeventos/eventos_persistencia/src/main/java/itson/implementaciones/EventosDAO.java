package itson.implementaciones;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import itson.conexion.ManejadorConexiones;
import itson.dtos.ActividadDTO;
import itson.dtos.EventoDTO;
import itson.entidades.Actividad;
import itson.entidades.Evento;
import itson.entidades.Lugar;
import itson.entidades.Responsable;
import itson.enums.EstadoEvento;
import itson.interfaces.IEventosDAO;

public class EventosDAO implements IEventosDAO {

    @Override
    public Evento guardarEventoConActividades(EventoDTO eventoDTO, List<ActividadDTO> actividades) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Evento evento = new Evento();
        evento.setTitulo(eventoDTO.getTitulo());
        evento.setDescripcion(eventoDTO.getDescripcion());
        evento.setFechaInicio(eventoDTO.getFechaInicio());
        evento.setFechaFin(eventoDTO.getFechaFin());
        evento.setModalidad(eventoDTO.getModalidad());
        evento.setEstado(EstadoEvento.PLANEADO);
        Responsable responsable = buscarResponsablePorId(eventoDTO.getResponsableId());
        if (responsable == null) {
            throw new IllegalArgumentException("Responsable no encontrado");
        } else {
            evento.setResponsable(responsable);
        }
        List<Actividad> listaActividades = new LinkedList<>(); 
        for(ActividadDTO actividad : actividades) {
            Actividad actividadEntity = new Actividad();
            actividadEntity.setNombre(actividad.getNombre());
            actividadEntity.setTipoActividad(actividad.getTipoActividad());
            actividadEntity.setFechaInicio(actividad.getFechaInicio());
            actividadEntity.setDuracionEstimada(actividad.getDuracionEstimada());
            Lugar lugar = buscarLugarPorId(actividad.getLugarId());
            if(lugar == null) {
                throw new IllegalArgumentException("Lugar no encontrado");
            } else {
                actividadEntity.setLugar(lugar);
            }
            Responsable responsableActividad = buscarResponsablePorId(actividad.getResponsableId());
            if(responsableActividad == null) {
                throw new IllegalArgumentException("Responsable no encontrado");
            } else {
                actividadEntity.setResponsable(responsableActividad);
            }
            listaActividades.add(actividadEntity);
        }
        evento.setActividades(listaActividades);

        try {
            entityManager.getTransaction().begin();
            // Guardar el evento
            entityManager.persist(evento);
            entityManager.getTransaction().commit();
            return evento;
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

    private Responsable buscarResponsablePorId(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Responsable responsable = entityManager.find(Responsable.class, id);
        if (responsable != null) {
            return responsable;
        }
        return null;
    }

    private Lugar buscarLugarPorId(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Lugar lugar = entityManager.find(Lugar.class, id);
        if (lugar != null) {
            return lugar;
        }
        return null;
    }

    @Override
    public EventoDTO buscarEventoPorId(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Evento evento = entityManager.find(Evento.class, id);
        if(evento != null) {
            List<Integer> actividadesIds = new LinkedList<>();
            for (Actividad actividad : evento.getActividades()) {
                actividadesIds.add(actividad.getId());
            }
            EventoDTO eventoDTO = new EventoDTO(
                evento.getId(),
                evento.getTitulo(),
                evento.getDescripcion(),
                evento.getFechaInicio(),
                evento.getFechaFin(),
                evento.getEstado(),
                evento.getModalidad(),
                actividadesIds,
                evento.getResponsable().getId()
            );
            return eventoDTO;
        }
        return null;
    }

    @Override
    public List<EventoDTO> obtenerEventos() {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        List<EventoDTO> eventosDTO = new LinkedList<>();
        try {
            List<Evento> eventos = entityManager.createQuery("SELECT e FROM Evento e", Evento.class).getResultList();
            for (Evento evento : eventos) {
                List<Integer> actividadesIds = new LinkedList<>();
                for (Actividad actividad : evento.getActividades()) {
                    actividadesIds.add(actividad.getId());
                }
                EventoDTO eventoDTO = new EventoDTO(
                    evento.getId(),
                    evento.getTitulo(),
                    evento.getDescripcion(),
                    evento.getFechaInicio(),
                    evento.getFechaFin(),
                    evento.getEstado(),
                    evento.getModalidad(),
                    actividadesIds,
                    evento.getResponsable().getId()
                );
                eventosDTO.add(eventoDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return eventosDTO;
    }

    @Override
    public boolean eliminarEvento(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        try {
            entityManager.getTransaction().begin();
            Evento evento = entityManager.find(Evento.class, id);
            if (evento != null) {
                entityManager.remove(evento);
                entityManager.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    
}
