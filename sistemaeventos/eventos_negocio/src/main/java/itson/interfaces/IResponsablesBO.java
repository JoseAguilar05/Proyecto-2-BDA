package itson.interfaces;

import java.util.List;

import itson.dtos.ResponsableDTO;
import itson.enums.TipoResponsable;
import itson.excepciones.NegocioException;

public interface IResponsablesBO {

    /**
     * Obtiene la lista de todos los responsables.
     * @return Lista de responsables.
     */
    List<ResponsableDTO> obtenerResponsables();

    /**
     * Obtiene un responsable por su ID.
     * @param id ID del responsable a buscar.
     * @return ResponsableDTO con la información del responsable encontrado, o null si no se encontró.
     * @throws NegocioException Si no se encuentra el responsable o si ocurre un error al buscarlo.  
     */
    ResponsableDTO obtenerResponsablePorId(int id) throws NegocioException;

    /**
     * Obtiene la lista de responsables por su tipo.
     * @param tipoResponsable Tipo de responsable a buscar.
     * @return Lista de responsables del tipo especificado.
     * @throws NegocioException Si no se encuentran responsables o si ocurre un error al buscarlos.
     */
    List<ResponsableDTO> obtenerResponsablesPorTipo(TipoResponsable tipoResponsable) throws NegocioException;
}
