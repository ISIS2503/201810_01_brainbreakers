/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;


import co.edu.uniandes.csw.boda.entities.OpcionServicioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author sp.joven
 */
@Stateless
public class OpcionServicioPersistence 
{
       private static final Logger LOGGER = Logger.getLogger(OpcionServicioPersistence.class.getName());

    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto  OpcionServicio que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public OpcionServicioEntity create(OpcionServicioEntity entity) {
        LOGGER.info("Creando un opcionServicio nuevo");
        em.persist(entity);
        LOGGER.info("Creando un OpcionServicio nuevo");
        return entity;
    }

    /**
     * Actualiza un  OpcionServicio.
     *
     * @param entity: el  OpcionServicio que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un  OpcionServicio con los cambios aplicados.
     */
    public  OpcionServicioEntity update( OpcionServicioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando  OpcionServicio con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * Borra un OpcionServicio de la base de datos recibiendo como argumento el id
     * de la OpcionServicio
     *
     * @param id: id correspondiente a la  OpcionServicio a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando  OpcionServicio con id={0}", id);
         OpcionServicioEntity entity = em.find( OpcionServicioEntity.class, id);
        em.remove(entity);
    }
    public OpcionServicioEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando opcionServicio con name= ", name);
        TypedQuery<OpcionServicioEntity> q
                = em.createQuery("select u from OpcionServicioEntity u where u.name = :name", OpcionServicioEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }


    /**
     * Busca si hay algun  OpcionServicio con el id que se envía de argumento
     *
     * @param id: id correspondiente a la  OpcionServicio buscada.
     * @return un OpcionServicio.
     */
    public  OpcionServicioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando  OpcionServicio con id={0}", id);
        return em.find( OpcionServicioEntity.class, id);
    }
public  OpcionServicioEntity findByProveedor(Long proveedorId,Long opcionId) {
       TypedQuery< OpcionServicioEntity> q = em.createQuery("Select x from  OpcionServicioEntity x where x.proveedor.id = :proveedorId and x.id = :opcionId",  OpcionServicioEntity.class);

        q.setParameter("proveedorId", proveedorId);
       q.setParameter("opcionId", opcionId);
        List< OpcionServicioEntity> results = q.getResultList();
         OpcionServicioEntity opcion;
        if (results.isEmpty()) {
            opcion = null;
        }else {
           opcion = results.get(0);
        }

        return opcion;
    }
    /**
     * Devuelve todas las  OpcionServicio de la base de datos.
     *
     * @return una lista con todas las  OpcionServicio que encuentre en la base de
     * datos, "select u from  OpcionServicioEntity u" es como un "select * from
     *  OpcionServicioEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List< OpcionServicioEntity> findAll() {
        LOGGER.info("Consultando todas las  OpcionServicio");
        TypedQuery query = em.createQuery("select u from  OpcionServicioEntity u",  OpcionServicioEntity.class);
        return query.getResultList();
    }
}
    

