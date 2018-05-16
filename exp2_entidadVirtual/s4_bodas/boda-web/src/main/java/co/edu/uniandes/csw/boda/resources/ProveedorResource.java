/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.ProveedorDetailDTO;
import co.edu.uniandes.csw.boda.ejb.ProveedorLogic;
import co.edu.uniandes.csw.boda.entities.ProveedorEntity;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author aj.ortiz10
 */
@Path("proveedores")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ProveedorResource {
    
    @Inject
    ProveedorLogic proveedorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @GET
    @Path("{id: \\d+}")
    public ProveedorDetailDTO getProveedor(@PathParam("id") Long id){
        ProveedorEntity entity = proveedorLogic.getProveedor(id);
    if (entity == null) {
        throw new WebApplicationException("El recurso proveedor: " + id + " no existe.", 404);
    }
    ProveedorDetailDTO city = new ProveedorDetailDTO(entity);
    return city;
    }
    
    @POST
    public ProveedorDetailDTO createProveedor(ProveedorDetailDTO proveedor) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ProveedorEntity proveedorEntity = proveedor.toEntity();
        // Invoca la lógica para crear el proveedor nuevo
        ProveedorEntity nuevoProveedor = proveedorLogic.createProveedor(proveedorEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ProveedorDetailDTO(nuevoProveedor);
    }
    
    @GET
    public List<ProveedorDetailDTO> getProveedores() throws BusinessLogicException {
        return listEntity2DetailDTO(proveedorLogic.getProveedor());
    }
   
    private List<ProveedorDetailDTO> listEntity2DetailDTO(List<ProveedorEntity> entityList) {
        List<ProveedorDetailDTO> list = new ArrayList<>();
        for (ProveedorEntity entity : entityList) {
            list.add(new ProveedorDetailDTO(entity));
        }
        return list;
    }
   
     @Path("{idProveedor: \\d+}/opcionServicios")
    public Class<OpcionServicioResource> obtenerOpciones(@PathParam("idProveedor") Long idProveedor) throws BusinessLogicException{
        ProveedorEntity buscado = proveedorLogic.findProveedorById(idProveedor);
        if(buscado==null)
        {throw new WebApplicationException("El recurso /proveedor/" + idProveedor + " no existe.", 404);
        
        }
        return OpcionServicioResource.class;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ProveedorDetailDTO updateProveedor(@PathParam("id") Long id, ProveedorDetailDTO proveedor) throws BusinessLogicException
    {
        proveedor.setId(id);
        ProveedorEntity entity = proveedorLogic.getProveedor(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /proveedor/" + id + " no existe.", 404);
        }
        return new ProveedorDetailDTO(proveedorLogic.update(proveedor.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProveedor(@PathParam("id") Long id) 
    { 
    ProveedorEntity entity = proveedorLogic.getProveedor(id);
    if (entity == null) {
        throw new WebApplicationException("El recurso /proveedor/" + id + " no existe.", 404);
    }
    proveedorLogic.deleteProveedor(entity);
    }
}
