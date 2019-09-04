package com.file;

import java.io.File;
import java.io.FileWriter;

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

		} catch (Exception e) {
			e.printStackTrace();
			log.error("[Archivo] error al crear el directorio y el archivo usuarios",e);
		}
		
	}
	
	public Response guardarCliente(Cliente cliente) {
		
		return Response.ok("Se guardo correctamente").build();
	}

}
