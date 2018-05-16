/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.OpcionServicioEntity;
import co.edu.uniandes.csw.boda.entities.PagoEntity;
import co.edu.uniandes.csw.boda.entities.TarjetaCreditoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author ca.guerrero
 */
@RunWith(Arquillian.class)

public class PagoPersistenceTest {
    
    /**
     * Inyección de la dependencia a la clase PagoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PagoPersistence persistence;

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
    private List<PagoEntity> data = new ArrayList<PagoEntity>();
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Pago, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public PagoPersistenceTest() {
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
    em.createQuery("delete from PagoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @After
    public void tearDown() {
    }
    @Test
    public void testEntity() {
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        
        //Prueba el metodo getMontoTotal() 
        Assert.assertNotNull("Contiene un monto total asignada", newEntity.getMontoTotal());
        
        //Prueba el metodo getFecha()
        Assert.assertNotNull("Contiene una fecha asignada", newEntity.getFecha());
        
        //Prueba el metodo getTarjetaCredito()
        Assert.assertNull("No Contiene Tarjetas de Credito asignadas", newEntity.getTarjetaCredito());
        
        //Prueba el metodo setTarjetaCredito()
         factory = new PodamFactoryImpl();
        try{newEntity.setTarjetaCredito(factory.manufacturePojo(TarjetaCreditoEntity.class));}catch(Exception e){Assert.fail("No debio generar error");}
        
        //Prueba el metodo getOpcionServicio()
        Assert.assertNull("No Contiene opciones servicio asignadas", newEntity.getOpcionServicio());
        
        //Prueba el metodo setTarjetaCredito()
         factory = new PodamFactoryImpl();
        try{newEntity.setOpcionServicio(factory.manufacturePojo(OpcionServicioEntity.class));}catch(Exception e){Assert.fail("No debio generar error");}
    }
    /**
     * Test of create method, of class PagoPersistence.
     */
    @Test
    public void testCreatePagoEntity(){
    PodamFactory factory = new PodamFactoryImpl();
    PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
    PagoEntity result = persistence.create(newEntity);
    Assert.assertNotNull(result);
    PagoEntity entity = em.find(PagoEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of find method, of class PagoPersistence.
     */
    @Test
    public void testFindPagoEntity() {
    PagoEntity entity = data.get(0);
    PagoEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Test of findByNombre method, of class PagoPersistence.
     */
    @Test
    public void testFindByNombre() {
    PagoEntity entity = data.get(0);
    PagoEntity newEntity = persistence.findByNombre(entity.getName());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    
    /**
     * Test of findAll method, of class PagoPersistence.
     */
    @Test
    public void findAllPagoEntity() {
        List<PagoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PagoEntity ent : list) {
        boolean found = false;
        for (PagoEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }
    /**
     * Test of update method, of class PagoPersistence.
     */
    @Test
    public void updatePagoEntityTest() {
    PagoEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    PagoEntity resp = em.find(PagoEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
