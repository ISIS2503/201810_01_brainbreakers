/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.OpcionServicioEntity;
import co.edu.uniandes.csw.boda.entities.TareaEntity;
import co.edu.uniandes.csw.boda.entities.UbicacionEntity;
import co.edu.uniandes.csw.boda.persistence.TareaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;




/**
 *
 * @author sp.joven
 */
@RunWith(Arquillian.class)
public class TareaPersistenceTest {
    
    /**
     * Inyección de la dependencia a la clase TareaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private TareaPersistence persistence;
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     *
     */
    private List<TareaEntity> data = new ArrayList<TareaEntity>();
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TareaEntity.class.getPackage())
                .addPackage(TareaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
     public void testEntity() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        TareaEntity entity = factory.manufacturePojo(TareaEntity.class);
        
        //Prueba el metodo  isAprobada())
        Assert.assertNotNull( entity.isAprobada());
        
        //Prueba el metodo  getDia() 
        Assert.assertNotNull("Tiene dia asignada", entity.getDia() );
        
        //Prueba el metodo  getNombre()
        Assert.assertNotNull("Tiene nombre asignado", entity.getNombre());
        
        //Prueba el metodo  getImage()
        Assert.assertNotNull("Tiene imagen asignada", entity.getImage());
        
        //Prueba el metodo getOpcionServicio()
        Assert.assertNull("No Tiene opcionServicio asignada", entity.getOpcionServicio());
        
        //Prueba el metodo  setOpcionServicio()
        factory = new PodamFactoryImpl();
        try{entity. setOpcionServicio(factory.manufacturePojo(OpcionServicioEntity.class));}catch(Exception e){Assert.fail("No debio generar error");} 
        
              
       //Prueba el metodo getUbicacion() 
        Assert.assertNull("No Tiene ubicacion asignada", entity.getUbicacion());
        
        //Prueba el metodo  setUbicacion() 
        factory = new PodamFactoryImpl();
        try{entity.setUbicacion(factory.manufacturePojo(UbicacionEntity.class));}catch(Exception e){Assert.fail("No debio generar error");} 
        
    }
    @Test
public void createTareaPersistenceTest() {
    PodamFactory factory = new PodamFactoryImpl();
    TareaEntity newEntity = factory.manufacturePojo(TareaEntity.class);
    TareaEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    TareaEntity entity = em.find(TareaEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
}
/*
@Test
public void getTareasTest() {
    List<TareaEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (TareaEntity ent : list) {
        boolean found = false;
        for (TareaEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
}
*/

 /**
     * Test of findAllByOpcion method, of class CalificacionPersistence.
     * busca todas las calificaciones de un servicio
     */
    @Test
    public void testFindAllByOpcion() throws Exception {
          //Se supone que ningun opcion Servicio tiene Calificacion
        Assert.assertEquals(0,persistence.findAllByOpcion(Long.MIN_VALUE).size());
    }

@Test
public void getTareaTest() {
    TareaEntity entity = data.get(0);
    TareaEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
}
@Test
public void getTareaByNameTest() {
    TareaEntity entity = data.get(0);
    TareaEntity newEntity = persistence.findByName(entity.getName());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
}
@Test
public void updateTareaTest() {
    TareaEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    TareaEntity newEntity = factory.manufacturePojo(TareaEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    TareaEntity resp = em.find(TareaEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
}
@Test
public void deleteTareaTest() {
    TareaEntity entity = data.get(0);
    persistence.delete(entity.getId());
    TareaEntity deleted = em.find(TareaEntity.class, entity.getId());
    Assert.assertNull(deleted);
}
    public TareaPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
        @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    private void clearData() {
        em.createQuery("delete from TareaEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TareaEntity entity = factory.manufacturePojo(TareaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    @After
    public void tearDown() {
    }
}
