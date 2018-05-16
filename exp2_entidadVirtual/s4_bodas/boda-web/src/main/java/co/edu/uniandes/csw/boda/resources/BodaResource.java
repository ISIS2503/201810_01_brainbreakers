/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.BodaDetailDTO;
import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.ejb.BodaLogic;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author vn.gomez
 */
@Path("bodas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class BodaResource {
    
    @Inject
    BodaLogic bodaLogic;
    
    /**
     * POST http://localhost:8080/boda-web/api/bodas Ejemplo
     * json: { "name":"Boda: Luis y Maria", "fecha":"05-Dic-2017" }
     *
     * @param boda correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     la base de datos y el tipo del objeto java. Ejemplo: { "type":
     "BodaDetailDTO", "id": 1, "name": "Boda: Luis y Maria" }
     * @throws BusinessLogicException
     */
    @POST
    @Path("{name}")    
    public BodaDetailDTO createBoda(@PathParam("name") String idPareja,BodaDetailDTO boda) throws BusinessLogicException {
       
        return new  BodaDetailDTO(bodaLogic.create(idPareja,boda.toEntity()));
    }

    /**
     * GET para todas las bodas.
     * http://localhost:8080/boda-web/api/bodas
     *
     * @return la lista de todas las bodas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<BodaDetailDTO> getBodas() throws BusinessLogicException {
        return listEntity2DetailDTO(bodaLogic.getBodas());
    }

    /**
     * GET para una boda
     * http://localhost:8080/boda-web/api/bodas/1
     *
     * @param id corresponde al id de la boda buscada.
     * @return La Boda encontrada. Ejemplo: { "type": "BodaDetailDTO",
     "id": 1, "name": "Boda: Luis y Maria" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la boda  buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public BodaDetailDTO getBoda(@PathParam("id") Long id) throws BusinessLogicException {
       BodaEntity entity = bodaLogic.findBodaById(id);
        if(entity==null)
       {
           throw new  WebApplicationException("No existe una boda con el id dado",404);
       }
        return  new BodaDetailDTO(bodaLogic.findBodaById(id));
    }

    /**
     * PUT 
     * http://localhost:8080/boda-web/api/bodas/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la boda a actualizar.
     * @param boda corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La boda actualizado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la boda a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public BodaDetailDTO updateBoda(@PathParam("id") Long id, BodaDetailDTO boda) throws BusinessLogicException {
        BodaEntity entity = boda.toEntity();
        entity.setId(id);
        BodaEntity oldEntity = bodaLogic.findBodaById(id);
        if (oldEntity == null) {
            throw new WebApplicationException("La boda no existe", 404);
        }
        entity.setRegalos((oldEntity.getRegalos()));
        entity.setInvitados((oldEntity.getInvitados()));
        return new BodaDetailDTO(bodaLogic.updateBoda(entity));
    }
    
    @PUT
    @Path("{idBoda: \\d+}/pareja/{name}")
    public BodaDetailDTO asignarPareja(@PathParam("name") String parejaId, @PathParam("idBoda") Long bodaId ) throws BusinessLogicException{
        
        if(bodaLogic.findBodaById(bodaId)==null){
           throw new  WebApplicationException("No existe un boda con el id dado",404);
        }
        bodaLogic.asginarPareja(parejaId, bodaId); 
        return  new BodaDetailDTO(bodaLogic.findBodaById(bodaId));
    }
    
    /**
     * DELETE http://localhost:8080/boda-web/api/bodas/1
     *
     * @param id corresponde a la boda a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la boda a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBoda(@PathParam("id") Long id) throws BusinessLogicException {
        
       if(bodaLogic.findBodaById(id)==null)
       {
           throw new  WebApplicationException("No existe un boda con el id dado",404);
       }
       bodaLogic.removeBoda(id);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BodaEntity a una lista de
      objetos BodaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de bodas de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de bodas en forma DTO (json)
     */
    private List<BodaDetailDTO> listEntity2DetailDTO(List<BodaEntity> entityList) {
        List<BodaDetailDTO> list = new ArrayList<>();
        for (BodaEntity entity : entityList) {
            list.add(new BodaDetailDTO(entity));
        }
        return list;
    }
    
    @Path("{idBoda: \\d+}/regalos")
    public Class<RegaloResource> getRegaloResource(@PathParam("idBoda") Long bodaId) throws BusinessLogicException {
        BodaEntity entity = bodaLogic.findBodaById(bodaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /boda/" + bodaId + "/regalos no existe.", 404);
        }
        return RegaloResource.class;
    }
    
     @Path("{idBoda: \\d+}/invitados")
    public Class<InvitadoResource> getInvitadoResource(@PathParam("idBoda") Long bodaId) throws BusinessLogicException {
        BodaEntity entity = bodaLogic.findBodaById(bodaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /boda/" + bodaId + "/regalos no existe.", 404);
        }
        return InvitadoResource.class;
    }
      @Path("{idBoda: \\d+}/opcionesServicio")
    public Class<BodaOpcionServicioResource> getOpcionResource(@PathParam("idBoda") Long bodaId) throws BusinessLogicException {
        BodaEntity entity = bodaLogic.findBodaById(bodaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /boda/" + bodaId + "/regalos no existe.", 404);
        }
        return BodaOpcionServicioResource.class;
    }
  

}


    

