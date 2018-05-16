/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.RegaloDetailDTO;
import co.edu.uniandes.csw.boda.ejb.RegaloLogic;
import co.edu.uniandes.csw.boda.entities.RegaloEntity;
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
 * @author mf.valllejo
 */
@Produces("application/json")
@Consumes("application/json")
public class RegaloResource {
    
    @Inject
    RegaloLogic regaloLogic;
    
    @GET
    @Path("{id: \\d+}")
    public RegaloDetailDTO getRegalo(@PathParam("idBoda") Long idBoda , @PathParam("id") Long id) throws BusinessLogicException {
        RegaloEntity entity = regaloLogic.get(id);
        if(entity == null){
            throw new WebApplicationException("El recurso /bodas/" + idBoda + "/regalos/" + id + " no existe.", 404);
        }
        return new RegaloDetailDTO(entity) ;
    }
    
    @POST
    public RegaloDetailDTO createRegalo(@PathParam("idBoda") Long idBoda,RegaloDetailDTO regalo) throws BusinessLogicException {
        RegaloEntity regaloEntity = regalo.toEntity();
        RegaloEntity nuevoRegalo = regaloLogic.createRegalo(idBoda,regaloEntity);
        return new RegaloDetailDTO(nuevoRegalo);
    }
    
    @GET
    public List<RegaloDetailDTO> getregalos(@PathParam("idBoda") Long idBoda) throws BusinessLogicException {
        return listEntity2DetailDTO(regaloLogic.getRegalos(idBoda));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public RegaloDetailDTO updateRegalo(@PathParam("idBoda") Long idBoda,@PathParam("id") Long id, RegaloDetailDTO regalo) throws BusinessLogicException {
        regalo.setId(id);
        RegaloEntity entity = regaloLogic.get(id);
        if(entity == null){
            throw new WebApplicationException("El recurso /bodas/" + idBoda + "/regalos/" + id + " no existe.", 404);
        }
        return new RegaloDetailDTO(regaloLogic.update(idBoda,regalo.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRegalo(@PathParam("idBoda") Long idBoda,@PathParam("id") Long id) throws BusinessLogicException {
        RegaloEntity entity = regaloLogic.get(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bodas/" + idBoda + "/regalos/" + id + " no existe.", 404);
        }
        regaloLogic.delete(id);
    }
    
    private List<RegaloDetailDTO> listEntity2DetailDTO(List<RegaloEntity> entityList) {
        List<RegaloDetailDTO> list = new ArrayList<>();
        for (RegaloEntity entity : entityList) {
            list.add(new RegaloDetailDTO(entity));
        }
        return list;
    }
    
    @Path("{regalosId: \\d+}/ubicaciones")
    public Class<RegaloubicacionResource> getRegaloUbicacionResource(@PathParam("regalosId") Long regalosId) {
        RegaloEntity entity = regaloLogic.get(regalosId);
        if (entity == null) {
            throw new WebApplicationException("El regalo no existe", 404);
        }
        return RegaloubicacionResource.class;
    }
    
}
