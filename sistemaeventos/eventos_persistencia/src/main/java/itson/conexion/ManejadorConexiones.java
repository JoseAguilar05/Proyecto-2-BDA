package itson.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManejadorConexiones {

    private static boolean isTestMode = false;
    private static final String TEST_PU = "itson_eventos_dominio_jar_1.0-SNAPSHOTPUTEST";
    private static final String REAL_PU = "itson_eventos_dominio_jar_1.0-SNAPSHOTPU";

    /**
     * Crea un entityManager a partir de un String con el nombre de la
     * persistence Unit
     *
     * @param persistenceUnit String con el nombre de la persistence Unit
     * @return EntityManager de la persistence Unit
     */
    public static EntityManager obtenerConexion() {
        if(isTestMode) {
            return crearEntityManager(TEST_PU);
        } else {
            return crearEntityManager(REAL_PU);
        }
    }
    /**
     * Crea un entityManager a partir de un String con el nombre de la
     * persistence Unit
     *
     * @param persistenceUnit String con el nombre de la persistence Unit
     * @return EntityManager de la persistence Unit
     */
    private static EntityManager crearEntityManager(String persistenceUnit) {
        EntityManagerFactory emFactory
                = Persistence.createEntityManagerFactory(persistenceUnit);
        EntityManager entityManager = emFactory.createEntityManager();
        return entityManager;
    }

    
    /**
     * Activa el modo de pruebas
     */
    public static void activateTestMode() {
        isTestMode = true;
    }

    /**
     * Desactiva el modo de pruebas
     */
    public static void deactivateTestMode() {
        isTestMode = false;
    }
}
