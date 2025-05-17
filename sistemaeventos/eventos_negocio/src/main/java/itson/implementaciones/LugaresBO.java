package itson.implementaciones;

import java.util.List;

import itson.dtos.LugarDTO;
import itson.excepciones.NegocioException;
import itson.interfaces.ILugaresBO;
import itson.interfaces.ILugaresDAO;

public class LugaresBO implements ILugaresBO{

    private ILugaresDAO lugaresDAO;

    public LugaresBO(ILugaresDAO lugaresDAO) {
        this.lugaresDAO = lugaresDAO;
        inicializarLugares();
    }

    private void inicializarLugares() {
        List<LugarDTO> lugares = obtenerLugares();
        if (lugares == null || lugares.isEmpty()) {
            // Cargar lugares por defecto
            lugaresDAO.guardarLugar(new LugarDTO("Auditorio", "Edificio A"));
            lugaresDAO.guardarLugar(new LugarDTO("Sala de Conferencias", "Edificio B"));
            lugaresDAO.guardarLugar(new LugarDTO("Laboratorio", "Edificio C"));
            lugaresDAO.guardarLugar(new LugarDTO("Sala de Reuniones", "Edificio D"));
            lugaresDAO.guardarLugar(new LugarDTO("Teatro", "Edificio A"));
            lugaresDAO.guardarLugar(new LugarDTO("Gimnasio", "Edificio B"));
            lugaresDAO.guardarLugar(new LugarDTO("Cafetería", "Edificio C"));
            lugaresDAO.guardarLugar(new LugarDTO("Biblioteca", "Edificio A"));
            lugaresDAO.guardarLugar(new LugarDTO("Sala de Estudio", "Edificio D"));
        }
    }

    

    @Override
    public List<LugarDTO> obtenerLugares() {
        return lugaresDAO.obtenerLugares();
    }

    @Override
    public LugarDTO obtenerLugarPorId(int id) throws NegocioException {
        LugarDTO lugar = lugaresDAO.buscarLugarPorId(id);
        if (lugar == null) {
            throw new NegocioException("Lugar no encontrado");
        }
        return lugar;
    }

}
