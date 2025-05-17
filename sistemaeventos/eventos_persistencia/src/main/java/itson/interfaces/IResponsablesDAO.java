package itson.interfaces;

import java.util.List;

import itson.dtos.ResponsableDTO;
import itson.enums.TipoResponsable;

public interface IResponsablesDAO {

    /**
     * Guarda un responsable en la base de datos.
     *
     * @param responsable El responsable a guardar.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    boolean guardarResponsable(ResponsableDTO responsable);

    /**
     * Busca un responsable por su ID.
     *
     * @param id El ID del responsable a buscar.
     * @return El responsable encontrado o null si no se encontró.
     */
    ResponsableDTO buscarResponsablePorId(int id);

    /**
     * Elimina un responsable de la base de datos.
     *
     * @param id El ID del responsable a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    boolean eliminarResponsable(int id);

    /**
     * Obtiene la lista de todos los responsables.
     *
     * @return Lista de responsables.
     */
    List<ResponsableDTO> obtenerResponsables();

    /**
     * Busca responsables por su tipo.
     *
     * @param tipoResponsable El tipo de responsable a buscar.
     * @return Lista de responsables del tipo especificado.
     */    
    List<ResponsableDTO> obtenerResponsablesPorTipo(TipoResponsable tipoResponsable);
}
