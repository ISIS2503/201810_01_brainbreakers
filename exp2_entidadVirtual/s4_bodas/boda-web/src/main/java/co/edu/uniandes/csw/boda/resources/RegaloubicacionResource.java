/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.UbicacionDetailDTO;
import co.edu.uniandes.csw.boda.ejb.RegaloLogic;
import co.edu.uniandes.csw.boda.entities.UbicacionEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mf.valllejo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegaloubicacionResource {
    
    
    @Inject private RegaloLogic regaloLogic;

    private List<UbicacionDetailDTO> ubicacionesListEntity2DTO(List<UbicacionEntity> entityList){
        List<UbicacionDetailDTO> list = new ArrayList<>();
        for (UbicacionEntity entity : entityList) {
            list.add(new UbicacionDetailDTO(entity));
        }
        return list;
    }

    @GET
    public List<UbicacionDetailDTO> listUbicaciones(@PathParam("regalosId") Long regaloId) {
        return ubicacionesListEntity2DTO(regaloLogic.listUbicaciones(regaloId));
    }

    @GET
    @Path("{ubicacionId: \\d+}")
    public UbicacionDetailDTO getUbicaciones(@PathParam("regalosId") Long regaloId, @PathParam("ubicacionId") Long ubicacionId) {
        return new UbicacionDetailDTO(regaloLogic.getUbicaciones(regaloId, ubicacionId));
    }

    @POST
    @Path("{ubicacionId: \\d+}")
    public UbicacionDetailDTO addUbicaciones(@PathParam("regalosId") Long regaloId, @PathParam("ubicacionId") Long ubicacionId) throws BusinessLogicException {
        return new UbicacionDetailDTO(regaloLogic.addUbi(regaloId, ubicacionId));
    }
    
}
