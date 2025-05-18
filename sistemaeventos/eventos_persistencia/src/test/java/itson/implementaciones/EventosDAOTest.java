package itson.implementaciones;

import itson.conexion.ManejadorConexiones;
import itson.dtos.ActividadDTO;
import itson.dtos.BusquedaEventoDTO;
import itson.dtos.EventoDTO;
import itson.dtos.LugarDTO;
import itson.dtos.ResponsableDTO;
import itson.entidades.Evento;
import itson.entidades.Lugar;
import itson.entidades.Responsable;
import itson.enums.ModalidadEvento;
import itson.enums.TipoResponsable;
import itson.enums.EstadoEvento; // Asumiendo que existe este enum
import itson.interfaces.IEventosDAO;
import itson.interfaces.ILugaresDAO;
import itson.interfaces.IResponsablesDAO;

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
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class EventosDAOTest {

    private final IEventosDAO eventosDAO = new EventosDAO();
    private final ILugaresDAO lugaresDAO = new LugaresDAO(); // Para crear lugares de prueba
    private final IResponsablesDAO responsablesDAO = new ResponsablesDAO(); // Para crear responsables de prueba

    private Evento eventoGuardado;
    private List<Lugar> lugaresCreadosTest;
    private List<Responsable> responsablesCreadosTest;
    private List<Evento> eventosDePruebaCreados;

    public EventosDAOTest() {
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
        lugaresCreadosTest = new ArrayList<>();
        responsablesCreadosTest = new ArrayList<>();
        eventosDePruebaCreados = new ArrayList<>();
        eventoGuardado = null;
    }

    @AfterEach
    public void tearDown() {
        if (eventoGuardado != null && eventoGuardado.getId() != null) {
            try {
                eventosDAO.eliminarEvento(eventoGuardado.getId());
            } catch (Exception e) {
                System.err.println(
                        "Error al limpiar eventoGuardado con ID " + eventoGuardado.getId() + ": " + e.getMessage());
            }
        }

        for (Evento ev : eventosDePruebaCreados) {
            if (ev != null && ev.getId() != null) {
                // Evitar doble eliminación si alguno fue asignado a eventoGuardado
                if (eventoGuardado == null || !ev.getId().equals(eventoGuardado.getId())) {
                    try {
                        eventosDAO.eliminarEvento(ev.getId());
                    } catch (Exception e) {
                        System.err.println(
                                "Error al limpiar evento de prueba con ID " + ev.getId() + ": " + e.getMessage());
                    }
                }
            }
        }
        for (Lugar lugar : lugaresCreadosTest) {
            if (lugar != null && lugar.getId() != null) {
                try {
                    lugaresDAO.eliminarLugar(lugar.getId());
                } catch (Exception e) {
                    System.err.println("Error al limpiar lugar con ID " + lugar.getId() + ": " + e.getMessage());
                }
            }
        }
        for (Responsable resp : responsablesCreadosTest) {
            if (resp != null && resp.getId() != null) {
                try {
                    responsablesDAO.eliminarResponsable(resp.getId());
                } catch (Exception e) {
                    System.err.println("Error al limpiar responsable con ID " + resp.getId() + ": " + e.getMessage());
                }
            }
        }
        eventosDePruebaCreados.clear();
        lugaresCreadosTest.clear();
        responsablesCreadosTest.clear();
        eventoGuardado = null;
    }

    private void prepararEventosParaFiltro() {
        Responsable r1 = crearYGuardarResponsable("Resp Filtro 1", "999001", TipoResponsable.RESPONSABLE);
        Responsable r2 = crearYGuardarResponsable("Resp Filtro 2", "999002", TipoResponsable.RESPONSABLE);

        EventoDTO ev1DTO = new EventoDTO(null, "Conferencia Anual de Java", "Java a fondo",
                toCalendar(LocalDateTime.now().plusDays(5)), toCalendar(LocalDateTime.now().plusDays(6)),
                EstadoEvento.PLANEADO, ModalidadEvento.PRESENCIAL, null, r1.getId());
        eventosDePruebaCreados.add(eventosDAO.guardarEventoConActividades(ev1DTO, new ArrayList<>()));

        EventoDTO ev2DTO = new EventoDTO(null, "Taller de Python Avanzado", "Python para expertos",
                toCalendar(LocalDateTime.now().plusDays(10)), toCalendar(LocalDateTime.now().plusDays(11)),
                EstadoEvento.EN_CURSO, ModalidadEvento.EN_LINEA, null, r2.getId());
        eventosDePruebaCreados.add(eventosDAO.guardarEventoConActividades(ev2DTO, new ArrayList<>()));

        EventoDTO ev3DTO = new EventoDTO(null, "Seminario Web sobre IA", "IA en la industria",
                toCalendar(LocalDateTime.now().plusDays(15)), toCalendar(LocalDateTime.now().plusDays(15)),
                EstadoEvento.PLANEADO, ModalidadEvento.EN_LINEA, null, r1.getId());
        eventosDePruebaCreados.add(eventosDAO.guardarEventoConActividades(ev3DTO, new ArrayList<>()));

        EventoDTO ev4DTO = new EventoDTO(null, "Encuentro de Desarrolladores Java", "Networking Java",
                toCalendar(LocalDateTime.now().plusDays(20)), toCalendar(LocalDateTime.now().plusDays(21)),
                EstadoEvento.FINALIZADO, ModalidadEvento.PRESENCIAL, null, r2.getId());
        eventosDePruebaCreados.add(eventosDAO.guardarEventoConActividades(ev4DTO, new ArrayList<>()));
    }

    private Responsable crearYGuardarResponsable(String nombre, String tel, TipoResponsable tipo) {
        ResponsableDTO dto = new ResponsableDTO(null, nombre, "ApTest", "AmTest", tel, tipo);
        responsablesDAO.guardarResponsable(dto);
        EntityManager em = ManejadorConexiones.obtenerConexion();
        try {
            Responsable r = em
                    .createQuery("SELECT r FROM Responsable r WHERE r.telefono = :tel ORDER BY r.id DESC",
                            Responsable.class)
                    .setParameter("tel", tel).setMaxResults(1).getSingleResult();
            responsablesCreadosTest.add(r);
            return r;
        } catch (NoResultException e) {
            fail("No se pudo recuperar el responsable guardado con teléfono: " + tel);
            return null;
        } finally {
            em.close();
        }
    }

    private Lugar crearYGuardarLugar(String nombre, String edificio) {
        LugarDTO dto = new LugarDTO(null, nombre, edificio);
        lugaresDAO.guardarLugar(dto);
        EntityManager em = ManejadorConexiones.obtenerConexion();
        try {
            Lugar l = em.createQuery(
                    "SELECT l FROM Lugar l WHERE l.nombre = :nombre AND l.edificio = :edificio ORDER BY l.id DESC",
                    Lugar.class)
                    .setParameter("nombre", nombre).setParameter("edificio", edificio).setMaxResults(1)
                    .getSingleResult();
            lugaresCreadosTest.add(l);
            return l;
        } catch (NoResultException e) {
            fail("No se pudo recuperar el lugar guardado: " + nombre);
            return null;
        } finally {
            em.close();
        }
    }

    // --- Tests ---
    @Test
    public void testGuardarEventoConActividades_Exitoso() {
        System.out.println("guardarEventoConActividades_Exitoso");

        Responsable orgEvento = crearYGuardarResponsable("Org Evento", "111111", TipoResponsable.RESPONSABLE);
        Responsable respActividad = crearYGuardarResponsable("Resp Actividad", "222222", TipoResponsable.PONENTE);
        Lugar lugarActividad = crearYGuardarLugar("Sala Magna", "Edificio Central");

        assertNotNull(orgEvento, "Organizador del evento no debería ser nulo.");
        assertNotNull(respActividad, "Responsable de actividad no debería ser nulo.");
        assertNotNull(lugarActividad, "Lugar de actividad no debería ser nulo.");

        EventoDTO eventoDTO = new EventoDTO(null, "Congreso Anual de IA", "Discusión sobre avances en IA",
                toCalendar(LocalDateTime.now().plusDays(10)), toCalendar(LocalDateTime.now().plusDays(12)),
                EstadoEvento.PLANEADO, ModalidadEvento.PRESENCIAL, null, orgEvento.getId());

        List<ActividadDTO> actividades = new ArrayList<>();
        actividades.add(new ActividadDTO("Charla Inaugural", "Conferencia",
                toCalendar(LocalDateTime.now().plusDays(10).plusHours(2)), 60, 30, lugarActividad.getId(),
                respActividad.getId()));
        actividades.add(new ActividadDTO("Taller de Machine Learning", "Taller",
                toCalendar(LocalDateTime.now().plusDays(11).plusHours(4)), 120, 30, lugarActividad.getId(),
                respActividad.getId()));

        eventoGuardado = eventosDAO.guardarEventoConActividades(eventoDTO, actividades);

        assertNotNull(eventoGuardado, "El evento guardado no debería ser nulo.");
        assertNotNull(eventoGuardado.getId(), "El ID del evento guardado no debería ser nulo.");
        assertEquals("Congreso Anual de IA", eventoGuardado.getTitulo());
        assertEquals(orgEvento.getId(), eventoGuardado.getResponsable().getId());
        assertNotNull(eventoGuardado.getActividades(), "La lista de actividades no debería ser nula.");
        assertEquals(2, eventoGuardado.getActividades().size(), "Debería haber 2 actividades guardadas.");
        assertEquals("Charla Inaugural", eventoGuardado.getActividades().get(0).getNombre());
    }

    @Test
    public void testGuardarEvento_ResponsableEventoNoEncontrado() {
        System.out.println("guardarEvento_ResponsableEventoNoEncontrado");
        EventoDTO eventoDTO = new EventoDTO(null, "Evento Fallido", "Desc", toCalendar(LocalDateTime.now()),
                toCalendar(LocalDateTime.now().plusDays(1)),
                EstadoEvento.PLANEADO, ModalidadEvento.EN_LINEA, null, -999); // ID de responsable inválido
        List<ActividadDTO> actividades = new ArrayList<>(); // Puede estar vacía o no, el fallo es antes

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            eventosDAO.guardarEventoConActividades(eventoDTO, actividades);
        });
        assertEquals("Responsable no encontrado", exception.getMessage());
    }

    @Test
    public void testGuardarEvento_LugarActividadNoEncontrado() {
        System.out.println("guardarEvento_LugarActividadNoEncontrado");
        Responsable orgEvento = crearYGuardarResponsable("Org Evento LugarFail", "333333", TipoResponsable.RESPONSABLE);
        Responsable respActividad = crearYGuardarResponsable("Resp Actividad LugarFail", "444444",
                TipoResponsable.PONENTE);

        EventoDTO eventoDTO = new EventoDTO(null, "Evento Lugar Fail", "Desc", toCalendar(LocalDateTime.now()),
                toCalendar(LocalDateTime.now().plusDays(1)),
                EstadoEvento.PLANEADO, ModalidadEvento.PRESENCIAL, null, orgEvento.getId());
        List<ActividadDTO> actividades = new ArrayList<>();
        actividades.add(new ActividadDTO("Actividad Lugar Fail", "Conferencia", toCalendar(LocalDateTime.now()), 60, 30,
                -998, respActividad.getId())); // ID de lugar inválido

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            eventosDAO.guardarEventoConActividades(eventoDTO, actividades);
        });
        assertEquals("Lugar no encontrado", exception.getMessage());
    }

    @Test
    public void testBuscarEventoPorId_Exitoso() {
        System.out.println("buscarEventoPorId_Exitoso");
        // Guardar un evento primero
        Responsable orgEvento = crearYGuardarResponsable("Org Buscar", "555555", TipoResponsable.RESPONSABLE);
        EventoDTO eventoParaGuardar = new EventoDTO(null, "Evento para Buscar", "Desc Buscar",
                toCalendar(LocalDateTime.now().plusDays(5)), toCalendar(LocalDateTime.now().plusDays(6)),
                EstadoEvento.PLANEADO, ModalidadEvento.HIBRIDO, null, orgEvento.getId());
        eventoGuardado = eventosDAO.guardarEventoConActividades(eventoParaGuardar, new ArrayList<>()); // Sin
                                                                                                       // actividades
                                                                                                       // para
                                                                                                       // simplificar
        assertNotNull(eventoGuardado, "El evento base para buscar no se pudo guardar.");
        assertNotNull(eventoGuardado.getId(), "El ID del evento guardado es nulo.");

        EventoDTO encontrado = eventosDAO.buscarEventoPorId(eventoGuardado.getId());
        assertNotNull(encontrado, "No se encontró el evento por ID.");
        assertEquals(eventoGuardado.getId(), encontrado.getId());
        assertEquals("Evento para Buscar", encontrado.getTitulo());
        assertEquals(orgEvento.getId(), encontrado.getResponsableId());
    }

    @Test
    public void testBuscarEventoPorId_NoEncontrado() {
        System.out.println("buscarEventoPorId_NoEncontrado");
        EventoDTO encontrado = eventosDAO.buscarEventoPorId(-997);
        assertNull(encontrado, "No debería encontrar un evento con ID inexistente.");
    }

    @Test
    public void testObtenerEventos() {
        System.out.println("obtenerEventos");
        // Guardar dos eventos
        Responsable org1 = crearYGuardarResponsable("Org Obtener1", "666666", TipoResponsable.RESPONSABLE);
        Responsable org2 = crearYGuardarResponsable("Org Obtener2", "777777", TipoResponsable.RESPONSABLE);

        EventoDTO ev1DTO = new EventoDTO(null, "Evento Uno Obtener", "D1", toCalendar(LocalDateTime.now().plusDays(1)),
                toCalendar(LocalDateTime.now().plusDays(2)), EstadoEvento.PLANEADO, ModalidadEvento.PRESENCIAL, null,
                org1.getId());
        EventoDTO ev2DTO = new EventoDTO(null, "Evento Dos Obtener", "D2", toCalendar(LocalDateTime.now().plusDays(3)),
                toCalendar(LocalDateTime.now().plusDays(4)), EstadoEvento.PLANEADO, ModalidadEvento.EN_LINEA, null,
                org2.getId());

        Evento ev1Guardado = eventosDAO.guardarEventoConActividades(ev1DTO, new ArrayList<>());
        Evento ev2Guardado = eventosDAO.guardarEventoConActividades(ev2DTO, new ArrayList<>());
        // Asignar uno a eventoGuardado para el teardown general, el otro se limpiará
        // manualmente o por el teardown de la lista.
        eventoGuardado = ev1Guardado;

        List<EventoDTO> eventos = eventosDAO.obtenerEventos();
        assertNotNull(eventos);
        assertTrue(eventos.size() >= 2, "Debería haber al menos 2 eventos."); // >= por si otros tests dejan datos

        boolean encontrado1 = eventos.stream()
                .anyMatch(e -> e.getTitulo().equals("Evento Uno Obtener") && e.getResponsableId().equals(org1.getId()));
        boolean encontrado2 = eventos.stream()
                .anyMatch(e -> e.getTitulo().equals("Evento Dos Obtener") && e.getResponsableId().equals(org2.getId()));
        assertTrue(encontrado1, "No se encontró Evento Uno Obtener en la lista.");
        assertTrue(encontrado2, "No se encontró Evento Dos Obtener en la lista.");

        // Limpieza explícita del segundo evento si no es el `eventoGuardado`
        if (ev2Guardado != null && (eventoGuardado == null || !ev2Guardado.getId().equals(eventoGuardado.getId()))) {
            eventosDAO.eliminarEvento(ev2Guardado.getId());
        }
    }

    @Test
    public void testObtenerEventos_Vacio() {
        System.out.println("obtenerEventos_Vacio");
        // Asegurar que la tabla esté vacía (el @BeforeEach y @AfterEach deberían
        // ayudar)
        // Limpiar explícitamente por si acaso
        List<EventoDTO> actuales = eventosDAO.obtenerEventos();
        for (EventoDTO evDTO : actuales) {
            eventosDAO.eliminarEvento(evDTO.getId());
        }

        List<EventoDTO> eventos = eventosDAO.obtenerEventos();
        assertNotNull(eventos);
        assertTrue(eventos.isEmpty(), "La lista de eventos debería estar vacía.");
    }

    @Test
    public void testEliminarEvento_Exitoso() {
        System.out.println("eliminarEvento_Exitoso");
        Responsable orgEliminar = crearYGuardarResponsable("Org Eliminar", "888888", TipoResponsable.RESPONSABLE);
        EventoDTO eventoParaEliminarDTO = new EventoDTO(null, "Evento Para Eliminar", "Desc Eliminar",
                toCalendar(LocalDateTime.now().plusDays(7)), toCalendar(LocalDateTime.now().plusDays(8)),
                EstadoEvento.PLANEADO, ModalidadEvento.PRESENCIAL, null, orgEliminar.getId());

        // No asignar a eventoGuardado global, ya que se eliminará aquí.
        Evento eventoAEliminar = eventosDAO.guardarEventoConActividades(eventoParaEliminarDTO, new ArrayList<>());
        assertNotNull(eventoAEliminar, "El evento a eliminar no se pudo guardar.");
        assertNotNull(eventoAEliminar.getId(), "El ID del evento a eliminar es nulo.");

        boolean resultado = eventosDAO.eliminarEvento(eventoAEliminar.getId());
        assertTrue(resultado, "La eliminación del evento debería ser exitosa.");

        EventoDTO verificado = eventosDAO.buscarEventoPorId(eventoAEliminar.getId());
        assertNull(verificado, "El evento no debería encontrarse después de ser eliminado.");
    }

    private Calendar toCalendar(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Test
    public void testEliminarEvento_NoEncontrado() {
        System.out.println("eliminarEvento_NoEncontrado");
        boolean resultado = eventosDAO.eliminarEvento(-996);
        assertFalse(resultado, "No debería poder eliminar un evento con ID inexistente.");
    }

    @Test
    public void testBuscarEventosPorFiltro_PorTitulo() {
        System.out.println("buscarEventosPorFiltro_PorTitulo");
        prepararEventosParaFiltro();
        BusquedaEventoDTO filtro = new BusquedaEventoDTO(
                "Java",
                null,
                null,
                null,
                null);

        List<EventoDTO> resultados = eventosDAO.buscarEventosPorFiltro(filtro);
        assertNotNull(resultados);
        assertEquals(2, resultados.size(), "Deberían encontrarse 2 eventos con 'Java' en el título.");
        assertTrue(resultados.stream().allMatch(e -> e.getTitulo().toLowerCase().contains("java")));
    }

    @Test
    public void testBuscarEventosPorFiltro_PorModalidad() {
        System.out.println("buscarEventosPorFiltro_PorModalidad");
        prepararEventosParaFiltro();
        BusquedaEventoDTO filtro = new BusquedaEventoDTO(
                null,
                null,
                null,
                ModalidadEvento.EN_LINEA,
                null);

        List<EventoDTO> resultados = eventosDAO.buscarEventosPorFiltro(filtro);
        assertNotNull(resultados);
        assertEquals(2, resultados.size(), "Deberían encontrarse 2 eventos EN_LINEA.");
        assertTrue(resultados.stream().allMatch(e -> e.getModalidad() == ModalidadEvento.EN_LINEA));
    }

    @Test
    public void testBuscarEventosPorFiltro_PorEstado() {
        System.out.println("buscarEventosPorFiltro_PorEstado");
        prepararEventosParaFiltro();
        BusquedaEventoDTO filtro = new BusquedaEventoDTO(
                null,
                null,
                null,
                null,
                EstadoEvento.PLANEADO);

        List<EventoDTO> resultados = eventosDAO.buscarEventosPorFiltro(filtro);
        assertNotNull(resultados);
        assertEquals(2, resultados.size(), "Deberían encontrarse 2 eventos PLANEADO.");
        assertTrue(resultados.stream().allMatch(e -> e.getEstado() == EstadoEvento.PLANEADO));
    }

    @Test
    public void testBuscarEventosPorFiltro_PorFechaInicio() {
        System.out.println("buscarEventosPorFiltro_PorFechaInicio");
        prepararEventosParaFiltro();
        BusquedaEventoDTO filtro = new BusquedaEventoDTO(
                null,
                toCalendar(LocalDateTime.now().plusDays(10)),
                null,
                null,
                null);

        List<EventoDTO> resultados = eventosDAO.buscarEventosPorFiltro(filtro);
        assertNotNull(resultados);
        assertEquals(3, resultados.size(), "Deberían encontrarse 3 eventos desde Hoy+10.");
        // Taller Python (Hoy+10), Seminario IA (Hoy+15), Encuentro Java (Hoy+20)
        assertTrue(resultados.stream().anyMatch(e -> e.getTitulo().contains("Python")));
        assertTrue(resultados.stream().anyMatch(e -> e.getTitulo().contains("Seminario Web sobre IA")));
        assertTrue(resultados.stream().anyMatch(e -> e.getTitulo().contains("Encuentro de Desarrolladores Java")));
    }

    @Test
    public void testBuscarEventosPorFiltro_PorFechaFin() {
        System.out.println("buscarEventosPorFiltro_PorFechaFin");
        prepararEventosParaFiltro();
        BusquedaEventoDTO filtro = new BusquedaEventoDTO(
                null,
                null,
                toCalendar(LocalDateTime.now().plusDays(11)),
                null,
                null);
        List<EventoDTO> resultados = eventosDAO.buscarEventosPorFiltro(filtro);
        assertNotNull(resultados);
        assertEquals(2, resultados.size(), "Deberían encontrarse 2 eventos que terminan hasta Hoy+11.");
        // Conferencia Java (Hoy+6), Taller Python (Hoy+11)
        assertTrue(resultados.stream().anyMatch(e -> e.getTitulo().contains("Conferencia Anual de Java")));
        assertTrue(resultados.stream().anyMatch(e -> e.getTitulo().contains("Taller de Python Avanzado")));
    }

    @Test
    public void testBuscarEventosPorFiltro_Combinado_TituloYModalidad() {
        System.out.println("buscarEventosPorFiltro_Combinado_TituloYModalidad");
        prepararEventosParaFiltro();
        BusquedaEventoDTO filtro = new BusquedaEventoDTO(
                "Java",
                null,
                null,
                ModalidadEvento.PRESENCIAL,
                null);

        List<EventoDTO> resultados = eventosDAO.buscarEventosPorFiltro(filtro);
        assertNotNull(resultados);
        assertEquals(2, resultados.size(), "Debería encontrarse 2 eventos 'Java' y PRESENCIAL.");
        assertTrue(resultados.stream().allMatch(
                e -> e.getTitulo().toLowerCase().contains("java") && e.getModalidad() == ModalidadEvento.PRESENCIAL));
    }

    @Test
    public void testBuscarEventosPorFiltro_Combinado_EstadoYFechaInicio() {
        System.out.println("buscarEventosPorFiltro_Combinado_EstadoYFechaInicio");
        prepararEventosParaFiltro();
        BusquedaEventoDTO filtro = new BusquedaEventoDTO(
                null,
                toCalendar(LocalDateTime.now().plusDays(14)),
                null,
                null,
                EstadoEvento.PLANEADO
        );
        List<EventoDTO> resultados = eventosDAO.buscarEventosPorFiltro(filtro);
        assertNotNull(resultados);
        assertEquals(1, resultados.size(), "Debería encontrarse 1 evento PLANEADO desde Hoy+14.");
        assertEquals("Seminario Web sobre IA", resultados.get(0).getTitulo());
    }

    @Test
    public void testBuscarEventosPorFiltro_SinResultados() {
        System.out.println("buscarEventosPorFiltro_SinResultados");
        prepararEventosParaFiltro();
        BusquedaEventoDTO filtro = new BusquedaEventoDTO(
                "Título Inexistente",
                null,
                null,
                null,
                null
                );

        List<EventoDTO> resultados = eventosDAO.buscarEventosPorFiltro(filtro);
        assertNotNull(resultados);
        assertTrue(resultados.isEmpty(), "No deberían encontrarse eventos con un título inexistente.");
    }

    @Test
    public void testBuscarEventosPorFiltro_FiltroDTOVacio() {
        System.out.println("buscarEventosPorFiltro_FiltroDTOVacio");
        prepararEventosParaFiltro(); // Debería devolver todos los eventos (4 en este caso)
        BusquedaEventoDTO filtro = new BusquedaEventoDTO(
            null,null,null,null,null
        ); // Sin setear ningún campo

        List<EventoDTO> resultados = eventosDAO.buscarEventosPorFiltro(filtro);
        assertNotNull(resultados);
        assertEquals(4, resultados.size(), "Deberían devolverse todos los eventos si el filtro está vacío.");
    }

        @Test
    public void testModificarEstadoEvento_Exitoso() {
        System.out.println("modificarEstadoEvento_Exitoso");
        // 1. Crear y guardar un evento de prueba
        Responsable orgEvento = crearYGuardarResponsable("Org ModificarEstado", "101010", TipoResponsable.RESPONSABLE);
        EventoDTO eventoParaGuardar = new EventoDTO(null, "Evento para Modificar Estado", "Desc Modificar",
                toCalendar(LocalDateTime.now().plusDays(1)), toCalendar(LocalDateTime.now().plusDays(2)),
                EstadoEvento.PLANEADO, ModalidadEvento.PRESENCIAL, null, orgEvento.getId());
        
        // Guardar el evento y asignarlo a eventoGuardado para limpieza automática en tearDown
        eventoGuardado = eventosDAO.guardarEventoConActividades(eventoParaGuardar, new ArrayList<>());
        assertNotNull(eventoGuardado, "El evento base para modificar estado no se pudo guardar.");
        assertNotNull(eventoGuardado.getId(), "El ID del evento guardado es nulo.");
        assertEquals(EstadoEvento.PLANEADO, eventoGuardado.getEstado(), "El estado inicial del evento no es el esperado.");

        // 2. Modificar el estado del evento
        EstadoEvento nuevoEstado = EstadoEvento.EN_CURSO;
        boolean resultadoModificacion = eventosDAO.modificarEstadoEvento(eventoGuardado.getId(), nuevoEstado);
        assertTrue(resultadoModificacion, "La modificación del estado del evento debería ser exitosa.");

        // 3. Verificar que el estado se haya actualizado en la base de datos
        // Es importante obtener una nueva instancia del EntityManager para asegurar que no estamos leyendo de la caché de primer nivel.
        EntityManager emVerificacion = ManejadorConexiones.obtenerConexion();
        Evento eventoVerificado = emVerificacion.find(Evento.class, eventoGuardado.getId());
        emVerificacion.close(); // Cerrar el EntityManager de verificación

        assertNotNull(eventoVerificado, "No se pudo encontrar el evento después de intentar modificar su estado.");
        assertEquals(nuevoEstado, eventoVerificado.getEstado(), "El estado del evento no se actualizó correctamente en la BD.");
    }
    
    @Test
    public void testModificarEvento_Exitoso_CambioSimple() {
        System.out.println("modificarEvento_Exitoso_CambioSimple");
        // 1. Crear y guardar un evento de prueba
        Responsable orgOriginal = crearYGuardarResponsable("Org Original Mod", "202020", TipoResponsable.RESPONSABLE);
        EventoDTO eventoOriginalDTO = new EventoDTO(null, "Evento Original", "Descripción Original",
                toCalendar(LocalDateTime.now().plusDays(10)), toCalendar(LocalDateTime.now().plusDays(11)),
                EstadoEvento.PLANEADO, ModalidadEvento.PRESENCIAL, null, orgOriginal.getId());
        
        eventoGuardado = eventosDAO.guardarEventoConActividades(eventoOriginalDTO, new ArrayList<>());
        assertNotNull(eventoGuardado, "El evento original no se pudo guardar.");
        assertNotNull(eventoGuardado.getId(), "El ID del evento original es nulo.");

        // 2. Preparar el DTO con las modificaciones
        EventoDTO eventoModificadoDTO = new EventoDTO(
                eventoGuardado.getId(), // ID del evento a modificar
                "Evento Modificado",    // Nuevo título
                "Descripción Modificada", // Nueva descripción
                toCalendar(LocalDateTime.now().plusDays(12)), // Nueva fecha de inicio
                toCalendar(LocalDateTime.now().plusDays(13)), // Nueva fecha de fin
                EstadoEvento.PLANEADO, // Nuevo estado
                ModalidadEvento.EN_LINEA, // Nueva modalidad
                null, // Lista de actividades (no se modifica en este método)
                orgOriginal.getId() // ID del responsable (no se modifica en este método directamente)
        );

        // 3. Ejecutar la modificación
        Evento eventoResultado = eventosDAO.modificarEvento(eventoModificadoDTO);
        assertNotNull(eventoResultado, "El método modificarEvento no debería devolver null en caso de éxito.");
        assertEquals(eventoGuardado.getId(), eventoResultado.getId(), "El ID del evento modificado no coincide.");

        // 4. Verificar los cambios en la base de datos
        EntityManager emVerificacion = ManejadorConexiones.obtenerConexion();
        Evento eventoVerificado = emVerificacion.find(Evento.class, eventoGuardado.getId());
        emVerificacion.close();

        assertNotNull(eventoVerificado, "No se pudo encontrar el evento después de la modificación.");
        assertEquals("Evento Modificado", eventoVerificado.getTitulo());
        assertEquals("Descripción Modificada", eventoVerificado.getDescripcion());
        // Para comparar Calendar, es mejor convertir a un formato comparable o comparar campos individuales
        assertEquals(eventoModificadoDTO.getFechaInicio().getTimeInMillis() / 1000, eventoVerificado.getFechaInicio().getTimeInMillis() / 1000);
        assertEquals(eventoModificadoDTO.getFechaFin().getTimeInMillis() / 1000, eventoVerificado.getFechaFin().getTimeInMillis() / 1000);
        assertEquals(EstadoEvento.PLANEADO, eventoVerificado.getEstado());
        assertEquals(ModalidadEvento.EN_LINEA, eventoVerificado.getModalidad());
        // Verificar que el responsable no cambió (ya que el método no lo modifica)
        assertEquals(orgOriginal.getId(), eventoVerificado.getResponsable().getId());
    }

    @Test
    public void testModificarEvento_EventoNoEncontrado() {
        System.out.println("modificarEvento_EventoNoEncontrado");
        EventoDTO dtoInexistente = new EventoDTO(
                -998, // ID Inexistente
                "Título Inexistente", "Desc Inexistente",
                toCalendar(LocalDateTime.now()), toCalendar(LocalDateTime.now().plusDays(1)),
                EstadoEvento.PLANEADO, ModalidadEvento.PRESENCIAL, null, 1
        );

        Evento eventoResultado = eventosDAO.modificarEvento(dtoInexistente);
        assertNull(eventoResultado, "Modificar un evento inexistente debería devolver null.");
    }

}