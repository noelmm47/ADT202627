package unidad1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Propuesta4 {
	/* Intenta realizar el ejercicio que borre los ficheros que contiene el directorio y luego borre el directorio.
	 * Intentarlo también con NIO  */

	private static void borrar(Path ruta) throws IOException {
		if(Files.isDirectory(ruta)) 
			try(DirectoryStream<Path> stream= Files.newDirectoryStream(ruta)){
				for(Path p: stream) 
					borrar(p); //Si es un directorio, saldrá del método cuando borre todos sus ficheros
				
			}
		//Sea fichero, o carpeta (después de borrar su ficheros), se eliminará
		if(Files.deleteIfExists(ruta))
			System.out.println("Borrado exitosamente: " + ruta.toString());
	}
	
	private static void borrarNIO(Path ruta) throws IOException { //TODO
		if(Files.isDirectory(ruta)) 
			try(DirectoryStream<Path> stream= Files.newDirectoryStream(ruta)){
				for(Path p: stream) 
					borrar(p); //Si es un directorio, saldrá del método cuando borre todos sus ficheros
				
			}
		//Sea fichero, o carpeta (después de borrar su ficheros), se eliminará
		if(Files.deleteIfExists(ruta))
			System.out.println("Borrado exitosamente: " + ruta.toString());
	}
}
