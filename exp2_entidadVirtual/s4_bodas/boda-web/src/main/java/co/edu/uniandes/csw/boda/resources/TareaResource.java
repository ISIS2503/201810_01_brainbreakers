/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.TareaDetailDTO;
import co.edu.uniandes.csw.boda.ejb.TareaLogic;
import co.edu.uniandes.csw.boda.entities.TareaEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
 * @author sp.joven
 */

@Produces("application/json")
@Consumes("application/json")
public class TareaResource {

    @Inject
    private TareaLogic tareaLogic;

    private static final Logger LOGGER = Logger.getLogger(TareaResource.class.getName());

    /**
     * POST http://localhost:8080/bodas-web/api/tareas Ejemplo json: {
     * "name":"Norma" }
     *
     * @param idOpcion
     * @param tarea correponde a la representación java del objeto json enviado
     * en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "TareaDetailDTO", "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     */
    @POST
    public TareaDetailDTO createTarea(@PathParam("idOpcion") Long idOpcion, TareaDetailDTO tarea) throws BusinessLogicException {

        TareaEntity tareaEntity = tarea.toEntity();
        TareaEntity nuevaTarea = tareaLogic.create(idOpcion, tareaEntity);
        return new TareaDetailDTO(nuevaTarea);
    }

    /**
     * GET para todas las tareas. http://localhost:8080/boda-web/api/tareas
     *
     * @return la lista de todas las tareas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<TareaDetailDTO> getTareas(@PathParam("idOpcion") Long idOpcion) throws BusinessLogicException {
        return listEntity2DetailDTO(tareaLogic.getTareas(idOpcion));
    }

    /**
     * GET para una tarea http://localhost:8080/boda-web/api/tareas/1
     *
     * @param id corresponde al id de la tarea buscada.
     * @return La tarea encontrada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la tarea buscada se retorna un 404 con el
     * mensaje.
     */
   @GET
    @Path("{id: \\d+}")
    public TareaDetailDTO getTarea(@PathParam("id") Long id) throws BusinessLogicException {
        TareaEntity entity = tareaLogic.findTareaById(id);
        if (entity == null) {
            throw new WebApplicationException("No existe una tarea con el id dado", 404);
        }
        return new TareaDetailDTO(entity);
    }
   
   
   
    

    /**
     * PUT http://localhost:8080/boda-web/api/tareas/1 Ejemplo json { "id": 1,
     * "name": "cambio de nombre" }
     *
     * @param id corresponde a la tarea a actualizar.
     * @param tarea corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La tarea actualizado.
     * @throws BusinessLogicException En caso de no existir el id de la tarea a
     * actualizar se retorna un 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public TareaDetailDTO updateTarea(@PathParam("idOpcion") Long idOpcion, @PathParam("id") Long id, TareaDetailDTO tarea) throws BusinessLogicException {
        
        tarea.setId(id);
        TareaEntity entity = tareaLogic.findTareaById(id);

        if (entity == null) {
            throw new WebApplicationException("No existe una tarea con el id dado", 404);
        }
        return new TareaDetailDTO(tareaLogic.updateTarea(idOpcion, tarea.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/boda-web/api/tareas/1
     *
     * @param id corresponde a la tareas a borrar.
     * @throws BusinessLogicException En caso de no existir el id de la tarea a
     * actualizar se retorna un 404 con el mensaje.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTarea(@PathParam("idOpcion") Long idOpcion,@PathParam("id") Long id) throws BusinessLogicException {
        TareaEntity entity = tareaLogic.findTareaById(id);
        if (entity == null) {
            throw new WebApplicationException("No existe una tarea con el id dado", 404);
        }
        tareaLogic.removeTarea(id);
    }
      
   
   

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BodaEntity a una lista de
     * objetos BodaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de tarea de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de bodas en forma DTO (json)
     */
    private List<TareaDetailDTO> listEntity2DetailDTO(List<TareaEntity> entityList) {
        List<TareaDetailDTO> list = new ArrayList<>();
        for (TareaEntity entity : entityList) {
            list.add(new TareaDetailDTO(entity));
        }
        return list;
    }
    
      
   
    


}