package com.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.entity.Cliente;
import com.entity.Transaccion;
import com.result.Code;
import com.result.Result;

class ArchivoTest {

	@Test
	void creacionDirectorioArchivoTest() {
		Archivo archivo = new Archivo();
		boolean inputFile = archivo.fileUsuario.exists();
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

		Result respuesta = archivo.validarSiExisteLogindeUsuario("vargasped");

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
	
	@Test
	void validarClienteCredencialesTest() {
		Archivo archivo = new Archivo();

		Result respuesta = archivo.validarClienteCredenciales("padillas","tele123");

		boolean expectedRespuesta = true;

		assertEquals(expectedRespuesta, respuesta.getData());
	}
	
	@Test
	void guardarTransaccionClienteTest() {
		Archivo archivo = new Archivo();
		Transaccion transaccion = new Transaccion();
		transaccion.setLogin("floresj");
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setDeposito(0.0);
		transaccion.setRetiro(3);
		transaccion.setSaldo(1.0);
		Result respuesta = archivo.guardarTransaccionesCliente(transaccion);

		// String expectedRespuesta="Se guardo correctamente el usuario " +
		// cliente.getLogin();
		String expectedRespuesta = Code.OK;

		assertEquals(expectedRespuesta, respuesta.getCode());
	}
	
	@Test
	void obtenerTrasanccionesClientePorLoginTest() {
		Archivo archivo = new Archivo();
		Result respuesta = archivo.obtenerTrasanccionesClientePorLogin("floresj");
		List<Transaccion> listaTransacciones=(List<Transaccion>) respuesta.getData();
		int expectedRespuesta = 2;

		assertEquals(expectedRespuesta, listaTransacciones.size());
	}

}
