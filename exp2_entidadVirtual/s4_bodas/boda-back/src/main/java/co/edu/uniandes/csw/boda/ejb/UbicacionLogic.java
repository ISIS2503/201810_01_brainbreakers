/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.ejb;

import co.edu.uniandes.csw.boda.entities.RegaloEntity;
import co.edu.uniandes.csw.boda.entities.UbicacionEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.UbicacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vn.gomez
 */
@Stateless
public class UbicacionLogic {

            private static final Logger LOGGER = Logger.getLogger(UbicacionLogic.class.getName());
        
        @Inject
        private UbicacionPersistence persistence;
        
     /**
     * Crea una ubicacion
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
        public UbicacionEntity create(UbicacionEntity entity)throws BusinessLogicException 
     {
        LOGGER.info("Inicia proceso de creación de una ubicacion!");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de una ubicacion");
         return entity;
     }
    /**
     * 
     * Obtener todas las ubicaciones existentes en la base de datos.
     *
     * @return una lista de ubicaciones.
     */
    public List<UbicacionEntity> getUbicaciones() {
        LOGGER.info("Inicia proceso de consultar todas las ubicaciones");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<UbicacionEntity> ubicaciones = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las ubicaciones");
        return ubicaciones;
    }
  
     /**
      *Actualiza una ubicacion con el id dado y la informacion
     * @param id
     * @param entity
     * @return UbicacionEntity
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     * si no se encuentra una ubicacion con el id dado arroja exception
      */
      public UbicacionEntity updateUbicacion(Long id, UbicacionEntity entity)throws BusinessLogicException
      {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una ubicacion ");
        return persistence.update(entity);
      }
      
      public UbicacionEntity findUbicacionById(Long id) throws BusinessLogicException{
        if(persistence.find(id)==null)
        {
            throw new BusinessLogicException("Lo sentimos, no existe una ubicacion con el id dado.");
        }
        return persistence.find(id);
    }
    
    /**
       * Borra una ubicacion con el id dado
       * @param id
       * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
       */
      public void removeUbicacion(Long id) throws BusinessLogicException
      {
         LOGGER.info("Inicia proceso de eliminar una Ubicacion");
         if (persistence.find(id)==null) 
         {
             throw new BusinessLogicException("No existe una Ubicacion con el id \"" + id+"\"");
         }
         persistence.delete(id);
         LOGGER.info("Termina proceso de eliminar una Ubicacion");  
      }
      
     public RegaloEntity getRegalo(Long ubicacionId, Long regaloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", ubicacionId);
        List<RegaloEntity> list = findUbicacionById(ubicacionId).getRegalos();
        RegaloEntity regEntity = new RegaloEntity();
        regEntity.setId(regaloId);
        int index = list.indexOf(regEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    public RegaloEntity addRegalo(Long ubicacionId, Long regaloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un regalo a la ubicacion con id = {0}", ubicacionId);
        UbicacionEntity ubiEntity = findUbicacionById(ubicacionId);
        RegaloEntity regaloEntity = new RegaloEntity();
        regaloEntity.setId(regaloId);
        ubiEntity.getRegalos().add(regaloEntity);
        return getRegalo(ubicacionId, regaloId);
    }
}
