/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.ServicioEntity;
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
 * @author aj.ortiz10
 */
@Stateless
public class ServicioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ServicioPersistence.class.getName());

    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto city que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ServicioEntity create(ServicioEntity entity) throws BusinessLogicException{
        LOGGER.info("Creando un servicio nuevo");
        if(findByName(entity.getName())!=null)
            throw new BusinessLogicException("Ya existe un servicio con el mismo nombre.");
        em.persist(entity);
        LOGGER.info("Se creó un servicio nuevo");
        return entity;
    }

    /**
     * Busca si hay alguna boda con el nombre que se envía de argumento
     *
     * @param name: Nombre de la boda que se está buscando
     * @return null si no existe ninguna city con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public ServicioEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando Servicio por nombre ", name);

        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ServicioEntity e where e.name = :name", ServicioEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<ServicioEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    public List<ServicioEntity> findAll() {
        LOGGER.info("Consultando todos los servicios");
        TypedQuery query = em.createQuery("select u from ServicioEntity u", ServicioEntity.class);
        return query.getResultList();
    }
    
    public ServicioEntity find (Long id)
    {
         return em.find(ServicioEntity.class, id);
    }
    
    public ServicioEntity update(ServicioEntity entity) { 
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        ServicioEntity entity = em.find(ServicioEntity.class, id);
    LOGGER.log(Level.INFO, "Borrando servicio con id={0}", entity.getId());
    em.remove(entity);
}

}
