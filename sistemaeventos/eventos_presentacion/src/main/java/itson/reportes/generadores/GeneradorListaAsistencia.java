package itson.reportes.generadores;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
// Nuevas importaciones necesarias
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import itson.conexion.ManejadorConexiones; // Asumiendo que puedes obtener una conexión JDBC desde aquí

public class GeneradorListaAsistencia {

    public void GenerarReporte(Integer idActividad) {
        Connection conexion = null; // Declarar la conexión fuera del try para cerrarla en finally
        try{
            // 1. Obtener la ruta al archivo .jasper
            // Es más robusto obtener el recurso a través del ClassLoader
            String jasperFilePath = "reportes/lista_asistencia.jasper"; // Ruta relativa al classpath (ej. src/main/resources)
            java.io.InputStream jasperStream = getClass().getClassLoader().getResourceAsStream(jasperFilePath);
            if (jasperStream == null) {
                System.err.println("No se pudo encontrar el archivo Jasper: " + jasperFilePath + 
                                   ". Asegúrate que esté en 'src/main/resources/reportes/' y que 'resources' esté en el classpath.");
                // Fallback a la ruta original si no se encuentra como recurso, aunque es menos portable
                jasperStream = new java.io.FileInputStream(new java.io.File("sistemaeventos/eventos_presentacion/src/main/resources/reportes/lista_asistencia.jasper"));
            }
            
            JasperReport reporte = (JasperReport) JRLoader.loadObject(jasperStream);

            // 2. Preparar los parámetros para el reporte
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("idActividad", idActividad);

            // 3. Obtener una conexión JDBC a la base de datos
            // Deberás implementar la lógica para obtener una conexión JDBC.
            // Si ManejadorConexiones puede proporcionarla, úsalo.
            // Ejemplo: conexion = ManejadorConexiones.obtenerConexionJDBC(); 
            // Por ahora, asumiré que tienes un método para esto.
            // Si estás usando JPA y quieres obtener la conexión subyacente:
            javax.persistence.EntityManager em = ManejadorConexiones.obtenerConexion(); // Obtén el EntityManager
            em.getTransaction().begin(); // Necesario para algunos proveedores JPA para acceder a la conexión
            conexion = em.unwrap(java.sql.Connection.class); // Desenvuelve la conexión JDBC
            // Nota: La transacción abierta aquí debería manejarse con cuidado.
            // Para reportes de solo lectura, usualmente no se necesita una transacción activa a nivel de JDBC.

            // 4. Llenar el reporte
            // JasperFillManager se encarga de ejecutar la consulta del reporte usando la conexión y los parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, conexion);
            
            // Si abriste una transacción en el EntityManager solo para obtener la conexión, ciérrala.
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // O commit() si hiciste cambios, aunque para reportes es raro.
            }
            // No cierres el EntityManager aquí si lo vas a seguir usando en otro lado.
            // La conexión JDBC obtenida de un EntityManager es gestionada por el EntityManager.

            // 5. Mostrar el reporte usando JasperViewer
            // El segundo parámetro 'false' significa que la aplicación no saldrá cuando se cierre el visor.
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
            // Considera mostrar un JOptionPane al usuario aquí
            // JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage(), "Error de Reporte", JOptionPane.ERROR_MESSAGE);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Archivo de reporte no encontrado: " + e.getMessage(), "Error de Reporte", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) { // Captura general para otros errores, como obtener la conexión
            e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Error inesperado al generar el reporte: " + e.getMessage(), "Error de Reporte", JOptionPane.ERROR_MESSAGE);
        } finally {
            // La conexión JDBC obtenida de un EntityManager generalmente no debe cerrarse manualmente aquí,
            // ya que su ciclo de vida está ligado al EntityManager.
            // Si obtuviste la conexión JDBC de otra manera (ej. DriverManager.getConnection()),
            // deberías cerrarla aquí:
            // if (conexion != null) {
            //     try {
            //         conexion.close();
            //     } catch (SQLException ex) {
            //         ex.printStackTrace();
            //     }
            // }
        }
    }    
}