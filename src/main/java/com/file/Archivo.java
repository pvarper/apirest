package com.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.ws.rs.core.Response;

import com.entity.Cliente;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class Archivo {

	private static final String nombreDirectorio = "ficheros";
	private static final String nombreArchivoUsuarios = "usuarios.txt";
	public  File file;
	
	public Archivo() {
		try {
			log.info("si el directorio "+nombreDirectorio+" no existe, se procede a crearlo");
			this.file = new File(nombreDirectorio);
			if (!this.file.exists()) {
				this.file.mkdir();
				log.info("se creo el directorio "+nombreDirectorio);
				log.info("se va crear el archivo "+nombreArchivoUsuarios);
				FileWriter flwriter = new FileWriter(this.file.getAbsolutePath() + "\\" + nombreArchivoUsuarios);
				log.info("se creo el archivo "+nombreArchivoUsuarios);
				flwriter.close();
			}
			this.file=new File(nombreDirectorio+"\\"+nombreArchivoUsuarios);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[Archivo] error al crear el directorio y el archivo usuarios",e);
		}
		
	}
	
	public Response guardarCliente(Cliente cliente) {
		log.info("Se validara que el login "+cliente.getLogin()+" no exista para guardarlo");
		Response response = validarSiExisteLogindeUsuario(cliente.getLogin());
		if ((boolean)response.getEntity()) {		
			return Response.ok("No se guardo el usuario " + cliente.getLogin()+", por que este ya existe").build();
		}
		log.info("el login "+cliente.getLogin()+" no existe, se procedera a guardarlo");
		FileWriter flwriter = null;
		try {
			flwriter = new FileWriter(nombreDirectorio + "\\" + nombreArchivoUsuarios, true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write(cliente.getCi() + "," + cliente.getLogin() + "," + cliente.getPassword() + ","
					+ cliente.getNombre() + "," + cliente.getApellidos() + "," + cliente.getTelefono() + ","
					+ cliente.getSaldo() + "\n");

			bfwriter.close();
			log.info("Se guardo correctamente el login " + cliente.getLogin());
			return Response.ok("Se guardo correctamente el usuario " + cliente.getLogin()).build();

		} catch (IOException e) {
			log.error("error al guardar el login "+cliente.getLogin(),e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Response validarSiExisteLogindeUsuario(String login) {
		
		log.info("Se procedera a validar el login: "+login);
		Scanner scanner = null;
		
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
					log.info("Se valido que el login "+login +" ya existe");
					return Response.ok(true).build();
				}
				cliente = new Cliente();

			}
			log.info("Se valido que el login "+login +" NO existe");
			return Response.ok(false).build();
		} catch (Exception e) {		
			log.error("Error al validar el login "+login,e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}finally {
			if (file != null) {
				file = null;
			}
			if (scanner != null) {
				scanner.close();
			}
		}
		
		
		
	}
	
	public Response obtenerClientePorLogin(String login) {
		
		log.info("Se va obtener el cliente con el login: "+login);
		File file = new File(nombreDirectorio + "\\" + nombreArchivoUsuarios);
		Scanner scanner = null;
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
					log.info("Se obtuvo correctamente el cliente con el login: "+login);
					return Response.ok(cliente).build();
				}
				cliente = new Cliente();

			}
			scanner.close();
			log.info("El cliente con el login "+login +" NO existe");
			return Response.status(Response.Status.BAD_REQUEST).entity("El cliente con el login "+login +" NO existe").build();

		} catch (FileNotFoundException e) {
			log.error("Error al obtener cliente con el login "+login,e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} finally {
			if (file != null) {
				file = null;
			}
			if (scanner != null) {
				scanner.close();
			}
		}

	}
	
	public Response eliminarClientePorLogin(String login) {
		
		return Response.ok(false).build();
	}
	

}
