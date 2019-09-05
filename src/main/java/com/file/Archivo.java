package com.file;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.ws.rs.core.Response;

import com.entity.Cliente;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class Archivo {

	private static final String nombreDirectorio = "ficheros";
	private static final String nombreArchivoUsuarios = "usuarios.txt";
	File file;
	
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
		
		return Response.ok("Se guardo correctamente").build();
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
					log.info("Se valido que el login "+login +"existe");
					return Response.ok(true).build();
				}
				cliente = new Cliente();

			}
			log.info("Se valido que el login "+login +"NO existe");
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

}
