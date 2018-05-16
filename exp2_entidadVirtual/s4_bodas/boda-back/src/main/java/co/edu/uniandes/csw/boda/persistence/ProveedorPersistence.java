/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.ProveedorEntity;
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
public class ProveedorPersistence {
    
    
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorPersistence.class.getName());

    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto boda que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ProveedorEntity create(ProveedorEntity entity) throws BusinessLogicException {
        LOGGER.info("Creando un proveedor nuevo");
        if(findByName(entity.getName())!=null)
            throw new BusinessLogicException("Ya existe un proveedor con el mismo nombre.");
        em.persist(entity);
        LOGGER.info("Se creó un proveedor nuevo");
        return entity;
    }

    /**
     * Busca si hay alguna boda con el nombre que se envía de argumento
     *
     * @param name: Nombre de la boda que se está buscando
     * @return null si no existe ninguna boda con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public ProveedorEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando proveedor por nombre ", name);

        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ProveedorEntity e where e.name = :name", ProveedorEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<ProveedorEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    public List<ProveedorEntity> findAll() {
        LOGGER.info("Consultando todos los proveedores");
        TypedQuery query = em.createQuery("select u from ProveedorEntity u", ProveedorEntity.class);
        return query.getResultList();
    }
    
    public ProveedorEntity find (Long id)
    {
         ProveedorEntity proveedor = em.find(ProveedorEntity.class, id);
         return proveedor;
    }
    
    public ProveedorEntity update(ProveedorEntity entity) { 
        return em.merge(entity);
    }
    
  
    public void delete(ProveedorEntity entity) {
        LOGGER.log(Level.INFO, "Borrando proveedor con id={0}", entity.getId());
         ProveedorEntity entity2 = em.find(ProveedorEntity.class, entity.getId());
         if(entity.getOpcionesServicio()!=null){
         LOGGER.log(Level.INFO, "Numero de opciones ={0}", entity.getOpcionesServicio().size());}
         else{
             LOGGER.log(Level.INFO, "No retorna nada de opciones");
         }
    em.remove(entity2);
}

}
