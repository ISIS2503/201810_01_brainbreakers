/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
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
public class BodaPersistence {

    private static final Logger LOGGER = Logger.getLogger(BodaPersistence.class.getName());

    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;

    public BodaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando bodas con id={0}", id);
        return em.find(BodaEntity.class, id);
    }

    public BodaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando bodas con name= ", name);
        TypedQuery<BodaEntity> q
                = em.createQuery("select u from BodaEntity u where u.name = :name", BodaEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<BodaEntity> findAll() {
        LOGGER.info("Consultando todas las bodas");
        Query q = em.createQuery("select u from BodaEntity u");
        return q.getResultList();
    }

    public BodaEntity create(BodaEntity entity) {
        LOGGER.info("Creando una boda nueva");
        LOGGER.info(entity.getId()+" ...---+++");
        em.persist(entity);
        LOGGER.info("Boda creada");
        return entity;
    }

    public BodaEntity update(BodaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando boda con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando boda con id={0}", id);
        BodaEntity entity = em.find(BodaEntity.class, id);
        em.remove(entity);
    }   
}
