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
import co.edu.uniandes.isis2503.nosqljpa.logic.UnidadResidencialLogic;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.UnidadResidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DivisionResidencialDTO;
import java.net.URISyntaxException;
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
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

/**
 *
 * @author e.reyesm
 */
@Path("/controlador")
@Produces(MediaType.APPLICATION_JSON)
public class WiringService {

    private final IResidenciaLogic residenciaLogic;

    public WiringService() {
        residenciaLogic = new ResidenciaLogic();
    }

    public void publish(String mensaje) throws Exception {
        System.out.println("Llego 1");
        MQTT mqtt = new MQTT();
        mqtt.setHost("tcp://172.24.42.29:8083");

        // crear conexion con el broker
        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        System.out.println("Llego 2");
        // publicar al topico el mensaje ON u OFF en fncion a la intensidad de la lectura
        connection.publish("torre1.1-201", mensaje.getBytes(), QoS.AT_LEAST_ONCE, false);

        System.out.println("Llego 3");
        connection.disconnect();
        System.out.println("Llego 4");
    }

    @POST
    @Secured({Role.user})
    @Path("/agregar")
    public Response addPassword(@QueryParam("clave") String clave, @QueryParam("index") String index, @QueryParam("residenca") String residencia, @QueryParam("user") String user) throws Exception {
        System.out.println("Resourse");
        residenciaLogic.validarUsuario(user, residencia);
        for (int i = 0; i < 4; i++) {
            publish("agregarClave;" + clave + ";" + index);
        }

        return Response.ok().entity("{\"llego\":\"Agregar contraseña\"}").status(Response.Status.ACCEPTED).build();
    }

    @POST
    @Secured({Role.user})
    @Path("/silenciarAlarma")
    public Response silenciarAlarma(@QueryParam("alarma") String alarma, @QueryParam("residenca") String residencia, @QueryParam("user") String user) throws Exception {
        System.out.println("Resourse");
        residenciaLogic.validarUsuario(user, residencia);
        for (int i = 0; i < 4; i++) {
            publish("silenciarAlarma;" + alarma);
        }
        
        return Response.ok().entity("{\"llego\":\"Silenciar alarma\"}").status(Response.Status.ACCEPTED).build();
    }

    @POST
    @Secured({Role.user})
    @Path("/cambiar")
    public Response updatePassword(@QueryParam("clave") String clave, @QueryParam("index") String index, @QueryParam("residenca") String residencia, @QueryParam("user") String user) throws Exception {
        System.out.println("Resourse");
        residenciaLogic.validarUsuario(user, residencia);
        for (int i = 0; i < 4; i++) {
            publish("actualizarClave;" + clave + ";" + index);
        }
        
        return Response.ok().entity("{\"llego\":\"Actualizar contraseña\"}").status(Response.Status.ACCEPTED).build();
    }

    @POST
    @Secured({Role.user})
    @Path("/borrar")
    public Response deletePassword(@QueryParam("index") String index, @QueryParam("residenca") String residencia, @QueryParam("user") String user) throws Exception {
        System.out.println("Resourse");
        residenciaLogic.validarUsuario(user, residencia);
        for (int i = 0; i < 4; i++) {
            publish("borrarClave;0;" + index);
        }
        
        return Response.ok().entity("{\"llego\":\"Actualizar contraseña\"}").status(Response.Status.ACCEPTED).build();
    }

    @POST
    //@Secured({Role.user})
    @Path("/borrartodas")
    public Response deleteAllPasswords() throws Exception {
        System.out.println("Resourse");
        for (int i = 0; i < 4; i++) {
            publish("borrarTodo;0;0");
        } 
        return Response.ok().entity("{\"llego\":\"Actualizar contraseña\"}").status(Response.Status.ACCEPTED).build();
    }

}
