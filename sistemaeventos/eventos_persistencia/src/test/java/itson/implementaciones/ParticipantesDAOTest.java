package itson.implementaciones;

import itson.conexion.ManejadorConexiones;
import itson.dtos.ParticipanteDTO;
import itson.entidades.Participante;
import itson.enums.TipoParticipante;
import itson.excepciones.SeguridadException;
import itson.interfaces.IParticipantesDAO;
import itson.seguridad.Seguridad;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ParticipantesDAOTest {

        private final IParticipantesDAO participantesDAO = new ParticipantesDAO();
        private Participante participanteGuardado;

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

        @AfterEach
        public void tearDown() {
                if (participanteGuardado != null) {
                        participantesDAO.eliminarParticipante(participanteGuardado.getId());
                }
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
