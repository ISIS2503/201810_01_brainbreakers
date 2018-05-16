/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.ejb;
import co.edu.uniandes.csw.boda.entities.ParejaEntity;
import co.edu.uniandes.csw.boda.entities.TarjetaCreditoEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.TarjetaCreditoPersistence;
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
public class TarjetaCreditoLogic {
    private static final int NUMERO_CARACTERES_SEGURIDAD_1OPCION = 3;
    private static final int NUMERO_CARACTERES_SEGURIDAD_2OPCION = 4;
    private static final int NUMERO_CARACTERES_TARJETA = 16;
    private static final Logger LOGGER = Logger.getLogger(TarjetaCreditoLogic.class.getName());

    @Inject
    private TarjetaCreditoPersistence persistence;
    
    @Inject
    ParejaLogic parejaLogic;
    
     /**
     *
     * @param parejaId
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public TarjetaCreditoEntity createTarjetaCredito(String parejaId, TarjetaCreditoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de TarjetaCredito");
        ParejaEntity pareja = parejaLogic.getPareja(parejaId);
        entity.setPareja(pareja);
        if (persistence.findByNumDeSeg(entity.getNumDeSeg())!= null) {
            throw new BusinessLogicException("Ya existe una TarjetaCredito con el numDeSeg \"" + entity.getNumDeSeg() + "\"");
        }
        if (persistence.findByNumero(entity.getNumero())!= null) {
            throw new BusinessLogicException("Ya existe una TarjetaCredito con el numero \"" + entity.getNumero() + "\"");
        }
        int ingresoNumero = String.valueOf(entity.getNumero()).length();
        if(ingresoNumero != NUMERO_CARACTERES_TARJETA)
        {
            throw new BusinessLogicException("El numero de la Tarjeta de credito debe tener 16 digitos");
        }
        int ingresoNumeroSeguridad = String.valueOf(entity.getNumDeSeg()).length();
        if (ingresoNumeroSeguridad != NUMERO_CARACTERES_SEGURIDAD_1OPCION && ingresoNumeroSeguridad != NUMERO_CARACTERES_SEGURIDAD_2OPCION) {
            throw new BusinessLogicException("El numero de seguridad de la Tarjeta de credito debe tener 3 o 4 digitos");
        }
        if (entity.getFechaVen() == null) {
            throw new BusinessLogicException("Debe ingresar la fecha en la cual vence la Tarjeta de credito");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de TarjetaCredito");
        return entity;
    }
    
    /**
     *
     * Actualizar una TarjetaCredito.
     *
     * @param parejaId
     * @param id: id de la TarjetaCredito para buscarla en la base de datos.
     * @param entity: TarjetaCredito con los cambios para ser actualizada, por
     * ejemplo el numero.
     * @return la TarjetaCredito con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     */
    public TarjetaCreditoEntity updateTarjetaCredito(String parejaId, Long id, TarjetaCreditoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar TarjetaCredito con id={0}", id);
        ParejaEntity pareja = parejaLogic.getPareja(parejaId);
        entity.setPareja(pareja);
        if (persistence.findByNumDeSeg(entity.getNumDeSeg()) != null && entity.getNumDeSeg() != persistence.find(id).getNumDeSeg()) {
            throw new BusinessLogicException("Ya existe una TarjetaCredito con el numDeSeg \"" + entity.getNumDeSeg() + "\"");
        }
        if (persistence.findByNumero(entity.getNumero()) != null && !(entity.getNumero().equals(persistence.find(id).getNumero()))) {
            throw new BusinessLogicException("Ya existe una TarjetaCredito con el numero \"" + entity.getNumero() + "\"");
        }
        int ingresoNumeroSeguridad = String.valueOf(entity.getNumDeSeg()).length();
        if (ingresoNumeroSeguridad != NUMERO_CARACTERES_SEGURIDAD_1OPCION && ingresoNumeroSeguridad != NUMERO_CARACTERES_SEGURIDAD_2OPCION) {
            throw new BusinessLogicException("El numero de seguridad de la Tarjeta de credito debe tener 3 o 4 digitos");
        }
        if (entity.getFechaVen() == null) {
            throw new BusinessLogicException("Debe ingresar la fecha en la cual vence la Tarjeta de credito");
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar TarjetaCredito con id={0}", entity.getNumero());
        return persistence.update(entity);
    }
    
     /**
     * Borrar una TarjetaCredito
     * @param idPareja
     * @param id: id de la TarjetaCredito a borrar
     */
    public void deleteTarjetaCredito(String idPareja, Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar TarjetaCredito con id={0}", id);
        TarjetaCreditoEntity tarjeta = persistence.find(id);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "La TarjetaCredito con el id {0} no existe", id);
        }
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar TarjetaCredito con id={0}", id);
    }
    
    /**
     *
     * Obtener una TarjetaCredito por medio de su id.
     * 
     * @param id: id de la TarjetaCredito para ser buscada.
     * @return la TarjetaCredito solicitada por medio de su id.
     */
    public TarjetaCreditoEntity getTarjetaCredito(Long id) {
        return persistence.find(id);
    }
    
    /**
     *
     * Obtener una TarjetaCredito por medio de su numDeSeg.
     * 
     * @param numDeSeg: numero de seguridad de la TarjetaCredito para ser buscada.
     * @return la TarjetaCredito solicitada por medio de su numDeSeg.
     */
    public TarjetaCreditoEntity getTarjetaCreditoByNumDeSeg(int numDeSeg) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar TarjetaCredito con numDeSeg={0}", numDeSeg);
        TarjetaCreditoEntity tarjeta = persistence.findByNumDeSeg(numDeSeg);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "La TarjetaCredito con el numDeSeg {0} no existe", numDeSeg);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar TarjetaCredito con numDeSeg={0}", numDeSeg);
        return tarjeta;
    }
    
    /**
     *
     * Obtener una TarjetaCredito por medio de su numero.
     * 
     * @param numero: numero de la TarjetaCredito para ser buscada.
     * @return la TarjetaCredito solicitada por medio de su numero.
     */
    public TarjetaCreditoEntity getTarjetaCreditoByNumero(Long numero) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar TarjetaCredito con numero={0}", numero);
        TarjetaCreditoEntity tarjeta = persistence.findByNumero(numero);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "La TarjetaCredito con el numero {0} no existe", numero);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar TarjetaCredito con numero={0}", numero);
        return tarjeta;
    }
    
    /**
     * 
     * Obtener todas las Tarjetas de Credito existentes en la base de datos.
     *
     * @param idPareja
     * @return una lista de Tarjetas de Credito.
     * @throws co.edu.uniandes.csw.boda.exceptions.BusinessLogicException
     */
    public List<TarjetaCreditoEntity> getTarjetasCredito(String idPareja) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las Tarjetas de Credito");
        ParejaEntity pareja = parejaLogic.getPareja(idPareja);
        if (pareja.getTarjetasCredito() == null) {
            throw new BusinessLogicException("La pareja con ese email aun no tiene tarjetas de credito");
        }
        if (pareja.getTarjetasCredito().isEmpty()) {
            throw new BusinessLogicException("La pareja con ese email aun no tiene tarjetas de credito");
        }
        LOGGER.info("Termina proceso de consultar todas las Tarjetas de Credito");
        return pareja.getTarjetasCredito();
    }
}