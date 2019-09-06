package rest;

import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import com.entity.Cliente;
import com.google.gson.Gson;

class LoginRestTest {

	@Test
	void validarClientePorLoginTest() {
		LoginRest servicio= new LoginRest();
		Response respuesta=servicio.validarClientePorLogin("floresj");
		
		boolean responseExpected=true;
		
		assertEquals(responseExpected, respuesta.getEntity());
		
	}
	
	@Test
	void guardarClienteTest() {
		LoginRest servicio= new LoginRest();
		Cliente cliente = new Cliente();
		cliente.setCi("5456175");
		cliente.setLogin("vargasped");
		cliente.setPassword("tele123");
		cliente.setNombre("Julio");
		cliente.setApellidos("Flores");
		cliente.setTelefono("77809878");
		cliente.setSaldo(32.54);
		Response respuesta=servicio.guardarCliente(new Gson().toJson(cliente));
		
		boolean responseExpected=true;
		
		assertEquals(responseExpected, respuesta.getEntity());
		
	}
	
	@Test
	void obtenerClientePorLoginTest() {
		LoginRest servicio= new LoginRest();
		Response respuesta=servicio.obtenerClientePorLogin("vargasped");
		Cliente cliente=(Cliente)respuesta.getEntity();
		
		String responseExpected="77809878";
		
		assertEquals(responseExpected, cliente.getTelefono());
		
	}
	
	@Test
	void validarClienteCredencialesTest() {
		LoginRest servicio= new LoginRest();
		Response respuesta=servicio.validarClienteCredenciales("vargasped","tele123");
		
		boolean responseExpected=true;
		
		assertEquals(responseExpected, respuesta.getEntity());
		
	}
	
	@Test
	void actualizarSaldoClienteTest() {
		LoginRest servicio= new LoginRest();
		Response respuesta=servicio.actualizarSaldoCliente(500,200);
		
		double responseExpected=700;
		
		assertEquals(responseExpected, respuesta.getEntity());
		
	}
	
	@Test
	void eliminarClientePorLoginTest() {
		LoginRest servicio= new LoginRest();
		Response respuesta=servicio.eliminarClientePorLogin("vargasped");
		
		boolean responseExpected=true;
		
		assertEquals(responseExpected, respuesta.getEntity());
		
	}

}
