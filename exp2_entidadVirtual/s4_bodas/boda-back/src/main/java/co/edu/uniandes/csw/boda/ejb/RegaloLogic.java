/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.ejb;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.entities.RegaloEntity;
import co.edu.uniandes.csw.boda.entities.UbicacionEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.RegaloPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author mf.valllejo
 */
@Stateless
public class RegaloLogic {

    private static final Logger LOGGER = Logger.getLogger(RegaloLogic.class.getName());

    @Inject
    RegaloPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    BodaLogic bodaLogic;
    
    @Inject
    private UbicacionLogic ubicacionLogic;

    public RegaloEntity createRegalo(Long bodaId, RegaloEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de un regalo");
        BodaEntity boda = bodaLogic.findBodaById(bodaId);
        entity.setBoda(boda);
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un regalo con el nombre \"" + entity.getName() + "\"");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de regalo");
        return entity;
    }

    public List<RegaloEntity> getRegalos(Long bodaId) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas los Regalos");
        BodaEntity boda = bodaLogic.findBodaById(bodaId);
        if (boda.getRegalos() == null) {
            throw new BusinessLogicException("La boda que consulta aún no tiene regalos");
        }
        if (boda.getRegalos().isEmpty()) {
            throw new BusinessLogicException("La boda que consulta aún no tiene regalos");
        }
        return boda.getRegalos();
    }

    public RegaloEntity get(Long id) {
        return persistence.find(id);
    }

    public RegaloEntity update(Long bodaId, RegaloEntity entity) throws BusinessLogicException {
        BodaEntity boda = bodaLogic.findBodaById(bodaId);
        entity.setBoda(boda);
        return persistence.update(entity);
    }

    public void delete(Long id) {
        persistence.delete(id);
    }

    public List<UbicacionEntity> listUbicaciones(Long regaloId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", regaloId);
        return get(regaloId).getLocations();
    }

    public UbicacionEntity getUbicaciones(Long regaloId, Long ubicacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un libro con id = {0}", ubicacionId);
        List<UbicacionEntity> list = get(regaloId).getLocations();
        UbicacionEntity ubiEntity = new UbicacionEntity();
        ubiEntity.setId(ubicacionId);
        int index = list.indexOf(ubiEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    public UbicacionEntity addUbi(Long regaloId, Long ubicacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar un libro al author con id = {0}", regaloId);
        RegaloEntity regEntity = get(regaloId);
        UbicacionEntity ubiEntity = new UbicacionEntity();
        ubiEntity.setId(ubicacionId);
        regEntity.getLocations().add(ubiEntity);
        ubicacionLogic.addRegalo(ubicacionId, regaloId);
        return ubicacionLogic.findUbicacionById(ubicacionId);
    }

}
