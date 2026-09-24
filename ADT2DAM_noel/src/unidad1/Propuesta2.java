package unidad1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Propuesta2 {

	/* Método que compruebe la existencia de un archivo, creándolo si no existe, y 
	 * listando su contenido de ser un directorio */
	
	public static void main(String[] args) {
		mirarArchivo(args[0]);
	}
	
	private static void mirarArchivo(String ruta) {
		//Comprobar si existe
		Path p= Paths.get(ruta);
		
		try {
			if(Files.exists(p)) {
				System.out.printf("El archivo existe.");
				if(Files.isRegularFile(p))
					System.out.printf("Tamaño en bytes: %d\n", Files.size(p));
			}else
				crearArchivo(p);
			
			//Listar
			if(Files.isDirectory(p))
				listarDirectorio(p);
			
		}catch(IOException e) {
			System.err.printf("Ha ocurrido un error: %s", e.getMessage());
		}
	}

	private static void crearArchivo(Path p) throws IOException {
		System.out.println("El archivo no existe.\nCreando.....");
		Files.createFile(p);
		System.out.println("¡Archivo creado con éxito!");
	}
	
	private static void listarDirectorio(Path p) throws IOException {
		if(!Files.exists(p)) 
			crearArchivo(p);
		
		try(DirectoryStream<Path> stream= Files.newDirectoryStream(p)){
			for(Path file: stream) {
				System.out.printf("[%s] NOMBRE: %s /// ", 
											Files.isDirectory(file) ? "DIR":"FICH",
											file.getFileName());
				if(Files.isRegularFile(file))
					System.out.printf("TAMAÑO: %d", Files.size(file));
			}
		}
	}
}
