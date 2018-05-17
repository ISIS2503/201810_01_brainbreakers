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
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.ValidacionDTO;
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
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

    public UserService() {
        usuarioLogic = new UserLogic();
    }

    @POST
    @Path("/add")
    public Response add(UserDTO dto, @QueryParam("residencia") String residencia, @QueryParam("tipo") String tipo) throws Exception {
        usuarioLogic.add(dto, residencia, tipo);
        HttpResponse<String> response = Unirest.post("https://brainbreakers.auth0.com/dbconnections/signup")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"vJihRX7vDJpg-Y821hGreFqMoa2TAxmp\",\"email\":\"" + dto.getCorreo() + "\",\"password\":\"" + dto.getPassword() + "\",\"connection\":\"Username-Password-Authentication\"}")
                .asString();
        return Response.ok().entity("{\"Se agrego ususario\"}").status(Response.Status.ACCEPTED).build();
    }

    @POST
    @Path("/updatePassword")
    public Response update(UserDTO dto, @QueryParam("tipo") String tipo) throws Exception {
        usuarioLogic.update(dto);
        HttpResponse<String> response = Unirest.post("https://brainbreakers.auth0.com/dbconnections/change_password")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"vJihRX7vDJpg-Y821hGreFqMoa2TAxmp\",\"email\":\"" + dto.getCorreo() + "\",\"password\":\"\",\"connection\":\"Username-Password-Authentication\"}")
                .asString();
        return Response.ok().entity("{\"Se le envio un link al correo para cambiar contraseña\"}").status(Response.Status.ACCEPTED).build();
    }
    @GET
    @Path("/validarSeguridad")
    public ValidacionDTO validarSeguridad(@QueryParam("email") String email, @QueryParam("contraseña") String password)
    {
        if((usuarioLogic.validarUsuarioSeguridad(email, password))==true)
        {
            return new ValidacionDTO(true);
        }
        else
        {
            return new ValidacionDTO(false);
        }
    }

    @DELETE
    public Response borrar(@QueryParam("residencia") String residencia, @QueryParam("email") String email, @QueryParam("userId") String id, @QueryParam("auto") String auto, @QueryParam("tipo") String tipo) throws Exception {
        usuarioLogic.delete(email, residencia, tipo);
        HttpResponse<String> response = Unirest.delete("https://brainbreakers.auth0.com/api/v2/users/auth0|" + id)
                .header("Authorization", "Bearer " + auto)
                .asString();
        return Response.ok().entity("{\"Se borro el usuario\"}").status(Response.Status.ACCEPTED).build();
    }

    @GET
    public Response obtenerInfo(@QueryParam("auto") String auto) throws Exception {
//      URL url = new URL("https://brainbreakers.auth0.com/userinfo");
//        URLConnection con = url.openConnection();
//        con.addRequestProperty("Authorization", "Bearer "+auto);
//        
//        Authenticator au = new Authenticator() {
//         @Override
//         protected PasswordAuthentication
//            getPasswordAuthentication() {
//            return new PasswordAuthentication
//               ("usuario", "clave".toCharArray());
//         }
//      };
//      Authenticator.setDefault(au);
//      BufferedReader in = new BufferedReader(
//         new InputStreamReader(con.getInputStream()));
//
//      String linea;
//      while ((linea = in.readLine()) != null) {
//         System.out.println(linea+"*******************************");
//      }
//     
//        return Response.ok().entity("{\"Se borro el usuario\"}").status(Response.Status.ACCEPTED).build();
        HttpResponse<String> response = Unirest.get("https://brainbreakers.auth0.com/userinfo")
                .header("Authorization", "Bearer " + auto)
                .asString();
        return Response.ok().entity("{\"" + response.getBody() + "\"}").status(Response.Status.ACCEPTED).build();
    }

}
