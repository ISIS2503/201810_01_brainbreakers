
package co.edu.uniandes.csw.boda.ejb;
import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.entities.OpcionServicioEntity;
import co.edu.uniandes.csw.boda.entities.ProveedorEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.OpcionServicioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author sp.joven
 */

@Stateless
public class OpcionServicioLogic {
    private static final Logger LOGGER = Logger.getLogger(OpcionServicioLogic.class.getName());
    
     @Inject
      private OpcionServicioPersistence persistence;
    
      @Inject
      ProveedorLogic proveedorLogic;
       @Inject
      BodaLogic bodaLogic;
      /**
     * Crea una opcionServicio
     * @param entity
     * @param proveedorId
     * @return OpcionServicioEntity
     * @throws BusinessLogicException
     */
     public OpcionServicioEntity create(Long proveedorId,OpcionServicioEntity entity)throws BusinessLogicException 
     {
        LOGGER.info("Inicia proceso de creación de una opcion de servicio");
        ProveedorEntity proveedor = proveedorLogic.findProveedorById(proveedorId);
        entity.setProveedor(proveedor);
        if(entity.getCosto()<0)
          {
               throw new BusinessLogicException("El costo no puede ser negativo.");
          }
        
        //Si no existe ninguna opcion de servicio con el id.
        persistence.create(entity);
         return entity;
     }
    
    /**
     * Consulta  laa opcionesServicio
     * @param proveedorId
     * @return Lista de OpcionServicioEntity
     * @throws BusinessLogicException
     */
     public List<OpcionServicioEntity> getOpcionesServicio(Long proveedorId) throws BusinessLogicException{
         LOGGER.info("Inicia proceso de consultar todas las opciones de servicio");
           ProveedorEntity proveedor= proveedorLogic.findProveedorById(proveedorId);
            if (proveedor.getOpcionesServicio() == null) {
            throw new BusinessLogicException("El proveedor que consulta aún no tiene opciones de servicio");
        }
        if (proveedor.getOpcionesServicio().isEmpty()) {
            throw new BusinessLogicException("El proveedor que consulta aún no tiene opciones de servicio");
        }

        return proveedor.getOpcionesServicio();
    }
     /**
     * Consulta una opcionServicio con el id dado
     * @param id
     * @return OpcionServicioEntity
     * @throws BusinessLogicException
     */
      public OpcionServicioEntity getOpcionServicioByProveedor(Long proveedorId,Long id)throws BusinessLogicException{          
            LOGGER.info("Inicia proceso de buscar la tarea por Id");
            OpcionServicioEntity buscado = persistence.findByProveedor(proveedorId,id);
            if(buscado == null )throw new BusinessLogicException("No existe una opcionServicio con el id \"" + id +"\"");
            LOGGER.info("Termina proceso de buscar la opcionServicio por Id");
            return buscado;
        }
         public OpcionServicioEntity getOpcionServicio(Long id)throws BusinessLogicException{          
            LOGGER.info("Inicia proceso de buscar la tarea por Id");
            OpcionServicioEntity buscado = persistence.find(id);
            if(buscado == null )throw new BusinessLogicException("No existe una opcionServicio con el id \"" + id +"\"");
            LOGGER.info("Termina proceso de buscar la opcionServicio por Id");
            return buscado;
        }
       /**
      *Actualiza una opcionServicio con el id Dado y la informacion
      * @param proveedorId
      * @param entity
      * @return OpcionServicioEntity
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
      * @throws BussinesLogicException si no se encuentra una opcionServicio con el id dado
      */
      public OpcionServicioEntity updateOpcionServicio(Long proveedorId,Long id,OpcionServicioEntity entity)throws BusinessLogicException
      {       
        ProveedorEntity proveedor = proveedorLogic.findProveedorById(proveedorId);
        entity.setProveedor(proveedor);
         if(entity.getCosto()<0)
          {
               throw new BusinessLogicException("El costo no puede ser negativo.");
          }     
          if(persistence.findByProveedor(proveedorId,id)==null) 
          {
              throw new BusinessLogicException("No existe una opcion servicio con el id dado hola.");
          }
          entity.setPago(entity.getPago());
          entity.setBodas(entity.getBodas());
          persistence.update(entity);
          return entity;
      }
    
         
      /**
     * Elimina una opcionServicio con el id dado
     * @param id
     * @throws BusinessLogicException
     */
      public void removeOpcionServicio(Long proveedorId,Long id) throws BusinessLogicException
      {
         LOGGER.info("Inicia proceso de eliminar opcion servicio");
        if (persistence.findByProveedor( proveedorId,id)==null) 
        {
            throw new BusinessLogicException("No existe una opcion servicio con el id \"" + id+"\"");
        }
        persistence.delete(id);
          LOGGER.info("Termina proceso de eliminar una opcion servicio");  
      }
      
       /**
       * Encuentra una opcionServicio con el id Dado
       * @param id
       * @throws BusinessLogicException si no existe la opcionServicio con el id dado
       * @return OpcionServicioEntity
       */
 public OpcionServicioEntity findOpcionServicioByIdProveedor(Long proveedorId,Long id) throws BusinessLogicException{
        if(persistence.findByProveedor( proveedorId,id)==null)
        {
            throw new BusinessLogicException("No existe una opcion servicio con el id dado.");
        }
        return persistence.findByProveedor( proveedorId,id);
    }

 public OpcionServicioEntity findOpcionServicioById(Long id) throws BusinessLogicException{
        if(persistence.find( id)==null)
        {
            throw new BusinessLogicException("No existe una opcion servicio con el id dado.");
        }
        return persistence.find(id);
    }

 
 
 
 
 
 
 public List<BodaEntity> listBodas(Long opcionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", opcionId);
        return getOpcionServicio(opcionId).getBodas();
    }

    /**
     * Obtiene una instancia de BookEntity asociada a una instancia de Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @return
     * @generated
     */
    public BodaEntity getBoda(Long opcionId, Long bodaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un libro con id = {0}", bodaId);
        List<BodaEntity> list = getOpcionServicio(opcionId).getBodas();
        BodaEntity booksEntity = new BodaEntity();
        booksEntity.setId(bodaId);
        int index = list.indexOf(booksEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Book existente a un Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @return Instancia de BookEntity que fue asociada a Author
     * @generated
     */
    public BodaEntity addBoda(Long opcionId, Long bodaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar un libro al author con id = {0}", opcionId);
        bodaLogic.addOpcionServicio(bodaId, opcionId);
        return bodaLogic.findBodaById(bodaId);
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param list Colección de instancias de BookEntity a asociar a instancia
     * de Author
     * @return Nueva colección de BookEntity asociada a la instancia de Author
     * @generated
     */
    public List<BodaEntity> replaceBodas(Long bodaId, List<BodaEntity> list) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al author con id = {0}", bodaId);
        OpcionServicioEntity authorEntity = getOpcionServicio(bodaId);
        List<BodaEntity> bookList = bodaLogic.getBodas();
        for (BodaEntity book : bookList) {
            if (list.contains(book)) {
                if (!book.getOpcionServicios().contains(authorEntity)) {
                    bodaLogic.addOpcionServicio(book.getId(), bodaId);
                }
            } else {
                bodaLogic.removeOpciones(book.getId(), bodaId);
            }
        }
        authorEntity.setBodas(list);
        return authorEntity.getBodas();
    }

    /**
     * Desasocia un Book existente de un Author existente
     *
     * @param authorId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @generated
     */
    public void removeBoda(Long opcionId, Long bodasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un boda del opcion con id = {0}", opcionId);
        bodaLogic.removeOpciones(bodasId, opcionId);
    }
}
