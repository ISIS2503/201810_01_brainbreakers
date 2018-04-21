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
import co.edu.uniandes.isis2503.nosqljpa.interfaces.IUserLogic;
import co.edu.uniandes.isis2503.nosqljpa.logic.UserLogic;
import co.edu.uniandes.isis2503.nosqljpa.logic.UserLogic;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.UserDTO;
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
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * /**
 *
 * @author e.reyesm
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    
    private final IUserLogic usuarioLogic;
    
    public UserService ()
    {
        usuarioLogic = new UserLogic();
    }
    

    @POST
    @Path("/add")
    public Response add(UserDTO dto) throws Exception {
        usuarioLogic.add(dto);
        HttpResponse<String> response = Unirest.post("https://brainbreakers.auth0.com/dbconnections/signup")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"vJihRX7vDJpg-Y821hGreFqMoa2TAxmp\",\"email\":\""+dto.getCorreo()+"\",\"password\":\""+dto.getPassword()+"\",\"connection\":\"Username-Password-Authentication\"}")
                .asString();
        return Response.ok().entity("{\"Se agrego ususario\"}").status(Response.Status.ACCEPTED).build();
    }
    
    @POST
    @Path("/updatePassword")
    public Response update(UserDTO dto) throws Exception {
        usuarioLogic.add(dto);
        HttpResponse<String> response = Unirest.post("https://brainbreakers.auth0.com/dbconnections/change_password")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"vJihRX7vDJpg-Y821hGreFqMoa2TAxmp\",\"email\":\""+dto.getCorreo()+"\",\"password\":\""+dto.getPassword()+"\",\"connection\":\"Username-Password-Authentication\"}")
                .asString();
        return Response.ok().entity("{\"Se agrego ususario\"}").status(Response.Status.ACCEPTED).build();
    }
}
