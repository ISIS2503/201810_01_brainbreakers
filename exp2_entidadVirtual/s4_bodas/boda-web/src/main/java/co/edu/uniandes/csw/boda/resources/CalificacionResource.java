/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.boda.ejb.CalificacionLogic;
import co.edu.uniandes.csw.boda.entities.CalificacionEntity;
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
 * @author nf.ortiz
 */

@Produces("application/json")
@Consumes("application/json")

public class CalificacionResource  {
    @Inject
    CalificacionLogic logic;
    
    /**
     * POST http://localhost:8080/boda-web/api/calificaciones Ejemplo
     * json: { "name":"Norma" }
     *
     * @param pareja correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "parejaDetailDTO", "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     */
    @POST
    public CalificacionDetailDTO create(@PathParam("opcionId")Long opcionId,CalificacionDetailDTO calif) throws BusinessLogicException{
        return new CalificacionDetailDTO(logic.create(opcionId,calif.toEntity()));
    }
    
    @GET
    public List<CalificacionDetailDTO> getCalificiones(@PathParam("opcionId")Long opcionId){
        return listEntity2DetailDTO(logic.getCalificaciones(opcionId));
    }
    
    @GET
    @Path("{id: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("opcionId")Long opcionId,@PathParam ("id") Long id) throws BusinessLogicException {
        if(logic.getCalificacion(opcionId,id)== null)throw new  WebApplicationException("No existe una calificacion con el id dado",404);
        return new CalificacionDetailDTO(logic.getCalificacion(opcionId,id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CalificacionDetailDTO update(@PathParam("opcionId")Long opcionId,@PathParam ("id") Long id, CalificacionDetailDTO calif) throws BusinessLogicException{
        calif.setId(id);
        if(logic.getCalificacion(opcionId, id)==null) throw new WebApplicationException("El recurso /calificaciones/" + id + " no existe.", 404);
        return new CalificacionDetailDTO(logic.updateCalificacion(opcionId,id, calif.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void delete(@PathParam("opcionId")Long opcionId,@PathParam ("id") Long id) throws BusinessLogicException{
        if(logic.getCalificacion(opcionId, id)==null) throw new WebApplicationException("El recurso /calificaciones/" + id + " no existe.", 404);
        logic.deleteCalificacion(id);
    }
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ParejaEntity a una lista de
     * objetos EditorialDetailDTO (json)
     *
     * @param entityList corresponde a la lista de parejas de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de parejas en forma DTO (json)
     */
    private List<CalificacionDetailDTO> listEntity2DetailDTO(List<CalificacionEntity> entityList) {
        List<CalificacionDetailDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDetailDTO(entity));
        }
        return list;
    }
}
