package unidad1;

import java.io.File;
import java.io.IOException;

public class Ejercicio2 {
	
	/*
	* Crea el fichero si no existe y finaliza.
	* Si el fichero existe me indica la ruta absoluta y el tamaño del archivo en bytes
	*/
	
	public static void main(String[] args) {
		crearFichero("C:\\Users\\Tarde\\Downloads\\jaja.txt");
	}
	
	private static void crearFichero(String path) {
		File file= new File(path);
		
		if(file.exists()) {
			System.out.printf("RUTA: %s\t\tTAMAÑO(B): %d", file.getAbsolutePath(), file.length());
		}else{
			//Crea el archivo
			try {
				if(file.createNewFile()) //Crea un archivo a partir del path del file
					System.out.println("Se creó el archivo con éxito");
				else
					System.out.println("Hubo un problema y no se pudo crear el archivo");
			} catch (IOException e) {
				System.err.println("Error al crear el fichero: " + e.getMessage());
			}
		}
	}

}
