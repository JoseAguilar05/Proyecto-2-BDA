package itson.implementaciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import itson.conexion.ManejadorConexiones;
import itson.dtos.ParticipanteDTO;
import itson.entidades.Participante;
import itson.enums.TipoParticipante;
import itson.excepciones.SeguridadException;
import itson.interfaces.IParticipantesDAO;
import itson.seguridad.Seguridad;

public class ParticipantesDAO implements IParticipantesDAO {

    @Override
    public Participante registrarParticipante(ParticipanteDTO participanteDTO) throws SeguridadException {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        entityManager.getTransaction().begin();

        Participante participante = new Participante();
        participante.setNombre(participanteDTO.getNombre());
        participante.setApellidoPaterno(participanteDTO.getApellidoPaterno());
        participante.setApellidoMaterno(participanteDTO.getApellidoMaterno());
        participante.setCorreo(Seguridad.encriptar(participanteDTO.getCorreo()));
        participante.setTipoParticipante(participanteDTO.getTipoParticipante());
        if (participanteDTO.getDependencia() != null) {
            participante.setDependencia(participanteDTO.getDependencia());
        }
        if (participanteDTO.getNumeroControl() != null) {
            participante.setNumeroControl(Seguridad.encriptar(participanteDTO.getNumeroControl()));
        }
        entityManager.persist(participante);
        entityManager.getTransaction().commit();
        entityManager.close();
        return participante;

    }

    @Override
    public ParticipanteDTO obtenerParticipantePorId(Integer id) throws SeguridadException {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        entityManager.getTransaction().begin();
        TypedQuery<Participante> query = entityManager.createQuery("SELECT p FROM Participante p WHERE p.id = :id",
                Participante.class);
        query.setParameter("id", id);
        List<Participante> resultados = query.getResultList();
        if (resultados.isEmpty()) {
            return null;
        }

        Participante participante = resultados.get(0);
        entityManager.getTransaction().commit();
        entityManager.close();
        if (participante != null) {
            if (participante.getNumeroControl() != null) {
                participante.setNumeroControl(Seguridad.desencriptar(participante.getNumeroControl()));
            }
            participante.setCorreo(Seguridad.desencriptar(participante.getCorreo()));
            return new ParticipanteDTO(participante.getId(), participante.getNombre(),
                    participante.getApellidoPaterno(),
                    participante.getApellidoMaterno(), participante.getCorreo(),
                    participante.getTipoParticipante(), participante.getDependencia(),
                    participante.getNumeroControl());
        } else {
            return null;
        }
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantesConFiltro(String filtro) throws SeguridadException {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Participante> cq = cb.createQuery(Participante.class);
            Root<Participante> root = cq.from(Participante.class);

            // Simplemente ordenamos, pero no filtramos en la BD por el 'filtro' general
            cq.orderBy(cb.asc(root.get("nombre")));

            // Obtenemos todos los participantes (o una lista menos restringida)
            List<Participante> participantes = entityManager.createQuery(cq).getResultList();
            List<ParticipanteDTO> participantesDTO = new ArrayList<>();

            String filtroLower = filtro.toLowerCase(); // Convertir a minúsculas una vez

            for (Participante participante : participantes) {
                String correoDesencriptado = Seguridad.desencriptar(participante.getCorreo());
                String numeroControlDesencriptado = participante.getNumeroControl() != null
                        ? Seguridad.desencriptar(participante.getNumeroControl())
                        : null;

                // Verificar coincidencias en todos los campos
                if (participante.getNombre().toLowerCase().contains(filtroLower) ||
                        participante.getApellidoPaterno().toLowerCase().contains(filtroLower) ||
                        participante.getApellidoMaterno().toLowerCase().contains(filtroLower) ||
                        correoDesencriptado.toLowerCase().contains(filtroLower) ||
                        (numeroControlDesencriptado != null &&
                                numeroControlDesencriptado.toLowerCase().contains(filtroLower))) {

                    ParticipanteDTO dto = new ParticipanteDTO(
                            participante.getId(),
                            participante.getNombre(),
                            participante.getApellidoPaterno(),
                            participante.getApellidoMaterno(),
                            correoDesencriptado,
                            participante.getTipoParticipante(),
                            participante.getDependencia(),
                            numeroControlDesencriptado);
                    participantesDTO.add(dto);
                }
            }

            return participantesDTO;
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().commit();
            }
            entityManager.close();
        }
    }

    @Override
    public boolean eliminarParticipante(Integer id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        entityManager.getTransaction().begin();
        Participante participante = entityManager.find(Participante.class, id);
        if (participante != null) {
            entityManager.remove(participante);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return false;
        }
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantesConFiltroYTipo(String filtro, TipoParticipante tipo)
            throws SeguridadException {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Participante> cq = cb.createQuery(Participante.class);
            Root<Participante> root = cq.from(Participante.class);

            List<Predicate> predicates = new ArrayList<>();

            // 1. Añadir predicado para filtrar por TipoParticipante si se proporciona
            if (tipo != null) {
                predicates.add(cb.equal(root.get("tipoParticipante"), tipo));
            }

            // Aplicar los predicados a la consulta si hay alguno
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[0]));
            }

            cq.orderBy(cb.asc(root.get("nombre")));

            // Obtenemos los participantes filtrados por tipo (si se especificó)
            List<Participante> participantesFiltradosPorTipo = entityManager.createQuery(cq).getResultList();
            List<ParticipanteDTO> participantesDTO = new ArrayList<>();

            String filtroLower = filtro.toLowerCase(); // Convertir a minúsculas una vez

            // 2. Aplicar el filtro de texto en memoria sobre los resultados anteriores
            for (Participante participante : participantesFiltradosPorTipo) {
                String correoDesencriptado = Seguridad.desencriptar(participante.getCorreo());
                String numeroControlDesencriptado = participante.getNumeroControl() != null
                        ? Seguridad.desencriptar(participante.getNumeroControl())
                        : null;

                // Verificar coincidencias con el filtro de texto
                boolean coincideFiltroTexto = false;
                if (filtroLower.isEmpty()) { // Si el filtro de texto está vacío, consideramos que coincide
                    coincideFiltroTexto = true;
                } else {
                    coincideFiltroTexto = participante.getNombre().toLowerCase().contains(filtroLower) ||
                            participante.getApellidoPaterno().toLowerCase().contains(filtroLower) ||
                            participante.getApellidoMaterno().toLowerCase().contains(filtroLower) ||
                            correoDesencriptado.toLowerCase().contains(filtroLower) ||
                            (numeroControlDesencriptado != null &&
                                    numeroControlDesencriptado.toLowerCase().contains(filtroLower));
                }

                if (coincideFiltroTexto) {
                    ParticipanteDTO dto = new ParticipanteDTO(
                            participante.getId(),
                            participante.getNombre(),
                            participante.getApellidoPaterno(),
                            participante.getApellidoMaterno(),
                            correoDesencriptado,
                            participante.getTipoParticipante(),
                            participante.getDependencia(),
                            numeroControlDesencriptado);
                    participantesDTO.add(dto);
                }
            }

            return participantesDTO;
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().commit();
            }
            entityManager.close();
        }
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantes() throws SeguridadException {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        entityManager.getTransaction().begin();
        TypedQuery<Participante> query = entityManager.createQuery("SELECT p FROM Participante p", Participante.class);
        List<Participante> resultados = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        List<ParticipanteDTO> participantesDTO = new ArrayList<>();
        for (Participante participante : resultados) {
            if (participante.getNumeroControl() != null) {
                participante.setNumeroControl(Seguridad.desencriptar(participante.getNumeroControl()));
            }
            participante.setCorreo(Seguridad.desencriptar(participante.getCorreo()));
            participantesDTO.add(new ParticipanteDTO(participante.getId(), participante.getNombre(),
                    participante.getApellidoPaterno(),
                    participante.getApellidoMaterno(), participante.getCorreo(),
                    participante.getTipoParticipante(), participante.getDependencia(),
                    participante.getNumeroControl()));
        }
        return participantesDTO;
    }

    @Override
    public List<ParticipanteDTO> obtenerParticipantesPorTipo(TipoParticipante tipo) throws SeguridadException {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        entityManager.getTransaction().begin();
        TypedQuery<Participante> query = entityManager.createQuery("SELECT p FROM Participante p WHERE p.tipoParticipante = :tipo",
                Participante.class);
        query.setParameter("tipo", tipo);
        List<Participante> resultados = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        List<ParticipanteDTO> participantesDTO = new ArrayList<>();
        for (Participante participante : resultados) {
            if (participante.getNumeroControl() != null) {
                participante.setNumeroControl(Seguridad.desencriptar(participante.getNumeroControl()));
            }
            participante.setCorreo(Seguridad.desencriptar(participante.getCorreo()));
            participantesDTO.add(new ParticipanteDTO(participante.getId(), participante.getNombre(),
                    participante.getApellidoPaterno(),
                    participante.getApellidoMaterno(), participante.getCorreo(),
                    participante.getTipoParticipante(), participante.getDependencia(),
                    participante.getNumeroControl()));
        }
        return participantesDTO;
    }

}
