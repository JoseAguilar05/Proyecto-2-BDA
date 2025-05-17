package itson.interfaces;

import java.util.List;

import itson.dtos.LugarDTO;
import itson.excepciones.NegocioException;

public interface ILugaresBO {

    /**
     * Obtiene la lista de todos los lugares
     * @return Lista de lugares
     */
    List<LugarDTO> obtenerLugares();

    /**
     * Obtiene un lugar por su ID.
     * @param id ID del lugar a buscar.
     * @return LugarDTO con la información del lugar encontrado, o null si no se encontró.
     * @throws NegocioException Si ocurre un error al buscar el lugar.   
     */
    LugarDTO obtenerLugarPorId(int id) throws NegocioException;

}
