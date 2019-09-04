package com.file;

import java.io.File;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class Archivo {

	private static final String nombreDirectorio = "ficheros";
	private static final String nombreArchivoUsuarios = "usuarios.txt";
	File file;
	
	public Archivo() {
		
		
	}

}
