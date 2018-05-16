/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.ServicioDetailDTO;
import co.edu.uniandes.csw.boda.ejb.ServicioLogic;
import co.edu.uniandes.csw.boda.entities.ServicioEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.PathParam;


/**
 *
 * @author aj.ortiz10
 */
 
@Path("servicios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ServicioResource {
    
    @Inject
    ServicioLogic servicioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @GET
    @Path("{id: \\d+}")
    public ServicioDetailDTO getServicio(@PathParam("id") Long id)throws BusinessLogicException {
        ServicioEntity entity = servicioLogic.getServicio(id);
        System.out.println(entity);
    if (entity == null) {
        throw new WebApplicationException("El recurso servicio: " + id + " no existe.", 404);
    }
    return new ServicioDetailDTO(entity);
    }
    
    @POST
    public ServicioDetailDTO createServicio(ServicioDetailDTO servicio) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ServicioEntity servicioEntity = servicio.toEntity();
        // Invoca la lógica para crear el servicio nuevo
        ServicioEntity nuevoServicio = servicioLogic.createServicio(servicioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ServicioDetailDTO(nuevoServicio);
    }
    
    @GET
    public List<ServicioDetailDTO> getServicios() throws BusinessLogicException {
        return listEntity2DetailDTO(servicioLogic.getServicios());
    }
   
    private List<ServicioDetailDTO> listEntity2DetailDTO(List<ServicioEntity> entityList) {
        List<ServicioDetailDTO> list = new ArrayList<>();
        for (ServicioEntity entity : entityList) {
            list.add(new ServicioDetailDTO(entity));
        }
        return list;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ServicioDetailDTO updateServicio(@PathParam("id") Long id, ServicioDetailDTO servicio) throws BusinessLogicException
    {
        servicio.setId(id);
        ServicioEntity entity = servicioLogic.getServicio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /servicio/" + id + " no existe.", 404);
        }
        return new ServicioDetailDTO(servicioLogic.update(id, servicio.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteServicio(@javax.ws.rs.PathParam("id") Long id)throws BusinessLogicException 
    { 
    ServicioEntity entity = servicioLogic.getServicio(id);
    if (entity == null) {
        throw new WebApplicationException("El recurso /servicio/" + id + " no existe.", 404);
    }
    servicioLogic.deleteServicio(id);
    }
    @Path("{serviciosId: \\d+}/proveedores")
    public Class<ServiciosProveedoresResource> getProveedoresResource(@PathParam("serviciosId") Long servicioId) throws BusinessLogicException {
        ServicioEntity entity = servicioLogic.getServicio(servicioId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /servicios/" + servicioId + "/proveedores no existe.", 404);
        }
        return ServiciosProveedoresResource.class;
    }
}
