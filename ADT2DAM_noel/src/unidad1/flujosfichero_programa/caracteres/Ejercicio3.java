package unidad1.flujosfichero_programa.caracteres;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio3 {

	/* Leer un fichero línea a línea con BufferedReader */
	/* Escribir un fichero línea a línea con BufferedReader */
	
	public static void main(String[] args) {
		leerLineas(Paths.get("./LeerFichTexto.txt"));
		String[] lineas = new String[] {"Prueba de BufferedWriter.", "No pienses nada raro.", "Aprovecho estar 'on fire'.", "Hago estos versos y paro."};
		escribirLineas(Paths.get("./EscribirFichTexto.txt"), lineas);
	}
	
	private static boolean leerLineas(Path p){
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(p.toFile())))){
			if(!Files.exists(p))
				Files.createFile(p);
			
			String linea;
			while((linea= reader.readLine()) != null) {
				System.out.print(linea);
			}
			return true;
		}catch(IOException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean escribirLineas(Path p, String[] lineas) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(p.toFile()))){
			if(!Files.exists(p))
				Files.createFile(p);
			
			for(int i=0; i < lineas.length; i++) {
				writer.write(lineas[i]);
				writer.newLine(); //escribe un salto de linea 
			}
			
			//A esto se refería el ejercicio, pero vaya, lo de arriba debería estar bien
			for (int i=1; i<11; i++){ 
			     writer.write("Fila numero: "+i); //escribe una linea 
			     writer.newLine(); //escribe un salto de linea 
			}
			return true;
		}catch(IOException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}
	}
}
