package com.file;

import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import com.entity.Cliente;

class ArchivoTest {

	@Test
	void creacionDirectorioArchivoTest() {
		Archivo archivo = new Archivo();
		boolean inputFile=archivo.file.exists();
		boolean expectedFile=true;
		
		assertEquals(expectedFile,inputFile);
	}
	
	@Test
	void guardarClienteTest() {
		Archivo archivo = new Archivo();
		Cliente cliente = new Cliente();
		cliente.setCi("5456175");
		cliente.setLogin("floresj");
		cliente.setPassword("tele123");
		cliente.setNombre("Julio");
		cliente.setApellidos("Flores");
		cliente.setTelefono("77809878");
		cliente.setSaldo(32.54);
		Response respuesta=archivo.guardarCliente(cliente);

		//String expectedRespuesta="Se guardo correctamente el usuario " + cliente.getLogin();
		String expectedRespuesta="No se guardo el usuario " + cliente.getLogin()+", por que este ya existe";
		
		assertEquals(expectedRespuesta,respuesta.getEntity());
	}
	
	@Test
	void validarSiExisteLogindeUsuarioTest() {
		Archivo archivo = new Archivo();

		Response respuesta=archivo.validarSiExisteLogindeUsuario("vargasped");

		boolean expectedRespuesta=true;
		
		assertEquals(expectedRespuesta,respuesta.getEntity());
	}

}
