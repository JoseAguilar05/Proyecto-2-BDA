package itson.interfaces;

import java.util.List;

import itson.dtos.LugarDTO;

public interface ILugaresDAO {
    /**
     * Guarda un lugar en la base de datos.
     *
     * @param lugarDTO El lugar a guardar.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    boolean guardarLugar(LugarDTO lugar);

    /**
     * Busca un lugar por su ID.
     *
     * @param id El ID del lugar a buscar.
     * @return El lugar encontrado o null si no se encontró.
     */
    LugarDTO buscarLugarPorId(int id);

    /**
     * Obtiene la lista de todos los lugares
     * @return Lista de lugares
     */
    List<LugarDTO> obtenerLugares();

    /**
     * Elimina un lugar de la base de datos.
     *
     * @param id El ID del lugar a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    boolean eliminarLugar(int id);
}
