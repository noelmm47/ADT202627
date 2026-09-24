package unidad1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Propuesta1 {

	/* Comprobar la existencia de una ruta; si no existe, crear el fichero.
	 * Si existe, imprimir por pantalla si es directorio o fichero y su tamaño.
	 */
	public static void main(String[] args) {
		Path path = Paths.get("C:/Users/noelg/Documents/Borrar/t.txt"); //TODO Me da problemas la ruta
		try {
			comprobar(path);
			recorrer(path);
		}catch(NotDirectoryException e) {
			System.err.println("La ruta pasada no es de un directorio");
		}catch(IOException e) {
			System.err.println("Hubo un problema de E/S: " + e.getLocalizedMessage());
		}
	}
	
	private static void comprobar(Path ruta) throws IOException{
		if(Files.exists(ruta)) {
			System.out.println("EXISTE");
		}else {
			System.out.println("NO EXISTE\nCREANDO.......");
			Files.createFile(ruta);
			System.out.println("CREADO CON ÉXITO");
		}
	}
	
	private static void recorrer(Path ruta) throws IOException {
		try(DirectoryStream<Path> stream= Files.newDirectoryStream(ruta)){
			for(Path p: stream)
				if(Files.isDirectory(p))
					System.out.println("[DIR]: " + p.getFileName());
				else 
					System.out.println("[FICHERO]: " + p.getFileName() + "//// Tamaño: " + Files.size(p) + " bytes");
		}
	}
}
