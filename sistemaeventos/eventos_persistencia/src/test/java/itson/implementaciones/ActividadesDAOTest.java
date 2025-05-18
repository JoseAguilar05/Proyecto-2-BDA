package itson.implementaciones;

import itson.conexion.ManejadorConexiones;
import itson.dtos.*;
import itson.entidades.*;
import itson.enums.*;
import itson.interfaces.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class ActividadesDAOTest {

    private final IActividadesDAO actividadesDAO = new ActividadesDAO();
    private final IEventosDAO eventosDAO = new EventosDAO(); // Para crear eventos padre
    private final ILugaresDAO lugaresDAO = new LugaresDAO(); // Para crear lugares padre
    private final IResponsablesDAO responsablesDAO = new ResponsablesDAO(); // Para crear responsables padre

    private Actividad actividadGuardada;
    private Evento eventoPadreGuardado;
    private Lugar lugarPadreGuardado;
    private Responsable responsablePadreGuardado;

    // Listas para limpieza general de entidades creadas en helpers
    private List<Lugar> lugaresCreadosTest;
    private List<Responsable> responsablesCreadosTest;
    private List<Evento> eventosCreadosTest;


    public ActividadesDAOTest() {
    }

    @BeforeAll
    public static void activarModoPruebas() {
        ManejadorConexiones.activateTestMode();
    }

    @AfterAll
    public static void desactivarModoPruebas() {
        ManejadorConexiones.deactivateTestMode();
    }

    @BeforeEach
    public void setUp() {
        actividadGuardada = null;
        eventoPadreGuardado = null;
        lugarPadreGuardado = null;
        responsablePadreGuardado = null;

        lugaresCreadosTest = new ArrayList<>();
        responsablesCreadosTest = new ArrayList<>();
        eventosCreadosTest = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        if (actividadGuardada != null && actividadGuardada.getId() != null) {
            try {
                // Verificar si aún existe antes de intentar eliminar
                EntityManager em = ManejadorConexiones.obtenerConexion();
                if (em.find(Actividad.class, actividadGuardada.getId()) != null) {
                    actividadesDAO.eliminarActividad(actividadGuardada.getId());
                }
                em.close();
            } catch (Exception e) {
                System.err.println("Error al limpiar actividadGuardada con ID " + actividadGuardada.getId() + ": " + e.getMessage());
            }
        }
        // Limpiar entidades padre si fueron creadas específicamente para un test y asignadas
        if (eventoPadreGuardado != null && eventoPadreGuardado.getId() != null) {
             try {
                EntityManager em = ManejadorConexiones.obtenerConexion();
                if (em.find(Evento.class, eventoPadreGuardado.getId()) != null) {
                    eventosDAO.eliminarEvento(eventoPadreGuardado.getId());
                }
                em.close();
            } catch (Exception e) {
                System.err.println("Error al limpiar eventoPadreGuardado: " + e.getMessage());
            }
        }
        if (lugarPadreGuardado != null && lugarPadreGuardado.getId() != null) {
            try {
                EntityManager em = ManejadorConexiones.obtenerConexion();
                if (em.find(Lugar.class, lugarPadreGuardado.getId()) != null) {
                     lugaresDAO.eliminarLugar(lugarPadreGuardado.getId());
                }
                em.close();
            } catch (Exception e) {
                 System.err.println("Error al limpiar lugarPadreGuardado: " + e.getMessage());
            }
        }
        if (responsablePadreGuardado != null && responsablePadreGuardado.getId() != null) {
            try {
                EntityManager em = ManejadorConexiones.obtenerConexion();
                if (em.find(Responsable.class, responsablePadreGuardado.getId()) != null) {
                    responsablesDAO.eliminarResponsable(responsablePadreGuardado.getId());
                }
                em.close();
            } catch (Exception e) {
                System.err.println("Error al limpiar responsablePadreGuardado: " + e.getMessage());
            }
        }

        // Limpieza de listas generales
        for (Evento ev : eventosCreadosTest) {
            if (ev != null && ev.getId() != null && (eventoPadreGuardado == null || !ev.getId().equals(eventoPadreGuardado.getId()))) {
                try { eventosDAO.eliminarEvento(ev.getId()); } catch (Exception e) { /* ignore */ }
            }
        }
        for (Lugar lug : lugaresCreadosTest) {
             if (lug != null && lug.getId() != null && (lugarPadreGuardado == null || !lug.getId().equals(lugarPadreGuardado.getId()))) {
                try { lugaresDAO.eliminarLugar(lug.getId()); } catch (Exception e) { /* ignore */ }
            }
        }
        for (Responsable resp : responsablesCreadosTest) {
            if (resp != null && resp.getId() != null && (responsablePadreGuardado == null || !resp.getId().equals(responsablePadreGuardado.getId()))) {
                try { responsablesDAO.eliminarResponsable(resp.getId()); } catch (Exception e) { /* ignore */ }
            }
        }
        eventosCreadosTest.clear();
        lugaresCreadosTest.clear();
        responsablesCreadosTest.clear();
    }

    // --- Helper Methods ---
    private Calendar toCalendar(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Responsable crearYGuardarResponsableHelper(String nombre, String tel, TipoResponsable tipo) {
        ResponsableDTO dto = new ResponsableDTO(null, nombre, "ApTestAct", "AmTestAct", tel, tipo);
        responsablesDAO.guardarResponsable(dto);
        EntityManager em = ManejadorConexiones.obtenerConexion();
        try {
            Responsable r = em.createQuery("SELECT r FROM Responsable r WHERE r.telefono = :tel ORDER BY r.id DESC", Responsable.class)
                .setParameter("tel", tel).setMaxResults(1).getSingleResult();
            responsablesCreadosTest.add(r);
            return r;
        } catch(NoResultException e){
            fail("No se pudo recuperar el responsable guardado con teléfono: " + tel); return null;
        } finally { em.close(); }
    }

    private Lugar crearYGuardarLugarHelper(String nombre, String edificio) {
        LugarDTO dto = new LugarDTO(null, nombre, edificio);
        lugaresDAO.guardarLugar(dto);
        EntityManager em = ManejadorConexiones.obtenerConexion();
        try {
            Lugar l = em.createQuery("SELECT l FROM Lugar l WHERE l.nombre = :nombre AND l.edificio = :edificio ORDER BY l.id DESC", Lugar.class)
                .setParameter("nombre", nombre).setParameter("edificio", edificio).setMaxResults(1).getSingleResult();
            lugaresCreadosTest.add(l);
            return l;
        } catch(NoResultException e){
            fail("No se pudo recuperar el lugar guardado: " + nombre); return null;
        } finally { em.close(); }
    }

    private Evento crearYGuardarEventoHelper(String titulo, Responsable responsable) {
        EventoDTO dto = new EventoDTO(null, titulo, "Desc Evento Act", toCalendar(LocalDateTime.now().plusDays(1)),
                                      toCalendar(LocalDateTime.now().plusDays(2)), EstadoEvento.PLANEADO,
                                      ModalidadEvento.PRESENCIAL, null, responsable.getId());
        Evento ev = eventosDAO.guardarEventoConActividades(dto, new ArrayList<>());
        eventosCreadosTest.add(ev);
        return ev;
    }

    // --- Tests ---

    @Test
    public void testGuardarActividad_Exitoso() {
        System.out.println("guardarActividad_Exitoso");
        responsablePadreGuardado = crearYGuardarResponsableHelper("Resp Actividad", "303030", TipoResponsable.PONENTE);
        lugarPadreGuardado = crearYGuardarLugarHelper("Aula Actividad", "Edif A");
        eventoPadreGuardado = crearYGuardarEventoHelper("Evento para Actividad", responsablePadreGuardado);

        ActividadDTO dto = new ActividadDTO(null, "Charla de Bienvenida", "CONFERENCIA",
                toCalendar(LocalDateTime.now().plusHours(1)), 60, 100, EstadoActividad.PLANEADA,
                lugarPadreGuardado.getId(), responsablePadreGuardado.getId(),
                Collections.emptyList(), eventoPadreGuardado.getId());

        actividadGuardada = actividadesDAO.guardarActividad(dto);

        assertNotNull(actividadGuardada);
        assertNotNull(actividadGuardada.getId());
        assertEquals("Charla de Bienvenida", actividadGuardada.getNombre());
        assertEquals(EstadoActividad.PLANEADA, actividadGuardada.getEstado());
        assertEquals(lugarPadreGuardado.getId(), actividadGuardada.getLugar().getId());
        assertEquals(responsablePadreGuardado.getId(), actividadGuardada.getResponsable().getId());
        assertEquals(eventoPadreGuardado.getId(), actividadGuardada.getEvento().getId());
    }

    @Test
    public void testGuardarActividad_ResponsableNoEncontrado() {
        System.out.println("guardarActividad_ResponsableNoEncontrado");
        lugarPadreGuardado = crearYGuardarLugarHelper("Aula RespFail", "Edif B");
        // Crear un responsable temporal solo para el evento, no el que fallará en la actividad
        Responsable respEvento = crearYGuardarResponsableHelper("Resp Evento Temp", "313131", TipoResponsable.RESPONSABLE);
        eventoPadreGuardado = crearYGuardarEventoHelper("Evento RespFail", respEvento);

        ActividadDTO dto = new ActividadDTO(null, "Actividad Resp Fail", "TALLER",
                toCalendar(LocalDateTime.now()), 90, 50, EstadoActividad.PLANEADA,
                lugarPadreGuardado.getId(), -999, 
                Collections.emptyList(), eventoPadreGuardado.getId());
        
        Actividad resultado = actividadesDAO.guardarActividad(dto);
        assertNull(resultado, "Guardar actividad con responsable inexistente debería fallar y devolver null.");
    }
    

    @Test
    public void testBuscarActividadPorId_Exitoso() {
        System.out.println("buscarActividadPorId_Exitoso");
        responsablePadreGuardado = crearYGuardarResponsableHelper("Resp BuscarAct", "323232", TipoResponsable.PONENTE);
        lugarPadreGuardado = crearYGuardarLugarHelper("Aula BuscarAct", "Edif C");
        eventoPadreGuardado = crearYGuardarEventoHelper("Evento BuscarAct", responsablePadreGuardado);
        ActividadDTO dtoGuardar = new ActividadDTO(null, "Actividad Encontrada", "CONFERENCIA",
                toCalendar(LocalDateTime.now()), 60, 30, EstadoActividad.PLANEADA,
                lugarPadreGuardado.getId(), responsablePadreGuardado.getId(),
                Collections.emptyList(), eventoPadreGuardado.getId());
        actividadGuardada = actividadesDAO.guardarActividad(dtoGuardar);
        assertNotNull(actividadGuardada, "No se pudo guardar la actividad para buscarla.");

        ActividadDTO encontrada = actividadesDAO.buscarActividadPorId(actividadGuardada.getId());

        assertNotNull(encontrada);
        assertEquals(actividadGuardada.getId(), encontrada.getId());
        assertEquals("Actividad Encontrada", encontrada.getNombre());
        assertEquals(lugarPadreGuardado.getId(), encontrada.getLugarId());
        assertEquals(responsablePadreGuardado.getId(), encontrada.getResponsableId());
        assertEquals(eventoPadreGuardado.getId(), encontrada.getIdEvento());
        assertTrue(encontrada.getParticipantesIds().isEmpty(), "La lista de participantes debería estar vacía para una nueva actividad.");
    }

    @Test
    public void testBuscarActividadPorId_NoEncontrada() {
        System.out.println("buscarActividadPorId_NoEncontrada");
        assertThrows(NullPointerException.class, () -> {
            actividadesDAO.buscarActividadPorId(-997);
        }, "Buscar una actividad inexistente debería lanzar NullPointerException debido al acceso a propiedades de un objeto null.");

    }

    @Test
    public void testEliminarActividad_Exitoso() {
        System.out.println("eliminarActividad_Exitoso");
        responsablePadreGuardado = crearYGuardarResponsableHelper("Resp ElimAct", "333333", TipoResponsable.PONENTE);
        lugarPadreGuardado = crearYGuardarLugarHelper("Aula ElimAct", "Edif D");
        eventoPadreGuardado = crearYGuardarEventoHelper("Evento ElimAct", responsablePadreGuardado);
        ActividadDTO dtoGuardar = new ActividadDTO(null, "Actividad a Eliminar", "TALLER",
                toCalendar(LocalDateTime.now()), 120, 20, EstadoActividad.PLANEADA,
                lugarPadreGuardado.getId(), responsablePadreGuardado.getId(),
                Collections.emptyList(), eventoPadreGuardado.getId());
        
        Actividad actividadAEliminar = actividadesDAO.guardarActividad(dtoGuardar);
        assertNotNull(actividadAEliminar, "No se pudo guardar la actividad para eliminarla.");
        assertNotNull(actividadAEliminar.getId(), "El ID de la actividad a eliminar es nulo.");

        boolean resultado = actividadesDAO.eliminarActividad(actividadAEliminar.getId());
        assertTrue(resultado, "La eliminación de la actividad debería ser exitosa.");

        EntityManager em = ManejadorConexiones.obtenerConexion();
        Actividad verificada = em.find(Actividad.class, actividadAEliminar.getId());
        em.close();
        assertNull(verificada, "La actividad no debería encontrarse después de ser eliminada.");
    }

    @Test
    public void testEliminarActividad_NoEncontrada() {
        System.out.println("eliminarActividad_NoEncontrada");
        boolean resultado = actividadesDAO.eliminarActividad(-996);
        assertFalse(resultado, "No debería poder eliminar una actividad con ID inexistente.");
    }

    @Test
    public void testModificarEstadoActividadDTO_Exitoso() {
        System.out.println("modificarEstadoActividadDTO_Exitoso");
        responsablePadreGuardado = crearYGuardarResponsableHelper("Resp ModEstAct", "343434", TipoResponsable.PONENTE);
        lugarPadreGuardado = crearYGuardarLugarHelper("Aula ModEstAct", "Edif E");
        eventoPadreGuardado = crearYGuardarEventoHelper("Evento ModEstAct", responsablePadreGuardado);
        ActividadDTO dtoGuardar = new ActividadDTO(null, "Actividad Modificar Estado", "CONFERENCIA",
                toCalendar(LocalDateTime.now()), 45, 70, EstadoActividad.PLANEADA,
                lugarPadreGuardado.getId(), responsablePadreGuardado.getId(),
                Collections.emptyList(), eventoPadreGuardado.getId());
        actividadGuardada = actividadesDAO.guardarActividad(dtoGuardar);
        assertNotNull(actividadGuardada, "No se pudo guardar la actividad para modificar estado.");
        assertEquals(EstadoActividad.PLANEADA, actividadGuardada.getEstado());

        boolean resultado = actividadesDAO.modificarEstadoActividadDTO(actividadGuardada.getId(), EstadoActividad.FINALIZADA);
        assertTrue(resultado, "La modificación del estado debería ser exitosa.");

        EntityManager em = ManejadorConexiones.obtenerConexion();
        Actividad verificada = em.find(Actividad.class, actividadGuardada.getId());
        em.close();
        assertNotNull(verificada);
        assertEquals(EstadoActividad.FINALIZADA, verificada.getEstado(), "El estado no se actualizó correctamente.");
    }

    @Test
    public void testModificarEstadoActividadDTO_NoEncontrada() {
        System.out.println("modificarEstadoActividadDTO_NoEncontrada");
        boolean resultado = actividadesDAO.modificarEstadoActividadDTO(-995, EstadoActividad.FINALIZADA);
        assertFalse(resultado, "No debería poder modificar estado de actividad inexistente.");
    }
    
    @Test
    public void testObtenerParticipantesPorActividad_SinParticipantes() {
        System.out.println("obtenerParticipantesPorActividad_SinParticipantes");
        responsablePadreGuardado = crearYGuardarResponsableHelper("Resp ObtPart", "363636", TipoResponsable.PONENTE);
        lugarPadreGuardado = crearYGuardarLugarHelper("Aula ObtPart", "Edif G");
        eventoPadreGuardado = crearYGuardarEventoHelper("Evento ObtPart", responsablePadreGuardado);
        ActividadDTO dtoGuardar = new ActividadDTO(null, "Actividad Sin Participantes", "CONFERENCIA",
                toCalendar(LocalDateTime.now()), 60, 50, EstadoActividad.PLANEADA,
                lugarPadreGuardado.getId(), responsablePadreGuardado.getId(),
                Collections.emptyList(), // Sin participantes
                eventoPadreGuardado.getId());
        actividadGuardada = actividadesDAO.guardarActividad(dtoGuardar);
        assertNotNull(actividadGuardada);

        List<ParticipanteDTO> participantes = actividadesDAO.obtenerParticipantesPorActividad(actividadGuardada.getId());
        assertNotNull(participantes, "La lista de participantes no debería ser nula.");
        assertTrue(participantes.isEmpty(), "La lista de participantes debería estar vacía.");
    }

    @Test
    public void testObtenerParticipantesPorActividad_ActividadNoEncontrada() {
        System.out.println("obtenerParticipantesPorActividad_ActividadNoEncontrada");

        assertThrows(NullPointerException.class, () -> {
            actividadesDAO.obtenerParticipantesPorActividad(-994);
        }, "Obtener participantes de actividad inexistente debería lanzar NullPointerException.");

    }
}