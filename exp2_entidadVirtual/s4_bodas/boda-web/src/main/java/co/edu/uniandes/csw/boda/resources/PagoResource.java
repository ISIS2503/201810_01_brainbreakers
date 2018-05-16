/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.PagoDetailDTO;
import co.edu.uniandes.csw.boda.ejb.PagoLogic;
import co.edu.uniandes.csw.boda.entities.PagoEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
public class PagoResource {
    @Inject
    PagoLogic pagoLogic;
    
    
    /**
     * POST http://localhost:8080/boda-web/api/pagos Ejemplo
     * json: {"numero": 0000 0000 0000 0000, "numDeSeg": 000, "fechaVen":"05-Dic-2017", "nombreBanco": "BancaMia}
     * @param idTarjeta
     * @param pago correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: {"type":
     * "pagoDetailDTO", "id": 1, "montoTotal": "100000"}
     * @throws BusinessLogicException
     */
    @POST
    public PagoDetailDTO createPago(@PathParam("idTarjeta") Long idTarjeta, PagoDetailDTO pago) throws BusinessLogicException {
        PagoEntity pagoEntity = pago.toEntity();
        PagoEntity nuevoPago = pagoLogic.createPago(idTarjeta, pagoEntity);
        return new PagoDetailDTO(nuevoPago);
    }
    
    /**
     * PUT http://localhost:8080/boda-web/api/pagos Ejemplo
     * json: {"numero": 0000 0000 0000 0000, "numDeSeg": 000, "fechaVen":"05-Dic-2017", "nombreBanco": "BancaMia}
     * @param idTarjeta
     * @param id corresponde al pago a actualizar.
     * @param pago correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: {"type":
     * "pagoDetailDTO", "id": 1, "montoTotal": "100000"}
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public PagoDetailDTO updatePago(@PathParam("idTarjeta") Long idTarjeta, @PathParam("id") Long id, PagoDetailDTO pago) throws BusinessLogicException {
        pago.setId(id);
        PagoEntity entity = pagoLogic.getPago(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso tarjetasCredito/" + idTarjeta + "/pagos/" + id + " no existe.", 404);
        }
        return new PagoDetailDTO(pagoLogic.updatePago(idTarjeta, id, pago.toEntity()));
    }
    
    /**
     * GET para un Pago
     * http://localhost:8080/bodas-web/api/parejas/pagos/1
     *
     * @param idTarjeta
     * @param id corresponde al id del Pago buscada.
     * @return El pago encontrado. Ejemplo: {"type":
     * "pagoDetailDTO", "id": 1, "montoTotal": "100000"}
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pago buscado se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public PagoDetailDTO getPago(@PathParam("idTarjeta") Long idTarjeta, @PathParam("id") Long id) throws BusinessLogicException {
        PagoEntity entity = pagoLogic.getPago(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso tarjetasCredito/" + idTarjeta + "/pagos" + id + "no existe.", 404);
        }
        return new PagoDetailDTO(entity);
    }
    /**
     * GET para todos los pagos.
     * http://localhost:8080/boda-web/api/pagos
     *
     * @param idTarjeta
     * @return la lista de todas los pagos en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<PagoDetailDTO> getPagos(@PathParam("idTarjeta") Long idTarjeta) throws BusinessLogicException {
        return listEntity2DetailDTO(pagoLogic.getPagos(idTarjeta));
    }
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PagoEntity a una lista de
     * objetos PagoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de pagos de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de pagos en forma DTO (json)
     */
    private List<PagoDetailDTO> listEntity2DetailDTO(List<PagoEntity> entityList) {
        List<PagoDetailDTO> list = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDetailDTO(entity));
        }
        return list;
    }
}