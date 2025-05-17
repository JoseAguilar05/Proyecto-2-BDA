package itson.implementaciones;

import java.util.List;

import itson.dtos.ResponsableDTO;
import itson.enums.TipoResponsable;
import itson.excepciones.NegocioException;
import itson.interfaces.IResponsablesBO;
import itson.interfaces.IResponsablesDAO;

public class ResponsablesBO implements IResponsablesBO{

    private IResponsablesDAO responsablesDAO;

    public ResponsablesBO(IResponsablesDAO responsablesDAO) {
        this.responsablesDAO = responsablesDAO;
        inicializarResponsables();
    }

    private void inicializarResponsables() {
        List<ResponsableDTO> responsables = obtenerResponsables();
        if (responsables == null || responsables.isEmpty()) {
            // Cargar responsables por defecto
            responsablesDAO.guardarResponsable(new ResponsableDTO("Juan", "Pérez", "Gómez", "1234567890", TipoResponsable.RESPONSABLE));
            responsablesDAO.guardarResponsable(new ResponsableDTO("María", "López", "Hernández", "0987654321", TipoResponsable.PONENTE));
            responsablesDAO.guardarResponsable(new ResponsableDTO("Carlos", "García", "Martínez", "1122334455", TipoResponsable.PONENTE));
            responsablesDAO.guardarResponsable(new ResponsableDTO("Ana", "Sánchez", "Ramírez", "5566778899", TipoResponsable.RESPONSABLE));
            responsablesDAO.guardarResponsable(new ResponsableDTO("Luis", "Torres", "Jiménez", "2233445566", TipoResponsable.PONENTE));
            responsablesDAO.guardarResponsable(new ResponsableDTO("Laura", "Mendoza", "Salazar", "3344556677", TipoResponsable.RESPONSABLE));
            responsablesDAO.guardarResponsable(new ResponsableDTO("Pedro", "Ramírez", "Cruz", "4455667788", TipoResponsable.PONENTE));
            responsablesDAO.guardarResponsable(new ResponsableDTO("Sofía", "Reyes", "Gutiérrez", "5566778899", TipoResponsable.RESPONSABLE));
            responsablesDAO.guardarResponsable(new ResponsableDTO("Diego", "Vázquez", "Morales", "6677889900", TipoResponsable.PONENTE));
            responsablesDAO.guardarResponsable(new ResponsableDTO("Valeria", "Cervantes", "Pérez", "7788990011", TipoResponsable.RESPONSABLE));            
        }
    }

    @Override
    public List<ResponsableDTO> obtenerResponsables() {
        List<ResponsableDTO> responsables = responsablesDAO.obtenerResponsables();
        return responsables;
    }

    @Override
    public ResponsableDTO obtenerResponsablePorId(int id) throws NegocioException {
        ResponsableDTO responsable = responsablesDAO.buscarResponsablePorId(id);
        if (responsable == null) {
            throw new NegocioException("Responsable no encontrado");
        }
        return responsable;
    }

    @Override
    public List<ResponsableDTO> obtenerResponsablesPorTipo(TipoResponsable tipoResponsable) throws NegocioException {
        List<ResponsableDTO> responsables = responsablesDAO.obtenerResponsablesPorTipo(tipoResponsable);
        if (responsables == null || responsables.isEmpty()) {
            throw new NegocioException("No se encontraron responsables del tipo especificado");
        }
        return responsables;
    }


}
