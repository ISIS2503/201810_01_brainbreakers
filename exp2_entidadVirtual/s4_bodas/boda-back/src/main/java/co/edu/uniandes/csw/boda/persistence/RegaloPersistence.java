


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;


import co.edu.uniandes.csw.boda.entities.RegaloEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author mf.valllejo
 */
@Stateless
public class RegaloPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(RegaloPersistence.class.getName());
    
    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;
    
    public RegaloEntity find(Long id){
        return em.find(RegaloEntity.class, id);
    }
    
    public RegaloEntity create(RegaloEntity entity) {
        em.persist(entity);
        return entity;
    }
    
    public RegaloEntity update(RegaloEntity entity) {
        return em.merge(entity);
    }
    
    public void delete(Long id){
        RegaloEntity entity = em.find(RegaloEntity.class, id);
        em.remove(entity);
    }
    
    public RegaloEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando regalo por nombre ", name);
        TypedQuery<RegaloEntity> query = em.createQuery("Select e From RegaloEntity e where e.name = :name", RegaloEntity.class);
        query = query.setParameter("name", name);
        List<RegaloEntity> results = query.getResultList();
        RegaloEntity regalo = null;
        if (results.isEmpty()) {
            regalo = null;
        } else{
            regalo = results.get(0);
        }

        return regalo;
    }
}