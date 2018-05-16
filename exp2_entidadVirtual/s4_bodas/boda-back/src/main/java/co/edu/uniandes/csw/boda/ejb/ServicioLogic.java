/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.ejb;

import co.edu.uniandes.csw.boda.entities.ProveedorEntity;
import co.edu.uniandes.csw.boda.entities.ServicioEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.ServicioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author aj.ortiz10
 */
@Stateless
public class ServicioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ServicioLogic.class.getName());

    @Inject
    private ServicioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ServicioEntity createServicio(ServicioEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación del servicio");
        // Invoca la persistencia para crear el servicio
        if(persistence.findByName(entity.getName())!=null)
            throw new BusinessLogicException("No pueden existir dos servicios iguales");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del servicio");
        return entity;

    }

    /**
     * 
     * Obtener todas los servicios existentes en la base de datos.
     *
     * @return una lista de servicios.
     */
    public List<ServicioEntity> getServicios() {
        LOGGER.info("Inicia proceso de consultar todos los servicios");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ServicioEntity> Servicios = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los servicios");
        return Servicios;
    }
    
    
    public ServicioEntity getServicio(Long id){
        LOGGER.info("Inicia proceso de buscar el servicio por Id");
            LOGGER.info("Termina proceso de buscar el sevicio por Id");
        return persistence.find(id);
    }

    public ServicioEntity update(Long id,ServicioEntity entity)throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de actualizar el servicio");
        
        //Verifica que exista una pareja con el id dado
        if(persistence.find(id)==null) throw new BusinessLogicException("No existe un servicio con el id dado.");
          
        return persistence.update(entity);
    }

    public void deleteServicio(Long id)throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de eliminar un Servicio");
        if (persistence.find(id)==null) throw new BusinessLogicException("No existe un servicio con el id \"" + id+"\"");
        persistence.delete(id);
        LOGGER.info("Termina proceso de eliminar un Servicio"); 
    }
    public List<ProveedorEntity> listProveedores(Long servicioId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los proveedores del servicio con id = {0}", servicioId);
        return getServicio(servicioId).getProveedores();
    }
    /**
     * Obtiene una instancia de AuthorEntity asociada a una instancia de Book
     *
     * @param servicioId Identificador de la instancia de Book
     * @param proveedoresId Identificador de la instancia de Author
     * 
     */
    public ProveedorEntity getProveedor(Long servicioId, Long proveedoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", servicioId);
        List<ProveedorEntity> list = getServicio(servicioId).getProveedores();
        ServicioEntity authorsEntity = new ServicioEntity();
        authorsEntity.setId(proveedoresId);
        int index = list.indexOf(authorsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }
    /**
     * Asocia un Proveedor existente a un Servicio
     *
     * @param servicioId Identificador de la instancia de Book
     * @param proveedoresId Identificador de la instancia de Author
     * @return Instancia de AuthorEntity que fue asociada a Book
     * 
     */
    public ProveedorEntity addProveedor(Long servicioId, Long proveedoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un proveedor al servicio con id = {0}", servicioId);
        ServicioEntity servicioEntity = getServicio(servicioId);
        ProveedorEntity proveedoresEntity = new ProveedorEntity();
        proveedoresEntity.setId(proveedoresId);
        servicioEntity.getProveedores().add(proveedoresEntity);
        return getProveedor(servicioId, proveedoresId);
    }
    /**
     * Remplaza las instancias de Proveedor asociadas a una instancia de Servicio
     *
     * @param servicioId Identificador de la instancia de Book
     * @param list Colección de instancias de AuthorEntity a asociar a instancia
     * de Book
     * @return Nueva colección de ProveedorEntity asociada a la instancia de Servicio
     * 
     */
    public List<ProveedorEntity> replaceProveedores(Long servicioId, List<ProveedorEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un autor del libro con id = {0}", servicioId);
        ServicioEntity servEntity = getServicio(servicioId);
        servEntity.setProveedores(list);
        return servEntity.getProveedores();
    }
    /**
     * Desasocia un Proveedor existente de un Servicio existente
     *
     * @param serviciosId Identificador de la instancia de Book
     * @param proveedoresId Identificador de la instancia de Author
     * 
     */
    public void removeProveedor(Long serviciosId, Long proveedoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un proveedor del servicio con id = {0}", serviciosId);
        ServicioEntity entity = getServicio(serviciosId);
        ProveedorEntity proveedoresEntity = new ProveedorEntity();
        proveedoresEntity.setId(proveedoresId);
        entity.getProveedores().remove(proveedoresEntity);
    }
}
