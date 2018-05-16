/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.CalificacionEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nf.ortiz
 */
@Stateless
public class CalificacionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Comentario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity entity)throws BusinessLogicException{ 
        LOGGER.info("Creando un Comentario nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la Default en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
         em.persist(entity);
        return entity;
    }

    /**
     * Actualiza un comentario.
     * @param entity : el comentario que viene con los nuevos cambios. Por ejemplo
 el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un default con los cambios aplicados.
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     */
    public CalificacionEntity update(CalificacionEntity entity)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Actualizando Calificacion con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Default con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra una Calificacion de la base de datos recibiendo como argumento el id
     * de la Default
     *
     * @param id: id correspondiente a la Default a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Calificacion con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public DefaultEntity find(Long id) para obtener la Default a borrar.
        CalificacionEntity entity = em.find(CalificacionEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from DefaultEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun default con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Default buscada.
     * @return un default.
     */
    public CalificacionEntity find(Long opcionId,Long id) {
       TypedQuery<CalificacionEntity> q = em.createQuery("select x from CalificacionEntity x where (x.opcionServicio.id = :opcionId) and (x.id = :calificacionId)", CalificacionEntity.class);
        q.setParameter("opcionId", opcionId);
        q.setParameter("calificacionId", id);
        List<CalificacionEntity> results = q.getResultList();
        CalificacionEntity calificacion;
        if (results.isEmpty()) {
            calificacion = null;
        }else {
            calificacion = results.get(0);
        }

        return calificacion;
    }

    /**
     * Devuelve todas los Comentarios de la base de datos.
     *
     * @return una lista con todas las comentarios que encuentre en la base de
     * datos, "select u from DefaultEntity u" es como un "select * from
     * DefaultEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<CalificacionEntity> findAllByOpcion(Long opcionId) {
       LOGGER.log(Level.INFO, "Consultando calificacion por id de servicio ", opcionId);
        TypedQuery<CalificacionEntity> query = em.createQuery("Select y From CalificacionEntity y where y.opcionServicio.id = :opcionId", CalificacionEntity.class);
        query = query.setParameter("opcionId", opcionId);
       return query.getResultList();
    }
       public CalificacionEntity findById(Long id) {
       TypedQuery<CalificacionEntity> q = em.createQuery("select z from CalificacionEntity z where z.id = :calificacionId", CalificacionEntity.class);
        q.setParameter("calificacionId", id);
        List<CalificacionEntity> results = q.getResultList();
        CalificacionEntity calificacion;
        if (results == null||results.isEmpty()) {
            calificacion = null;
        }else  {
            calificacion = results.get(0);
        }

        return calificacion;
    }
    
}
