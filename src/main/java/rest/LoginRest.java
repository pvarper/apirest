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

import com.entity.Cliente;
import com.file.Archivo;
import com.google.gson.Gson;
import com.result.Code;
import com.result.Result;

import lombok.extern.jbosslog.JBossLog;
import rest.configuration.path.RestPath;

@Path(RestPath.REST_LOGIN)
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@JBossLog
public class LoginRest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GET
	@Path(RestPath.VALIDATE_LOGIN)
	public Response validarClientePorLogin(@QueryParam(RestPath.LOGIN) String login) {
		try {
			log.info("Se validara el cliente con el login " + login);
			Archivo archivo = new Archivo();
			Result respuesta = archivo.validarSiExisteLogindeUsuario(login);
			if (respuesta.getCode().equals(Code.OK)) {
				if ((boolean) respuesta.getData()) {
					log.info(respuesta.getDescription());
					return Response.ok(respuesta.getData()).build();
				}
			}
			log.info(respuesta.getDescription());
			return Response.ok(respuesta.getData()).build();
		} catch (Exception e) {
			log.error("Error al validar el cliente con el login: " + login, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(RestPath.VALIDATE_CREDENCIALES)
	public Response validarClienteCredenciales(@QueryParam(RestPath.LOGIN) String login,
			@QueryParam(RestPath.PASSWORD) String password) {
		try {

			log.info("Se validara las credenciales del cliente con el login " + login);
			Archivo archivo = new Archivo();
			Result respuesta = archivo.validarClienteCredenciales(login, password);
			if (respuesta.getCode().equals(Code.OK)) {
				if ((boolean) respuesta.getData()) {
					log.info(respuesta.getDescription());
					return Response.ok(respuesta.getData()).build();
				}
			}
			log.info(respuesta.getDescription());
			return Response.ok(respuesta.getData()).build();
		} catch (Exception e) {
			log.error("Error al validar las credenciales el cliente con el login: " + login, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(RestPath.VALIDATE_CREDENCIALES)
	public Response actualizarSaldoCliente(@QueryParam(RestPath.SALDO_ACTUAL) double saldoActual,
			@QueryParam(RestPath.MONTO_RETIRO_DEPOSITO) double montoRetiroOdeposito) {
		try {

			
			return Response.ok(1).build();
		} catch (Exception e) {
			log.error("Error al actualiza el saldo del cliente", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(RestPath.SAVE)
	public Response guardarCliente(String jsonCliente) {
		try {
			Archivo archivo = new Archivo();
			Cliente cliente = new Gson().fromJson(jsonCliente, Cliente.class);
			log.info("Se guardara el cliente " + cliente.getLogin());
			Result respuesta = archivo.guardarCliente(cliente);
			if (respuesta.getCode().equals(Code.OK)) {
				if ((boolean) respuesta.getData()) {
					log.info(respuesta.getDescription());
					return Response.ok(respuesta.getData()).build();
				}
			}
			log.info(respuesta.getDescription());
			return Response.ok(respuesta.getData()).build();
		} catch (Exception e) {
			log.error("Error al validar el cliente con el login: ", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(RestPath.OBTENER_CLIENTE)
	public Response obtenerClientePorLogin(@QueryParam(RestPath.LOGIN) String login) {
		try {
			log.info("Se obtendra el cliente " + login);
			Archivo archivo = new Archivo();
			Result respuesta = archivo.obtenerClientePorLogin(login);
			if (respuesta.getCode().equals(Code.OK)) {
				if ((Cliente) respuesta.getData() != null) {
					log.info(respuesta.getDescription());
					return Response.ok(respuesta.getData()).build();
				}
			}
			log.info(respuesta.getDescription());
			return Response.ok(respuesta.getData()).build();
		} catch (Exception e) {
			log.error("Error al validar el cliente con el login: ", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
