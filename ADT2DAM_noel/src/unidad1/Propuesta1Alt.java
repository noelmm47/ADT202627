package unidad1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Propuesta1Alt {

	/* Comprobar la existencia de una ruta; si no existe, crear el fichero.
	 * Si existe, imprimir por pantalla si es directorio o fichero y su tamaño.
	 */
	public static void main(String[] args) {
		Path path = Paths.get("C:\\Users\\Tarde\\Datos\\Borrar\\p.txt"); //TODO Me da problemas la ruta
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
			solucionar(ruta);
			//Files.createFile(ruta);
			System.out.println("CREADO CON ÉXITO");
		}
	}
	
	private static void solucionar(Path p) throws IOException {
		String f= p.toAbsolutePath().toString();
		String[] parts= f.split("\\\\");
		if(parts[parts.length - 1].split(".").length == 2) { //Es el nombre de un archivo: "nombre.extensión"
			construirCarpetasYFichero(parts);
		}else { //Es una carpeta
			Files.createDirectories(p);
		}
		
	}
	
	private static void construirCarpetasYFichero(String[] parts) throws IOException {
		StringBuilder path= new StringBuilder();
		//Path building;
		
		for(int i=0; i<parts.length; i++) {
			if(i != parts.length -1) { //Si no es el archivo
				path.append(parts[i] + "\\");
			}else {
				Files.createDirectories(Paths.get(path.toString()));//Crea los directorios inexistentes
				Files.createFile(Paths.get(path.toString() + "\\" + parts[i])); //Crea el archivo
			}
		}
		
	/*	for(int i=0; i<parts.length; i++) {
			path.append(parts[i] + "\\");
			building= Paths.get(path.toString());
			if(!Files.exists(building)) { //Si ya no existe la ruta establecida, no existe a partir de la carpeta recién añadida
				for(int j=i; j<parts.length; j++) { //Voy creando paulatinamente todos los directorios
					if(j!=parts.length-1) { //Si no es el archivo
						
					}else {
						building
						Files.createDirectories(building); //Crea todos los directorios
						
					}
				}
			
			}
		}*/
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