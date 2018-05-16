/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.TarjetaCreditoDetailDTO;
import co.edu.uniandes.csw.boda.ejb.TarjetaCreditoLogic;
import co.edu.uniandes.csw.boda.entities.TarjetaCreditoEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ca.guerrero
 */
@Produces("application/json")
@Consumes("application/json")
public class TarjetaCreditoResource {
    @Inject
    TarjetaCreditoLogic tarjetaCreditoLogic;
    
    /**
     * POST http://localhost:8080/boda-web/api/tarjetasCredito Ejemplo
     * json: {"numero": 0000 0000 0000 0000, "numDeSeg": 000, "fechaVen":"05-Dic-2017", "nombreBanco": "BancaMia"}
     *
     * @param idPareja
     * @param tarjeta correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "tarjetaDetailDTO", "id": 1, "numero": "0000 0000 0000 0000", "nombreBanco": "BancaMia" }
     * @throws BusinessLogicException
     */
    @POST
    public TarjetaCreditoDetailDTO createTarjetaCredito(@PathParam("idPareja") String idPareja,TarjetaCreditoDetailDTO tarjeta) throws BusinessLogicException {
        TarjetaCreditoEntity tarjetaEntity = tarjeta.toEntity();
        TarjetaCreditoEntity nuevoTarjeta = tarjetaCreditoLogic.createTarjetaCredito(idPareja, tarjetaEntity);
        return new TarjetaCreditoDetailDTO(nuevoTarjeta);
    }
    /**
     * PUT http://localhost:8080/boda-web/api/tarjetasCredito/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param idPareja
     * @param id corresponde a la tarjetaCredito a actualizar.
     * @param tarjeta corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La tarjeta actualizada.
     * @throws BusinessLogicException En caso de no existir el id de la tarjeta a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public TarjetaCreditoDetailDTO updateTarjetaCredito(@PathParam("idPareja") String idPareja, @PathParam("id") Long id, TarjetaCreditoDetailDTO tarjeta) throws BusinessLogicException {
        tarjeta.setNumero(id);
        TarjetaCreditoEntity entity = tarjetaCreditoLogic.getTarjetaCredito(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tarjetasCredito/" + id + " no existe.", 404);
        }
        return new TarjetaCreditoDetailDTO(tarjetaCreditoLogic.updateTarjetaCredito(idPareja, id, tarjeta.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/boda-web/api/tarjetasCredito/1
     *
     * @param idPareja
     * @param id corresponde a la tarjetaCredito a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la tarjetaCredito a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTarjetaCredito(@PathParam("idPareja") String idPareja,@PathParam("id") Long id) throws BusinessLogicException {
        TarjetaCreditoEntity entity =  tarjetaCreditoLogic.getTarjetaCredito( id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /parejas/tarjetasCredito/" + id + " no existe.", 404);
        }
        tarjetaCreditoLogic.deleteTarjetaCredito(idPareja, id);
    }
    
    /**
     * GET para una tarjetaCredito
     * http://localhost:8080/bodas-web/api/parejas/tarjetasCredito/1
     *
     * @param idPareja
     * @param id corresponde al id de la tarjetaCredito buscada.
     * @return La tarjetaCredito encontrada. Ejemplo: { "type":
     * "tarjetaDetailDTO", "id": 1, "numero": "0000 0000 0000 0000", "nombreBanco": "BancaMia" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la tarjetaCredito buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public TarjetaCreditoDetailDTO getTarjetaCredito(@PathParam("idPareja") String idPareja, @PathParam("id") Long id) throws BusinessLogicException {
        TarjetaCreditoEntity entity = tarjetaCreditoLogic.getTarjetaCredito(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /parejas/"  + idPareja + "tarjetasCredito/" + id + " no existe.", 404);
        }
        return new TarjetaCreditoDetailDTO(entity);
    }
    /**
     * GET para todas las tarjetasCredito.
     * http://localhost:8080/boda-web/api/parejas/idPareja/tarjetasCredito
     *
     * @param idPareja
     * @return la lista de todas las tarjetasCredito en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<TarjetaCreditoDetailDTO> getTarjetasCredito(@PathParam("idPareja") String idPareja) throws BusinessLogicException {
        return listEntity2DetailDTO(tarjetaCreditoLogic.getTarjetasCredito(idPareja));
    }
    
    @Path("{idTarjeta: \\d+}/pagos")
    public Class<PagoResource> getPagoResource(@PathParam("idTarjeta") Long tarjetaId) throws BusinessLogicException {
        TarjetaCreditoEntity entity = tarjetaCreditoLogic.getTarjetaCredito(tarjetaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tarjetasCredito/" + tarjetaId + "/pagos no existe.", 404);
        }
        return PagoResource.class;
    }
    
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TarjetaCreditoEntity a una lista de
     * objetos TarjetaCreditoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de tarjetasCredito de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de tarjetasCredito en forma DTO (json)
     */
    private List<TarjetaCreditoDetailDTO> listEntity2DetailDTO(List<TarjetaCreditoEntity> entityList) {
        List<TarjetaCreditoDetailDTO> list = new ArrayList<>();
        for(TarjetaCreditoEntity entity : entityList) {
            list.add(new TarjetaCreditoDetailDTO(entity));
        }
        return list;
    }
}