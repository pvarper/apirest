package com.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.entity.Cliente;
import com.result.Result;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class Archivo {

	private static final String nombreDirectorio = "ficheros";
	private static final String nombreArchivoUsuarios = "usuarios.txt";
	public File file;
	Result respuesta;

	public Archivo() {
		try {
			log.info("si el directorio " + nombreDirectorio + " no existe, se procede a crearlo");
			this.file = new File(nombreDirectorio);
			if (!this.file.exists()) {
				this.file.mkdir();
				log.info("se creo el directorio " + nombreDirectorio);
				log.info("se va crear el archivo " + nombreArchivoUsuarios);
				FileWriter flwriter = new FileWriter(this.file.getAbsolutePath() + "\\" + nombreArchivoUsuarios);
				log.info("se creo el archivo " + nombreArchivoUsuarios);
				flwriter.close();
			}
			this.file = new File(nombreDirectorio + "\\" + nombreArchivoUsuarios);
			log.info("path ficheros: "+this.file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[Archivo] error al crear el directorio y el archivo usuarios", e);
		}

	}

	public Result guardarCliente(Cliente cliente) {
		log.info("Se guardara el cliente " + cliente.getLogin());
		FileWriter flwriter = null;
		respuesta = new Result();

		try {

			flwriter = new FileWriter(nombreDirectorio + "\\" + nombreArchivoUsuarios, true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write(cliente.getCi() + "," + cliente.getLogin() + "," + cliente.getPassword() + ","
					+ cliente.getNombre() + "," + cliente.getApellidos() + "," + cliente.getTelefono() + ","
					+ cliente.getSaldo() + "\n");

			bfwriter.close();
			log.info("Se guardo correctamente el login " + cliente.getLogin());
			respuesta.ok("Se guardo correctamente el login " + cliente.getLogin(), true);
			return respuesta;

		} catch (Exception e) {

			log.error("Error al guardar el cliente", e);
			respuesta.error("Error al guardar el cliente");
			return respuesta;
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					log.error("Error al cerrar el bufferedwrite", e);

				}
			}
		}
	}

	public Result validarSiExisteLogindeUsuario(String login) {

		log.info("Se procedera a validar el login: " + login);
		Scanner scanner = null;
		respuesta = new Result();
		try {
//			log.info("path del file: "+this.file.getAbsolutePath());
			scanner = new Scanner(this.file);
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				Scanner delimitar = new Scanner(linea);
				delimitar.useDelimiter("\\s*,\\s*");
				Cliente cliente = new Cliente();
				cliente.setCi(delimitar.next());
				cliente.setLogin(delimitar.next());
				cliente.setPassword(delimitar.next());
				cliente.setNombre(delimitar.next());
				cliente.setApellidos(delimitar.next());
				cliente.setTelefono(delimitar.next());
				cliente.setSaldo(Double.valueOf(delimitar.next()));
				delimitar.close();
				if (login.equalsIgnoreCase(cliente.getLogin())) {
					log.info("Se valido que el login " + login + " ya existe");
					respuesta.ok("El login existe", true);
					return respuesta;
				}
				cliente = new Cliente();

			}
			log.info("Se valido que el login " + login + " NO existe");
			respuesta.ok("El login no existe", false);
			return respuesta;
		} catch (Exception e) {
			log.error("Error al validar el login " + login, e);
			respuesta.error("Error al validar el login");
			return respuesta;
		} finally {
			if (file != null) {
				file = null;
			}
			if (scanner != null) {
				scanner.close();
			}
		}

	}

	public Result obtenerClientePorLogin(String login) {

		log.info("Se va obtener el cliente con el login: " + login);
		File file = new File(nombreDirectorio + "\\" + nombreArchivoUsuarios);
		Scanner scanner = null;
		respuesta = new Result();
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				Scanner delimitar = new Scanner(linea);
				delimitar.useDelimiter("\\s*,\\s*");
				Cliente cliente = new Cliente();
				cliente.setCi(delimitar.next());
				cliente.setLogin(delimitar.next());
				cliente.setPassword(delimitar.next());
				cliente.setNombre(delimitar.next());
				cliente.setApellidos(delimitar.next());
				cliente.setTelefono(delimitar.next());
				cliente.setSaldo(Double.valueOf(delimitar.next()));
				delimitar.close();
				if (login.equalsIgnoreCase(cliente.getLogin())) {
					log.info("Se obtuvo correctamente el cliente con el login: " + login);
					respuesta.ok("Se obtuvo correctamente el cliente con el login: " + login, cliente);
					return respuesta;
				}
				cliente = new Cliente();

			}
			scanner.close();
			log.info("El cliente con el login " + login + " NO existe");
			respuesta.ok("El cliente con el login " + login + " NO existe");
			return respuesta;

		} catch (FileNotFoundException e) {
			log.error("Error al obtener cliente con el login " + login, e);
			respuesta.error("Error al obtener cliente con el login " + login);
			return respuesta;
		} finally {
			if (file != null) {
				file = null;
			}
			if (scanner != null) {
				scanner.close();
			}
		}

	}

	public Result eliminarClientePorLogin(String login) {
		log.info("Se eliminar el login " + login);
		respuesta = new Result();
		try {
			File file = new File(nombreDirectorio + "\\" + nombreArchivoUsuarios);
			File tempFile = new File(nombreDirectorio + "\\" + nombreArchivoUsuarios + ".tmp");
			if (tempFile.exists()) {
				if (!tempFile.delete()) {
					log.info("No se pudo Eliminar el archivo temporal de usuarios: " + tempFile.getAbsolutePath());
					respuesta.error(
							"No se pudo Eliminar el archivo temporal de usuarios: " + tempFile.getAbsolutePath());
					return respuesta;
				}
			}
			FileReader fread = new FileReader(nombreDirectorio + "\\" + nombreArchivoUsuarios);
			BufferedReader br = new BufferedReader(fread);
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!deStringaObjeto(line).getLogin().equals(login)) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			pw = null;
			br.close();
			br = null;
			fread.close();
			fread = null;
			if (file.exists()) {
				if (!file.delete()) {
					log.info("No se pudo Eliminar el archivo  de usuarios: " + tempFile.getAbsolutePath());
					respuesta.error("No se pudo Eliminar el archivo  de usuarios: " + tempFile.getAbsolutePath());
					return respuesta;
				}
			}
			if (!tempFile.renameTo(file)) {
				log.info("No se pudo renombrar el archivo temporal de usuarios: " + tempFile.getAbsolutePath());
				respuesta.error("No se pudo renombrar el archivo temporal de usuarios: " + tempFile.getAbsolutePath());
				return respuesta;
			}
			log.info("Se Elimino correctamente el usuario: " + login);
			respuesta.ok("Se Elimino correctamente el usuario: " + login);
			return respuesta;

		} catch (FileNotFoundException ex) {
			log.error("Error al eliminar el cliente " + login, ex);
			respuesta.error("Error al eliminar el cliente " + login);
			return respuesta;

		} catch (IOException ex) {
			log.error("Error al eliminar el cliente " + login, ex);
			respuesta.error("Error al eliminar el cliente " + login);
			return respuesta;
		}

	}
	
	public Result actualizarSaldoCliente(double saldo, double montoDepositoORetiro) {
		try {


			respuesta= new Result();
			double saldoRestante = saldo + montoDepositoORetiro;
			if (saldoRestante < 0) {
				log.warn("Saldo insuficiente, el monto a retirar es mayor al saldo actual");
				respuesta.error("Saldo insuficiente, el monto a retirar es mayor al saldo actual");
				return respuesta;
			}

			
			log.info("Se actualizo el saldo correctamente");
			respuesta.ok("Se actualizo el saldo correctamente",saldoRestante);
			return respuesta;
		} catch (Exception e) {
			log.error("error al actualizar el saldo",e);
			respuesta.error("error al actualizar el saldo");
			return respuesta;
		}

		
	}

	public Cliente deStringaObjeto(String linea) {
		Cliente cliente = new Cliente();
		Scanner delimitar = new Scanner(linea);
		delimitar.useDelimiter("\\s*,\\s*");
		cliente.setCi(delimitar.next());
		cliente.setLogin(delimitar.next());
		cliente.setPassword(delimitar.next());
		cliente.setNombre(delimitar.next());
		cliente.setApellidos(delimitar.next());
		cliente.setTelefono(delimitar.next());
		cliente.setSaldo(Double.valueOf(delimitar.next()));
		delimitar.close();
		return cliente;
	}
}
