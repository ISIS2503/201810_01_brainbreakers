/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.ParejaEntity;
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
public class ParejaPersistence {
     private static final Logger LOGGER = Logger.getLogger(ParejaPersistence.class.getName());
     
    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;
    
    public ParejaEntity create(ParejaEntity enty)throws BusinessLogicException{
        LOGGER.info("Creando una Pareja nuevo");
        if(find(enty.getCorreoElec())!=null)throw new BusinessLogicException("Ya existe una pareja con un correo igual.");

        em.persist(enty);
        LOGGER.info("Termina creacion una Pareja nuevo");
        return enty;         
    }
    
    public ParejaEntity update(ParejaEntity entity)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Actualizando Pareja con correoElec=''", entity.getCorreoElec());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Default con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
            if(find(entity.getCorreoElec())==null)throw new BusinessLogicException("No existe");
        return em.merge(entity);
    }
    
     public void delete(String id) {
        LOGGER.log(Level.INFO, "Borrando Pareja con correoElec={0}", id);
        // Se hace uso de mismo método que esta explicado en public DefaultEntity find(Long id) para obtener la Default a borrar.
        ParejaEntity entity = em.find(ParejaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from DefaultEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }
    
         public ParejaEntity find(String id) {
        LOGGER.log(Level.INFO, "Consultando Pareja con correoElec={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from DefaultEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(ParejaEntity.class, id);
    }

    /**
     * Devuelve todas las Default de la base de datos.
     *
     * @return una lista con todas las Default que encuentre en la base de
     * datos, "select u from DefaultEntity u" es como un "select * from
     * DefaultEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<ParejaEntity> findAll() {
        LOGGER.info("Consultando todas las Parejas");
        // Se crea un query para buscar todas las Default en la base de datos.
        TypedQuery query = em.createQuery("select u from ParejaEntity u", ParejaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Default.
        return query.getResultList();
    }
}
