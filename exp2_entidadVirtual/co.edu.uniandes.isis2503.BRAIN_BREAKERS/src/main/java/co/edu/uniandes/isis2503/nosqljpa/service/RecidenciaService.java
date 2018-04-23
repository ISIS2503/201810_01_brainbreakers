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

import ch.qos.logback.classic.util.ContextInitializer;
import co.edu.uniandes.isis2503.nosqljpa.auth.AuthorizationFilter.Role;
import co.edu.uniandes.isis2503.nosqljpa.auth.Secured;
import co.edu.uniandes.isis2503.nosqljpa.interfaces.IResidenciaLogic;
import co.edu.uniandes.isis2503.nosqljpa.logic.ResidenciaLogic;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.ResidenciaDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.AlertaDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DivisionResidencialDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("/residencia")
@Produces(MediaType.APPLICATION_JSON)
public class RecidenciaService {

    private final IResidenciaLogic residenciaLogic;

    public RecidenciaService() {
        this.residenciaLogic = new ResidenciaLogic();
    }

    @POST
    @Path("/{nombreU}/{nombreD}/{nombreR}/addAlarma")
    public ResidenciaDTO addAlerta(@PathParam("nombreU") String nombreU, @PathParam("nombreD") String nombreD, @PathParam("nombreR") String nombreR, @QueryParam("alerta") String alerta) throws Exception {
        return residenciaLogic.addAlerta(nombreU, nombreD, nombreR, alerta);
    }
    
    @GET
    @Secured({Role.yale})
    @Path("/allAlarmas")
    public List<AlertaDTO> getAllAlarmas() throws Exception {
        return residenciaLogic.getAllAlertas();
    }
    
    @POST
    @Secured({Role.yale,Role.admin,Role.seguridad})
    @Path("/{nombreU}/consultarAlarmas")
    public List<AlertaDTO> addAlerta(@PathParam("nombreU") String nombreU) throws Exception {
        return residenciaLogic.getAllAlertasByUnidad(nombreU);
    }
    
    @POST
    @Secured({Role.user})
    @Path("/{nombreU}/{nombreD}/{nombreR}/addHorario")
    public ResidenciaDTO addHorario(@PathParam("nombreU") String nombreU, @PathParam("nombreD") String nombreD, @PathParam("nombreR") String nombreR, @QueryParam("horario") String horario, @QueryParam("usuario") String user) throws Exception {
        String key =nombreR+"_"+nombreD+"_"+nombreU;
        residenciaLogic.validarUsuario(user, key);
        return residenciaLogic.addHorario(nombreU, nombreD, nombreR, horario);
    }
    
    @PUT
    @Secured({Role.user})
    @Path("/{nombreR}/cambiarHorario")
    public ResidenciaDTO cambiarHorario(@PathParam("nombreR") String nombreR, @QueryParam("horarioA") String horario, @QueryParam("horarioN") String horarioN, @QueryParam("usuario") String user) throws Exception {
        residenciaLogic.validarUsuario(user, nombreR);
        return residenciaLogic.updateHorario(horarioN, nombreR, horario);
    }
    
    @DELETE
    @Secured({Role.user})
    @Path("/{nombreR}/borrarHorario")
    public ResidenciaDTO cambiarHorario(@PathParam("nombreR") String nombreR, @QueryParam("horarioA") String horario, @QueryParam("usuario") String user) throws Exception {
        residenciaLogic.validarUsuario(user, nombreR);
        return residenciaLogic.deleteHorario(nombreR, horario);
    }
    
    @PUT
    @Secured({Role.admin})
    public ResidenciaDTO update(ResidenciaDTO dto) {
        return residenciaLogic.update(dto);
    }

    @GET
    @Secured({Role.user})
    @Path("/{id}")
    public ResidenciaDTO find(@PathParam("id") String id) {
        return residenciaLogic.find(id);
    }

    @GET
    @Secured({Role.admin})
    public List<ResidenciaDTO> all() {
        return residenciaLogic.all();
    }

    @DELETE
    @Secured({Role.admin})
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            residenciaLogic.delete(id);
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Sucessful: Sensor was deleted").build();
        } catch (Exception e) {
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }
}
