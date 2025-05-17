package itson.implementaciones;

import itson.conexion.ManejadorConexiones;
import itson.dtos.ResponsableDTO;
import itson.entidades.Responsable; // Necesitarás la entidad Responsable
import itson.enums.TipoResponsable;
import itson.interfaces.IResponsablesDAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class ResponsablesDAOTest {

    private final IResponsablesDAO responsablesDAO = new ResponsablesDAO();
    private Responsable responsableGuardado; // Para guardar la entidad y poder obtener su ID para limpieza

    public ResponsablesDAOTest() {
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
        if (responsableGuardado != null && responsableGuardado.getId() != null) {
            try {
                responsablesDAO.eliminarResponsable(responsableGuardado.getId());
            } catch (Exception e) {
                System.err.println("Error al limpiar responsableGuardado con ID " + responsableGuardado.getId() + ": " + e.getMessage());
            }
        }
        responsableGuardado = null; // Resetear para el siguiente test
    }

    // Método helper para guardar un responsable y obtener la entidad completa con ID
    private Responsable guardarResponsableParaTest(ResponsableDTO dto) {
        responsablesDAO.guardarResponsable(dto);
        EntityManager em = ManejadorConexiones.obtenerConexion();
        try {
            // Intentar buscar por una combinación única, por ejemplo, teléfono si se espera que sea único
            // O por nombre completo si es suficientemente distintivo para el test.
            // Esta es la parte más delicada si el método guardar no devuelve la entidad/ID.
            String jpql = "SELECT r FROM Responsable r WHERE r.nombre = :nombre " +
                          "AND r.apellidoPaterno = :apellidoPaterno " +
                          "AND r.apellidoMaterno = :apellidoMaterno " +
                          "AND r.telefono = :telefono " +
                          "ORDER BY r.id DESC"; // Tomar el último en caso de múltiples inserciones rápidas (poco probable en test)
            List<Responsable> responsables = em.createQuery(jpql, Responsable.class)
                                    .setParameter("nombre", dto.getNombre())
                                    .setParameter("apellidoPaterno", dto.getApellidoPaterno())
                                    .setParameter("apellidoMaterno", dto.getApellidoMaterno())
                                    .setParameter("telefono", dto.getTelefono())
                                    .setMaxResults(1)
                                    .getResultList();
            if (!responsables.isEmpty()) {
                return responsables.get(0);
            }
        } catch (Exception e) {
            System.err.println("Error en guardarResponsableParaTest al recuperar: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        return null; // No se pudo recuperar
    }

    @Test
    public void testGuardarResponsable() {
        System.out.println("guardarResponsable");
        ResponsableDTO responsableDTO = new ResponsableDTO(null, "Juan", "Pérez", "Gómez", "6621234567", TipoResponsable.PONENTE);
        boolean result = responsablesDAO.guardarResponsable(responsableDTO);
        assertTrue(result, "El responsable debería guardarse correctamente.");

        // Recuperar para limpieza y verificación adicional
        responsableGuardado = guardarResponsableParaTest(responsableDTO); // Re-usar el helper para asegurar que se recupera el correcto

        assertNotNull(responsableGuardado, "El responsable guardado no debería ser nulo después de la recuperación.");
        assertEquals("Juan", responsableGuardado.getNombre());
        assertEquals("Pérez", responsableGuardado.getApellidoPaterno());
        assertEquals("Gómez", responsableGuardado.getApellidoMaterno());
        assertEquals("6621234567", responsableGuardado.getTelefono());
        assertEquals(TipoResponsable.PONENTE, responsableGuardado.getTipoResponsable());
    }

    @Test
    public void testBuscarResponsablePorId() {
        System.out.println("buscarResponsablePorId");
        ResponsableDTO responsableDTO = new ResponsableDTO(null, "Ana", "López", "Ruiz", "6627654321", TipoResponsable.RESPONSABLE);
        responsableGuardado = guardarResponsableParaTest(responsableDTO);
        assertNotNull(responsableGuardado, "No se pudo guardar y recuperar el responsable para la prueba.");
        assertNotNull(responsableGuardado.getId(), "El ID del responsable guardado no debería ser nulo.");

        ResponsableDTO resultDTO = responsablesDAO.buscarResponsablePorId(responsableGuardado.getId());
        assertNotNull(resultDTO, "Debería encontrar el responsable por ID.");
        assertEquals(responsableGuardado.getId(), resultDTO.getId());
        assertEquals("Ana", resultDTO.getNombre());
        assertEquals("López", resultDTO.getApellidoPaterno());
        assertEquals(TipoResponsable.RESPONSABLE, resultDTO.getTipoResponsable());
    }

    @Test
    public void testBuscarResponsablePorIdNoExistente() {
        System.out.println("buscarResponsablePorIdNoExistente");
        ResponsableDTO result = responsablesDAO.buscarResponsablePorId(-999); // Un ID que seguramente no existe
        assertNull(result, "No debería encontrar un responsable con un ID inexistente.");
    }

    @Test
    public void testObtenerResponsablesPorTipo() {
        System.out.println("obtenerResponsablesPorTipo");
        List<Responsable> creadosParaTest = new ArrayList<>();

        ResponsableDTO respLog1DTO = new ResponsableDTO(null, "Carlos", "Soto", "Vega", "6621112233", TipoResponsable.RESPONSABLE);
        ResponsableDTO respTec1DTO = new ResponsableDTO(null, "Laura", "Mora", "Sol", "6624445566", TipoResponsable.PONENTE);
        ResponsableDTO respLog2DTO = new ResponsableDTO(null, "Pedro", "Gil", "Luna", "6627778899", TipoResponsable.RESPONSABLE);

        creadosParaTest.add(guardarResponsableParaTest(respLog1DTO));
        creadosParaTest.add(guardarResponsableParaTest(respTec1DTO));
        creadosParaTest.add(guardarResponsableParaTest(respLog2DTO));
        responsableGuardado = creadosParaTest.get(0); // Asignar uno para el teardown general

        List<ResponsableDTO> logisticos = responsablesDAO.obtenerResponsablesPorTipo(TipoResponsable.RESPONSABLE);
        assertNotNull(logisticos);
        assertEquals(2, logisticos.size(), "Debería haber 2 coordinadores de logística.");
        assertTrue(logisticos.stream().allMatch(r -> r.getTipoResponsable() == TipoResponsable.RESPONSABLE));
        assertTrue(logisticos.stream().anyMatch(r -> r.getNombre().equals("Carlos")));
        assertTrue(logisticos.stream().anyMatch(r -> r.getNombre().equals("Pedro")));


        List<ResponsableDTO> tecnicos = responsablesDAO.obtenerResponsablesPorTipo(TipoResponsable.PONENTE);
        assertNotNull(tecnicos);
        assertEquals(1, tecnicos.size(), "Debería haber 1 coordinador técnico.");
        assertEquals(TipoResponsable.PONENTE, tecnicos.get(0).getTipoResponsable());
        assertEquals("Laura", tecnicos.get(0).getNombre());

        // Limpieza manual para los no asignados a responsableGuardado
        for (int i = 1; i < creadosParaTest.size(); i++) {
            if (creadosParaTest.get(i) != null && creadosParaTest.get(i).getId() != null) {
                responsablesDAO.eliminarResponsable(creadosParaTest.get(i).getId());
            }
        }
    }

    @Test
    public void testObtenerResponsables() {
        System.out.println("obtenerResponsables");
        List<Responsable> creadosParaTest = new ArrayList<>();

        ResponsableDTO r1DTO = new ResponsableDTO(null, "Miguel", "Rojas", "Paz", "6620001122", TipoResponsable.RESPONSABLE);
        ResponsableDTO r2DTO = new ResponsableDTO(null, "Sofia", "Vera", "Luz", "6623334455", TipoResponsable.PONENTE);
        
        creadosParaTest.add(guardarResponsableParaTest(r1DTO));
        creadosParaTest.add(guardarResponsableParaTest(r2DTO));
        responsableGuardado = creadosParaTest.get(0); // Asignar uno para el teardown general


        List<ResponsableDTO> result = responsablesDAO.obtenerResponsables();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // El número exacto dependerá de si otros tests dejaron datos y no se limpiaron completamente.
        // Es mejor verificar la presencia de los que acabamos de añadir.
        assertTrue(result.stream().anyMatch(r -> r.getNombre().equals("Miguel") && r.getTelefono().equals("6620001122")));
        assertTrue(result.stream().anyMatch(r -> r.getNombre().equals("Sofia") && r.getTelefono().equals("6623334455")));
        
        // Limpieza manual
         if (creadosParaTest.size() > 1 && creadosParaTest.get(1) != null && creadosParaTest.get(1).getId() != null) {
            responsablesDAO.eliminarResponsable(creadosParaTest.get(1).getId());
        }
    }
    
    @Test
    public void testObtenerResponsablesCuandoEstaVacia() {
        System.out.println("obtenerResponsablesCuandoEstaVacia");
        // Limpiar todos los responsables existentes para esta prueba específica
        List<ResponsableDTO> responsablesActuales = responsablesDAO.obtenerResponsables();
        for (ResponsableDTO respExistente : responsablesActuales) {
            responsablesDAO.eliminarResponsable(respExistente.getId());
        }

        List<ResponsableDTO> result = responsablesDAO.obtenerResponsables();
        assertNotNull(result);
        assertTrue(result.isEmpty(), "La lista de responsables debería estar vacía.");
    }

    @Test
    public void testEliminarResponsable() {
        System.out.println("eliminarResponsable");
        ResponsableDTO responsableDTO = new ResponsableDTO(null, "Temporal", "Borrar", "Este", "6629876543", TipoResponsable.RESPONSABLE);
        Responsable tempResponsableGuardado = guardarResponsableParaTest(responsableDTO);
        assertNotNull(tempResponsableGuardado, "No se pudo guardar y recuperar el responsable para la prueba de eliminación.");
        assertNotNull(tempResponsableGuardado.getId(), "El ID del responsable a eliminar no puede ser nulo.");

        boolean result = responsablesDAO.eliminarResponsable(tempResponsableGuardado.getId());
        assertTrue(result, "Debería eliminar el responsable correctamente.");

        ResponsableDTO verificado = responsablesDAO.buscarResponsablePorId(tempResponsableGuardado.getId());
        assertNull(verificado, "El responsable no debería encontrarse después de ser eliminado.");
        // No asignar a responsableGuardado ya que ya fue eliminado.
    }

    @Test
    public void testEliminarResponsableNoExistente() {
        System.out.println("eliminarResponsableNoExistente");
        boolean result = responsablesDAO.eliminarResponsable(-998); // Un ID que seguramente no existe
        assertFalse(result, "No debería poder eliminar un responsable con un ID inexistente.");
    }
}