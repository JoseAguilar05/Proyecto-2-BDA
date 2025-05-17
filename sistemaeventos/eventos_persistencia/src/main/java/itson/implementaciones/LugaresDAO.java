package itson.implementaciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import itson.conexion.ManejadorConexiones;
import itson.dtos.LugarDTO;
import itson.entidades.Lugar;
import itson.interfaces.ILugaresDAO;

public class LugaresDAO implements ILugaresDAO {

    /**
     * Guarda un lugar en la base de datos.
     *
     * @param lugar El lugar a guardar.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    @Override
    public boolean guardarLugar(LugarDTO lugar) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Lugar nuevoLugar = new Lugar();
        nuevoLugar.setNombre(lugar.getNombre());
        nuevoLugar.setEdificio(lugar.getEdificio());
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nuevoLugar);
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

    /**
     * Busca un lugar por su ID.
     *
     * @param id El ID del lugar a buscar.
     * @return El lugar encontrado o null si no se encontró.
     */
    @Override
    public LugarDTO buscarLugarPorId(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Lugar lugar = entityManager.find(Lugar.class, id);
        if (lugar != null) {
            LugarDTO lugarDTO = new LugarDTO(lugar.getId(), lugar.getNombre(), lugar.getEdificio());
            return lugarDTO;
        }
        return null;
    }

    /**
     * Obtiene la lista de todos los lugares
     * @return Lista de lugares
     */
    @Override
    public List<LugarDTO> obtenerLugares() {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        List<Lugar> lugares = entityManager.createQuery("SELECT l FROM Lugar l", Lugar.class).getResultList();
        List<LugarDTO> lugaresDTO = new ArrayList<>();
        for (Lugar lugar : lugares) {
            lugaresDTO.add(new LugarDTO(lugar.getId(), lugar.getNombre(), lugar.getEdificio()));
        }
        return lugaresDTO;
    }

    /**
     * Elimina un lugar de la base de datos.
     *
     * @param id El ID del lugar a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    @Override
    public boolean eliminarLugar(int id) {
        EntityManager entityManager = ManejadorConexiones.obtenerConexion();
        Lugar lugar = entityManager.find(Lugar.class, id);
        if (lugar == null) {
            return false; // El lugar no existe
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(lugar);
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

}
