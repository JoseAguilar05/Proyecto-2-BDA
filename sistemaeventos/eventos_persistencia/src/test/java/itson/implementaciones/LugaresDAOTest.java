package itson.implementaciones;

import itson.conexion.ManejadorConexiones;
import itson.dtos.LugarDTO;
import itson.entidades.Lugar; // Necesitarás la entidad Lugar para la limpieza
import itson.interfaces.ILugaresDAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager; // Para obtener el ID del lugar guardado

public class LugaresDAOTest {

    private final ILugaresDAO lugaresDAO = new LugaresDAO();
    private Lugar lugarGuardado; // Para guardar la entidad y poder obtener su ID para limpieza

    public LugaresDAOTest() {
    }

    @BeforeAll
    public static void activarModoPruebas() {
        ManejadorConexiones.activateTestMode();
    }

    @AfterAll
    public static void desactivarModoPruebas() {
        ManejadorConexiones.deactivateTestMode();
    }

    @AfterEach
    public void tearDown() {
        if (lugarGuardado != null && lugarGuardado.getId() != null) {
            try {
                lugaresDAO.eliminarLugar(lugarGuardado.getId());
            } catch (Exception e) {
                System.err.println("Error al limpiar lugarGuardado con ID " + lugarGuardado.getId() + ": " + e.getMessage());
            }
        }
        lugarGuardado = null; 
    }

    private Lugar guardarLugarParaTest(LugarDTO dto) {
        lugaresDAO.guardarLugar(dto);
        EntityManager em = ManejadorConexiones.obtenerConexion();
        try {
            List<Lugar> lugares = em.createQuery("SELECT l FROM Lugar l WHERE l.nombre = :nombre AND l.edificio = :edificio ORDER BY l.id DESC", Lugar.class)
                                    .setParameter("nombre", dto.getNombre())
                                    .setParameter("edificio", dto.getEdificio())
                                    .setMaxResults(1)
                                    .getResultList();
            if (!lugares.isEmpty()) {
                return lugares.get(0);
            }
        } finally {
            em.close();
        }
        return null; // No se pudo recuperar
    }


    @Test
    public void testGuardarLugar() {
        System.out.println("guardarLugar");
        LugarDTO lugarDTO = new LugarDTO(null, "Auditorio Principal", "Edificio A");
        boolean result = lugaresDAO.guardarLugar(lugarDTO);
        assertTrue(result, "El lugar debería guardarse correctamente.");

        EntityManager em = ManejadorConexiones.obtenerConexion();
        try {
            Lugar l = em.createQuery("SELECT l FROM Lugar l WHERE l.nombre = :nombre AND l.edificio = :edificio", Lugar.class)
                        .setParameter("nombre", "Auditorio Principal")
                        .setParameter("edificio", "Edificio A")
                        .getSingleResult();
            lugarGuardado = l; 
        } catch (Exception e) {
            fail("No se pudo recuperar el lugar guardado para la limpieza: " + e.getMessage());
        } finally {
            em.close();
        }

        assertNotNull(lugarGuardado, "El lugar guardado no debería ser nulo después de la recuperación.");
        assertEquals("Auditorio Principal", lugarGuardado.getNombre());
        assertEquals("Edificio A", lugarGuardado.getEdificio());
    }

    @Test
    public void testBuscarLugarPorId() {
        System.out.println("buscarLugarPorId");
        LugarDTO lugarDTO = new LugarDTO(null, "Sala de Conferencias B1", "Edificio B");
        lugarGuardado = guardarLugarParaTest(lugarDTO); // Usar el helper
        assertNotNull(lugarGuardado, "No se pudo guardar y recuperar el lugar para la prueba.");
        assertNotNull(lugarGuardado.getId(), "El ID del lugar guardado no debería ser nulo.");


        LugarDTO result = lugaresDAO.buscarLugarPorId(lugarGuardado.getId());
        assertNotNull(result, "Debería encontrar el lugar por ID.");
        assertEquals(lugarGuardado.getId(), result.getId());
        assertEquals("Sala de Conferencias B1", result.getNombre());
        assertEquals("Edificio B", result.getEdificio());
    }

    @Test
    public void testBuscarLugarPorIdNoExistente() {
        System.out.println("buscarLugarPorIdNoExistente");
        LugarDTO result = lugaresDAO.buscarLugarPorId(-99); // Un ID que seguramente no existe
        assertNull(result, "No debería encontrar un lugar con un ID inexistente.");
    }

    @Test
    public void testObtenerLugares() {
        System.out.println("obtenerLugares");

        // Guardar algunos lugares para asegurarse de que la lista no esté vacía
        LugarDTO lugar1DTO = new LugarDTO(null, "Laboratorio C1", "Edificio C");
        LugarDTO lugar2DTO = new LugarDTO(null, "Aula Magna", "Edificio Principal");
        
        Lugar l1 = guardarLugarParaTest(lugar1DTO);
        Lugar l2 = guardarLugarParaTest(lugar2DTO);

        List<LugarDTO> result = lugaresDAO.obtenerLugares();
        assertNotNull(result, "La lista de lugares no debería ser nula.");
        assertFalse(result.isEmpty(), "La lista de lugares no debería estar vacía después de añadir algunos.");

        // Verificar que los lugares añadidos están en la lista (o al menos que hay la cantidad esperada)
        assertTrue(result.stream().anyMatch(l -> l.getNombre().equals("Laboratorio C1") && l.getEdificio().equals("Edificio C")));
        assertTrue(result.stream().anyMatch(l -> l.getNombre().equals("Aula Magna") && l.getEdificio().equals("Edificio Principal")));
        
        // Limpieza manual para estos lugares si no se asignan a lugarGuardado
        if (l1 != null) lugaresDAO.eliminarLugar(l1.getId());
        if (l2 != null) lugaresDAO.eliminarLugar(l2.getId());
    }
    
    @Test
    public void testObtenerLugaresCuandoEstaVacia() {
        System.out.println("obtenerLugaresCuandoEstaVacia");
        // Asegurarse de que la tabla esté vacía (esto es difícil sin un método de limpieza total)
        // Por ahora, asumimos que puede estar vacía o probamos en un estado conocido.
        // Si se ejecutan otros tests antes, esta prueba podría no ser fiable sin una limpieza total previa.
        // Una opción es limpiar todo antes de este test específico.
        
        // Limpiar todos los lugares existentes para esta prueba específica
        List<LugarDTO> lugaresActuales = lugaresDAO.obtenerLugares();
        for (LugarDTO lugarExistente : lugaresActuales) {
            lugaresDAO.eliminarLugar(lugarExistente.getId());
        }

        List<LugarDTO> result = lugaresDAO.obtenerLugares();
        assertNotNull(result, "La lista de lugares no debería ser nula.");
        assertTrue(result.isEmpty(), "La lista de lugares debería estar vacía.");
    }


    @Test
    public void testEliminarLugar() {
        System.out.println("eliminarLugar");
        LugarDTO lugarDTO = new LugarDTO(null, "Oficina D2", "Edificio D");
        Lugar tempLugarGuardado = guardarLugarParaTest(lugarDTO); 
        assertNotNull(tempLugarGuardado, "No se pudo guardar y recuperar el lugar para la prueba de eliminación.");
        assertNotNull(tempLugarGuardado.getId(), "El ID del lugar a eliminar no puede ser nulo.");

        boolean result = lugaresDAO.eliminarLugar(tempLugarGuardado.getId());
        assertTrue(result, "Debería eliminar el lugar correctamente.");

        LugarDTO verificado = lugaresDAO.buscarLugarPorId(tempLugarGuardado.getId());
        assertNull(verificado, "El lugar no debería encontrarse después de ser eliminado.");
        
    }

    @Test
    public void testEliminarLugarNoExistente() {
        System.out.println("eliminarLugarNoExistente");
        boolean result = lugaresDAO.eliminarLugar(-98); // Un ID que seguramente no existe
        assertFalse(result, "No debería poder eliminar un lugar con un ID inexistente.");
    }
}