/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.entities.CalificacionEntity;
import co.edu.uniandes.csw.boda.entities.OpcionServicioEntity;
import co.edu.uniandes.csw.boda.entities.ProveedorEntity;
import co.edu.uniandes.csw.boda.entities.ServicioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
 * @author nf.ortiz
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest {

    @Deployment
    public static JavaArchive creatDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    //Atributos para pruebas
    @Inject
    private CalificacionPersistence persistence;

//    @Inject
//    private OpcionServicioPersistence persistenceOpcion;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<CalificacionEntity> data = new ArrayList<>();
    //Metodos

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

    public void clearData() {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }

    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Test of create method, of class CalificacionPersistence.
     */
    
      @Test
    public void testEntity() throws Exception {
        //Para las pruebas de las Calificaciones el servidor no genera el id
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
        OpcionServicioEntity opcion = factory.manufacturePojo(OpcionServicioEntity.class);
        
        //Se le agrega un opcion
        try{entity.setOpcionServicio(opcion);}catch(Exception e){fail("No debio generar error");}        
        // tiene una opcionServicio
        Assert.assertNotNull(entity.getOpcionServicio());
        
        //Como tiene comentario, el comentario no debe ser null
        Assert.assertNotNull(entity.getComentario());
        
        //Como tiene calificacion, el comentario no debe ser null
        Assert.assertNotNull(entity.getCalificacionNum());
       
    }

    @Test
    public void testCreate() throws Exception {
        //Para las pruebas de las Calificaciones el servidor no genera el id
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);

        //Se crea una calificaccion por CalificacionPersistence
        Assert.assertNotNull("La persistencia debio retornar la clase", persistence.create(entity));

        //Deben haber 4 elementos en la base de datos
        int n = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class).getResultList().size();
        Assert.assertEquals(4, n);
    }

    /**
     * Test of update method, of class CalificacionPersistence.
     */
    @Test
    public void testUpdate() throws Exception {

        //Se actualiza una Calificacion que existe
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
        Long idOld = data.get(0).getId();
        entity.setId(idOld);
        Assert.assertNotNull(persistence.update(entity));
    }

    /**
     * Test of delete method, of class CalificacionPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        //Caso uno: se trata de borrar una Calificacion que no existe

        try {
            persistence.delete(Long.MIN_VALUE);
            fail("Debio mandar error.");
        } catch (Exception e) {
        }
        //Caso dos: se trata de borrar una Calificacion que existe
        try {
            persistence.delete(data.get(0).getId());

        } catch (Exception e) {
            fail("No debio llegar aqui.");
        }
    }

    /**
     * Test of find method, of class CalificacionPersistence. Encuentra una
     * Calificacion de un servicio dado.
     */
    @Test
    public void testFind() throws Exception {
        //Se supone que ningun opcion Servicio tiene Calificacion
        Assert.assertNull(persistence.find(Long.MIN_VALUE, data.get(0).getId()));
        
        //Que si exista una calificacion
        PodamFactory factory = new PodamFactoryImpl();
        OpcionServicioEntity opcion = factory.manufacturePojo(OpcionServicioEntity.class);
//        
//        factory = new PodamFactoryImpl();
//        BodaEntity boda = factory.manufacturePojo(BodaEntity.class);
//        em.persist(boda);
//        factory = new PodamFactoryImpl();
//        ProveedorEntity proveedor = factory.manufacturePojo(ProveedorEntity.class);
//        em.persist(proveedor);
//        
//        opcion.setBoda(boda);
//        opcion.setProveedor(proveedor);
//        
//        em.persist(opcion);    
//        
//        CalificacionEntity caf = data.get(0);
//        caf.setOpcionServicio(opcion);
//        em.merge(caf);
//        Assert.assertNotNull(persistence.find(opcion.getId(), caf.getId()));
    }

    /**
     * Test of findAllByOpcion method, of class CalificacionPersistence. busca
     * todas las calificaciones de un servicio
     */
    @Test
    public void testFindAllByOpcion() throws Exception {
        //Se supone que ningun opcion Servicio tiene Calificacion
        Assert.assertEquals(0, persistence.findAllByOpcion(Long.MIN_VALUE).size());

        //Que si exista una calificacion
//        PodamFactory factory = new PodamFactoryImpl();
//        OpcionServicioEntity opcion = factory.manufacturePojo(OpcionServicioEntity.class);
//        
//        factory = new PodamFactoryImpl();
//        BodaEntity boda = factory.manufacturePojo(BodaEntity.class);
//        em.persist(boda);
//        
//        factory = new PodamFactoryImpl();
//        ServicioEntity servicio = factory.manufacturePojo(ServicioEntity.class);
//        em.persist(servicio);
//        
//        factory = new PodamFactoryImpl();
//        ProveedorEntity proveedor = factory.manufacturePojo(ProveedorEntity.class);
//        em.persist(proveedor);
//        
//        List<ProveedorEntity> prove= new ArrayList<>();
//        prove.add(proveedor);
//        servicio.setProveedores(prove);
//        em.merge(servicio);
//        
//        opcion.setBoda(boda);
//        opcion.setProveedor(proveedor);
//        
//        em.persist(opcion);        
//        CalificacionEntity caf = data.get(0);
//        caf.setOpcionServicio(opcion);
//        em.merge(caf);
//        Assert.assertEquals(1, persistence.findAllByOpcion(opcion.getId()).size());

    }

    /**
     * Test of findById method, of class CalificacionPersistence. Busca una
     * calificacion por id
     */
    @Test
    public void testFindById() throws Exception {
        //Se supone que ningun opcion Servicio tiene Calificacion
        //Se busca un id que no existe
        Assert.assertNull(persistence.findById(Long.MIN_VALUE));
        //Se busca un id que  existe
        Assert.assertNotNull(persistence.findById(data.get(1).getId()));

    }

}
