package unidad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class Actividad1 {

	/* Realiza un programa Java que utilizando el método list() de Files, muestre los ficheros de un directorio. El nombre del
	directorio se pasará al programa desde los argumentos de main().Si no hay argumento se muestra el mensaje "HAY
	QUE INTRODUCIR UN ARGUMENTO....". Si el directorio no existe se debe mostrar un mensaje indicándolo
	"DIRECTORIO INEXISTENTE" */
	
	public static void main(String[] args) {
		String path = args[0];
		
		if(path == null)
			exit("HAY QUE INTRODUCIR UN ARGUMENTO....");
		
		File f = new File(path);
		if(!f.exists() || !f.isDirectory()) 
			exit("DIRECTORIO INEXISTENTE");
		
		try {
			mostrarFicheros(f);
		} catch (IOException e) {
			System.err.println("Error de I/O: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}
	
	private static void exit(String message) {
		System.err.println(message);
		System.exit(0);
	}
	
	private static void mostrarFicheros(File dir) throws IOException{
		
		Stream<Path> stream = Files.list(dir.toPath());
		stream.forEach((p) -> {
			try {
				BasicFileAttributes att= Files.readAttributes(p, BasicFileAttributes.class);
				System.out.println("Nombre: " + p.getFileName());
				System.out.println("Dirección: " + p);
				System.out.println("Dirección absoluta: " + p.toAbsolutePath());
				System.out.println("Se puede leer: " + Files.isReadable(p));
				System.out.println("Se puede escribir: " + Files.isWritable(p));
				System.out.println("Se puede ejecutar: " + Files.isExecutable(p));
				System.out.println("Está oculto: " + Files.isHidden(p));
				System.out.println("Directorio padre: " + p.getParent().relativize(p));
				
				//System.out.println(Files.isDirectory(p) ? "Directorio":("Fichero\nBytes: " + att.size());
				if(Files.isDirectory(p))
					System.out.println("Es Directorio");
				else {
					System.out.println("Es Fichero");
					System.out.println("Bytes: " + att.size());
				}
				
				System.out.println("Fecha de creación: " + att.creationTime());
				System.out.println("Fecha de modificación: " + att.lastModifiedTime());
				System.out.println("Último acceso: " + att.lastAccessTime());
			}catch(IOException e) {
				System.err.println("Error de I/O: " + e.getLocalizedMessage());
			}
		});
		
		stream.close();
	}
}
