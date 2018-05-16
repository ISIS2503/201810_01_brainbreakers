/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.entities.CalificacionEntity;
import co.edu.uniandes.csw.boda.entities.ParejaEntity;
import co.edu.uniandes.csw.boda.entities.TarjetaCreditoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.runner.RunWith;

import org.jboss.arquillian.container.test.api.Deployment;

import org.jboss.shrinkwrap.api.ShrinkWrap;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author nf.ortiz
 */
@RunWith(Arquillian.class)
public class ParejaPersistenceTest {
     /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ParejaPersistence persistence;

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
    private List<ParejaEntity> data = new ArrayList<ParejaEntity>();
     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Pareja, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ParejaEntity.class.getPackage())
                .addPackage(ParejaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    private void clearData() {
        em.createQuery("delete from ParejaEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ParejaEntity entity = factory.manufacturePojo(ParejaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    public ParejaPersistenceTest() {
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
    
    @After
    public void tearDown() {
    }
    
    
    /**
     * Test of method of class ParejaEntity.
     */
    @Test
    public void testEntity() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        ParejaEntity entity = factory.manufacturePojo(ParejaEntity.class);
        
        //Prueba el metodo getBoda()
        Assert.assertNull("No tiene bodas asignadas", entity.getBodas());
        
        
        //Prueba el metodo setBoda()
        factory = new PodamFactoryImpl();
        List<BodaEntity> bodas = new ArrayList<>();
        for(int i =0; i<3 ;i++){
            bodas.add(factory.manufacturePojo(BodaEntity.class));
        }
        try{entity.setBodas(bodas);}catch(Exception e){Assert.fail("No debio generar error");} 
        
        //Prueba el metodo getTarjetasCredito()
        Assert.assertNull("No tiene tarjetas asignadas", entity.getTarjetasCredito());
        
        //Prueba el metodo setBoda()
        factory = new PodamFactoryImpl();
        List<TarjetaCreditoEntity>tarjetas = new ArrayList<>();
        for(int i=0;i<3;i++){
            tarjetas.add(factory.manufacturePojo(TarjetaCreditoEntity.class));
        }        
        try{entity.setTarjetasCredito(tarjetas);}catch(Exception e){Assert.fail("No debio generar error");} 
        
        //Prueba el metodo dar numero
        Assert.assertNotNull("Tiene numero asignado", entity.getTelefono());
        
        //Prueba el metodo dar numero
        Assert.assertNotNull( entity.isPago());
        
    }
    
    /**
     * Test of create method, of class ParejaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        //Caso 1: Se agrega correctamente el elemento
        PodamFactory factory = new PodamFactoryImpl();
        ParejaEntity entity = factory.manufacturePojo(ParejaEntity.class);
       try{        
            persistence.create(entity);
            assertEquals("Se debio agregar el elemento", 4,persistence.findAll().size());  
       }catch(Exception e)
       {
           fail("No debio llegar aquí.");
       }
        
        //Caso 2: Se agrega una entity con el mismo correo.
        try{
            persistence.create(entity);
            fail("No debio llegar aquí.");
        }catch(Exception e){    
            
        }
    }

    /**
     * Test of update method, of class ParejaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        //Caso 1: se actualiza un Comentario existente
        try{
            PodamFactory factory = new PodamFactoryImpl();
            ParejaEntity entity = factory.manufacturePojo(ParejaEntity.class);
            String idData = data.get(0).getCorreoElec(); entity.setCorreoElec(idData);
            persistence.update(entity);
            assertNotEquals("No deben ser iguales", persistence.find(idData).getContrasenia(), data.get(0).getContrasenia());
            
        }catch(Exception e){
            fail("No debió llegar aquí.");
        }  
        
        //Caso 2: se actualiza un Comentario no existente
        try{
            PodamFactory factory = new PodamFactoryImpl();
            ParejaEntity entity = factory.manufacturePojo(ParejaEntity.class);
            entity.setCorreoElec("IsNotText18977777");
            persistence.update(entity);
            fail("No debió llegar aquí.");
        }catch(Exception e){
           
        }
    }

    /**
     * Test of delete method, of class ParejaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        //Caso 1: El elemento a borrar no existe
        try{
            persistence.delete("IsNotText18977777");
            fail("Nunca debio llegar aquí");
        }
        catch(Exception e){
            assertEquals("El elemento a borrar no existe",3,persistence.findAll().size());
        }
        
        //Caso 2: el elemento a borrar existe
        try{
            String idData=data.get(0).getCorreoElec();
            persistence.delete(idData);
            assertEquals("El elemento a borrar existe",2,persistence.findAll().size());            
        }
        catch(Exception e){
            fail("Nunca debio llegar aquí");
            
        }
    }

    /**
     * Test of find method, of class ParejaPersistence.
     */
    @Test
    public void testFind() throws Exception {
         //Caso 1: el id dado no existe
         assertNull("Debe ser null, el id no existe", persistence.find("IsNotText18977777"));
         
         //Caso 2: El id dado existe
         String idData = data.get(0).getCorreoElec();
         assertNotNull("Debe existir el objeto", persistence.find(idData));
         
    }

    /**
     * Test of findAll method, of class ParejaPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
       //Caso 1: Dar todos los elementos de la Base de Datos
        assertEquals("Debe haber tres elementos en la lista", 3, persistence.findAll().size());
        
         
        //Caso 2: Todos los elementos debieron quedar bien guardados
        List<ParejaEntity> base = persistence.findAll();
        for(int i=0; i<3;i++){
            ParejaEntity obj2=base.get(i);
            boolean bien =true;
            boolean encontrado =false;
            for(ParejaEntity obj1: data){
                if(obj1.getCorreoElec().equals(obj2.getCorreoElec())){
                    encontrado=true;
                    if(!obj1.getContrasenia().equals(obj2.getContrasenia()))bien=false;
                    else if(!obj1.getDireccion().equals(obj2.getDireccion()))bien=false;
                    else if(!obj1.getNombreAbreviado().equals(obj2.getNombreAbreviado()))bien=false;
                    else if(!obj1.getNombreInd1().equals(obj2.getNombreInd1()))bien=false;
                    else if(!obj1.getNombreInd2().equals(obj2.getNombreInd2()))bien=false;
                    break;
                 }
            }         
           
            assertTrue("El elemento no se guardo de forma correcta " + i,(bien&&encontrado));
        }
    }
    
}
