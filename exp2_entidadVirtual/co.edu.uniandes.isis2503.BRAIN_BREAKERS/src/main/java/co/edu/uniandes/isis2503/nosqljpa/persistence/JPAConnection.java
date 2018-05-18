package co.edu.uniandes.isis2503.nosqljpa.persistence;

import com.impetus.client.cassandra.common.CassandraConstants;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnection {

    public static final String DERBY = "derby_db";
    public static final String CASSANDRA = "cassandra_db";

    public static final String DB = DERBY;

    private EntityManager entityManager;
    
//    public static final JPAConnection CONNECTION = new JPAConnection();

    public JPAConnection() {
        System.out.println("ENTRA A CONSTRUCTOR ------------------------------------------------");
        if (entityManager == null) {
            System.out.println("ENTRO DADO QUE ENTITYMANAGER ES NULO ------------------------------------------------");
            EntityManagerFactory emf;
            if (DB.equals(DERBY)) {
                System.out.println("ENTRA A CREAR EMF CON DERBY ------------------------------------------------");
                emf = Persistence.createEntityManagerFactory(DERBY);
                System.out.println("SALE DE CREAR EMF CON DERBY ------------------------------------------------");
            } else {
                Map<String, String> propertyMap = new HashMap<>();
                propertyMap.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
                emf = Persistence.createEntityManagerFactory(CASSANDRA, propertyMap);
            }
            entityManager = emf.createEntityManager();
            System.out.println("SALE DE CREAR EL ENTITYMANAGER ------------------------------------------------");
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
