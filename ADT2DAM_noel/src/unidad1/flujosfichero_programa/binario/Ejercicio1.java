package unidad1.flujosfichero_programa.binario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio1 {
	/** Un programa que escriba a un fichero binario y luego lo lea */
	
	
	public static String[] rutas= new String[] {"C:/Users/Tarde/Datos/Borrar", "C:/Users/noelg/Borrar"};

	public static void main(String[] args) { //TODO probar
		Path ruta= Paths.get(rutas[1]+"fichBinario.dat");
		
		try {
			lecturaEscritura(ruta);
		}catch(FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero: " + e.getLocalizedMessage());
			e.printStackTrace();
		}catch(IOException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	private static void lecturaEscritura(Path ruta) throws FileNotFoundException, IOException {
		File file;
		
		if(!Files.exists(ruta)) {
			System.out.println("La ruta especificada no existe.\nCreando....");
			file= Files.createFile(ruta).toFile();
			System.out.println("¡Creado con éxito!");
		}else {
			file= ruta.toFile();
		}
		
		//Escritura
		try(FileOutputStream out= new FileOutputStream(file, true)){ //También sirve para el constructor ruta.toString()
			for(int i=0; i<100; i++) {
				out.write(i); //Escribe el dato int i hasta que alcance 99
			}
			System.out.println("Escritura terminada.");
		} //cerrado automático
		
		//Lectura
		try(FileInputStream in = new FileInputStream(file)){
			int dato;
			while((dato= in.read())!=-1) //TODO Si hubiese escrito negativos, ¿Cómo sería?
				System.out.println(dato);
		} //cerrado automático
	}

}
