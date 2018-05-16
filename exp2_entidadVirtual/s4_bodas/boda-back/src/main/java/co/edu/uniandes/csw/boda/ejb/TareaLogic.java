
package co.edu.uniandes.csw.boda.ejb;

import co.edu.uniandes.csw.boda.entities.OpcionServicioEntity;
import co.edu.uniandes.csw.boda.entities.TareaEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.TareaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author sp.joven
 */
@Stateless
public class TareaLogic {

    private static final Logger LOGGER = Logger.getLogger(TareaLogic.class.getName());

    @Inject
    private TareaPersistence persistence;

    @Inject
    OpcionServicioLogic opcionLogic;
    
     /**
     *Crea  una tarea con la informacion
     * @param opcionId
     * @param entity
     * @return TareaEntity
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     *
     */
    public TareaEntity create(Long opcionId, TareaEntity entity) throws BusinessLogicException {

        OpcionServicioEntity opcion = opcionLogic.findOpcionServicioById(opcionId);
        entity.setOpcionServicio(opcion);
        persistence.create(entity);
        return entity;
    }

    /**
     * Consulta de todas las tareas 
     * @param opcionId
     * @return TareaEntity
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     */
    public List<TareaEntity> getTareas(Long opcionId) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las tareas");
        OpcionServicioEntity opcion = opcionLogic.findOpcionServicioById(opcionId);
             
        if (opcion.getTareas() == null) {
            throw new BusinessLogicException("La opcion que consulta aún no tiene tareas");
        }
        if (opcion.getTareas().isEmpty()) {
            throw new BusinessLogicException("La opcion que consulta aún no tiene tareas");
        }
        List<TareaEntity> lista=persistence.findAllByOpcion(opcionId);
        System.out.println("Tareas em tareaLogiccccc:   "+lista.size() );
        return lista;
    }

      /**
     * Consulta una tarea con el id dado
     * @param id
     * @return TareaEntity
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     */
    public TareaEntity getTarea(Long id) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de buscar la tarea por Id");
        TareaEntity buscado = persistence.find(id);
        if (buscado == null) {
            throw new BusinessLogicException("No existe una tarea con el id \"" + id + "\"");
        }
        LOGGER.info("Termina proceso de buscar la tarea por Id");
        return buscado;
    }

    /**
     * Actualiza una tarea con el id dado y la informacion
     * @param opcionId
     * @param entity
     * @return TareaEntity
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     */
public TareaEntity updateTarea(Long opcionId, TareaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar tarea");
        OpcionServicioEntity opcion = opcionLogic.findOpcionServicioById(opcionId);
        entity.setOpcionServicio(opcion);
//        if(opcion.getBoda().getFecha().before(entity.getDia()))
//        {
//            throw new BusinessLogicException("La fecha de la tarea no puede ser posterior a la de la boda"); 
//        }
        return persistence.update(entity);
    }


    /**
     * Borra una tarea con el id Dado
     * @param id
     * @throws BusinessLogicException si no existe la tarea con el id dado
     */
    public void removeTarea(Long id) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de eliminar tarea");
        if (persistence.find(id) == null) {
            throw new BusinessLogicException("No existe una Tarea con el id \"" + id + "\"");
        }
        persistence.delete(id);
        LOGGER.info("Termina proceso de eliminar una tarea");
    }

    /**
     * Encuentra una tarea con el id Dado
     * @param id
     * @throws BusinessLogicException si no existe la opcionServicio con el id
     * dado
     * @return TareaEntity
     */
    public TareaEntity findTareaById(Long id) throws BusinessLogicException {
        if (persistence.find(id) == null) {
            throw new BusinessLogicException("No existe una tarea con el id dado.");
        }
        return persistence.find(id);
    }
    
}
