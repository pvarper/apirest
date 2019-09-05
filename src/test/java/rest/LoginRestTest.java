package rest;

import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

class LoginRestTest {

	@Test
	void validarClintePorLoginTest() {
		LoginRest servicio= new LoginRest();
		Response respuesta=servicio.validarClientePorLogin("floresj");
		
		boolean responseExpected=true;
		
		assertEquals(responseExpected, respuesta.getEntity());
		
	}

}
