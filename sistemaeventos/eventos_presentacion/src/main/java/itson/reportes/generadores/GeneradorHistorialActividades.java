package itson.reportes.generadores;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import itson.conexion.ManejadorConexiones;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class GeneradorHistorialActividades {

    public void generarHistorialActividades(Integer idParticipante) {
        Connection conexion = null; 
        try{
            String jasperFilePath = "reportes/historial_participante.jasper"; 
            java.io.InputStream jasperStream = getClass().getClassLoader().getResourceAsStream(jasperFilePath);
            if (jasperStream == null) {
                System.err.println("No se pudo encontrar el archivo Jasper: " + jasperFilePath + 
                                   ". Asegúrate que esté en 'src/main/resources/reportes/' y que 'resources' esté en el classpath.");
                jasperStream = new java.io.FileInputStream(new java.io.File("sistemaeventos/eventos_presentacion/src/main/resources/reportes/lista_asistencia.jasper"));
            }
            
            JasperReport reporte = (JasperReport) JRLoader.loadObject(jasperStream);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("idParticipante", idParticipante);

            javax.persistence.EntityManager em = ManejadorConexiones.obtenerConexion(); 
            em.getTransaction().begin(); 
            conexion = em.unwrap(java.sql.Connection.class); 

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, conexion);
            
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); 
            }

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
            } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) { 
            e.printStackTrace();
        } 
    }

}
