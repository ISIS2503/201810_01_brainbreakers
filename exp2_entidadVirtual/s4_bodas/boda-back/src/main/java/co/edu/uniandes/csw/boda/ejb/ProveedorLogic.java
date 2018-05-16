/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.ejb;

import co.edu.uniandes.csw.boda.entities.ProveedorEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.ProveedorPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author aj.ortiz10
 */
@Stateless
public class ProveedorLogic {

    private static final Logger LOGGER = Logger.getLogger(ProveedorLogic.class.getName());

    @Inject
    private ProveedorPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ProveedorEntity createProveedor(ProveedorEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Proveedor");
        // Invoca la persistencia para crear la Default
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("No pueden existir dos proveedores iguales");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Proveedor");
        return entity;
    }

    /**
     *
     * Obtener todas las Defaultes existentes en la base de datos.
     *
     * @return una lista de Defaultes.
     */
    public List<ProveedorEntity> getProveedor() {
        LOGGER.info("Inicia proceso de consultar todos los Proveedores");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ProveedorEntity> Default = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los Proveedores");
        return Default;
    }

    public ProveedorEntity getProveedor(Long id) {
        return persistence.find(id);
    }

    public ProveedorEntity update(ProveedorEntity entity) {
        return persistence.update(entity);
    }

    public void deleteProveedor(ProveedorEntity entity) {
        persistence.delete(entity);
    }

    public ProveedorEntity findProveedorById(Long id) throws BusinessLogicException {
        if (persistence.find(id) == null) {
            throw new BusinessLogicException("No existe una proveedor con el id dado.");
        }
        return persistence.find(id);
    }
}
