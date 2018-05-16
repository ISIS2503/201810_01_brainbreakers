/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.resources;

import co.edu.uniandes.csw.boda.dtos.ParejaDetailDTO;
import co.edu.uniandes.csw.boda.entities.ParejaEntity;
import co.edu.uniandes.csw.boda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.boda.persistence.ParejaPersistence;
import co.edu.uniandes.csw.boda.ejb.ParejaLogic;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
/**
 *
 * @author nf.ortiz
 */
@Path("parejas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ParejaResource {
    
    @Inject
    ParejaLogic parejaLogic;
    
     private static final Logger LOGGER = Logger.getLogger(ParejaPersistence.class.getName());

    /**
     * POST http://localhost:8080/boda-web/api/parejas Ejemplo
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
    public ParejaDetailDTO createPareja(ParejaDetailDTO pareja) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ParejaEntity editorialEntity = pareja.toEntity();
        // Invoca la lógica para crear la editorial nueva
        ParejaEntity nuevoEditorial = parejaLogic.create(editorialEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ParejaDetailDTO(nuevoEditorial);
    }

    /**
     * GET para todas las editoriales.
     * http://localhost:8080/bodas/api/parejas
     *
     * @return la lista de todas las parejas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<ParejaDetailDTO> getParejas() throws BusinessLogicException {
        return listEntity2DetailDTO(parejaLogic.getParejas());
    }

    /**
     * GET para una editorial
     * http://localhost:8080/bodas/api/parejas/string
     *
     * @param id corresponde al id de la pareja buscada.
     * @return La editorial encontrada. Ejemplo: { "type": "editorialDetailDTO",
     * "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{name}")
    public ParejaDetailDTO getEditorial(@PathParam("name") String id) throws BusinessLogicException {
        ParejaEntity entity = parejaLogic.getPareja(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /parejas/" + id + " no existe.", 404);
        }
        return new ParejaDetailDTO(parejaLogic.getPareja(id));
    }

    /**
     * PUT http://localhost:8080/bodas/api/parejas/string Ejemplo
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la pareja a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{name}")
    public ParejaDetailDTO updateEditorial(@PathParam("name") String id, ParejaDetailDTO pareja) throws BusinessLogicException {
        pareja.setCorreoElec(id);
        ParejaEntity entity = parejaLogic.getPareja(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /parejas/" + id + " no existe.", 404);
        }
        return new ParejaDetailDTO(parejaLogic.updatePareja(id, pareja.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/bodas/api/parejas/string
     *
     * @param id corresponde a la editorial a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la pareja a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{name}")
    public void deleteEditorial(@PathParam("name") String id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una pareja con id {0}", id);
        ParejaEntity entity = parejaLogic.getPareja(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /parejas/" + id + " no existe.", 404);
        }
        parejaLogic.removePareja(id);
    }
    
    @Path("{idPareja}/tarjetasCredito")
    public Class<TarjetaCreditoResource> getTarjetaCreditoResource(@PathParam("idPareja") String parejaId) throws BusinessLogicException {
        ParejaEntity entity = parejaLogic.getPareja(parejaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /parejas/" + parejaId + "/tarjetasCredito no existe.", 404);
        }
        return TarjetaCreditoResource.class;
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
    private List<ParejaDetailDTO> listEntity2DetailDTO(List<ParejaEntity> entityList) {
        List<ParejaDetailDTO> list = new ArrayList<>();
        for (ParejaEntity entity : entityList) {
            list.add(new ParejaDetailDTO(entity));
        }
        return list;
    }

}
