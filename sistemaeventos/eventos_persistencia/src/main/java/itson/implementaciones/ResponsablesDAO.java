package itson.implementaciones;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import itson.conexion.ManejadorConexiones;
import itson.dtos.ResponsableDTO;
import itson.entidades.Responsable;
import itson.enums.TipoResponsable;
import itson.interfaces.IResponsablesDAO;

public class ResponsablesDAO implements IResponsablesDAO {

    @Override
    public boolean guardarResponsable(ResponsableDTO responsable) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Responsable nuevoResponsable = new Responsable();
        nuevoResponsable.setNombre(responsable.getNombre());
        nuevoResponsable.setApellidoPaterno(responsable.getApellidoPaterno());
        nuevoResponsable.setApellidoMaterno(responsable.getApellidoMaterno());
        nuevoResponsable.setTelefono(responsable.getTelefono());
        nuevoResponsable.setTipoResponsable(responsable.getTipoResponsable());
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nuevoResponsable);
            entityManager.getTransaction().commit();
            return true;
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

    @Override
    public boolean eliminarResponsable(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Responsable responsable = entityManager.find(Responsable.class, id);
        if (responsable != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(responsable);
                entityManager.getTransaction().commit();
                return true;
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
        return false;
    }

    @Override
    public List<ResponsableDTO> obtenerResponsablesPorTipo(TipoResponsable tipoResponsable) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        List<ResponsableDTO> responsablesDTO = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ResponsableDTO> cq = cb.createQuery(ResponsableDTO.class);
            Root<Responsable> root = cq.from(Responsable.class);
            cq.select(cb.construct(ResponsableDTO.class, root.get("id"), root.get("nombre"), root.get("apellidoPaterno"),
                    root.get("apellidoMaterno"), root.get("telefono"), root.get("tipoResponsable")))
                    .where(cb.equal(root.get("tipoResponsable"), tipoResponsable));
            responsablesDTO = entityManager.createQuery(cq).getResultList();
            return responsablesDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ResponsableDTO buscarResponsablePorId(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Responsable responsable = entityManager.find(Responsable.class, id);
        if (responsable != null) {
            return new ResponsableDTO(responsable.getId(), responsable.getNombre(), responsable.getApellidoPaterno(),
                    responsable.getApellidoMaterno(), responsable.getTelefono(), responsable.getTipoResponsable());
        }
        return null;
    }

    @Override
    public List<ResponsableDTO> obtenerResponsables() {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        List<ResponsableDTO> responsablesDTO = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ResponsableDTO> cq = cb.createQuery(ResponsableDTO.class);
            Root<Responsable> root = cq.from(Responsable.class);
            cq.select(cb.construct(ResponsableDTO.class, root.get("id"), root.get("nombre"), root.get("apellidoPaterno"),
                    root.get("apellidoMaterno"), root.get("telefono"), root.get("tipoResponsable")));
            responsablesDTO = entityManager.createQuery(cq).getResultList();
            return responsablesDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

}
