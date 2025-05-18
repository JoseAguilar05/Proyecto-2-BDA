package itson.implementaciones;

import itson.conexion.ManejadorConexiones;
import itson.dtos.ActividadDTO;
import itson.dtos.EventoDTO;
import itson.dtos.LugarDTO;
import itson.dtos.ParticipanteDTO;
import itson.dtos.ResponsableDTO;
import itson.entidades.Actividad;
import itson.entidades.Evento;
import itson.entidades.Lugar;
import itson.entidades.Participante;
import itson.entidades.Responsable;
import itson.enums.EstadoActividad;
import itson.enums.EstadoEvento;
import itson.enums.ModalidadEvento;
import itson.enums.TipoParticipante;
import itson.enums.TipoResponsable;
import itson.excepciones.SeguridadException;
import itson.interfaces.IActividadesDAO;
import itson.interfaces.IEventosDAO;
import itson.interfaces.ILugaresDAO;
import itson.interfaces.IParticipantesDAO;
import itson.interfaces.IResponsablesDAO;
import itson.seguridad.Seguridad;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

/**
 *
 * @author pc
 */
public class ParticipantesDAOTest {

    private final IParticipantesDAO participantesDAO = new ParticipantesDAO();
    private Participante participanteGuardado;

    // DAOs y entidades para pruebas de asociación de actividad
    private final IActividadesDAO actividadesDAO = new ActividadesDAO();
    private final IEventosDAO eventosDAO = new EventosDAO();
    private final ILugaresDAO lugaresDAO = new LugaresDAO();
    private final IResponsablesDAO responsablesDAO = new ResponsablesDAO();

    private Actividad actividadGuardadaParaTest;
    private Evento eventoPadreActividad;
    private Lugar lugarActividad;
    private Responsable responsableActividad;
    private Responsable responsableEvento; // Responsable para el evento padre

    // Listas para limpieza general de entidades creadas en helpers de actividad
    private List<Lugar> lugaresCreadosTest;
    private List<Responsable> responsablesCreadosTest;
    private List<Evento> eventosCreadosTest;


    public ParticipantesDAOTest() {
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
    public void setUp() { // Añadir inicialización de nuevas variables
        participanteGuardado = null;
        actividadGuardadaParaTest = null;
        eventoPadreActividad = null;
        lugarActividad = null;
        responsableActividad = null;
        responsableEvento = null;

        lugaresCreadosTest = new ArrayList<>();
        responsablesCreadosTest = new ArrayList<>();
        eventosCreadosTest = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        if (participanteGuardado != null && participanteGuardado.getId() != null) {
            // Desasociar actividades primero si es necesario para evitar constraint violations
            // Esto depende de cómo esté configurado el borrado en cascada.
            // Por seguridad, podríamos recargar el participante y limpiar sus actividades.
            try {
                EntityManager em = ManejadorConexiones.obtenerConexion();
                Participante p = em.find(Participante.class, participanteGuardado.getId());
                if (p != null && p.getActividades() != null && !p.getActividades().isEmpty()) {
                    em.getTransaction().begin();
                    // Crear una copia de la lista para evitar ConcurrentModificationException
                    List<Actividad> actividadesAsociadas = new ArrayList<>(p.getActividades());
                    for (Actividad act : actividadesAsociadas) {
                        act.getParticipantes().remove(p); // Quitar de la actividad
                        p.getActividades().remove(act);   // Quitar del participante
                        em.merge(act);
                    }
                    em.merge(p);
                    em.getTransaction().commit();
                }
                em.close();
            } catch (Exception e) {
                System.err.println("Error desasociando actividades del participante " + participanteGuardado.getId() + ": " + e.getMessage());
            }
            // Ahora eliminar el participante
            participantesDAO.eliminarParticipante(participanteGuardado.getId());
            participanteGuardado = null;
        }

        // Limpieza de la actividad y sus entidades padre
        if (actividadGuardadaParaTest != null && actividadGuardadaParaTest.getId() != null) {
            try {
                EntityManager em = ManejadorConexiones.obtenerConexion();
                if (em.find(Actividad.class, actividadGuardadaParaTest.getId()) != null) {
                    actividadesDAO.eliminarActividad(actividadGuardadaParaTest.getId());
                }
                em.close();
            } catch (Exception e) {
                System.err.println("Error al limpiar actividadGuardadaParaTest con ID " + actividadGuardadaParaTest.getId() + ": " + e.getMessage());
            }
            actividadGuardadaParaTest = null;
        }
        if (eventoPadreActividad != null && eventoPadreActividad.getId() != null) {
             try {
                EntityManager em = ManejadorConexiones.obtenerConexion();
                if (em.find(Evento.class, eventoPadreActividad.getId()) != null) {
                    eventosDAO.eliminarEvento(eventoPadreActividad.getId());
                }
                em.close();
            } catch (Exception e) { System.err.println("Error al limpiar eventoPadreActividad: " + e.getMessage()); }
            eventoPadreActividad = null;
        }
        // Los lugares y responsables se limpian a través de las listas generales si fueron creados por helpers
        // o individualmente si fueron asignados a lugarActividad/responsableActividad/responsableEvento

        if (lugarActividad != null && lugarActividad.getId() != null) {
            try {
                EntityManager em = ManejadorConexiones.obtenerConexion();
                if (em.find(Lugar.class, lugarActividad.getId()) != null) {
                     lugaresDAO.eliminarLugar(lugarActividad.getId());
                }
                em.close();
            } catch (Exception e) { System.err.println("Error al limpiar lugarActividad: " + e.getMessage()); }
            lugarActividad = null;
        }
        if (responsableActividad != null && responsableActividad.getId() != null) {
            try {
                EntityManager em = ManejadorConexiones.obtenerConexion();
                if (em.find(Responsable.class, responsableActividad.getId()) != null) {
                    responsablesDAO.eliminarResponsable(responsableActividad.getId());
                }
                em.close();
            } catch (Exception e) { System.err.println("Error al limpiar responsableActividad: " + e.getMessage()); }
            responsableActividad = null;
        }
         if (responsableEvento != null && responsableEvento.getId() != null && (responsableActividad == null || !responsableEvento.getId().equals(responsableActividad.getId()))) {
            try {
                EntityManager em = ManejadorConexiones.obtenerConexion();
                if (em.find(Responsable.class, responsableEvento.getId()) != null) {
                    responsablesDAO.eliminarResponsable(responsableEvento.getId());
                }
                em.close();
            } catch (Exception e) { System.err.println("Error al limpiar responsableEvento: " + e.getMessage()); }
            responsableEvento = null;
        }


        // Limpieza de listas generales
        for (Evento ev : eventosCreadosTest) {
            if (ev != null && ev.getId() != null && (eventoPadreActividad == null || !ev.getId().equals(eventoPadreActividad.getId()))) {
                try { eventosDAO.eliminarEvento(ev.getId()); } catch (Exception e) { /* ignore */ }
            }
        }
        for (Lugar lug : lugaresCreadosTest) {
             if (lug != null && lug.getId() != null && (lugarActividad == null || !lug.getId().equals(lugarActividad.getId()))) {
                try { lugaresDAO.eliminarLugar(lug.getId()); } catch (Exception e) { /* ignore */ }
            }
        }
        for (Responsable resp : responsablesCreadosTest) {
            if (resp != null && resp.getId() != null && 
                (responsableActividad == null || !resp.getId().equals(responsableActividad.getId())) &&
                (responsableEvento == null || !resp.getId().equals(responsableEvento.getId())) ) {
                try { responsablesDAO.eliminarResponsable(resp.getId()); } catch (Exception e) { /* ignore */ }
            }
        }
        eventosCreadosTest.clear();
        lugaresCreadosTest.clear();
        responsablesCreadosTest.clear();
    }

    // --- Helper Methods para Actividad y sus padres (adaptados de ActividadesDAOTest) ---
    private Calendar toCalendar(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Responsable crearYGuardarResponsableHelper(String nombre, String tel, TipoResponsable tipo) {
        ResponsableDTO dto = new ResponsableDTO(null, nombre, "ApTestAsoc", "AmTestAsoc", tel, tipo);
        responsablesDAO.guardarResponsable(dto); // Asumiendo que guardarResponsable devuelve void o el DTO
        EntityManager em = ManejadorConexiones.obtenerConexion();
        try { // Recuperar por un campo único para obtener el ID
            Responsable r = em.createQuery("SELECT r FROM Responsable r WHERE r.telefono = :tel ORDER BY r.id DESC", Responsable.class)
                .setParameter("tel", tel).setMaxResults(1).getSingleResult();
            responsablesCreadosTest.add(r); // Añadir a la lista general para limpieza
            return r;
        } catch(NoResultException e){
            fail("No se pudo recuperar el responsable guardado con teléfono: " + tel); return null;
        } finally { if(em.isOpen()) em.close(); }
    }

    private Lugar crearYGuardarLugarHelper(String nombre, String edificio) {
        LugarDTO dto = new LugarDTO(null, nombre, edificio);
        lugaresDAO.guardarLugar(dto); // Asumiendo que guardarLugar devuelve void o el DTO
        EntityManager em = ManejadorConexiones.obtenerConexion();
        try { // Recuperar por un campo único para obtener el ID
            Lugar l = em.createQuery("SELECT l FROM Lugar l WHERE l.nombre = :nombre AND l.edificio = :edificio ORDER BY l.id DESC", Lugar.class)
                .setParameter("nombre", nombre).setParameter("edificio", edificio).setMaxResults(1).getSingleResult();
            lugaresCreadosTest.add(l); // Añadir a la lista general para limpieza
            return l;
        } catch(NoResultException e){
            fail("No se pudo recuperar el lugar guardado: " + nombre); return null;
        } finally { if(em.isOpen()) em.close(); }
    }

    private Evento crearYGuardarEventoHelper(String titulo, Responsable respEvento) {
        EventoDTO dto = new EventoDTO(null, titulo, "Desc Evento para Asociar Act", toCalendar(LocalDateTime.now().plusDays(5)),
                                      toCalendar(LocalDateTime.now().plusDays(6)), EstadoEvento.PLANEADO,
                                      ModalidadEvento.PRESENCIAL, null, respEvento.getId());
        Evento ev = eventosDAO.guardarEventoConActividades(dto, new ArrayList<>()); // guardarEventoConActividades devuelve Evento
        eventosCreadosTest.add(ev); // Añadir a la lista general para limpieza
        return ev;
    }
    
    private Actividad crearYGuardarActividadHelper(String nombreAct, Evento eventoPadre, Lugar lugar, Responsable respActividad) {
        ActividadDTO dto = new ActividadDTO(null, nombreAct, "CONFERENCIA_ASOC",
                toCalendar(LocalDateTime.now().plusDays(5).plusHours(1)), 60, 50, EstadoActividad.PLANEADA,
                lugar.getId(), respActividad.getId(),
                Collections.emptyList(), eventoPadre.getId());
        return actividadesDAO.guardarActividad(dto); // guardarActividad devuelve Actividad
    }

    // --- Tests para asociarActividad ---

    @Test
    public void testAsociarActividad_Exitoso() throws SeguridadException {
        System.out.println("asociarActividad_Exitoso");

        // 1. Crear Participante
        ParticipanteDTO pDto = new ParticipanteDTO("Carlos", "Soto", "Vega", "csoto@test.com", TipoParticipante.ESTUDIANTE, "ISC", "007");
        participanteGuardado = participantesDAO.registrarParticipante(pDto);
        assertNotNull(participanteGuardado, "El participante no se pudo guardar.");

        // 2. Crear Actividad (con su Evento, Lugar, Responsable)
        responsableEvento = crearYGuardarResponsableHelper("Resp EventoAsoc", "707070", TipoResponsable.RESPONSABLE);
        eventoPadreActividad = crearYGuardarEventoHelper("Evento para Asociar", responsableEvento);
        lugarActividad = crearYGuardarLugarHelper("Aula Magna Asoc", "Edif Z");
        responsableActividad = crearYGuardarResponsableHelper("Ponente ActAsoc", "808080", TipoResponsable.PONENTE);
        actividadGuardadaParaTest = crearYGuardarActividadHelper("Charla de IA", eventoPadreActividad, lugarActividad, responsableActividad);
        assertNotNull(actividadGuardadaParaTest, "La actividad no se pudo guardar.");

        // 3. Asociar
        boolean resultado = participantesDAO.asociarActividad(participanteGuardado.getId(), actividadGuardadaParaTest.getId());
        assertTrue(resultado, "La asociación debería ser exitosa.");

        // 4. Verificar asociación en BD
        EntityManager em = ManejadorConexiones.obtenerConexion();
        Participante pVerificado = em.find(Participante.class, participanteGuardado.getId());
        Actividad aVerificada = em.find(Actividad.class, actividadGuardadaParaTest.getId());
        em.close();

        assertNotNull(pVerificado, "El participante no se encontró después de la asociación.");
        assertNotNull(aVerificada, "La actividad no se encontró después de la asociación.");

        assertTrue(pVerificado.getActividades().stream().anyMatch(a -> a.getId().equals(actividadGuardadaParaTest.getId())),
                "La actividad no está en la lista del participante.");
        assertTrue(aVerificada.getParticipantes().stream().anyMatch(p -> p.getId().equals(participanteGuardado.getId())),
                "El participante no está en la lista de la actividad.");
    }

    @Test
    public void testAsociarActividad_YaAsociada() throws SeguridadException {
        System.out.println("asociarActividad_YaAsociada");
        // 1. Crear y asociar una vez
        ParticipanteDTO pDto = new ParticipanteDTO("Laura", "Kent", "Lane", "lkent@test.com", TipoParticipante.EXTERNO, null, null);
        participanteGuardado = participantesDAO.registrarParticipante(pDto);
        responsableEvento = crearYGuardarResponsableHelper("Resp EventoYaAsoc", "717171", TipoResponsable.RESPONSABLE);
        eventoPadreActividad = crearYGuardarEventoHelper("Evento Ya Asociado", responsableEvento);
        lugarActividad = crearYGuardarLugarHelper("Sala Ya Asoc", "Edif Y");
        responsableActividad = crearYGuardarResponsableHelper("Ponente Ya Asoc", "818181", TipoResponsable.PONENTE);
        actividadGuardadaParaTest = crearYGuardarActividadHelper("Taller Avanzado", eventoPadreActividad, lugarActividad, responsableActividad);
        
        participantesDAO.asociarActividad(participanteGuardado.getId(), actividadGuardadaParaTest.getId()); // Primera asociación

        // 2. Intentar asociar de nuevo y esperar SeguridadException
        SeguridadException exception = assertThrows(SeguridadException.class, () -> {
            participantesDAO.asociarActividad(participanteGuardado.getId(), actividadGuardadaParaTest.getId());
        });
        assertEquals("La actividad ya está asociada al participante", exception.getMessage());
    }

    @Test
    public void testAsociarActividad_ParticipanteNoEncontrado() throws SeguridadException {
        System.out.println("asociarActividad_ParticipanteNoEncontrado");
        responsableEvento = crearYGuardarResponsableHelper("Resp EventoPNE", "727272", TipoResponsable.RESPONSABLE);
        eventoPadreActividad = crearYGuardarEventoHelper("Evento Part No Enc", responsableEvento);
        lugarActividad = crearYGuardarLugarHelper("Aula PNE", "Edif X");
        responsableActividad = crearYGuardarResponsableHelper("Ponente PNE", "828282", TipoResponsable.PONENTE);
        actividadGuardadaParaTest = crearYGuardarActividadHelper("Curso Básico", eventoPadreActividad, lugarActividad, responsableActividad);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            participantesDAO.asociarActividad(-999, actividadGuardadaParaTest.getId()); // ID Participante inexistente
        });
        assertEquals("Participante o actividad no encontrados", exception.getMessage());
    }

    @Test
    public void testAsociarActividad_ActividadNoEncontrada() throws SeguridadException {
        System.out.println("asociarActividad_ActividadNoEncontrada");
        ParticipanteDTO pDto = new ParticipanteDTO("Pedro", "Ramirez", "Solis", "pramirez@test.com", TipoParticipante.DOCENTE, "Sociales", null);
        participanteGuardado = participantesDAO.registrarParticipante(pDto);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            participantesDAO.asociarActividad(participanteGuardado.getId(), -998); // ID Actividad inexistente
        });
        assertEquals("Participante o actividad no encontrados", exception.getMessage());
    }
        /**
         * Test of registrarParticipante method, of class ParticipantesDAO.
         */
        @Test
        public void testRegistrarParticipanteEstudiante() throws Exception {
                System.out.println("registrarParticipante");
                ParticipanteDTO participanteDTO = new ParticipanteDTO(
                                "Juan",
                                "Pérez",
                                "Gómez",
                                "juanperez@example.com",
                                TipoParticipante.ESTUDIANTE,
                                "Ingeniería",
                                "00000111111");
                Participante result = participantesDAO.registrarParticipante(participanteDTO);
                participanteGuardado = result; // Guardar el participante para eliminarlo después
                assertNotNull(result);
                assertEquals(participanteDTO.getNombre(), result.getNombre());
                assertEquals(participanteDTO.getApellidoPaterno(), result.getApellidoPaterno());
                assertEquals(participanteDTO.getApellidoMaterno(), result.getApellidoMaterno());
                assertEquals(participanteDTO.getCorreo(), Seguridad.desencriptar(result.getCorreo()));
                assertEquals(participanteDTO.getTipoParticipante(), result.getTipoParticipante());
                assertEquals(participanteDTO.getDependencia(), result.getDependencia());
                assertEquals(participanteDTO.getNumeroControl(), Seguridad.desencriptar(result.getNumeroControl()));
        }

        /**
         * Test of registrarParticipante method, of class ParticipantesDAO.
         */
        @Test
        public void testRegistrarParticipanteExterno() throws Exception {
                System.out.println("registrarParticipante");
                ParticipanteDTO participanteDTO = new ParticipanteDTO(
                                "Juan",
                                "Pérez",
                                "Gómez",
                                "juanperez@example.com",
                                TipoParticipante.EXTERNO,
                                null,
                                null);
                Participante result = participantesDAO.registrarParticipante(participanteDTO);
                participanteGuardado = result; // Guardar el participante para eliminarlo después
                assertNotNull(result);
                assertEquals(participanteDTO.getNombre(), result.getNombre());
                assertEquals(participanteDTO.getApellidoPaterno(), result.getApellidoPaterno());
                assertEquals(participanteDTO.getApellidoMaterno(), result.getApellidoMaterno());
                assertEquals(participanteDTO.getCorreo(), Seguridad.desencriptar(result.getCorreo()));
                assertEquals(participanteDTO.getTipoParticipante(), result.getTipoParticipante());
                assertEquals(participanteDTO.getDependencia(), result.getDependencia());
        }

        /**
         * Test of registrarParticipante method, of class ParticipantesDAO.
         */
        @Test
        public void testRegistrarParticipanteProfesor() throws Exception {
                System.out.println("registrarParticipante");
                ParticipanteDTO participanteDTO = new ParticipanteDTO(
                                "Juan",
                                "Pérez",
                                "Gómez",
                                "juanperez@example.com",
                                TipoParticipante.DOCENTE,
                                "Ingeniería",
                                null);
                Participante result = participantesDAO.registrarParticipante(participanteDTO);
                participanteGuardado = result; // Guardar el participante para eliminarlo después
                assertNotNull(result);
                assertEquals(participanteDTO.getNombre(), result.getNombre());
                assertEquals(participanteDTO.getApellidoPaterno(), result.getApellidoPaterno());
                assertEquals(participanteDTO.getApellidoMaterno(), result.getApellidoMaterno());
                assertEquals(participanteDTO.getCorreo(), Seguridad.desencriptar(result.getCorreo()));
                assertEquals(participanteDTO.getTipoParticipante(), result.getTipoParticipante());
                assertEquals(participanteDTO.getDependencia(), result.getDependencia());
                assertNull(result.getNumeroControl());
        }

        /**
         * Test of obtenerParticipantePorId method, of class ParticipantesDAO.
         */
        @Test
        public void testObtenerParticipantePorId() throws SeguridadException {
                System.out.println("obtenerParticipantePorId");
                registrarParticipante();
                Integer id = participanteGuardado.getId();
                Participante expResult = participanteGuardado;

                ParticipanteDTO result = participantesDAO.obtenerParticipantePorId(id);
                assertNotNull(result);
                assertEquals(expResult.getId(), result.getId());
                assertEquals(expResult.getNombre(), result.getNombre());
                assertEquals(expResult.getApellidoPaterno(), result.getApellidoPaterno());
                assertEquals(expResult.getApellidoMaterno(), result.getApellidoMaterno());
                assertEquals(Seguridad.desencriptar(expResult.getCorreo()), result.getCorreo());
                assertEquals(expResult.getTipoParticipante(), result.getTipoParticipante());
                assertEquals(expResult.getDependencia(), result.getDependencia());

        }

        /**
         * Test of obtenerParticipantePorId method, of class ParticipantesDAO.
         */
        @Test
        public void testObtenerParticipantePorIdNoExistente() throws SeguridadException {
                System.out.println("obtenerParticipantePorId");
                registrarParticipante();
                Integer id = 4;
                ParticipanteDTO result = participantesDAO.obtenerParticipantePorId(id);
                assertNull(result);

        }

        @Test
        public void testObtenerParticipantesConFiltro() throws SeguridadException {
                System.out.println("obtenerParticipantesConFiltro");

                List<Participante> participantesCreadosEnEsteTest = new ArrayList<>();

                // Registrar varios participantes para la prueba
                ParticipanteDTO participante1 = new ParticipanteDTO(
                                "Juan Carlos",
                                "Pérez",
                                "Gómez",
                                "juanperez@example.com",
                                TipoParticipante.ESTUDIANTE,
                                "Ingeniería",
                                "00000111111");

                ParticipanteDTO participante2 = new ParticipanteDTO(
                                "Ana",
                                "García",
                                "López",
                                "ana.garcia@example.com",
                                TipoParticipante.DOCENTE,
                                "Ingeniería",
                                null);

                ParticipanteDTO participante3 = new ParticipanteDTO(
                                "Pedro",
                                "Sánchez",
                                "Pérez",
                                "pedro@example.com",
                                TipoParticipante.EXTERNO,
                                null,
                                null);

                // Registrar los participantes
                registrarYRecordar(participante1, participantesCreadosEnEsteTest);
                registrarYRecordar(participante2, participantesCreadosEnEsteTest);
                registrarYRecordar(participante3, participantesCreadosEnEsteTest);

                // Probar diferentes filtros
                // 1. Filtrar por nombre
                List<ParticipanteDTO> resultadosNombre = participantesDAO.obtenerParticipantesConFiltro("Juan");
                assertFalse(resultadosNombre.isEmpty());
                assertTrue(resultadosNombre.stream()
                                .anyMatch(p -> p.getNombre().contains("Juan")));

                // 2. Filtrar por apellido
                List<ParticipanteDTO> resultadosApellido = participantesDAO.obtenerParticipantesConFiltro("Pérez");
                assertFalse(resultadosApellido.isEmpty());
                assertTrue(resultadosApellido.stream()
                                .anyMatch(p -> p.getApellidoPaterno().contains("Pérez") ||
                                                p.getApellidoMaterno().contains("Pérez")));

                // 3. Filtrar por correo
                List<ParticipanteDTO> resultadosCorreo = participantesDAO.obtenerParticipantesConFiltro("@example.com");
                assertFalse(resultadosCorreo.isEmpty());
                assertTrue(resultadosCorreo.stream()
                                .allMatch(p -> p.getCorreo().contains("@example.com")));

                // 4. Filtrar por número de control
                List<ParticipanteDTO> resultadosNumControl = participantesDAO
                                .obtenerParticipantesConFiltro("00000111111");
                assertFalse(resultadosNumControl.isEmpty());
                assertTrue(resultadosNumControl.stream()
                                .anyMatch(p -> p.getNumeroControl() != null &&
                                                p.getNumeroControl().equals("00000111111")));

                // 5. Filtrar con texto que no existe
                List<ParticipanteDTO> resultadosVacios = participantesDAO.obtenerParticipantesConFiltro("XYZ123");
                assertTrue(resultadosVacios.isEmpty());

                // Limpieza específica para este test
                for (Participante p : participantesCreadosEnEsteTest) {
                        if (p != null && p.getId() != null) {
                                try {
                                        participantesDAO.eliminarParticipante(p.getId());
                                } catch (Exception e) {
                                        System.err.println("Error al limpiar participante con ID " + p.getId() + ": "
                                                        + e.getMessage());
                                }
                        }
                }
        }

        private void registrarParticipante() throws SeguridadException {
                ParticipanteDTO participanteDTO = new ParticipanteDTO(
                                "Juan",
                                "Pérez",
                                "Gómez",
                                "juanperez@example.com",
                                TipoParticipante.DOCENTE,
                                "Ingeniería",
                                null);
                Participante result = participantesDAO.registrarParticipante(participanteDTO);
                participanteGuardado = result; // Guardar el participante para eliminarlo después
        }

        // ... (imports y código existente de la clase)

        @Test
        public void testObtenerParticipantesConFiltroYTipo() throws SeguridadException {
                System.out.println("obtenerParticipantesConFiltroYTipo");

                List<Participante> participantesCreadosEnEsteTest = new ArrayList<>();

                // Registrar varios participantes para la prueba
                ParticipanteDTO pEstudiante1DTO = new ParticipanteDTO(
                                "Juan Carlos", "Pérez", "Gómez", "juan.estudiante@example.com",
                                TipoParticipante.ESTUDIANTE, "Ingeniería de Software", "0000012345");
                ParticipanteDTO pEstudiante2DTO = new ParticipanteDTO(
                                "Ana", "López", "Ruiz", "ana.estudiante@example.com",
                                TipoParticipante.ESTUDIANTE, "Sistemas", "0000067890");
                ParticipanteDTO pDocente1DTO = new ParticipanteDTO(
                                "Carlos Alberto", "Pérez", "Díaz", "carlos.docente@example.com",
                                TipoParticipante.DOCENTE, "Departamento de Ciencias", null);
                ParticipanteDTO pDocente2DTO = new ParticipanteDTO(
                                "Laura", "García", "Martínez", "laura.docente@example.com",
                                TipoParticipante.DOCENTE, "Departamento de Artes", null);
                ParticipanteDTO pExterno1DTO = new ParticipanteDTO(
                                "Pedro", "Sánchez", "Pérez", "pedro.externo@example.com",
                                TipoParticipante.EXTERNO, null, null);

                registrarYRecordar(pEstudiante1DTO, participantesCreadosEnEsteTest);
                registrarYRecordar(pEstudiante2DTO, participantesCreadosEnEsteTest);
                registrarYRecordar(pDocente1DTO, participantesCreadosEnEsteTest);
                registrarYRecordar(pDocente2DTO, participantesCreadosEnEsteTest);
                registrarYRecordar(pExterno1DTO, participantesCreadosEnEsteTest);

                // Escenario 1: Filtrar solo por TipoParticipante (ESTUDIANTE), filtro de texto
                // vacío
                List<ParticipanteDTO> resTipoEstudiante = participantesDAO.obtenerParticipantesConFiltroYTipo("",
                                TipoParticipante.ESTUDIANTE);
                assertNotNull(resTipoEstudiante);
                assertEquals(2, resTipoEstudiante.size(), "Debería haber 2 estudiantes");
                assertTrue(resTipoEstudiante.stream()
                                .allMatch(p -> p.getTipoParticipante() == TipoParticipante.ESTUDIANTE));
                assertTrue(resTipoEstudiante.stream().anyMatch(p -> p.getNombre().equals("Juan Carlos")));
                assertTrue(resTipoEstudiante.stream().anyMatch(p -> p.getNombre().equals("Ana")));

                // Escenario 2: Filtrar solo por TipoParticipante (DOCENTE), filtro de texto
                // vacío
                List<ParticipanteDTO> resTipoDocente = participantesDAO.obtenerParticipantesConFiltroYTipo("",
                                TipoParticipante.DOCENTE);
                assertNotNull(resTipoDocente);
                assertEquals(2, resTipoDocente.size(), "Debería haber 2 docentes");
                assertTrue(resTipoDocente.stream().allMatch(p -> p.getTipoParticipante() == TipoParticipante.DOCENTE));

                // Escenario 3: Filtrar por TipoParticipante (ESTUDIANTE) Y filtro de texto
                // ("Juan")
                List<ParticipanteDTO> resTipoYTextoEst = participantesDAO.obtenerParticipantesConFiltroYTipo("Juan",
                                TipoParticipante.ESTUDIANTE);
                assertNotNull(resTipoYTextoEst);
                assertEquals(1, resTipoYTextoEst.size(), "Debería haber 1 estudiante llamado Juan");
                assertEquals("Juan Carlos", resTipoYTextoEst.get(0).getNombre());
                assertEquals(TipoParticipante.ESTUDIANTE, resTipoYTextoEst.get(0).getTipoParticipante());

                // Escenario 4: Filtrar por TipoParticipante (DOCENTE) Y filtro de texto
                // ("Pérez")
                List<ParticipanteDTO> resTipoYTextoDoc = participantesDAO.obtenerParticipantesConFiltroYTipo("Pérez",
                                TipoParticipante.DOCENTE);
                assertNotNull(resTipoYTextoDoc);
                assertEquals(1, resTipoYTextoDoc.size(), "Debería haber 1 docente con apellido Pérez");
                assertEquals("Carlos Alberto", resTipoYTextoDoc.get(0).getNombre());
                assertEquals("Pérez", resTipoYTextoDoc.get(0).getApellidoPaterno());
                assertEquals(TipoParticipante.DOCENTE, resTipoYTextoDoc.get(0).getTipoParticipante());

                // Escenario 5: Filtrar solo por texto ("Pérez"), TipoParticipante es null
                List<ParticipanteDTO> resSoloTexto = participantesDAO.obtenerParticipantesConFiltroYTipo("Pérez", null);
                assertNotNull(resSoloTexto);
                assertEquals(3, resSoloTexto.size(),
                                "Deberían haber 3 participantes con 'Pérez' en su nombre/apellido");
                assertTrue(resSoloTexto.stream()
                                .anyMatch(p -> p.getNombre().equals("Juan Carlos")
                                                && p.getApellidoPaterno().equals("Pérez"))); // Juan
                                                                                             // Carlos
                                                                                             // Pérez
                assertTrue(resSoloTexto.stream()
                                .anyMatch(p -> p.getNombre().equals("Carlos Alberto")
                                                && p.getApellidoPaterno().equals("Pérez"))); // Carlos
                                                                                             // Alberto
                                                                                             // Pérez
                assertTrue(resSoloTexto.stream()
                                .anyMatch(p -> p.getNombre().equals("Pedro")
                                                && p.getApellidoMaterno().equals("Pérez"))); // Pedro
                                                                                             // Sánchez
                                                                                             // Pérez

                // Escenario 6: Filtrar por TipoParticipante (EXTERNO) y texto que no coincide
                // ("Juan Carlos")
                List<ParticipanteDTO> resTipoYTextoNoCoincide = participantesDAO
                                .obtenerParticipantesConFiltroYTipo("Juan Carlos", TipoParticipante.EXTERNO);
                assertNotNull(resTipoYTextoNoCoincide);
                assertTrue(resTipoYTextoNoCoincide.isEmpty(), "No debería haber externos llamados Juan Carlos");

                // Escenario 7: Filtro de texto vacío y TipoParticipante null (debería devolver
                // todos)
                List<ParticipanteDTO> resTodos = participantesDAO.obtenerParticipantesConFiltroYTipo("", null);
                assertNotNull(resTodos);
                assertEquals(5, resTodos.size(), "Debería devolver todos los 5 participantes");

                // Escenario 8: Filtro de texto que no existe y TipoParticipante null
                List<ParticipanteDTO> resTextoNoExiste = participantesDAO
                                .obtenerParticipantesConFiltroYTipo("XYZ123ABC", null);
                assertNotNull(resTextoNoExiste);
                assertTrue(resTextoNoExiste.isEmpty(), "No debería encontrar participantes con texto inexistente");

                // Escenario 9: Filtro de texto que no existe y TipoParticipante específico
                List<ParticipanteDTO> resTextoNoExisteConTipo = participantesDAO.obtenerParticipantesConFiltroYTipo(
                                "XYZ123ABC",
                                TipoParticipante.ESTUDIANTE);
                assertNotNull(resTextoNoExisteConTipo);
                assertTrue(resTextoNoExisteConTipo.isEmpty(), "No debería encontrar estudiantes con texto inexistente");

                // Escenario 10: Filtrar por correo y tipo
                List<ParticipanteDTO> resCorreoYTipo = participantesDAO
                                .obtenerParticipantesConFiltroYTipo("juan.estudiante@example.com",
                                                TipoParticipante.ESTUDIANTE);
                assertNotNull(resCorreoYTipo);
                assertEquals(1, resCorreoYTipo.size());
                assertEquals("juan.estudiante@example.com", resCorreoYTipo.get(0).getCorreo());
                assertEquals(TipoParticipante.ESTUDIANTE, resCorreoYTipo.get(0).getTipoParticipante());

                // Escenario 11: Filtrar por número de control y tipo
                List<ParticipanteDTO> resNumControlYTipo = participantesDAO.obtenerParticipantesConFiltroYTipo(
                                "0000012345",
                                TipoParticipante.ESTUDIANTE);
                assertNotNull(resNumControlYTipo);
                assertEquals(1, resNumControlYTipo.size());
                assertEquals("0000012345", resNumControlYTipo.get(0).getNumeroControl());
                assertEquals(TipoParticipante.ESTUDIANTE, resNumControlYTipo.get(0).getTipoParticipante());

                // Limpieza específica para este test
                for (Participante p : participantesCreadosEnEsteTest) {
                        if (p != null && p.getId() != null) {
                                try {
                                        participantesDAO.eliminarParticipante(p.getId());
                                } catch (Exception e) {
                                        System.err.println("Error al limpiar participante con ID " + p.getId() + ": "
                                                        + e.getMessage());
                                }
                        }
                }
        }

        // Método helper para registrar y guardar en una lista para limpieza
        private Participante registrarYRecordar(ParticipanteDTO dto, List<Participante> listaParaLimpiar)
                        throws SeguridadException {
                Participante p = participantesDAO.registrarParticipante(dto);
                if (p != null) {
                        listaParaLimpiar.add(p);
                }
                return p;

        }

        @Test
        public void testObtenerParticipantes() throws SeguridadException {
                System.out.println("obtenerParticipantes");
                registrarParticipante();
                List<ParticipanteDTO> result = participantesDAO.obtenerParticipantes();
                assertNotNull(result);
                assertFalse(result.isEmpty(), "La lista de participantes no debería estar vacía");
                for (ParticipanteDTO p : result) {
                        assertNotNull(p.getId(), "El ID del participante no debería ser nulo");
                        assertNotNull(p.getNombre(), "El nombre del participante no debería ser nulo");
                        assertNotNull(p.getApellidoPaterno(),
                                        "El apellido paterno del participante no debería ser nulo");
                        assertNotNull(p.getApellidoMaterno(),
                                        "El apellido materno del participante no debería ser nulo");
                        assertNotNull(p.getCorreo(), "El correo del participante no debería ser nulo");
                        assertNotNull(p.getTipoParticipante(), "El tipo de participante no debería ser nulo");
                }
        }

        @Test
        public void testObtenerParticipantesPorTipo() throws SeguridadException {
                System.out.println("obtenerParticipantesPorTipo");

                List<Participante> participantesCreadosEnEsteTest = new ArrayList<>();

                // Registrar participantes de diferentes tipos
                ParticipanteDTO pEstudiante1DTO = new ParticipanteDTO(
                                "Elena", "Valdez", "Campos", "elena.estudiante@example.com",
                                TipoParticipante.ESTUDIANTE, "Arquitectura", "0000022222");
                ParticipanteDTO pEstudiante2DTO = new ParticipanteDTO(
                                "Mario", "Luna", "Solano", "mario.estudiante@example.com",
                                TipoParticipante.ESTUDIANTE, "Diseño Gráfico", "0000033333");
                ParticipanteDTO pDocente1DTO = new ParticipanteDTO(
                                "Ricardo", "Montes", "Vega", "ricardo.docente@example.com",
                                TipoParticipante.DOCENTE, "Departamento de Historia", null);
                ParticipanteDTO pExterno1DTO = new ParticipanteDTO(
                                "Sofia", "Ríos", "Blanco", "sofia.externo@example.com",
                                TipoParticipante.EXTERNO, null, null);

                registrarYRecordar(pEstudiante1DTO, participantesCreadosEnEsteTest);
                registrarYRecordar(pEstudiante2DTO, participantesCreadosEnEsteTest);
                registrarYRecordar(pDocente1DTO, participantesCreadosEnEsteTest);
                registrarYRecordar(pExterno1DTO, participantesCreadosEnEsteTest);

                // Escenario 1: Obtener solo ESTUDIANTES
                List<ParticipanteDTO> estudiantes = participantesDAO
                                .obtenerParticipantesPorTipo(TipoParticipante.ESTUDIANTE);
                assertNotNull(estudiantes, "La lista de estudiantes no debería ser nula.");
                assertEquals(2, estudiantes.size(), "Debería haber 2 participantes de tipo ESTUDIANTE.");
                for (ParticipanteDTO p : estudiantes) {
                        assertEquals(TipoParticipante.ESTUDIANTE, p.getTipoParticipante(),
                                        "Todos los participantes deberían ser ESTUDIANTE.");
                        assertNotNull(p.getNumeroControl(), "Los estudiantes deben tener número de control.");
                }
                assertTrue(estudiantes.stream().anyMatch(p -> p.getNombre().equals("Elena")),
                                "Debería encontrar a Elena.");
                assertTrue(estudiantes.stream().anyMatch(p -> p.getNombre().equals("Mario")),
                                "Debería encontrar a Mario.");

                // Escenario 2: Obtener solo DOCENTES
                List<ParticipanteDTO> docentes = participantesDAO.obtenerParticipantesPorTipo(TipoParticipante.DOCENTE);
                assertNotNull(docentes, "La lista de docentes no debería ser nula.");
                assertEquals(1, docentes.size(), "Debería haber 1 participante de tipo DOCENTE.");
                assertEquals(TipoParticipante.DOCENTE, docentes.get(0).getTipoParticipante(),
                                "El participante debería ser DOCENTE.");
                assertEquals("Ricardo", docentes.get(0).getNombre(), "Debería ser Ricardo.");
                assertNull(docentes.get(0).getNumeroControl(),
                                "Los docentes en este test no tienen número de control.");

                // Escenario 3: Obtener solo EXTERNOS
                List<ParticipanteDTO> externos = participantesDAO.obtenerParticipantesPorTipo(TipoParticipante.EXTERNO);
                assertNotNull(externos, "La lista de externos no debería ser nula.");
                assertEquals(1, externos.size(), "Debería haber 1 participante de tipo EXTERNO.");
                assertEquals(TipoParticipante.EXTERNO, externos.get(0).getTipoParticipante(),
                                "El participante debería ser EXTERNO.");
                assertEquals("Sofia", externos.get(0).getNombre(), "Debería ser Sofia.");
                assertNull(externos.get(0).getNumeroControl(), "Los externos no tienen número de control.");
                assertNull(externos.get(0).getDependencia(), "Los externos no tienen dependencia.");

                // Escenario 4: Intentar obtener un tipo sin participantes registrados (si fuera
                // el caso)
                // Para este test, todos los tipos tienen al menos un participante.
                // Si tuvieras un TipoParticipante que no se usa, podrías probarlo así:
                // List<ParticipanteDTO> otros =
                // participantesDAO.obtenerParticipantesPorTipo(TipoParticipante.OTRO_TIPO_NO_USADO);
                // assertNotNull(otros);
                // assertTrue(otros.isEmpty(), "No debería haber participantes de un tipo no
                // registrado.");

                // Limpieza específica para este test
                for (Participante p : participantesCreadosEnEsteTest) {
                        if (p != null && p.getId() != null) {
                                try {
                                        participantesDAO.eliminarParticipante(p.getId());
                                } catch (Exception e) {
                                        System.err.println("Error al limpiar participante con ID " + p.getId()
                                                        + " en testObtenerParticipantesPorTipo: " + e.getMessage());
                                }
                        }
                }
        }

}
