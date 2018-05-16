/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.ejb;

import co.edu.uniandes.csw.boda.entities.CalificacionEntity;
import co.edu.uniandes.csw.boda.entities.OpcionServicioEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.CalificacionPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author nf.ortiz
 */
@Stateless
public class CalificacionLogic {
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
     @Inject
    private CalificacionPersistence persistence;
     
     @Inject 
     private OpcionServicioLogic opcionLogic;
     
    /**
     * Retorna todas las calificaciones
     */
     public CalificacionEntity create(Long opcionId, CalificacionEntity entity)throws BusinessLogicException 
     {
        LOGGER.info("Inicia proceso de creación de una calificacion");
        //Verifica que no esten dos calificacion con el mismo id
         OpcionServicioEntity m = opcionLogic.getOpcionServicio(opcionId);
         entity.setOpcionServicio(m);
         if (persistence.findById(entity.getId())!=null){
            throw new WebApplicationException("Ya existe un Invitado con el documento \"" + entity.getId()+ "\"",404);
        }
        //Si no existe ninguna pareja con el correo.
        persistence.create(entity);
         return entity;
     }
     
     /**
      * Retorna la lista de calificaciones
      */
     public List<CalificacionEntity> getCalificaciones(Long opcionId){
        LOGGER.info("Inicia proceso de consultar todas las calificaciones");
        return persistence.findAllByOpcion(opcionId);
     }
     
     /**
      * Retorna la calificacion con el id dado
      */
     public CalificacionEntity getCalificacion(Long opcionId,Long id){
         LOGGER.info("Inicia proceso de consultar  la calificacion con el id dado.");
         return persistence.find(opcionId,id);
     }
      /**
      * Actualiza la calificacion con el id dado
      */
     public CalificacionEntity updateCalificacion(Long opcionId,Long id, CalificacionEntity entity)throws BusinessLogicException{
        LOGGER.info("Inicia proceso de actualizar  la calificacion con el id dado.");
        OpcionServicioEntity m = opcionLogic.getOpcionServicio(opcionId);
        entity.setOpcionServicio(m);
        persistence.update(entity);
         return entity;
     }
     
     /**
      * Borra la calificacion con el id dado
      */
    public void deleteCalificacion(Long id)throws BusinessLogicException{
        LOGGER.info("Inicia proceso de borrar  la calificacion con el id dado.");
        persistence.delete(id);
    }
}
