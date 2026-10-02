package unidad1.flujosfichero_programa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Propuesta1_Ejercicio2 {

	/* Lectura por bloque de 30 caracteres */
	
	public static void main(String[] args) {
		try {
			leerBloque(Paths.get("./LeerFichTexto.txt"));
		}catch(IOException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	private static boolean leerBloque(Path p) throws IOException {
		try(FileReader reader = new FileReader(p.toFile())){
			/*int bloque = 30;
			while(bloque > 0 && reader.ready()) {
				System.out.print(reader.read());
				--bloque;
			}
			if(bloque > 0) { //El reader se detuvo o no había 30 caracteres
				System.err.println("Carácteres que faltan por leer: " + bloque);
			}*/
			int num; 
			char buffer[]= new char[30];  
			while ((num = reader.read(buffer)) != -1) { //Lee caracteres hasta llenar el array, eso es lectura en bloques
			   //System.out.println(buffer); No funciona, porque lee incluso si el buffer no tiene 30 caracteres
			   // para que limpie el buffer en cada pasada 
			   System.out.print(new String(buffer, 0, num));  //Funciona, num es el número de caracteres leídos, así no se leen posiciones vacías
			}
			return true;
		}catch(FileNotFoundException e) {
			System.err.println("No se encontró el archivo: " + e.getLocalizedMessage());
			return false;
		}
	}
}
