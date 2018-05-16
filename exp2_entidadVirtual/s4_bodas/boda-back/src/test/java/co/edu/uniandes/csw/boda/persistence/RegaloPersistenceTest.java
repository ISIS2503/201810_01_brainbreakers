/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.entities.InvitadoEntity;
import co.edu.uniandes.csw.boda.entities.RegaloEntity;
import co.edu.uniandes.csw.boda.entities.UbicacionEntity;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author mf.valllejo
 */
@RunWith(Arquillian.class)
public class RegaloPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RegaloEntity.class.getPackage())
                .addPackage(RegaloPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public RegaloPersistenceTest() {
    }

    /**
     * Inyección de la dependencia a la clase RegaloPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private RegaloPersistence persistence;

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
        em.createQuery("delete from RegaloEntity").executeUpdate();
    }

    /**
     *
     */
    private List<RegaloEntity> data = new ArrayList<RegaloEntity>();

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            RegaloEntity entity = factory.manufacturePojo(RegaloEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    @Test
    public void testEntity() {
        PodamFactory factory = new PodamFactoryImpl();
        RegaloEntity newEntity = factory.manufacturePojo(RegaloEntity.class);        
      
        //Prueba el metodo getInvitado()
        Assert.assertNull("No Contiene Invitados asignados", newEntity.getInvitado());
        
        //Prueba el metodo getInvitado()
         factory = new PodamFactoryImpl();
        try{newEntity.setInvitado(factory.manufacturePojo(InvitadoEntity.class));}catch(Exception e){Assert.fail("No debio generar error");}
        
        //Prueba el metodo getLocations()
        Assert.assertNull("No Contiene locaciones asignadas", newEntity.getLocations());
        
        //Prueba el metodo setTarjetaCredito()         
        factory = new PodamFactoryImpl();
        List<UbicacionEntity>ubicaciones = new ArrayList<>();
        for(int i=0;i<3;i++){
            ubicaciones.add(factory.manufacturePojo(UbicacionEntity.class));
        }
        try{newEntity.setLocations(ubicaciones);}catch(Exception e){Assert.fail("No debio generar error");}
        
        //Prueba el metodo getBoda()
        Assert.assertNull("No Contiene boda asignada", newEntity.getBoda());
        
        //Prueba el metodo getInvitado()
         factory = new PodamFactoryImpl();
        try{newEntity.setBoda(factory.manufacturePojo(BodaEntity.class));}catch(Exception e){Assert.fail("No debio generar error");}
        
        //Prueba el metodo  getImagen()
        Assert.assertNotNull("Tiene imagen asignada", newEntity.getImagen());
        
         //Prueba el metodo  isComprado()
        Assert.assertNotNull("Tiene imagen asignada", newEntity.isComprado());
    }
    /**
     * Test of find method, of class RegaloPersistence.
     */
    @Test
    public void testFind() {
        RegaloEntity entity = data.get(0);
        RegaloEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of create method, of class RegaloPersistence.
     */
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        RegaloEntity newEntity = factory.manufacturePojo(RegaloEntity.class);
        RegaloEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        RegaloEntity entity = em.find(RegaloEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class RegaloPersistence.
     */
    @Test
    public void testUpdate() {
        RegaloEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        RegaloEntity newEntity = factory.manufacturePojo(RegaloEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        RegaloEntity resp = em.find(RegaloEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of Delete method, of class RegaloPersistence.
     */
    @Test
    public void testDelete() {
        RegaloEntity entity = data.get(0);
        persistence.delete(entity.getId());
        RegaloEntity deleted = em.find(RegaloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void getRegaloByNameTest() {
        //Caso: El regalo existe
        RegaloEntity entity = data.get(0);
        RegaloEntity newEntity = persistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());

        //Caso: El regalo NO existe
        newEntity = persistence.findByName("Un_Regalo_No_Existente");
        Assert.assertNull("El regalo no existe.",newEntity);

    }
}
