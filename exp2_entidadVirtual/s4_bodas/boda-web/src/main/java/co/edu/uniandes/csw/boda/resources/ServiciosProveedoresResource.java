/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.ProveedorDetailDTO;

import co.edu.uniandes.csw.boda.ejb.ServicioLogic;
import co.edu.uniandes.csw.boda.entities.ProveedorEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ca.guerrero
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiciosProveedoresResource {
    @Inject
    private ServicioLogic servicioLogic;
    /**
     * Convierte una lista de ProveedorEntity a una lista de ProveedorDetailDTO.
     *
     * @param entityList 
     * @return List
     * 
     */
    private List<ProveedorDetailDTO> proveedoresListEntity2DTO(List<ProveedorEntity> entityList) {
        List<ProveedorDetailDTO> list = new ArrayList<>();
        for (ProveedorEntity entity : entityList) {
            list.add(new ProveedorDetailDTO(entity));
        }
        return list;
    }
    /**
     * Convierte una lista de ProveedorDetailDTO a una lista de ProveedorEntity.
     *
     * @param dtos
     * @return List
     * 
     */
    private List<ProveedorEntity> proveedoresListDTO2Entity(List<ProveedorDetailDTO> dtos) {
        List<ProveedorEntity> list = new ArrayList<>();
        for (ProveedorDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    /**
     * Obtiene una colección de instancias de ProveedorDetailDTO asociadas a una
     * instancia de Servicio
     *
     * @param servicioId
     * @return Colección de instancias
     * 
     */
    @GET
    public List<ProveedorDetailDTO> listProveedores(@PathParam("serviciosId") Long servicioId) {
        return proveedoresListEntity2DTO(servicioLogic.listProveedores(servicioId));
    }

    /**
     * Obtiene una instancia de ProveedorDetailDTO asociada a una instancia de Servicio
     *
     * @param serviciosId Identificador de la instancia de Book
     * @param proveedorId Identificador de la instancia de Author
     * @return 
     * 
     */
    @GET
    @Path("{proveedoresId: \\d+}")
    public ProveedorDetailDTO getProveedores(@PathParam("serviciosId") Long serviciosId, @PathParam("proveedoresId") Long proveedorId) {
        return new ProveedorDetailDTO(servicioLogic.getProveedor(serviciosId, proveedorId));
    }

    /**
     * Asocia un Author existente a un Book
     *
     * @param serviciosId Identificador de la instancia de Book
     * @param proveedoresId Identificador de la instancia de Author
     * @return Instancia de AuthorDetailDTO que fue asociada a Book
     * 
     */
    @POST
    @Path("{proveedoresId: \\d+}")
    public ProveedorDetailDTO addProveedores(@PathParam("serviciosId") Long serviciosId, @PathParam("proveedoresId") Long proveedoresId) {
        return new ProveedorDetailDTO(servicioLogic.addProveedor(serviciosId, proveedoresId));
    }

    /**
     * Remplaza las instancias de Author asociadas a una instancia de Book
     *
     * @param serviciosId Identificador de la instancia de Servicio
     * @param proveedores Colección de instancias de ProveedorDTO a asociar a instancia
     * de Proveedores
     * @return Nueva colección de AuthorDTO asociada a la instancia de Book
     * 
     */
    @PUT
    public List<ProveedorDetailDTO> replaceProveedores(@PathParam("serviciosId") Long serviciosId, List<ProveedorDetailDTO> proveedores) {
        return proveedoresListEntity2DTO(servicioLogic.replaceProveedores(serviciosId, proveedoresListDTO2Entity(proveedores)));
    }

    /**
     * Desasocia un Proveedor existente de un Servicio existente
     *
     * @param serviciosId Identificador de la instancia de Book
     * @param proveedoresId Identificador de la instancia de Author
     * 
     */
    @DELETE
    @Path("{proveedoresId: \\d+}")
    public void removeProveedores(@PathParam("booksId") Long serviciosId, @PathParam("proveedoresId") Long proveedoresId) {
        servicioLogic.removeProveedor(serviciosId, proveedoresId);
    }
}
