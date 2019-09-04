package com.file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArchivoTest {

	@Test
	void creacionDirectorioArchivoTest() {
		Archivo archivo = new Archivo();
		boolean inputFile=archivo.file.exists();
		boolean expectedFile=true;
		
		assertEquals(expectedFile,inputFile);
	}

}
