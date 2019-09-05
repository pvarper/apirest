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
		cliente.setCi("5356175");
		cliente.setLogin("vargasped");
		cliente.setPassword("tele123");
		cliente.setNombre("Pedro Vargas");
		cliente.setApellidos("Vargas Pereira");
		cliente.setTelefono("77802564");
		cliente.setSaldo(20.54);
		Response respuesta=archivo.guardarCliente(cliente);

		String expectedRespuesta="Se guardo correctamente el usuario " + cliente.getLogin();
		
		assertEquals(expectedRespuesta,respuesta.getStatusInfo().getReasonPhrase());
	}
	
	@Test
	void validarSiExisteLogindeUsuarioTest() {
		Archivo archivo = new Archivo();

		Response respuesta=archivo.validarSiExisteLogindeUsuario("vargasped");

		boolean expectedRespuesta=false;
		
		assertEquals(expectedRespuesta,respuesta.getEntity());
	}

}
