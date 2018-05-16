
package co.edu.uniandes.csw.boda.ejb;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.entities.OpcionServicioEntity;
import co.edu.uniandes.csw.boda.entities.ParejaEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.BodaPersistence;
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
public class BodaLogic {
        private static final Logger LOGGER = Logger.getLogger(BodaLogic.class.getName());
        
        @Inject
        private BodaPersistence persistence;
        
        @Inject
        ParejaLogic parejaLogic;
        
        
        
     /**
     * Crea una boda
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
        public BodaEntity create(String idPareja,BodaEntity entity)throws BusinessLogicException 
     {
          ParejaEntity parejita = parejaLogic.getPareja(idPareja);
         if(parejita == null){
             throw new BusinessLogicException("No existe una Pareja con el id \"" + idPareja+"\"");
         }
         if(entity.getPareja() !=null){
             throw new BusinessLogicException("Ya existe una pareja en esta boda \"" + idPareja+"\"");
         }
         entity.setPareja(parejita);
        LOGGER.log(Level.INFO, "Inicia proceso de crear una boda ");
        
        return persistence.create(entity);
     }
    /**
     * 
     * Obtener todas las bodas existentes en la base de datos.
     *
     * @return una lista de bodas.
     */
    public List<BodaEntity> getBodas() {
        LOGGER.info("Inicia proceso de consultar todas las bodas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<BodaEntity> bodas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las bodas");
        return bodas;
    }
  
     /**
     * Actualiza una boda con el id dado y la informacion
     * @param entity
     * @return BodaEntity
      */
    public BodaEntity updateBoda(BodaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una boda ");
        entity.setOpcionServicios(entity.getOpcionServicios());
        return persistence.update(entity);
    }
      
      public BodaEntity findBodaById(Long id) throws BusinessLogicException{
        if(persistence.find(id)==null) 
        {
            throw new BusinessLogicException("No existe una boda con el id dado.");
        }
        return persistence.find(id);
    }
    
    /**
       * Borra una boda con el id dado
       * @param id
       * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
       */
      public void removeBoda(Long id) throws BusinessLogicException
      {
         LOGGER.info("Inicia proceso de eliminar una Boda");
         if (persistence.find(id)==null) 
         {
             throw new BusinessLogicException("No existe una Boda con el id \"" + id+"\"");
         }
         persistence.delete(id);
         LOGGER.info("Termina proceso de eliminar una boda");  
      }    
      
      public void asginarPareja(String parejaId, Long bodaId) throws BusinessLogicException{
         BodaEntity bodita = persistence.find(bodaId);
          if (bodita==null) 
         {
             throw new BusinessLogicException("No existe una Boda con el id \"" + bodaId+"\"");
         }
         ParejaEntity parejita = parejaLogic.getPareja(parejaId);
         if(parejita == null){
             throw new BusinessLogicException("No existe una Pareja con el id \"" + parejaId+"\"");
         }
         if(bodita.getPareja() !=null){
             throw new BusinessLogicException("Ya existe una pareja en esta boda \"" + bodaId+"\"");
         }
         bodita.setPareja(parejita);
      }
      
      
      
      
      
      
      
       public List<OpcionServicioEntity> listOpcionesServicios(Long bodaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los opciones de una boda con id = {0}", bodaId);
        return findBodaById(bodaId).getOpcionServicios();
    }

    public OpcionServicioEntity getOpcionServicio(Long bodaId, Long opcionServicioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", bodaId);
        List<OpcionServicioEntity> list = findBodaById(bodaId).getOpcionServicios();
        OpcionServicioEntity authorsEntity = new OpcionServicioEntity();
        authorsEntity.setId(opcionServicioId);
        int index = list.indexOf(authorsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Author existente a un Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param authorsId Identificador de la instancia de Author
     * @return Instancia de AuthorEntity que fue asociada a Book
     * 
     */
    public OpcionServicioEntity addOpcionServicio(Long bodaId, Long opcionServicioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un autor al libro con id = {0}", bodaId);
        BodaEntity bookEntity = findBodaById(bodaId);
        OpcionServicioEntity authorsEntity = new OpcionServicioEntity();
        authorsEntity.setId(opcionServicioId);
        bookEntity.getOpcionServicios().add(authorsEntity);
        return getOpcionServicio(bodaId, opcionServicioId);
    }

    /**
     * Remplaza las instancias de Author asociadas a una instancia de Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param list Colección de instancias de AuthorEntity a asociar a instancia
     * de Book
     * @return Nueva colección de AuthorEntity asociada a la instancia de Book
     * 
     */
    public List<OpcionServicioEntity> replaceOpciones(Long bodaId, List<OpcionServicioEntity> list) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un autor del libro con id = {0}", bodaId);
        BodaEntity bookEntity = findBodaById(bodaId);
        bookEntity.setOpcionServicios(list);
        return bookEntity.getOpcionServicios();
    }

    public void removeOpciones(Long bodaId, Long opcionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", bodaId);
        BodaEntity entity = findBodaById(bodaId);
        OpcionServicioEntity authorsEntity = new OpcionServicioEntity();
        authorsEntity.setId(opcionesId);
        entity.getOpcionServicios().remove(authorsEntity);
    }
}
