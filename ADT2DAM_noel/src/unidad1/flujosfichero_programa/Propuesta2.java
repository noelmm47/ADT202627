package unidad1.flujosfichero_programa;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Propuesta2 {
	/*
	 * Un programa que escriba en un fichero los caracteres de un String, de uno en uno.
	 * Si el fichero no existe, lo crea.
	 * 
	 * Añade otra línea al fichero de modo que se escriba todo el array (utiliza fic.write(cad)) 
	 * Añade otra línea para que escriba un String.(igual que para el array 
	 * Añade  el contenido de un array de String (utiliza fic.write (nombarray[i])) 
	 */
	
	public static void main(String[] args) {
		try {
			escribirCharAChar(Paths.get("./EscribirFichTexto.txt"), "Esto es una prueba.");
		}catch(Exception e) {
			System.err.println("ERROR: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	private static boolean escribirCharAChar(Path p, String escribir) {
		try(FileWriter writer= new FileWriter(p.toFile())) {
			if(!Files.exists(p))
				Files.createFile(p);
			
			char[] charArray= escribir.toCharArray();
			for(char c: charArray) {
				writer.write(c); //TODO ¿Lo añade como un append?
			}
			writer.append('*');
			writer.append('\n');
			//Escribir todo el array de una
			writer.write(charArray);
			writer.write("*\n");
			//Escribir todo el String directamente
			writer.write(escribir);
			writer.write("*\n");
			//Escribir todo el contenido de un array de String
			String[] stringArray= new String[] {escribir, " De escritura de un array", " de Strings", ".","*\n"};
			for(int i=0; i<stringArray.length; i++) {
				writer.write(stringArray[i]);
			}
			
			return true;
		}catch(IOException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage());
			return false;
		}
	}
}
