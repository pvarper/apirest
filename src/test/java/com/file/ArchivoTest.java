package com.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.entity.Cliente;
import com.result.Code;
import com.result.Result;

class ArchivoTest {

	@Test
	void creacionDirectorioArchivoTest() {
		Archivo archivo = new Archivo();
		boolean inputFile = archivo.file.exists();
		boolean expectedFile = true;

		assertEquals(expectedFile, inputFile);
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
		Result respuesta = archivo.guardarCliente(cliente);

		// String expectedRespuesta="Se guardo correctamente el usuario " +
		// cliente.getLogin();
		String expectedRespuesta = Code.OK;

		assertEquals(expectedRespuesta, respuesta.getCode());
	}

	@Test
	void validarSiExisteLogindeUsuarioTest() {
		Archivo archivo = new Archivo();

		Result respuesta = archivo.validarSiExisteLogindeUsuario("floresj");

		boolean expectedRespuesta = true;

		assertEquals(expectedRespuesta, respuesta.getData());
	}

	@Test
	void eliminarClientePorLoginTest() {

		Archivo archivo = new Archivo();

		Result respuesta = archivo.eliminarClientePorLogin("vargasped");
		String expectedRespuesta = Code.OK;

		assertEquals(expectedRespuesta, respuesta.getCode());
	}
	
	@Test
	void actualizarSaldoClienteTest() {

		Archivo archivo = new Archivo();

		Result respuesta = archivo.actualizarSaldoCliente(100, -75);
		
		double expectedRespuesta = 25;

		assertEquals(expectedRespuesta, respuesta.getData());
	}

}
