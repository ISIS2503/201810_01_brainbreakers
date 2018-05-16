

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.ejb;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.entities.InvitadoEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.InvitadoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author mf.valllejo
 */
@Stateless
public class InvitadoLogic {

    private static final Logger LOGGER = Logger.getLogger(InvitadoLogic.class.getName());

    @Inject
    InvitadoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    @Inject
    BodaLogic bodaLogic;
    
    public InvitadoEntity createInvitado(Long bodaId,InvitadoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de un invitado");
        BodaEntity boda = bodaLogic.findBodaById(bodaId);
        entity.setBoda(boda);
        if (persistence.findByDocumento(entity.getDocumento())!=null){
            throw new BusinessLogicException("Ya existe un Invitado con el documento \"" + entity.getDocumento() + "\"");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de invitado");
        return entity;
    }

    public List<InvitadoEntity> getInvitados(Long bodaId) throws BusinessLogicException {
        BodaEntity boda = bodaLogic.findBodaById(bodaId);
        if (boda.getInvitados() == null) {
            throw new BusinessLogicException("La boda que consulta aún no tiene invitados");
        }
        if (boda.getInvitados().isEmpty()) {
            throw new BusinessLogicException("La boda que consulta aún no tiene invitados");
        }
        return boda.getInvitados();
    }

    public InvitadoEntity get(Long id) {
        return persistence.find(id);
    }

    public InvitadoEntity update(Long bodaId,InvitadoEntity entity) throws BusinessLogicException {
        BodaEntity boda = bodaLogic.findBodaById(bodaId);
        entity.setBoda(boda);
        return persistence.update(entity);
    }

    public void delete(Long id) throws BusinessLogicException {
        if(!persistence.find(id).getAsistencia().equals("Confirmado")){
            throw new BusinessLogicException("El invitado ya esta confirmado no puede ser eliminado");
        }
        persistence.delete(id);
    }

}
