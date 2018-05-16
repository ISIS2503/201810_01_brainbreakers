/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.UbicacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author vn.gomez
 */

 @Stateless
public class UbicacionPersistence {

    private static final Logger LOGGER = Logger.getLogger(UbicacionPersistence.class.getName());

    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;

    public UbicacionEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando ubicaciones con id={0}", id);
        return em.find(UbicacionEntity.class, id);
    }

    public UbicacionEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando ubicaciones con name= ", name);
        TypedQuery<UbicacionEntity> q
                = em.createQuery("select u from UbicacionEntity u where u.name = :name", UbicacionEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<UbicacionEntity> findAll() {
        LOGGER.info("Consultando todas las ubicaciones");
        Query q = em.createQuery("select u from UbicacionEntity u");
        return q.getResultList();
    }

    public UbicacionEntity create(UbicacionEntity entity) {
        LOGGER.info("Creando una ubicacion nueva");
        em.persist(entity);
        LOGGER.info("Ubicacion creada");
        return entity;
    }

    public UbicacionEntity update(UbicacionEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando ubicacion con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando ubicacion con id={0}", id);
        UbicacionEntity entity = em.find(UbicacionEntity.class, id);
        em.remove(entity);
    }   
}   

