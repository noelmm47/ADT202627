package unidad1;

import java.io.File;

public class Ejercicio3 {
	
	/* Método que enliste el contenido de un directorio */
	
	public static void main(String[] args) {
		leerDirectorio(".");
	}
	
	private static void leerDirectorio(String path) {
		File dir = new File(path);
		
		if(dir.exists() && dir.isDirectory()) {
			File[] files= dir.listFiles();
			
			if(files!=null) {
				for(File file: files) {
					System.out.printf("[%s] %s", file.isDirectory() ? "DIRECTORIO":"FICHERO",
													file.getName());
					if(file.isFile())
						System.out.printf(" | Tamaño: %d Bytes\n", file.length());
				}
			}else {
				System.err.println("No se pudo encontrar o acceder al directorio");
			}
			
		}else {
			System.err.println("No existe o no es un directorio");
		}
	}

}
