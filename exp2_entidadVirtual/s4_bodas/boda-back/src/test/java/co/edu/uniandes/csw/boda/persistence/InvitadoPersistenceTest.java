/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.entities.InvitadoEntity;
import co.edu.uniandes.csw.boda.entities.RegaloEntity;
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
public class InvitadoPersistenceTest {
    
    public InvitadoPersistenceTest() {
    }
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InvitadoEntity.class.getPackage())
                .addPackage(InvitadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase InvitadoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private InvitadoPersistence persistence;

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
        em.createQuery("delete from InvitadoEntity").executeUpdate();
    }
    
    private List<InvitadoEntity> data = new ArrayList<InvitadoEntity>();
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            InvitadoEntity entity = factory.manufacturePojo(InvitadoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
     @Test
    public void testEntity() {
        PodamFactory factory = new PodamFactoryImpl();
        InvitadoEntity newEntity = factory.manufacturePojo(InvitadoEntity.class);
        
        //Prueba el metodo getRegalo()
        Assert.assertNull("No Contiene regalo asignado", newEntity.getRegalo());
        
        //Prueba el metodo setRegalo()
        factory = new PodamFactoryImpl();        
        try{newEntity.setRegalo(factory.manufacturePojo(RegaloEntity.class));}catch(Exception e){Assert.fail("No debio generar error");} 
        
         //Prueba el metodo getBoda()
        junit.framework.Assert.assertNull("No tiene bodas asignadas", newEntity.getBoda());
        
        
        //Prueba el metodo setBoda()
        factory = new PodamFactoryImpl();
        BodaEntity boda = factory.manufacturePojo(BodaEntity.class);
        try{newEntity.setBoda(boda);}catch(Exception e){junit.framework.Assert.fail("No debio generar error");} 
        
               
        //Prueba el metodo getCorreo()
        Assert.assertNotNull(" Contiene una imagen asignada", newEntity.getCorreo());  
        
        //Prueba el metodo isAsistencia() 
        Assert.assertNotNull(" Contiene una asistencia asignada", newEntity.getAsistencia());
        
        //Prueba el metodo getCategoria() 
        Assert.assertNotNull(" Contiene una imagen asignada", newEntity.getCategoria());  
    }
    
    
    /**
     * Test of create method, of class InvitadoPersistence.
     */
    @Test
    public void testCreate(){
        PodamFactory factory = new PodamFactoryImpl();
        InvitadoEntity newEntity = factory.manufacturePojo(InvitadoEntity.class);
        InvitadoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        InvitadoEntity entity = em.find(InvitadoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of find method, of class InvitadoPersistence.
     */
    @Test
    public void testFind() {
        InvitadoEntity entity = data.get(0);
        InvitadoEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of update method, of class InvitadoPersistence.
     */
    @Test
    public void testUpdate() {
        InvitadoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        InvitadoEntity newEntity = factory.manufacturePojo(InvitadoEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        InvitadoEntity resp = em.find(InvitadoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of Delete method, of class InvitadoPersistence.
     */
    @Test
    public void testDelete(){
        InvitadoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        InvitadoEntity deleted = em.find(InvitadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void testDocumento(){
        //Caso: el documento Existe
        InvitadoEntity entity = data.get(0);
        InvitadoEntity newEntity = persistence.findByDocumento(entity.getDocumento());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDocumento(), newEntity.getDocumento());
        
        //Caso: el documento NO Existe
        newEntity = persistence.findByDocumento(9951L);
        Assert.assertNull(newEntity);
    }
}
