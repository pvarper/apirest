package rest;



import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.file.Archivo;
import com.result.Code;
import com.result.Result;

import lombok.extern.jbosslog.JBossLog;
import rest.configuration.path.RestPath;


@Path(RestPath.REST_LOGIN)
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@JBossLog
public class LoginRest implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GET
    @Path(RestPath.VALIDATE)
    public Response validarClientePorLogin(@QueryParam(RestPath.LOGIN) String login) {
        try {
        	log.info("Se validara el cliente con el login "+login);
        	Archivo archivo= new Archivo();
        	Result respuesta= archivo.validarSiExisteLogindeUsuario(login);
        	if(respuesta.getCode().equals(Code.OK)) {
        		if((boolean)respuesta.getData()) {
        			log.info(respuesta.getDescription());
        			return Response.ok(respuesta.getData()).build();
        		}
        	}
        	log.info(respuesta.getDescription());
            return Response.ok(respuesta.getData()).build();
        } catch (Exception e) {
        	log.error("Error al validar el cliente con el login: "+login,e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
	
	@POST
    @Path(RestPath.SAVE)
    public Response guardarCliente(String jsonCliente) {
        try {
        	log.info("Se guardara el cliente");
        	
            return Response.ok(false).build();
        } catch (Exception e) {
        	log.error("Error al validar el cliente con el login: ",e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
