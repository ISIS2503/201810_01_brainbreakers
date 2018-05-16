/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.ejb;

import co.edu.uniandes.csw.boda.entities.PagoEntity;
import co.edu.uniandes.csw.boda.entities.TarjetaCreditoEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.PagoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ca.guerrero
 */
@Stateless
public class PagoLogic {
    private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());

    @Inject
    private PagoPersistence persistence;
    
    @Inject
    private TarjetaCreditoLogic tarjetaLogic;
    
    @Inject
    private ParejaLogic parejaLogic;

    /**
     *
     * @param tarjetaId
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public PagoEntity createPago(Long tarjetaId, PagoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Pago");
        if(tarjetaLogic.getTarjetaCredito(tarjetaId) == null)
        {
            throw new BusinessLogicException("No existe una tarjeta de credito N°: " + entity.getCorreoPareja());
        }
        TarjetaCreditoEntity tarjeta = tarjetaLogic.getTarjetaCredito(tarjetaId);
        entity.setTarjetaCredito(tarjeta);
        if (entity.getMontoTotal() == 0 || entity.getMontoTotal() < 0)
        {
            throw new BusinessLogicException("El monto total del pago debe ser mayor que 0");
        }
        if(parejaLogic.getPareja(entity.getCorreoPareja()) == null)
        {
            throw new BusinessLogicException("No existe una pareja con el correo electronico: " + entity.getCorreoPareja());
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Pago");
        return entity;
    }
    
    /**
     *
     * Actualizar un Pago.
     *
     * @param tarjetaId
     * @param id: id de la Pago para buscarla en la base de datos.
     * @param entity: Pago con los cambios para ser actualizado, por
     * ejemplo el montoTotal.
     * @return el Pago con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     */
    public PagoEntity updatePago(Long tarjetaId, Long id, PagoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar Pago con id={0}", id);
        
        TarjetaCreditoEntity tarjeta = tarjetaLogic.getTarjetaCredito(tarjetaId);
        entity.setTarjetaCredito(tarjeta);
        entity.setOpcionServicio(entity.getOpcionServicio());
        if (entity.getMontoTotal() == 0 || entity.getMontoTotal() < 0)
        {
            throw new BusinessLogicException("El monto total del pago debe ser mayor que 0");
        }
        if (entity.getFecha() == null) {
            throw new BusinessLogicException("Debe ingresar la fecha en la cual se realizo el Pago");
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar Pago con id={0}", entity.getId());
        return persistence.update(entity);
    }
    
     /**
     *
     * Obtener un Pago por medio de su id.
     * 
     * @param id: id del Pago para ser buscado.
     * @return el Pago solicitado por medio de su id.
     */
    public PagoEntity getPago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Pago con id={0}", id);
        PagoEntity pago = persistence.find(id);
        LOGGER.log(Level.INFO, "Termina proceso de consultar Pago con id={0}", id);
        return pago;
    }
    
    /**
     * Obtener todos los Pagos existentes en la base de datos.
     *
     * @param idTarjeta
     * @return una lista de Pagos.
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     */
    public List<PagoEntity> getPagos(Long idTarjeta) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de consultar todos los Pagos");
        TarjetaCreditoEntity tarjeta = tarjetaLogic.getTarjetaCredito(idTarjeta);
        List<PagoEntity> pagos = tarjeta.getPagos();
        if(pagos == null) {
            throw new BusinessLogicException("Aun no se han realizado pagos con la tarjeta de credito");
        }
        if(pagos.isEmpty()) {
            throw new BusinessLogicException("Aun no se han realizado pagos con la tarjeta de credito");
        }
        LOGGER.info("Termina proceso de consultar todos los Pagos");
        return pagos;
    }
}
