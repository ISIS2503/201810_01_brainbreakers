/*
 * The MIT License
 *
 * Copyright 2018 Universidad De Los Andes - Departamento de Ingeniería de Sistemas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.isis2503.nosqljpa.service;

import co.edu.uniandes.isis2503.nosqljpa.auth.AuthorizationFilter.Role;
import co.edu.uniandes.isis2503.nosqljpa.auth.Secured;
import co.edu.uniandes.isis2503.nosqljpa.interfaces.IResidenciaLogic;
import co.edu.uniandes.isis2503.nosqljpa.interfaces.IUnidadResidencialLogic;
import co.edu.uniandes.isis2503.nosqljpa.logic.UnidadResidencialLogic;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.AlertaDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.UnidadResidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DivisionResidencialDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author e.reyesm
 */
@Path("/unidadResidencial")
@Produces(MediaType.APPLICATION_JSON)
public class UnidadResidencialService {

    private final IUnidadResidencialLogic unidadResidencialLogic;

    public UnidadResidencialService() {
        this.unidadResidencialLogic = new UnidadResidencialLogic();
    }

    @POST
    //@Secured({Role.yale})
    public UnidadResidencialDTO add(UnidadResidencialDTO dto) {
        return unidadResidencialLogic.add(dto);
    }
    @POST    
    @Path("/autho")
    public Response autho() {
        return Response.ok().entity("{\"Hola Mundo\"}").status(Response.Status.ACCEPTED).build();
    }
    @POST
    //@Secured({Role.admin})
    @Path("/{nombre}/addDivisionResidencial")
    public UnidadResidencialDTO addDivision(@PathParam("nombre") String nombre, @QueryParam("division") String division) throws Exception{
        return unidadResidencialLogic.addDivison(nombre, division);
    }
    
    @POST
    //@Secured({Role.admin})
    @Path("/{nombreU}/{nombreD}/addResidencia")
    public DivisionResidencialDTO addResidencia(@PathParam("nombreU") String nombreU,@PathParam("nombreD") String nombreD ,@QueryParam("residencia") String residencia, @QueryParam("barrio") String barrio) throws Exception{
        System.out.println("-----LLEGO++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(residencia+"-"+ barrio);
        return unidadResidencialLogic.addResidencia(nombreU, nombreD, residencia, barrio);
    }
    
    @PUT
    @Secured({Role.yale})
    public UnidadResidencialDTO update(UnidadResidencialDTO dto) {
        return unidadResidencialLogic.update(dto);
    }

    @GET
    @Secured({Role.yale})
    @Path("/{id}")
    public UnidadResidencialDTO find(@PathParam("id") String id) {
        return unidadResidencialLogic.find(id);
    }

    @GET
    //@Secured({Role.yale})
    public List<UnidadResidencialDTO> all() {
        return unidadResidencialLogic.all();
    }
    
    @GET
    //@Secured({Role.admin})
    @Path("/getAlarmasPorUnidadResidencialYMes")
    public List<String> getAlarmasPorUnidadResidencialYmes(@QueryParam("nombreUnidadResidencial")String ur, @QueryParam("mes")String mes)
    {
        ArrayList<String> alertasBarrio = new ArrayList<>();
        List<UnidadResidencialDTO> unidades = unidadResidencialLogic.all();
        for (int i = 0; i < unidades.size(); i++) {
            UnidadResidencialDTO actual =unidades.get(i);
            if(actual.getNombre().equals(ur)){
            String divisionActual = actual.getDivisionesResidenciales().get(i);
          }
        }
        //if(alertasBarrio.size()==0){}
        return alertasBarrio;
    }
    


    @DELETE
    @Secured({Role.yale})
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            unidadResidencialLogic.delete(id);
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Sucessful: Sensor was deleted").build();
        } catch (Exception e) {
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }
}
