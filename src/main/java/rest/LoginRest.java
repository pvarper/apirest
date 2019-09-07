package rest;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entity.Cliente;
import com.entity.Transaccion;
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
	@Path(RestPath.ACTUALIZAR_SALDO)
	public Response actualizarSaldoCliente(@QueryParam(RestPath.SALDO_ACTUAL) double saldoActual,
			@QueryParam(RestPath.MONTO_RETIRO_DEPOSITO) double montoRetiroOdeposito) {
		try {
			log.info("Se actualizara el saldo del cliente: "+saldoActual+","+montoRetiroOdeposito);
			Archivo archivo = new Archivo();
			Result respuesta = archivo.actualizarSaldoCliente(saldoActual, montoRetiroOdeposito);
			if (respuesta.getCode().equals(Code.OK)) {
					log.info(respuesta.getDescription()+" ,saldo: "+respuesta.getData());
					return Response.ok(respuesta.getData()).build();
				
			}
			log.info(respuesta.getDescription());
			return Response.ok(respuesta.getDescription()).build();
		} catch (Exception e) {
			log.error("Error al actualiza el saldo del cliente", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path(RestPath.ELIMINAR_CLIENTE)
	public Response eliminarClientePorLogin(@QueryParam(RestPath.LOGIN) String login) {
		try {
			log.info("Se eliminara el cliente: "+login);
			Archivo archivo = new Archivo();
			Result respuesta = archivo.eliminarClientePorLogin(login);
			if (respuesta.getCode().equals(Code.OK)) {
					log.info(respuesta.getDescription());
					return Response.ok(respuesta.getData()).build();
				
			}
			log.info(respuesta.getDescription());
			return Response.ok(respuesta.getDescription()).build();
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
			return Response.ok(respuesta.getDescription()).build();
		} catch (Exception e) {
			log.error("Error al guardar el cliente: ", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path(RestPath.SAVE_TRANSACCION)
	public Response guardarTransaccionesCliente(String jsonTransaccionCliente) {
		try {
			Archivo archivo = new Archivo();
			Transaccion transaccion = new Gson().fromJson(jsonTransaccionCliente, Transaccion.class);
			transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
			log.info("Se guardara la transaccion del cliente " + transaccion.getLogin());
			Result respuesta = archivo.guardarTransaccionesCliente(transaccion);
			if (respuesta.getCode().equals(Code.OK)) {
				if ((boolean) respuesta.getData()) {
					log.info(respuesta.getDescription());
					return Response.ok(respuesta.getData()).build();
				}
			}
			log.info(respuesta.getDescription());
			return Response.ok(respuesta.getDescription()).build();
		} catch (Exception e) {
			log.error("Error guardar la transaccion del cliente", e);
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
	
	@GET
	@Path(RestPath.OBTENER_TRANSACCION)
	public Response obtenerTrasanccionesClientePorLogin(@QueryParam(RestPath.LOGIN) String login) {
		try {
			log.info("Se obtendra las trsnsacciones del cliente " + login);
			Archivo archivo = new Archivo();
			Result respuesta = archivo.obtenerTrasanccionesClientePorLogin(login);
			if (respuesta.getCode().equals(Code.OK)) {

			  return Response.ok(respuesta.getData()).build();

			}
			log.info(respuesta.getDescription());
			return Response.ok(respuesta.getData()).build();
		} catch (Exception e) {
			log.error("Error al obtener las transacciones del cliente con el login: ", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	

}
