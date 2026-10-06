package unidad1.flujosfichero_programa.binario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio2 {

	/** Escribir datos primitivos
	 * Escribir y leer datos primitivos
	 */
	static String[] nombres= new String[] {"Ana", "Beatriz", "Cecilia", "Diego", "Ernesto", "Félix"};
	static int[] edades = new int[] {1, 39, 109, 75, 47, 12};
	
	public static void main(String[] args) { //TODO probar
		Path ruta= Paths.get(Ejercicio1.rutas[1]+"fichBinario.dat");
		
		try {
			lecturaEscrituraPrimitivas(ruta);
		}catch(FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero: " + e.getLocalizedMessage());
			e.printStackTrace();
		}catch(IOException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	private static void lecturaEscrituraPrimitivas(Path path) throws IOException {
		if(!Files.exists(path)) {
			System.out.println("Archivo inexistente.\nCreando...");
			Files.createFile(path);
			Files.setAttribute(path, "read", true);
			Files.setAttribute(path, "write", true);
			Files.setAttribute(path, "execute", true);
		}

		try(DataOutputStream out= new DataOutputStream(new FileOutputStream(path.toString()))){
			for(int i=0; i<edades.length; i++) {
				out.writeUTF(nombres[i]);
				out.writeInt(edades[i]);
			}
			System.out.println("Escritura terminada con éxito.");
		}
		
		try(DataInputStream in = new DataInputStream(new FileInputStream(path.toString()))){
			while(in.available() > 0) { //Va por pares nombre - edad
				System.out.println(in.readUTF());
				System.out.println(in.read());
			}
		}
	}
}
