/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.TareaEntity;
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
public class TareaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(TareaPersistence.class.getName());

    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Tarea que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TareaEntity create(TareaEntity entity) {
        LOGGER.info("Creando una Tarea nuevo");
        em.persist(entity);
        LOGGER.info("Creando una Tarea nuevo");
        return entity;
    }

  
    public  TareaEntity update( TareaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando  Tarea con id={0}", entity.getId());
        return em.merge(entity);
    }

   
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando  Tarea con id={0}", id);
         TareaEntity entity = em.find( TareaEntity.class, id);
        em.remove(entity);
    }
public TareaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando Tarea con name= ", name);
        TypedQuery<TareaEntity> q
                = em.createQuery("select u from TareaEntity u where u.name = :name", TareaEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }
    
    public  TareaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando  Tarea con id={0}", id);
        return em.find( TareaEntity.class, id);
    }

    /**
     * Devuelve todas las Tarea de la base de datos.
     *
     * @return una lista con todas las Tarea que encuentre en la base de
     * datos, "select u from DefaultEntity u" es como un "select * from
     * DefaultEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List< TareaEntity> findAllByOpcion(Long opcionId) {
        
        LOGGER.log(Level.INFO, "Consultando tarea por id de servicio ", opcionId);
        TypedQuery<TareaEntity> query = em.createQuery("Select y From TareaEntity y where y.opcionServicio.id = :opcionId", TareaEntity.class);
        query = query.setParameter("opcionId", opcionId);
        List<TareaEntity> results = query.getResultList();
       return results;
    }
    
    }
        /*
        LOGGER.info("Consultando todas las  Tareas");
        // Se crea un query para buscar todas las Tarea en la base de datos.
        TypedQuery query = em.createQuery("select u from TareaEntity u ",  TareaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Default.
        return query.getResultList();
*/
   

    


    

