package unidad1.flujosfichero_programa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio1 {

	/**
	 * Leen cada uno de los caracteres del fichero de texto de nombre 
	 * LeerFichTexto.txt (localizado en una ruta concreta) y los muestra en  pantalla, los métodos read() 
	 * pueden lanzar la excepción IOException, por ello en main() se ha añadido
	 * throws IOException ya que no se incluye el manejador try-catch
	 */
	
	public static void main(String[] args) {
		try {
			leerCaracteres(Paths.get("./LeerFichTexto.txt"));
		}catch(IOException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	private static boolean leerCaracteres(Path p) throws IOException {
		try(FileReader reader= new FileReader(p.toFile())){
			//Como sé que se va a leer un fichero de texto, elijo FileReader
			//Podría usar InputStreamReader para especificar la codificación que se quiere leer,
			//pero como el ejercicio no ha especificado ninguna, lo dejo con el predeterminado
			//de FileReader
			while(reader.ready())
				System.out.print((char)reader.read()); //read lee un caracter
		}catch(FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero: " + e.getLocalizedMessage());
			return false;
		}
		return true;
	}
}
