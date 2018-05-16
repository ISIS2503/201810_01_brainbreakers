/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.ProveedorEntity;
import co.edu.uniandes.csw.boda.entities.ServicioEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
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
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author aj.ortiz10
 */
@RunWith(Arquillian.class)
public class ServicioPersistenceTest {

    public ServicioPersistenceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Boda, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ServicioEntity.class.getPackage())
                .addPackage(ServicioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase BodaPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private ServicioPersistence persistence;

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
        em.createQuery("delete from ServicioEntity").executeUpdate();
    }

    /**
     * Arreglo que contiene el conjunto de datos de prueba
     */
    private List<ServicioEntity> data = new ArrayList<ServicioEntity>();

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ServicioEntity entity = factory.manufacturePojo(ServicioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
     @Test
    public void testEntity() {
        PodamFactory factory = new PodamFactoryImpl();
        ServicioEntity newEntity = factory.manufacturePojo(ServicioEntity.class);
        
        //Prueba el metodo getDescripcion()
        Assert.assertNotNull("Contiene una fecha asignada", newEntity.getDescripcion());
        
        //Prueba el metodo getProveedores()
        Assert.assertNull("No Contiene una lista de proveedores asignada", newEntity.getProveedores());
        
        //Prueba el metodo setProveedores()
        factory = new PodamFactoryImpl();
        List<ProveedorEntity>proveedores = new ArrayList<>();
        for(int i=0;i<3;i++){
            proveedores.add(factory.manufacturePojo(ProveedorEntity.class));
        }
        try{newEntity.setProveedores(proveedores);}catch(Exception e){Assert.fail("No debio generar error");} 
        
        //Prueba el metodo getPareja()
        Assert.assertNotNull(" Contiene una imagen asignada", newEntity. getImage());       
    }

    @Test
    public void createServicioEntityTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ServicioEntity newEntity = factory.manufacturePojo(ServicioEntity.class);
        ServicioEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        ServicioEntity entity = em.find(ServicioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    @Test
    public void getServiciosTest() {
        List<ServicioEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ServicioEntity ent : list) {
            boolean found = false;
            for (ServicioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getServicioTest() {
        //Caso: El servicio existe
        ServicioEntity entity = data.get(0);
        ServicioEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());

        //Caso: El servicio No existe
        newEntity = persistence.find(1000L);
        Assert.assertNull("El servicio no existe, debe retornar null",newEntity);
        
    }

    @Test
    public void getServicioByNameTest() {
        ServicioEntity entity = data.get(0);
        ServicioEntity newEntity = persistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        
        //Caso: El servicio No existe
        newEntity = persistence.findByName("UnServicioSantaFe");
        Assert.assertNull("El servicio no existe, debe retornar null",newEntity);
    }

    @Test
    public void updateServicioTest() {
        ServicioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ServicioEntity newEntity = factory.manufacturePojo(ServicioEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        ServicioEntity resp = em.find(ServicioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    @Test
    public void deleteServicioTest() {
        ServicioEntity entity = data.get(0);
        persistence.delete(entity.getId());
        ServicioEntity deleted = em.find(ServicioEntity.class, entity.getId());
        assertNull(deleted);
    }

}
